package eu.foobarssgamesmithy.chessmanager.service.mapper;

import eu.foobarssgamesmithy.chessmanager.core.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchBoFixtures;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchDtoFixtures;
import eu.foobarssgamesmithy.chessmanager.service.data.MatchDto;
import eu.foobarssgamesmithy.chessmanager.service.mapper.DtoBoMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class DtoBoMapperTest {

    private final DtoBoMapper underTest = Mappers.getMapper(DtoBoMapper.class);

    @Test
    void mapMatch_shouldMapMatchDtoFieldsCorrect(){
        // arrange
        MatchBo match = MatchBoFixtures.aMatch();
        MatchDto expected = MatchDtoFixtures.aMatch();

        // act
        MatchDto actual = this.underTest.mapMatch(match);

        // assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void mapMatch_shouldMapMatchBoFieldsCorrect(){
        // arrange
        MatchBo expected = MatchBoFixtures.aMatch();
        MatchDto match = MatchDtoFixtures.aMatch();

        // act
        MatchBo actual = this.underTest.mapMatch(match);

        // assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

}