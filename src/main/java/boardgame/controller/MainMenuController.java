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
import org.tinylog.Logger;

import java.io.IOException;

public class MainMenuController {

    private Draw view = new Draw();

    @FXML
    public Button playButton;

    public void playAction(ActionEvent event) {
        Logger.info("The player pushed the Play button and opened the Player Selection scene.");
        view.draw(event, "/fxml/player_select.fxml");
    }


    @FXML
    public Button scoreboardButton;

    @FXML
    public Button exitButton;

    @FXML
    public void exitAction(ActionEvent event) {
        Logger.info("Exit from the game.");
        System.exit(0);
    }
}
