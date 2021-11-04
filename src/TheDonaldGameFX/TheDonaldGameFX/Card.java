package TheDonaldGameFX;

public class Card {

    public enum Colour{
        green,
        blue,
        yellow,
        red,
    }

    public enum Rank{
        One     (1),
        Two     (2),
        Three   (3),
        Four    (4),
        Five    (5),
        Six     (6),
        Seven   (7),
        Eight   (8),
        Nine    (9),
        Ten     (10),
        A       (11),
        B       (12),
        C       (13);

        public final Integer val;

        Rank(int r){
            this.val = r;
        }
    }

    private final Colour colour;
    private final Rank rank;

    public Card(Colour colour, Rank rank) {
        this.colour = colour;
        this.rank = rank;
    }

    public Colour getColour() {
        return colour;
    }

    public Rank getRank() {
        return rank;
    }


    public String toString(){
        if(this.rank.val <= 10)
            return this.colour.toString() + " " + rank.val;
        return this.colour.toString() + " " + rank.toString();
    }

}