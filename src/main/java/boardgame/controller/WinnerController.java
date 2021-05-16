package boardgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.tinylog.Logger;

public class WinnerController {

    private Draw view = new Draw();

    @FXML
    private Label side;

    @FXML
    private Button newGameButton;

    @FXML
    private Button exitButton;

    @FXML
    public void newAction(ActionEvent event) {
        Logger.info("The user clicked the New Game button and a new game has been started.");
        view.draw(event, "/fxml/ui.fxml");
    }

    @FXML
    public void exitAction(ActionEvent event) {
        Logger.info("The user quit to the main menu.");
        view.draw(event, "/fxml/menu.fxml");
    }

    @FXML
    public void changeLabel(String text) {
        side.setText(text);
    }
}
