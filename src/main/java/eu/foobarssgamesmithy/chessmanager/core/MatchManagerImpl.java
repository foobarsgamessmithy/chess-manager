package eu.foobarssgamesmithy.chessmanager.core;

import eu.foobarssgamesmithy.chessmanager.core.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.exception.MatchException;
import eu.foobarssgamesmithy.chessmanager.core.exception.MatchExceptionFactory;
import eu.foobarssgamesmithy.chessmanager.core.mapper.BoEtyMapper;
import eu.foobarssgamesmithy.chessmanager.persistence.MatchRepository;
import eu.foobarssgamesmithy.chessmanager.persistence.entity.MatchEntity;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class MatchManagerImpl implements MatchManager {

    private final MatchRepository matchRepository;

    private final BoEtyMapper mapper;

    public MatchManagerImpl(MatchRepository matchRepository, BoEtyMapper mapper) {
        this.matchRepository = matchRepository;
        this.mapper = mapper;
    }

    @Override
    public MatchBo saveMatch(MatchBo match) {
        MatchEntity entity = this.matchRepository.save(this.mapper.mapMatch(match));
        return this.mapper.mapMatch(entity);
    }

    @Override
    public void deleteMatch(UUID uuid) {
        this.matchRepository.deleteById(uuid);
    }

    @Override
    public MatchBo getMatch(UUID id) throws MatchException {
        Optional<MatchEntity> matchOpt = this.matchRepository.findById(id);
        if(!matchOpt.isPresent()){
            throw MatchExceptionFactory.notFound(id);
        }
        return this.mapper.mapMatch(matchOpt.get());
    }

    @Override
    public List<MatchBo> getMatches() {
        return this.mapper.mapMatches(Streamable.of(this.matchRepository.findAll()).toList());
    }
}
