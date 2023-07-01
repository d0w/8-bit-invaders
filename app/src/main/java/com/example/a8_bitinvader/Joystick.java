package com.example.a8_bitinvader;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Joystick class which the user interacts with to control the player
 */
public class Joystick {
    private static final double MAX_SPEED = 3;
    private int outerCircleCenterX;
    private int outerCircleCenterY;
    private int innerCircleCenterX;
    private int innerCircleCenterY;
    private int outerCircleRadii;
    private int innerCircleRadii;
    private Paint innerCirclePaint;
    private Paint outerCirclePaint;
    private double joystickCenterToTouchDistance;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;
    public double XVelocity;
    public double YVelocity;

    /**
     * Constructs a Joystick instance
     * @param CenterPositionX X position of the joystick
     * @param CenterPositionY Y position of the joystick
     * @param outerCircleRad Radius of outer circle of joystick
     * @param innerCircleRad Radius of inner circle of joystick
     */
    public Joystick(int CenterPositionX, int CenterPositionY, int outerCircleRad, int innerCircleRad){
        outerCircleCenterX = CenterPositionX;
        outerCircleCenterY = CenterPositionY;
        innerCircleCenterX = CenterPositionX;
        innerCircleCenterY = CenterPositionY;
        outerCircleRadii = outerCircleRad;
        innerCircleRadii = innerCircleRad;

        outerCirclePaint= new Paint();
        outerCirclePaint.setColor(Color.CYAN);
        outerCirclePaint.setStyle(Paint.Style.STROKE);
        outerCirclePaint.setStrokeWidth(5);

        innerCirclePaint= new Paint();
        innerCirclePaint.setColor(Color.WHITE);
        innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    /**
     * Constructor of Joystick instance FOR TESTING
     * @param CenterPositionX X position of the joystick
     * @param CenterPositionY Y position of the joystick
     * @param outerCircleRad Radius of outer circle of joystick
     * @param innerCircleRad Radius of inner circle of joystick
     */
    public Joystick(int CenterPositionX, int CenterPositionY, int outerCircleRad, int innerCircleRad, boolean test) {
        outerCircleCenterX = CenterPositionX;
        outerCircleCenterY = CenterPositionY;
        innerCircleCenterX = CenterPositionX;
        innerCircleCenterY = CenterPositionY;
        outerCircleRadii = outerCircleRad;
        innerCircleRadii = innerCircleRad;
    }

    /**
     * Draws the joystick onto the given canvas
     * @param canvas The canvas object to draw on
     */
    public void draw(Canvas canvas) {
        //outer
        canvas.drawCircle(
                outerCircleCenterX,
                outerCircleCenterY,
                outerCircleRadii,
                outerCirclePaint
        );
        //inner
        canvas.drawCircle(
                innerCircleCenterX,
                innerCircleCenterY,
                innerCircleRadii,
                innerCirclePaint
        );
    }

    /**
     * Updates the given joystick and updates the x and y velocities that are outputted from user input
     * @param joystick Joystick instance to update
     */
    public void update(Joystick joystick) {
        XVelocity = joystick.getActuatorX() * MAX_SPEED;
        YVelocity = joystick.getActuatorY() * MAX_SPEED;
        updateInnerCirclePosition();
    }

    /**
     * Updates the inner circle of the joystick
     */
    private void updateInnerCirclePosition() {
        innerCircleCenterX = (int) (outerCircleCenterX + actuatorX * outerCircleRadii);
        innerCircleCenterY = (int) (outerCircleCenterY + actuatorY * outerCircleRadii);
    }

    /**
     * Checks whether the joystick has been touched by the user
     * @param TouchPosX X position of the user's touch
     * @param TouchPosY Y position of the user's touch
     * @return True if the joystick has been pressed
     */
    public boolean isPressed(double TouchPosX,double TouchPosY) {
        joystickCenterToTouchDistance = Math.sqrt(Math.pow(outerCircleCenterX-TouchPosX,2) +
                Math.pow(outerCircleCenterY-TouchPosY,2)
        );
        return  joystickCenterToTouchDistance < outerCircleRadii;
    }

    /**
     * Set the joystick to be pressed or not pressed
     * @param isPressed Boolean for whether the joystick is pressed
     */
    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    /**
     *
     * @return True if the joystick is pressed
     */
    public boolean getIsPressed() {
        return isPressed;
    }

    /**
     * Sets the value of actuation depending on how far away the user touches from the center
     * of the joystick.
     * @param TouchPosX X position of the user touch
     * @param TouchPosY Y position of the user touch
     */
    public void setActuator(double TouchPosX,double TouchPosY) {
        double deltaX = TouchPosX - outerCircleCenterX;
        double deltaY = TouchPosY - outerCircleCenterY;
        double deltaDistance = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY,2));

        if(deltaDistance < outerCircleRadii) {
            actuatorX = deltaX/outerCircleRadii;
            actuatorY = deltaY/outerCircleRadii;
        }else {
            actuatorX = deltaX/deltaDistance;
            actuatorY = deltaY/deltaDistance;
        }

    }

    /**
     * Resets the value of actuation to 0
     */
    public void resetActuator() {
        actuatorX = 0.0;
        actuatorY = 0.0;
    }

    /**
     *
     * @return The x value of actuation
     */
    public double getActuatorX() {
        return actuatorX;
    }

    /**
     *
     * @return The Y value of actuation
     */
    public double getActuatorY() {
        return actuatorY;
    }

}
