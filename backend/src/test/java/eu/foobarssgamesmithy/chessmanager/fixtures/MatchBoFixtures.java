package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.match.data.ResultBo;
import eu.foobarssgamesmithy.chessmanager.service.data.MatchEndReasonTyp;
import eu.foobarssgamesmithy.chessmanager.service.data.PlayedPieces;
import eu.foobarssgamesmithy.chessmanager.service.data.WinnerTyp;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.*;
import static eu.foobarssgamesmithy.chessmanager.fixtures.UserBoFixtures.aUser;

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
}
