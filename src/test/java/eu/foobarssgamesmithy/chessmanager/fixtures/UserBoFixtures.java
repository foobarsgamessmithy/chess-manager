package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;

public class UserBoFixtures {

    public static UserBo aUser(){
        return UserBo.builder()
                .id(1L)
                .userName("foobar")
                .build();
    }

}
