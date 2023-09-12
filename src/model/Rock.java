package model;

/**
 * Class Rock that represent a rock in the game
 * char c represent the character of the rock
 */
public class Rock extends Object {
    private String r;

    /**
     * Constructor for Rock
     */
    public Rock() {
        r = "■";
    }

    /**
     * Method to print the String of the object rock used in the console game
     *
     * @return the String attribute
     */
    @Override
    public String toString() {
        return r;
    }


}
