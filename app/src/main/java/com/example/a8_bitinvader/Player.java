package com.example.a8_bitinvader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

/**
 * The player class which extends {@link Sprite}
 */
public class Player extends Sprite{

    /** Lives of player */
    public int playerLives = 3;

    /** Player Kills */
    public int playerKills = 0;

    /** Speed */
    public final int SPEED_MULTIPLIER = 10;

    /** Bullet List */
    ArrayList<Bullet> bulletList;

    /** Resources */
    Resources res;
    private static int selectedCharacterIndex;

    /**
     * Constructs a new player at given coordinates and
     * @param xPos X position of player
     * @param yPos Y position of player
     * @param xVel X velocity of player
     * @param yVel Y velocity of player
     * @param res Resource object
     */
    public Player(int xPos, int yPos, int xVel, int yVel, int screenWidth, int screenHeight, Resources res) {
        super(xPos,yPos,xVel,yVel, screenWidth, screenHeight);
        this.xPos = xPos;
        this.yPos = yPos;
        this.res = res;

        int[] characterImages = {R.drawable.player, R.drawable.player2, R.drawable.player3, R.drawable.player4};
        int characterImageResource = characterImages[selectedCharacterIndex];

        spriteImage = BitmapFactory.decodeResource(res, characterImageResource);
        spriteImage = Bitmap.createScaledBitmap(spriteImage, spriteWidth, spriteHeight, false);

        bulletList = new ArrayList<Bullet>(10);
    }
    public static void setSelectedCharacterIndex(int index) {
        selectedCharacterIndex = index;
    }

    /**
     * CONSTRUCTOR FOR TESTINGConstructs a new player at given coordinates and
     * @param xPos X position of player
     * @param yPos Y position of player
     * @param xVel X velocity of player
     * @param yVel Y velocity of player
     */
    public Player(int xPos, int yPos, int xVel, int yVel, int screenWidth, int screenHeight, boolean test) {
        super(xPos,yPos,xVel,yVel, screenWidth, screenHeight);
        this.xPos = xPos;
        this.yPos = yPos;

        bulletList = new ArrayList<Bullet>(10);
    }

    /**
     * Update the position of the player based on joystick offset
     * @param joyStickInputX X input from joystick
     * @param joyStickInputY Y input from joystick
     */
    public void update(int joyStickInputX, int joyStickInputY){
        xVel = joyStickInputX * SPEED_MULTIPLIER;
        yVel = joyStickInputY * SPEED_MULTIPLIER;

        /** calls Sprite update(). Updates xPos and yPos */
        update();

        /** checks if Player is offscreen. If so, repositions them within the screen */
        if(xPos < 0){
            xPos = 5;
        } else if(xPos + spriteWidth > screenWidth){
            xPos = screenWidth - spriteWidth - 5;
        }

        if(yPos < 0){
            yPos = 5;
        } else if(yPos + spriteHeight > screenHeight){
            yPos = screenHeight - spriteHeight - 5;
        }
    }
    public void shootNewBullet(){
        bulletList.add(new Bullet(this.xPos + spriteWidth/2, this.yPos, 0, -25, screenWidth, screenWidth, res));
    }
}
