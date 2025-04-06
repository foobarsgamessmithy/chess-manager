package eu.foobarssgamesmithy.chessmanager.core.match.data;

import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import eu.foobarssgamesmithy.chessmanager.service.match.data.PlayedPieces;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder(access = AccessLevel.PUBLIC)
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MatchBo {

    private UUID id;

    private String externalId;

    private ZonedDateTime playedAt;

    private PlayedPieces playedWith;

    private ResultBo result;

    private UserBo user;

}
