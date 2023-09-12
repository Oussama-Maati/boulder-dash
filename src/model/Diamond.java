package model;

/**
 * Represent a Diamond in the game
 * With a String attribute to print in the console
 */
public class Diamond {
    private String d;

    /**
     * Constructor for the Diamond
     */
    public Diamond() {
        d = "◇";
    }

    /**
     * Override method used to print the String of the Diamond for the console game
     *
     * @return the String attribute
     */
    @Override
    public String toString() {
        return d;
    }
}
