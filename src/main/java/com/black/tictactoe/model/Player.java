package com.black.tictactoe.model;

import java.util.Scanner;

class Player {
    private static final String INPUT_POSITION = "Enter your x,y positons. E.g: 1,1";
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
        System.out.println(INPUT_POSITION);
        String userInput = readUserInput();
        try {
            game.move(userInput);
        } catch (TicTacToeException e) {
            System.out.println(e.getMessage());
            playMove(game);
        }
    }

    String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        if ("q".equals(userInput) || "quit".equals(userInput)) {
            System.out.println("Goodbye");
            scanner.close();
            System.exit(0);
        }
        return userInput;
    }
}
