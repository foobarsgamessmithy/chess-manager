package eu.foobarssgamesmithy.chessmanager.service.dto;

import java.time.ZonedDateTime;

public class MatchDto {

    private Integer id;

    private String playedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlayedAt() {
        return playedAt;
    }

    public void setPlayedAt(String playedAt) {
        this.playedAt = playedAt;
    }
}
