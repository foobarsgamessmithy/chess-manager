package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.match.data.ResultBo;
import eu.foobarssgamesmithy.chessmanager.service.match.data.MatchEndReasonTyp;
import eu.foobarssgamesmithy.chessmanager.service.match.data.PlayedPieces;
import eu.foobarssgamesmithy.chessmanager.service.match.data.WinnerTyp;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.*;
import static eu.foobarssgamesmithy.chessmanager.fixtures.UserBoFixtures.aUser;
import static eu.foobarssgamesmithy.chessmanager.fixtures.UserBoFixtures.anotherUser;

public class MatchBoFixtures {

    public static MatchBo aMatch(){
        return MatchBo.builder()
                .id(MATCH_WITH_RESULT_UUID)
                .playedAt(PLAYED_AT)
                .playedWith(PlayedPieces.WHITE)
                .result(aResult())
                .user(aUser())
                .build();
    }

    public static ResultBo aResult(){
        return ResultBo.builder()
                .winner(WinnerTyp.WHITE)
                .reason(MatchEndReasonTyp.CHECK_MADE)
                .moves(MOVE_LIST)
                .build();
    }

    public static ResultBo aImportedResult(){
        return ResultBo.builder()
                .winner(WinnerTyp.BLACK)
                .reason(MatchEndReasonTyp.RESIGN)
                .moves(IMPORTED_MOVE_LIST)
                .build();
    }

    public static Object aImportedMatch() {
        return MatchBo.builder()
                .id(null)
                .playedAt(IMPORTED_MATCH_PLAYED_AT)
                .playedWith(PlayedPieces.WHITE)
                .result(aImportedResult())
                .user(anotherUser())
                .build();
    }
}
