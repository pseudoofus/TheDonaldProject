package TheDonaldGameFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class GameViewController implements Initializable {

    @FXML private Text player1;
    @FXML private Text player2;
    @FXML private Text player3;
    @FXML private Text player4;
    @FXML private Text currentTurnDisplay;
    @FXML private Text priorityColourDisplay;
    @FXML private Text donaldActive;
    @FXML private Text team1roundsleft;
    @FXML private Text team2roundsleft;
    @FXML private Text player1played;
    @FXML private Text player2played;
    @FXML private Text player3played;
    @FXML private Text player4played;
    @FXML private HBox hand1;
    @FXML private HBox hand2;
    @FXML private HBox hand3;
    @FXML private HBox hand4;
    @FXML private ToggleGroup group1;
    @FXML private ToggleGroup group2;
    @FXML private ToggleGroup group3;
    @FXML private ToggleGroup group4;

    int current_turn = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb){


        player1.setText(Main.game.getPlayers()[0].getName());
        player2.setText(Main.game.getPlayers()[1].getName());
        player3.setText(Main.game.getPlayers()[2].getName());
        player4.setText(Main.game.getPlayers()[3].getName());
        player1played.setText(null);
        player2played.setText(null);
        player3played.setText(null);
        player4played.setText(null);
        priorityColourDisplay.setText(Main.game.getPriorityColour().toUpperCase(Locale.ROOT));
        currentTurnDisplay.setText(Integer.toString(Main.game.getCurrentTurn()+1));
        donaldActive.setText(Main.game.DonaldActivated());
        team1roundsleft.setText(Integer.toString(Main.game.getTeam1rounds()));
        team2roundsleft.setText(Integer.toString(Main.game.getTeam2rounds()));
        current_turn = 0;
        deckUpdate();
    }


    public void playCardButtonPushed(ActionEvent event) throws IOException {


        current_turn = Main.game.getCurrentTurn();

        switch (current_turn) {
            case 0 -> playRound(group1, hand1, current_turn, player1played);
            case 1 -> playRound(group2, hand2, current_turn, player2played);
            case 2 -> playRound(group3, hand3, current_turn, player3played);
            case 3 -> playRound(group4, hand4, current_turn, player4played);
        }

        currentTurnDisplay.setText(Integer.toString(Main.game.getCurrentTurn()+1));
        priorityColourDisplay.setText(Main.game.getPriorityColour().toUpperCase(Locale.ROOT));
        donaldActive.setText(Main.game.DonaldActivated());
        team1roundsleft.setText(Integer.toString(Main.game.getTeam1rounds()));
        team2roundsleft.setText(Integer.toString(Main.game.getTeam2rounds()));
        resetPlayed();

        if(Main.game.isGameEnded()){
            Parent GameEnd = FXMLLoader.load(getClass().getResource("GameEnd.fxml"));
            Scene GameEndScene = new Scene(GameEnd);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(GameEndScene);
            window.show();
        }
    }

    private void resetPlayed(){
        if(!player1played.getText().isBlank() && !player2played.getText().isBlank() &&
        !player3played.getText().isBlank() && !player4played.getText().isBlank()) {
            player1played.setText("");
            player2played.setText("");
            player3played.setText("");
            player4played.setText("");
        }
    }

    public void deckUpdate(){
        int i = 0;
        for(Toggle t : group1.getToggles()){
            if(Main.game.getPlayers()[0].getHand()[i].getRank().val > 10)
                ((ToggleButton) t).setText(Main.game.getPlayers()[0].getHand()[i].getRank().toString());
            else
                ((ToggleButton) t).setText(Main.game.getPlayers()[0].getHand()[i].getRank().val.toString());
            ((ToggleButton) t).setStyle("-fx-text-fill:" + Main.game.getPlayers()[0].getHand()[i].getColour().toString() + ";");
            i++;
        }
        i = 0;
        for(Toggle t : group2.getToggles()){
            if(Main.game.getPlayers()[1].getHand()[i].getRank().val > 10)
                ((ToggleButton) t).setText(Main.game.getPlayers()[1].getHand()[i].getRank().toString());
            else
                ((ToggleButton) t).setText(Main.game.getPlayers()[1].getHand()[i].getRank().val.toString());
            ((ToggleButton) t).setStyle("-fx-text-fill:" + Main.game.getPlayers()[1].getHand()[i].getColour().toString() + ";");
            i++;
        }
        i = 0;
        for(Toggle t : group3.getToggles()){
            if(Main.game.getPlayers()[2].getHand()[i].getRank().val > 10)
                ((ToggleButton) t).setText(Main.game.getPlayers()[2].getHand()[i].getRank().toString());
            else
                ((ToggleButton) t).setText(Main.game.getPlayers()[2].getHand()[i].getRank().val.toString());
            ((ToggleButton) t).setStyle("-fx-text-fill:" + Main.game.getPlayers()[2].getHand()[i].getColour().toString() + ";");
            i++;
        }
        i = 0;
        for(Toggle t : group4.getToggles()){
            if(Main.game.getPlayers()[3].getHand()[i].getRank().val > 10)
                ((ToggleButton) t).setText(Main.game.getPlayers()[3].getHand()[i].getRank().toString());
            else
                ((ToggleButton) t).setText(Main.game.getPlayers()[3].getHand()[i].getRank().val.toString());
            ((ToggleButton) t).setStyle("-fx-text-fill:" + Main.game.getPlayers()[3].getHand()[i].getColour().toString() + ";");
            i++;
        }
    }

    public void playRound(ToggleGroup g, HBox h, int player, Text played){
        int i = g.getToggles().indexOf(g.getSelectedToggle());
        h.getChildren().remove(g.getSelectedToggle());
        g.getToggles().remove(g.getSelectedToggle());
        played.setText(Main.game.getPlayers()[player].getName() + " played " +
                Main.game.getPlayers()[player].getHand()[i].toString().toUpperCase(Locale.ROOT));
        Main.game.Play(i);
        deckUpdate();
    }
}
