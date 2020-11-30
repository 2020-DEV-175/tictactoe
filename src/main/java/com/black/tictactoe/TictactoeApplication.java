package com.black.tictactoe;

import com.black.tictactoe.model.Game;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TictactoeApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(TictactoeApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments arg0) {
		Game game = new Game();
		while (true) {
			System.out.println("Welcome to Tic Tac Toe");
			game.startNew();
		}
	}

}
