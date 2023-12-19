package pl.mwasyluk.games.snake;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.scene.input.KeyEvent;
import lombok.Data;
import pl.mwasyluk.games.snake.domain.Apple;
import pl.mwasyluk.games.snake.domain.BoardGrid;
import pl.mwasyluk.games.snake.domain.Position;
import pl.mwasyluk.games.snake.suplier.MovementDirection;
import pl.mwasyluk.games.snake.domain.snake.Snake;
import pl.mwasyluk.games.snake.node.Board;
import pl.mwasyluk.games.snake.node.GameNode;
import pl.mwasyluk.games.snake.node.Goodie;

import java.util.*;

@Data
public class Game {
    private Board board;
    private Snake snake;
    private BoardGrid boardGrid;
    private Goodie goodie;
    private int points;
    private boolean paused = false;

    private long startDelay = 1000;
    private long timerPeriod = 100;
    private long continueDelay = timerPeriod;

    public Game (){
        this.board = new Board();

        startNewGame();
    }

    private void fireEventToController(EventType<Game.GameEvent> eventType){
        board.getParent().fireEvent(new GameEvent(eventType));
    }

    //Method handling events from BoardController like Pop-ups' button pressed etc.
    public <T extends Event> void handleGameEvent(T event) {
        if (event.getEventType().equals(GameEvent.PAUSE) ) {
            pauseGame();
        }
        else if (event.getEventType().equals(GameEvent.RESTART)){
            paused = false;
            timerTask.cancel();
            startNewGame();
        }
    }

    //Method handling key events from the BoardController which are responsible for snake movement.
    public void handleKeyEvent(KeyEvent event){
        switch (event.getCode()){
            case UP: {
                snake.getSnakeMovement().setNextDirection(MovementDirection.UP_DIRECTION);
                break;
            }
            case DOWN: {
                snake.getSnakeMovement().setNextDirection(MovementDirection.DOWN_DIRECTION);
                break;
            }
            case RIGHT: {
                snake.getSnakeMovement().setNextDirection(MovementDirection.RIGHT_DIRECTION);
                break;
            }
            case LEFT: {
                snake.getSnakeMovement().setNextDirection(MovementDirection.LEFT_DIRECTION);
                break;
            }
            case SPACE: {

            }
        }
    }

    private Timer timer;
    private TimerTask timerTask;

    public void startNewGame(){
        //Creates new Snake with provided size and default position (head in the board center, body on the left side)
        this.snake = new Snake(5);
        //Init the BoardGrid responsible for the Nodes position holding and checking
        this.boardGrid = new BoardGrid();
        //Init apple
        this.goodie = new Apple();
        this.points = 0;

        Platform.runLater(()->{
            board.getChildren().clear();
            initSnake();

            // Places the apple in a random place on the board
            setNodePositionToRandomFreeInRange(goodie);
            board.getChildren().add(goodie);
        });

        //Starts game timer and move task
        timer = new Timer(true);
        timerTask = new GameTick();
        timer.schedule(timerTask, startDelay, timerPeriod);
    }

    //Fires an event to the BoardController which informing about change of the point amount
    private void setPointsLabel(){
        fireEventToController(GameEvent.UPDATE_POINTS);
    }

    // Stop the timer, or restart it if it is already stopped
    private void pauseGame(){
        if (paused) {
            timerTask.cancel();
            timerTask = new GameTick();
            timer.schedule(timerTask, continueDelay, timerPeriod);
        }
        paused = !paused;
    }

    // Makes the Snake visible on the board and set its body position as occupied
    private void initSnake(){
        board.getChildren().addAll(snake.getSnakeView());
        for (Position position : snake.getSnakePosition().getWholeSnakePositions())
            boardGrid.addOccupiedPosition(position.clone());
    }

    // Puts a provided node into a random free position in the range BoardGrid and set a previous position as free
    private void setNodePositionToRandomFreeInRange(GameNode gameNode){
        Position randomInRangePosition = BoardGrid.getRandomInRangePosition();
        while (!setNodePosition(gameNode, randomInRangePosition)) {
            randomInRangePosition = BoardGrid.getRandomInRangePosition();
        }
    }

    // Puts a provided node into a provided position and sets a previous position as free.
    // Returns false if a provided position is null, not in the BoardGrid range or already occupied.
    private boolean setNodePosition(GameNode node, Position position){
        if (position != null && boardGrid.isPositionInRange(position) && !boardGrid.isPositionOccupied(position)){
            boardGrid.setPositionFree(node.getPosition());
            node.setPosition(position);
            boardGrid.addOccupiedPosition(position);
            return true;
        }
        return false;
    }

    // Checks if the Snake colliding with itself or the border
    private boolean isColliding(){
        return !boardGrid.isPositionInRange(snake.getSnakePosition().getHeadPosition()) || snake.getSnakePosition().isCollide();
    }

    // Pauses the game timer and fire the event to the BoardController which is informing to show a suitable Pop-up
    private void gameOver(){
        paused = true;
        fireEventToController(GameEvent.GAME_OVER);
    }

    // Defines actions to be performed on each timer's tick
    private class GameTick extends TimerTask{
        @Override
        public void run() {
            if (!paused)
                Platform.runLater(()->{
                    // Previous snake positions view to be shown if the snake will collide with smth
                    List<GameNode> snakeView = new ArrayList<>(snake.getSnakeView());
                    boardGrid.setPositionFree(snake.getSnakePosition().getTailPosition().clone());
                    board.getChildren().removeAll(snakeView);
                    snake.getSnakeMovement().setPrimaryDirection(snake.getSnakeMovement().getNextDirection());
                    snake.moveSnake();
                    // Sets the new snake's head position as occupied ( only new head position is not occupied yet )
                    boardGrid.addOccupiedPosition(snake.getSnakePosition().getHeadPosition().clone());
                    // Checks if the snake ate apple
                    if(snake.getSnakePosition().getHeadPosition().equals(goodie.getPosition())){
                        Position eaten = goodie.getPosition().clone();
                        setNodePositionToRandomFreeInRange(goodie);
                        // Sets the position of the eaten apple as occupied (it is the snake's body part now and was set as free by the previous method)
                        boardGrid.addOccupiedPosition(eaten);
                        int goodiePoints = goodie.getPointAmount();
                        snake.increaseSizeBy(goodiePoints);
                        points += goodiePoints;
                        setPointsLabel();
                        boardGrid.addOccupiedPosition(snake.getSnakePosition().getTailPosition().clone());
                    }
                    // Checks if the snake colliding with smth. If so - finishes the game and shows the previous view of the snake, else shows the new view
                    if (isColliding()) {
                        gameOver();
                        board.getChildren().addAll(snakeView);
                    } else board.getChildren().addAll(snake.getSnakeView());
                });
        }
    }

    public static class GameEvent extends Event{
        public static EventType<GameEvent> PAUSE = new EventType<>("PAUSE");
        public static EventType<GameEvent> RESTART = new EventType<>("RESTART");
        public static EventType<GameEvent> GAME_OVER = new EventType<>("GAME_OVER");
        public static EventType<GameEvent> UPDATE_POINTS = new EventType<>("UPDATE_POINTS");
        public static EventType<GameEvent> HIDE_MENU = new EventType<>("HIDE_MENU");

        public GameEvent(EventType<? extends Event> eventType) {
            super(eventType);
        }
    }
}
