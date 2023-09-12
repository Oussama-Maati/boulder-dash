package model;

/**
 * Represent a player in the cave
 * String p attribute to print it in the console
 * Int x attribute for the position
 * Int y attribute for the position
 */
public class Player {
    private String p;
    private int x;
    private int y;

    /**
     * Constructor for the Player
     */
    public Player() {
        p = "P";
    }

    /**
     * Setter for the x and y attribute of the player.
     *
     * @param x the new int that will replace the current x
     * @param y the new int that will replace the current y
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for x
     *
     * @return the int x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for y
     *
     * @return the int y
     */
    public int getY() {
        return y;
    }

    /**
     * Method to get the object at the given pos
     *
     * @param side the direction
     * @param cave the cave where it happens
     * @return the Object at the given direction from the player in a cave
     */
    public Object ObjectAtPos(String side, Cave cave) {
        switch (side.toUpperCase()) {
            case "D":
                return cave.getTab()[x][y + 1];
            case "Q":
                return cave.getTab()[x][y - 1];
            case "Z":
                return cave.getTab()[x - 1][y];
            case "S":
                return cave.getTab()[x + 1][y];
            default:
                return null;
        }
    }

    /**
     * Override method to print the String of the player in the console
     *
     * @return the string attribute
     */
    @Override
    public String toString() {
        return p;
    }
}


