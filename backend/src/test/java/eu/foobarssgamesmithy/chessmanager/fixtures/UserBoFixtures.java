package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;

public class UserBoFixtures {

    public static UserBo aUser() {
        return UserBo.builder()
                .id(1L)
                .userName("foobar")
                .build();
    }

    public static UserBo aOnlineUser() {
        return UserBo.builder()
                .id(2L)
                .userName("MagnusC")
                .lichessUsername("drdrunkenstein")
                .autoImport(true)
                .build();
    }

    public static UserBo aOfflineUser() {
        return UserBo.builder()
                .id(3L)
                .userName("HildegardOffline")
                .build();
    }

}
