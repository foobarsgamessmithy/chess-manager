package eu.foobarssgamesmithy.chessmanager.core.match;

import eu.foobarssgamesmithy.chessmanager.core.mapper.BoEtyMapper;
import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.match.exception.MatchException;
import eu.foobarssgamesmithy.chessmanager.core.match.exception.MatchNotFoundException;
import eu.foobarssgamesmithy.chessmanager.core.match.impl.MatchManagerImpl;
import eu.foobarssgamesmithy.chessmanager.core.user.User;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchBoFixtures;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchEtyFixtures;
import eu.foobarssgamesmithy.chessmanager.persistence.match.MatchRepository;
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

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.MATCH_WITHOUT_RESULT_UUID;
import static eu.foobarssgamesmithy.chessmanager.fixtures.UserBoFixtures.aUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MatchManagerTest {

    private MatchManager underTest;

    @Mock
    private MatchRepository matchRepositoryMock;

    @Mock
    private User userFacadeMock;

    @Captor
    private ArgumentCaptor<UUID> matchIdCaptor;

    @BeforeEach
    void beforeEach(){
        this.underTest = new MatchManagerImpl(this.matchRepositoryMock, Mappers.getMapper(BoEtyMapper.class),
                this.userFacadeMock);
    }

    @Test
    void getMatch_shouldReturnMatch() throws MatchException {
        // Arrange
        MatchBo expected = MatchBoFixtures.aMatch();
        UUID id = expected.getId();

        when(this.matchRepositoryMock.findById(id)).thenReturn(Optional.ofNullable(MatchEtyFixtures.aMatchWithResult()));
        when(this.userFacadeMock.getUser()).thenReturn(aUser());

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

        when(this.matchRepositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act + Assert
        assertThrows(MatchNotFoundException.class, () -> this.underTest.getMatch(id));
    }

    @Test
    void getMatches_shouldReturnMatchList() {
        // Arrange
        List<MatchBo> expected =  List.of(MatchBoFixtures.aMatch());

        when(this.matchRepositoryMock.findAll()).thenReturn(Collections.singleton(MatchEtyFixtures.aMatchWithResult()));
        when(this.userFacadeMock.getUser()).thenReturn(aUser());

        // Act
        List<MatchBo> actual = this.underTest.getMatches();

        // Assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void deleteMatch_shouldDeleteMatch() throws MatchException {
        // Arrange
        when(this.matchRepositoryMock.findById(MATCH_WITHOUT_RESULT_UUID))
                .thenReturn(Optional.ofNullable(MatchEtyFixtures.aMatchWithoutResult()));
        when(this.userFacadeMock.getUser()).thenReturn(aUser());

        // Act
        this.underTest.deleteMatch(MATCH_WITHOUT_RESULT_UUID);

        // Assert
        verify(this.matchRepositoryMock).deleteById(this.matchIdCaptor.capture());
        assertThat(this.matchIdCaptor.getValue())
                .isEqualTo(MATCH_WITHOUT_RESULT_UUID);
    }

}