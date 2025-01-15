package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.persistence.entity.MatchEntity;
import eu.foobarssgamesmithy.chessmanager.persistence.entity.ResultEntity;
import eu.foobarssgamesmithy.chessmanager.service.data.PlayedPieces;

import java.util.UUID;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.*;

public class MatchEtyFixtures {

    public static MatchEntity aMatchWithoutResult(UUID matchId){
        MatchEntity match = aMatchWithoutResult();
        match.setMatchId(matchId);
        return match;
    }

    public static MatchEntity aMatchWithoutResult(){
        return MatchEntity.builder()
                .matchId(MATCH_WITHOUT_RESULT_UUID)
                .playedAt(PLAYED_AT)
                .playedWith(PlayedPieces.WHITE)
                .build();
    }

    public static MatchEntity aMatchWithResult(UUID matchId){
        MatchEntity match = aMatchWithResult();
        match.setMatchId(matchId);
        return match;
    }

    public static MatchEntity aMatchWithResult(){
        return MatchEntity.builder()
                .matchId(MATCH_WITH_RESULT_UUID)
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
