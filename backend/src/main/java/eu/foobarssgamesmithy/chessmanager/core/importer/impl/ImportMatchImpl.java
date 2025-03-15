package eu.foobarssgamesmithy.chessmanager.core.importer.impl;

import eu.foobarssgamesmithy.chessmanager.common.HttpClient;
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
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Component
public class ImportMatchImpl {

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

    public void importMatch(String lichessUsername) throws UserNotFoundException {
        String result = httpClient.get(getMatchByUser + lichessUsername, new HashMap<>());
        List<LichessMatchDto> matchList = this.mapper.mapMatchList(result);
        List<MatchBo> matches = new ArrayList<>();
        UserBo user = this.userFacade.getUsersByLichessName(lichessUsername);
        for (LichessMatchDto matchDto : matchList){
            PlayedPieces playedWith = PlayedPieces.WHITE;
            if(matchDto.getPlayers().getBlack().getUser().getId().equals(lichessUsername)) {
                playedWith = PlayedPieces.BLACK;
            }

            matches.add(MatchBo.builder()
                            .playedAt(
                                    Instant.ofEpochMilli(Long.parseLong(matchDto.getCreatedAt())).atZone(ZoneId.of("Europe/Paris"))
                            )
                    .playedWith(playedWith)
                    .user(user)
                    .result(ResultBo.builder()
                            .moves(createMoveList(matchDto.getMoves()))
                            .winner(WinnerTyp.valueOf(matchDto.getWinner().toUpperCase(Locale.ROOT)))
                            .reason(MatchEndReasonTyp.valueOf(matchDto.getStatus().toUpperCase(Locale.ROOT)))
                            .build())
                    .build());
        }
        this.matchManager.saveMatches(matches);
    }

    private List<String> createMoveList(String moves) {
        List<String> moveList = new ArrayList<>();
        String[] splitMoves = moves.split(" ");
        for(int i=0; i < splitMoves.length; i+=2){
            String move = splitMoves[i];
            if( i+1 < splitMoves.length) {
                move += " " + splitMoves[i+1];
            }
            moveList.add(move);
        }

        return moveList;
    }

}
