package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.persistence.entity.MatchEntity;
import eu.foobarssgamesmithy.chessmanager.persistence.entity.ResultEntity;
import eu.foobarssgamesmithy.chessmanager.service.data.PlayedPieces;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.MATCH_UUID;
import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.PLAYED_AT;

public class MatchEtyFixtures {

    public static MatchEntity aMatch(){
        return MatchEntity.builder()
                .matchId(MATCH_UUID)
                .playedAt(PLAYED_AT)
                .playedWith(PlayedPieces.WHITE)
                .result(aResult())
                .build();
    }

    public static ResultEntity aResult(){
        return ResultEntity.builder()
                .pointsBlack(0)
                .pointsWhite(1)
                .build();
    }

}
