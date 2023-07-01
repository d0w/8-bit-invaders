package com.example.a8_bitinvader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * The space background object
 */
public class Background {

    int xPos = 0, yPos = 0;
    Bitmap background;

    /**
     * Creates a background
     * @param screenWidth Width of screen in pixels
     * @param screenHeight Height of screen in pixels
     * @param res Resource object
     */
    Background(int screenWidth, int screenHeight, Resources res) {
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = Bitmap.createScaledBitmap(background, screenWidth, screenHeight, false);
    }

}
