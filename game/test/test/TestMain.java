package test;

import model.Game;
import view.TestWindow;

public class TestMain {
    public static void main(String[] args){
        // launch the game in the GUI
        //new TestWindow();
        
        Game g = new Game();

        // launch the in the console
        g.playGameConsole();

        

        
    }

}
