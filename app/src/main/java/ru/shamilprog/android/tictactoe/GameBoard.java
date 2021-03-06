package ru.shamilprog.android.tictactoe;

import java.io.Serializable;

public class GameBoard implements Serializable {

    // Size of a game board (matrix)
    private int size;

    // a board itself
    private CellState[][] cells;

    public GameBoard() {
        size = 3;
        cells = new CellState[3][3];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = CellState.EMPTY;
            }
        }
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = CellState.EMPTY;
            }
        }
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j] == CellState.EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }

    public int getSize() {
        return size;
    }

    public CellState getCell(int top, int left) {
        return cells[top][left];
    }

    public void setCell(int top, int left, CellState state) {
        cells[top][left] = state;
    }
}
