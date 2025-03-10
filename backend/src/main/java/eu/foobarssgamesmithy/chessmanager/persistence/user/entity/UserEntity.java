package eu.foobarssgamesmithy.chessmanager.persistence.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
// 'User' is a reserved keyword so for now we use PLAYER
@Table(name = "PLAYER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAYER_SEQ_GEN")
    @SequenceGenerator(name = "PLAYER_SEQ_GEN", sequenceName = "PLAYER_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "lichess_user_name")
    private String lichessUsername;

    @Column(name = "auto_import")
    private boolean autoImport;

}
