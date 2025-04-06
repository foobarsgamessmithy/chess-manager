package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.core.messaging.data.AutoImportMessage;

public class MessageFixtures {

    public static AutoImportMessage aAutoImportMessage() {
        return AutoImportMessage.builder()
                .id(2L)
                .userName("MagnusC")
                .lichessUsername("drdrunkenstein")
                .autoImport(true)
                .build();
    }

}
