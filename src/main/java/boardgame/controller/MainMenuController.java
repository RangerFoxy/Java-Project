package boardgame.controller;

import boardgame.Draw;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    public Button playButton;

    /*public void playAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/player_select.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }*/

    public void playAction(ActionEvent event) {
        Draw drawPlayerSelect = new Draw();
        drawPlayerSelect.draw(event, "/fxml/player_select.fxml");
    }


    @FXML
    public Button scoreboardButton;

    @FXML
    public Button exitButton;

    @FXML
    public void exitAction(ActionEvent event) {
        System.exit(0);
    }
}
