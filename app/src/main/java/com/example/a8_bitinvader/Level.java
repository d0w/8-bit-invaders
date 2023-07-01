package com.example.a8_bitinvader;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.graphics.Paint;             // necessary when drawing Bitmaps with Canvas objects
import java.util.ArrayList;
import java.util.Random;

/**
 * The actual level which includes gameplay with player and enemies
 */
public class Level extends SurfaceView implements Runnable {
    private Thread thread;
    private ScoreFileWriter scoreFW = new ScoreFileWriter(getContext());
    private Context context;
    public boolean isRunning;
    public int screenWidth, screenHeight;
    private float screenRatioWidth, screenRatioHeight;
    private Background background1, background2;
    private Paint paint;
    public int inputLives;
    public int minEnemyVelocity = 6;
    public int maxEnemyVelocity = 11;
    private MediaPlayer explosionSound;
    Boolean limiter = false;

    /** The {@link Joystick} */
    Joystick joystick;

    /** The {@link Player} */
    Player player;

    /** The {@link Enemy} */
    ArrayList<Enemy> enemyArrayList;
    int bulletCycleDelay = 0;

    /**
     * Create a {@link Level} scaled based on the device size which includes the background, player, joystick, and enemies
     * @param context      Context of the device
     * @param screenWidth  Screen width in pixels
     * @param screenHeight Screen height in pixels
     */
    public Level(Context context, int screenWidth, int screenHeight, int inputLives) {
        super(context);
        this.context = context;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.inputLives = inputLives;

        screenRatioWidth = 1080f / screenWidth;
        screenRatioHeight = 1920f / screenHeight;

        background1 = new Background(screenWidth, screenHeight, getResources());
        background2 = new Background(screenWidth, screenHeight, getResources());
        explosionSound = MediaPlayer.create(context, R.raw.explosion);

        background2.yPos = -screenHeight;

        paint = new Paint();

        /** Create Joystick and Player Objects */
        joystick = new Joystick(screenWidth / 2, screenHeight * 9 / 10, 130, 40);
        player = new Player(screenWidth / 2, screenHeight * 7 / 10, 0, 0, screenWidth, screenHeight, getResources());
        player.playerLives = inputLives;
    }

