package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.persistence.user.entity.UserEntity;

public class UserEtyFixtures {

    public static UserEntity aUser(){
        return UserEntity.builder()
                .id(1L)
                .userName("foobar")
                .build();
    }



    public static UserEntity anotherUser(){
        return UserEntity.builder()
                .id(2L)
                .userName("drdrunkenstein")
                .lichessUsername("drdrunkenstein")
                .build();
    }

}
