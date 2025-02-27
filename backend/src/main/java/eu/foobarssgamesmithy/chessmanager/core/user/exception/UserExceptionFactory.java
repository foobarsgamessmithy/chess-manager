package eu.foobarssgamesmithy.chessmanager.core.user.exception;

public class UserExceptionFactory {

    public static UserException notFound(String userId) {
        return new UserException(String.format("User with the id %s not found.", userId));
    }

}
