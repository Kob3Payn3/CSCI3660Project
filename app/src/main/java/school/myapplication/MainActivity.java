package school.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private WhackAMoleGame game;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    long timerLength = 20000;
    private boolean shuffleEnabled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        game = new WhackAMoleGame(scoreTextView);
        disableMoleButtons();
        // Set up the timer
        countDownTimer = new CountDownTimer(timerLength, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                long timeLeft = millisUntilFinished / 1000;
                timerTextView.setText("Time Left: " + timeLeft + " seconds");
            }

            @Override
            public void onFinish() {
                // Finish the game when the timer ends
                endGame();
            }
        };

        // Set up the onClickListener for all mole buttons
        for (int i = 1; i <= 25; i++) {
            int buttonId = getResources().getIdentifier("moleHole" + i, "id", getPackageName());
            Button moleHoleButton = findViewById(buttonId);
            moleHoleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    v.setEnabled(false);
                    // Increase the score by 10 and shuffle buttons
                    game.whackingTheMole();
                    shuffleButtons();
                }
            });
        }

        // Start the game when the start button is clicked
        Button startButton = findViewById(R.id.start_game_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });
    }

    // Help Button Functionality
    public void onHelpClick(View view){
        Intent intent = new Intent(this, HelpInstructionsActivity.class);
        startActivity(intent);
    }

    // Settings button functionality
    public void onTimerOptions(View view){
        showTimerOptionsDialog();
    }

    //The dialog popup that gives timer duration options
    private void showTimerOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Timer Duration");

        final CharSequence[] timerOptions = {"20 Seconds", "15 Seconds", "10 Seconds"};
        builder.setItems(timerOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        timerLength = 20000;
                        break;
                    case 1:
                        timerLength = 15000;
                        break;
                    case 2:
                        timerLength = 10000;
                        break;
                }

                // New timer is created if any options are selected
                countDownTimer = new CountDownTimer(timerLength, 1000){
                    @Override
                    public void onTick(long millisUntilFinished) {
                        long timeLeft = millisUntilFinished / 1000;
                        timerTextView.setText("Time Left: " + timeLeft + " seconds");
                    }

                    @Override
                    public void onFinish() {
                        // Finish the game when the timer ends
                        endGame();
                    }
                };
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void startGame() {
        // Start the game by enabling buttons and starting the timer
        shuffleEnabled = true;
        shuffleButtons();
        countDownTimer.start();

        // Disable settings button on game start
        Button settingsButton = findViewById(R.id.timer_options_button);
        settingsButton.setEnabled(false);
    }

    private void endGame() {
        // Disable buttons and stop the timer when the game ends
        countDownTimer.cancel();
        disableMoleButtons();
        showEndGameDialog(game.getPlayerScore());
        game.resetPlayerScore();
        shuffleEnabled = false;
        // Enable settings button after game end
        Button settingsButton = findViewById(R.id.timer_options_button);
        settingsButton.setEnabled(true);
    }

    private void showEndGameDialog(int score) {
        // Create the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.total_score, null);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();

        // Set the score value
        TextView textViewScoreValue = dialogView.findViewById(R.id.textViewScoreValue);
        textViewScoreValue.setText(String.valueOf(score));

        // Show the dialog
        dialog.show();
    }

    private void disableMoleButtons() {
        // Disable all mole buttons
        for (int i = 1; i <= 25; i++) {
            int buttonId = getResources().getIdentifier("moleHole" + i, "id", getPackageName());
            Button moleHoleButton = findViewById(buttonId);
            moleHoleButton.setEnabled(false);
        }
    }

    private void shuffleButtons() {
        // Checks to see if shuffling is allowed
        if (!shuffleEnabled) {
            return;
        }

        Random random = new Random();
        // Button enabled at random time intervals in this range
        int delay = random.nextInt(500) + 500;
        // Randomize and enable buttons, the shuffling and enabling of buttons occurs at random intervals set by the delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ButtonRandomizer buttonRandomizer = new ButtonRandomizer();
                List<Integer> enabledButtonIds = buttonRandomizer.getRandomButtonIds(1);
                enableSelectedButtons(enabledButtonIds);
            }
        }, delay);
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