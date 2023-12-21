package pl.mwasyluk.games.snake.domain.snake;

import lombok.Data;
import lombok.NonNull;
import pl.mwasyluk.games.snake.suplier.MovementDirection;

@Data
public class SnakeMovement {
    private MovementDirection primaryDirection;
    private MovementDirection nextDirection;

    public void setPrimaryDirection(@NonNull MovementDirection primaryDirection) {
        if ( this.primaryDirection != null) {
                if ( (this.primaryDirection.equals(MovementDirection.RIGHT_DIRECTION) || this.primaryDirection.equals(MovementDirection.LEFT_DIRECTION)) &&
                        (primaryDirection == MovementDirection.LEFT_DIRECTION || primaryDirection.equals(MovementDirection.RIGHT_DIRECTION)) ) return;
                else if ( (this.primaryDirection.equals(MovementDirection.UP_DIRECTION) || this.primaryDirection.equals(MovementDirection.DOWN_DIRECTION)) &&
                        (primaryDirection.equals(MovementDirection.UP_DIRECTION) || primaryDirection.equals(MovementDirection.DOWN_DIRECTION)) ) return;
                else this.primaryDirection = primaryDirection;
            }
        else this.primaryDirection = primaryDirection;
    }
}
