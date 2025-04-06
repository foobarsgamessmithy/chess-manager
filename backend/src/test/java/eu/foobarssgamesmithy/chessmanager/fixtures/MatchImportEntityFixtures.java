package eu.foobarssgamesmithy.chessmanager.fixtures;

import eu.foobarssgamesmithy.chessmanager.persistence.importer.entity.MatchImportEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class MatchImportEntityFixtures {

    public final static Timestamp LAST_IMPORT = Timestamp.valueOf(LocalDateTime.of(2023, 4, 5, 13, 11));

    public static MatchImportEntity aMatchImportEntity() {
        return MatchImportEntity.builder()
                .id(2L)
                .userName("MagnusC")
                .lichessUsername("drdrunkenstein")
                .lastImport(null)
                .autoImport(true)
                .build();
    }

}
