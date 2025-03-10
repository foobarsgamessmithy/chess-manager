package eu.foobarssgamesmithy.chessmanager.core.user.data;

import lombok.*;

@Builder(access = AccessLevel.PUBLIC)
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserBo {

    private Long id;

    private String userName;

    private String lichessUsername;

    private boolean autoImport;
}
