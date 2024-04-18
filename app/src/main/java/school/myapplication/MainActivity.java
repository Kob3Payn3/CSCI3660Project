package school.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private WhackAMoleGame game;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private final long timerLength = 30000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        game = new WhackAMoleGame(scoreTextView);

        disableMoleButtons();

        // Allows user to tap all buttons on the grid
        for (int i=1; i<=9; i++){
            int buttonId = getResources().getIdentifier("moleHole" + i, "id", getPackageName());
            Button moleHoleButton = findViewById(buttonId);
            moleHoleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    game.whackingTheMole();
                }
            });

        }

        Button startButton = findViewById(R.id.start_game_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        countDownTimer = new CountDownTimer(timerLength, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                long timeLeft = millisUntilFinished / 1000;
                timerTextView.setText("Time Left: " + timeLeft + " seconds");
            }
            @Override
            public void onFinish(){

            }
        };
    }

    private void startGame() {
        startButtons();
        countDownTimer.start();
    }


    // Help Button Functionality
    public void onHelpClick(View view){
        Intent intent = new Intent(this, HelpInstructionsActivity.class);
        startActivity(intent);
    }

    private void disableMoleButtons() {
        for (int i = 1; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("moleHole" + i, "id", getPackageName());
            Button moleHoleButton = findViewById(buttonId);
            moleHoleButton.setEnabled(false);
        }
    }

    /*
        private void enableMoleButtons() {
        for (int i = 1; i <= 9; i++) {
            int buttonId = getResources().getIdentifier("moleHole" + i, "id", getPackageName());
            Button moleHoleButton = findViewById(buttonId);
            moleHoleButton.setEnabled(true);
        }
    }
    */

    private void startButtons() {
        // Randomize the buttons
        ButtonRandomizer buttonRandomizer = new ButtonRandomizer();
        List<Integer> enabledButtonIds = buttonRandomizer.getRandomButtonIds(3);

        // Enable only the selected buttons
        enableSelectedButtons(enabledButtonIds);

        // Start the timer
        countDownTimer.start();
    }

    private void enableSelectedButtons(List<Integer> enabledButtonIds) {
        // Disable all buttons first
        disableMoleButtons();

        // Enable only the selected buttons
        for (int buttonId : enabledButtonIds) {
            int buttonResourceId = getResources().getIdentifier("moleHole" + buttonId, "id", getPackageName());
            Button moleHoleButton = findViewById(buttonResourceId);
            moleHoleButton.setEnabled(true);
        }
    }




}