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
    public void display(){
        int top4_cards = 4;
        for(Card card : hand){
            if(top4_cards == 0){
                break;
            }
            System.out.println(card);
            top4_cards--;
        }
    }
    
    
}
