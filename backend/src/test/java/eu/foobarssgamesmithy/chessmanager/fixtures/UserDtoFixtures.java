package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.service.user.data.UserDto;

public class UserDtoFixtures {

    public static UserDto aUser() {
        return UserDto.builder()
                .userName("foobar")
                .build();
    }

    public static UserDto aOnlineUser() {
        return UserDto.builder()
                .userName("MagnusC")
                .lichessUsername("drdrunkenstein")
                .autoImport(true)
                .build();
    }

}
