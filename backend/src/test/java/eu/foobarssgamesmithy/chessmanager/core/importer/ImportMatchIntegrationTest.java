package eu.foobarssgamesmithy.chessmanager.core.importer;

import eu.foobarssgamesmithy.chessmanager.auth.AuthenticationFacade;
import eu.foobarssgamesmithy.chessmanager.common.SpringProfiles;
import eu.foobarssgamesmithy.chessmanager.core.importer.impl.ImportMatchImpl;
import eu.foobarssgamesmithy.chessmanager.core.match.MatchManager;
import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.user.User;
import eu.foobarssgamesmithy.chessmanager.core.user.exception.UserException;
import eu.foobarssgamesmithy.chessmanager.fixtures.FileFixtures;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static eu.foobarssgamesmithy.chessmanager.common.HttpClient.getStandardHttpEntity;
import static eu.foobarssgamesmithy.chessmanager.fixtures.UrlFixtures.LICHESS_GAMES_BY_USERID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles(profiles = {SpringProfiles.TEST})
public class ImportMatchIntegrationTest {

    @Autowired
    private ImportMatchImpl underTest;

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private User userFacade;

    @Autowired
    private MatchManager matchManager;

    @MockBean
    private AuthenticationFacade authenticationFacade;

    @Test
    void importMatch_shouldImportMatchesCorrect() throws IOException, UserException {
        // Arrange
        String lichessUserName = "drdrunkenstein";
        String chessManagerUsername = "MagnusC";

        String resultBody = FileFixtures.getFromResources("matchdata/lichess_DrDrunkenstein_2025-03-15.json");

        String urlTemplate = LICHESS_GAMES_BY_USERID + lichessUserName;
        HttpEntity<String> entity = getStandardHttpEntity();
        HashMap<String, String> params = new HashMap<>();

        when(restTemplate.exchange(urlTemplate, HttpMethod.GET, entity, String.class, params ))
                .thenReturn(new ResponseEntity<>(resultBody, HttpStatus.OK));
        when(this.authenticationFacade.getUserName()).thenReturn(chessManagerUsername);

        // Act
        this.underTest.importMatch(lichessUserName);

        // Assert
        List<MatchBo> matchList = this.matchManager.getMatches();
        assertThat(matchList.size()).isEqualTo(5);
    }

}
