package model;

public class Emerald {

    private String e;

    /**
     * Constructor for the wall object
     */
    public Emerald() {
        e = "E";
    }

    /**
     * Method toString override to use for the console game and display the wall as a wall with the String
     *
     * @return the String attribute
     */
    @Override
    public String toString() {
        return e;
    }
}
