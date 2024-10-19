package eu.foobarssgamesmithy.chessmanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.foobarssgamesmithy.chessmanager.fixtures.MatchDtoFixtures;
import eu.foobarssgamesmithy.chessmanager.service.dto.MatchDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.ZonedDateTime;

import static eu.foobarssgamesmithy.chessmanager.fixtures.SharedFixtures.formatZonedDateTimeForDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {"test"})
class ChessManagerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void createMatch_shouldReturnOk() throws Exception {
		// Arrange
		ZonedDateTime playedAt = ZonedDateTime.now();
		MatchDto match = MatchDtoFixtures.aMatch();
		match.setPlayedAt(playedAt.toString());

		MatchDto expected = MatchDtoFixtures.aMatch();

		expected.setPlayedAt(formatZonedDateTimeForDto(playedAt));
		expected.setId(1L);

		// Act
		ResultActions result = mockMvc.perform(post("/api/match")
				.contentType(APPLICATION_JSON_UTF8)
				.content(new ObjectMapper().writeValueAsString(match)));

		// Assert
		result.andExpect(status().isOk());
		MatchDto actual = new ObjectMapper()
				.readValue(result.andReturn().getResponse().getContentAsString(), MatchDto.class);
		assertThat(actual)
				.usingRecursiveComparison()
				.isEqualTo(expected);

	}

	@Test
	void getMatch_shouldReturnNotFound() throws Exception {
		// Act
		ResultActions result = mockMvc.perform(get("/api/match/{id}", 0));

		// Assert
		result.andExpect(status().isNotFound());
	}

	@Test
	void getMatch_shouldReturnOk() throws Exception {
		// Arrange
		MatchDto match = MatchDtoFixtures.aMatch();
		ResultActions createdMatchResult = mockMvc.perform(post("/api/match")
				.contentType(APPLICATION_JSON_UTF8)
				.content(new ObjectMapper().writeValueAsString(match)));
		createdMatchResult.andExpect(status().isOk());
		MatchDto createdMatch = new ObjectMapper()
				.readValue(createdMatchResult.andReturn().getResponse().getContentAsString(), MatchDto.class);

		MatchDto expected = MatchDtoFixtures.aMatch();
		expected.setId(createdMatch.getId());

		// Act
		ResultActions result = mockMvc.perform(get("/api/match/{id}", createdMatch.getId()));

		// Assert
		result.andExpect(status().isOk());
		MatchDto actual = new ObjectMapper()
				.readValue(result.andReturn().getResponse().getContentAsString(), MatchDto.class);
		assertThat(actual)
				.usingRecursiveComparison()
				.isEqualTo(expected);
	}

}
