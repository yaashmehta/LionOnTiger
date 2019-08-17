package com.example.lionortiger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    enum Player {

        ONE, TWO, No
    }
    Player currentPlayer = Player.ONE;


    Player[] playerChoices = new Player[9];
    int[][] winnerRowsColumns = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6},
            {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    Button btnReset;
    boolean gameover = false;


    private GridLayout LionGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerChoices[0] = Player.No;
        playerChoices[1] = Player.No;
        playerChoices[2] = Player.No;
        playerChoices[3] = Player.No;
        playerChoices[4] = Player.No;
        playerChoices[5] = Player.No;
        playerChoices[6] = Player.No;
        playerChoices[7] = Player.No;
        playerChoices[8] = Player.No;

        btnReset = findViewById(R.id.btnReset);
        LionGrid= findViewById(R.id.LionGrid);

        btnReset.setVisibility(View.GONE);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetTheGame();

            }
        });


    }

    public void imageViewIsTapped(View imageView) {



        ImageView tappedImageView = (ImageView) imageView;
        int tiTag = Integer.parseInt(tappedImageView.getTag().toString());

        if(playerChoices[tiTag] == Player.No && gameover != true) {
            Log.i("Mytag","Click1");
            playerChoices[tiTag] = currentPlayer;
            tappedImageView.setTranslationX(-2000);
            if (currentPlayer == Player.ONE) {
                tappedImageView.setImageResource(R.drawable.lion);
                currentPlayer = Player.TWO;
            } else if (currentPlayer == Player.TWO) {
                tappedImageView.setImageResource(R.drawable.tiger);
                currentPlayer = Player.ONE;
            }
            tappedImageView.animate().translationXBy(2000).
                    alpha(1).rotation(3600).setDuration(1000);



            for (int[] winnerColumns : winnerRowsColumns) {

                if (playerChoices[winnerColumns[0]] == playerChoices[winnerColumns[1]]
                        && playerChoices[winnerColumns[1]] == playerChoices[winnerColumns[2]]
                        && playerChoices[winnerColumns[0]] != Player.No) {

                    String winnerOfTheGame = "";

                    btnReset.setVisibility(View.VISIBLE);
                    gameover=true;
                    if (currentPlayer == Player.ONE) {
                        winnerOfTheGame = "Player Two";
                    } else if (currentPlayer == Player.TWO) {
                        winnerOfTheGame = "Player One";
                    }

                    Toast.makeText(this, winnerOfTheGame + "is the winner", Toast.LENGTH_LONG).show();


                }

            }
        }
    }

    public void resetTheGame(){
        for(int i=0;i<LionGrid.getChildCount();i++){
            ImageView imageView = (ImageView) LionGrid.getChildAt(i);

            imageView.setImageDrawable(null);
            imageView.setAlpha(0.4f);
        }
        currentPlayer = Player.ONE;

        playerChoices[0] = Player.No;
        playerChoices[1] = Player.No;
        playerChoices[2] = Player.No;
        playerChoices[3] = Player.No;
        playerChoices[4] = Player.No;
        playerChoices[5] = Player.No;
        playerChoices[6] = Player.No;
        playerChoices[7] = Player.No;
        playerChoices[8] = Player.No;
        gameover = false;
        btnReset.setVisibility(View.GONE);
    }
}

