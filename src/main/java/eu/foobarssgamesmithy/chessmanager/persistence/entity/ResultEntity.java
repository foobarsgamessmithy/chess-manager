package eu.foobarssgamesmithy.chessmanager.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private double pointsWhite;

    private double pointsBlack;

}