    /**
     * Runs the app by continuously calling private methods {@link Void update()} and {@link Void draw()} based on whether the app {@link Boolean isRunning}
     */
    @Override
    public void run() {
        while (isRunning) {
            update();
            draw();

            /** creates 30 millisecond delay in between draw() and update() */
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Updates all animated parts necessary for GameActivity
     */
    private void update() {
        backgroundUpdate();

        System.loadLibrary("native-lib");

        /**
         * Updates player position based on Joystick input
         */
        player.update((int) joystick.XVelocity, (int) joystick.YVelocity);
        joystick.update(joystick);

        for (Enemy enemy : enemyArrayList) {
            enemy.update();

            /**
             * If player collides with enemy, game over
             */
            int playerMiddleX = player.xPos + (player.spriteWidth / 2);
            int playerMiddleY = player.yPos + (player.spriteHeight / 2);
            if(playerMiddleX > enemy.xPos &&
                    playerMiddleX < enemy.xPos + enemy.spriteWidth &&
                    playerMiddleY > enemy.yPos &&
                    playerMiddleY < enemy.yPos + enemy.spriteHeight){
                explosionSound.start();
                player.playerLives = 0;
            }

            /**
             * Once an enemy exits the screen, the player loses a life,
             * and a new enemy is drawn from the top
             * Once player has 0 lives, game over
             */
            if (enemy.yPos > screenHeight) {
                player.playerLives--;

                Random rand = new Random();
                Enemy replacement = new Enemy(rand.nextInt(3 * screenWidth / 4), 0, 0, minEnemyVelocity + rand.nextInt(maxEnemyVelocity), screenWidth, screenHeight, getResources());
                enemyArrayList.set(enemyArrayList.indexOf(enemy), replacement);
            }

            /** for when the player died and game ends*/
            if (player.playerLives <= 0 && !limiter) {
                /** switches to the MainActivity page */
                Intent intent = new Intent(getContext(), GameOverActivity.class);
                int calcScore = player.playerKills/inputLives;
                /** Sends Player's Score to GameOverActivity and Records it to Scores file */
                scoreFW.writePtsToFile(calcScore);
                String score = Integer.toString(calcScore);
                intent.putExtra("score", score);
                intent.putExtra("inputLives", inputLives);
                context.startActivity(intent);
                limiter = true;
            }
        }
        boolean ndkCollision;

        /**
         * Checks for collision between player bullets and enemies
         */

        for (int ii = 0; ii < player.bulletList.size(); ii++) {
            if (player.bulletList.get(ii).isOffScreen()) {
                player.bulletList.remove(player.bulletList.get(ii));
                ii -= 1;
            } else {
                player.bulletList.get(ii).update();
                for (int jj = 0; jj < enemyArrayList.size(); jj++) {
                    ndkCollision = false;
                    if (ii > -1) {
                        ndkCollision = checkForCollision(player.bulletList.get(ii).xPos, player.bulletList.get(ii).yPos, player.bulletList.get(ii).xVel, player.bulletList.get(ii).yVel, player.bulletList.get(ii).screenWidth, player.bulletList.get(ii).screenHeight, enemyArrayList.get(jj).xPos, enemyArrayList.get(jj).yPos, enemyArrayList.get(jj).xVel, enemyArrayList.get(jj).yVel, enemyArrayList.get(jj).screenWidth, enemyArrayList.get(jj).screenHeight);
                    }

                    /** old condition implemented with new NDK collision
                     *  player.bulletList.get(ii).checkForCollision(enemyArrayList.get(jj))
                     */

                    if (ii > -1 && ndkCollision) {
                        player.bulletList.remove(player.bulletList.get(ii));

                        /** Replace dead enemy with new enemy */
                        Random rand = new Random();
                        Enemy replacement = new Enemy(rand.nextInt(3 * screenWidth / 4), 0, 0, minEnemyVelocity + rand.nextInt(maxEnemyVelocity), screenWidth, screenHeight, getResources());
                        enemyArrayList.set(jj, replacement);
                        ii -= 1;

                        /** Add to player's kill count */
                        player.playerKills++;
                        playerKills(1);
                        explosionSound.start();
                    }
                }
            }
        }
    }

    /**
     * Updates the position of the backgrounds to give the illusion of an infinitely moving background.
     * Cycles the background within a treadmill fashion, placing one of the
     * two backgrounds behind each other while slowly moving both upward.
     */
    void backgroundUpdate() {
        background1.yPos += (int) (5 * screenRatioHeight);
        background2.yPos += (int) (5 * screenRatioHeight);

        if (background1.yPos > screenHeight) {
            background1.yPos = -screenHeight;
        }

        if (background2.yPos > screenHeight) {
            background2.yPos = -screenHeight;
        }
    }

    /**
     * Initializes and draws the backgrounds at a given x position and y position
     */
    private void draw() {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();

            /** draw background */
            canvas.drawBitmap(background1.background, background1.xPos, background1.yPos, paint);
            canvas.drawBitmap(background2.background, background2.xPos, background2.yPos, paint);

            /** draw player and enemies */
            canvas.drawBitmap(player.spriteImage, player.xPos, player.yPos, paint);
            for (Enemy enemy : enemyArrayList) {
                canvas.drawBitmap(enemy.spriteImage, enemy.xPos, enemy.yPos, paint);
            }

            /** draw bullets */
            for (Bullet bullet : player.bulletList) {
                canvas.drawBitmap(bullet.spriteImage, bullet.xPos, bullet.yPos, paint);
            }
            joystick.draw(canvas);

            /**
             * Only fires a new bullet every 20 loops of run()
             * 20 * 30ms delay = 0.6  seconds
             */

            bulletCycleDelay++;
            if (bulletCycleDelay == 20) {
                bulletCycleDelay = 0;
                player.shootNewBullet();
            }

            /** draw player lives */
            Bitmap heartImage = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
            heartImage = Bitmap.createScaledBitmap(heartImage, 128, 128, false);
            for (int i = 0; i < player.playerLives; i++) {
                canvas.drawBitmap(heartImage, 50 + i * 128, 100, paint);
            }

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    /**
     * https://developer.android.com/reference/java/lang/Thread#start()
     * initializes a thread and starts it, automatically calling the overridden run() method above.
     */
    public void resume() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * https://developer.android.com/reference/java/lang/Thread#join()
     * waits for thread to die if game is paused
     */
    public void pause() {
        try {
            isRunning = false;
            thread.join();
            explosionSound.release();
            explosionSound = null;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Keeps track of user touch events to trigger joystick movement and thus trigger player movement.
     * This function calls the {@link Joystick} object class to change player movement as the user must touch
     * the joystick specifically for movement.
     *
     * @param event The {@link MotionEvent} that the user triggers
     * @return True if there is an action
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (joystick.isPressed((double) event.getX(), (double) event.getY())) {
                    joystick.setIsPressed(true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joystick.getIsPressed()) {
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
                return true;
        }
        return super.onTouchEvent(event);
    }

    /**
     * Calculates whether a bullet has collided with an enemy sprite, and it passes in all the attributes
     * of the sprite and bullet to allow the calculations to be done in c++
     * @param b_xPos
     * @param b_yPos
     * @param b_xVel
     * @param b_yVel
     * @param b_screenWidth
     * @param b_screenHeight
     * @param s_xPos
     * @param s_yPos
     * @param s_xVel
     * @param s_yVel
     * @param s_screenWidth
     * @param s_screenHeight
     * @return a boolean to tell whether the collision has happened
     */
    public native boolean checkForCollision(int b_xPos, int b_yPos, int b_xVel, int b_yVel, int b_screenWidth, int b_screenHeight, int s_xPos, int s_yPos, int s_xVel, int s_yVel, int s_screenWidth, int s_screenHeight);

    /**
     * Function to keep track of the players kills in c++
     * @param points represents the number of points the enemy counts as (1 per enemy)
     * @return the total number of points
     */
    public native int playerKills(int points);
}
