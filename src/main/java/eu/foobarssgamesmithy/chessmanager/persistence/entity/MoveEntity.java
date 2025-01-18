package eu.foobarssgamesmithy.chessmanager.persistence.entity;

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

    private String figure;

    private String field;

    private boolean hasCaptured;

    private boolean isEnPassant;

    private boolean hasPromoted;

    private boolean isCheck;

    private boolean isMade;

}
