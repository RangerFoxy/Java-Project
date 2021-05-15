package boardgame.controller;

import boardgame.Draw;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;
import java.io.IOException;

public class PlayerSelectController {

    private Draw view = new Draw();

    @FXML
    private TextField lightPlayerName;

    @FXML
    private TextField darkPlayerName;

    @FXML
    public void backAction(ActionEvent event) {
        Logger.info("The user pushed the Back button and returned to the main menu.");
        view.draw(event, "/fxml/menu.fxml");
    }

    @FXML
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
                Logger.error("IOException has been occured!");
                System.exit(1);
            }
        }
        else
            view.nameAlert();
    }

}
