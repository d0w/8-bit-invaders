package com.example.a8_bitinvader;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import android.content.Context;
import android.media.AudioManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.media.MediaPlayer;
import android.content.Context;
import android.app.Dialog;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.SpannableStringBuilder;
import android.text.style.BulletSpan;
import android.text.Spanned;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.Gravity;
import android.graphics.Color;

public class MainActivity extends AppCompatActivity {
    Animation animationLiveOne;
    Animation animationLiveThree;
    Animation animationLiveFive;
    private View blinkingButton;
    int currentCharacter;
    int inputLives = 3;
    private MediaPlayer themeSound;
    private CharacterPagerAdapter characterPagerAdapter;
    private ViewPager characterViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** initializes information about NDK */
        System.loadLibrary("native-lib");

        setContentView(R.layout.activity_main);

        /** Initialize the CharacterPagerAdapter */
        characterPagerAdapter = new CharacterPagerAdapter();
        characterViewPager = findViewById(R.id.characterViewPager);
        characterViewPager.setAdapter(characterPagerAdapter);

        /** Playing Background Music
         *  Music Download Link: https://hypeddit.com/track/njf8op
         */

        themeSound = MediaPlayer.create(this, R.raw.homeaudio);
        themeSound.start();

        /** the if statement below hides the action bar
         *  https://www.geeksforgeeks.org/different-ways-to-hide-action-bar-in-android-with-examples/
         */

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        /** Initializes 8-bit Invaders Logo */
        ImageView logo = (ImageView) findViewById(R.id.image_logo);
        logo.setImageResource(R.drawable.invaderslogo);

        /** Initializes Box to enter name */
        EditText nameBox = findViewById(R.id.name_box);

        Context context = this;

