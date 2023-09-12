package JavaFX;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;
import model.Void;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Launch of javaFX
 */
public class BoulderMain extends Application {

    private Score score;
    private int actualLvl;
    private BorderPane root = new BorderPane();

    Facade facade = new Facade();

    private Board board = new Board();

    Image intro = new Image(String.valueOf(Path.of("img","Intro.png")));
    ImageView gameOver = new ImageView(new Image(String.valueOf(Path.of("img","GameOver.gif"))));
    ImageView youWon = new ImageView(String.valueOf(Path.of("img","YouWon.png")));

    Button lvl1 = new Button("Level 1");
    Button lvl2 = new Button("Level 2");
    Button lvl3 = new Button("Level 3");

    @Override
    public void start(Stage stage) {
        ImageView intros = new ImageView(intro);
        Scene scene = new Scene(root, 925, 560);
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(Path.of("img","boulder-dash.png"))));
        stage.setTitle("Boulder Dash");
        stage.show();

        HBox hBox = new HBox(lvl1, lvl2, lvl3);
        lvl1.setOnAction(e -> {
            root.setStyle("-fx-background-color: #bd5633");
            actualLvl = 1;
            facade.selectLvl(actualLvl, board.getGame());
            root.getChildren().removeAll(lvl1, lvl2,lvl3,hBox);
            board.makeGrid();
            root.getChildren().add(board);
            root.setRight(score);
            score = new Score(board.getGame(), facade, board, this);
            root.setRight(score);
            board.getGame().getCave().addObserver(board.getGame());
            root.getChildren().remove(intros);
        });
        lvl2.setOnAction(e -> {
            root.setStyle("-fx-background-color: #bd5633");
            actualLvl = 2;
            facade.selectLvl(actualLvl, board.getGame());
            root.getChildren().removeAll(lvl1, lvl2,lvl3,hBox);
            board.makeGrid();
            root.getChildren().add(board);
            root.setRight(score);
            score = new Score(board.getGame(), facade, board, this);
            root.setRight(score);
            board.getGame().getCave().addObserver(board.getGame());
            root.getChildren().remove(intros);
        });

        lvl3.setOnAction(e -> {
            root.setStyle("-fx-background-color: #bd5633");
            actualLvl = 3;
            facade.selectLvl(actualLvl, board.getGame());
            root.getChildren().removeAll(lvl1, lvl2,lvl3,hBox);
            board.makeGrid();
            root.getChildren().add(board);
            root.setRight(score);
            score = new Score(board.getGame(), facade, board, this);
            root.setRight(score);
            board.getGame().getCave().addObserver(board.getGame());
            root.getChildren().remove(intros);
        });

        hBox.setPadding(new Insets(10));
        hBox.setSpacing(20);
        root.setBottom(hBox);
        root.setCenter(intros);

        setGameOver(stage);

        key(stage);


    }

    public void key(Stage stage) {
        stage.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            //System.out.println("Key pressed: " + e.toString());s
            @Override
            public void handle(KeyEvent event) {
                String character = event.getCode().toString();
                //System.out.println(character);

                if (board.getGame().getCave().getTab() != null) {
                    Object beside = board.getGame().getCave().getPlayer().ObjectAtPos(character, board.getGame().getCave());
                    if (beside instanceof Ground || beside == null
                            || beside instanceof Diamond || beside instanceof Void || beside instanceof  Emerald

                            || (beside instanceof Door && board.getGame().getState() == State.DOOR_OPEN))
                        switch (character) {
                            case "UP" -> facade.move("Z", board.getGame());
                            case "DOWN" -> facade.move("S", board.getGame());
                            case "LEFT" -> facade.move("Q", board.getGame());
                            case "RIGHT" -> facade.move("D", board.getGame());
                            default -> {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Keyboard error");
                                alert.setHeaderText("The key you tiped is not a direction !");
                                alert.setContentText("You can only type the direction key.");
                                alert.showAndWait();
                            }
                        }
                    board.getGame().update(board.getGame().getCave());
                    score.updateLabel(board.getGame());
                    board.updateGrid();
                    //System.out.println(board.getGame().getHavingDiamond());
                    //root.setLeft(board.updateGrid(board));
                }

                board.setCache(true);
                board.setCacheHint(CacheHint.SPEED);
//                gridPane.setCache(true);
//                gridPane.setCacheHint(CacheHint.SPEED);
                //root.setCenter(imageView);
                //gridPane = new GridPane();
                //gridPane = updateGrid(gridPane);
                //root.setCenter(gridPane);
                setGameOver(stage);
                setCaveFinished(stage);
            }
        });
    }

    /**
     * Getter for the actual number attribute
     * @return the int level
     */
    public int getActualLvl() {
        return actualLvl;
    }


    public void setGameOver(Stage stage){
        if (facade.isGameOver(board.getGame())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Game OVer !");
            alert.setHeaderText("You got crushed by a rock or diamond !");
            alert.setContentText("Restart !");
            gameOver.setFitWidth(250);
            gameOver.setFitHeight(100);
            alert.setGraphic(gameOver);

            Optional<ButtonType> result = alert.showAndWait();
            if (!result.isPresent()) {
            } else if (result.get() == ButtonType.OK) {
                board.getGame().getCave().getGamesUndo().clear();
                board.getGame().getCave().getGamesRedo().clear();
                board.getGame().getCave().getGamesUndo().empty();
                facade.selectLvl(actualLvl, board.getGame());
                board.makeGrid();
                board.getGame().setState(State.DOOR_CLOSED);
                board.getGame().setHavingDiamond(0);
            } else if (result.get() == ButtonType.CANCEL) {
                stage.close();
            }
            board.getGame().setState(State.DOOR_CLOSED);
        }
    }

    public void setCaveFinished(Stage stage){
        if (actualLvl == 3 && facade.isCaveFinished(board.getGame())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Congratulation ! You won the last level !");
            alert.setHeaderText("Come back to the level "+(actualLvl-2)+" ?");
            alert.setContentText("Press ok to continue");
            board.getGame().setNeededDiamond(1);
            Optional<ButtonType> result = alert.showAndWait();
            if (!result.isPresent()) {
            } else if (result.get() == ButtonType.OK) {
                actualLvl = 1;
                facade.selectLvl(actualLvl, board.getGame());
                board.makeGrid();
                score.setLvlBoard(actualLvl);
                board.getGame().getCave().getGamesUndo().clear();
                board.getGame().getCave().getGamesRedo().clear();
                board.getGame().setState(State.DOOR_CLOSED);
            } else if (result.get() == ButtonType.CANCEL) {
                stage.close();
            }
        }
        if (facade.isCaveFinished(board.getGame())){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("You won the level !");
            alert.setHeaderText("Let's go to the level "+(actualLvl+1)+" ?");
            alert.setContentText("Press ok to continue");
            youWon.setFitWidth(300);
            youWon.setFitHeight(100);
            alert.setGraphic(youWon);
            Optional<ButtonType> result = alert.showAndWait();

            if (!result.isPresent()) {
            } else if (result.get() == ButtonType.OK) {
                actualLvl++;
                facade.selectLvl(actualLvl, board.getGame());
                board.makeGrid();
                score.setLvlBoard(actualLvl);
                board.getGame().getCave().getGamesUndo().clear();
                board.getGame().getCave().getGamesRedo().clear();
                board.getGame().setState(State.DOOR_CLOSED);
            } else if (result.get() == ButtonType.CANCEL) {
                stage.close();
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}