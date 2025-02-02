package eu.foobarssgamesmithy.chessmanager.persistence.match.entity;

import eu.foobarssgamesmithy.chessmanager.core.match.data.Castle;
import eu.foobarssgamesmithy.chessmanager.core.match.data.Promotion;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MOVE")
public class MoveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOVE_SEQ_GEN")
    @SequenceGenerator(name = "MOVE_SEQ_GEN", sequenceName = "MOVE_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "figure")
    private String figure;

    @Column(name = "field")
    private String field;

    @Column(name = "has_captured")
    private boolean hasCaptured;

    @Column(name = "is_en_passant")
    private boolean isEnPassant;

    @Enumerated(EnumType.STRING)
    @Column(name = "promoted")
    private Promotion promotion;

    @Column(name = "is_check")
    private boolean isCheck;

    @Column(name = "is_made")
    private boolean isMade;

    @Enumerated(EnumType.STRING)
    @Column(name = "castle")
    private Castle castle;

    @Column(name = "pawn")
    private String pawn;

    @Column(name = "file")
    private String file;

    @Column(name = "rank")
    private String rank;

}
