package model;

import java.util.ArrayList;

public class Hand extends ListOfCards{
    private ArrayList<Card> hand;

    public Hand(){
        hand = new ArrayList<Card>();
    }
    public void addCard(Card card){
        hand.add(0, card);
    }
    public void removeCard(Card card){
        // Remove the specified card from the hand
        hand.remove(card);
        }
        
    public int length(){
        // Return the number of cards in the hand
        return hand.size();
        }
        
    public int getHandSize(){
        // Return the size of the hand
        return hand.size();
        }
        
    public ArrayList<Card> getHand(){
        // Return the entire hand
        return hand;
    }
    public void clearHand(){
        hand.clear();
    }
    public Card getCard(int position){
        if (position < 0 || position >= hand.size()){
            return null;
        }
        return hand.get(position);
    }
    
    
    
}
