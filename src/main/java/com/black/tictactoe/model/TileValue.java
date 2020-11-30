package com.black.tictactoe.model;

public enum TileValue {
    EMPTY(" "),
    X("X"),
    O("O");

    public final String value;

    TileValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}