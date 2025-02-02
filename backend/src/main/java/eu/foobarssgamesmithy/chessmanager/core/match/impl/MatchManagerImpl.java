package eu.foobarssgamesmithy.chessmanager.core.match.impl;

import eu.foobarssgamesmithy.chessmanager.core.match.MatchManager;
import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.match.exception.MatchException;
import eu.foobarssgamesmithy.chessmanager.core.match.exception.MatchExceptionFactory;
import eu.foobarssgamesmithy.chessmanager.core.mapper.BoEtyMapper;
import eu.foobarssgamesmithy.chessmanager.core.user.User;
import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import eu.foobarssgamesmithy.chessmanager.persistence.match.MatchRepository;
import eu.foobarssgamesmithy.chessmanager.persistence.match.entity.MatchEntity;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class MatchManagerImpl implements MatchManager {

    private final MatchRepository matchRepository;

    private final BoEtyMapper mapper;

    private final User userFacade;

    public MatchManagerImpl(MatchRepository matchRepository, BoEtyMapper mapper, User userFacade) {
        this.matchRepository = matchRepository;
        this.mapper = mapper;
        this.userFacade = userFacade;
    }

    @Transactional
    @Override
    public MatchBo saveMatch(MatchBo match) {
        UserBo user = this.userFacade.getUser();
        match.setUser(user);
        MatchEntity entity = this.matchRepository.save(this.mapper.mapMatch(match));
        return this.mapper.mapMatch(entity);
    }

    @Transactional
    @Override
    public void deleteMatch(UUID uuid) throws MatchException {
        if(this.getMatch(uuid) != null) {
            this.matchRepository.deleteById(uuid);
        }
    }

    @Transactional
    @Override
    public MatchBo getMatch(UUID id) throws MatchException {
        Optional<MatchEntity> matchOpt = this.matchRepository.findById(id);
        if(matchOpt.isEmpty()){
            throw MatchExceptionFactory.notFound(id);
        }
        MatchBo match = this.mapper.mapMatch(matchOpt.get());
        UserBo user = this.userFacade.getUser();
        if(!user.equals(match.getUser())) {
            throw MatchExceptionFactory.notOwner(id, user);
        }
        return match;
    }

    @Transactional
    @Override
    public List<MatchBo> getMatches() {
        List<MatchBo> matches = this.mapper.mapMatches(Streamable.of(this.matchRepository.findAll()).toList());
        UserBo user = this.userFacade.getUser();
        return matches.stream().filter(m -> m.getUser().equals(user)).toList();
    }
}
