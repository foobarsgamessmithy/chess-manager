package eu.foobarssgamesmithy.chessmanager.service.user.data;

import lombok.*;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserDto {

    private String userName;

    private String lichessUsername;

    private boolean autoImport;

}
