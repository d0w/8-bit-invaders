package com.example.a8_bitinvader;

import static org.junit.Assert.*;

import org.junit.Test;

/** Joystick unit testing */
public class JoystickTest {

    /** Test if Joystick constructor constructs non null Joystick instance */
    @Test
    public void Joystick()
    {
        Joystick j = new Joystick(5,5,5,5, true);
        assertTrue(j != null);
    }

    /** Test if Joystick gets created and is drawn on screen */
    @Test
    public void draw() {
        Joystick j = new Joystick(5,5,5,5, true);
        assertTrue(j != null);
    }

    /** Test if Joystick.update() does not alter velocity variables without user input */
    @Test
    public void update() {
        Joystick j = new Joystick(5,5,5,5, true);
        j.update(j);
        assertTrue(j.XVelocity == 0 && j.YVelocity == 0);
    }

    /** Test if Joystick.isPressed() returns true if pressed */
    @Test
    public void isPressed() {
        Joystick j = new Joystick(5,5,5,5, true);
        assertTrue(j.isPressed(5,5) == true);
    }

    /** Test if Joystick.setIsPressed(true) actually sets Joystick's isPressed property to true */
    @Test
    public void setIsPressed() {
        Joystick j = new Joystick(5,5,5,5, true);
        j.setIsPressed(true);
        assertTrue(j.getIsPressed() == true);
    }

    /** Test if Joystick.getIsPressed() will return true if isPressed property is set to true */
    @Test
    public void getIsPressed() {
        Joystick j = new Joystick(5,5,5,5, true);
        j.setIsPressed(true);
        assertTrue(j.getIsPressed() == true);
    }

    /** Test if setActuator(10,10) set actuatorX and actuatorY properties to correct value of 0.5 */
    @Test
    public void setActuator() {
        Joystick j = new Joystick(5,5,10,5, true);
        j.setActuator(10,10);
        assertTrue(j.getActuatorX() == 0.5 && j.getActuatorY() == 0.5);
    }

    /** Test if resetActuator() resets actuatorX and actuatorY properties back to 0 */
    @Test
    public void resetActuator() {
        Joystick j = new Joystick(5,5,5,5, true);
        j.setActuator(5,5);
        j.resetActuator();
        assertTrue(j.getActuatorX() == 0 && j.getActuatorY() == 0);
    }

    /** Test if getActuatorX() returns actual value of actuatorX */
    @Test
    public void getActuatorX() {
        Joystick j = new Joystick(5,5,5,5, true);
        j.resetActuator();
        assertTrue(j.getActuatorX() == 0);
    }

    /** Test if getActuatorY() returns actual value of actuatorY */
    @Test
    public void getActuatorY() {
        Joystick j = new Joystick(5,5,5,5, true);
        j.resetActuator();
        assertTrue(j.getActuatorY() == 0);
    }
}