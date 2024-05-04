package test;

import model.Deck;
import model.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    @Test
    public void testShuffleDeck() {
        Deck deck = new Deck();
        deck.shuffleDeck();
        assertEquals(52, deck.getDeck().size()); // A deck of cards has 52 cards

        // Check if the deck contains duplicates
        long distinctCards = deck.getDeck().stream().distinct().count();
        assertEquals(52, distinctCards); // All cards should be unique
    }
}