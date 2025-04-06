package eu.foobarssgamesmithy.chessmanager.core.importer;

import eu.foobarssgamesmithy.chessmanager.core.user.exception.UserNotFoundException;

public interface ImportMatch {

    void importMatchesByLichessUser(String lichessUsername) throws UserNotFoundException;

}
