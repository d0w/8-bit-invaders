package com.example.a8_bitinvader;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.view.MotionEvent;
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

/** Instrumented tests for every level */
@RunWith(AndroidJUnit4.class)
public class InstrumentedLevelTest {


    // tests Level, tests construct, run(), resume(), and onTouchEvent()
    @Test
    public void LevelTest() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Level level = new Level(appContext, 100, 100, 4);
        assertNotNull(level);
        assertNotNull(level.joystick);
        assertNotNull(level.player);

        //tests resume()
        level.resume();
        assertTrue(level.isRunning == true);

        boolean bool = level.onTouchEvent(MotionEvent.obtain(500, 0, 5, 5, 5, 5));
        assertTrue(level.joystick.getIsPressed() == false);

    }
}
