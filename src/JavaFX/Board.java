package JavaFX;

import javafx.collections.ObservableList;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.*;
import model.Void;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent the board of the game with a game attribute and all the image needed.
 */
public class Board extends GridPane {

    private Game game = new Game();
    Image Wall = new Image(String.valueOf(Path.of("img", "Wall.png")), true);
    Image Ground = new Image(String.valueOf(Path.of("img", "Earth.png")), true);
    Image Player = new Image(String.valueOf(Path.of("img", "Player.png")), true);
    Image Diamond = new Image(String.valueOf(Path.of("img", "Diamond.png")), true);
    Image Rock = new Image(String.valueOf(Path.of("img", "Rock.png")), true);
    Image Empty = new Image(String.valueOf(Path.of("img", "Empty.png")), true);
    Image Door = new Image(String.valueOf(Path.of("img", "Door.png")), true);

    /**
     * Constructor for the board
     * The still brown will be put so that the image won't have a empty border
     */
    public Board() {
        this.setStyle("-fx-background-color: brown");
    }

    /**
     * Getter for the game attribute
     *
     * @return the game of the board
     */
    public Game getGame() {
        return game;
    }

    /**
     * Method to "initialise" the board with all the image needed depending on the cave.
     */
    public void makeGrid() {

        int maxW = 0, maxH = 0, k = 0, l = 0;
        if (game.getCave().getPlayerPos().getX() <= game.getCave().getTab().length / 2 && game.getCave().getPlayerPos().getY() <= game.getCave().getTab()[0].length / 2) {
            maxW = (game.getCave().getTab().length / 2) + 2;
            maxH = (game.getCave().getTab()[0].length / 3) * 2;
        } else if (game.getCave().getPlayerPos().getX() >= game.getCave().getTab().length / 2 && game.getCave().getPlayerPos().getY() <= game.getCave().getTab()[0].length / 2) {
            maxW = game.getCave().getTab().length;
            maxH = (game.getCave().getTab()[0].length / 4) * 3;
            k = game.getCave().getTab().length / 2;
        } else if (game.getCave().getPlayerPos().getX() <= game.getCave().getTab().length / 2 && game.getCave().getPlayerPos().getY() >= game.getCave().getTab()[0].length / 2) {
            maxW = (game.getCave().getTab().length / 2);
            maxH = game.getCave().getTab()[0].length;
            l = maxW;
        } else if (game.getCave().getPlayerPos().getX() >= game.getCave().getTab().length / 2 && game.getCave().getPlayerPos().getY() >= game.getCave().getTab()[0].length / 2) {
            k = game.getCave().getTab().length / 3;
            l = game.getCave().getTab()[0].length / 3;
            maxW = game.getCave().getTab().length;
            maxH = game.getCave().getTab()[0].length;
        } else {
            maxW = game.getCave().getTab().length;
            maxH = game.getCave().getTab()[0].length;

        }

        for (int i = 0; i < game.getCave().getTab().length; i++) {
            for (int j = 0; j < game.getCave().getTab()[i].length; j++) {
                if (game.getCave().getTab()[i][j] instanceof model.Wall) {
                    removeNodeByRowColumnIndex(i, j);
                    Wall.isBackgroundLoading();
                    ImageView imageView = new ImageView(Wall);
                    imageView.setCache(true);
                    imageView.setCacheHint(CacheHint.SPEED);
                    this.add(imageView, j, i);
                } else if (game.getCave().getTab()[i][j] instanceof model.Ground) {
                    removeNodeByRowColumnIndex(i, j);
                    Ground.isBackgroundLoading();
                    ImageView imageView = new ImageView(Ground);
                    imageView.setCache(true);
                    imageView.setCacheHint(CacheHint.SPEED);
                    this.add(imageView, j, i);
                } else if (game.getCave().getTab()[i][j] instanceof model.Player) {
                    removeNodeByRowColumnIndex(i, j);
                    Player.isBackgroundLoading();
                    ImageView imageView = new ImageView(Player);
                    imageView.setCache(true);
                    imageView.setCacheHint(CacheHint.SPEED);
                    this.add(imageView, j, i);
                } else if (game.getCave().getTab()[i][j] instanceof model.Diamond) {
                    removeNodeByRowColumnIndex(i, j);
                    Diamond.isBackgroundLoading();
                    ImageView imageView = new ImageView(Diamond);
                    imageView.setCache(true);
                    imageView.setCacheHint(CacheHint.SPEED);
                    this.add(imageView, j, i);
                } else if (game.getCave().getTab()[i][j] instanceof model.Rock) {
                    removeNodeByRowColumnIndex(i, j);
                    Rock.isBackgroundLoading();
                    ImageView imageView = new ImageView(Rock);
                    imageView.setCache(true);
                    imageView.setCacheHint(CacheHint.SPEED);
                    this.add(imageView, j, i);
                } else if (game.getCave().getTab()[i][j] instanceof Door) {
                    removeNodeByRowColumnIndex(i, j);
                    Door.isBackgroundLoading();
                    ImageView imageView = new ImageView(Door);
                    imageView.setCache(true);
                    imageView.setCacheHint(CacheHint.SPEED);
                    this.add(imageView, j, i);
                } else if (game.getCave().getTab()[i][j] instanceof Emerald) {
                    removeNodeByRowColumnIndex(i, j);
                    Diamond.isBackgroundLoading();
                    ImageView imageView = new ImageView(Diamond);
                    imageView.setCache(true);
                    imageView.setCacheHint(CacheHint.SPEED);
                    this.add(imageView, j, i);
                } else {
                    removeNodeByRowColumnIndex(i, j);
                    Empty.isBackgroundLoading();
                    ImageView imageView = new ImageView(Empty);
                    imageView.setCache(true);
                    imageView.setCacheHint(CacheHint.SPEED);
                    this.add(imageView, j, i);
                }

            }
        }
    }

