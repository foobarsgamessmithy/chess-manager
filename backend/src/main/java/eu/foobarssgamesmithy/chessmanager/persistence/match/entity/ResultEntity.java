package eu.foobarssgamesmithy.chessmanager.persistence.match.entity;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESULT_SEQ_GEN")
    @SequenceGenerator(name = "RESULT_SEQ_GEN", sequenceName = "RESULT_SEQ", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private WinnerTyp winner;

    @Enumerated(EnumType.STRING)
    private MatchEndReasonTyp reason;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="RESULT_ID")
    private List<MoveEntity> moves;

}
