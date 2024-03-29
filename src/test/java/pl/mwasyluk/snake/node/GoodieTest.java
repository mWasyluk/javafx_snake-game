package pl.mwasyluk.snake.node;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import pl.mwasyluk.games.snake.domain.Apple;
import pl.mwasyluk.games.snake.node.Goodie;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Goodie object should")
class GoodieTest extends ApplicationTest {
    Goodie goodie;

    @BeforeEach
    void initializeObject(){
        goodie = new Apple();
    }

    @Test
    @DisplayName("return the default point amount for a new object")
    void shouldReturnDefaultPointAmount(){
        assertEquals(1, goodie.getPointAmount());
    }
}