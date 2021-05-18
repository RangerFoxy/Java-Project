package boardgame.controller;

import javafx.fxml.FXML;
import org.tinylog.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class WinnerController {

    private Draw view = new Draw();

    @FXML
    private Label side;

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
