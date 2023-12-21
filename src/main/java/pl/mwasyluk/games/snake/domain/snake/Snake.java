package pl.mwasyluk.games.snake.domain.snake;

import lombok.Data;
import pl.mwasyluk.games.snake.node.GameNode;
import pl.mwasyluk.games.snake.node.SnakePartView;
import pl.mwasyluk.games.snake.suplier.MovementDirection;

import java.util.ArrayList;
import java.util.List;

@Data
public class Snake {
    private SnakePosition snakePosition;
    private SnakeMovement snakeMovement;
    private List<GameNode> snakeView;
    private int size;

    public Snake (int size){
        this.snakePosition = SnakePosition.getDefaultSnakePositionBySize(size);
        this.size = size;
        this.snakeView = new ArrayList<>();
        this.snakeMovement = new SnakeMovement();
        this.snakeMovement.setPrimaryDirection(MovementDirection.RIGHT_DIRECTION);
        this.snakeMovement.setNextDirection(MovementDirection.RIGHT_DIRECTION);
        this.setActualSnakeView();
    }

    public void moveSnake(){
        snakePosition.changePositionByDirection(getSnakeMovement().getPrimaryDirection());
        setActualSnakeView();
    }

    private void setActualSnakeView(){
        List<SnakePartView> list = SnakeView.getLisOfSnakePartsView(this);
        snakeView.clear();
        snakeView.addAll(list);
    }

    public void increaseSizeBy(int size){
        this.size += size;
        for (int i = 0; i < size; i++)
            snakePosition.getBodyPositionList().addFirst(getSnakePosition().getTailPosition().clone());
    }
}
