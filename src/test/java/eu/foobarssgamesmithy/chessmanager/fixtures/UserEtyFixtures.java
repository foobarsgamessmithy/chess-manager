package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.persistence.user.entity.UserEntity;

public class UserEtyFixtures {

    public static UserEntity aUser(){
        return UserEntity.builder()
                .id(1L)
                .userName("foobar")
                .build();
    }

}
