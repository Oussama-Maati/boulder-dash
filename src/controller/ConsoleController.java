package controller;

import view.ConsoleView;

public class ConsoleController {

    public void play() {
        ConsoleView view = new ConsoleView();
        view.start();
        view.play();
    }




}
