package pl.mvasio.snake.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.mvasio.snake.suplier.BoardProperties;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardGridPositionTest {
    static BoardGrid boardGrid;
    static int lowestX, lowestY, highestX, highestY;

    @BeforeAll
    static void initializeBoundaryValues(){
        lowestX = 0;
        lowestY = 0;
        highestX = BoardProperties.HORIZONTAL_FIELDS_NUM - 1;
        highestY = BoardProperties.VERTICAL_FIELDS_NUM - 1;
    }

    @BeforeEach
    void initializeBoardGrid(){
        boardGrid = new BoardGrid();
    }

    static Stream<Arguments> getCorrectPositions(){
        return Stream.of(
                Arguments.of(Position.of(lowestX, lowestY)),
                Arguments.of(Position.of(lowestX + 1, lowestY)),
                Arguments.of(Position.of(lowestX, lowestY + 1)),
                Arguments.of(Position.of(highestX, highestY)),
                Arguments.of(Position.of(highestX - 1, highestY)),
                Arguments.of(Position.of(highestX, highestY - 1))
        );
    }

    static Stream<Arguments> getIncorrectPositions(){
        return Stream.of(
                Arguments.of(Position.of(lowestX - 1, lowestY - 1)),
                Arguments.of(Position.of(lowestX, lowestY - 1)),
                Arguments.of(Position.of(lowestX - 1, lowestY)),
                Arguments.of(Position.of(highestX + 1, highestY + 1)),
                Arguments.of(Position.of(highestX, highestY + 1)),
                Arguments.of(Position.of(highestX + 1, highestY))
        );
    }

    @ParameterizedTest
    @MethodSource("getCorrectPositions")
    void isPositionOccupied_ShouldReturnTrue_ForOccupiedPosition(Position position){
        boardGrid.addOccupiedPosition(position);

        assertTrue(boardGrid.isPositionOccupied(position.clone()));
    }

    @ParameterizedTest
    @MethodSource("getCorrectPositions")
    void isPositionOccupied_ShouldReturnFalse_ForNotOccupiedPosition(Position position){
        assertFalse(boardGrid.isPositionOccupied(position));
    }

    @ParameterizedTest
    @MethodSource("getCorrectPositions")
    void isPositionInRange_ShouldReturnTrue_ForInBoardRangePosition(Position position){
        assertTrue(boardGrid.isPositionInRange(position));
    }

    @ParameterizedTest
    @MethodSource("getIncorrectPositions")
    void isPositionInRange_ShouldReturnFalse_ForOutOfBoardRangePosition(Position position){
        assertFalse(boardGrid.isPositionInRange(position));
    }

    @ParameterizedTest
    @MethodSource("getCorrectPositions")
    void addOccupiedPosition_ShouldSetPositionAsOccupied_ForNotOccupiedPositionGiven(Position position){
        boardGrid.addOccupiedPosition(position);

        assertTrue(boardGrid.isPositionOccupied(position.clone()));
    }

    @ParameterizedTest
    @MethodSource("getCorrectPositions")
    void addOccupiedPosition_ShouldSetPositionAsOccupied_ForMoreThanOneNotOccupiedPositionsGiven(Position position){
        Position position2 = Position.of(2,4);

        boardGrid.addOccupiedPosition(position);
        boardGrid.addOccupiedPosition(position2);

        assertTrue(boardGrid.isPositionOccupied(position.clone()) && boardGrid.isPositionOccupied(position2.clone()));
    }
}