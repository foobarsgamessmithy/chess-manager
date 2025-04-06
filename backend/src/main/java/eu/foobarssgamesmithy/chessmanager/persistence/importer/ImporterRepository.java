package eu.foobarssgamesmithy.chessmanager.persistence.importer;

import eu.foobarssgamesmithy.chessmanager.persistence.importer.entity.MatchImportEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ImporterRepository extends CrudRepository<MatchImportEntity, Long> {

    Optional<MatchImportEntity> findByUserName(String userName);

}
