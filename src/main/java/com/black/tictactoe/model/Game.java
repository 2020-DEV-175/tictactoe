package com.black.tictactoe.model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Game {
    static final String NEXT_PLAYER_TURN = "It's %s turn.";
    private static final String NEW_GAME = "Let's play a new game !";
    private static final String WINNER_RESULT = "%s is the winner";
    private static final String TIED_RESULT = "Tied";
    public static final String EXIT_GAME = "Enter 'exit' to leave the game";
    private Board board;
    private Player[] players = new Player[]{new Player("Player X", TileValue.X), new Player("Player O", TileValue.O)};
    private Player currentPlayer;
    private boolean isGameOver = false;

    public Game() {}

    public void startNew() {
        currentPlayer = players[0];

        if(board == null) {
            board = new Board();
        } else if(isGameOver) {
            isGameOver = false;
            board.reset();
        }
        board.printBoard();
        System.out.printf(NEXT_PLAYER_TURN, currentPlayer.getName());
        System.out.println();
        currentPlayer.playMove(this);
    }

    void move(String userMove) throws TicTacToeException {
        this.board.move(currentPlayer, userMove);
        switchPlayer();
        board.printBoard();
        this.handleGameOver();
        if(!isGameOver) {
            System.out.printf(NEXT_PLAYER_TURN, currentPlayer.getName());
            System.out.println();
            currentPlayer.playMove(this);
        } else {
            System.out.println(NEW_GAME);
            reset();
        }
    }

    private void handleGameOver() {
        if (getWinner().isPresent()) {
            System.out.printf(WINNER_RESULT, getWinner().get().getName());
            System.out.println();
            System.out.println(EXIT_GAME);
            isGameOver = true;
        } else if(!hasAnyMoveLeft()) {
            System.out.println(TIED_RESULT);
            System.out.println();
            System.out.println(EXIT_GAME);
            isGameOver = true;
        }
    }

    private Optional<Player> getWinner() {
        List<List<Tile>> allLines = board.getAllLines();

        for(List<Tile> line: allLines) {
            Tile firstTile = line.get(0);
            if (firstTile.isEmpty()) {
                continue;
            }
            if (line.stream().allMatch(t -> t.getValue() == firstTile.getValue())) {
                return Stream.of(players)
                             .filter(p -> p.getTileValue().equals(firstTile.getValue()))
                             .findFirst();
            }
        }
        return Optional.empty();
    }

    private boolean hasAnyMoveLeft() {
        return this.board.hasAnyMoveLeft();
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer.equals(players[1]) ? players[0]: players[1];
    }

    private void reset() { startNew(); }

}
