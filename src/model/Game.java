package model;

import util.Observable;
import util.Observer;

import java.util.*;

/**
 * Game class that will have everything we need.
 * Implement observer, so it can "Observe" the cave and everytime a mode is made the game will update
 * and all the rock and diamond that can fall will do.
 * Cave attribute for the array, Player attribute to play, an 2 attribute for the needed and having diamond.
 * State attribute to how the game is going.
 */
public class Game implements Observer {
    private Cave cave = new Cave();
    private Player player = new Player();
    private int neededDiamond = 0;
    private int havingDiamond = 0;
    private State state = State.DOOR_CLOSED;

    /**
     * Setter for the state attribute
     * @param state the new state to replace the old one.
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Getter for the state attribute
     * @return the actual state
     */
    public State getState() {
        return state;
    }

    /**
     * Setter for the number of diamond the player need.
     * Used for the facade when the file is read.
     */
    public void setNeededDiamond(int neededDiamond) {
        this.neededDiamond = neededDiamond;
    }

    /**
     * Getter for the number of diamond the player need to go to the next lvl.
     *
     * @return the number of diamond actually nedded.
     */
    public int getNeededDiamond() {
        return neededDiamond;
    }

    /**
     * Getter for the number of diamond the player have.
     *
     * @return the number of diamond actually having
     */
    public int getHavingDiamond() {
        return havingDiamond;
    }


    public void setHavingDiamond(int num) {
        havingDiamond = num;
    }


    /**
     * Getter for the cave attribute
     *
     * @return the cave
     */
    public Cave getCave() {
        return cave;
    }


    /**
     * Set the actual cave of the game with a given 2D array, used when the player call an undo or redo
     *
     * @param cave the cave that will replace the actual game
     */
    public void setCave(Object[][] cave) {
        for (int i = 0; i < this.cave.getTab().length; i++) {
            for (int j = 0; j < this.cave.getTab()[i].length; j++) {
                this.cave.setTab(i, j, cave[i][j]);
            }
        }
    }

    /**
     * Method to move a player, the player is set the oldpos of the player is saved
     * and the method check if the player can go where he want, if so then the move is made the oldpos
     * in the cave is a void and the player is saved, also at each move the stack of undo is called
     *
     * @param move the string direction (left, up...)
     */
    public void movePlayer(String move) {
        setPlayer();
        Position oldPos = cave.getPlayerPos();
        Object beside = player.ObjectAtPos(move, cave);
        int x = cave.getPlayerPos().getX();
        int y = cave.getPlayerPos().getY();
        if (state == State.DOOR_OPEN && beside instanceof Door) state = State.DOOR_PASSED;
        if (beside instanceof Diamond || beside instanceof Emerald)  havingDiamond++;
        if (canMove(beside)) {
            cave.stack();
            switch (move.toUpperCase()) {
                case "D":
                    player.setXY(x, y + 1);
                    break;
                case "Q":
                    player.setXY(x, y - 1);
                    break;
                case "Z":
                    player.setXY(x - 1, y);
                    break;
                case "S":
                    player.setXY(x + 1, y);
                    break;
            }
            if (havingDiamond >= neededDiamond) state = State.DOOR_OPEN;
            isLvlWon(beside);
            if (oldPos != null && cave.getPlayerPos() != null) {
                cave.setTab(oldPos.getX(), oldPos.getY(), new Void());
                cave.setTab(player.getX(), player.getY(), player);

            }
        }

        cave.notifyObservers();
        setPlayer();

    }

    /**
     * Check if the object where the player want to go is Object he can walk on
     *
     * @param beside the object where the player want to go
     * @return if the player can go on the object
     */
    public boolean canMove(Object beside) {
        return (beside == null || beside instanceof Ground
                || beside instanceof Diamond
                || beside instanceof Void
                || beside instanceof  Emerald
                || (beside instanceof Door && state == State.DOOR_OPEN));
    }

    /**
     * Method to set the XY player attribute after a move have been made
     */
    public void setPlayer() {
        for (int i = 0; i < cave.getTab().length; i++) {
            for (int j = 0; j < cave.getTab()[i].length; j++) {
                if (cave.getTab()[i][j] instanceof Player) player.setXY(i, j);
            }
        }
    }

    /**
     * Method to check if the level is won to update the State of the game
     *
     * @param beside the object where the player want ot go
     */
    public void isLvlWon(Object beside) {
        if (beside instanceof Door && state == State.DOOR_OPEN) {
            state = State.DOOR_PASSED;
        }
    }

    @Override
    public void update(Cave c) {
        if (cave.updateFallIsGameOver()) state = State.GAME_OVER;
    }
}
