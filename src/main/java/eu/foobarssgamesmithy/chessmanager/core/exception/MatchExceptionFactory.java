package eu.foobarssgamesmithy.chessmanager.core.exception;

import java.util.UUID;

public class MatchExceptionFactory{

    public static MatchException notFound(UUID id) {
        return new MatchNotFoundException(String.format("Match with id %s not found", id));
    }

}
