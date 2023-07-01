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
 * Instrumented background test
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedBackgroundTest {

    // test creation of the background
    @Test
    public void makeBackground() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Background back = new Background(500, 500, appContext.getResources());
        //Background back = null;
        assertNotNull(back);
    }





}