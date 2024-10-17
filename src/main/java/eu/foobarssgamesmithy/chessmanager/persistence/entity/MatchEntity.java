package eu.foobarssgamesmithy.chessmanager.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.ZonedDateTime;

@Entity
public class MatchEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private ZonedDateTime playedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(ZonedDateTime playedAt) {
        this.playedAt = playedAt;
    }
}
