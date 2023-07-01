package com.example.a8_bitinvader;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.Button;
import android.content.Context;
import android.media.AudioManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.media.MediaPlayer;
import android.content.Context;
import android.widget. TextView;

/**
 * Game over screen which is called when the game is over
 */
public class GameOverActivity extends AppCompatActivity {
    Animation animation;
    private ScoreFileWriter  scoreFW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_finish);
        Context context = getApplicationContext();
        scoreFW = new ScoreFileWriter(context);

        /**  the if statement below hides the action bar
          *  https://www.geeksforgeeks.org/different-ways-to-hide-action-bar-in-android-with-examples/
          */
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        /** Goes back to GameActivity once replay button is pressed */
        findViewById(R.id.replay_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replay = new Intent(GameOverActivity.this, GameActivity.class);
                int inputLives = getIntent().getIntExtra("inputLives",3);
                replay.putExtra("inputLives", inputLives);
                startActivity(replay);
            }
        });

        /** Goes back to MainActivity Page when home button is pressed */
        findViewById(R.id.home_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameOverActivity.this, MainActivity.class));
            }
        });

        /** Display Player's Score (number of kills) from previous round */
        Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.player_score);
        String score = intent.getStringExtra("score");
        textView.setText(score);

        /**code to add the highscore on final page*/
        LinearLayout linearLayout = findViewById(R.id.linearLayoutScore);
        View view;

        /**gets string of high score*/
        String[] highScores = scoreFW.getTopScores();

        /** prints one score in each box for each of the 10 boxes */
        for(int i = 0; i < 10; i ++){
            view = linearLayout.getChildAt(i);

            if(view instanceof TextView) {
                TextView scoreText = (TextView) view;
                scoreText.setText(highScores[i]);
            }
        }

    }

}