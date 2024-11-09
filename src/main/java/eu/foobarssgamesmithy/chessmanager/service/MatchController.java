package eu.foobarssgamesmithy.chessmanager.service;

import eu.foobarssgamesmithy.chessmanager.core.MatchManager;
import eu.foobarssgamesmithy.chessmanager.core.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.exception.MatchException;
import eu.foobarssgamesmithy.chessmanager.service.data.MatchDto;
import eu.foobarssgamesmithy.chessmanager.service.mapper.DtoBoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private final MatchManager matchManager;

    private final DtoBoMapper mapper;

    public MatchController(MatchManager matchManager, DtoBoMapper mapper) {
        this.matchManager = matchManager;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<MatchDto> createMatch(@RequestBody MatchDto matchDto){
        MatchBo match = this.mapper.mapMatch(matchDto);
        match = this.matchManager.saveMatch(match);
        return ResponseEntity.ok().body(this.mapper.mapMatch(match));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMatch(@PathVariable("id") String id){
        try {
            if(this.matchManager.getMatch(UUID.fromString(id)) != null){
                this.matchManager.deleteMatch(UUID.fromString(id));
            }
        } catch (MatchException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> getMatch(@PathVariable("id") String id){
        try {
            MatchBo match = this.matchManager.getMatch(UUID.fromString(id));
            return ResponseEntity.ok().body(this.mapper.mapMatch(match));
        } catch (MatchException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<MatchDto>> getMatches(){
        List<MatchBo> matches = this.matchManager.getMatches();
        return ResponseEntity.ok().body(this.mapper.mapMatches(matches));
    }

}
