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
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.next();
        try {
            game.move(userInput);
        } catch (TicTacToeException e) {
            System.out.println(e.getMessage());
            playMove(game);
        }
    }
}
