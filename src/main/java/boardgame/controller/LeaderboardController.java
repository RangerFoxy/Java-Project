package boardgame.controller;

import boardgame.json.Leaderboard;
import boardgame.json.LeaderboardElement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.tinylog.Logger;
import java.util.List;

public class LeaderboardController {

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

    Leaderboard leaderboard = Leaderboard.getInstance();

    public void initialize() {
        List<LeaderboardElement> leaderboardList = leaderboard.getLeaderboard();

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        lightColumn.setCellValueFactory(new PropertyValueFactory<>("lightPlayer"));
        darkColumn.setCellValueFactory(new PropertyValueFactory<>("darkPlayer"));
        winnerColumn.setCellValueFactory(new PropertyValueFactory<>("winner"));

        ObservableList<LeaderboardElement> observableResult = FXCollections.observableArrayList();
        observableResult.addAll(leaderboardList);

        leaderboardTable.setItems(observableResult);
    }

    public void backAction(ActionEvent event) {
        Logger.debug("Back to Main menu.");
        view.draw(event, "/fxml/menu.fxml");
    }
}
