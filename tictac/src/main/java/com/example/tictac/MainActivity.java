package com.example.tictac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView box1Red, box1Yellow, box2Red, box2Yellow, box3Red, box3Yellow;
    ImageView box4Red, box4Yellow, box5Red, box5Yellow, box6Red, box6Yellow;
    ImageView box7Red, box7Yellow, box8Red, box8Yellow, box9Red, box9Yellow;

    Button btnNewGame;
    TextView textWinner;

    boolean gameActive = true;

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int activePlayer = 0;

    public void dropIn(View view) {

        ImageView imageView = (ImageView) view;



        int tappedImage = Integer.parseInt(imageView.getTag().toString());


        if (gameState[tappedImage] == 2 && gameActive) {

            imageView.setTranslationY(-1500);

            gameState[tappedImage] = activePlayer;

            Log.i("Game", imageView.getTag().toString());

            if (activePlayer % 2 == 0) {
                imageView.setImageResource(R.drawable.red);
                activePlayer = 1;

            } else {
                imageView.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }

            imageView.animate().translationYBy(1500).setDuration(300);

            for (int[] position : winningPositions) {
                if (gameState[position[0]] == gameState[position[1]]
                        && gameState[position[1]] == gameState[position[2]]
                        && gameState[position[0]] != 2) {

                    String winner = "";

                    if (activePlayer == 0) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    textWinner.setText( winner + " won");
                    textWinner.setVisibility(View.VISIBLE);

                    btnNewGame.setVisibility(View.VISIBLE);
                    gameActive = false;

                    Toast.makeText(this, winner + " won", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textWinner = findViewById(R.id.textWinner);
        btnNewGame = findViewById(R.id.btnNewGame);



        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout layout = findViewById(R.id.inside_constrain);

                for (int i = 0; i < layout.getChildCount() ; i++) {
                    ImageView imageView = (ImageView) layout.getChildAt(i);

                    imageView.setImageDrawable(null);

                    gameState[i] = 2;
                    activePlayer = 0;
                    gameActive = true;

                    textWinner.setVisibility(View.INVISIBLE);
                    btnNewGame.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}
