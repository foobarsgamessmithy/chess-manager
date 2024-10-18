package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.service.dto.MatchDto;
import eu.foobarssgamesmithy.chessmanager.service.dto.PlayedPieces;
import eu.foobarssgamesmithy.chessmanager.service.dto.ResultDto;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.PLAYED_AT;

public class MatchDtoFixtures {

    public static MatchDto aMatch(){
        return MatchDto.builder()
                .id(100L)
                .playedAt(PLAYED_AT.toString())
                .playedWith(PlayedPieces.WHITE)
                .result(aResult())
                .build();
    }

    public static ResultDto aResult(){
        return ResultDto.builder()
                .pointsBlack(0)
                .pointsWhite(1)
                .build();
    }

}
