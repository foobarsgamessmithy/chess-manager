package eu.foobarssgamesmithy.chessmanager.core.match.exception;

import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;

import java.util.UUID;

public class MatchExceptionFactory{

    public static MatchException notFound(UUID id) {
        return new MatchNotFoundException(String.format("Match with id %s not found.", id));
    }

    public static MatchException notOwner(UUID id, UserBo user) {
        return new MatchNotOwnedByUserException(
                String.format("Match with id %s does not belong to user %s.", id, user.getUserName()));
    }
}
