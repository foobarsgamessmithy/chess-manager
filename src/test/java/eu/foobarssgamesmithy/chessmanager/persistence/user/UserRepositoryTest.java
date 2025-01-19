package eu.foobarssgamesmithy.chessmanager.persistence.user;

import eu.foobarssgamesmithy.chessmanager.fixtures.UserEtyFixtures;
import eu.foobarssgamesmithy.chessmanager.persistence.user.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void save_shouldSaveUserCorrectly() {
        // arrange
        UserEntity user = UserEtyFixtures.aUser();
        UserEntity expected = UserEtyFixtures.aUser();

        // act
        UserEntity actual = this.underTest.save(user);

        // arrange
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

}