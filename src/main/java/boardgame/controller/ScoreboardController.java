package boardgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import javax.swing.text.TableView;

/**
 *
 */
public class ScoreboardController {

    @FXML
    TableView scoreboardTable;

    @FXML
    TableColumn dateColumn;

    @FXML
    TableColumn lightColumn;

    @FXML
    TableColumn darkColumn;

    @FXML
    TableColumn winnerColumn;



    public void backAction(ActionEvent event) {

    }
}
