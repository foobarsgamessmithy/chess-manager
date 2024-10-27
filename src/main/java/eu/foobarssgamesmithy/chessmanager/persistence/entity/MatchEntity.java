package eu.foobarssgamesmithy.chessmanager.persistence.entity;

import eu.foobarssgamesmithy.chessmanager.service.dto.PlayedPieces;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Builder
@Entity
@Table(name = "MATCH")
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID matchId;

    @Column(name = "played_at")
    private ZonedDateTime playedAt;

    @Column(name = "played_with")
    private PlayedPieces playedWith;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "result_id")
    private ResultEntity result;

    public UUID getMatchId() {
        return matchId;
    }

    public void setMatchId(UUID matchId) {
        this.matchId = matchId;
    }

    public ZonedDateTime getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(ZonedDateTime playedAt) {
        this.playedAt = playedAt;
    }

    public PlayedPieces getPlayedWith() {
        return playedWith;
    }

    public void setPlayedWith(PlayedPieces playedWith) {
        this.playedWith = playedWith;
    }

    public ResultEntity getResult() {
        return result;
    }

    public void setResult(ResultEntity result) {
        this.result = result;
    }
}