    /**
     * Method to update the board of the game,
     * it will use "whatToChange" method to get all the position to change
     * Then all imageView will be deleted and all the right image will be put in place
     */
    public void updateGrid() {
        List<Position> willChange = whatToChange();
        for (Position pos : willChange) {
            if (game.getCave().getTab()[pos.getX()][pos.getY()] instanceof model.Wall) {
                removeNodeByRowColumnIndex(pos.getX(), pos.getY());
                Wall.isBackgroundLoading();
                ImageView imageView = new ImageView(Wall);
                imageView.setCache(true);
                imageView.setCacheHint(CacheHint.SPEED);
                this.add(imageView, pos.getY(), pos.getX());
            } else if (game.getCave().getTab()[pos.getX()][pos.getY()] instanceof model.Player) {
                removeNodeByRowColumnIndex(pos.getX(), pos.getY());
                Player.isBackgroundLoading();
                ImageView imageView = new ImageView(Player);
                imageView.setCache(true);
                imageView.setCacheHint(CacheHint.SPEED);
                this.add(imageView, pos.getY(), pos.getX());
            } else if (game.getCave().getTab()[pos.getX()][pos.getY()] instanceof model.Diamond) {
                removeNodeByRowColumnIndex(pos.getX(), pos.getY());
                Diamond.isBackgroundLoading();
                ImageView imageView = new ImageView(Diamond);
                imageView.setCache(true);
                imageView.setCacheHint(CacheHint.SPEED);
                this.add(imageView, pos.getY(), pos.getX());
            } else if (game.getCave().getTab()[pos.getX()][pos.getY()] instanceof model.Rock) {
                removeNodeByRowColumnIndex(pos.getX(), pos.getY());
                Rock.isBackgroundLoading();
                ImageView imageView = new ImageView(Rock);
                imageView.setCache(true);
                imageView.setCacheHint(CacheHint.SPEED);
                this.add(imageView, pos.getY(), pos.getX());
            } else if (game.getCave().getTab()[pos.getX()][pos.getY()] instanceof Door) {
                removeNodeByRowColumnIndex(pos.getX(), pos.getY());
                Door.isBackgroundLoading();
                ImageView imageView = new ImageView(Door);
                imageView.setCache(true);
                imageView.setCacheHint(CacheHint.SPEED);
                this.add(imageView, pos.getY(), pos.getX());
            } else if (game.getCave().getTab()[pos.getX()][pos.getY()] instanceof Ground) {
                removeNodeByRowColumnIndex(pos.getX(), pos.getY());
                Ground.isBackgroundLoading();
                ImageView imageView = new ImageView(Ground);
                imageView.setCache(true);
                imageView.setCacheHint(CacheHint.SPEED);
                this.add(imageView, pos.getY(), pos.getX());
            } else if (game.getCave().getTab()[pos.getX()][pos.getY()] instanceof Emerald) {
                removeNodeByRowColumnIndex(pos.getX(), pos.getY());
                Diamond.isBackgroundLoading();
                ImageView imageView = new ImageView(Diamond);
                imageView.setCache(true);
                imageView.setCacheHint(CacheHint.SPEED);
                this.add(imageView, pos.getY(), pos.getX());
            }else {
                removeNodeByRowColumnIndex(pos.getX(), pos.getY());
                Empty.isBackgroundLoading();
                ImageView imageView = new ImageView(Empty);
                imageView.setCache(true);
                imageView.setCacheHint(CacheHint.SPEED);
                this.add(imageView, pos.getY(), pos.getX());
            }
        }
    }

