package ru.shamilprog.android.tictactoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GameFragment extends Fragment {

    private TextView player1ScoreTextView;
    private TextView player2ScoreTextView;
    private Button resetButton;
    private Button[][] buttonsBoard;
    private Game game;

    public static GameFragment newInstance() {
        return new GameFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game = new Game();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_game, container, false);

        player1ScoreTextView = (TextView) root.findViewById(R.id.player1_textView);
        player2ScoreTextView = (TextView) root.findViewById(R.id.player2_textView);

        resetButton = (Button) root.findViewById(R.id.reset_button);

        int size = game.getBoard().getSize();
        buttonsBoard = new Button[size][size];

        for (int i = 0; i < buttonsBoard.length; i++) {
            for (int j = 0; j < buttonsBoard.length; j++) {
                String tag = i + "" + j;
                buttonsBoard[i][j] = (Button) root.findViewWithTag(tag);
                int finalI = i;
                int finalJ = j;
                buttonsBoard[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CellState move = game.makeMove();
                        if (move == CellState.X) {
                            buttonsBoard[finalI][finalJ].setText("X");
                        } else {
                            buttonsBoard[finalI][finalJ].setText("O");
                        }
                        game.getBoard().setCell(finalI, finalJ, move);
                        buttonsBoard[finalI][finalJ].setEnabled(false);

                        Outcome resultOfTheGame = game.checkForWin();
                        switch (resultOfTheGame) {
                            case X:
                                player1ScoreTextView.setText("Player 1: " + game.incScoreOfPlayerX());
                                game.end();
                                updateButtons();
                                Toast.makeText(getActivity(), "Player 1 won!", Toast.LENGTH_LONG).show();
                                break;
                            case O:
                                player2ScoreTextView.setText("Player 2: " + game.incScoreOfPlayerO());
                                game.end();
                                updateButtons();
                                Toast.makeText(getActivity(), "Player 2 won!", Toast.LENGTH_LONG).show();
                                break;
                            case DRAW:
                                game.end();
                                updateButtons();
                                Toast.makeText(getActivity(), "It's a draw!", Toast.LENGTH_LONG).show();
                                break;
                        };
                    }
                });
            }
        }

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.reset();
                player1ScoreTextView.setText(R.string.player1_score);
                player2ScoreTextView.setText(R.string.player2_score);

                updateButtons();
            }
        });

        return root;
    }

    private void updateButtons() {
        int size = game.getBoard().getSize();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                buttonsBoard[i][j].setText("");
                buttonsBoard[i][j].setEnabled(true);
            }
        }
    }
}
