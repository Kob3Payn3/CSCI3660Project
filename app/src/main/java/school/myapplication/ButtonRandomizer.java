package school.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ButtonRandomizer {
    private final List<Integer> buttonIds;

    public ButtonRandomizer() {
        // Initialize the list with button ids
        buttonIds = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            buttonIds.add(i);
        }
    }

    public List<Integer> getRandomButtonIds(int numberOfButtonsToEnable) {
        // Shuffle the button ids
        Collections.shuffle(buttonIds);

        // Select the first 'numberOfButtonsToEnable' buttons after shuffling
        return buttonIds.subList(0, numberOfButtonsToEnable);
    }
}