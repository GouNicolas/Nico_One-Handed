package model;
import java.util.ArrayList;

public class Deck {
    ArrayList<Card> deck = new ArrayList();
    
    public Deck(ArrayList<Card> deck){
        this.deck = deck;
    }

    public Deck shuffle(Deck deck){
        for (Suit suit : Suit.values){
            for (Rank rank : Rank.values){
                Card tempCard = new Card(rank,suit);
                Deck.add(tempCard);
            }
        }
        return Collections.shuffle(deck);
    }

    public void printDeck(Deck deck){
        for (Card card : deck){
            System.out.println(card);
        }
    }

    public static void main(String[] args){
        Deck deck = new Deck;
        printDeck(deck);
    }

}