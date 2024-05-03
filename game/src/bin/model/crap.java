package model;
/* 
import subClassOfCard.*;



import java.util.*;
enum Rank { 
    two(new Character('2'),"Two"),three(new Character('3'),"Three"),four(new Character('4'),"Four"),five(new Character('5'),"Five"),six(new Character('6'),"Six"),seven(new Character('7'),"Seven"),eight(new Character('8'),"Eight"),nine(new Character('9'),"Nine"),ten(new Character('T'),"Ten"),jack(new Character('J'),"Jack"),queen(new Character('Q'),"Queen"),king(new Character('K'),"King"),ace(new Character('A'),"Ace");
    Rank(final Character c,final String s) {
        this.c=c;
        this.s=s;
    }
    public static Rank fromInt(int n) {
        for(Rank r:Rank.values())
            if(r.ordinal()==n)
                return r;
        return null;
    }
    public static Rank fromCharacter(char c) {
        for(Rank r:Rank.values())
            if(r.c==c)
                return r;
        return null;
    }
    public static Rank[] fromCharacters(final String cards) {
        final Rank[] rank=new Rank[5];
        for(int i=0;i<5;i++)
            rank[i]=Rank.fromCharacter(cards.charAt(i));
        return rank;
    }
    public Character toCharacter() {
        return c;
    }
    public String toString() {
        return s;
    }
    public static String toString(final Rank[] rank) {
        String s="";
        for(Rank r:rank)
            s+=r.toCharacter();
        return s;
    }
    final Character c;
    final String s;
}
enum Suit {
    clubs(new Character('c'),"Club"),diamonds(new Character('d'),"Diamond"),hearts(new Character('h'),"Heart"),spades(new Character('s'),"Spade");
    Suit(final Character c,final String s) {
        this.c=c;
        this.s=s;
    }
    public static Suit fromCharacter(char c) {
        for(Suit s:Suit.values())
            if(s.c==c)
                return s;
        return null;
    }
    public Character toCharacter() {
        return c;
    }

    public String toString() {
        return s;
    }
    public static String toString(final Suit[] suit) {
        String s_="";
        for(Suit s:suit)
            s_+=s.toCharacter();
        return s_;
    }
    public static boolean areSuited(final Suit[] suit) {
        final int n=suit.length;
        for(int i=0;i<n-1;i++)
            if(suit[i]!=suit[i+1])
                return false;
        return true;
    }
    final Character c;
    final String s;
}
enum Card {
    twoOfClubs(Rank.two, Suit.clubs),
    twoOfDiamonds(Rank.two, Suit.diamonds),
    twoOfHearts(Rank.two, Suit.hearts),
    twoOfSpades(Rank.two, Suit.spades),
    threeOfClubs(Rank.three, Suit.clubs),
    threeOfDiamonds(Rank.three, Suit.diamonds),
    threeOfHearts(Rank.three, Suit.hearts),
    threeOfSpades(Rank.three, Suit.spades),
    fourOfClubs(Rank.four, Suit.clubs),
    fourOfDiamonds(Rank.four, Suit.diamonds),
    fourOfHearts(Rank.four, Suit.hearts),
    fourOfSpades(Rank.four, Suit.spades),
    fiveOfClubs(Rank.five, Suit.clubs),
    fiveOfDiamonds(Rank.five, Suit.diamonds),
    fiveOfHearts(Rank.five, Suit.hearts),
    fiveOfSpades(Rank.five, Suit.spades),
    sixOfClubs(Rank.six, Suit.clubs),
    sixOfDiamonds(Rank.six, Suit.diamonds),
    sixOfHearts(Rank.six, Suit.hearts),
    sixOfSpades(Rank.six, Suit.spades),
    sevenOfClubs(Rank.seven, Suit.clubs),
    sevenOfDiamonds(Rank.seven, Suit.diamonds),
    sevenOfHearts(Rank.seven, Suit.hearts),
    sevenOfSpades(Rank.seven, Suit.spades),
    eightOfClubs(Rank.eight, Suit.clubs),
    eightOfDiamonds(Rank.eight, Suit.diamonds),
    eightOfHearts(Rank.eight, Suit.hearts),
    eightOfSpades(Rank.eight, Suit.spades),
    nineOfClubs(Rank.nine, Suit.clubs),
    nineOfDiamonds(Rank.nine, Suit.diamonds),
    nineOfHearts(Rank.nine, Suit.hearts),
    nineOfSpades(Rank.nine, Suit.spades),
    tenOfClubs(Rank.ten, Suit.clubs),
    tenOfDiamonds(Rank.ten, Suit.diamonds),
    tenOfHearts(Rank.ten, Suit.hearts),
    tenOfSpades(Rank.ten, Suit.spades),
    jackOfClubs(Rank.jack, Suit.clubs),
    jackOfDiamonds(Rank.jack, Suit.diamonds),
    jackOfHearts(Rank.jack, Suit.hearts),
    jackOfSpades(Rank.jack, Suit.spades),
    queenOfClubs(Rank.queen, Suit.clubs),
    queenOfDiamonds(Rank.queen, Suit.diamonds),
    queenOfHearts(Rank.queen, Suit.hearts),
    queenOfSpades(Rank.queen, Suit.spades),
    kingOfClubs(Rank.king, Suit.clubs),
    kingOfDiamonds(Rank.king, Suit.diamonds),
    kingOfHearts(Rank.king, Suit.hearts),
    kingOfSpades(Rank.king,
    aceOfClubs(Rank.aceHigh,Suit.clubs),
    aceOfDiamonds(Rank.aceHigh,Suit.diamonds),
    aceOfHearts(Rank.aceHigh,Suit.hearts),
    aceOfSpades(Rank.aceHigh,Suit.spades);
    private Card(Rank rank,Suit suit) {
        this.rank=rank;
        this.suit=suit;
    }

    public Rank rank() {
        return rank;
    }
    public Suit suit() {
        return suit;
    }
    public String toString() {
        return rank+" of "+suit+'s';
    }
    public static String toString(final Card[] card) {
        String s=new String();
        for(int i=0;i<card.length;i++) {
            if(i>0)
                s+=", ";
            s+=card[i].toCharacters();
        }
        return s;
    }
    public String toCharacters() {
        return ""+rank().toCharacter()+suit().toCharacter();
    }
    public static Card instance(Rank rank,Suit suit) { // some hack for speed
        final int n=4*rank.ordinal()+suit.ordinal();
        //System.out.println(rank+" "+suit+" "+n);
        return element[4*rank.ordinal()+suit.ordinal()];
    }
    static Card[] create(int standards,int jokers) {
        int cards=0;
        Card card[]=new Card[standards*52+jokers];
        for(int i=0;i<standards;i++)
            for(Suit suit:EnumSet.range(Suit.clubs,Suit.spades))
                for(Rank rank:EnumSet.range(Rank.two,Rank.aceHigh))
                    card[cards++]=instance(rank,suit);
        for(int i=0;i<jokers;i++)
            card[cards++]=instance(Rank.joker,Suit.joker);
        return card;
    }
    private final Rank rank;
    private final Suit suit;
    private static final Card element[]={twoOfClubs,twoOfDiamonds,twoOfHearts,twoOfSpades,threeOfClubs,threeOfDiamonds,threeOfHearts,threeOfSpades,fourOfClubs,fourOfDiamonds,fourOfHearts,fourOfSpades,fiveOfClubs,fiveOfDiamonds,fiveOfHearts,fiveOfSpades,sixOfClubs,sixOfDiamonds,sixOfHearts,sixOfSpades,sevenOfClubs,sevenOfDiamonds,sevenOfHearts,sevenOfSpades,eightOfClubs,eightOfDiamonds,eightOfHearts,eightOfSpades,nineOfClubs,nineOfDiamonds,nineOfHearts,nineOfSpades,tenOfClubs,tenOfDiamonds,tenOfHearts,tenOfSpades,jackOfClubs,jackOfDiamonds,jackOfHearts,jackOfSpades,queenOfClubs,queenOfDiamonds,queenOfHearts,queenOfSpades,kingOfClubs,kingOfDiamonds,kingOfHearts,kingOfSpades,aceOfClubs,
        aceOfDiamonds,aceOfHearts,aceOfSpades};
}
 */