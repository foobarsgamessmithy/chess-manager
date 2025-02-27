package eu.foobarssgamesmithy.chessmanager.core.user.exception;

public class UserNotFoundException extends UserException{

    private final String userName;

    public UserNotFoundException(String message, String userName) {
        super(message);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
