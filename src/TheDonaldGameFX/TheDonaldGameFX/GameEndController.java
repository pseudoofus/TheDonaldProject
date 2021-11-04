package TheDonaldGameFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameEndController implements Initializable {


    @FXML private Button startAgain;
    @FXML private Button Quit;
    @FXML private Text winner;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        winner.setText(Main.game.getWinningTeam() + " wins!");
    }

    public void changeScreenButtonPushedStart(ActionEvent event) throws IOException{
        Parent MenuView = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene MenuViewScene = new Scene(MenuView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(MenuViewScene);
        window.show();
    }

    public void changeScreenButtonPushedQuit(ActionEvent event){
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.close();
    }

}
