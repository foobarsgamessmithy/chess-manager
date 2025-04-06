package eu.foobarssgamesmithy.chessmanager.persistence.importer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "MATCH_IMPORT")
@NoArgsConstructor
public class MatchImportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATCH_IMPORT_SEQ_GEN")
    @SequenceGenerator(name = "MATCH_IMPORT_SEQ_GEN", sequenceName = "MATCH_IMPORT_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "lichess_user_name")
    private String lichessUsername;

    @Column(name = "last_import")
    private Timestamp lastImport;

    @Column(name = "auto_import")
    private boolean autoImport;

}
