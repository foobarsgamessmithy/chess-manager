package eu.foobarssgamesmithy.chessmanager.core.importer.mapper;

import eu.foobarssgamesmithy.chessmanager.core.importer.data.LichessMatchDto;
import eu.foobarssgamesmithy.chessmanager.fixtures.FileFixtures;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.List;

import static eu.foobarssgamesmithy.chessmanager.fixtures.LichessFixtures.aSingleLichessGame;
import static org.assertj.core.api.Assertions.assertThat;

class LichessMapperTest {

    private final LichessMapper underTest = Mappers.getMapper(LichessMapper.class);

    @Test
    void mapMatch_shouldMapAllFields() throws IOException {
        // Arrange
        String match = FileFixtures.getFromResources("matchdata/lichess_single_game.json");
        LichessMatchDto expected = aSingleLichessGame();

        // Act
        LichessMatchDto actual = underTest.mapMatch(match);

        // Assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void mapMatchList_shouldMapAllFields() throws IOException {
        // Arrange
        String matchList = FileFixtures.getFromResources("matchdata/lichess_DrDrunkenstein_2025-03-15.json");
        LichessMatchDto expected = aSingleLichessGame();

        // Act
        List<LichessMatchDto> actual = underTest.mapMatchList(matchList);

        // Assert
        assertThat(actual.get(0))
                .usingRecursiveComparison()
                .isEqualTo(expected);
        assertThat(actual.size()).isEqualTo(5);
    }

}