package view;
import java.util.Map;

import javax.swing.ImageIcon;

import model.Card;
import model.Rank;
import model.Suit;


public class ViewCard extends Card{
    public ImageIcon image;
    public static final Map<Rank, String> rankMap = Map.ofEntries(
        Map.entry(Rank.ace, "A"),
        Map.entry(Rank.two, "2"),
        Map.entry(Rank.three, "3"),
        Map.entry(Rank.four, "4"),
        Map.entry(Rank.five, "5"),
        Map.entry(Rank.six, "6"),
        Map.entry(Rank.seven, "7"),
        Map.entry(Rank.eight, "8"),
        Map.entry(Rank.nine, "9"),
        Map.entry(Rank.ten, "10"),
        Map.entry(Rank.jack, "J"),
        Map.entry(Rank.queen, "Q"),
        Map.entry(Rank.king, "K")
    );
    
    
    public static final Map<Suit, String> suitMap = Map.of(
        Suit.clubs, "C",
        Suit.diamonds, "D",
        Suit.hearts, "H",
        Suit.spades, "S"
    );
    public ViewCard(Suit suit, Rank rank){
        super(rank, suit);
        image = new ImageIcon("game/resources/images/cards/"+rankMap.get(rank)+suitMap.get(suit)+".png");
    }
}
