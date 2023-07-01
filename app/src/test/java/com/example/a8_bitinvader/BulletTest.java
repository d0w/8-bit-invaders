package com.example.a8_bitinvader;

import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;

/** Bullet testing */
public class BulletTest {

    /** Checks if Bullet Constructor creates non-null instance of Bullet */
    @Test
    public void Bullet()
    {
        Bullet bul = new Bullet(5,5,5,5, 100, 100);
        assertNotNull(bul);
    }

    /** Checks if Bullet.isOffScreen() returns true for an instance of Bullet spawned off screen */
    @Test
    public void isOffScreen() {

        Bullet bul = new Bullet(-1000,-1000,5,5, 100, 100);
        assertTrue(bul.isOffScreen());
    }

    /** Checks if Bullet.checkForCollision() returns true if Bullet and Sprite instances occupy same space (collide) */
    @Test
    public void checkForCollision() {
        Bullet bul = new Bullet(5,5,5,5, 100, 100);
        Sprite sprite = new Sprite(5,5,5,5, 100, 100);
        assertTrue(bul.checkForCollision(sprite));
    }
}