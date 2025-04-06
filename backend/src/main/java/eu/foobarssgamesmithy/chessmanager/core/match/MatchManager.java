package eu.foobarssgamesmithy.chessmanager.core.match;

import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.match.exception.MatchException;
import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;

import java.util.List;
import java.util.UUID;

public interface MatchManager {

    MatchBo saveMatch(MatchBo match);

    MatchBo getMatch(UUID id) throws MatchException;

    List<MatchBo> getMatches();

    void deleteMatch(UUID uuid) throws MatchException;

    void saveMatches(List<MatchBo> matches);

    List<MatchBo> getMatchesByUser(UserBo user);
}
