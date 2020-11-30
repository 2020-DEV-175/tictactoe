package com.black.tictactoe.model;

public class Game {
    private Board board;
    private Player[] players;
    private Player currentPlayer;

    public Game(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }

}
