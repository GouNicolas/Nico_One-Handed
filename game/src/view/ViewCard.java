package view;
import java.util.Map;


import javax.swing.ImageIcon;

import model.Card;
import model.Rank;
import model.Suit;


public class ViewCard extends Card{
    public String imagePath;
    public static final Map<Rank, String> rankMap = Map.ofEntries(
        Map.entry(Rank.ACE, "A"),
        Map.entry(Rank.TWO, "2"),
        Map.entry(Rank.THREE, "3"),
        Map.entry(Rank.FOUR, "4"),
        Map.entry(Rank.FIVE, "5"),
        Map.entry(Rank.SIX, "6"),
        Map.entry(Rank.SEVEN, "7"),
        Map.entry(Rank.EIGHT, "8"),
        Map.entry(Rank.NINE, "9"),
        Map.entry(Rank.TEN, "10"),
        Map.entry(Rank.JACK, "J"),
        Map.entry(Rank.QUEEN, "Q"),
        Map.entry(Rank.KING, "K")
    );
    
    
    public static final Map<Suit, String> suitMap = Map.of(
        Suit.SPADES, "S",
        Suit.HEARTS, "H",
        Suit.DIAMONDS, "D",
        Suit.CLUBS, "C"
    );
    public ViewCard(Rank rank, Suit suit){
        super(rank, suit);
        imagePath = "/resources/"+rankMap.get(rank)+suitMap.get(suit)+".png";
    }
}
