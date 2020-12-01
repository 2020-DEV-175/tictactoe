package com.black.tictactoe.model;

import org.apache.log4j.Logger;

import java.util.Scanner;

class Player {
    private static final Logger LOGGER = Logger.getLogger(Player.class);
    private static final String INPUT_POSITION = "Enter your x,y positons. E.g: 1,1";
    private static final String GOODBYE_MESSAGE = "Goodbye";
    private String name;
    private TileValue tileValue;

    Player(String name, TileValue tileValue) {
        this.name = name;
        this.tileValue = tileValue;
    }

    String getName() {
        return name;
    }

    TileValue getTileValue() {
        return tileValue;
    }

    void playMove(Game game) {
        LOGGER.info(INPUT_POSITION);
        String userInput = readUserInput();
        try {
            game.move(userInput);
        } catch (TicTacToeException e) {
            LOGGER.info(e.getMessage());
            playMove(game);
        }
    }

    String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        if ("q".equals(userInput) || "quit".equals(userInput)) {
            LOGGER.info(GOODBYE_MESSAGE);
            scanner.close();
            System.exit(0);
        }
        return userInput;
    }
}
