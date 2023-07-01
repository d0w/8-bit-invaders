package com.example.a8_bitinvader;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.PositionAssertions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

/**
 * Instrumented tests for the actual gameplay
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedGameTests {


    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    private void play() {
        Espresso.onView(ViewMatchers.withId(R.id.play_button)).perform(ViewActions.click());
    }

    // test that the player can shoot
    @Test
    public void testShoot() {
        play();
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Player play = new Player(5,5,0,0,100,100, appContext.getResources());
        play.shootNewBullet();
        assertEquals(play.bulletList.size(), 1);

    }

    /**
     * Test if a bullet correctly collides with an enemy
     * Creates a player and enemy that are in line vertically, the player then shoots, then checks if the bullet is collided
     */
    @Test
    public void testCollision() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Player play = new Player(10, 10, 0, 0, 100, 100, appContext.getResources());
        Enemy enemy = new Enemy(10, 10, 0, 0, 100, 100, appContext.getResources());

        play.shootNewBullet();
        assertTrue(play.bulletList.get(0).checkForCollision(enemy));
    }





}