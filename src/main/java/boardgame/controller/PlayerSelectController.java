package boardgame.controller;

import boardgame.Draw;
import boardgame.model.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

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

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ui.fxml"));
                Parent root = fxmlLoader.load();
                fxmlLoader.<BoardGameController>getController().setLightPlayerName(lightPlayerName.getText());
                fxmlLoader.<BoardGameController>getController().setDarkPlayerName(darkPlayerName.getText());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                Logger.error("IOException has been occured!");
                System.exit(1);
            }


            //view.draw(event, "/fxml/ui.fxml");
        }
    }

}
