package eu.foobarssgamesmithy.chessmanager.persistence;

import eu.foobarssgamesmithy.chessmanager.persistence.entity.MatchEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static eu.foobarssgamesmithy.chessmanager.fixtures.MatchEtyFixtures.aMatchWithResult;
import static eu.foobarssgamesmithy.chessmanager.fixtures.MatchEtyFixtures.aMatchWithoutResult;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class MatchRepositoryTest {

    @Autowired
    private MatchRepository underTest;

    @Test
    void save_shouldSaveMatchCorrectly() {
        // arrange
        MatchEntity match = aMatchWithoutResult(null);
        MatchEntity expected = aMatchWithoutResult();

        // act
        MatchEntity actual = this.underTest.save(match);

        // arrange
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFieldsOfTypes(UUID.class)
                .isEqualTo(expected);
        assertThat(actual.getMatchId()).isNotNull();
    }

    @Test
    void save_withResult_shouldSaveMatchCorrectly() {
        // arrange
        MatchEntity match = aMatchWithResult(null);
        MatchEntity expected = aMatchWithResult();

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

    @Test
    void deleteById_shouldDeleteMatchCorrectly() {
        // arrange
        MatchEntity match = aMatchWithoutResult();
        match.setMatchId(null);
        MatchEntity savedMatch = this.underTest.save(match);

        // act
        this.underTest.deleteById(savedMatch.getMatchId());
        Optional<MatchEntity> actual = this.underTest.findById(savedMatch.getMatchId());

        // arrange
        assertThat(actual.isPresent()).isFalse();
    }

}