package eu.foobarssgamesmithy.chessmanager.core.exception;

public class MatchExceptionFactory{

    public static MatchException notFound(Long id) {
        return new MatchNotFoundException(String.format("Match with id %s not found", id));
    }

}
