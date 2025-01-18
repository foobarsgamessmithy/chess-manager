package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.service.data.*;

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
                .winner(WinnerTyp.WHITE)
                .reason(MatchEndReasonTyp.CHECK_MADE)
                .moves(MOVE_LIST)
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
                .result(savedResultForMatch2())
                .build();
    }

    public static ResultDto savedResultForMatch2() {
        return ResultDto.builder()
                .reason(MatchEndReasonTyp.CHECK_MADE)
                .winner(WinnerTyp.WHITE)
                .moves(MOVE_LIST)
                .build();
    }

}
