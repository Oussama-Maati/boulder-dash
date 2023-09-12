package model;

/**
 * Represent an empty block that the player as already walekd on
 */
public class Void {
    private String v;

    /**
     * Constructor for the Door
     */
    public Void() {
        v = " ";
    }

    /**
     * Override method to print the String Void in the game console
     *
     * @return the String attribute
     */
    @Override
    public String toString() {
        return v;
    }
}
