package com.black.tictactoe.model;

public class Tile {
    private TileValue value;

    Tile() {
        this.value = TileValue.EMPTY;
    }

    TileValue getValue() {
        return value;
    }

    void setValue(TileValue value) {
        this.value = value;
    }

    boolean isEmpty() {
        return this.value == TileValue.EMPTY;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
