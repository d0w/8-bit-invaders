package com.example.a8_bitinvader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

/**
 * The bullet class that the player shoots to destroy enemies
 */
public class Bullet extends Sprite {

    /**
     * Creates an instance of a bullet with an initial position and velocity.
     * @param xPos Starting x position
     * @param yPos Starting y position
     * @param xVel X velocity of the bullet
     * @param yVel Y velocity of the bullet
     * @param screenWidth The screen width
     * @param screenHeight The screen height
     * @param res {@link Resources} object
     */
    public Bullet(int xPos, int yPos, int xVel, int yVel, int screenWidth, int screenHeight, Resources res) {
        super(xPos, yPos, xVel, yVel, screenWidth, screenHeight);

        spriteHeight = 64;
        spriteWidth = 64;

        spriteImage = BitmapFactory.decodeResource(res, R.drawable.bullet);
        spriteImage = Bitmap.createScaledBitmap(spriteImage, spriteWidth, spriteHeight, false);
    }

    /**
     * CONSTRUCTOR FOR TESTING Creates an instance of a bullet with an initial position and velocity.
     * @param xPos Starting x position
     * @param yPos Starting y position
     * @param xVel X velocity of the bullet
     * @param yVel Y velocity of the bullet
     * @param screenWidth The screen width
     * @param screenHeight The screen height
     */
    public Bullet(int xPos, int yPos, int xVel, int yVel, int screenWidth, int screenHeight) {
        super(xPos, yPos, xVel, yVel, screenWidth, screenHeight);
        spriteHeight = 64;
        spriteWidth = 64;
    }

    /**
     * Checks if the bullet is still on screen
     * @return True if the bullet is off the view screen
     */
    public boolean isOffScreen() {
        return (yPos + spriteHeight < 0);
    }

    /**
     * Checks for collision between this bullet and sprite
     * @param sprite any sprite on the screen
     * @return true if this bullet collides with sprite
     */
    public boolean checkForCollision(Sprite sprite) {
        int xPosMid = xPos + spriteWidth / 2;
        if( (xPosMid >= sprite.xPos
                && xPosMid <= sprite.xPos + sprite.spriteWidth
                && yPos <= sprite.yPos + sprite.spriteHeight
                && yPos + spriteHeight >= sprite.yPos) ) {
            return true;
        }
        return false;
    }
}
