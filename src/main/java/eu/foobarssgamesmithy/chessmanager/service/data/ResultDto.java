package eu.foobarssgamesmithy.chessmanager.service.data;

import lombok.*;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ResultDto {

    private double pointsWhite;

    private double pointsBlack;

}
