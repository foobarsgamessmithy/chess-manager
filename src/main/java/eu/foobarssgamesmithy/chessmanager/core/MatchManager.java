package eu.foobarssgamesmithy.chessmanager.core;

import eu.foobarssgamesmithy.chessmanager.core.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.exception.MatchException;

import java.util.List;
import java.util.UUID;

public interface MatchManager {

    MatchBo saveMatch(MatchBo match);

    MatchBo getMatch(UUID id) throws MatchException;

    List<MatchBo> getMatches();

    void deleteMatch(UUID uuid);
}
