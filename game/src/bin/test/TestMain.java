package test;

public class TestMain {
    public static void main(String[] args){
        HandTest handTest = new HandTest();
        handTest.testHand();
        DeckTest deckTest = new DeckTest();
        deckTest.testShuffleDeck();
    }
}
