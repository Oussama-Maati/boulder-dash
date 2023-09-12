package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * Class FileRead used to "convert" the level wanted to a string
 * then the string will be set in the actual cave of the game.
 */
public class FileRead {

    /**
     * Method to convert a file to a string that will be returned
     * @param lvl the number of the lvl wanted
     * @return the file as a string
     */
    public String lvlToString(int lvl) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("LVL\\lvl" + lvl + ".txt"));
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fail";
    }

    /**
     * Method that will "translate" the string of the file into the 2D array of a cave
     * @param cave the cave where the lvl will be set.
     * @param file the string where are all the characters.
     */
    public void stringToCave(Cave cave, String file) {
        String[] fileToStringArray = file.split("\\n");
        String[][] to2DArray = new String[fileToStringArray.length][];
        int r = 0;
        for (String row : fileToStringArray) {
            to2DArray[r++] = row.split("\s+");
        }
        cave.setTab(to2DArray.length,to2DArray[0].length);
        for (int i = 0; i < to2DArray.length; i++) {
            for (int j = 0; j < to2DArray[i].length; j++) {
                if (to2DArray[i][j].equals("W")) {
                    cave.setTab(i, j, new Wall());
                } else if (to2DArray[i][j].equals("G")) {
                    cave.setTab(i, j, new Ground());
                } else if (to2DArray[i][j].equals("R")) {
                    cave.setTab(i, j, new Rock());
                } else if (to2DArray[i][j].equals("D")) {
                    cave.setTab(i, j, new Diamond());
                } else if (to2DArray[i][j].equals("P")) {
                    Player p = new Player();
                    p.setXY(i,j);
                    cave.setTab(i, j, p);
                } else if (to2DArray[i][j].equals("O")) {
                    cave.setTab(i, j, new Door());
                } else if (to2DArray[i][j].equals("E")) {
                    cave.setTab(i,j,new Emerald());
                }
            }
        }
    }
}
