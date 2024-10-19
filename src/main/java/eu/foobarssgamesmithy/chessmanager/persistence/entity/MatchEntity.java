package eu.foobarssgamesmithy.chessmanager.persistence.entity;

import eu.foobarssgamesmithy.chessmanager.service.dto.PlayedPieces;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Builder
@Entity(name = "MATCH")
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "match_id")
    private Long id;

    @Column(name = "played_at")
    private ZonedDateTime playedAt;

    @Column(name = "played_with")
    private PlayedPieces playedWith;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private ResultEntity result;

}
