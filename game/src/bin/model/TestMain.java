package model;

public class TestMain {
    public static void main(String[] args){
        Deck baba = new Deck();
        baba.shuffleDeck();
        for (Card card : baba.getDeck()){
            System.out.println(card);
        }
    }
}
