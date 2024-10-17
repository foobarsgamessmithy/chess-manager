package eu.foobarssgamesmithy.chessmanager.service;

import eu.foobarssgamesmithy.chessmanager.core.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.MatchManager;
import eu.foobarssgamesmithy.chessmanager.service.dto.MatchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@RestController
public class MatchController {

    private final MatchManager matchManager;

    public MatchController(MatchManager matchManager) {
        this.matchManager = matchManager;
    }

    @PostMapping("/api/match")
    public ResponseEntity<MatchDto> createMatch(
            @RequestBody MatchDto matchDto
            ){
        MatchBo match = new MatchBo();
        match.setPlayedAt(ZonedDateTime.parse( matchDto.getPlayedAt()));
        match =  this.matchManager.saveMatch(match);
        matchDto.setId(Math.toIntExact(match.getId()));
        return ResponseEntity.ok().body(matchDto);
    }

    @GetMapping("/api/match")
    public ResponseEntity<String> getMatch(){
        return ResponseEntity.ok().build();
    }

}
