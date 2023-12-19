package pl.mwasyluk.games.snake.domain.snake;

import lombok.Data;
import lombok.NonNull;
import pl.mwasyluk.games.snake.domain.Position;
import pl.mwasyluk.games.snake.suplier.MovementDirection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static pl.mwasyluk.games.snake.suplier.BoardProperties.HORIZONTAL_FIELDS_NUM;
import static pl.mwasyluk.games.snake.suplier.BoardProperties.VERTICAL_FIELDS_NUM;

@Data
public class SnakePosition {
    private Position headPosition;
    private Position tailPosition;
    private Deque<Position> bodyPositionList;
    private boolean collide = false;

    // Creates a SnakePosition object where the head of the Snake is positioned in the middle of the board,
    // the tail on the left side from it and the body between both.
    // The size have to be greater than 2 and lower equal than half of the number of the board's horizontal fields.
    public static SnakePosition getDefaultSnakePositionBySize (int size) {
        var pos = new SnakePosition();
        int maxSize = (HORIZONTAL_FIELDS_NUM / 2);
        if ( size > 2 && size <= maxSize ) {
            int headX = HORIZONTAL_FIELDS_NUM / 2;
            int headY = VERTICAL_FIELDS_NUM / 2;
            pos.headPosition = Position.of(headX, headY);
            pos.tailPosition = Position.of(headX - (size - 1), headY);
            pos.bodyPositionList = new LinkedList<>();
            for (int i = pos.tailPosition.getPositionX() + 1; i < headX; i++) {
                pos.bodyPositionList.add(Position.of(i, headY));
            }
        } else {
            String mess = String.format("Snake size can not be lower than 3 and greater than %d.", maxSize);
            throw new IllegalArgumentException(mess);
        }
        return pos;
    }
    // Changes the snake's body position moving it in a specific direction provided as a MovementDirection object.
    public void changePositionByDirection(@NonNull MovementDirection direction){
        tailPosition = bodyPositionList.removeFirst().clone();
        bodyPositionList.addLast(headPosition.clone());
        headPosition.changePositionXBy(direction.getXMovement());
        headPosition.changePositionYBy(direction.getYMovement());
        setCollide(bodyPositionList.stream().anyMatch(e -> e.equals(headPosition)) || tailPosition.equals(headPosition));
    }

    public List<Position> getWholeSnakePositions(){
        List<Position> list = new LinkedList<>();
        list.add(tailPosition);
        list.addAll(bodyPositionList);
        list.add(headPosition);
        return list;
    }
}
