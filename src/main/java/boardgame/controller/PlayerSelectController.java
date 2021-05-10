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

    @FXML
    private TextField lightPlayerName;

    @FXML
    private TextField darkPlayerName;

/*    public void backAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }*/

    public void backAction(ActionEvent event) {
        view.draw(event, "/fxml/menu.fxml");
    }

/*    public void startAction(ActionEvent event) throws IOException {
        if (!lightPlayerName.getText().equals("") && !darkPlayerName.getText().equals("")) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ui.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        }
    }*/

    public void startAction(ActionEvent event) {
        if (!lightPlayerName.getText().equals("") && !darkPlayerName.getText().equals("")) {
            view.draw(event, "/fxml/ui.fxml");
        }
    }

}
