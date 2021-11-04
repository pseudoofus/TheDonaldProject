package TheDonaldGameFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML private Button START;
    @FXML private TextField player1;
    @FXML private TextField player2;
    @FXML private TextField player3;
    @FXML private TextField player4;


    public void changeScreenButtonPushed(ActionEvent event) throws IOException{
        Main.game = new Game(player1.getText(), player2.getText(), player3.getText(), player4.getText());
        Main.bid = new Bid(Main.game);
        Parent BidView = FXMLLoader.load(getClass().getResource("BidView.fxml"));
        Scene BidViewScene = new Scene(BidView);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(BidViewScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}
