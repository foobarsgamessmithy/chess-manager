package eu.foobarssgamesmithy.chessmanager.service.user;

import eu.foobarssgamesmithy.chessmanager.core.user.User;
import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import eu.foobarssgamesmithy.chessmanager.core.user.exception.UserException;
import eu.foobarssgamesmithy.chessmanager.core.user.exception.UserNotFoundException;
import eu.foobarssgamesmithy.chessmanager.service.mapper.DtoBoMapper;
import eu.foobarssgamesmithy.chessmanager.service.user.data.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final User userFacade;

    private final DtoBoMapper mapper;

    public UserController(User userFacade, DtoBoMapper mapper) {
        this.userFacade = userFacade;
        this.mapper = mapper;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getMatch(@PathVariable("userId") String userId){
        try {
            UserBo user = this.userFacade.getUserById(userId);
            return ResponseEntity.ok().body(this.mapper.mapUser(user));
        } catch (UserException e) {
            LOG.info("User with id {} could not be found.", userId);
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/lichess/username/{userName}")
    public ResponseEntity<UserDto> setLichessUsername(@PathVariable("userName") String userName){
        try {
            UserBo user = this.userFacade.getUser();
            user = this.userFacade.setLichessUsername(user.getUserName(), userName);
            return ResponseEntity.ok().body(this.mapper.mapUser(user));
        } catch (UserNotFoundException e) {
            LOG.info("User with id {} could not be found.", e.getUserName());
            return ResponseEntity.notFound().build();
        }
    }

}
