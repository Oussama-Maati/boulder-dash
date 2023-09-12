package model;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * Facade used to simplify the use of every method of the Game and Cave class
 */
public class Facade {

    private FileRead convertor = new FileRead();

    /**
     * Method to select the level of the game
     * @param lvl the lvl for the file
     * @param game the game that the lvl will be put inside
     */
    public void selectLvl(int lvl, Game game) {
        System.out.println(lvl);
        if (Integer.valueOf(lvl) == 1) game.setNeededDiamond(10);
        if (Integer.valueOf(lvl) == 2) game.setNeededDiamond(15);
        if (Integer.valueOf(lvl) == 3) game.setNeededDiamond(20);
        String sLvl = convertor.lvlToString(lvl);
        convertor.stringToCave(game.getCave(), sLvl);
        game.setPlayer();
        game.getCave().stack();
    }

    /**
     * Method to print the cave in the console
     * @param game the game to print its actual cave
     */
    public void print(Game game) {
        //game.getCave().print();
        game.getCave().printRight();
    }

    /**
     * Method to move the player
     * @param move move the player
     * @param game the game where the player will move
     */
    public void move(String move, Game game) {
        game.movePlayer(move);
    }

    /**
     * Method to check is the game is over
     * @param game the game where it will be checked
     * @return if the game is over
     */
    public boolean isGameOver(Game game) {
        return (game.getState() == State.GAME_OVER);
    }

    /**
     * Method to check if the actual have been succeeded ed by the player
     * @param game the game where it will be checked.
     * @return if the cave have been accomplished.
     */
    public boolean isCaveFinished(Game game) {
        return game.getState() == State.DOOR_PASSED;
    }

    /**
     * Method to undo the cave of the game, and it will be replaced
     * @param game the game to undo
     */
    public void undo(Game game) {
        game.setPlayer();
        Object[][] a = game.getCave().getGamesUndo().peek();
        game.setCave(a);
        game.setPlayer();
        game.getCave().getGamesRedo().push(game.getCave().getGamesUndo().peek());
        game.getCave().getGamesUndo().pop();

    }

    /**
     * Method to redo the cave of the game, and it will be replaced
     * @param game the game to redo
     */
    public void redo(Game game) {
        game.setPlayer();
        Object[][] a = game.getCave().getGamesRedo().peek();
        game.setCave(a);
        game.setPlayer();
        game.getCave().getGamesUndo().push(game.getCave().getGamesRedo().peek());
        game.getCave().getGamesRedo().pop();


    }

    /**
     * Method to abandon the game, the state of the game will just be at gameOver
     * @param game the game to abandon
     */
    public void abandon(Game game) {
        game.setState(State.GAME_OVER);
    }

}
