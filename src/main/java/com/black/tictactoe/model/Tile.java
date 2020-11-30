package com.black.tictactoe.model;

public class Tile {
    private Value value;
    private int rowIndex;
    private int colIndex;

    public Tile(int rowIndex, int colIndex) {
        this.value = Value.EMPTY;
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
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

    public enum Value {
        EMPTY(" "),
        X("X"),
        O("O");

        public final String value;

        Value(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

}
