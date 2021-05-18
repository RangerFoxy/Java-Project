package boardgame.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.tinylog.Logger;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class PlayerSelectController {

    private Draw view = new Draw();

    @FXML
    private TextField lightPlayerName;

    @FXML
    private TextField darkPlayerName;

    public void backAction(ActionEvent event) {
        Logger.info("The user pushed the Back button and returned to the main menu.");
        view.draw(event, "/fxml/menu.fxml");
    }

    public void startAction(ActionEvent event) {
        if (!lightPlayerName.getText().equals("") && !darkPlayerName.getText().equals("") && !lightPlayerName.getText().equals(darkPlayerName.getText())) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/ui.fxml"));
                Parent root = fxmlLoader.load();
                fxmlLoader.<BoardGameController>getController().setLightPlayerName(lightPlayerName.getText());
                fxmlLoader.<BoardGameController>getController().setDarkPlayerName(darkPlayerName.getText());
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
                Logger.info("The game is started.");
            } catch (IOException e) {
                Logger.error("IOException has been occurred!");
                System.exit(1);
            }
        }
        else
            view.nameAlert();
    }

}
