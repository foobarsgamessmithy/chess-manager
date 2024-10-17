package eu.foobarssgamesmithy.chessmanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchController {

    @PostMapping("/api/match")
    public ResponseEntity<String> createMatch(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/api/match")
    public ResponseEntity<String> getMatch(){
        return ResponseEntity.ok().build();
    }

}
