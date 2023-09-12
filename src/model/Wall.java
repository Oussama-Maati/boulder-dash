package model;

/**
 * Class wall that represent a wall
 */
public class Wall {

    private String w;

    /**
     * Constructor for the wall object
     */
    public Wall() {
        w = "█";
    }

    /**
     * Method toString override to use for the console game and display the wall as a wall with the String
     *
     * @return the String attribute
     */
    @Override
    public String toString() {
        return w;
    }
}
