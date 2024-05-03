package model;


public class TestMain {
    public static void main(String[] args){
        Deck baba = new Deck();
        baba.shuffleDeck();
        int line_number= 0;
        for (Card card : baba.getDeck()){
            line_number+=1;
            System.out.println(line_number+" : "+ card);
        }
        
    }
}
