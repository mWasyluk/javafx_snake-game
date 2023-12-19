package pl.mwasyluk.games.snake.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.mwasyluk.games.snake.suplier.BoardProperties;

@Data
@AllArgsConstructor
public class Position implements Cloneable {
    private int positionX;
    private int positionY;

    public static Position of(int x, int y){
        return new Position(x, y);
    }

    public int getPositionXAsWidth() {
        return positionX * BoardProperties.FIELD_WIDTH;
    }
    public int getPositionYAsHeight() {
        return positionY * BoardProperties.FIELD_HEIGHT;
    }

    public void changePositionXBy(int relX){
        this.positionX += relX;
    }
    public void changePositionYBy(int relY){
        this.positionY += relY;
    }

    @Override
    public Position clone() {
        try {
            return (Position) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning is not supported for this object.");
        }
    }
}
