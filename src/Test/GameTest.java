package Test;

import model.Facade;
import model.Game;
import model.Player;
import model.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Game game = new Game();
    Facade facade = new Facade();

    @BeforeEach
    public void begin() {
        facade.selectLvl(1,game);
    }



    @Test
    void stateInitial() {
        Assertions.assertEquals(State.DOOR_CLOSED,game.getState());
    }

    @Test
    void getState() {
        game.setNeededDiamond(1);
        game.getCave().setTab(game.getCave().getPlayer().getX(),game.getCave().getPlayer().getY(),null);
        game.getCave().setTab(4,6, new Player());
        facade.move("right",game);
        Assertions.assertEquals(State.DOOR_OPEN,game.getState());
    }

    @Test
    void setNeededDiamond() {
        game.getCave().setTab(game.getCave().getPlayer().getX(),game.getCave().getPlayer().getY(),null);
        game.getCave().setTab(7,2, new Player());
        facade.move("right",game);
        facade.move("right",game);
    }

    @Test
    void getNeededDiamond() {
    }

    @Test
    void getHavingDiamond() {
    }
}