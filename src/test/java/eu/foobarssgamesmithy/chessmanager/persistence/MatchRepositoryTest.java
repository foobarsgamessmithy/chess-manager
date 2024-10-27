package eu.foobarssgamesmithy.chessmanager.persistence;

import eu.foobarssgamesmithy.chessmanager.persistence.entity.MatchEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static eu.foobarssgamesmithy.chessmanager.fixtures.MatchEtyFixtures.aMatch;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class MatchRepositoryTest {

    @Autowired
    private MatchRepository underTest;

    @Test
    void save_shouldSaveMatchCorrectly() {
        // arrange
        MatchEntity match = aMatch();
        match.setMatchId(null);
        MatchEntity expected = aMatch();

        // act
        MatchEntity actual = this.underTest.save(match);

        // arrange
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFieldsOfTypes(UUID.class)
                .ignoringFields("result.id")
                .isEqualTo(expected);
        assertThat(actual.getMatchId()).isNotNull();
    }

}