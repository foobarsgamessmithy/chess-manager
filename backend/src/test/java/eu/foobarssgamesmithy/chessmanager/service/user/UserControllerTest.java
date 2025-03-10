package eu.foobarssgamesmithy.chessmanager.service.user;

import com.c4_soft.springaddons.security.oauth2.test.annotations.WithMockAuthentication;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.foobarssgamesmithy.chessmanager.common.SpringProfiles;
import eu.foobarssgamesmithy.chessmanager.fixtures.UserDtoFixtures;
import eu.foobarssgamesmithy.chessmanager.service.user.data.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {SpringProfiles.TEST, SpringProfiles.DEVELOPMENT})
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockAuthentication
    void getUser_shouldReturnNotFound() throws Exception {
        // Arrange
        String userId = "notFound";

        // Act
        ResultActions result = mockMvc.perform(get("/api/user/{userId}",userId));

        // Assert
        result.andExpect(status().isNotFound());
    }

    @Test
    @WithMockAuthentication
    void getUser_shouldReturnOk() throws Exception {
        // Arrange
        String userId = "foobar";
        UserDto expected = UserDtoFixtures.aUser();

        // Act
        ResultActions result = mockMvc.perform(get("/api/user/{userId}",userId));

        // Assert
        result.andExpect(status().isOk());
        UserDto actual = new ObjectMapper()
                .readValue(result.andReturn().getResponse().getContentAsString(), UserDto.class);
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @WithMockAuthentication(name = "drdrunkenstein")
    void setLichessUsername_shouldSetLichessUsernameCorrect() throws Exception {
        // Arrange
        String userId = "drdrunkenstein";
        UserDto expected = UserDtoFixtures.anotherUser();

        // Act
        ResultActions result = mockMvc.perform(patch("/api/user/lichess/username/{userName}",userId));

        // Assert
        result.andExpect(status().isOk());
        UserDto actual = new ObjectMapper()
                .readValue(result.andReturn().getResponse().getContentAsString(), UserDto.class);
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    @WithMockAuthentication(name = "drdrunkenstein")
    void setAutoImport_shouldSetAutoImportCorrect() throws Exception {
        // Arrange
        String userId = "drdrunkenstein";
        UserDto expected = UserDtoFixtures.anotherUser();
        expected.setAutoImport(true);

        mockMvc.perform(patch("/api/user/lichess/username/{userName}",userId));

        // Act
        ResultActions result = mockMvc.perform(patch("/api/user/lichess/autoimport/true", userId));

        // Assert
        result.andExpect(status().isOk());
        UserDto actual = new ObjectMapper()
                .readValue(result.andReturn().getResponse().getContentAsString(), UserDto.class);
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

}