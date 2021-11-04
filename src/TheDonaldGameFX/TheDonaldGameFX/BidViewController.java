package TheDonaldGameFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class BidViewController implements Initializable {

    @FXML private Text player1;
    @FXML private Text player2;
    @FXML private Text player3;
    @FXML private Text player4;
    @FXML private Text CurrentTurn;
    @FXML private Text HighestBid;
    @FXML private Text BidColour;
    @FXML private Text Caller;
    @FXML private HBox hand1;
    @FXML private HBox hand2;
    @FXML private HBox hand3;
    @FXML private HBox hand4;
    @FXML private ToggleGroup rank;
    @FXML private ToggleGroup colour;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        player1.setText(Main.bid.getGame().getPlayers()[0].getName());
        player2.setText(Main.bid.getGame().getPlayers()[1].getName());
        player3.setText(Main.bid.getGame().getPlayers()[2].getName());
        player4.setText(Main.bid.getGame().getPlayers()[3].getName());
        CurrentTurn.setText("Player " + (Main.bid.getCurrentTurn() + 1));
        deckUpdate();
    }

    public void bidButtonPushed(){
        int r = rank.getToggles().indexOf(rank.getSelectedToggle());
        int c = colour.getToggles().indexOf(colour.getSelectedToggle());
        rank.getSelectedToggle().setSelected(false);
        colour.getSelectedToggle().setSelected(false);
        Main.bid.callBid(c+1,r+1);
        infoUpdate();
    }

    public void passButtonPushed(ActionEvent event) throws IOException {
        Main.bid.Continue();
        infoUpdate();
        if(Main.bid.isBidEnd()){
            Main.game = Main.bid.getGame();
            Parent TeamSelectView = FXMLLoader.load(getClass().getResource("TeamSelectView.fxml"));
            Scene TeamSelectViewScene = new Scene(TeamSelectView);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(TeamSelectViewScene);
            window.show();
        }
    }

    public void infoUpdate(){
        CurrentTurn.setText("Player " + (Main.bid.getCurrentTurn() + 1));
        HighestBid.setText(Integer.toString(Main.bid.getNum()));
        BidColour.setText(Main.bid.getColour().toUpperCase(Locale.ROOT));
        if(!Main.bid.getColour().equalsIgnoreCase("No Bid")) {
            Caller.setText(Main.bid.getGame().getPlayers()[Main.bid.getWinningBid()].getName());
        }
    }

    public void deckUpdate() {
        int i = 0;
        for (Node b : hand1.getChildren()) {
            if (Main.bid.getGame().getPlayers()[0].getHand()[i].getRank().val > 10)
                ((Button) b).setText(Main.bid.getGame().getPlayers()[0].getHand()[i].getRank().toString());
            else
                ((Button) b).setText(Main.bid.getGame().getPlayers()[0].getHand()[i].getRank().val.toString());
            b.setStyle("-fx-text-fill:" + (Main.bid.getGame()).getPlayers()[0].getHand()[i].getColour().toString() + ";");
            i++;
        }
        i = 0;
        for (Node b : hand2.getChildren()) {
            if (Main.bid.getGame().getPlayers()[1].getHand()[i].getRank().val > 10)
                ((Button) b).setText(Main.bid.getGame().getPlayers()[1].getHand()[i].getRank().toString());
            else
                ((Button) b).setText(Main.bid.getGame().getPlayers()[1].getHand()[i].getRank().val.toString());
            b.setStyle("-fx-text-fill:" + (Main.bid.getGame()).getPlayers()[1].getHand()[i].getColour().toString() + ";");
            i++;
        }
        i = 0;
        for (Node b : hand3.getChildren()) {
            if (Main.bid.getGame().getPlayers()[2].getHand()[i].getRank().val > 10)
                ((Button) b).setText(Main.bid.getGame().getPlayers()[2].getHand()[i].getRank().toString());
            else
                ((Button) b).setText(Main.bid.getGame().getPlayers()[2].getHand()[i].getRank().val.toString());
            b.setStyle("-fx-text-fill:" + (Main.bid.getGame()).getPlayers()[2].getHand()[i].getColour().toString() + ";");
            i++;
        }
        i = 0;
        for (Node b : hand4.getChildren()) {
            if (Main.bid.getGame().getPlayers()[3].getHand()[i].getRank().val > 10)
                ((Button) b).setText(Main.bid.getGame().getPlayers()[3].getHand()[i].getRank().toString());
            else
                ((Button) b).setText(Main.bid.getGame().getPlayers()[3].getHand()[i].getRank().val.toString());
            b.setStyle("-fx-text-fill:" + (Main.bid.getGame()).getPlayers()[3].getHand()[i].getColour().toString() + ";");
            i++;
        }
    }
}
