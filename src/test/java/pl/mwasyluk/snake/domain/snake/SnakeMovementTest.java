package pl.mwasyluk.snake.domain.snake;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.mwasyluk.games.snake.domain.snake.SnakeMovement;
import pl.mwasyluk.games.snake.suplier.MovementDirection;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("SnakeMovement object should")
class SnakeMovementTest {
    SnakeMovement snakeMovement;

    @BeforeEach
    void initializeObject(){
        snakeMovement = new SnakeMovement();
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("change the primary direction")
    class ChangePrimaryDirectionTest{

        @Test
        @DisplayName("when the current direction is null")
        void shouldSetUpDirectionWhenCurrentIsNull(){
            //should be null for a new object
            assert snakeMovement.getPrimaryDirection() == null;
            MovementDirection direction = MovementDirection.RIGHT_DIRECTION;

            snakeMovement.setPrimaryDirection(direction);

            assertEquals(MovementDirection.RIGHT_DIRECTION, snakeMovement.getPrimaryDirection());
        }

        Stream<Arguments> providePerpendicularDirections() {
            return Stream.of(
                    Arguments.of(MovementDirection.RIGHT_DIRECTION, MovementDirection.UP_DIRECTION),
                    Arguments.of(MovementDirection.RIGHT_DIRECTION, MovementDirection.DOWN_DIRECTION),
                    Arguments.of(MovementDirection.UP_DIRECTION, MovementDirection.LEFT_DIRECTION),
                    Arguments.of(MovementDirection.DOWN_DIRECTION, MovementDirection.LEFT_DIRECTION)
            );
        }

        @ParameterizedTest
        @DisplayName("when the perpendicular direction is given")
        @MethodSource("providePerpendicularDirections")
        void shouldChangeDirectionWhenPerpendicularDirectionIsGiven(MovementDirection direction1, MovementDirection direction2){
            snakeMovement.setPrimaryDirection(direction1);
            snakeMovement.setPrimaryDirection(direction2);

            assertEquals(direction2, snakeMovement.getPrimaryDirection());
        }


    }

    static Stream<Arguments> provideParallelDirections() {
        return Stream.of(
                Arguments.of(MovementDirection.RIGHT_DIRECTION, MovementDirection.LEFT_DIRECTION),
                Arguments.of(MovementDirection.LEFT_DIRECTION, MovementDirection.RIGHT_DIRECTION),
                Arguments.of(MovementDirection.UP_DIRECTION, MovementDirection.DOWN_DIRECTION),
                Arguments.of(MovementDirection.DOWN_DIRECTION, MovementDirection.UP_DIRECTION)
        );
    }

    @ParameterizedTest
    @DisplayName("not change the primary direction when the parallel direction is given")
    @MethodSource("provideParallelDirections")
    void shouldNotChangeDirectionWhenParallelDirectionIsGiven(MovementDirection direction1, MovementDirection direction2){
        snakeMovement.setPrimaryDirection(direction1);
        snakeMovement.setPrimaryDirection(direction2);

        assertEquals(direction1, snakeMovement.getPrimaryDirection());
    }
}
