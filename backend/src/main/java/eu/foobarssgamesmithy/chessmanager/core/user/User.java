package eu.foobarssgamesmithy.chessmanager.core.user;

import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import eu.foobarssgamesmithy.chessmanager.core.user.exception.UserException;
import eu.foobarssgamesmithy.chessmanager.core.user.exception.UserNotFoundException;

public interface User {

    UserBo getUser();

    UserBo getUserById(String userId) throws UserException;

    UserBo setLichessUsername(String userId, String lichessUserName) throws UserNotFoundException;

    UserBo setAutoImport(String userId, boolean isAutoImport) throws UserNotFoundException;
}
