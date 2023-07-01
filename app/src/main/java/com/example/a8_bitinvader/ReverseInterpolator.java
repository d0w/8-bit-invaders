package com.example.a8_bitinvader;

import android.view.animation.Interpolator;

/** This class is used to create reverse effect on any animation */
public class ReverseInterpolator implements Interpolator {
   @Override
   public float getInterpolation(float paramFloat) {
      return Math.abs(paramFloat -1f);
   }
}