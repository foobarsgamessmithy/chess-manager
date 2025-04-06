package eu.foobarssgamesmithy.chessmanager.service.mapper;

import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchBoFixtures;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchDtoFixtures;
import eu.foobarssgamesmithy.chessmanager.fixtures.UserBoFixtures;
import eu.foobarssgamesmithy.chessmanager.fixtures.UserDtoFixtures;
import eu.foobarssgamesmithy.chessmanager.service.match.data.MatchDto;
import eu.foobarssgamesmithy.chessmanager.service.user.data.UserDto;
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
        expected.setUser(null);
        MatchDto match = MatchDtoFixtures.aMatch();

        // act
        MatchBo actual = this.underTest.mapMatch(match);

        // assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void mapUser_shouldMapUserDtoFieldsCorrect(){
        // arrange
        UserBo user  = UserBoFixtures.aOnlineUser();
        UserDto expected = UserDtoFixtures.aOnlineUser();

        // act
        UserDto actual = this.underTest.mapUser(user);

        // assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void mapUser_shouldMapUserBoFieldsCorrect(){
        // arrange
        UserBo expected = UserBoFixtures.aOnlineUser();
        expected.setId(null);
        UserDto user = UserDtoFixtures.aOnlineUser();

        // act
        UserBo actual = this.underTest.mapUser(user);

        // assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

}