package eu.foobarssgamesmithy.chessmanager.core.user.impl;

import eu.foobarssgamesmithy.chessmanager.auth.AuthenticationFacade;
import eu.foobarssgamesmithy.chessmanager.core.mapper.BoEtyMapper;
import eu.foobarssgamesmithy.chessmanager.core.messaging.MessagePublisher;
import eu.foobarssgamesmithy.chessmanager.core.user.User;
import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import eu.foobarssgamesmithy.chessmanager.core.user.exception.UserException;
import eu.foobarssgamesmithy.chessmanager.core.user.exception.UserExceptionFactory;
import eu.foobarssgamesmithy.chessmanager.core.user.exception.UserNotFoundException;
import eu.foobarssgamesmithy.chessmanager.persistence.user.UserRepository;
import eu.foobarssgamesmithy.chessmanager.persistence.user.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static eu.foobarssgamesmithy.chessmanager.core.messaging.MessageTopics.AUTO_IMPORT_TOPIC;

@Component
public class UserImpl implements User {

    private final AuthenticationFacade authenticationFacade;

    private final UserRepository repository;

    private final BoEtyMapper mapper;

    private final MessagePublisher messagePublisher;

    private static final Logger LOG = LoggerFactory.getLogger(UserImpl.class);

    public UserImpl(AuthenticationFacade authenticationFacade, UserRepository repository, BoEtyMapper mapper,
                    MessagePublisher messagePublisher) {
        this.authenticationFacade = authenticationFacade;
        this.repository = repository;
        this.mapper = mapper;
        this.messagePublisher = messagePublisher;
    }

    @Transactional
    @Override
    public UserBo getUser() {
        String userName = this.authenticationFacade.getUserName();
        Optional<UserEntity> userOpt = this.repository.findByUserName(userName);
        UserEntity user = userOpt.orElseGet(() -> this.repository.save(UserEntity.builder().userName(userName).build()));
        LOG.debug("Get user from repository: {}.", user);
        return this.mapper.mapUser(user);
    }

    @Transactional
    @Override
    public UserBo getUserById(String userId) throws UserException {
        Optional<UserEntity> userOpt = this.repository.findByUserName(userId);
        UserEntity userEty = userOpt.orElseThrow(() -> UserExceptionFactory.notFound(userId));
        return this.mapper.mapUser(userEty);
    }

    @Transactional
    @Override
    public UserBo setLichessUsername(String userId, String lichessUserName) throws UserNotFoundException {
        UserEntity user = getUser(userId);
        user.setLichessUsername(lichessUserName);
        return this.mapper.mapUser(user);
    }

    @Transactional
    @Override
    public UserBo setAutoImport(String userId, boolean isAutoImport) throws UserNotFoundException {
        UserEntity user = getUser(userId);
        user.setAutoImport(isAutoImport);
        if(isAutoImport) {
            publishSetAutoImport(userId);
        }
        return this.mapper.mapUser(user);
    }

    private void publishSetAutoImport(String userId) {
        messagePublisher.sendMessage(AUTO_IMPORT_TOPIC, userId);
    }

    private UserEntity getUser(String userId) throws UserNotFoundException{
        Optional<UserEntity> userOpt = this.repository.findByUserName(userId);
        return userOpt.orElseThrow(() -> UserExceptionFactory.notFound(userId));
    }
}