        /** Creates new Intent and starts GameActivity if 'play' is pressed on MainActivity screen. */
        findViewById(R.id.play_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /** set name to whatever the user input into the text box*/
                String name = nameBox.getText().toString();

                /** check to see if the name entered and if it is prompt the user to enter a name and
                 * will not allow them to continue till they enter a name
                 */
                if (name.isEmpty()) {
                    /** Show an error message indicating that the field is required */
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }

                /**set the name for the current player to name */
                ScoreFileWriter scoreName = new ScoreFileWriter(context);
                scoreName.name = name;

                /** pass number of player lives and player name to gameActivity */
                Intent intentStart = new Intent(MainActivity.this, GameActivity.class);
                intentStart.putExtra("inputLives", inputLives);
                Player.setSelectedCharacterIndex(characterViewPager.getCurrentItem());
                startActivity(intentStart);
            }
        });

        findViewById(R.id.help_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelpDialog();
            }
        });

        /** Creates new Intent and Mutes/UnMutes Device Audio if mute button is pressed on MainActivity screen
          * Reference: https://www.youtube.com/watch?v=_Klq62-me8s&ab_channel=AppleCoders
          * Reference: https://developer.android.com/reference/android/media/MediaPlayer */
        findViewById(R.id.mute_button).setOnClickListener(new View.OnClickListener() {

            /** Flag to track audio state */
            private boolean isAudioMuted = false;

            @Override
            public void onClick(View view) {
                AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

                if (isAudioMuted) {
                    /** Unmute the audio */
                    audioManager.adjustVolume(AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI);
                    isAudioMuted = false;
                } else {
                    /** Mute the audio */
                    audioManager.adjustVolume(AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
                    isAudioMuted = true;
                }
            }
        });

        /** Creates new Intent and stars LeaderboardView if the leaderboard button is pressed on the MainActivity Screen */
        findViewById(R.id.leaderboard_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLeaderboardDialog();
            }
        });

        findViewById(R.id.lives1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Cancel the animation on the previously blinking button (if any) */
                if (blinkingButton != null) {
                    blinkingButton.clearAnimation();
                }

                /** Start the animation on the clicked button */
                blinkingButton = view;
                Animation animationLiveOne = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_animation);
                blinkingButton.startAnimation(animationLiveOne);
                inputLives = 1;
            }
        });

        findViewById(R.id.lives3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Cancel the animation on the previously blinking button (if any) */
                if (blinkingButton != null) {
                    blinkingButton.clearAnimation();
                }

                /** Start the animation on the clicked button */
                blinkingButton = view;
                Animation animationLiveThree = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_animation);
                blinkingButton.startAnimation(animationLiveThree);
                inputLives = 3;
            }
        });

        findViewById(R.id.lives5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /** Cancel the animation on the previously blinking button (if any) */
                if (blinkingButton != null) {
                    blinkingButton.clearAnimation();
                }

                /** Start the animation on the clicked button */
                blinkingButton = view;
                Animation animationLiveFive = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_animation);
                blinkingButton.startAnimation(animationLiveFive);
                inputLives = 5;
            }
        });

        /** Retrieve the reference to the "left_button" ImageView in the layout */
        ImageView leftButton = findViewById(R.id.left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Get the current position of the characterViewPager */
                int currentPosition = characterViewPager.getCurrentItem();
                /** Calculate the maximum position by subtracting 1 from the total number of characters in the characterPagerAdapter */
                int maxPosition = characterPagerAdapter.getCount() - 1;
                /** Check if the current position is greater than 0 */
                if (currentPosition > 0) {
                    /** If so, decrement the currentPosition and set the characterViewPager to the new position */
                    characterViewPager.setCurrentItem(currentPosition - 1);
                }
                else {
                    /** If the current position is 0, set the characterViewPager to the maximum position */
                    characterViewPager.setCurrentItem(maxPosition);
                }
            }
        });

        /** Retrieve the reference to the "right_button" ImageView in the layout */
        ImageView rightButton = findViewById(R.id.right_button);
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /** Get the current position of the characterViewPager */
                int currentPosition = characterViewPager.getCurrentItem();
                /** Calculate the maximum position by subtracting 1 from the total number of characters in the characterPagerAdapter */
                int maxPosition = characterPagerAdapter.getCount() - 1;
                /** Check if the current position is less than the maximum position */
                if (currentPosition < maxPosition) {
                    /** If so, increment the currentPosition and set the characterViewPager to the new position */
                     characterViewPager.setCurrentItem(currentPosition + 1);
                }
                else {
                    /** If the current position is equal to the maximum position, set the characterViewPager to position 0 */
                     characterViewPager.setCurrentItem(0);
                }
            }
        });

    }
    private class CharacterPagerAdapter extends PagerAdapter {
        private int[] characterImages = {R.drawable.player, R.drawable.player2, R.drawable.player3, R.drawable.player4};
        private int selectedCharacterIndex = 0;
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            /** Create a new ImageView to display the character image */
            ImageView characterImageView = new ImageView(MainActivity.this);
            /** Set the image resource of the ImageView based on the position in the characterImages array */
            characterImageView.setImageResource(characterImages[position]);
            /** Set the scale type of the ImageView to CENTER_INSIDE for proper image scaling */
            characterImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            /** Add the ImageView to the container **/
            container.addView(characterImageView);
            /** Return the ImageView as the instantiated item */
            return characterImageView;
        }
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            /** Remove the ImageView from the container when it is no longer needed */
            container.removeView((ImageView) object);
        }
        @Override
        public int getCount() {
            /** Return the total number of characters available */
            return characterImages.length;
        }
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            /** Check if the view belongs to the provided object */
            return view == object;
        }

        /**
         * Add a getter method to retrieve the selected character index
         * This can be used to determine the currently selected character by other classes
         */
        public int getSelectedCharacterIndex() {
            return selectedCharacterIndex;
        }
    }
    
    /**
     * Open up the popup window
     */
    private void showLeaderboardDialog() {

        ScoreFileWriter scoreFw = new ScoreFileWriter(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Leaderboard");
        String[] scores = scoreFw.getTopScores();

        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.popup_table, null);
        builder.setView(dialogLayout);

        TableLayout tableLayout = dialogLayout.findViewById(R.id.tableLayout);

        int i = 1;
        for (String score : scores) {
            if (score != null) {
                TableRow tableRow = new TableRow(this);
                tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                tableRow.setGravity(Gravity.CENTER);

                TextView positionTextView = new TextView(this);
                positionTextView.setText(String.valueOf(i));
                positionTextView.setPadding(10, 0, 10, 0);
                positionTextView.setGravity(Gravity.CENTER);
                positionTextView.setTextColor(Color.BLACK);

                TextView scoreTextView = new TextView(this);
                scoreTextView.setText(score);
                scoreTextView.setGravity(Gravity.CENTER);
                scoreTextView.setTextColor(Color.BLACK);


                tableRow.addView(positionTextView);
                tableRow.addView(scoreTextView);
                tableLayout.addView(tableRow);
            }
            i++;
        }

        /** Add an "OK" button to close the dialog */
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); /** Close the dialog */
            }
        });

        /** Create and show the dialog */
        AlertDialog leaderboardDialog = builder.create();
        leaderboardDialog.show();
    }

    /**
     * Pop Up Window for Help Button
     */
    private void showHelpDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game Instructions");
        String[] instructions = {
                "Choose your character using the left and right arrows on the Main Screen.",
                "Select the number of lives you want. The lower the lives, the higher the score multiplier!",
                "Use the joystick to move your character. Shoot down as many enemies as possible!",
                "Do not crash your spaceship and do not let enemies leave the screen!",
                "HAVE FUN!!"
        };

        StringBuilder messageBuilder = new StringBuilder();
        for (String instruction : instructions) {
            messageBuilder.append("\uD83D\uDE80 ").append(instruction).append("\n");
        }

        builder.setMessage(messageBuilder.toString());

        /** Add an "OK" button to close the dialog */
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); /** Close the dialog */
            }
        });

        /** Create and show the dialog */
        AlertDialog helpDialog = builder.create();
        helpDialog.show();
    }

    /** This function calls the android NDK to use c++ code */

    public native boolean isOffScreen(int yPos, int screenHeight);

    @Override
    protected void onStart() {
        super.onStart();
    }

    /**
     * Resume the game
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Pause the game
     */
    @Override
    protected void onPause() {
        super.onPause();
        themeSound.release();
        themeSound = null;
    }

    /**
     * Stop the game
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * Destroy the sprite
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Restart the game
     */
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        /** Call super.onBackPressed() to perform default back button behavior */
        super.onBackPressed();
    }
}