package school.myapplication;

import android.widget.TextView;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;


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

    public int getPlayerScore() {
        return playerScore;
    }

}

