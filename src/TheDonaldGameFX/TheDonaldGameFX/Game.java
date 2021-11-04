package TheDonaldGameFX;

public class Game {

    private Player[] players;
    private Card[] deck = createDeck();
    private Card[] playedCards;
    private String donald;
    private String priorityColour;

    public void selectStartTurn(int startTurn) {
        this.startTurn = startTurn;
        this.currentTurn = startTurn;
    }

    private String winningTeam;
    private int currentTurn;
    private int startTurn;
    private int roundWon;
    private int team1rounds;
    private int team2rounds;
    private boolean donaldActivated;

    public String DonaldActivated() {
        if(donaldActivated)
            return "Activated";
        return "Inactive";
    }

    public String getWinningTeam() {
        return winningTeam;
    }

    private int[] team1;
    private int[] team2;

    public void setTeams(int winner, int selected){
        int team2player1 = -1;
        int team2player2 = -1;

        for(int i = 0; i < 4; i++){
            if (i != winner && i != selected) {
                team2player1 = i;
                for(int j = 0; j < 4; j++){
                    if (j != winner && j != selected && j != team2player1){
                        team2player2 = j;
                    }
                }
            }
        }
        team1 = new int[]{winner, selected};
        team2 = new int[]{team2player1, team2player2};
    }

    public int getTeam1rounds() {
        return team1rounds;
    }

    public void setTeam1rounds(int team1rounds) {
        this.team1rounds = team1rounds;
    }

    public int getTeam2rounds() {
        return team2rounds;
    }

    public void setTeam2rounds(int team2rounds) {
        this.team2rounds = team2rounds;
    }

    public String getPriorityColour() {
        return priorityColour;
    }

    public Game(String player1, String player2, String player3, String player4) {
        if(player1.equals("")) player1 = "Player 1";
        if(player2.equals("")) player2 = "Player 2";
        if(player3.equals("")) player3 = "Player 3";
        if(player4.equals("")) player4 = "Player 4";
        Player[] players = {new Player(player1), new Player(player2),
                new Player(player3), new Player(player4)};
        this.players = players;
        shuffle(deck, players);
        printDeck(players);
        priorityColour = "";
        players[0].callDonald();
        playedCards = new Card[4];
        donaldActivated = false;
    }

    private static void shuffle(Card[] deck, Player[] players){
        int round = 0;
        while(deck.length > 0){
            for (Player player : players) {
                int index = (int) (deck.length * Math.random());
                player.addCard(deck[index], round);
                deck = removeCard(deck, index);
            }
            round++;
        }
    }

    public static Card[] removeCard(Card[] c, int index){
        Card[] copy = new Card[c.length -1 ];
        for (int i = 0, j = 0; i < c.length; i++) {
            if (i != index) {
                copy[j++] = c[i];
            }
        }
        return copy;
    }

    private static Card[] createDeck(){
        Card[] deck = new Card[52];
        int i = 0;
        for(Card.Colour c : Card.Colour.values()){
            for(Card.Rank r : Card.Rank.values()){
                deck[i] = new Card(c , r);
                i++;
            }
        }
        return deck;
    }

    private static void printDeck(Player[] players){
        for(Player p : players){
            System.out.println(p.toString() + "'s hand:\n");
            p.printHand();
            System.out.println();
        }
    }

    public void Play(int i){
        if(currentTurn == startTurn){
            setPriorityColour(players[currentTurn].getHand()[i].getColour().toString());
        }
        if(players[currentTurn].isDonaldCalled() && players[currentTurn].getHand()[i].getColour().toString().equalsIgnoreCase(donald)){
            setPriorityColour(donald);
            donaldActivated = true;
        }
        playedCards[currentTurn] = players[currentTurn].playCard(i);
        Continue();
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void Continue(){
        if(nextTurn() == startTurn)
            endRound();
        else
            currentTurn = nextTurn();
    }

    private void endRound() {
        roundWon = Winner();
        startTurn = roundWon;
        currentTurn = startTurn;
        winningTeam();
        setPriorityColour(" ");
        donaldActivated = false;
    }

    private void winningTeam() {
        for(int i : team1){
            if(i == roundWon) team1rounds--;
        }
        for(int i : team2){
            if(i == roundWon) team2rounds--;
        }
    }

    private int Winner() {
        int highestRank = 0;
        int tempwinner = 0;
        for(int i = 0; i < 4; i++){
            if(priorityColour.equalsIgnoreCase(playedCards[i].getColour().toString())){
                if(playedCards[i].getRank().val > highestRank){
                    highestRank = playedCards[i].getRank().val;
                    tempwinner = i;
                }
            }
        }
        return tempwinner;
    }

    public int nextTurn(){
        if(currentTurn == 3)
            return 0;
        else
            return (currentTurn + 1);
    }

    public boolean isGameEnded(){
        if(team1rounds == 0){
            winningTeam = "Team 1";
            return true;
        }
        if(team2rounds == 0){
            winningTeam = "Team 2";
            return true;
        }
        return false;
    }

    public Player[] getPlayers() {
        return players;
    }


    public String getDonald() {
        return donald;
    }

    public void setDonald(String donald) {
        this.donald = donald;
    }

    public void setPriorityColour(String priorityColour) {
        this.priorityColour = priorityColour;
    }
}