package eu.foobarssgamesmithy.chessmanager.core.mapper;

import eu.foobarssgamesmithy.chessmanager.core.match.data.Castle;
import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.match.data.Promotion;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchBoFixtures;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchEtyFixtures;
import eu.foobarssgamesmithy.chessmanager.persistence.match.entity.MatchEntity;
import eu.foobarssgamesmithy.chessmanager.persistence.match.entity.MoveEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("legalMoves")
    void map_withString_shouldMapMoveToEntityCorrect(String move, MoveEntity expected){
        // act
        MoveEntity actual = this.underTest.map(move);

        // assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("legalMoves")
    void map_withMapMoveEntity_shouldMapToStringCorrect(String expected, MoveEntity move){
        // act
        String actual = this.underTest.map(move);

        // assert
        assertThat(actual)
                .isEqualTo(expected);
    }

    private static Stream<Arguments> legalMoves() {
        return Stream.of(
                Arguments.of("Nc4", MoveEntity.builder().field("c4").figure("N").build()),
                Arguments.of("Nxc4", MoveEntity.builder().field("c4").figure("N").hasCaptured(true).build()),
                Arguments.of("b4", MoveEntity.builder().field("b4").build()),
                Arguments.of("axb4", MoveEntity.builder().field("b4").pawn("a").hasCaptured(true).build()),
                Arguments.of("fxg6 e.p.", MoveEntity.builder().field("g6").pawn("f").hasCaptured(true).isEnPassant(true).build()),
                Arguments.of("Bec4", MoveEntity.builder().figure("B").field("c4").file("e").build()),
                Arguments.of("Bexc4", MoveEntity.builder().figure("B").field("c4").file("e").hasCaptured(true).build()),
                Arguments.of("R1c7", MoveEntity.builder().figure("R").rank("1").field("c7").build()),
                Arguments.of("cxd8Q", MoveEntity.builder().field("d8").pawn("c").hasCaptured(true).promotion(Promotion.QUEEN).build()),
                Arguments.of("cxd8N+", MoveEntity.builder().field("d8").pawn("c").hasCaptured(true).promotion(Promotion.KNIGHT).isCheck(true).build()),
                Arguments.of("0-0", MoveEntity.builder().castle(Castle.SHORT).build()),
                Arguments.of("0-0-0", MoveEntity.builder().castle(Castle.LONG).build())
        );
    }

}