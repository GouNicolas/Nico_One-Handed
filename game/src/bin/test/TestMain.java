package test;

import model.Game;

public class TestMain {
    public static void main(String[] args){
        
        Game g = new Game();
        // debug pruposes
        g.getDeck().display();
        // launch the in the console
        g.playGameConsole();
        
    }
}
