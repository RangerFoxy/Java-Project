package boardgame.controller;

import boardgame.Draw;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class PlayerSelectController {

    private Draw view = new Draw();

    public TextField getLightPlayerName() {
        return lightPlayerName;
    }

    @FXML
    private TextField lightPlayerName;

    public TextField getDarkPlayerName() {
        return darkPlayerName;
    }

    @FXML
    private TextField darkPlayerName;

    public void backAction(ActionEvent event) {
        view.draw(event, "/fxml/menu.fxml");
    }

    public void startAction(ActionEvent event) {
        if (!lightPlayerName.getText().equals("") && !darkPlayerName.getText().equals("")) {

            view.draw(event, "/fxml/ui.fxml");
        }
    }

}
