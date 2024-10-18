package eu.foobarssgamesmithy.chessmanager.core;

import eu.foobarssgamesmithy.chessmanager.core.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.exception.MatchException;

public interface MatchManager {

    MatchBo saveMatch(MatchBo match);

    MatchBo getMatch(Long id) throws MatchException;
}
