package com.example.a8_bitinvader;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.graphics.Point;              // necessary to get ScreenWidth and ScreenHeight
import android.view.WindowManager;

/**
 * The main GameActivity that begins the game upon being called.
 */
public class GameActivity extends AppCompatActivity {
    private Level currLevel;

    /**
     * Create an instance of the activity based on the saved state. Then initialize the gameview and display the gameview onto the screen
     * @param savedInstanceState The cached game state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** puts app into fullscreen when GameActivity is running */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        Intent startIntent = getIntent();

        /** get number of player lives from MainActivity */
        int inputLives = startIntent.getIntExtra("inputLives", 3);

        currLevel = new LevelOne(this, point.x, point.y, inputLives);
        setContentView(currLevel);

    }

    /**
     * Pause the game
     */
    @Override
    protected void onPause() {
        super.onPause();
        currLevel.pause();
    }

    /**
     * Resume the game
     */
    @Override
    protected void onResume() {
        super.onResume();
        currLevel.resume();
    }
}
