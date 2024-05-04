package model;

public class Game {
    private Deck deck;
    private Deck discard;
    private Hand hand;
    private int score;
    private int jokers_left;

    public Game(){
        deck = new Deck();
        discard = new Deck();
        hand = new Hand();
        score = 0;
        jokers_left = 3;
    }

    public void init(Deck d, Deck dis, Hand h, int s, int j){
        d.shuffleDeck();
        for(int i=0; i<4;i++){
            h.addCard(d.getHead());
        }
        System.out.println("Salut");
    }
}
