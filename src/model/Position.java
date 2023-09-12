package model;

import java.util.Objects;

/**
 * Represent a position will be used mostly for the player
 * with an int x attribute for the x position
 * with an int y attribute for the y position
 */
public class Position {
    private int x;
    private int y;

    /**
     * Constructor of the position
     *
     * @param x the x position in the game tab
     * @param y the y position in the game tab
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the x
     *
     * @return the int x
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y
     *
     * @return the int y
     */
    public int getY() {
        return y;
    }


    /**
     * Override equal used for the test Junit
     *
     * @param o the other position
     * @return is equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    /**
     * Override method used for the Junit test
     *
     * @return an hashcode
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
