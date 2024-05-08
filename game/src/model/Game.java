package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import view.ConsoleDisplay;


public class Game {
    private Deck deck;
    private Discard discard;
    private Hand hand;
    private int score;
    private int jokers_left;
    private ConsoleDisplay display;


    public Game(){
        deck = new Deck();
        discard = new Discard();
        hand = new Hand();
        display = new ConsoleDisplay();
        score = 0;
        jokers_left = 3;
    }

    public void draw(){
        // Draw a card from the deck and add it to the hand
        hand.addCard(deck.getHead());

        // Remove the card from the deck
        deck.removeHead();
        

    }

    public void init(){
        // Initialize this.deck
        deck = new Deck();
        deck.initDeck();
    
        // Initialize this.discard
        discard = new Discard();

        // Initialize this.hand
        hand = new Hand();
    
        // Initialize this.score and this.jokers_left
        score = 0;
        jokers_left = 3;
    }
    public final Map<String, String> responseMap = Map.of(
                "r","Rank",
                "s","Suit",
                "j","Joker",
                "d","Draw",
                "c","Close",
                "h","Help"
            );

    public void saveGame(){
        // save the game
        // open the file save.csv
        // write the deck, discard, hand, score, jokers_left
        // close the file
        
        try {
            File file = new File("save.csv");
            if (file.exists()) {
                file.delete();
            }
            FileWriter myWriter = new FileWriter("save.csv");
            myWriter.write(deck.toString()+"\n");
            myWriter.write(discard.toString()+"\n");
            myWriter.write(hand.toString()+"\n");
            myWriter.write(score+";\n");
            myWriter.write(jokers_left+";\n");
            myWriter.close();
            
            } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            }
    }
    public boolean loadGame() {
        // load the game
        // open the file save.csv
        // read the deck, discard, hand, score, jokers_left
        // set the game state based on the read attributes
        // close the file
    
        try {
            File file = new File("save.csv");
            if (!file.exists()) {
                return false;
            }
            Scanner myReader = new Scanner(file);
            deck = new Deck(myReader.nextLine());
            discard = new Discard(myReader.nextLine());
            hand = new Hand(myReader.nextLine());
            score = Integer.parseInt(myReader.nextLine().split(";")[0]);
            jokers_left = Integer.parseInt(myReader.nextLine().split(";")[0]);
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void handleRankCase() {
        // If the first card of the hand has the same rank as the 4th card, the player wins 5 points
        if(hand.getCard(0).rankEquals(hand.getCard(3))){
            score += 5;
            // Put 4 top cards (of the hand) in the discard pile
            for (int i = 0; i < 4; i++){
                discard.addCard(hand.getCard(i));
            }
            // Remove these 4 cards from the hand
            for (int i = 0; i < 4; i++){
                hand.removeCard(hand.getCard(0));
            }
        }
        else {
            display.WrongAction("Rank");
        }
    }
    
    public void handleSuitCase() {
        // If the first card of the hand has the same suit as the 4th card, the player wins 2 points
        if(hand.getCard(0).suitEquals(hand.getCard(3))){
            score += 2;
            // Put the 2 middle cards (of the Four top cards of the hand) in the discard pile
            for (int i = 1; i < 3; i++){
                discard.addCard(hand.getCard(i));
            }
            // Remove these 2 cards from the hand
            for (int i = 1; i < 3; i++){
                hand.removeCard(hand.getCard(1));
            }
        }
        else {
            display.WrongAction("Suit");
        }
    }

    public void handleJokerCase() {
        // If the player still has jokers, do the same as handleSuitCase() but without modifying the score
        if(jokers_left > 0){
            jokers_left--;
            // Put the 2 middle cards (of the Four top cards of the hand) in the discard pile
            for (int i = 1; i < 3; i++){
                discard.addCard(hand.getCard(i));
            }
            // Remove these 2 cards from the hand
            for (int i = 1; i < 3; i++){
                hand.removeCard(hand.getCard(1));
            }
        }
        else {
        display.WrongAction("Joker");
        }
    }
    
    public void handleDrawCase() {
        // Draw 1 card or more if necessary to have 4 cards in hand
        if (deck.length() == 0){
            display.WrongAction("Draw");
        }
        else {
            do{
                draw();
            }while(hand.length() < 4);
        }
    }
    public void fillHand(){
        // Draw as many cards as necessary to have 4 cards in hand
        while(hand.length() < 4){
            draw();
        }
    }
    public boolean noSuitNoRank(){
        // if the first and last cards in the hand are neither the same suit nor the same rank, then return true
        return !((hand.getCard(0).rankEquals(hand.getCard(3)))&&(hand.getCard(0).suitEquals(hand.getCard(3))));
    }
    
    public boolean isGameOver(){
        if (deck.length() == 0){
            // The game is over if the deck is empty and the hand has less than 4 cards
            if (hand.length() <4){
                return true;
            }
            // The game is also over if the hand has 4 cards and the first and last cards has neither the same rank nor the same suit as the 4th card
            else if (noSuitNoRank()){
                return true;
            }
        }
        else {
            // The game is over if the discard pile has 52 cards
            if(discard.length() == 52){
                return true;
            }
        }
        // The game is not over
        return false;
    }
    
    /**
     * javadoc to add
     */
    public void playGameConsole(){
        boolean from_a_save = display.wantLoadSave();
        if (from_a_save){
            loadGame();
        }
        else{
            // initialize the game with default values for a new game
            init();
        }
        
        boolean run = true;

        // Only for debugging purposes
        //deck.display();

        // ask the player if he wants to see the rules, else explain the actions
        display.HelpConsole();

        // Draw 4 cards at the beginning of the game
        handleDrawCase();
        while(run){
            // Only for debugging purposes
            // display.debugCount(deck, hand, discard);

            // Show the game state
            display.displayGame(hand, score, jokers_left);
            // Ask the player for an action
            String rep = display.askAction();
            
            while(!responseMap.containsKey(rep)){
                display.badInput();
                rep = display.askAction();
            };

            switch(responseMap.get(rep)){
                case "Rank":
                    handleRankCase();
                    fillHand();
                    break;
                case "Suit":
                    handleSuitCase();
                    fillHand();
                    break;
                case "Joker":
                    handleJokerCase();
                    fillHand();
                    break;
                case "Draw":
                    handleDrawCase();
                    break;
                case "Close":
                    saveGame();
                    run = false;
                    break;
                case "Help":
                    display.HelpConsole();
                    break;
            }
            if(isGameOver()){
                run = false;
                display.GameOver();
                display.displayScore(score);
                display.displayHand(hand);
                System.exit(0);
            }
        }
    }
}
