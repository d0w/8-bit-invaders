package com.example.a8_bitinvader;

import android.content.Context;
import android.content.Intent;
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
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.ActivityTestRule$$ExternalSyntheticLambda0;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

/**
 * Instrumented game over test
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedGameOverTest {
    @Rule
        public ActivityTestRule<GameOverActivity> gameOver = new ActivityTestRule<>(GameOverActivity.class);

    /**
     * Test the creation of the game over activity by first launching the game over activity and then
     * checking whether the replay_button exists on the screen and is clickable.
     * All of the UI checks utilize {@link Espresso} in order to automate interactions
     */
    @Test
    public void gameOver() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(appContext, GameOverActivity.class);
        gameOver.launchActivity(intent);

        Espresso.onView(ViewMatchers.withId(R.id.replay_button))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.replay_button))
                .check(ViewAssertions.matches(ViewMatchers.isClickable()));
    }





}