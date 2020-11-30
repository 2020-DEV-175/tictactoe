package com.black.tictactoe.model;

import java.util.Scanner;

public class Player {
    private String name;
    private TileValue tileValue;

    public Player(String name, TileValue tileValue) {
        this.name = name;
        this.tileValue = tileValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TileValue getTileValue() {
        return tileValue;
    }

    public void setTileValue(TileValue tileValue) {
        this.tileValue = tileValue;
    }

    public void playMove(Game game) {
        System.out.println("Enter your x,y positons. E.g: 1,1");
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
