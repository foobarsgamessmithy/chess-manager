package eu.foobarssgamesmithy.chessmanager.core.messaging.data;

import lombok.*;

@Builder(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AutoImportMessage extends BaseChessManagerMessage {

    private Long id;

    private String userName;

    private String lichessUsername;

    private boolean autoImport;
}
