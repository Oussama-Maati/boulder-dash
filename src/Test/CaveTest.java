package Test;

import javafx.geometry.Pos;
import model.Facade;
import model.Game;
import model.Player;
import model.Position;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;


public class CaveTest {

    Facade facade = new Facade();

    @org.junit.jupiter.api.Test
    void rightPosition() {
        Game game = new Game();
        facade.selectLvl(1,game);
        Position playerPos = game.getCave().getPlayerPos();
        Assertions.assertEquals(new Position(1,1),playerPos);
    }

    @org.junit.jupiter.api.Test
    void rightPositionAfterRightMove() {
        Game game = new Game();
        facade.selectLvl(1,game);
        facade.move("right",game);
        Position playerPos = game.getCave().getPlayerPos();
        Assertions.assertEquals(new Position(1,2),playerPos);
    }

    @org.junit.jupiter.api.Test
    void rightPositionAfterDownMove() {
        Game game = new Game();
        facade.selectLvl(1,game);
        facade.move("down",game);
        Position playerPos = game.getCave().getPlayerPos();
        Assertions.assertEquals(new Position(2,1),playerPos);
    }

    @org.junit.jupiter.api.Test
    void rightPositionAfterLeftMove() {
        Game game = new Game();
        facade.selectLvl(1,game);
        game.getCave().setTab(game.getCave().getPlayer().getX(),game.getCave().getPlayer().getY(),null);
        game.getCave().setTab(2,2, new Player());
        facade.move("left",game);
        Position playerPos = game.getCave().getPlayerPos();
        Assertions.assertEquals(new Position(2,1),playerPos);
    }

    @org.junit.jupiter.api.Test
    void rightPositionAfterUpMove() {
        Game game = new Game();
        facade.selectLvl(1,game);
        game.getCave().setTab(game.getCave().getPlayer().getX(),game.getCave().getPlayer().getY(),null);
        game.getCave().setTab(2,2, new Player());
        facade.move("up",game);
        Position playerPos = game.getCave().getPlayerPos();
        Assertions.assertEquals(new Position(1,2),playerPos);
    }

    @org.junit.jupiter.api.Test
    void rightPositionAfterUpMoveButWall() {
        Game game = new Game();
        facade.selectLvl(1,game);
        facade.move("up",game);
        Position playerPos = game.getCave().getPlayerPos();
        Assertions.assertEquals(new Position(1,1),playerPos);
    }

    @org.junit.jupiter.api.Test
    void rightPositionAfterLeftMoveButWall() {
        Game game = new Game();
        facade.selectLvl(1,game);
        facade.move("up",game);
        Position playerPos = game.getCave().getPlayerPos();
        Assertions.assertEquals(new Position(1,1),playerPos);
    }

    @org.junit.jupiter.api.Test
    void rightPositionAfterRightMoveButRock() {
        Game game = new Game();
        facade.selectLvl(1,game);
        game.getCave().setTab(game.getCave().getPlayer().getX(),game.getCave().getPlayer().getY(),null);
        game.getCave().setTab(6,2, new Player());
        facade.move("right",game);
        Position playerPos = game.getCave().getPlayerPos();
        Assertions.assertEquals(new Position(6,2),playerPos);
    }

    @org.junit.jupiter.api.Test
    void rightPositionAfterRightMoveButDiamond() {
        Game game = new Game();
        facade.selectLvl(1,game);
        game.getCave().setTab(game.getCave().getPlayer().getX(),game.getCave().getPlayer().getY(),null);
        game.getCave().setTab(4,6, new Player());
        facade.move("right",game);
        Position playerPos = game.getCave().getPlayerPos();
        Assertions.assertEquals(new Position(4,7),playerPos);
    }

    @org.junit.jupiter.api.Test
    void rightPositionAfterLeftMoveButDiamond() {
        Game game = new Game();
        facade.selectLvl(1,game);
        game.getCave().setTab(game.getCave().getPlayer().getX(),game.getCave().getPlayer().getY(),null);
        game.getCave().setTab(4,8, new Player());
        facade.move("left",game);
        Position playerPos = game.getCave().getPlayerPos();
        Assertions.assertEquals(new Position(4,7),playerPos);
    }

    @org.junit.jupiter.api.Test
    void rightPositionAfterUpMoveButDiamond() {
        Game game = new Game();
        facade.selectLvl(1,game);
        game.getCave().setTab(game.getCave().getPlayer().getX(),game.getCave().getPlayer().getY(),null);
        game.getCave().setTab(5,7, new Player());
        facade.move("up",game);
        Position playerPos = game.getCave().getPlayerPos();
        Assertions.assertEquals(new Position(4,7),playerPos);
    }

    @org.junit.jupiter.api.Test
    void rightPositionAfterDownMoveButDiamond() {
        Game game = new Game();
        facade.selectLvl(1,game);
        game.getCave().setTab(game.getCave().getPlayer().getX(),game.getCave().getPlayer().getY(),null);
        game.getCave().setTab(3,7, new Player());
        facade.move("down",game);
        Position playerPos = game.getCave().getPlayerPos();
        Assertions.assertEquals(new Position(4,7),playerPos);
    }

    @org.junit.jupiter.api.Test
    void goUnderRockRightNoGameOver() {
        Game game = new Game();
        facade.selectLvl(1,game);
        game.getCave().setTab(game.getCave().getPlayer().getX(),game.getCave().getPlayer().getY(),null);
        game.getCave().setTab(7,2, new Player());
        facade.move("right",game);
        facade.move("right",game);
        Boolean IsGameOver = facade.isGameOver(game);
        Assertions.assertEquals(IsGameOver,false);
    }

}