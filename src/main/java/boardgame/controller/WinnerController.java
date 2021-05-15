package boardgame.controller;

import boardgame.Draw;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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
        view.draw(event, "/fxml/ui.fxml");
    }

    @FXML
    public void exitAction(ActionEvent event) {
        view.draw(event, "/fxml/menu.fxml");
    }

    @FXML
    public void changeLabel(String text) {
        side.setText(text);
    }
}
