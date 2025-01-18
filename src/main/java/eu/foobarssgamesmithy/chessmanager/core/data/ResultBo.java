package eu.foobarssgamesmithy.chessmanager.core.data;

import eu.foobarssgamesmithy.chessmanager.service.data.MatchEndReasonTyp;
import eu.foobarssgamesmithy.chessmanager.service.data.WinnerTyp;
import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ResultBo {

    private Long id;

    private WinnerTyp winner;

    private MatchEndReasonTyp reason;

    private List<String> moves;

}
