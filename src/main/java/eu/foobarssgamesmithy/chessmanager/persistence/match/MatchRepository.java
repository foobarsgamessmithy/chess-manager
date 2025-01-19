package eu.foobarssgamesmithy.chessmanager.persistence.match;

import eu.foobarssgamesmithy.chessmanager.persistence.match.entity.MatchEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface MatchRepository extends CrudRepository<MatchEntity, UUID> {

}
