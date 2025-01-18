package eu.foobarssgamesmithy.chessmanager.persistence.entity;

import eu.foobarssgamesmithy.chessmanager.service.data.PlayedPieces;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MATCH")
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID matchId;

    @Column(name = "played_at")
    private ZonedDateTime playedAt;

    @Column(name = "played_with")
    @Enumerated(EnumType.STRING)
    private PlayedPieces playedWith;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private ResultEntity result;

    public MatchEntity() {
    }
}
