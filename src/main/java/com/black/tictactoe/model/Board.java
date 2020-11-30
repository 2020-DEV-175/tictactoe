package com.black.tictactoe.model;

import static org.springframework.util.StringUtils.hasText;

public class Board {
    static final String WRONG_INPUT_FORMAT = "Unexpected input : %s. Please use format x,y. E.g: 1,1";
    static final String UNEXPECTED_INPUT_TYPE = "Unexpected input : %s. Please input numeric values. E.g: 1,1";
    static final String OUTSIDE_ARRAY_BOUNDARY = "Please select tile within table boundaries";
    static final String ROW_OUTSIDE_BOUNDARY = "Row %s is outside table boundaries";
    static final String COL_OUTSIDE_BOUNDARY = "Column %s is outside table boundaries";
    static final String TILE_ALREADY_SET = "Tile in position  %s is already set. Please update another tile.";
    private static final int ROW_SIZE = 3;
    private static final int COL_SIZE = 3;
    private Tile[][] tiles;

    public Board() {
        tiles = new Tile[ROW_SIZE][COL_SIZE];

        for (int rowIndex = 0; rowIndex < ROW_SIZE; rowIndex ++) {
            for (int colIndex = 0; colIndex < COL_SIZE; colIndex ++) {
                tiles[rowIndex][colIndex] = new Tile(rowIndex, colIndex);
            }
        }
    }

    public void move(Player player, String tileId) throws TicTacToeException {
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

    public Tile get(int rowIndex, int colIndex) throws TicTacToeException {
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

    public void printBoard() {
        for (int rowIndex = 0; rowIndex < ROW_SIZE; rowIndex ++) {
            System.out.print(" ");
            for (int colIndex = 0; colIndex < COL_SIZE; colIndex ++) {
                System.out.print(tiles[rowIndex][colIndex] + " ");
            }
            System.out.println();
        }
    }

    public void reset() {
        for(Tile[] row : tiles) {
            for(Tile tile : row) {
                tile.setValue(TileValue.EMPTY);
            }
        }
    }
}
