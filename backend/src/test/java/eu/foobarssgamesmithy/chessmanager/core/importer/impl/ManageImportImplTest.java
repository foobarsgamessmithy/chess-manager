package eu.foobarssgamesmithy.chessmanager.core.importer.impl;

import eu.foobarssgamesmithy.chessmanager.core.importer.mapper.ImporterMapper;
import eu.foobarssgamesmithy.chessmanager.core.messaging.data.AutoImportMessage;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchImportEntityFixtures;
import eu.foobarssgamesmithy.chessmanager.fixtures.MessageFixtures;
import eu.foobarssgamesmithy.chessmanager.persistence.importer.ImporterRepository;
import eu.foobarssgamesmithy.chessmanager.persistence.importer.entity.MatchImportEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ManageImportImplTest {

    private ManageImportImpl underTest;

    @Mock
    private ImporterRepository repositoryMock;

    @Captor
    private ArgumentCaptor<MatchImportEntity> matchImportCaptor;

    @BeforeEach
    void beforeEach() {
        this.underTest = new ManageImportImpl(this.repositoryMock, Mappers.getMapper(ImporterMapper.class));
    }

    @Test
    void listenAutoImport_setAutoImportCorrect() {
        // Arrange
        AutoImportMessage message = MessageFixtures.aAutoImportMessage();
        message.setAutoImport(false);
        MatchImportEntity expected = MatchImportEntityFixtures.aMatchImportEntity();
        expected.setAutoImport(false);
        when(this.repositoryMock.findByUserName("MagnusC")).thenReturn(
                Optional.ofNullable(MatchImportEntityFixtures.aMatchImportEntity()));

        // Act
        this.underTest.listenAutoImport(message);

        // Assert
        verify(this.repositoryMock).save(this.matchImportCaptor.capture());
        assertThat(this.matchImportCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

}