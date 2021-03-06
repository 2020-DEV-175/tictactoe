package com.black.tictactoe.model;

import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.black.tictactoe.utils.Constants.EXIT_MESSAGE;
import static com.black.tictactoe.utils.Constants.NEW_GAME;
import static com.black.tictactoe.utils.Constants.NEXT_PLAYER_TURN;
import static com.black.tictactoe.utils.Constants.TIED_RESULT;
import static com.black.tictactoe.utils.Constants.WINNER_RESULT;

public class Game {
    private static final Logger LOGGER = Logger.getLogger(Game.class);
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
        LOGGER.info(String.format(NEXT_PLAYER_TURN, currentPlayer.getName()));
        currentPlayer.playMove(this);
    }

    void move(String userMove) throws TicTacToeException {
        this.board.move(currentPlayer, userMove);
        switchPlayer();
        board.printBoard();
        this.handleGameOver();
        if(!isGameOver) {
            LOGGER.info(String.format(NEXT_PLAYER_TURN, currentPlayer.getName()));
            currentPlayer.playMove(this);
        } else {
            LOGGER.info(NEW_GAME);
            reset();
        }
    }

    private void handleGameOver() {
        if (getWinner().isPresent()) {
            LOGGER.info(String.format(WINNER_RESULT, getWinner().get().getName()));
            LOGGER.info(EXIT_MESSAGE);
            isGameOver = true;
        } else if(!hasAnyMoveLeft()) {
            LOGGER.info(TIED_RESULT);
            LOGGER.info(EXIT_MESSAGE);
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
