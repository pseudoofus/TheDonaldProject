package TheDonaldGameFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class TeamSelectController implements Initializable {

    @FXML private Text winner;
    @FXML private Text donald;
    @FXML private ToggleGroup players;

    private final int[] playerIndices = new int[3];

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Player DonaldCaller = null;
        for(Player p : Main.game.getPlayers()){
            if(p.isDonaldCalled()){
                DonaldCaller = p;
            }
        }
        assert DonaldCaller != null;
        winner.setText(DonaldCaller.getName() + " wins the bid!");
        donald.setText("Donald Colour: " + Main.game.getDonald().toUpperCase(Locale.ROOT));
        playerUpdate();
    }

    public void playerUpdate(){
        int i = 0, k = 0;
        for(Toggle t : players.getToggles()){
            if(i == Main.bid.getWinningBid()){
                i++;
            }
            ((ToggleButton) t).setText(Main.game.getPlayers()[i].getName());
            playerIndices[k] = i;
            i++;
            k++;
        }
    }

    public void selectButtonPressed(ActionEvent event) throws IOException {
        int selected = players.getToggles().indexOf(players.getSelectedToggle());
        Main.game.setTeams(Main.bid.getWinningBid(), playerIndices[selected]);
        Parent GameView = FXMLLoader.load(getClass().getResource("GameView.fxml"));
        Scene GameViewScene = new Scene(GameView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(GameViewScene);
        window.show();
    }
}
