package pl.mvasio.snake.node;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import pl.mvasio.snake.domain.Apple;
import pl.mvasio.snake.domain.Position;
import pl.mvasio.snake.suplier.BoardProperties;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("GameNode object should")
class GameNodeTest extends ApplicationTest {
    GameNode gameNode;

    @BeforeEach
    void initializeGameNodeByAppleClass(){
        gameNode = new Apple();
    }

    @Test
    @DisplayName("set the initial position as (0,0)")
    void shouldSetInitialPosition() {
        assertEquals(Position.of(0, 0), gameNode.getPosition());
    }

    @Test
    @DisplayName("return the actual position of the node")
    void shouldReturnActualPosition() {
        Position position = Position.of(5, 6);
        gameNode.setPosition(position);
        assertEquals(position.clone(), gameNode.getPosition());
    }

    @Test
    @DisplayName("change the position to the given one")
    void shouldChangePositionToGiven() {
        Position position = new Position(4, 2);

        gameNode.setPosition(position);

        assertAll(
                ()-> assertEquals(Position.of(4, 2), gameNode.getPosition()),
                ()-> assertEquals(position.getPositionX() * BoardProperties.FIELD_WIDTH, gameNode.getX()),
                ()-> assertEquals(position.getPositionY() * BoardProperties.FIELD_HEIGHT, gameNode.getY())
        );
    }
}