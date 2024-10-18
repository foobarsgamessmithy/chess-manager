package eu.foobarssgamesmithy.chessmanager.persistence.entity;

import eu.foobarssgamesmithy.chessmanager.service.dto.PlayedPieces;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private ZonedDateTime playedAt;

    private PlayedPieces playedWith;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private ResultEntity result;

}
