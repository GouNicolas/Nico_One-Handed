package view;
import java.util.Scanner;

import model.Deck;
import model.Discard;
import model.Hand;

public class ConsoleDisplay {
    
    public void ExplainRulesConsole(){
        System.out.println("The game is played with a deck of 52 cards");
        System.out.println("The deck is shuffled and the player draw 4 cards");
        System.out.println("The goal of the game is to discard all the cards or to get the highest score possible.");
        System.out.println("The game ends when the player has no action left or when the deck is empty.");
        System.out.println("The player can then choose between 4 actions :");
        System.out.println("Rank : If last and first cards in your hand have the same rank, you discard, you discard 4 cards your hand, you score 5");
        System.out.println("Suit : If last and first cards in your hand have the same suit, you discard, you discard the 2 cards in the middle your hand, you score 5");
        System.out.println("Joker : Discard the 2 cards in the middle of the hand, can only be used three times, you score 0");
        System.out.println("Draw : The player draws enough cards from the deck to have 4 cards in hands");
        System.out.println("Help : Display the rules and the actions");
    }

    public void ExplainActionsConsole(){
        System.out.println("For each action, type the first letter :");
        System.out.println("Rank : r");
        System.out.println("Suit : s");
        System.out.println("Joker : j");
        System.out.println("Draw : d");
        System.out.println("Close the game : c");
        System.out.println("Help : h");
    }
    // Only for debugging purposes
    public void displayDeck(Deck deck){
        System.out.println(deck.getDeck());
    }

    /**
     * Displays the help message and prompts the player to see the rules or actions.
     * If the player chooses to see the rules, it calls the ExplainRulesConsole method.
     * Else call the ExplainActionsConsole method.
     */
    public void HelpConsole(){
        // Display the help message
        // ask the player if he wants to see the rules
        // if the user type 'y' or 'o' or 'yes' or 'oui' then 
        // call the ExplainRulesConsole method
        // else call the ExplainActionsConsole method
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to see the rules? (y/n)");
        String response = scanner.nextLine().toLowerCase();

        if (response.equals("y") || response.equals("o") || response.equals("yes") || response.equals("oui")) {
            ExplainRulesConsole();
        } else {
            ExplainActionsConsole();
        }
    }
    public void displayScore(int score){
        System.out.println("Score : " + score);
    }
    public void badInput(){
        System.out.println("Bad input, please try again.");
    }
    public void WrongAction(String action){
        switch (action) {
            case "Draw":
                System.out.println("the deck is empty.");
                break;
            case "Joker":
                System.out.println("You have no jokers left.");
                break;
            case "Rank":
                System.out.println("You can't play the Rank action.");
                break;
            case "Suit":
                System.out.println("You can't play the Suit action.");
                break;
            default:
                break;
        }
    }
    // Only for debugging purposes
    public void debugCount(Deck deck, Hand hand, Discard discard){
        System.out.println("Deck : " + deck.length());
        System.out.println("Hand : " + hand.length());
        System.out.println("Discard : " + discard.length());
    }
    public void displayHand(Hand hand){
        for(int i = 0; i < 4; i++){
            System.out.printf(hand.getCard(i).toString()+ "  ");
        }
        System.out.println();
    }

    public void displayGame(Hand hand, int score, int jokers_left){
        System.out.println("Score : " + score + "   Jokers left : " + jokers_left);
        displayHand(hand);
        System.out.println();
    }
    public void GameOver(){
        System.out.println("Well played, the game is over.");
    }
    public String askAction(){
        System.out.printf("Type your action : ");
        Scanner input = new Scanner(System.in);
        String rep = input.nextLine();

        // temporary 
        System.out.println("Action : " + rep);

        rep = rep.toLowerCase();
        return rep;
    }
}
