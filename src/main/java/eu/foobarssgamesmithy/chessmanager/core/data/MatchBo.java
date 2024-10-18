package eu.foobarssgamesmithy.chessmanager.core.data;

import eu.foobarssgamesmithy.chessmanager.service.dto.PlayedPieces;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder(access = AccessLevel.PUBLIC)
@Data
public class MatchBo {

    private Long id;

    private ZonedDateTime playedAt;

    private PlayedPieces playedWith;

    private ResultBo result;

}
