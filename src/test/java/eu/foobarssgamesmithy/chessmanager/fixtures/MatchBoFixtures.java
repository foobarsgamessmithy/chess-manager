package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.core.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.data.ResultBo;
import eu.foobarssgamesmithy.chessmanager.service.data.PlayedPieces;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.MATCH_UUID;
import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.PLAYED_AT;

public class MatchBoFixtures {

    public static MatchBo aMatch(){
        return MatchBo.builder()
                .id(MATCH_UUID)
                .playedAt(PLAYED_AT)
                .playedWith(PlayedPieces.WHITE)
                .result(aResult())
                .build();
    }

    public static ResultBo aResult(){
        return ResultBo.builder()
                .pointsBlack(0)
                .pointsWhite(1)
                .build();
    }
}
