package com.black.tictactoe.model;

public class Tile {
    private TileValue value;
    private int rowIndex;
    private int colIndex;

    public Tile(int rowIndex, int colIndex) {
        this.value = TileValue.EMPTY;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public TileValue getValue() {
        return value;
    }

    public void setValue(TileValue value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return this.value == TileValue.EMPTY;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    @Override
    public String toString() {
        return value.toString();
    }

}
