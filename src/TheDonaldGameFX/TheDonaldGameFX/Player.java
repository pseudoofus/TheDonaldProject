package TheDonaldGameFX;

public class Player {

    private final String name;
    private boolean donaldCalled;

    public Card[] getHand() {
        return hand;
    }

    private Card[] hand;

    public boolean isDonaldCalled() {
        return donaldCalled;
    }



    public Player(String name) {
        this.name = name;
        this.hand = new Card[13];
        donaldCalled = false;
    }

    public void printHand(){
        System.out.print("| ");
        for(Card c : hand){
            System.out.printf("%-10s", c.toString());
            System.out.print("| ");
        }
        System.out.println();
    }

    public void addCard(Card c, int index){
        this.hand[index] = c;
    }

    public void removeCard(int index){
        Card[] copy = new Card[this.hand.length -1 ];
        for (int i = 0, j = 0; i < this.hand.length; i++) {
            if (i != index) {
                copy[j++] = this.hand[i];
            }
        }
        this.hand = copy;
    }

    public Card playCard(int i){
        Card card = hand[i];
        removeCard(i);
        return card;
    }

    public String toString(){
        return this.name;
    }

    public void callDonald(){
        this.donaldCalled = true;
    }

    public String getName() {
        return name;
    }

}