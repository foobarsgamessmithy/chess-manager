package eu.foobarssgamesmithy.chessmanager.core.user;

import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import eu.foobarssgamesmithy.chessmanager.core.user.exception.UserException;

public interface User {

    UserBo getUser();

    UserBo getUserById(String userId) throws UserException;
}
