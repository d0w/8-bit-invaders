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

/**
 * Instrumented tests for level one
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedLevelOneTest {

    // test LevelOne Constructor that it creates a non-null LevelOne
    @Test
    public void LevelOne()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LevelOne levelOne = new LevelOne(appContext, 100, 100, 3);
        assertNotNull(levelOne);
        assertTrue(levelOne.enemyArrayList != null);
    }

}
