package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.core.MatchBo;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.PLAYED_AT;

public class MatchBoFixtures {

    public static MatchBo aMatch(){
        return MatchBo.builder()
                .id(100L)
                .playedAt(PLAYED_AT)
                .build();
    }
}
