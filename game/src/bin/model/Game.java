package model;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private Deck discard;
    private Hand hand;
    private int score;
    private int jokers_left;

    public Game(){
        deck = new Deck();
        discard = new Deck();
        hand = new Hand();
        score = 0;
        jokers_left = 3;
    }

    public void pioche(Deck d, Hand h){
        h.addCard(d.getHead());

    }

    public void init(Deck d, Deck dis, Hand h, int s, int j){
        d.shuffleDeck();
        for(int i=0; i<4;i++){
            pioche(d,h);
        }
    }

    public void playGameConsole(){
        init(deck, discard, hand, score, jokers_left);
        boolean run = true;
        while(run){
            while(hand.length() < 4){
                pioche(deck,hand);
            }

            System.out.println("Choose between these actions by typing it into the terminal :");
            System.out.println("Rank");
            System.out.println("Suit");
            System.out.println("Joker");
            System.out.println("Piocher");

            Scanner input = new Scanner(System.in);
            String rep = input.nextLine();
            System.out.println("Choix : " + rep);

            switch(rep){
                case "Rank":
                    if(hand.getCard(hand.length()).rankEquals(hand.getCard(hand.length()-3))){
                        score+=5;
                        //enlever les 4 cartes de la main
                        //mettre ces cartes dans la défausse
                    }
                    break;
                case "Suit":
                    if(hand.getCard(hand.length()).suitEquals(hand.getCard(hand.length()-3))){

                    }
                    break;
                case "Joker":

                    break;
                case "Piocher":

                    break;
            }
        }
        
        

    }    
}
