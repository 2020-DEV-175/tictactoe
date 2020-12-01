package com.black.tictactoe.model;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.black.tictactoe.model.TileValue.EMPTY;
import static com.black.tictactoe.utils.Constants.COL_OUTSIDE_BOUNDARY;
import static com.black.tictactoe.utils.Constants.OUTSIDE_ARRAY_BOUNDARY;
import static com.black.tictactoe.utils.Constants.ROW_OUTSIDE_BOUNDARY;
import static com.black.tictactoe.utils.Constants.TILE_ALREADY_SET;
import static com.black.tictactoe.utils.Constants.UNEXPECTED_INPUT_TYPE;
import static com.black.tictactoe.utils.Constants.WRONG_INPUT_FORMAT;
import static org.springframework.util.StringUtils.hasText;

public class Board {
    private static final Logger LOGGER = Logger.getLogger(Board.class);

    private static final int ROW_SIZE = 3;
    private static final int COL_SIZE = 3;

    private Tile[][] tiles;

    public Board() {
        tiles = new Tile[ROW_SIZE][COL_SIZE];

        for (int rowIndex = 0; rowIndex < ROW_SIZE; rowIndex ++) {
            for (int colIndex = 0; colIndex < COL_SIZE; colIndex ++) {
                tiles[rowIndex][colIndex] = new Tile();
            }
        }
    }

    void move(Player player, String tileId) throws TicTacToeException {
        String[] indices = tileId.split(",");
        if (indices.length != 2) {
            throw new TicTacToeException(String.format(WRONG_INPUT_FORMAT, tileId));
        }
        try {
            Tile tile = this.get(Integer.parseInt(indices[0]), Integer.parseInt(indices[1]));
            if (tile.isEmpty()) {
                tile.setValue(player.getTileValue());
            } else {
                throw new TicTacToeException(String.format(TILE_ALREADY_SET, tileId));
            }
        } catch (NumberFormatException nfe) {
            throw new TicTacToeException(String.format(UNEXPECTED_INPUT_TYPE, tileId));
        }
    }

    Tile get(int rowIndex, int colIndex) throws TicTacToeException {
        this.validateArrayInboundUserInput(rowIndex, colIndex);
        // User inputs non-zero index
        return tiles[rowIndex - 1][colIndex - 1];
    }

    private void validateArrayInboundUserInput(int rowIndex, int colIndex) throws TicTacToeException {
        StringBuilder stringBuilder = new StringBuilder();
        if(rowIndex < 1 || rowIndex > ROW_SIZE) {
            stringBuilder.append(OUTSIDE_ARRAY_BOUNDARY);
            stringBuilder.append(" ");
            stringBuilder.append(String.format(ROW_OUTSIDE_BOUNDARY, rowIndex));
        }
        if(colIndex < 1 || colIndex > COL_SIZE) {
            if (!hasText(stringBuilder.toString())) {
                stringBuilder.append(OUTSIDE_ARRAY_BOUNDARY);
            }
            stringBuilder.append(" ");
            stringBuilder.append(String.format(COL_OUTSIDE_BOUNDARY, colIndex));
        }
        if (hasText(stringBuilder.toString())) {
            throw new TicTacToeException(stringBuilder.toString());
        }
    }

    void printBoard() {
        LOGGER.info("\n");
        String header = " ";
        for (int colIndex = 0; colIndex < COL_SIZE; colIndex ++) {
            header += " | " + (colIndex + 1);
        }
        LOGGER.info(header);
        for (int rowIndex = 0; rowIndex < ROW_SIZE; rowIndex ++) {
            String row = "" + (rowIndex + 1);
            for (int colIndex = 0; colIndex < COL_SIZE; colIndex ++) {
                row += " | " + tiles[rowIndex][colIndex];
            }
            LOGGER.info(row);
            for (int colIndex = 0; colIndex < COL_SIZE; colIndex ++) {
                header += " _ " + (colIndex + 1);
            }
        }
        LOGGER.info("\n");
    }

    void reset() {
        for(Tile[] row : this.tiles) {
            for(Tile tile : row) {
                tile.setValue(EMPTY);
            }
        }
    }

    private List<Tile> getRow(int rowIndex) {
        return Arrays.asList(this.tiles[rowIndex]);
    }

    private List<Tile> getColumn(int colIndex) {
        List<Tile> columns = new ArrayList<>();

        for (Tile[] row: this.tiles) {
            columns.add(row[colIndex]);
        }
        return columns;
    }

    private List<Tile> getDiagonalTopRightBottomLeft() {
        List<Tile> diagonal = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < ROW_SIZE; rowIndex ++) {
            for (int colIndex = 0; colIndex < COL_SIZE; colIndex ++) {
                diagonal.add(this.tiles[rowIndex][colIndex]);
            }
        }
        return diagonal;
    }

    private List<Tile> getDiagonalTopLeftBottomRight() {
        List<Tile> diagonal = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < ROW_SIZE; rowIndex ++) {
            for (int colIndex = COL_SIZE -1; colIndex >= 0; colIndex --) {
                diagonal.add(this.tiles[rowIndex][colIndex]);
            }
        }
        return diagonal;
    }

    List<List<Tile>> getAllLines() {
        List<List<Tile>> lines = new ArrayList<>();

        for (int rowIndex = 0; rowIndex < ROW_SIZE; rowIndex++) {
            lines.add(getRow(rowIndex));
        }

        for (int colIndex = 0; colIndex < COL_SIZE; colIndex++) {
            lines.add(getColumn(colIndex));
        }

        lines.add(getDiagonalTopLeftBottomRight());
        lines.add(getDiagonalTopRightBottomLeft());

        return lines;
    }

    boolean hasAnyMoveLeft() {
        return Stream.of(this.tiles).flatMap(Stream::of).anyMatch(tile -> tile.getValue().equals(EMPTY));
    }
}
