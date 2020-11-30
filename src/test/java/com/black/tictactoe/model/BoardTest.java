package com.black.tictactoe.model;

import org.junit.Before;
import org.junit.Test;

import static com.black.tictactoe.model.Board.COL_OUTSIDE_BOUNDARY;
import static com.black.tictactoe.model.Board.OUTSIDE_ARRAY_BOUNDARY;
import static com.black.tictactoe.model.Board.ROW_OUTSIDE_BOUNDARY;
import static com.black.tictactoe.model.Board.UNEXPECTED_INPUT_TYPE;
import static com.black.tictactoe.model.Board.WRONG_INPUT_FORMAT;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void whenWrongFormatInput_thenThrowsException() {
        Exception exception = assertThrows(TicTacToeException.class, () -> {
            board.get("5");
        });

        String expectedMessage = String.format(WRONG_INPUT_FORMAT, 5);
        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    public void whenWrongTypeInput_thenThrowsException() {
        Exception exception = assertThrows(TicTacToeException.class, () -> {
            board.get("A,4");
        });

        String expectedMessage = String.format(UNEXPECTED_INPUT_TYPE, "A,4");
        assertEquals(exception.getMessage(), expectedMessage);
    }

    @Test
    public void whenBothInputOutsideBoundaries_thenThrowsException() {
        Exception exception = assertThrows(TicTacToeException.class, () -> {
            board.get("0,4");
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
            board.get("0,3");
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
            board.get("1,4");
        });

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OUTSIDE_ARRAY_BOUNDARY);
        stringBuilder.append(" ");
        stringBuilder.append(String.format(COL_OUTSIDE_BOUNDARY, 4));
        String expectedMessage = stringBuilder.toString();
        assertEquals(exception.getMessage(), expectedMessage);
    }
}