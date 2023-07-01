package com.example.a8_bitinvader;

import android.graphics.Bitmap;

/**
 * Sprite class for all displayed sprites in the game. All calculations such as collision checking is written in C++ with the NDK
 */
public class Sprite {

    /** X and Y position of the sprite */
    int xPos, yPos;

    /** X and Y velocity of the sprite */
    int xVel, yVel;

    /** Screen size */
    int screenWidth, screenHeight;

    /** Image of the sprite */
    Bitmap spriteImage;

    /** Scalable value for enemies and player */
    public int spriteHeight = 128, spriteWidth = 128;

    /**
     * @param xPos initial x position of Sprite
     * @param yPos initial y position of Sprite
     * @param xVel initial x velocity of Sprite
     * @param yVel initial y velocity of Sprite
     */
    public Sprite(int xPos, int yPos, int xVel, int yVel, int screenWidth, int screenHeight)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.xVel = xVel;
        this.yVel = yVel;

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * Updates the sprite to move according to a given velocity
     */
    public void update() {
        xPos += xVel;
        yPos += yVel;
    }
}
