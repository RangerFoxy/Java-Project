package boardgame.controller;

import java.util.List;
import javafx.fxml.FXML;
import org.tinylog.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import boardgame.controller.json.Element;
import boardgame.controller.json.Scoreboard;
import javafx.scene.control.cell.PropertyValueFactory;

public class ScoreboardController {

    private Draw view = new Draw();

    @FXML
    TableView leaderboardTable;

    @FXML
    TableColumn dateColumn;

    @FXML
    TableColumn darkColumn;

    @FXML
    TableColumn winnerColumn;

    @FXML
    TableColumn lightColumn;

    Scoreboard leaderboard = Scoreboard.getInstance();

    @FXML
    public void initialize() {
        List<Element> leaderboardList = leaderboard.getLeaderboard();

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        lightColumn.setCellValueFactory(new PropertyValueFactory<>("lightPlayer"));
        darkColumn.setCellValueFactory(new PropertyValueFactory<>("darkPlayer"));
        winnerColumn.setCellValueFactory(new PropertyValueFactory<>("winner"));

        ObservableList<Element> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(leaderboardList);

        leaderboardTable.setItems(observableResult);
    }

    @FXML
    public void backAction(ActionEvent event) {
        Logger.debug("Back to Main menu.");
        view.draw(event, "/fxml/menu.fxml");
    }

}
