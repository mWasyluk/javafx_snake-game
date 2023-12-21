package pl.mwasyluk.snake.domain;

import org.junit.jupiter.api.Test;
import pl.mwasyluk.games.snake.domain.Position;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void changePositionXBy_ShouldChangePositionX_ForGivenPositiveInteger(){
        Position position = Position.of(2, 5);

        position.changePositionXBy(3);

        assertEquals(5, position.getPositionX());
    }

    @Test
    void changePositionYBy_ShouldChangePositionY_ForGivenPositiveInteger(){
        Position position = Position.of(2, 5);

        position.changePositionYBy(5);

        assertEquals(10, position.getPositionY());
    }

    @Test
    void changePositionXBy_ShouldChangePositionX_ForGivenNegativeInteger(){
        Position position = Position.of(2, 5);

        position.changePositionXBy(-4);

        assertEquals(-2, position.getPositionX());
    }

    @Test
    void changePositionYBy_ShouldChangePositionY_ForGivenNegativeInteger(){
        Position position = Position.of(2, 5);

        position.changePositionYBy(-3);

        assertEquals(2, position.getPositionY());
    }

    @Test
    void changePositionYBy_ShouldNotChangePositionX_ForGivenInteger(){
        Position position = Position.of(2, 5);

        position.changePositionYBy(3);

        assertEquals(2, position.getPositionX());
    }

    @Test
    void changePositionXBy_ShouldNotChangePositionY_ForGivenInteger(){
        Position position = Position.of(2, 5);

        position.changePositionXBy(3);

        assertEquals(5, position.getPositionY());
    }

    @Test
    void changePositionBy_ShouldNotChangePosition_ForGiven0(){
        Position position = Position.of(2, 5);

        position.changePositionXBy(0);
        position.changePositionYBy(0);

        assertAll(
                () -> assertEquals(2, position.getPositionX()),
                () -> assertEquals(5, position.getPositionY())
        );
    }

}