package eu.foobarssgamesmithy.chessmanager.core;

import eu.foobarssgamesmithy.chessmanager.core.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.exception.MatchException;
import eu.foobarssgamesmithy.chessmanager.core.exception.MatchNotFoundException;
import eu.foobarssgamesmithy.chessmanager.core.mapper.BoEtyMapper;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchBoFixtures;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchEtyFixtures;
import eu.foobarssgamesmithy.chessmanager.persistence.MatchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.MATCH_UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchManagerTest {

    private MatchManager underTest;

    @Mock
    private MatchRepository matchRepository;

    @Captor
    private ArgumentCaptor<UUID> matchIdCaptor;

    @BeforeEach
    void beforeEach(){
        this.underTest = new MatchManagerImpl(this.matchRepository, Mappers.getMapper(BoEtyMapper.class));
    }

    @Test
    void getMatch_shouldReturnMatch() throws MatchException {
        // Arrange
        MatchBo expected = MatchBoFixtures.aMatch();
        UUID id = expected.getId();

        when(this.matchRepository.findById(id)).thenReturn(Optional.ofNullable(MatchEtyFixtures.aMatch()));

        // Act
        MatchBo actual = this.underTest.getMatch(id);

        // Assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void getMatch_shouldThrowNotFoundException() {
        // Arrange
        MatchBo expected = MatchBoFixtures.aMatch();
        UUID id = expected.getId();

        when(this.matchRepository.findById(id)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(MatchNotFoundException.class, () -> this.underTest.getMatch(id));
    }

    @Test
    void getMatches_shouldReturnMatchList() {
        // Arrange
        List<MatchBo> expected =  List.of(MatchBoFixtures.aMatch());

        when(this.matchRepository.findAll()).thenReturn(Collections.singleton(MatchEtyFixtures.aMatch()));

        // Act
        List<MatchBo> actual = this.underTest.getMatches();

        // Assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void deleteMatch_shouldDeleteMatch() {
        // Arrange

        // Act
        this.underTest.deleteMatch(MATCH_UUID);

        // Assert
        verify(this.matchRepository).deleteById(this.matchIdCaptor.capture());
        assertThat(this.matchIdCaptor.getValue())
                .isEqualTo(MATCH_UUID);
    }

}