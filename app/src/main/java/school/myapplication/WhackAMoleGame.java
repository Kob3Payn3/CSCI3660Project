package school.myapplication;

import android.widget.TextView;

public class WhackAMoleGame {

    private int playerScore = 0;
    private TextView scoreTextView;

    public WhackAMoleGame(TextView scoreTextView){
        this.scoreTextView = scoreTextView;
    }

    public void whackingTheMole(){

    playerScore = playerScore + 10;
    scoreTextView.setText("Score: " + playerScore);
    }

}
