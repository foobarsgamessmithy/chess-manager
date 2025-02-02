package eu.foobarssgamesmithy.chessmanager.service;

import eu.foobarssgamesmithy.chessmanager.core.match.MatchManager;
import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.match.exception.MatchException;
import eu.foobarssgamesmithy.chessmanager.core.match.exception.MatchNotFoundException;
import eu.foobarssgamesmithy.chessmanager.core.match.exception.MatchNotOwnedByUserException;
import eu.foobarssgamesmithy.chessmanager.service.data.MatchDto;
import eu.foobarssgamesmithy.chessmanager.service.mapper.DtoBoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchManager matchManager;

    private final DtoBoMapper mapper;

    private static final Logger LOG = LoggerFactory.getLogger(MatchController.class);

    public MatchController(MatchManager matchManager, DtoBoMapper mapper) {
        this.matchManager = matchManager;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<MatchDto> createMatch(@RequestBody MatchDto matchDto){
        try {
            MatchBo match = this.mapper.mapMatch(matchDto);
            match = this.matchManager.saveMatch(match);
            return ResponseEntity.ok().body(this.mapper.mapMatch(match));
        } catch (Throwable ex) {
            LOG.warn("Could not create match: {}", matchDto);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable("id") String id){
        try {
            this.matchManager.deleteMatch(UUID.fromString(id));
        } catch (MatchNotFoundException e) {
            LOG.info("Try to delete match which does not exist with id {}.", id);
            return ResponseEntity.notFound().build();
        } catch (MatchNotOwnedByUserException e) {
            LOG.warn("User {} tries to delete match {}, which is owned by another user.", e.getCausedByUser(), id);
            return ResponseEntity.badRequest().build();
        } catch (MatchException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> getMatch(@PathVariable("id") String id){
        try {
            MatchBo match = this.matchManager.getMatch(UUID.fromString(id));
            return ResponseEntity.ok().body(this.mapper.mapMatch(match));
        } catch (MatchException e) {
            LOG.info("Match with id {} could not be found.", id);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MatchDto>> getMatches(){
        List<MatchBo> matches = this.matchManager.getMatches();
        return ResponseEntity.ok().body(this.mapper.mapMatches(matches));
    }

}
