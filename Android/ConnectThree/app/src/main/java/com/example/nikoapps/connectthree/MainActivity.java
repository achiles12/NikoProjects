package com.example.nikoapps.connectthree;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow
    // 1 = red
    int activePlayer = 0;
    String winner = "Yellow";
    boolean gameActive = true;
    int move = 0;

    // 2 = unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] win = {{0, 1, 2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("game state start");
        for (int x=0;x<9;x++) {System.out.print(gameState[x]);}
        System.out.println("");

    }

    public void drop(View view) {

        ImageView count = (ImageView) view;

        int tapCount = Integer.parseInt(count.getTag().toString());

        if (gameActive == true) {

            if (gameState[tapCount] == 2) {
                count.setTranslationY(-1000f);
                System.out.println(count.getTag().toString());


                if (activePlayer == 0) {
                    count.setImageResource(R.drawable.yellow);
                    gameState[tapCount] = activePlayer;
                    activePlayer = 1;
                } else {
                    count.setImageResource(R.drawable.red);
                    gameState[tapCount] = activePlayer;
                    activePlayer = 0;
                }

                count.animate().translationYBy(1000f).rotation(3600).setDuration(350);

                System.out.println("after move");
                for (int x=0;x<9;x++) {System.out.print(gameState[x]);}
                System.out.println("");
            }

            for (int[] win : win) {

                // check winner
                if (gameState[win[0]] == gameState[win[1]] &&
                        gameState[win[1]] == gameState[win[2]] &&
                        gameState[win[0]] != 2) {

                    if (activePlayer == 0) {
                        winner = "Red";
                    }

                    gameActive = false;
                    Toast.makeText(getApplicationContext(),"Player " + winner + " Wins!", Toast.LENGTH_LONG).show();

                    Button resetBtn = (Button) findViewById(R.id.resetButton);
                    resetBtn.setEnabled(true);
                }
            }


            move++;
            if (move == 9 && gameActive == true){
                gameActive = false;
                Toast.makeText(getApplicationContext(),"It's a Draw!", Toast.LENGTH_LONG).show();

                Button resetBtn = (Button) findViewById(R.id.resetButton);
                resetBtn.setEnabled(true);
            }

            for (int counterState : gameState) {
                System.out.println("counter State " + counterState + " here");
            }
        }
    }

    public void resetGame(View view) {
        ImageView slot00 = (ImageView) findViewById(R.id.slot00);
        ImageView slot01 = (ImageView) findViewById(R.id.slot01);
        ImageView slot02 = (ImageView) findViewById(R.id.slot02);
        ImageView slot10 = (ImageView) findViewById(R.id.slot10);
        ImageView slot11 = (ImageView) findViewById(R.id.slot11);
        ImageView slot12 = (ImageView) findViewById(R.id.slot12);
        ImageView slot20 = (ImageView) findViewById(R.id.slot20);
        ImageView slot21 = (ImageView) findViewById(R.id.slot21);
        ImageView slot22 = (ImageView) findViewById(R.id.slot22);

        Button resetBtn = (Button) findViewById(R.id.resetButton);
        resetBtn.setEnabled(false);

        slot00.setImageResource(0);
        slot01.setImageResource(0);
        slot02.setImageResource(0);
        slot10.setImageResource(0);
        slot11.setImageResource(0);
        slot12.setImageResource(0);
        slot20.setImageResource(0);
        slot21.setImageResource(0);
        slot22.setImageResource(0);

        for (int x=0; x < gameState.length; x++) {
            gameState[x] = 2;
        }

        winner = "Yellow";
        gameActive = true;
        move = 0;

    }
}
