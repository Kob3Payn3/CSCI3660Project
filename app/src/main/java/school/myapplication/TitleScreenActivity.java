package school.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TitleScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.titlescreen);

        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);

        // Title screen animation code
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
    }

    // Provides onClick function to enter Main Activity
    public void onEnterGameClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}