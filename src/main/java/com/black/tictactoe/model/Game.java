package com.black.tictactoe.model;

public class Game {
    private Board board;
    private Player[] players = new Player[]{new Player("Player 1", TileValue.X), new Player("Player 2", TileValue.O)};
    private Player currentPlayer;
    private boolean isGameOver = false;

    public Game() {
        startNew();
    }

    private void startNew() {
        currentPlayer = pickRandomPlayer();

        if(board == null) {
            board = new Board();
        } else if(isGameOver) {
            board.reset();
        }
        board.printBoard();
        currentPlayer.playMove(this);
    }

    private Player pickRandomPlayer() {
        int randomPlayerId = (int) (Math.random() * players.length);
        return this.players[randomPlayerId];
    }

    public void move(String userMove) throws TicTacToeException {
        this.board.move(currentPlayer, userMove);
        switchPlayer();
        board.printBoard();
        if(!isGameOver()) {
            currentPlayer.playMove(this);
        } else {
            startNew();
        }
    }

    private boolean isGameOver() {
        return false;
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.equals(players[1]) ? players[0]: players[1];
    }

    public void reset() { startNew(); }

}
