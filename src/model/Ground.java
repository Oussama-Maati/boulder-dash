package model;

/**
 * Class Ground represent an earth/ground in a cave
 * With a String to show in the console
 */
public class Ground {
    private String ground;

    /**
     * Constructor for the ground object
     */
    public Ground() {
        ground = "░";
    }

    /**
     * Override method to print the String in the board
     *
     * @return the string attribute
     */
    @Override
    public String toString() {
        return ground;
    }
}
