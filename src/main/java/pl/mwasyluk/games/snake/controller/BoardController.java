package pl.mwasyluk.games.snake.controller;

import javafx.application.Platform;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import pl.mwasyluk.games.snake.Game;
import pl.mwasyluk.games.snake.suplier.BoardProperties;

public class BoardController {
    @FXML
    private BorderPane root;
    @FXML
    private Label scoreLabel;
    @FXML
    private Button pauseButton;
    @FXML
    private Pane background;
    @FXML
    private StackPane menuPopup;
    @FXML
    private StackPane gameOverPopup;

    private final Game game = new Game();

    @FXML
    private void initialize() {
        root.setCenter(game.getBoard());
        setScoreLabel(0);
        setPauseButtonEventListener();
        setGameEventFilters();
    }

    private void setGameEventFilters(){
        root.addEventFilter(EventType.ROOT, (event) ->{
            if (event.getEventType().equals(Game.GameEvent.UPDATE_POINTS))
                setScoreLabel(game.getPoints());
            else if (event.getEventType().equals(Game.GameEvent.GAME_OVER)) {
                showGameOverPopup();
                getGameOverButton(0).setOnAction((e)->{
                    setScoreLabel(0);
                    hideGameOverPopup();
                    fireEventToBoard(Game.GameEvent.RESTART);
                });
                getGameOverButton(1).setOnAction((e)->{
                    System.exit(0);
                });
            }
        });
        root.addEventFilter(KeyEvent.KEY_PRESSED, game::handleKeyEvent);
        root.addEventFilter(Game.GameEvent.ANY, game::handleGameEvent);
    }

    private void setPauseButtonEventListener() {
        pauseButton.setOnAction(event -> {
            Platform.runLater(() -> {
                showMenuPopup();
                getMenuButton(0).setOnAction(e -> {
                    hideMenuPopup();
                    fireEventToBoard(Game.GameEvent.PAUSE);
                });
                getMenuButton(1).setOnAction(e -> {
                    setScoreLabel(0);
                    hideMenuPopup();
                    fireEventToBoard(Game.GameEvent.RESTART);
                });
                getMenuButton(2).setOnAction(e -> {
                    System.exit(0);
                });
            });
        });

    }

    private void setScoreLabel(int points) {
        Platform.runLater(() -> {
            scoreLabel.setText(BoardProperties.SCORE_LABEL_PREFIX + points);
        });
    }

    private void fireEventToBoard(EventType<Game.GameEvent> eventType) {
        game.getBoard().fireEvent(new Game.GameEvent(eventType));
    }

    private void showMenuPopup() {
        pauseButton.setFocusTraversable(false);
        background.setVisible(true);
        menuPopup.setVisible(true);
        menuPopup.requestFocus();
        if (!game.isPaused()) game.getBoard().fireEvent(new Game.GameEvent(Game.GameEvent.PAUSE));
    }

    private Button getMenuButton(int index){
        return (Button) ((VBox) menuPopup.getChildren().get(0)).getChildren().get(index);
    }

    private void hideMenuPopup() {
        background.setVisible(false);
        menuPopup.setVisible(false);
        pauseButton.setFocusTraversable(true);
    }

    private void showGameOverPopup() {
        pauseButton.setFocusTraversable(false);
        background.setVisible(true);
        gameOverPopup.setVisible(true);
        gameOverPopup.requestFocus();
    }

    private Button getGameOverButton(int index) {
        return (Button) ((HBox) ((VBox) gameOverPopup.getChildren().get(0)).getChildren().get(1)).getChildren().get(index);
    }

    private void hideGameOverPopup() {
        gameOverPopup.setVisible(false);
        background.setVisible(false);
        pauseButton.setFocusTraversable(true);
    }
}
