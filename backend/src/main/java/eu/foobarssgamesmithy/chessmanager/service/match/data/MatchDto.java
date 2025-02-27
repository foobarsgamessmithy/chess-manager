package eu.foobarssgamesmithy.chessmanager.service.match.data;

import lombok.*;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MatchDto {

    private UUID id;

    private String playedAt;

    private PlayedPieces playedWith;

    private ResultDto result;

}
