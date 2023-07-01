package com.example.a8_bitinvader;

import static org.junit.Assert.*;

import org.junit.Test;

/** Sprite testing */
public class SpriteTest {

    /** Test if Sprite constructor successfully creates non null Sprite instance */
    @Test
    public void Sprite()
    {
        Sprite sprite = new Sprite(5,5,5,5,100,100);
        assertTrue(sprite != null);
    }

    /** Test if Sprite.update() returns correct value of the new position with non-zero velocity */
    @Test
    public void update() {
        Sprite sprite = new Sprite(5,5,1,1,100,100);
        sprite.update();
        assertTrue(sprite.xPos == 6 && sprite.yPos == 6);
    }
}