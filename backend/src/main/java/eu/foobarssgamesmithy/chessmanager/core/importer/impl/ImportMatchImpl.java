package eu.foobarssgamesmithy.chessmanager.core.importer.impl;

import eu.foobarssgamesmithy.chessmanager.common.HttpClient;
import eu.foobarssgamesmithy.chessmanager.core.importer.ImportMatch;
import eu.foobarssgamesmithy.chessmanager.core.importer.data.LichessMatchDto;
import eu.foobarssgamesmithy.chessmanager.core.importer.mapper.LichessMapper;
import eu.foobarssgamesmithy.chessmanager.core.match.MatchManager;
import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.match.data.ResultBo;
import eu.foobarssgamesmithy.chessmanager.core.user.User;
import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import eu.foobarssgamesmithy.chessmanager.core.user.exception.UserNotFoundException;
import eu.foobarssgamesmithy.chessmanager.service.match.data.MatchEndReasonTyp;
import eu.foobarssgamesmithy.chessmanager.service.match.data.PlayedPieces;
import eu.foobarssgamesmithy.chessmanager.service.match.data.WinnerTyp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ImportMatchImpl implements ImportMatch {

    private static final Logger LOG = LoggerFactory.getLogger(ImportMatchImpl.class);

    // TODO move to application property
    private final String getMatchByUser = "https://lichess.org/api/games/user/";

    private final HttpClient httpClient;

    private final LichessMapper mapper;

    private final MatchManager matchManager;

    private final User userFacade;

    public ImportMatchImpl(HttpClient httpClient, LichessMapper mapper, MatchManager matchManager, User userFacade) {
        this.httpClient = httpClient;
        this.mapper = mapper;
        this.matchManager = matchManager;
        this.userFacade = userFacade;
    }

    @Override
    public void importMatchesByLichessUser(String lichessUsername) throws UserNotFoundException {
        LOG.info("Import lichess matches for {}.", lichessUsername);
        // TODO react to http client error
        String response = this.httpClient.get(getMatchByUser + lichessUsername, new HashMap<>());
        List<LichessMatchDto> matchList = this.mapper.mapMatchList(response);
        List<MatchBo> matches = new ArrayList<>();
        UserBo user = this.userFacade.getUsersByLichessName(lichessUsername);
        if (!matchList.isEmpty()) {
            LOG.info("Found {} matches for importing.", matchList.size());
            Set<String> externalIds = this.matchManager.getMatchesByUser(user).stream()
                    .map(MatchBo::getExternalId).collect(Collectors.toSet());
            for (LichessMatchDto matchDto : matchList) {
                if (!externalIds.contains(matchDto.getId())) {
                    LOG.debug("Try mapping match with id {}.", matchDto.getId());
                    matches.add(mapMatch(user, matchDto));
                }
            }
        }
        if (!matches.isEmpty()) {
            LOG.debug("Import {} matches.", matches.size());
            this.matchManager.saveMatches(matches);
        }
    }

    private MatchBo mapMatch(UserBo user, LichessMatchDto matchDto) {
        PlayedPieces playedWith = PlayedPieces.WHITE;
        if (matchDto.getPlayers().getBlack().getUser().getId().equals(user.getLichessUsername())) {
            playedWith = PlayedPieces.BLACK;
        }
        MatchEndReasonTyp reason = MatchEndReasonTyp.UNKOWN;
        try {
            reason = MatchEndReasonTyp.valueOf(matchDto.getStatus().toUpperCase(Locale.ROOT));
        } catch (Exception e) {
            LOG.warn("Unknown result reason in Lichess match: {}", matchDto.getStatus());
        }

        WinnerTyp winner = WinnerTyp.DRAW;
        if (matchDto.getWinner() != null) {
            winner = WinnerTyp.valueOf(matchDto.getWinner().toUpperCase(Locale.ROOT));
        }
        return MatchBo.builder()
                .externalId(matchDto.getId())
                .playedAt(
                        Instant.ofEpochMilli(Long.parseLong(matchDto.getCreatedAt()))
                                .atZone(ZoneId.of("Europe/Paris"))
                )
                .playedWith(playedWith)
                .user(user)
                .result(ResultBo.builder()
                        .moves(createMoveList(matchDto.getMoves()))
                        .winner(winner)
                        .reason(reason)
                        .build())
                .build();
    }

    private List<String> createMoveList(String moves) {
        List<String> moveList = new ArrayList<>();
        String[] splitMoves = moves.split(" ");
        for (int i = 0; i < splitMoves.length; i += 2) {
            String move = splitMoves[i];
            if (i + 1 < splitMoves.length) {
                move += " " + splitMoves[i + 1];
            }
            moveList.add(move);
        }
        return moveList;
    }

}
