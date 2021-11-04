package TheDonaldGameFX;

public class Bid {

    private Game game;
    private String colour;

    public int getWinningBid() {
        return winningBid;
    }

    private int colourNumber;

    public String getColour() {
        switch(colourNumber){
            case 1 -> {
                return "green";
            }
            case 2 -> {
                return "blue";
            }
            case 3 -> {
                return "yellow";
            }
            case 4 -> {
                return "red";
            }
            case 5 -> {
                return "nocolour";
            }
        }
        return "No Bid";
    }

    public int getNum() {
        return bidNumber;
    }

    private int bidNumber;
    private int currentTurn;

    public int getCurrentTurn() {
        return currentTurn;
    }

    private int startTurn;
    private int winningBid;
    private boolean bidEnd;


    public Bid(Game game){
        this.game = game;
        currentTurn = 0;
        startTurn = 0;
        colour = "";
        bidNumber = 0;
        bidEnd = false;
    }

    public void callBid(int colourIndex, int bid){
        if(bid > bidNumber){
            colourNumber = colourIndex;
            bidNumber = bid;
            startTurn = currentTurn;
            winningBid = currentTurn;
            Continue();
        }
        else if(bid == bidNumber) {
            if (colourIndex > colourNumber) {
                colourNumber = colourIndex;
                startTurn = currentTurn;
                winningBid = currentTurn;
                Continue();
            }
        }
    }

    public void Continue(){
        if(nextTurn() == startTurn && bidNumber != 0)
            bidWon();
        else
            currentTurn = nextTurn();
    }

    public int nextTurn(){
        if(currentTurn == 3)
            return 0;
        else
            return (currentTurn + 1);
    }

    public void bidWon(){
        game.getPlayers()[winningBid].callDonald();
        game.selectStartTurn(winningBid);
        switch (colourNumber){
            case 1 -> colour = "green";
            case 2 -> colour = "blue";
            case 3 -> colour = "yellow";
            case 4 -> colour = "red";
            case 5 -> colour = "nocolour";
        }
        game.setDonald(colour);
        game.setTeam1rounds(6+bidNumber);
        game.setTeam2rounds(8-bidNumber);
        bidEnd = true;
    }

    public boolean isBidEnd(){
        return bidEnd;
    }

    public Game getGame(){
        return this.game;
    }
}
