package com.example.a8_bitinvader;

import static org.junit.Assert.*;

import org.junit.Test;

/** Player testing */
public class PlayerTest {

    /** Test if Player constructor creates non null Player instance */
    @Test
    public void Player()
    {
        Player player = new Player(5,5,5,5,100,100, true);
        assertTrue(player != null);
    }

    /** Test if Player.update() returns actual value of new position with non zero velocity */
    @Test
    public void update() {
        Player player = new Player(5,5,5,5,100,100, true);
        player.update();
        assertTrue(player.xPos == 10 && player.yPos == 10);
    }

    /** Cannot make unit test as this method uses getResources() */
    @Test
    public void shootNewBullet() {
        Player player = new Player(5,5,5,5,100,100, true);
        assertTrue(player != null);
    }
}