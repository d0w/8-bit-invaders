#include <jni.h>
#include <string>
#include <iostream>
#include <vector>
#include <algorithm>
#include "Sprite.h"
#include "Bullet.h"
static std::vector<int> scores;
static int totalScore;


extern "C"
JNIEXPORT void JNICALL
/**
 * keeps a local copy of the player scores (will not save after closing the file)
 * @param env
 * @param thiz
 * @param value
 *
 */
Java_com_example_a8_1bitinvader_Level_pushScore(JNIEnv *env, jobject thiz, jint value) {
    if(value >= scores[4]){
        scores.pop_back();
        scores.push_back(value);
    }
    std::sort(scores.begin(),scores.end());
}

extern "C"
JNIEXPORT jint JNICALL
/**
 * retrieves a copy of the local score at some specified index
 * @param env
 * @param thiz
 * @param index
 * @return the value of that particular run
 */
Java_com_example_a8_1bitinvader_Level_getScore(JNIEnv *env, jobject thiz, jint index) {
    if (index >= scores.size() && index <= 0)
        return 0;
    else
        return scores[index];
}

//when you pull, please delete your .gradle folder, and build folder(this one is in app folder), and rebuild the app, there is some issue with the NDK and gradle not able to include all the c++ libraries correctly.
/**
 * uses the bullet class to calculate the collision
 * @param bullet
 * @param sprite
 * @return a boolean for the collision
 */
bool checkForCollision(Bullet bullet, Sprite sprite) {
    return bullet.checkForCollision(sprite);
}



/**
 * Calcuates whether the bullet (what this is used for) is offscreen
 * @param yPos
 * @param screenHeight
 * @return a boolean representing whether the bullet is offscreen
 */
extern "C"
JNIEXPORT jboolean JNICALL
Java_com_example_a8_1bitinvader_MainActivity_isOffScreen(JNIEnv *env, jobject thiz, jint yPos, jint screenHeight) {
    return (yPos + screenHeight < 0);
}


/** Creates a sprite and bullet from all of its attributes and uses them to use the bullet class' claculates for collisions
 *
 * @param env
 * @param thiz
 * @param b_x_pos
 * @param b_y_pos
 * @param b_x_vel
 * @param b_y_vel
 * @param b_screen_width
 * @param b_screen_height
 * @param s_x_pos
 * @param s_y_pos
 * @param s_x_vel
 * @param s_y_vel
 * @param s_screen_width
 * @param s_screen_height
 * @return boolean for the event of a collision
 */
 extern "C"
JNIEXPORT jboolean JNICALL
Java_com_example_a8_1bitinvader_Level_checkForCollision(JNIEnv *env, jobject thiz, jint b_x_pos,
                                                        jint b_y_pos, jint b_x_vel, jint b_y_vel,
                                                        jint b_screen_width, jint b_screen_height,
                                                        jint s_x_pos, jint s_y_pos, jint s_x_vel,
                                                        jint s_y_vel, jint s_screen_width,
                                                        jint s_screen_height) {
    Bullet bullet = Bullet(b_x_pos, b_y_pos, b_x_vel, b_y_vel, b_screen_width, b_screen_height);
    Sprite sprite = Sprite(s_x_pos, s_y_pos, s_x_vel, s_y_vel, s_screen_width, s_screen_height);
    return checkForCollision(bullet, sprite);
}
extern "C"
JNIEXPORT jint JNICALL
/**
 * keeps track of the total number of player kills
 * @param env
 * @param thiz
 * @param points
 * @return number of player kills
 */
Java_com_example_a8_1bitinvader_Level_playerKills(JNIEnv *env, jobject thiz, jint points = 1) {
    totalScore+=points;
    return totalScore;
}
