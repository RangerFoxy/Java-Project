package boardgame.controller;

import boardgame.json.ScoreboardElements;
import boardgame.json.Scoreboard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.tinylog.Logger;

import java.util.List;

/**
 *
 */
public class ScoreboardController {

    private Draw view = new Draw();

    @FXML
    TableView<ScoreboardElements> scoreboardTable;

    @FXML
    TableColumn<ScoreboardElements, String> dateColumn;

    @FXML
    TableColumn<ScoreboardElements, String> lightColumn;

    @FXML
    TableColumn<ScoreboardElements, String> darkColumn;

    @FXML
    TableColumn<ScoreboardElements, String> winnerColumn;

    Scoreboard scoreboard = Scoreboard.getInstance();

    public void initialize() {
        List<ScoreboardElements> scoreboardList = scoreboard.getScoreboard();

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateColumn"));
        lightColumn.setCellValueFactory(new PropertyValueFactory<>("lightColumn"));
        darkColumn.setCellValueFactory(new PropertyValueFactory<>("darkColumn"));
        winnerColumn.setCellValueFactory(new PropertyValueFactory<>("winnerColumn"));

        ObservableList<ScoreboardElements> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(scoreboardList);

        scoreboardTable.setItems(observableResult);

    }

    public void backAction(ActionEvent event) {
        Logger.info("The user pushed the Back button and returned to the main menu.");
        view.draw(event, "/fxml/menu.fxml");
    }
}
