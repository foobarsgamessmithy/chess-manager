package eu.foobarssgamesmithy.chessmanager.service;

import eu.foobarssgamesmithy.chessmanager.core.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.MatchManager;
import eu.foobarssgamesmithy.chessmanager.service.mapper.DtoBoMapper;
import eu.foobarssgamesmithy.chessmanager.service.dto.MatchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    private final MatchManager matchManager;

    private final DtoBoMapper mapper;

    public MatchController(MatchManager matchManager, DtoBoMapper mapper) {
        this.matchManager = matchManager;
        this.mapper = mapper;
    }

    @PostMapping("/api/match")
    public ResponseEntity<MatchDto> createMatch(
            @RequestBody MatchDto matchDto
            ){
        MatchBo match = this.mapper.mapMatch(matchDto);
        match = this.matchManager.saveMatch(match);
        return ResponseEntity.ok().body(this.mapper.mapMatch(match));
    }

    @GetMapping("/api/match")
    public ResponseEntity<String> getMatch(){
        return ResponseEntity.ok().build();
    }

}
