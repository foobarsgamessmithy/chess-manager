package eu.foobarssgamesmithy.chessmanager.persistence.entity;

import eu.foobarssgamesmithy.chessmanager.service.data.MatchEndReasonTyp;
import eu.foobarssgamesmithy.chessmanager.service.data.WinnerTyp;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RESULT")
public class ResultEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private WinnerTyp winner;

    @Enumerated(EnumType.ORDINAL)
    private MatchEndReasonTyp reason;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="MOVE_ID")
    private List<MoveEntity> moves;

}
