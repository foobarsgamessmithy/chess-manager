package eu.foobarssgamesmithy.chessmanager.persistence;

import eu.foobarssgamesmithy.chessmanager.persistence.entity.MatchEntity;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<MatchEntity, Long> {

}
