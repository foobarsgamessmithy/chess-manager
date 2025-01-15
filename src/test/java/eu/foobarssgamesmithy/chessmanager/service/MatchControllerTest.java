package eu.foobarssgamesmithy.chessmanager.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchDtoFixtures;
import eu.foobarssgamesmithy.chessmanager.service.data.MatchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {"test"})
class MatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createMatch_shouldReturnOk() throws Exception {
        // Arrange
        ZonedDateTime playedAt = ZonedDateTime.now();
        MatchDto match = MatchDtoFixtures.aMatch();
        match.setPlayedAt(playedAt.toString());
        match.setId(null);

        MatchDto expected = MatchDtoFixtures.aMatch();

        expected.setPlayedAt(formatZonedDateTimeForDto(playedAt));
        expected.setId(MATCH_WITHOUT_RESULT_UUID);

        // Act
        ResultActions result = mockMvc.perform(post("/api/match")
                .contentType(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(match)));

        // Assert
        result.andExpect(status().isOk());
        MatchDto actual = new ObjectMapper()
                .readValue(result.andReturn().getResponse().getContentAsString(), MatchDto.class);
        assertThat(actual)
                .usingRecursiveComparison()
                .ignoringFieldsOfTypes(UUID.class)
                .isEqualTo(expected);

    }

    @Test
    void getMatch_shouldReturnNotFound() throws Exception {
        // Arrange
        UUID unknownSavedMatchId = UUID.fromString("00000000-0000-0000-0001-000000000000");

        // Act
        ResultActions result = mockMvc.perform(get("/api/match/{id}", unknownSavedMatchId.toString()));

        // Assert
        result.andExpect(status().isNotFound());
    }

    @Test
    void getMatch_shouldReturnOk() throws Exception {
        // Arrange
        MatchDto expected = MatchDtoFixtures.savedMatch1();

        // Act
        ResultActions result = mockMvc.perform(get("/api/match/{id}", SAVED_MATCH_UUID_1.toString()));

        // Assert
        result.andExpect(status().isOk());
        MatchDto actual = new ObjectMapper()
                .readValue(result.andReturn().getResponse().getContentAsString(), MatchDto.class);
        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void getMatches_shouldReturnOk() throws Exception {
        // Arrange
        List<MatchDto> expected =
                List.of(MatchDtoFixtures.savedMatch1(),
                        MatchDtoFixtures.savedMatch2());

        // Act
        ResultActions result = mockMvc.perform(get("/api/match/all"));

        // Assert
        result.andExpect(status().isOk());
        List<MatchDto> actual = new ObjectMapper()
                .readValue(result.andReturn().getResponse().getContentAsString(),
                        new TypeReference<>() {});
        assertThat(actual).containsAll(expected);
    }

    @Test
    void deleteMatch_shouldReturnOk() throws Exception {
        // Arrange
        MatchDto match = MatchDtoFixtures.aMatch();
        ResultActions savedMatchResult = mockMvc.perform(post("/api/match")
                .contentType(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(match)));
        MatchDto savedMatch = new ObjectMapper()
                .readValue(savedMatchResult.andReturn().getResponse().getContentAsString(), MatchDto.class);

        // Act
        ResultActions result = mockMvc.perform(delete("/api/match/{id}", savedMatch.getId().toString()));

        // Assert
        result.andExpect(status().isOk());
    }

}