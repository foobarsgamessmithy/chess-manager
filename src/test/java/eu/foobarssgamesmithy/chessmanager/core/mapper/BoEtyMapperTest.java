package eu.foobarssgamesmithy.chessmanager.core.mapper;

import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchBoFixtures;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchEtyFixtures;
import eu.foobarssgamesmithy.chessmanager.persistence.match.entity.MatchEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class BoEtyMapperTest {

    private final BoEtyMapper underTest = Mappers.getMapper(BoEtyMapper.class);

    @Test
    void mapMatch_shouldMapMatchEtyFieldsCorrect(){
        // arrange
        MatchBo match = MatchBoFixtures.aMatch();
        MatchEntity expected = MatchEtyFixtures.aMatchWithResult();

        // act
        MatchEntity actual = this.underTest.mapMatch(match);

        // assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void mapMatch_shouldMapMatchBoFieldsCorrect(){
        // arrange
        MatchBo expected = MatchBoFixtures.aMatch();
        MatchEntity match = MatchEtyFixtures.aMatchWithResult();

        // act
        MatchBo actual = this.underTest.mapMatch(match);

        // assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

}