package edu.quinnipiac.tictactoe;

/**
 * Tic Tac Toe Game
 * Author: Tom Couto
 * Date: 2/15/19
 * Description:
 * Creates a game board to play tic tac toe against the computer
 * with the computer on easy mode blocking most moves but not all.
 * The goal is to get 3 X's in a row to win the game.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] board = new Button[3][3];

    private int player = 0;
    private int round = 0;
    private TextView textViewPlayer;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gets intent from landing page to write name at the top
        textViewPlayer = findViewById(R.id.text_view_player);
        name = getIntent().getExtras().getString("Name");
        textViewPlayer.setText("Tic Tac Toe! Welcome " + name);

        //creates the game board array with id's
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                board[i][j] = findViewById(resID);
                board[i][j].setOnClickListener(this);
            }
        }

        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });
    }

    @Override
    public void onClick(View v) {
        //check if the spot is open
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        //if it is the player's turn put an X in that spot
        if (player == 0) {
            ((Button) v).setText("X");
        }

        //increment round for draw case
        round++;

        //checks for winner after move
        if (checkForWinner()) {
            if (player == 0) {
                playerWin();
            } else {
                computerWin();
            }
        } else if (round == 9) {
            draw();
        } else {
            if (player == 0) player = 1;
            else player = 0;
        }

        computerMove();
        round++;

        if (checkForWinner()) {
            if (player == 0) {
                playerWin();
            } else {
                computerWin();
            }
        } else if (round == 9) {
            draw();
        } else {
            if (player == 0) player = 1;
            else player = 0;
        }
    }

    private boolean checkForWinner() {
        //create an array to check run through using the board
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = board[i][j].getText().toString();
            }
        }

        //check the first row
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        //check the second row
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        //check the third row
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        //check diagonal
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;

    }

    //if player wins print winner on toast and clears the board
    private void playerWin() {
        Toast.makeText(this, "You Won!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    //if computer wins print lost on toast and clears the board
    private void computerWin() {
        Toast.makeText(this, "You Lost!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    //draw case prints draw on toast and clears the board
    private void draw() {
        Toast.makeText(this, "It's a Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    //sets the whole array to blank strings, sets move to player and sets round to 0
    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
            }
        }
        round = 0;
        player = 0;
    }

    //gets the move of the computer with simple algorithm, includes blocking conditionally(beatable)
    private void computerMove() {
        //computer moves here
        if (player == 1) {
            if (board[1][0].equals(board[0][0])
                    && !board[1][0].equals("")
                    && board[2][0].equals("")) {
                board[2][0].setText("O");
            } else if (board[0][1].equals(board[0][0])
                    && !board[0][1].equals("")
                    && board[0][2].equals("")) {
                board[0][2].setText("O");
            } else if (board[1][1].equals(board[0][2])
                    && !board[1][1].equals("")
                    && board[1][2].equals("")) {
                board[1][2].setText("O");
            } else if (board[0][0].getText() == "") {
                board[0][0].setText("O");
            } else if (board[0][1].getText() == "") {
                board[0][1].setText("O");
            } else if (board[0][2].getText() == "") {
                board[0][2].setText("O");
            } else if (board[2][1].getText() == "") {
                board[2][1].setText("O");
            } else if (board[2][2].getText() == "") {
                board[2][2].setText("O");
            } else if (board[1][2].getText() == "") {
                board[1][2].setText("O");
            } else if (board[2][0].getText() == "") {
                board[2][0].setText("O");
            } else if (board[1][0].getText() == "") {
                board[1][0].setText("O");
            } else if (board[1][1].getText() == "") {
                board[1][1].setText("O");
            }
        }
    }



    //sets the values to save in case of rotation
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("round", round);
        outState.putInt("player", player);
    }

    //rewrites values after rotation
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        round = savedInstanceState.getInt("round");
        player = savedInstanceState.getInt("player");
    }
}
