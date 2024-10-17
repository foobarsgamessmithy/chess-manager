package eu.foobarssgamesmithy.chessmanager.service;

import eu.foobarssgamesmithy.chessmanager.service.dto.MatchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController {

    @PostMapping("/api/match")
    public ResponseEntity<MatchDto> createMatch(
            @RequestBody MatchDto matchDto
            ){
        return ResponseEntity.ok().body(matchDto);
    }

    @GetMapping("/api/match")
    public ResponseEntity<String> getMatch(){
        return ResponseEntity.ok().build();
    }

}
