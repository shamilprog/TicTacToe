package ru.shamilprog.android.tictactoe;

public class GameBoard {

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
