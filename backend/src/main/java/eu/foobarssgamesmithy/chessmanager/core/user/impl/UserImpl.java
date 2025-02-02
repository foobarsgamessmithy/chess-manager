package eu.foobarssgamesmithy.chessmanager.core.user.impl;

import eu.foobarssgamesmithy.chessmanager.auth.AuthenticationFacade;
import eu.foobarssgamesmithy.chessmanager.core.mapper.BoEtyMapper;
import eu.foobarssgamesmithy.chessmanager.core.user.User;
import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import eu.foobarssgamesmithy.chessmanager.persistence.user.UserRepository;
import eu.foobarssgamesmithy.chessmanager.persistence.user.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UserImpl implements User {

    private final AuthenticationFacade authenticationFacade;

    private final UserRepository repository;

    private final BoEtyMapper mapper;

    public UserImpl(AuthenticationFacade authenticationFacade, UserRepository repository, BoEtyMapper mapper) {
        this.authenticationFacade = authenticationFacade;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public UserBo getUser() {
        String userName = this.authenticationFacade.getUserName();
        Optional<UserEntity> userOpt = this.repository.findByUserName(userName);
        UserEntity user = userOpt.orElseGet(() -> this.repository.save(UserEntity.builder().userName(userName).build()));
        return this.mapper.mapUser(user);
    }
}
