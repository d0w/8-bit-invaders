package com.example.a8_bitinvader;

import android.content.Context;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;


/**
 * Class for writing the score into the cache
 */
public class ScoreFileWriter implements Writer{
    private File scoreLog;
    public static String name;

    /**
     * Creates ScoreFileWriter object and also creates a files named scoreLog.txt if possible
     */
    public ScoreFileWriter(Context context) {

        scoreLog = new File(context.getFilesDir(), "scoreLog8BitInvador.txt");
        try {
            scoreLog.createNewFile();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * function that takes current entriel of the file, if any and then adds on the new one
     * Code also makes sure added entry in in order of list
     * @param points point value from end game
     */
    public void writePtsToFile(int points) {
        /**Creates Treemap to relate relation between points and the player*/
        TreeMap<Integer, ArrayList<String>> sorted = new TreeMap<Integer, ArrayList<String>>(Collections.reverseOrder());
        String[] fileLines = readFromFile();

        /**adds all the current enteries in the file to the treemap*/
        for(String line : fileLines) {
            int space = line.indexOf(" ");
            int pts = Integer.valueOf(line.substring(0, space));
            String nm = line.substring(space + 1);

            if(!sorted.containsKey(pts)) {
                sorted.put(pts, new ArrayList<String>());
            }
            sorted.get(pts).add(nm);
        }

        /**puts the new points value to be added and puts in the correct position*/
        if(!sorted.containsKey(points)) {
            sorted.put(points, new ArrayList<String>());
        }
        /**adds the name to the points value just added*/
        sorted.get(points).add(name);

        /**Takes the treemap and turns it all to a string formatted in a way that we store it in the file*/
        String sortedStr = "";
        for(Map.Entry<Integer, ArrayList<String>> entry : sorted.entrySet()) {
            for(String nameWPts : entry.getValue()) {
                sortedStr += entry.getKey() + " " + nameWPts + "\n";
            }
        }

        /**writes the values to the file opened above*/
        writeToFile(sortedStr);
    }

    /**
     * Function to get the top 10 scores stored on the file
     * @return Array of strings that each index has a value of Top 10 scores and player name that holds the score
     */
    public String[] getTopScores() {
        String[] out = new String[10];
        try {
            Scanner scan = new Scanner(scoreLog);

            for(int i = 0; i < 10; i++) {
                if(!scan.hasNextLine())
                    break;
                out[i] = scan.nextLine();
            }

            scan.close();
        }  catch(IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    /**
     * Writes code to the file
     * @param str str of what is to be written on the file
     */
    @Override
    public void writeToFile(String str) {
        try {
            FileWriter fw = new FileWriter(scoreLog, false);
            fw.write(str);
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads code from the file
     * @return Array of Strings that each entry is a player and their highscore
     */
    @Override
    public String[] readFromFile() {
        ArrayList<String> fileContents = new ArrayList<String>();
        try {
            /** scans each value in the file */
            Scanner scan = new Scanner(scoreLog);
            while(scan.hasNextLine()) {
                fileContents.add(scan.nextLine());
            }
            scan.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        /** returns arraylist of scores */
        return Arrays.copyOf(fileContents.toArray(), fileContents.size(), String[].class);
    }
}
