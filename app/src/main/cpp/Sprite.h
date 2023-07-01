//
// Created by Nathan Strahs on 4/19/23.
//

#ifndef GROUP5PROJECT_SPRITE_H
#define GROUP5PROJECT_SPRITE_H
class Sprite {
public:
    /** X and Y position of the sprite */
    int xPos, yPos;

    /** X and Y velocity of the sprite */
    int xVel, yVel;

    /** Screen size */
    int screenWidth, screenHeight;

    /**
 * @param xPos initial x position of Sprite
 * @param yPos initial y position of Sprite
 * @param xVel initial x velocity of Sprite
 * @param yVel initial y velocity of Sprite
 */
    Sprite(int xPos, int yPos, int xVel, int yVel, int screenWidth, int screenHeight)
    {
        this->xPos = xPos;
        this->yPos = yPos;
        this->xVel = xVel;
        this->yVel = yVel;

        this->screenWidth = screenWidth;
        this->screenHeight = screenHeight;
    }
    /**
    * @return height of the sprite
    */
    int getSpriteHeight()
    {
        return spriteHeight;
    }
/**
     * @return width of the sprite
     */
int getSpriteWidth()
    {
        return spriteWidth;
    }
    /**
     *
     * @param xx
     * @param yy
     * @return True if xx and yy are in the current sprite
     */
    // IMPLEMENT THIS LATER TO CHECK FOR COLLISION WITH PASSING BULLET OBJECTS
    bool checkForCollision(int xx, int yy)
    {
        //implement
        if((xx < (xPos+spriteWidth) && xx > xPos) && (yy < (yPos+spriteHeight) && yy > yPos))
        {
            return true;
        }
        return false;
    }
    /**
     * Updates the sprite to move according to a given velocity
     */
void update() {
        xPos += xVel;
        yPos += yVel;
    }
    int spriteHeight = 128, spriteWidth = 128;


};


#endif //GROUP5PROJECT_SPRITE_H
