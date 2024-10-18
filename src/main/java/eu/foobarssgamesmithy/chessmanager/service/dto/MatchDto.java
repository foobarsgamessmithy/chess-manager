package eu.foobarssgamesmithy.chessmanager.service.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class MatchDto {

    private Long id;

    private String playedAt;

    private PlayedPieces playedWith;

    private ResultDto result;

}
