package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.service.data.MatchDto;
import eu.foobarssgamesmithy.chessmanager.service.data.PlayedPieces;
import eu.foobarssgamesmithy.chessmanager.service.data.ResultDto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.*;

public class MatchDtoFixtures {

    public static MatchDto aMatch(){
        return MatchDto.builder()
                .id(MATCH_WITH_RESULT_UUID)
                .playedAt(formatZonedDateTimeForDto(PLAYED_AT))
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

    public static MatchDto savedMatch1(){
        return MatchDto.builder()
                .id(SAVED_MATCH_UUID_1)
                .playedAt(formatZonedDateTimeForDto(
                        ZonedDateTime.of(LocalDateTime.of(2024,10,18,12,30, 0),
                        STANDARD_ZONE)))
                .playedWith(PlayedPieces.WHITE)
                .build();
    }

    public static MatchDto savedMatch2(){
        return MatchDto.builder()
                .id(SAVED_MATCH_UUID_2)
                .playedAt(formatZonedDateTimeForDto(
                        ZonedDateTime.of(LocalDateTime.of(2024,10,19,5,7, 0),
                        STANDARD_ZONE)))
                .playedWith(PlayedPieces.BLACK)
                .build();
    }

}
