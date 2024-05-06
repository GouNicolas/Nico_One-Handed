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

    public void draw(Deck d, Hand h){
        h.addCard(d.getHead());

    }

    public void init(Deck d, Discard dis, Hand h, int s, int j){
        d.initDeck();
        for(int i=0; i<4;i++){
            draw(d,h);
        }
    }
    public final Map<String, String> responseMap = Map.of(
                "Rank","r",
                "Suit","s",
                "Joker","j",
                "Piocher","d",
                "Close","c");
    public boolean saveGame(Deck d, Discard dis, Hand h, int s, int j){
        // sauvegarde la partie
        // Todo : implementer la sauvegarde de la partie
        return true;
    }
    public void handleRankCase() {
        if(hand.getCard(hand.length()-1).rankEquals(hand.getCard(hand.length()-4))){
            score += 5;
            // Put 4 top cards (of the hand) in the discard pile
            for (int i = 0; i < 4; i++){
                discard.addCard(hand.getCard(hand.length()-1));
            }
            // Remove these 4 cards from the hand
            for (int i = 0; i < 4; i++){
                hand.removeCard(hand.getCard(hand.length()-1));
            }
        }
        else {
            display.WrongAction("Rank");
        }
    }
    
    public void handleSuitCase() {
        if(hand.getCard(hand.length()-1).suitEquals(hand.getCard(hand.length()-4))){
            score += 2;
            // Put the 2 middle cards (of the Four top cards of the hand) in the discard pile
            for (int i = 0; i < 2; i++){
                discard.addCard(hand.getCard(hand.length()-2));
            }
            // Remove these 2 cards from the hand
            for (int i = 0; i < 2; i++){
                hand.removeCard(hand.getCard(hand.length()-2));
            }
        }
        else {
            display.WrongAction("Suit");
        }
    }
    // only for debugging purposes
    public Deck getDeck(){
        return deck;
    }

    public void handleJokerCase() {
        // If the player still has jokers, do the same as handleSuitCase() but without modifying the score
        if(jokers_left > 0){
            jokers_left--;
            if(hand.getCard(hand.length()).suitEquals(hand.getCard(hand.length()-3))){
                // Put the 2 middle cards (of the Four top cards of the hand) in the discard pile
                for (int i = 0; i < 2; i++){
                    discard.addCard(hand.getCard(hand.length()-2));
                }
                // Remove these 2 cards from the hand
                for (int i = 0; i < 2; i++){
                    hand.removeCard(hand.getCard(hand.length()-2));
                }
            }
        }
        else {
        display.WrongAction("Joker");
        }
    }
    
    public void handleDrawCase() {
         // Draw as many cards as necessary to have 4 cards in hand
        while(hand.length() < 4){
            draw(deck,hand);
            if(deck.length() == 0){
                display.WrongAction("Draw");
                break;
            }
        }
    }
    public boolean noSuitNoRank(){
        // si la dernière carte de la main n'a ni la même couleur ni la même valeur que la carte 4, renvoie true
        return !((hand.getCard(hand.length()-1).rankEquals(hand.getCard(hand.length()-4)))||(hand.getCard(hand.length()-1).suitEquals(hand.getCard(hand.length()-4))));
    }
    
    public boolean isGameOver() {
        // si le deck est vide et que la main est vide, la partie est finie
        // si il ne reste plus de jokers, la partie est finie
        // si le joueur ne peut plus jouer, la partie est finie
        if ((jokers_left == 0 && hand.length() < 4) || (deck.length() == 0 && hand.length() < 4) || discard.length() == 52 || noSuitNoRank()){
            return true;
        }
        return false;
    }
    
    /**
     * javadoc to add
     */
    public void playGameConsole(){
        init(deck, discard, hand, score, jokers_left);
        boolean run = true;
        
        // ask the player if he wants to see the rules, else explain the actions
        display.HelpConsole();
        // launch the game in th console
        while(run){
            while(hand.length() < 4){
                draw(deck,hand);
            }
            // Show the game state
            display.displayGame(hand, score, jokers_left);
            // Ask the player for an action
            String rep = display.askAction();

            switch(rep){
                case "Rank":
                    handleRankCase();
                    break;
                case "Suit":
                    handleSuitCase();
                    break;
                case "Joker":
                    handleJokerCase();
                    break;
                case "Draw":
                    handleDrawCase();
                    break;
                case "Close":
                    run = false;
                    break;
                default:
                    // if the typed response is incorrect, call HelpConsole
                    if (!(responseMap.containsValue(rep))){
                        display.badInput();
                    }
                    break;
            }
            if(isGameOver()){
                run = false;
                display.GameOver();
                display.displayScore(score);
                System.exit(0);
            }
        }
    }
}
