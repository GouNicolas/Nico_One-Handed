package view;

import java.util.Scanner;

import model.Deck;
import model.Discard;
import model.Hand;

/**
 * The ConsoleDisplay class is responsible for displaying the game information and interacting with the user through the console.
 */
public class ConsoleDisplay {
    
    /**
     * Displays the rules of the game on the console.
     */
    public void ExplainRulesConsole(){
        System.out.println("The game is played with a deck of 52 cards");
        System.out.println("The deck is shuffled and the player draw 4 cards");
        System.out.println("The goal of the game is to discard all the cards or to get the highest score possible.");
        System.out.println("The game ends when the player has no action left or when the deck is empty.");
        System.out.println("The player can then choose between 6 actions :");
        System.out.println("Rank : If last and first cards in your hand have the same rank, you discard 4 cards from your hand and score 5");
        System.out.println("Suit : If last and first cards in your hand have the same suit, you discard the 2 cards in the middle your hand and score 2");
        System.out.println("Joker : Discard the 2 cards in the middle of the hand, can only be used three times, you score 0");
        System.out.println("Draw : The player draws enough cards from the deck to have 4 cards in hands");
        System.out.println("Close : to close the game and save it");
        System.out.println("Help : Display the rules and the actions");
    }

    /**
     * Displays the available actions and their corresponding keys on the console.
     */
    public void ExplainActionsConsole(){
        System.out.println("For each action, type the first letter :");
        System.out.println("Rank : r");
        System.out.println("Suit : s");
        System.out.println("Joker : j");
        System.out.println("Draw : d");
        System.out.println("Close the game : c");
        System.out.println("Help : h");
    }
    /**
     * Displays the rules and actions of the game on the console.
     */
    public void HelpConsole(){
        Scanner scanner = new Scanner(System.in);

        // ask the player if he wants to see the rules
        System.out.println("To see the rules, type \"r\" or \"rules\" :");
        String response1 = scanner.nextLine().toLowerCase();
        if (response1.equals("r") || response1.equals("rules")) {
            ExplainRulesConsole();
        }
        // ask the player if he wants to see the actions
        System.out.println("To see the actions, type \"a\" or \"actions\" :");
        String response2 = scanner.nextLine().toLowerCase();
        if (response2.equals("a") || response2.equals("actions")) {
            ExplainActionsConsole();
        }
    }

    /**
     * Displays the player's score on the console.
     * @param score The player's score.
     */
    public void displayScore(int score){
        System.out.println("Score : " + score);
    }

    /**
     * Displays a message for invalid user input on the console.
     */
    public void badInput(){
        System.out.println("Bad input, please try again.");
    }

    /**
     * Displays a message for wrong action on the console.
     * @param action The wrong action.
     */
    public void WrongAction(String action){
        switch (action) {
            case "Draw":
                System.out.println("the deck is empty.");
                break;
            case "Joker":
                System.out.println("You have no jokers left.");
                break;
            case "Rank":
                System.out.println("The first and last cards in your hand don't have the same rank.");
                break;
            case "Suit":
                System.out.println("The first and last cards in your hand don't have the same suit.");
                break;
            default:
                break;
        }
    }

    /**
     * Displays the count of cards in the deck, hand, and discard pile for debugging purposes.
     * @param deck The deck of cards.
     * @param hand The player's hand.
     * @param discard The discard pile.
     */
    public void debugCount(Deck deck, Hand hand, Discard discard){
        System.out.println("Deck : " + deck.length());
        System.out.println("Hand : " + hand.length());
        System.out.println("Discard : " + discard.length());
    }

    /**
     * Displays the player's hand on the console.
     * @param hand The player's hand.
     */
    public void displayHand(Hand hand){
        for(int i = 0; i < 4; i++){
            System.out.printf(hand.getCard(i).toString()+ "  ");
        }
        System.out.println();
    }

    /**
     * Displays the player's hand, score, and jokers left on the console.
     * @param hand The player's hand.
     * @param score The player's score.
     * @param jokers_left The number of jokers left.
     */
    public void displayGame(Hand hand, int score, int jokers_left){
        System.out.println("Score : " + score + "   Jokers left : " + jokers_left);
        displayHand(hand);
        System.out.println();
    }

    /**
     * Displays a message for game over on the console.
     */
    public void GameOver(){
        System.out.println("Well played, the game is over.");
    }

    /**
     * Prompts the player to enter an action and returns the user's input.
     * @return The user's action.
     */
    public String askAction(){
        System.out.printf("Type your action : ");
        Scanner input = new Scanner(System.in);
        String rep = input.nextLine();

        rep = rep.toLowerCase();
        return rep;
    }
    
    public boolean wantLoadSave(){
        // return true if the player wants to play the saved game
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to play the saved game? (y/n)");
        String response = scanner.nextLine().toLowerCase();

        if (response.equals("y") || response.equals("o") || response.equals("yes") || response.equals("oui")) {
            return true;
        } else {
            return false;
        }
    }
}
