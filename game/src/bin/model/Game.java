package model;
import java.util.Map;

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

    public void init(Deck d, Discard dis, Hand h, int s, int j){
        // Initialize the Deck field
        if (d == null) {
            deck = new Deck();
            deck.initDeck();
        } else {
            deck = d;
        }
    
        // Initialize the Discard field
        if (dis == null) {
            discard = new Discard();
        } else {
            discard = dis;
        }
    
        // Initialize the Hand field
        if (h == null) {
            hand = new Hand();
        } else {
            hand = h;
        }
    
        // Initialize the int fields
        score = s;
        jokers_left = j;
    }
    public final Map<String, String> responseMap = Map.of(
                "r","Rank",
                "s","Suit",
                "j","Joker",
                "d","Draw",
                "c","Close",
                "h","Help"
            );
    public boolean saveGame(Deck d, Discard dis, Hand h, int s, int j){
        // sauvegarde la partie
        // Todo : implementer la sauvegarde de la partie
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
        // si la dernière carte de la main n'a ni la même couleur ni la même valeur que la carte 4, renvoie true
        return !((hand.getCard(hand.length()-1).rankEquals(hand.getCard(hand.length()-4)))||(hand.getCard(hand.length()-1).suitEquals(hand.getCard(hand.length()-4))));
    }
    
    public boolean isGameOver(){
        // si le deck est vide et que la main est vide, la partie est finie
        // si il ne reste plus de jokers, la partie est finie
        // si le joueur ne peut plus jouer, la partie est finie
        if (deck.length() == 0){
            if (hand.length() <4){
                return true;
            }
            else if (noSuitNoRank()){
                return true;
            }
        }
        else {
            if(discard.length() == 52){
                return true;
            }
        }
        return false;
    }
    
    /**
     * javadoc to add
     */
    public void playGameConsole(){
        // initialize the game with default values for a new game
        init(null, null, null, score, jokers_left);
        boolean run = true;

        // This section is only for debugging purposes
        deck.display();
        // End of debugging section

        // ask the player if he wants to see the rules, else explain the actions
        display.HelpConsole();

        // Draw 4 cards at the beginning of the game
        handleDrawCase();
        while(run){
            // This section is only for debugging purposes
            display.debugCount(deck, hand, discard);
            // End of debugging section

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
                    //todo : implementer la sauvegarde de la partie
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
