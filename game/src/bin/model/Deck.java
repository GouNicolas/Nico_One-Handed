package model;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck = new ArrayList();
    
    public Deck(ArrayList<Card> deck){
        this.deck = deck;
    }

    public Deck shuffle(Deck deck){
        for (Suit suit : Suit.values()){
            for (Rank rank : Rank.values()){
                Card tempCard = new Card(rank,suit);
                this.deck.add(tempCard);
            }
        }
        return shuffle(deck);
    }

    public static void printDeck(Deck deck){
        for (Card card : this.deck){
            System.out.println(card);
        }
    }

    public static void main(String[] args){
        Deck deck;
        printDeck(deck);
    }

}