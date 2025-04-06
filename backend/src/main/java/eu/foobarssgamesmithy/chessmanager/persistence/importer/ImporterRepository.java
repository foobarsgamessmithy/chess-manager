package eu.foobarssgamesmithy.chessmanager.persistence.importer;

import eu.foobarssgamesmithy.chessmanager.persistence.importer.entity.MatchImportEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ImporterRepository extends CrudRepository<MatchImportEntity, Long> {

    List<MatchImportEntity> findByAutoImportTrueAndLastImportBefore(Timestamp lastImport);

    Optional<MatchImportEntity> findByUserName(String userName);

}
