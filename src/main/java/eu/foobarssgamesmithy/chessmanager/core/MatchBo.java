package eu.foobarssgamesmithy.chessmanager.core;

import java.time.ZonedDateTime;

public class MatchBo {

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
