package com.example.a8_bitinvader;

import static org.junit.Assert.*;
import org.junit.Test;

import junit.framework.TestCase;

/** Enemy testing */
public class EnemyTest {

    /** Checks if Enemy constructor creates non-null instance of Enemy */
    @Test
    public void Enemy() {
        Enemy en = new Enemy(5,5,5,5,5,5);
        assertTrue(en != null);
    }
}