package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Deck;

public class DeckTest {
    @Test
    public void testInitDeck() {
        Deck deck = new Deck();
        deck.initDeck();
        assertEquals(52, deck.getDeck().size()); // A deck of cards has 52 cards

        // Check if the deck contains duplicates
        long distinctCards = deck.getDeck().stream().distinct().count();
        assertEquals(52, distinctCards); // All cards should be unique
    }
}