package com.example.a8_bitinvader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Random;

/**
 * The enemy class extended from {@link Sprite}
 */
public class Enemy extends Sprite{

    /** Possible enemy sprites */
    private final int[] SpriteImages =
    {
        R.drawable.enemy1,
                R.drawable.enemy5,
                R.drawable.enemy3,
                R.drawable.enemy4,
    };

    /**
     * Initialize an enemy with the given coordinates and initial velocity
     * @param xPos X point
     * @param yPos Y point
     * @param xVel X velocity
     * @param yVel Y velocity
     * @param res Resource object
     */
    public Enemy(int xPos, int yPos, int xVel, int yVel, int screenWidth, int screenHeight, Resources res) {
        super(xPos, yPos, xVel, yVel, screenWidth, screenHeight);
        this.xPos = xPos;
        this.yPos = yPos;

        Random rand = new Random();
        int randInt = rand.nextInt(SpriteImages.length);
        spriteImage = BitmapFactory.decodeResource(res, SpriteImages[randInt]);
        spriteImage = Bitmap.createScaledBitmap(spriteImage, spriteWidth, spriteHeight, false);
    }

    /**
     * Constructor for testing
     * @param xPos
     * @param yPos
     * @param xVel
     * @param yVel
     * @param screenWidth
     * @param screenHeight
     */
    public Enemy(int xPos, int yPos, int xVel, int yVel, int screenWidth, int screenHeight) {
        super(xPos, yPos, xVel, yVel, screenWidth, screenHeight);
        this.xPos = xPos;
        this.yPos = yPos;
    }
}