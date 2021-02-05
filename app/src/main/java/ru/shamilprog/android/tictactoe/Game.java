package ru.shamilprog.android.tictactoe;

public class Game {

    private GameBoard board;
    private int scoreOfPlayerX;
    private int scoreOfPlayerO;
    private boolean currentMove; // true - X, false - O

    public Game() {
        board = new GameBoard();
        scoreOfPlayerX = 0;
        scoreOfPlayerO = 0;
        currentMove = true;
    }

    public CellState makeMove() {
        CellState result = currentMove? CellState.X : CellState.O;
        currentMove = !currentMove;
        return result;
    }

    public CellState checkForWin() {
        int size = board.getSize();

        // check horizontally
        for (int i = 0; i < size; i++) {
            boolean flag = true;
            CellState cell = board.getCell(i, 0);
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j) != cell) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return cell;
            }
        }

        // check vertically
        for (int j = 0; j < size; j++) {
            boolean flag = true;
            CellState cell = board.getCell(0, j);
            for (int i = 0; i < size; i++) {
                if (board.getCell(i, j) != cell) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return cell;
            }
        }

        // check diagonals
        for (int i = 0; i < size - 1; i++) {
            boolean flag = true;

            if (board.getCell(i, i) != board.getCell(i+1, i+1)) {
                flag = false;
                break;
            }
            if (flag) {
                return board.getCell(i, i);
            }
        }

        for (int i = 0; i < size - 1; i++) {
            boolean flag = true;

            if (board.getCell(i, size - i - 1) != board.getCell(i+1, size - i - 2)) {
                flag = false;
                break;
            }
            if (flag) {
                return board.getCell(size - 1, 0);
            }
        }

        return CellState.EMPTY;
    }

    public void end() {

    }
}
