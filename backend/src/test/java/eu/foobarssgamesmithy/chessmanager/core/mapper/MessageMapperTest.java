package eu.foobarssgamesmithy.chessmanager.core.mapper;

import eu.foobarssgamesmithy.chessmanager.core.messaging.data.AutoImportMessage;
import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import eu.foobarssgamesmithy.chessmanager.fixtures.MessageFixtures;
import eu.foobarssgamesmithy.chessmanager.fixtures.UserBoFixtures;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class MessageMapperTest {

    private final MessageMapper underTest = Mappers.getMapper(MessageMapper.class);

    @Test
    void mapFromUser_shouldMapMatchEtyFieldsCorrect(){
        // arrange
        UserBo user = UserBoFixtures.aOnlineUser();
        AutoImportMessage expected = MessageFixtures.aAutoImportMessage();

        // act
        AutoImportMessage actual = this.underTest.mapFromUser(user);

        // assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

}