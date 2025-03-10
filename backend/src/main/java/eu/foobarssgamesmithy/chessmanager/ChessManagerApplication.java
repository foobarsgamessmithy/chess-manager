package eu.foobarssgamesmithy.chessmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ChessManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChessManagerApplication.class, args);
	}

}
