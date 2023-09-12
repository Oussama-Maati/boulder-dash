package view;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleView {

    private Game game = new Game();
    Facade facade = new Facade();

    String entry = " ____   ___  _   _ _     ____  _____ ____    ____    _    ____  _   _ \n" +
            "| __ ) / _ \\| | | | |   |  _ \\| ____|  _ \\  |  _ \\  / \\  / ___|| | | |\n" +
            "|  _ \\| | | | | | | |   | | | |  _| | |_) | | | | |/ _ \\ \\___ \\| |_| |\n" +
            "| |_) | |_| | |_| | |___| |_| | |___|  _ <  | |_| / ___ \\ ___) |  _  |\n" +
            "|____/ \\___/ \\___/|_____|____/|_____|_| \\_\\ |____/_/   \\_|____/|_| |_|";

    String gameOver = " _____   ___  ___  ___ _____   _____  _   _ ___________   _ \n" +
            "|  __ \\ / _ \\ |  \\/  ||  ___| |  _  || | | |  ___| ___ \\ | |\n" +
            "| |  \\// /_\\ \\| .  . || |__   | | | || | | | |__ | |_/ / | |\n" +
            "| | __ |  _  || |\\/| ||  __|  | | | || | | |  __||    /  | |\n" +
            "| |_\\ \\| | | || |  | || |___  \\ \\_/ /\\ \\_/ / |___| |\\ \\  |_|\n" +
            " \\____/\\_| |_/\\_|  |_/\\____/   \\___/  \\___/\\____/\\_| \\_| (_)\n" +
            "                                                            ";

    String won = " __       ___________    ____  _______  __          __     ____    __    ____  ______   .__   __.     __  \n" +
            "|  |     |   ____\\   \\  /   / |   ____||  |        /_ |    \\   \\  /  \\  /   / /  __  \\  |  \\ |  |    |  | \n" +
            "|  |     |  |__   \\   \\/   /  |  |__   |  |         | |     \\   \\/    \\/   / |  |  |  | |   \\|  |    |  | \n" +
            "|  |     |   __|   \\      /   |   __|  |  |         | |      \\            /  |  |  |  | |  . `  |    |  | \n" +
            "|  `----.|  |____   \\    /    |  |____ |  `----.    | |       \\    /\\    /   |  `--'  | |  |\\   |    |__| \n" +
            "|_______||_______|   \\__/     |_______||_______|    |_|        \\__/  \\__/     \\______/  |__| \\__|    (__)";

    String abandon = "____    ____  ______    __    __          ___      .______        ___      .__   __.  _______   ______   .__   __.  _______  _______   __  \n" +
            "\\   \\  /   / /  __  \\  |  |  |  |        /   \\     |   _  \\      /   \\     |  \\ |  | |       \\ /  __  \\  |  \\ |  | |   ____||       \\ |  | \n" +
            " \\   \\/   / |  |  |  | |  |  |  |       /  ^  \\    |  |_)  |    /  ^  \\    |   \\|  | |  .--.  |  |  |  | |   \\|  | |  |__   |  .--.  ||  | \n" +
            "  \\_    _/  |  |  |  | |  |  |  |      /  /_\\  \\   |   _  <    /  /_\\  \\   |  . `  | |  |  |  |  |  |  | |  . `  | |   __|  |  |  |  ||  | \n" +
            "    |  |    |  `--'  | |  `--'  |     /  _____  \\  |  |_)  |  /  _____  \\  |  |\\   | |  '--'  |  `--'  | |  |\\   | |  |____ |  '--'  ||__| \n" +
            "    |__|     \\______/   \\______/     /__/     \\__\\ |______/  /__/     \\__\\ |__| \\__| |_______/ \\______/  |__| \\__| |_______||_______/ (__)";
    Scanner keyb = new Scanner(System.in);
    Pattern pLvl = Pattern.compile("[1-3]", Pattern.CASE_INSENSITIVE);
    Pattern moveSide = Pattern.compile("q|d|z|s|u|r", Pattern.CASE_INSENSITIVE);

    String lvl;


    public void start() {
        System.out.println(entry);
        System.out.println("Select the level you want from 1 to 3 :");
        lvl = keyb.next();
        while (!pLvl.matcher(lvl).matches()) {
            System.err.println("Enter one 1 number for the level and between 1 and 3");
            lvl = keyb.next();
        }
        facade.selectLvl(Integer.valueOf(lvl), game);
        facade.print(game);
        System.out.println("Diamond needed : " + game.getNeededDiamond());
        System.out.println("Diamond count : " + game.getHavingDiamond());
    }

    public void move() {
        System.out.println("Enter a move or abandon");
        String move = keyb.next();
        Object beside = game.getCave().getPlayer().ObjectAtPos(move, game.getCave());
        if (!move.equalsIgnoreCase("abandon") ) {
            while (!moveSide.matcher(move).matches() || beside instanceof Rock || beside instanceof Wall) {
                System.err.println("This is not a valid move !");
                move = keyb.next();
                beside = game.getCave().getPlayer().ObjectAtPos(move, game.getCave());
            }
            if (!move.equalsIgnoreCase("u") && !move.equalsIgnoreCase("r")) {
                facade.move(move, game);
            } else if (move.charAt(0) == 'u') {
                facade.undo(game);
            } else if (move.charAt(0) == 'r'){
                System.out.println("passé");
                facade.redo(game);
            }
        } else facade.abandon(game);

        game.update(game.getCave());
    }

    public void play() {
        while (!facade.isGameOver(game)) {
            move();
            game.update(game.getCave());
            facade.print(game);
            System.out.println("Diamond needed : " + game.getNeededDiamond());
            System.out.println("Diamond count : " + game.getHavingDiamond());
            if (game.getState() == State.DOOR_OPEN) System.out.println("The door is now open !");
            changeLvl();
        }
        if (facade.isGameOver(game)) {
            System.out.println(gameOver);
            System.out.println("You either got crushed by a rock or diamond or have abandoned ");
        }
    }

    public void changeLvl(){if (facade.isCaveFinished(game) && lvl.equalsIgnoreCase("1")) {
        game = new Game();
        System.out.println(won);
        System.out.println("Now here is the level 2.");
        game.setState(State.DOOR_CLOSED);
        lvl = String.valueOf(1);
        facade.selectLvl(Integer.valueOf(lvl), game);
        game.getCave().printRight();
    } else if(facade.isCaveFinished(game) && lvl.equalsIgnoreCase("2")) {
        game = new Game();
        System.out.println(won);
        System.out.println("Now here is the level 3.");
        game.setState(State.DOOR_CLOSED);
        lvl = "3";
        facade.selectLvl(Integer.valueOf(lvl), game);
        game.getCave().printRight();
    }}
}
