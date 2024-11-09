package eu.foobarssgamesmithy.chessmanager.core.data;

import eu.foobarssgamesmithy.chessmanager.service.data.PlayedPieces;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder(access = AccessLevel.PUBLIC)
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MatchBo {

    private UUID id;

    private ZonedDateTime playedAt;

    private PlayedPieces playedWith;

    private ResultBo result;

}
