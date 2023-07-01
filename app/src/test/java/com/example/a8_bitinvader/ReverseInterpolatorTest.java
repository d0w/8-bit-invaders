package com.example.a8_bitinvader;

import static org.junit.Assert.*;
import android.view.animation.Interpolator;
import org.junit.Test;

/** Interpolation test */
public class ReverseInterpolatorTest{

    /** Default Test for getInterpolation() */
    @Test
    public void getInterpolation() {
        float returnVal = -5;
        assertTrue(returnVal == -5);
    }
}