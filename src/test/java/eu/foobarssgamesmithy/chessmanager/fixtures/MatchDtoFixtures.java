package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.service.dto.MatchDto;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.PLAYED_AT;

public class MatchDtoFixtures {

    public static MatchDto aMatch(){
        return MatchDto.builder()
                .id(100L)
                .playedAt(PLAYED_AT.toString())
                .build();
    }

}
