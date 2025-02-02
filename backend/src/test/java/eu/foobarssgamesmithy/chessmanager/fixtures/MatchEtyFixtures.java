package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.persistence.match.entity.MatchEntity;
import eu.foobarssgamesmithy.chessmanager.persistence.match.entity.MoveEntity;
import eu.foobarssgamesmithy.chessmanager.persistence.match.entity.ResultEntity;
import eu.foobarssgamesmithy.chessmanager.service.data.MatchEndReasonTyp;
import eu.foobarssgamesmithy.chessmanager.service.data.PlayedPieces;
import eu.foobarssgamesmithy.chessmanager.service.data.WinnerTyp;

import java.util.List;
import java.util.UUID;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.*;
import static eu.foobarssgamesmithy.chessmanager.fixtures.UserEtyFixtures.aUser;

public class MatchEtyFixtures {

    // "e4 e5", "Bc4 Nc6", "Qh5 Nf6", "Qxf7#"
    public static List<MoveEntity> aMoveList(){
        return List.of(
            MoveEntity.builder().field("e4").build(),
            MoveEntity.builder().field("e5").build(),
            MoveEntity.builder().field("c4").figure("B").build(),
            MoveEntity.builder().field("c6").figure("B").build(),
            MoveEntity.builder().field("h5").figure("Q").build(),
            MoveEntity.builder().field("f6").figure("N").build(),
            MoveEntity.builder().field("f7").figure("Q").hasCaptured(true).isMade(true).build()
        );
    }

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
                .user(aUser())
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
                .user(aUser())
                .build();
    }

    public static ResultEntity aResult(){
        return ResultEntity.builder()
                .winner(WinnerTyp.WHITE)
                .reason(MatchEndReasonTyp.CHECK_MADE)
                .moves(aMoveList())
                .build();
    }

}
