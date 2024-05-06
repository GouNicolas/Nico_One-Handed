package model;

import java.util.ArrayList;

public class Discard extends ListOfCards{
    private ArrayList<Card> discard;
    
    public Discard(){
        discard = new ArrayList<Card>();
    }

    public void addCard(Card card){
        discard.add(card);
    }

    public void removeCard(Card card){
        discard.remove(card);
    }

    public int length(){
        return discard.size();
    }

    public int getDiscardSize(){
        return discard.size();
    }

    public ArrayList<Card> getDiscard(){
        return discard;
    }

    public void clearDiscard(){
        discard.clear();
    }

    public Card getCard(int position){
        return discard.get(position);
    }

    public void displayTopCard(){
        System.out.println(discard.get(discard.size()-1));
    }
}
