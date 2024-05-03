package model;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand;

    public Hand(){
        hand = new ArrayList<Card>();
    }
    public void addCard(Card card){
        hand.add(card);
    }
    public void removeCard(Card card){
        hand.remove(card);
    }
    public ArrayList<Card> getHand(){
        return hand;
    }
    public void clearHand(){
        hand.clear();
    }
    public int getHandSize(){
        return hand.size();
    }
    public Card getCard(int position){
        return hand.get(position);
    }
    
    
}
