package school.myapplication;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

// DEPRECATED

public class Scoreboard {
    private static Scoreboard instance;
    private List<Score> scoreBoard;

    public static Scoreboard getInstance(Context context) {
        if(instance == null) {
            instance = new Scoreboard(context);
        }
        return instance;
    }

    private Scoreboard(Context context) {
        scoreBoard = new ArrayList<>();
        Resources res = context.getResources();



        //String[] playerNames = res.getStringArray(R.array.player_names);
        //int[] playerScores = res.getIntArray(R.array.player_scores);

        //for (int i = 0; i < playerScores.length; i++) {
        //    scoreBoard.add(new Score(i + 1, playerNames[i], playerScores[i]));
        //}
    }

    public List<Score> getScores() { return scoreBoard; }

    public Score getScore(int playerId) {
        for (Score score : scoreBoard) {
            if (score.getId() == playerId) {
                return score;
            }
        }
        return null;
    }

    public void addScore(Score score) {
        scoreBoard.add(score);
    }

}
