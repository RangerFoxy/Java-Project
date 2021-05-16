package boardgame.controller;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import boardgame.json.Scoreboard;
import boardgame.json.Element;
import boardgame.model.Player;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.tinylog.Logger;
import boardgame.model.BoardGameModel;
import boardgame.model.KnightDirection;
import boardgame.model.Position;

public class BoardGameController {

    private enum SelectionPhase {
        SELECT_FROM,
        SELECT_TO;

        public SelectionPhase alter() {
            return switch (this) {
                case SELECT_FROM -> SELECT_TO;
                case SELECT_TO -> SELECT_FROM;
            };
        }
    }

    private SelectionPhase selectionPhase = SelectionPhase.SELECT_FROM;

    private List<Position> selectablePositions = new ArrayList<>();

    private Position selected;

    private BoardGameModel model = new BoardGameModel();

    private Draw view = new Draw();

    private Scoreboard leaderboard = Scoreboard.getInstance();

    @FXML
    private Label lightPlayerName;

    @FXML
    private Label darkPlayerName;

    @FXML
    public void setLightPlayerName(String name) {
        lightPlayerName.setText(name);
    }

    @FXML
    public void setDarkPlayerName(String name) {
        darkPlayerName.setText(name);
    }

    @FXML
    private GridPane board;

    @FXML
    private void initialize() {
        createBoard();
        createPieces();
        setSelectablePositions();
        showSelectablePositions();
    }

    private void createBoard() {
        for (int i = 0; i < board.getRowCount(); i++) {
            for (int j = 0; j < board.getColumnCount(); j++) {
                var square = createSquare(i, j);
                board.add(square, j, i);
            }
        }
        Logger.debug("The board has been created.");
    }

    private StackPane createSquare(int i, int j) {
        var square = new StackPane();
        square.getStyleClass().add("square");
        square.setOnMouseClicked(this::handleMouseClick);
        if ((i + j) % 2 == 0)
            square.getStyleClass().add("light");
        else
            square.getStyleClass().add("dark");
        return square;
    }

    private void createPieces() {
        for (int i = 0; i < model.getPieceCount(); i++) {
            model.positionProperty(i).addListener(this::piecePositionChange);
            var piece = view.createPiece(model.getPieceType(i).name());
            getSquare(model.getPiecePosition(i)).getChildren().add(piece);
        }
        Logger.debug("Pieces have been created.");
    }

    @FXML
    private void handleMouseClick(MouseEvent event) {
        var square = (StackPane) event.getSource();
        var row = GridPane.getRowIndex(square);
        var col = GridPane.getColumnIndex(square);
        var position = new Position(row, col);
        Logger.debug("Click on square {}", position);
        handleClickOnSquare(position);
    }

    private void handleClickOnSquare(Position position) {
        switch (selectionPhase) {
            case SELECT_FROM -> {
                if (selectablePositions.contains(position)) {
                    selectPosition(position);
                    alterSelectionPhase();
                }
            }
            case SELECT_TO -> {
                if (selectablePositions.contains(position)) {
                    var pieceNumber = model.getPieceNumber(selected).getAsInt();
                    var direction = KnightDirection.of(position.row() - selected.row(), position.col() - selected.col());
                    Logger.debug("Moving piece {} {}", pieceNumber, direction);
                    model.move(pieceNumber, direction);
                    deselectSelectedPosition();
                    alterSelectionPhase();
                }
            }
        }
    }

    private void alterSelectionPhase() {
        selectionPhase = selectionPhase.alter();
        hideSelectablePositions();
        setSelectablePositions();
        showSelectablePositions();
    }

    private void selectPosition(Position position) {
        selected = position;
        showSelectedPosition();
    }

    private void showSelectedPosition() {
        var square = getSquare(selected);
        square.getStyleClass().add("selected");
    }

    private void deselectSelectedPosition() {
        hideSelectedPosition();
        selected = null;
    }

    private void hideSelectedPosition() {
        var square = getSquare(selected);
        square.getStyleClass().remove("selected");
    }

    private void setSelectablePositions() {
        selectablePositions.clear();
        switch (selectionPhase) {
            case SELECT_FROM -> selectablePositions.add(model.getPiecePosition(model.getPlayer()));
            case SELECT_TO -> {
                var pieceNumber = model.getPieceNumber(selected).getAsInt();
                for (var direction : model.getValidMoves(pieceNumber)) {
                    selectablePositions.add(selected.moveTo(direction));
                }
            }
        }
    }

    private void showSelectablePositions() {
        for (var selectablePosition : selectablePositions) {
            var square = getSquare(selectablePosition);
            square.getStyleClass().add("selectable");
        }
    }

    private void hideSelectablePositions() {
        for (var selectablePosition : selectablePositions) {
            var square = getSquare(selectablePosition);
            square.getStyleClass().remove("selectable");
        }
    }

    private StackPane getSquare(Position position) {
        for (var child : board.getChildren()) {
            if (GridPane.getRowIndex(child) == position.row() && GridPane.getColumnIndex(child) == position.col()) {
                return (StackPane) child;
            }
        }
        throw new AssertionError();
    }

    private void piecePositionChange(ObservableValue<? extends Position> observable, Position oldPosition, Position newPosition) {
        Logger.debug("Move: {} -> {}", oldPosition, newPosition);
        StackPane oldSquare = getSquare(oldPosition);
        view.drawRedSquare(oldSquare);
        StackPane newSquare = getSquare(newPosition);
        newSquare.getChildren().addAll(oldSquare.getChildren());
        oldSquare.getChildren().clear();
        endGame();
    }

    private void saveToScoreboard(String winner) {
        Element result = Element.builder()
                .date(ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd. - HH:mm:ss")))
                .lightPlayer(lightPlayerName.getText())
                .darkPlayer(darkPlayerName.getText())
                .winner(winner)
                .build();
        Logger.debug("Results of the game is saved to the leaderboard.");

        leaderboard.saveLeaderboardElement(result);
        Logger.info("The Scoreboard is updated.");
    }

    private void endGame() {
        if (model.winner()) {
            Stage stage = (Stage) lightPlayerName.getScene().getWindow();
            if (model.isPlayer() == Player.LIGHT) {
                Logger.info("Light side won!");
                saveToScoreboard(lightPlayerName.getText());
                stage.close();
                view.victory(lightPlayerName.getText()+" won!");
            }
            else {
                Logger.info("Dark side won!");
                saveToScoreboard(darkPlayerName.getText());
                stage.close();
                view.victory(darkPlayerName.getText()+" won!");
            }
        }
    }

    @FXML
    private void closeAction(ActionEvent event) throws IOException {
        view.exitAlert(event);
    }

    @FXML
    private void rulesAction() {
        view.help();
    }

}
