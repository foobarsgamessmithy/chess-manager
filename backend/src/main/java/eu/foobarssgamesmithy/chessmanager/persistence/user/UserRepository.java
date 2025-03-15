package eu.foobarssgamesmithy.chessmanager.persistence.user;

import eu.foobarssgamesmithy.chessmanager.persistence.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUserName(String userName);

    Optional<UserEntity> findByLichessUsername(String userName);

}
