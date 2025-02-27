package eu.foobarssgamesmithy.chessmanager.core.user.exception;

public class UserExceptionFactory {

    public static UserNotFoundException notFound(String userId) {
        return new UserNotFoundException(String.format("User with the id %s not found.", userId), userId);
    }

}
