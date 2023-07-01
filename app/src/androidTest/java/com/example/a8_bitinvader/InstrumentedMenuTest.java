package com.example.a8_bitinvader;

import android.content.Context;
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

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

/**
 * Instrumented tests for testing the menu and its UI
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedMenuTest {

    /**
     * Wraps the class to be in the MainActivity Scenario
     */
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.a8_bitinvader", appContext.getPackageName());
    }

    // test the play button goes away once in game
    @Test
    public void testPlay() {
        Espresso.onView(ViewMatchers.withId(R.id.play_button)).perform(ViewActions.click())
                .check(ViewAssertions.doesNotExist());
    }

    /**
     * Tests that the mute button works. Please note that this test works when isolated, but flags when running all the tests in the whole testing folder.
     * For menu testing, running just the sound test by itself is recommended
     */
    @Test
    public void testSound() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AudioManager audio = (AudioManager) appContext.getSystemService(Context.AUDIO_SERVICE);
        Espresso.onView(ViewMatchers.withId(R.id.mute_button)).perform(ViewActions.click());

        int currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        assertEquals(currentVolume, 0);
    }

//    @Rule
//    public ActivityScenarioRule<PopUpWindow> popUpActivity= new ActivityScenarioRule<>(PopUpWindow.class);
    @Test
    public void testLeaderBoard() {
        Espresso.onView(ViewMatchers.withId(R.id.leaderboard_button)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.popup_text))
                .check(ViewAssertions
                .matches(ViewMatchers.isDisplayed()));
    }


}