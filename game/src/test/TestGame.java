package test;

import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestGame{
    @Test
    void testIsGameOver() {
        Game game = new Game();
        // Assuming that the deck and hand are initially empty
        assertTrue(game.isGameOver());
        // Add cards to the deck
        game.getDeck().add(new Card(Rank.ACE, Suit.HEARTS));  // replace Rank and Suit with actual enum or class used in your Card class
        assertFalse(game.isGameOver());
        // Add cards to the hand
        game.getHand().add(new Card(Rank.ACE, Suit.HEARTS));
        game.getHand().add(new Card(Rank.TWO, Suit.HEARTS));
        game.getHand().add(new Card(Rank.THREE, Suit.HEARTS));
        game.getHand().add(new Card(Rank.FOUR, Suit.HEARTS));
        assertFalse(game.isGameOver());
        // Make the first and last cards in the hand have neither the same rank nor the same suit
        game.getHand().set(0, new Card(Rank.ACE, Suit.HEARTS));
        game.getHand().set(3, new Card(Rank.TWO, Suit.DIAMONDS));
        assertTrue(game.isGameOver());
    }
}

