package boardgame;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import javax.imageio.IIOException;
import javax.swing.*;
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

    public void alert(ActionEvent event) throws IOException {
        Alert exit = new Alert(Alert.AlertType.CONFIRMATION);
        exit.setTitle("Exit to main menu");
        exit.setHeaderText("Are you sure?");
        exit.setContentText("You will return to the main menu.");
        Optional<ButtonType> result = exit.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void help() {
        Alert help = new Alert(Alert.AlertType.INFORMATION);
        help.setTitle("The rules of the game");
        help.setHeaderText("Rules");
        help.setContentText("Move your knight to achieve that the opponent cannot move to another tile!");
        help.showAndWait();
    }

    public void victory() {

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
