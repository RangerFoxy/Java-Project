package boardgame;

import boardgame.controller.WinnerController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.tinylog.Logger;
import java.io.IOException;
import java.util.Optional;

public class Draw {

    public void draw(ActionEvent event, String url) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void nameAlert() {
        Logger.warn("Incorrect name is given by a player!");
        Alert name = new Alert(Alert.AlertType.WARNING);
        name.setTitle("Incorrect name!");
        name.setHeaderText("Incorrect name");
        name.setContentText("Both player need to provide their names and they cannot be the same!");
        name.showAndWait();
    }

    public void exitAlert(ActionEvent event) throws IOException {
        Logger.info("An alert has been occured!");
        Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
        exit.setTitle("Exit to main menu");
        exit.setHeaderText("Are you sure?");
        exit.setContentText("You will return to the main menu.");
        Optional<ButtonType> result = exit.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            Logger.info("The user quit from the game.");
            Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void help() {
        Logger.info("The user opened the help menu.");
        Alert help = new Alert(Alert.AlertType.INFORMATION);
        help.setTitle("The rules of the game");
        help.setHeaderText("Rules");
        help.setContentText("Move your knight to achieve that the opponent cannot move to another tile! The player who made the last valid move will win.");
        help.showAndWait();
    }

    public void victory(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/winner.fxml"));
            Parent root = fxmlLoader.load();
            fxmlLoader.<WinnerController>getController().changeLabel(text);
            Stage stage = new Stage();
            stage.setTitle("Victory");
            stage.setResizable(false);
            stage.getIcons().add(new Image("/images/icon.png"));
            stage.setScene(new Scene(root));
            stage.show();
            Logger.info("Victory scene is opened.");
        } catch (IOException e) {
            Logger.error("IOException has been occured!");
            System.exit(1);
        }

    }

    public ImageView createPiece(String color) {
        var piece = new ImageView();
        piece.setImage(new Image(getClass().getResource("/images/"+color+".png").toExternalForm()));
        return piece;
    }

    public void drawRedSquare(StackPane square) {
        square.getStyleClass().add("red");
    }
}