    /**
     * Method that return a list of position depending on what have changed
     * between the last array and the array after the move.
     *
     * @return the list of position that need the image to be changed.
     */
    public List<Position> whatToChange() {
        List<Position> toChange = new ArrayList<>();
        Object[][] previous = game.getCave().getGamesUndo().peek().clone();
        for (int i = 0; i < game.getCave().getTab().length; i++) {
            for (int j = 0; j < game.getCave().getTab()[i].length; j++) {
                if (previous[i][j] != null && !previous[i][j].equals(game.getCave().getTab()[i][j])) {
                    toChange.add(new Position(i, j));
                } else if (previous[i][j] == null && game.getCave().getTab()[i][j] != null) {
                    toChange.add(new Position(i, j));
                } else if (!previous[i][j].equals(game.getCave().getTab()[i][j])) {
                    toChange.add(new Position(i, j));
                } else if (game.getCave().getTab()[i][j] instanceof Ground && previous[i][j] instanceof Player) {
                    toChange.add(new Position(i, j));
                } else if (previous[i][j] != null && game.getCave().getTab()[i][j] == null) {
                    toChange.add(new Position(i, j));
                } else if (previous[i][j] instanceof Player && !(game.getCave().getTab()[i][j] instanceof Player)) {
                    toChange.add(new Position(i, j));
                } else if (previous[i][j] instanceof Ground && !(game.getCave().getTab()[i][j] instanceof Ground)) {
                    toChange.add(new Position(i, j));
                } else if (previous[i][j] instanceof Rock && game.getCave().getTab()[i][j] instanceof Void) {
                    toChange.add(new Position(i, j));
                }
            }
        }
        toChange.add(new Position(game.getCave().getPlayerPos().getX(), game.getCave().getPlayerPos().getY()));
        toChange.add(new Position(game.getCave().getPlayerPos().getX(), game.getCave().getPlayerPos().getY() + 1));
        toChange.add(new Position(game.getCave().getPlayerPos().getX() + 1, game.getCave().getPlayerPos().getY()));
        toChange.add(new Position(game.getCave().getPlayerPos().getX(), game.getCave().getPlayerPos().getY() - 1));
        toChange.add(new Position(game.getCave().getPlayerPos().getX() - 1, game.getCave().getPlayerPos().getY()));
        return toChange;
    }

    /**
     * Method to remove the imageView at the given index row column of the board.
     *
     * @param row the row of the imageview
     * @param column the column of the imageview
     */
    public void removeNodeByRowColumnIndex(final int row, final int column) {
        ObservableList<Node> childrens = this.getChildren();
        for (Node node : childrens) {
            if (node instanceof ImageView && this.getRowIndex(node) == row && this.getColumnIndex(node) == column) {
                ImageView imageView = (ImageView) node; // use what you want to remove
                this.getChildren().remove(imageView);
                break;
            }
        }
    }
}
