package eu.foobarssgamesmithy.chessmanager.core.data;

import lombok.*;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ResultBo {

    private double pointsWhite;

    private double pointsBlack;

}
