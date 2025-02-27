package eu.foobarssgamesmithy.chessmanager.core.match.exception;

public class MatchNotOwnedByUserException extends MatchException {

    private final String causedByUser;

    public MatchNotOwnedByUserException(String message, String user) {
        super(message);
        this.causedByUser = user;
    }

    public String getCausedByUser() {
        return causedByUser;
    }
}
