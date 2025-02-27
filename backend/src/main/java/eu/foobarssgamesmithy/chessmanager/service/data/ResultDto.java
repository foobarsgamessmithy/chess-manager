package eu.foobarssgamesmithy.chessmanager.service.data;

import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ResultDto {

    private WinnerTyp winner;

    private MatchEndReasonTyp reason;

    private List<String> moves;

}
