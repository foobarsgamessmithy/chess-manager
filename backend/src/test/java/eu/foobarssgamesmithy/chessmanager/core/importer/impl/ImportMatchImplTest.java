package eu.foobarssgamesmithy.chessmanager.core.importer.impl;

import eu.foobarssgamesmithy.chessmanager.common.HttpClient;
import eu.foobarssgamesmithy.chessmanager.core.importer.mapper.LichessMapper;
import eu.foobarssgamesmithy.chessmanager.core.match.MatchManager;
import eu.foobarssgamesmithy.chessmanager.core.match.data.MatchBo;
import eu.foobarssgamesmithy.chessmanager.core.user.User;
import eu.foobarssgamesmithy.chessmanager.core.user.exception.UserNotFoundException;
import eu.foobarssgamesmithy.chessmanager.fixtures.FileFixtures;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchBoFixtures;
import eu.foobarssgamesmithy.chessmanager.fixtures.UserBoFixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImportMatchImplTest {

    private ImportMatchImpl underTest;

    @Mock
    private User userFacadeMock;

    @Mock
    private HttpClient httpClientMock;

    @Mock
    private MatchManager matchManagerMock;

    @Captor
    private ArgumentCaptor<List<MatchBo>> matchesCaptor;

    @BeforeEach
    void beforeEach(){
        this.underTest = new ImportMatchImpl(this.httpClientMock, Mappers.getMapper(LichessMapper.class),
                this.matchManagerMock, this.userFacadeMock);
    }

    @Test
    void importMatch_shouldCallSaveMatches() throws UserNotFoundException, IOException {
        // Arrange
        String lichessUsername = "drdunkenstein";
        String lichessResult = FileFixtures.getFromResources("matchdata/lichess_single_game.json")
                .replace("\n", "")
                .replace("\r", "");

        when(this.httpClientMock.get(any(), any())).thenReturn(lichessResult);
        when(this.userFacadeMock.getUsersByLichessName(lichessUsername)).thenReturn(UserBoFixtures.anotherUser());

        // Act
        this.underTest.importMatch(lichessUsername);

        // Assert
        verify(this.matchManagerMock).saveMatches(this.matchesCaptor.capture());
        assertThat(this.matchesCaptor.getValue().get(0))
                .usingRecursiveComparison()
                .isEqualTo(MatchBoFixtures.aImportedMatch());
    }
}