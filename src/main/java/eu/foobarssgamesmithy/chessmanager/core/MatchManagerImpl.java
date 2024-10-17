package eu.foobarssgamesmithy.chessmanager.core;

import eu.foobarssgamesmithy.chessmanager.persistence.MatchRepository;
import eu.foobarssgamesmithy.chessmanager.persistence.entity.MatchEntity;
import org.springframework.stereotype.Component;

@Component
public class MatchManagerImpl implements MatchManager {

    private final MatchRepository matchRepository;

    public MatchManagerImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Override
    public MatchBo saveMatch(MatchBo match) {
        MatchEntity entity = new MatchEntity();
        entity.setPlayedAt(match.getPlayedAt());
        this.matchRepository.save(entity);
        match.setId(entity.getId());
        return match;
    }
}
