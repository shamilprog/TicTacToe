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

    public GameBoard getBoard() {
        return board;
    }

    public int incScoreOfPlayerX() {
        return ++scoreOfPlayerX;
    }

    public int incScoreOfPlayerO() {
        return ++scoreOfPlayerO;
    }
    /**
     *
     * @return what move was made
     */
    public CellState makeMove() {
        CellState result = currentMove? CellState.X : CellState.O;
        currentMove = !currentMove;
        return result;
    }

    public Outcome checkForWin() {
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
            if (flag && (cell != CellState.EMPTY)) {
                return cell == CellState.X ? Outcome.X : Outcome.O;
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
            if (flag && (cell != CellState.EMPTY)) {
                return cell == CellState.X ? Outcome.X : Outcome.O;
            }
        }

        // check diagonals
        if (board.getCell(0, 0) == board.getCell(1, 1) &&
            board.getCell(1, 1) == board.getCell(2, 2) &&
            board.getCell(0, 0) != CellState.EMPTY) {
            return board.getCell(0, 0) == CellState.X ? Outcome.X : Outcome.O;
        }

        if (board.getCell(2, 0) == board.getCell(1, 1) &&
            board.getCell(1, 1) == board.getCell(0, 2) &&
            board.getCell(2, 0) != CellState.EMPTY) {
            return board.getCell(2, 0) == CellState.X ? Outcome.X : Outcome.O;
        }

        if (board.isFull()) {
            return Outcome.DRAW;
        }

        return Outcome.PLAYING;
    }

    public void reset() {
        end();
        scoreOfPlayerO = 0;
        scoreOfPlayerX = 0;
    }

    public void end() {
        board.clear();
        currentMove = true;
    }
}
