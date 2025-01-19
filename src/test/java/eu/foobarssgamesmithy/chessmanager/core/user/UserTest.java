package eu.foobarssgamesmithy.chessmanager.core.user;

import eu.foobarssgamesmithy.chessmanager.auth.AuthenticationFacade;
import eu.foobarssgamesmithy.chessmanager.core.mapper.BoEtyMapper;
import eu.foobarssgamesmithy.chessmanager.core.user.data.UserBo;
import eu.foobarssgamesmithy.chessmanager.core.user.impl.UserImpl;
import eu.foobarssgamesmithy.chessmanager.fixtures.UserEtyFixtures;
import eu.foobarssgamesmithy.chessmanager.persistence.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static eu.foobarssgamesmithy.chessmanager.fixtures.UserBoFixtures.aUser;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserTest {

    private User underTest;

    @Mock
    private AuthenticationFacade authenticationFacadeMock;

    @Mock
    private UserRepository repositoryMock;


    @BeforeEach
    void beforeEach(){
        this.underTest = new UserImpl(this.authenticationFacadeMock, this.repositoryMock,
                Mappers.getMapper(BoEtyMapper.class));
    }

    @Test
    void getUser_shouldReturnUser(){
        // Arrange
        UserBo expected = aUser();
        String userName = "foobar";

        when(this.authenticationFacadeMock.getUserName()).thenReturn(userName);
        when(this.repositoryMock.findByUserName(userName)).thenReturn(Optional.of(UserEtyFixtures.aUser()));

        // Act
        UserBo actual = this.underTest.getUser();

        // Assert
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

}