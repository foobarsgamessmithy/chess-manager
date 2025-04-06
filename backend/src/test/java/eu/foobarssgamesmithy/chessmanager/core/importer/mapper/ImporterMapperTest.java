package eu.foobarssgamesmithy.chessmanager.core.importer.mapper;

import eu.foobarssgamesmithy.chessmanager.core.messaging.data.AutoImportMessage;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchImportEntityFixtures;
import eu.foobarssgamesmithy.chessmanager.fixtures.MessageFixtures;
import eu.foobarssgamesmithy.chessmanager.persistence.importer.entity.MatchImportEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ImporterMapperTest {

    private final ImporterMapper underTest = Mappers.getMapper(ImporterMapper.class);

    @Test
    void mapMessage_shouldMapAllFields() {
        // Arrange
        AutoImportMessage message = MessageFixtures.aAutoImportMessage();
        MatchImportEntity expected = MatchImportEntityFixtures.aMatchImportEntity();

        // Act
        MatchImportEntity actual = this.underTest.mapMessage(message);

        // Assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

}