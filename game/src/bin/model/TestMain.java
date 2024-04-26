package model;

public class TestMain {
    public static void main(String[] args){
        Deck deck = new Deck();
        Deck.shuffle(deck);
        for (Card card : deck){
            System.out.println(card);
        }
    }
}
