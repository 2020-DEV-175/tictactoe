package com.black.tictactoe.model;

import org.junit.Before;
import org.junit.Test;

import static com.black.tictactoe.utils.Constants.COL_OUTSIDE_BOUNDARY;
import static com.black.tictactoe.utils.Constants.OUTSIDE_ARRAY_BOUNDARY;
import static com.black.tictactoe.utils.Constants.ROW_OUTSIDE_BOUNDARY;
import static com.black.tictactoe.utils.Constants.TILE_ALREADY_SET;
import static com.black.tictactoe.utils.Constants.UNEXPECTED_INPUT_TYPE;
import static com.black.tictactoe.utils.Constants.WRONG_INPUT_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {
    private Player player;
    private Board board;

    @Before
    public void setUp() {
        player = new Player("testPlayer", TileValue.X);
        board = new Board();
    }

    @Test
    public void whenInboundMove_thenOk() throws TicTacToeException {
        board.move(player, "1,1");

        assertEquals(board.get(1, 1).getValue(), TileValue.X);
    }

    @Test
    public void whenInboundMoveEdgeCase_thenOk() throws TicTacToeException {
        board.move(player, "3,3");

        assertEquals(board.get(3, 3).getValue(), TileValue.X);
    }

    @Test
    public void whenTileAlreadySet_thenWarning() throws TicTacToeException {
        board.get(3, 3).setValue(TileValue.X);
        Exception exception = assertThrows(TicTacToeException.class, () -> {
            board.move(player, "3,3");
        });

        String expectedMessage = String.format(TILE_ALREADY_SET, "3,3");
        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    public void whenWrongFormatInput_thenThrowsException() {
        Exception exception = assertThrows(TicTacToeException.class, () -> {
            board.move(player, "5");
        });

        String expectedMessage = String.format(WRONG_INPUT_FORMAT, 5);
        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    public void whenWrongTypeInput_thenThrowsException() {
        Exception exception = assertThrows(TicTacToeException.class, () -> {
            board.move(player, "A,4");
        });

        String expectedMessage = String.format(UNEXPECTED_INPUT_TYPE, "A,4");
        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    public void whenBothInputOutsideBoundaries_thenThrowsException() {
        Exception exception = assertThrows(TicTacToeException.class, () -> {
            board.move(player,"0,4");
        });

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OUTSIDE_ARRAY_BOUNDARY);
        stringBuilder.append(" ");
        stringBuilder.append(String.format(ROW_OUTSIDE_BOUNDARY, 0));
        stringBuilder.append(" ");
        stringBuilder.append(String.format(COL_OUTSIDE_BOUNDARY, 4));
        String expectedMessage = stringBuilder.toString();
        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    public void whenRowInputOutsideBoundaries_thenThrowsException() {
        Exception exception = assertThrows(TicTacToeException.class, () -> {
            board.move(player,"0,3");
        });

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OUTSIDE_ARRAY_BOUNDARY);
        stringBuilder.append(" ");
        stringBuilder.append(String.format(ROW_OUTSIDE_BOUNDARY, 0));
        String expectedMessage = stringBuilder.toString();
        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    public void whenColInputOutsideBoundaries_thenThrowsException() {
        Exception exception = assertThrows(TicTacToeException.class, () -> {
            board.move(player,"1,4");
        });

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OUTSIDE_ARRAY_BOUNDARY);
        stringBuilder.append(" ");
        stringBuilder.append(String.format(COL_OUTSIDE_BOUNDARY, 4));
        String expectedMessage = stringBuilder.toString();
        assertEquals(exception.getMessage(), expectedMessage);
    }
}