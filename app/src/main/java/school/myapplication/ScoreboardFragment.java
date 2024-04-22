package school.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
}