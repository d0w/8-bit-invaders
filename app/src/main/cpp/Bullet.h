//
// Created by Nathan Strahs on 4/24/23.
//

#ifndef GROUP5PROJECT_BULLET_H
#define GROUP5PROJECT_BULLET_H

#include "Sprite.h"


class Bullet: Sprite {
/**
 * Creates an instance of a bullet with an initial position and velocity.
 * @param xPos Starting x position
 * @param yPos Starting y position
 * @param xVel X velocity of the bullet
 * @param yVel Y velocity of the bullet
 * @param screenWidth The screen width
 * @param screenHeight The screen height
 * @param res {@link Resources} object
 */
public:
Bullet(int xPos, int yPos, int xVel, int yVel, int screenWidth, int screenHeight): Sprite(xPos, yPos, xVel, yVel, screenWidth, screenHeight) {

        spriteHeight = 64;
        spriteWidth = 64;
    }

/**
 * Checks if the bullet is still on screen
 * @return True if the bullet is off the view screen
 */
bool isOffScreen() {
    return (yPos + spriteHeight < 0);
}

/**
 * Checks for collision between this bullet and sprite
 * @param sprite any sprite on the screen
 * @return true if this bullet collides with sprite
 */
bool checkForCollision(Sprite sprite) {
    int xPosMid = xPos + spriteWidth / 2;
    if( (xPosMid >= sprite.xPos
         && xPosMid <= sprite.xPos + sprite.spriteWidth
         && yPos <= sprite.yPos + sprite.spriteHeight
         && yPos + spriteHeight >= sprite.yPos) ) {
        return true;
    }
    return false;
    }
};


#endif //GROUP5PROJECT_BULLET_H
