package JavaFX;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.*;

import java.nio.file.Path;
import java.util.Timer;
import java.util.TimerTask;


public class Score extends GridPane {
    Label score;
    Label onGameDia;

    Timer timer = new java.util.Timer();
    Image diamondBoard = new Image(String.valueOf(Path.of("img", "diamondBoard.png")));

    Image lvlBoard = new Image(String.valueOf(Path.of("img", "levelBoard.png")));

    Button undo = new Button("Undo");
    Button redo = new Button("Redo");
    Button abandon = new Button("Abandon");

    private static final Integer STARTTIME = 15;
    private Timeline timeline;


    public Score(Game game, Facade facade, Board board, BoulderMain main) {
        this.setStyle("-fx-background-color: #5456eb; -fx-padding: 2; -fx-hgap: 2; -fx-vgap: 2;");
        onGameDia = new Label(String.valueOf(maxDiamond(game)));
        onGameDia.setStyle("-fx-text-fill: White;");
        ImageView count = new ImageView(diamondBoard);
        ImageView leftDia = new ImageView(diamondBoard);

        score = new Label((30 - maxDiamond(game)) + " / " + game.getNeededDiamond());
        score.setStyle("-fx-text-fill: White;");

        StackPane scored = new StackPane();
        scored.getChildren().add(count);
        scored.getChildren().add(score);

        StackPane DiamondAvl = new StackPane();
        DiamondAvl.getChildren().add(leftDia);
        DiamondAvl.getChildren().add(onGameDia);


        setLvlBoard(main.getActualLvl());
        //lvl.getChildren().add();

        undo.setOnAction(e -> {
            if (!game.getCave().getGamesUndo().empty()) {
                facade.undo(game);
                //board.getGame().update(board.getGame().getCave());
                updateLabel(board.getGame());
                board.getGame().setPlayer();
                board.updateGrid();
                board.setCache(true);
                board.setCacheHint(CacheHint.SPEED);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Undo error");
                alert.setHeaderText("You cannot make an undo now !");
                alert.setContentText("You must move at least one time to use the undo.");
                alert.showAndWait();
            }
        });
        undo.setStyle("-fx-focus-color: transparent;");
        undo.setFocusTraversable(false);

        redo.setOnAction(e -> {
            if (!game.getCave().getGamesRedo().empty()) {
                facade.redo(game);
                //board.getGame().update(board.getGame().getCave());
                updateLabel(board.getGame());
                board.updateGrid();
//            board.setCache(true);
//            board.setCacheHint(CacheHint.SPEED);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Undo error");
                alert.setHeaderText("You cannot make an redo now !");
                alert.setContentText("You must move at least one time and use the undo.");
                alert.showAndWait();
            }
        });
        redo.setStyle("-fx-focus-color: transparent;");
        redo.setFocusTraversable(false);

        abandon.setOnAction(e -> {
            game.setState(State.GAME_OVER);
            Stage stage = (Stage) abandon.getScene().getWindow();
            main.setGameOver(stage);
            stage.close();
        });
        abandon.setStyle("-fx-focus-color: transparent;");
        abandon.setFocusTraversable(false);

        this.add(scored, 2, 2);
        this.add(DiamondAvl, 2, 4);

        this.add(undo, 2, 8);
        this.add(redo, 2, 8);
        GridPane.setMargin(redo, new Insets(0, 0, 0, 50));
        this.add(abandon,2,10);

//        this.add(currentDiam,2,4);
//        this.add(neededDiam,2,6);

    }

    public int maxDiamond(Game game) {
        int diam = 0;
        for (int i = 0; i < game.getCave().getTab().length; i++) {
            for (int j = 0; j < game.getCave().getTab()[i].length; j++) {
                if (game.getCave().getTab()[i][j] instanceof Diamond) diam++;
            }
        }
        return diam;
    }

    public void updateLabel(Game game) {

        score.setText((30 - maxDiamond(game)) + " / " + game.getNeededDiamond());
        onGameDia.setText(String.valueOf(maxDiamond(game)));
    }

    public void setLvlBoard(int lvl) {
        StackPane lvlpane = new StackPane();
        lvlpane.getChildren().add(new ImageView(lvlBoard));
        Label lvlnum = new Label("Level : " + lvl);
        lvlpane.getChildren().add(lvlnum);
        lvlnum.setFont(Font.font("Verdana", 10));
        lvlnum.setStyle("-fx-text-fill: White;");
        this.add(lvlpane, 2, 6);
    }


}
