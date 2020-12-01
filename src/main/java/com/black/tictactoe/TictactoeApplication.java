package com.black.tictactoe;

import com.black.tictactoe.model.Game;
import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TictactoeApplication implements ApplicationRunner {
	private static final Logger LOGGER = Logger.getLogger(TictactoeApplication.class);
	private static final String WELCOME_MESSAGE = "Welcome to Tic Tac Toe";
	private static final String EXIT_MESSAGE = "You can quit entering 'q' or 'quit'";

	public static void main(String[] args) {
		SpringApplication.run(TictactoeApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments arg0) {
		Game game = new Game();
		while (true) {
			LOGGER.info(WELCOME_MESSAGE);
			LOGGER.info(EXIT_MESSAGE);
			game.startNew();
		}
	}

}
