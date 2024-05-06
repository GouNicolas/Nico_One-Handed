package model;
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

    public void pioche(Deck d, Hand h){
        h.addCard(d.getHead());

    }

    public void init(Deck d, Discard dis, Hand h, int s, int j){
        d.initDeck();
        for(int i=0; i<4;i++){
            pioche(d,h);
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
        return true;
    }
    public void handleRankCase() {
        if(hand.getCard(hand.length()-1).rankEquals(hand.getCard(hand.length()-4))){
            score += 5;
            //mettre ces cartes dans la défausse
            for (int i = 0; i < 4; i++){
                discard.addCard(hand.getCard(hand.length()-1));
            }
            //enlever les 4 cartes de la main
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
            //mettre ces cartes dans la défausse
            for (int i = 0; i < 2; i++){
                discard.addCard(hand.getCard(hand.length()-2));
            }
            //enlever les 2 cartes de la main
            for (int i = 0; i < 2; i++){
                hand.removeCard(hand.getCard(hand.length()-2));
            }
        }
        else {
            display.WrongAction("Suit");
        }
    }
    
    public void handleJokerCase() {
        // si le joueur a encore des jokers, fais comme Suit mais sans modifier le score
        if(jokers_left > 0){
            jokers_left--;
            if(hand.getCard(hand.length()).suitEquals(hand.getCard(hand.length()-3))){
                //mettre ces cartes dans la défausse
                for (int i = 0; i < 2; i++){
                    discard.addCard(hand.getCard(hand.length()-2));
                }
                //enlever les 2 cartes de la main
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
        // pioche autant de cartes que nécessaire pour avoir 4 cartes en main
        
        while(hand.length() < 4){
            pioche(deck,hand);
            if(deck.length() == 0){
                display.WrongAction("Draw");
                break;
            }
        }
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
                pioche(deck,hand);
            }
            

            Scanner input = new Scanner(System.in);
            String rep = input.nextLine();
            System.out.println("Choix : " + rep);
            input.close();
            rep = rep.toLowerCase();

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
        }
        
        

    }
}
