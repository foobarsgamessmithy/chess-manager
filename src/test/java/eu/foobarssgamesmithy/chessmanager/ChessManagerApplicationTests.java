package eu.foobarssgamesmithy.chessmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.net.http.HttpClient;
import java.util.Optional;
import java.util.concurrent.Executor;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ChessManagerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void createMatch_shouldReturnOk() throws Exception {

		// Act
		ResultActions result = mockMvc.perform(post("/api/match"));

		// Assert
		result.andExpect(status().isOk());

	}

	@Test
	void getMatch_shouldReturnOk() throws Exception {

		// Act
		ResultActions result = mockMvc.perform(get("/api/match"));

		// Assert
		result.andExpect(status().isOk());

	}

}
