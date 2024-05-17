package test;


import model.Game;
import view.GameWindow;

public class TestMain {
    public static void main(String[] args){

        Game g = new Game();
        /* g.loadGame(); */

        // launch the game in the GUI
        GameWindow gw = new GameWindow(g);

        // launch the in the console
        //g.playGameConsole();
    }
}