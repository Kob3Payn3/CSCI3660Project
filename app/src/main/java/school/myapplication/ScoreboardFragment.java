package school.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


public class ScoreboardFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scoreboard, container, false);

        // Send bands to RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.score_list);
        List<Score> scoreBoard = Scoreboard.getInstance(requireContext()).getScores();

        return view;
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
            return new ScoreHolder(parent);
        }

        // Replace the contents of a view
        @Override
        public void onBindViewHolder(@NonNull ScoreHolder holder, int position) {
            Score score = scoreList.get(position);
            holder.bind(score);
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
        private final TextView scoreTextView;

        public ScoreHolder(View view) {
            super(view);
            scoreTextView = (TextView) view.findViewById(R.id.score_list);
        }

        public void bind(Score score) {
            scoreTextView.setText(score.getName());
        }
    }
}