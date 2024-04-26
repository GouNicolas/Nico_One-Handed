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
}