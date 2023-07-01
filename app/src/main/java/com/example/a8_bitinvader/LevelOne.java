package com.example.a8_bitinvader;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

/**
 * The first level which extends {@link Level} and adds its own parameters
 */
public class LevelOne extends Level {
    public int enemiesKilled;
    public LevelOne(Context context, int screenWidth, int screenHeight, int inputLives){
        super(context, screenWidth, screenHeight, inputLives);
        initEnemies();
        enemiesKilled = 0;
    }
    void initEnemies(){
        Random rand = new Random();

        /** spawn enemies with random x position and random velocity */
        enemyArrayList = new ArrayList<Enemy>(4);
        for(int ii = 0; ii < 4; ii++){
            enemyArrayList.add( new Enemy(rand.nextInt(7*screenWidth/8), -10, 0, minEnemyVelocity + rand.nextInt(maxEnemyVelocity), screenWidth, screenHeight, getResources()));
        }
    }
}
