package eu.foobarssgamesmithy.chessmanager.service;

import eu.foobarssgamesmithy.chessmanager.core.MatchManager;
import eu.foobarssgamesmithy.chessmanager.core.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.exception.MatchException;
import eu.foobarssgamesmithy.chessmanager.service.dto.MatchDto;
import eu.foobarssgamesmithy.chessmanager.service.mapper.DtoBoMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<MatchDto> createMatch(
            @RequestBody MatchDto matchDto
            ){
        MatchBo match = this.mapper.mapMatch(matchDto);
        match = this.matchManager.saveMatch(match);
        return ResponseEntity.ok().body(this.mapper.mapMatch(match));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDto> getMatch(@PathVariable("id") Integer id){
        try {
            MatchBo match = this.matchManager.getMatch(Long.valueOf(id));
            return ResponseEntity.ok().body(this.mapper.mapMatch(match));
        } catch (MatchException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MatchDto>> getMatches(){
        try {
            // TODO fix
            MatchBo match = this.matchManager.getMatch(1L);
            return ResponseEntity.ok().body(List.of(this.mapper.mapMatch(match)));
        } catch (MatchException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
