package school.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardFragment extends Fragment {
    private List<Score> scoreBoard;
    private SQLiteDatabase scoreDB;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scoreboard, container, false);

        scoreDB = null;
        try {
            initScoreDB();
        } catch (Exception e) {

        } finally {
            // Create and initialize the RecyclerView
            RecyclerView recyclerView = view.findViewById(R.id.score_recycler_view);
            scoreBoard = new ArrayList<Score>();
            recyclerView.setAdapter(new ScoreAdapter(scoreBoard));

            toListFromDB();
        }

        return view;
    }

    // Opens a DB or creates a new one if one does not exist.
    private void initScoreDB() {
        try {
            scoreDB = SQLiteDatabase.openOrCreateDatabase("/data/data/school.myapplication/scoreboard/", null, null);
            scoreDB.execSQL("CREATE TABLE IF NOT EXISTS scores (name TEXT, score TEXT);");

            Cursor cursor = scoreDB.rawQuery("SELECT * FROM scores", null);

            // TEST
            if (cursor != null) {
                if (cursor.getCount() == 0) {
                    scoreDB.execSQL("INSERT INTO scores (name, score) VALUES ('test', '100')");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Creates a list from the DB to work with in AndroidStudio
    private void toListFromDB() {
        try {
            Cursor cursor = scoreDB.rawQuery("SELECT * FROM scores", null);

            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {

                    Score score = new Score();
                    score.setName(cursor.getString(0));
                    score.setScore(Integer.parseInt(cursor.getString(1)));

                    scoreBoard.add(score);
                    cursor.moveToNext();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Adds a score to the DB.
    public void addScore(/*String name,*/ int value) {
        try {
            Cursor cursor = scoreDB.rawQuery("SELECT * FROM scores", null);
            scoreDB.execSQL("INSERT INTO scores (name, score) VALUES ('Default Player', value)");
        } catch (Exception e) {
            System.out.println(e);
        }

        // Update the scoreboard
        scoreBoard = null;
        scoreBoard = new ArrayList<Score>();
        toListFromDB();
    }





    /**
     * Creates an adapter to manage the ViewHolders
     */
    private class ScoreAdapter extends RecyclerView.Adapter<ScoreHolder> {
        private final List<Score> scoreList;
        //private final View.OnClickListener onClickListener;

        // Constructor to get the scores and event handler for the Scoreboard Fragment.
        public ScoreAdapter(List<Score> scoreList) { //, View.OnClickListener onClickListener
            this.scoreList = scoreList;
            //this.onClickListener = onClickListener;
        }

        // Create new views
        @NonNull
        @Override
        public ScoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new ScoreHolder(layoutInflater, parent);
        }

        // Replace the contents of a view
        @Override
        public void onBindViewHolder(@NonNull ScoreHolder holder, int position) {
            Score score = scoreList.get(position);
            holder.bind(score);
            holder.itemView.setTag(score.getId());
            //holder.itemView.setOnClickListener(onClickListener);
        }

        // Return the size of the dataset
        @Override
        public int getItemCount() {
            return scoreList.size();
        }
    }






    /**
     * Provides a reference to the type of views the ScoreAdapter manages.
     */
    private static class ScoreHolder extends RecyclerView.ViewHolder {
        private final TextView playerTextView;
        private final TextView scoreTextView;

        public ScoreHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_scoreboard, parent, false));
            playerTextView = itemView.findViewById(R.id.player_name);
            scoreTextView = itemView.findViewById(R.id.player_score);
        }

        public void bind(Score score) {
            playerTextView.setText(score.getName());
            scoreTextView.setText(Integer.toString(score.getScore()));
        }
    }
}