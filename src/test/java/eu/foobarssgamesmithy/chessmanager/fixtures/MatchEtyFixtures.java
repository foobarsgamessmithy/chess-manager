package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.persistence.entity.MatchEntity;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.PLAYED_AT;

public class MatchEtyFixtures {

    public static MatchEntity aMatch(){
        return MatchEntity.builder()
                .id(100L)
                .playedAt(PLAYED_AT)
                .build();
    }

}
