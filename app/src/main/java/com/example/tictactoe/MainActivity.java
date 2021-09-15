package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    // 0 = cross, 1 = circle
    int activePlayer = 0;

    boolean gameIsActive = true;

    // 2 means not played yet
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{6,7,8}, // horizontal
                             {0,3,6},{1,4,7},{2,5,8}, // vertical
                             {0,4,8},{2,4,6}};        // diagonal

    public void appear(View view){

        ImageView counter =(ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameIsActive) {
            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.x);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
            }
            for (int[] winningPosition : winningPositions){
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&    // checks if first and second index player are the same
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] && // checks if second and third index player are the same
                        gameState[winningPosition[0]] != 2) {               // checks if no place is empty

                    // announce the winner
                    gameIsActive = false;

                    String winner = "Circle";
                    if (gameState[winningPosition[0]] == 0){
                        winner = "Cross";
                    }
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(winner + " has won!");
                    System.out.println("winner: " + gameState[winningPosition[0]]);

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                }else{
                    boolean gameIsOver = true;

                    for (int i = 0; i< gameState.length;i++){
                        if (gameState[i] == 2){
                            gameIsOver = false;
                        }
                    }
                    if (gameIsOver){

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText(" It's a draw");
                        System.out.println("winner: " + gameState[winningPosition[0]]);

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
            }
        }

    }
    public void playAgain(View view) {

        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);


        activePlayer = 0;

        Arrays.fill(gameState, 2);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i < gridLayout.getChildCount();i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}