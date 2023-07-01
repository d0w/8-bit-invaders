# Group5Project


# Title
8-bit Invaders


# Category:
Animated Game


# TLDR:
We built a Space Shooter-like game in an 8-bit style.


# Description:
8-bit Indavers is a space shooter game in which you shoot down as many enemies as possible while trying to not let any of them get away. This game has 3 difficulties corresponding to the amount of lives that you have: 1 life, 3 lives, and 5 lives. Your score is calculated by the total number of kills divided by the number of initial lives (type casted to an integer). There are multiple kinds of players and multiple kinds of enemies, all with different graphics. A local leaderboard keeps track of highest scoring runs. Note that if your player collides with an enemy, you lose all your lives. If an enemy passes the bottom of the screen, you lose one life.


# Roles
Lead: AnishSinha [14.29%], KrishShah [14.29%], NathanStrahs [14.29%], JamesKnee [14.29%], ThomasPoimenidis [14.29%], DerekXu [14.29%], JiaweiXiang [14.29%]


Front: AnishSinha [14.29%], KrishShah [14.29%], NathanStrahs [14.29%], JamesKnee [14.29%], ThomasPoimenidis [14.29%], DerekXu [14.29%], JiaweiXiang [14.29%]


Back: AnishSinha [14.29%], KrishShah [14.29%], NathanStrahs [14.29%], JamesKnee [14.29%], ThomasPoimenidis [14.29%], DerekXu [14.29%], JiaweiXiang [14.29%]


Documenter: AnishSinha [14.29%], KrishShah [14.29%], NathanStrahs [14.29%], JamesKnee [14.29%], ThomasPoimenidis [14.29%], DerekXu [14.29%], JiaweiXiang [14.29%]


Tester: AnishSinha [14.29%], KrishShah [14.29%], NathanStrahs [14.29%], JamesKnee [14.29%], ThomasPoimenidis [14.29%], DerekXu [14.29%], JiaweiXiang [14.29%]


# Member Names:
Anish Sinha, Thomas Poimenidis, Jiawei Xiang(DavidXiang), Krish Shah, Nathan Strahs, Derek Xu, James Knee


# Minimum Requirements:
Moving Graphical Pieces [100%]

The ability for the user to control some of the moving pieces through input [100%]

Including a score that is changed based on the user satisfying requirements [100%]

Simple to use graphical interface [100%]

Provide a clear documentation of the rules of the game [100%]

Not require any Internet or network connectivity [100%]

Not violate the copyrights of any games [100%]


# Possible Features:
Allow the user to interact with the game by moving the phone (i) 10% (ii) We did not do this at all **0%** (iii) did not include

Provide a high-score list that persists when the app is closed and reopened (i) 10% (ii) we did **100%** of this

Allow the user to tweak the rules of the of the game being played (i) 10% (ii) We did this **100%** through the use of picking different characters, and choosing the amount of lives you start with

Add sound effects for specific activities in the game (i) 10% (ii) We did **100%** of this by adding sound effects for shooting down an enemy, and other music in the game

Allow the user to adapt all significant aspects of the GUI (i) 10% (ii) We did not do this at all **0%** (iii) we did not do this

Do all calculations in C++ and connect them through Android’s NDK (i) 20% (ii) We did this completely **100%** by calculating collisions in C++, which was the only major calculation that we needed. Very minor calculations were not placed in C++ because it was unrealistic and unnecessary to do so. 
 
# Execution:

# Project source:

Repository: https://agile.bu.edu/gitlab/ec327_projects/group5project.git

We did not use any external libraries, and our app can be compiled from scratch.

# INSTALL INSTRUCTIONS:
In order to run 8-Bit Invaders, you must first have Android Studio installed [[install](https://developer.android.com/studio)] on your device.
After downloading, you are ready to create a new project in Android Studio [Do not open the repo for 8-Bit Invaders – you will need to download more software before opening the 8-Bit Invaders project code]. To create a new project, open android studio, create a new project. From templates choose Phone and Tablets -> No Activity -> next. Then pick a name (does not matter because it is temporary), choose java as a language, and then press finish.


After you finish creating the project, please download an Android Emulator [[install](https://developer.android.com/studio/run/emulator)].


In order to run our project you will also need to download Android NDK and CMake. To do this please follow these instructions [[install](https://developer.android.com/studio/projects/install-ndk)].


After installing these components, you will need to clone the project repository to your local device. To do this, please use your computer’s terminal and use the following instructions:


1. Make sure you have git installed on your computer [[install](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)]
2. Then create a directory [through your file explorer or terminal -- NOT IN ANDROID STUDIO] in which you would like to clone the project, and subsequently navigate to that directory in your terminal.
3. Then you will need to clone this repository. To do this use the command:


`git clone https://agile.bu.edu/gitlab/ec327_projects/group5project.git`


NOTE: The https link is the HTTPS link when you navigate to the project repo on Gitlab, and use the blue drop down on the right hand side of the screen [the link provided may be out of date upon your cloning].


After downloading all of the above, you are ready to open the 8-Bit Invaders code. To do this navigate to File->Open. Then find the directory in which you cloned the project, and open the project. You may need to wait a few minutes for the project to configure everything. You will then be ready to run the project.

# Usage:
The app is very intuitive, but to be clear here are some instructions to use it. If you click on the name field, you can input your name which then saves it to the leaderboard depending on how you did. To get to the leaderboard, click the icon that looks like a leaderboard in the bottom right of the main screen. To see further instructions, locate the ‘help’ button in the top left corner of the home screen. 


# Miscellaneous:


# Extra features:
We drew all of the art ourselves through pixilart.com, and manually uploaded it to our app in the drawable file. 
We also implemented a joystick feature that is not the normal form of input. It was much harder than using typical left and right buttons.


# Challenges:
One of the challenges we faced was with the leaderboard. Instead of using the generic SharedPreferences Object in Android Studio, we decided to write to a file that is stored in the internal storage of the phone. This made displaying the leaderboard data on the game over activity screen and the pop up leaderboard on the main page more difficult.

Another challenge that we faced was creating resizable buttons that looked good and worked well on different types of emulators. This was very challenging because it involved a lot of pixel manipulation and fractions that became very complicated when trying to make everything compatible on most screen resolutions. 


# Supporting Materials:

[Link to video](https://www.dropbox.com/s/4n9mrwet8zpj1wx/GamePlayVideo1.webm?dl=0)


# Release:
We are all comfortable with this becoming public and uploading it to the play store. 
