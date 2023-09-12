package model;

import util.Observable;
import util.Observer;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Cave implements Observable {
    private Object[][] tab;

    private Stack<Object[][]> gamesUndo = new Stack<>();
    private Stack<Object[][]> gamesRedo = new Stack<>();

    public void setTab(int length, int width) {
        this.tab = new Object[length][width];
    }

    public Cave() {
    }

    public Stack<Object[][]> getGamesUndo() {
        return gamesUndo;
    }

    public Stack<Object[][]> getGamesRedo() {
        return gamesRedo;
    }

    private List<Observer> observers = new ArrayList<>();

//    public void setGamesUndo(Stack<Object[][]> gamesUndo) {
//        this.gamesUndo = gamesUndo;
//    }


//    public void setGamesRedo(Stack<Object[][]> gamesRedo) {
//        this.gamesRedo = gamesRedo;
//    }


    public void print() {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] != null) {
                    System.out.print(tab[i][j].toString() + " ");
                } else System.out.print("  ");
            }
            System.out.println();
        }
    }

    public void printRight() {
        int maxW = 0, maxH = 0, k = 0, l = 0;
        if (getPlayerPos().getX() <= tab.length / 2 && getPlayerPos().getY() <= tab[0].length / 2) {
            maxW = (tab.length / 2)+2;
            maxH = (tab[0].length /3)*2;
        } else if (getPlayerPos().getX() >= tab.length / 2 && getPlayerPos().getY() <= tab[0].length / 2) {
            maxW = tab.length;
            maxH = (tab[0].length/4)*3;
            k = tab.length / 2;
        } else if (getPlayerPos().getX() <= tab.length / 2 && getPlayerPos().getY() >= tab[0].length / 2) {
            maxW = (tab.length / 2);
            maxH = tab[0].length;
            l = maxW;
        } else if (getPlayerPos().getX() >= tab.length / 2 && getPlayerPos().getY() >= tab[0].length / 2)  {
            k = tab.length /3;
            l = tab[0].length /3;
            maxW = tab.length;
            maxH = tab[0].length;
        } else {
            maxW = tab.length;
            maxH = tab[0].length;
        }


        for (int i = k; i < maxW; i++) {
            for (int j = l; j < maxH; j++) {
                if (tab[i][j] != null) {
                    System.out.print(tab[i][j].toString() + " ");
                } else System.out.print("  ");
            }
            System.out.println();
        }
    }

    public Object[][] getTab() {
        return tab;
    }

    public void setTab(int i, int j, Object o) {
        tab[i][j] = o;
    }

    public Position getPlayerPos() {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                if (tab[i][j] instanceof Player) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    public Player getPlayer() {
        return (Player) tab[getPlayerPos().getX()][getPlayerPos().getY()];
    }

    public void stack() {
        Object[][] tab = new Object[getTab().length][getTab()[0].length];
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab[i].length; j++) {
                tab[i][j] = this.tab[i][j];
            }
        }
        gamesUndo.push(tab);
    }

    public boolean updateFallIsGameOver() {
        for (int i = 0; i < tab.length; i++) {
            for (int j = tab[i].length - 1; j >= 0; --j) {
                if (tab[i][j] instanceof Rock) {
                    int cpt = 1;
                    while (tab[i + cpt][j] instanceof  Void) {
                        setTab(i + cpt, j, new Rock());
                        setTab(i + cpt - 1, j, new Void());
                        cpt++;
                        if (tab[i + cpt][j] instanceof Player) return true;
                    }
                } else if (tab[i][j] instanceof Diamond) {
                    int cpt = 1;
                    while (tab[i + cpt][j] instanceof  Void) {
                        setTab(i + cpt, j, new Diamond());
                        setTab(i + cpt - 1, j, new Void());
                        cpt++;
                        if (tab[i + cpt][j] instanceof Player) return true;
                    }
                }
            }
        }
        return false;
    }


    @Override
    public void addObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
