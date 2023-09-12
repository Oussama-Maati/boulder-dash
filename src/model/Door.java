package model;

/**
 * Represent a door in the cave
 * With a String attribute for the console game
 */
public class Door {
    private String d;

    /**
     * Constructor for the Door
     */
    public Door() {
        d = "═";
    }

    /**
     * Override method to print the String of the Door in the game console
     *
     * @return the String attribute
     */
    @Override
    public String toString() {
        return d;
    }
}
