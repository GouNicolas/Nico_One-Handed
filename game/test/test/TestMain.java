package test;

import model.Game;
import view.TestWindow;

public class TestMain {
    public static void main(String[] args){
        Game g = new Game();
        
        
        // launch the game in the GUI
        
        new TestWindow();
        
        

        // launch the in the console
        g.playGameConsole();

        

        
    }

}
