package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.service.user.data.UserDto;

public class UserDtoFixtures {

    public static UserDto aUser(){
        return UserDto.builder()
                .userName("foobar")
                .build();
    }

    public static UserDto anotherUser(){
        return UserDto.builder()
                .userName("drdrunkenstein")
                .lichessUsername("drdrunkenstein")
                .build();
    }

}
