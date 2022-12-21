package vladut.myappcompany.connect3game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0: yellow, 1:reds, 2:empty
    int[] gameState= {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPoistions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                                {0, 4, 8}, {2, 4, 6}};
    int activePlayer = 0;

    boolean gameActive = true;

    public void dropin(View view){
         ImageView counter = (ImageView) view;



         int tappedCounter = Integer.parseInt(counter.getTag().toString()) ;

         if (gameState[tappedCounter] == 2 && gameActive) {

             counter.setTranslationY(-1500);
             gameState[tappedCounter] = activePlayer;

             if (activePlayer == 0) {
                 counter.setImageResource(R.drawable.yellow);
                 activePlayer = 1;
             } else {
                 counter.setImageResource(R.drawable.red);
                 activePlayer = 0;
             }

             counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

             for (int[] winningPosition : winningPoistions) {
                 if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                         gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                         gameState[winningPosition[0]] != 2) {
                     // Someone has won!

                     gameActive = false;

                     String winner = "";

                     if (activePlayer == 1) {
                         winner = "Yellow";
                     } else {
                         winner = "Red";
                     }

                     Button playAgainButon = (Button) findViewById(R.id.playAgainButton);
                     TextView winnerTextView = (TextView) findViewById(R.id.WinnerTextView);

                     winnerTextView.setText(winner + " has won");

                     playAgainButon.setVisibility(view.VISIBLE);
                     winnerTextView.setVisibility(view.VISIBLE);
                 }
             }
         }

    }

    public void playAgain(View view){
        Button playAgainButon = (Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.WinnerTextView);

        playAgainButon.setVisibility(view.INVISIBLE);
        winnerTextView.setVisibility(view.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int i = 0 ; i < gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0 ; i<gameState.length; i++) {
            gameState[i] = 2;
        }

        activePlayer = 0;

         gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}