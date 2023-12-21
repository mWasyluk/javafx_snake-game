package pl.mwasyluk.games.snake.suplier;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovementDirection {
    public static final MovementDirection UP_DIRECTION = new MovementDirection( 0, -1);
    public static final MovementDirection RIGHT_DIRECTION = new MovementDirection( 1, 0);
    public static final MovementDirection DOWN_DIRECTION = new MovementDirection( 0, 1);
    public static final MovementDirection LEFT_DIRECTION = new MovementDirection( -1, 0);

    private int xMovement;
    private int yMovement;
}
