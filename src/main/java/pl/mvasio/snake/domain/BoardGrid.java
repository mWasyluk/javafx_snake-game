package pl.mvasio.snake.domain;

import lombok.NonNull;
import pl.mvasio.snake.suplier.BoardProperties;
import java.util.*;

import static pl.mvasio.snake.suplier.BoardProperties.HORIZONTAL_FIELDS_NUM;
import static pl.mvasio.snake.suplier.BoardProperties.VERTICAL_FIELDS_NUM;

public class BoardGrid {
    private final Set<Position> occupiedPositions = new HashSet<>(HORIZONTAL_FIELDS_NUM * VERTICAL_FIELDS_NUM);

    public boolean isPositionOccupied(@NonNull Position position ) {
        return occupiedPositions.parallelStream().anyMatch((p) -> p.equals(position));
    }

    public boolean isPositionInRange(@NonNull Position position ) {
        int x = position.getPositionX();
        int y = position.getPositionY();
        return ( (x >= 0 && x < HORIZONTAL_FIELDS_NUM) && (y >= 0 && y < VERTICAL_FIELDS_NUM) );
    }

    public void addOccupiedPosition(@NonNull Position position ){
        occupiedPositions.add(position);
    }

    public void setPositionFree(@NonNull Position position ){
        occupiedPositions.remove(position);
    }

    public static Position getRandomInRangePosition(){
        Random random = new Random();
        return Position.of(random.nextInt(BoardProperties.HORIZONTAL_FIELDS_NUM), random.nextInt(BoardProperties.VERTICAL_FIELDS_NUM));
    }
}
