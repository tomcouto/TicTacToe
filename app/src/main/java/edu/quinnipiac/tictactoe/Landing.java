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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Landing extends AppCompatActivity {

    Button start;
    EditText username1;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        start = findViewById(R.id.start);
        username1 = findViewById(R.id.username1);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Landing.this,MainActivity.class);
                name = username1.getText().toString();
                intent.putExtra("Name", name);
                startActivity(intent);
                finish();
            }
        });
    }

}
