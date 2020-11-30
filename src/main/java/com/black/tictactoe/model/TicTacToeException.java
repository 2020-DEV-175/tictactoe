package com.black.tictactoe.model;

public class TicTacToeException extends Exception {
    public TicTacToeException(String message) {
        super(message);
    }
    public TicTacToeException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
