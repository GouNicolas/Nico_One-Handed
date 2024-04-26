package model;
import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    
    public Deck(){
        deck = new ArrayList<Card>();
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

    public void printDeck(Deck deck){
        for (Card card : this.deck){
            System.out.println(card);
        }
    }

    public void main(String[] args){
        Deck deck = new Deck();
        printDeck(deck);
    }

}