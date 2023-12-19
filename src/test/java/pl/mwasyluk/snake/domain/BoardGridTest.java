package pl.mwasyluk.snake.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pl.mwasyluk.games.snake.domain.BoardGrid;
import pl.mwasyluk.games.snake.domain.Position;
import pl.mwasyluk.games.snake.suplier.BoardProperties;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BoardGrid object should")
class BoardGridTest {
    static BoardGrid boardGrid;

    @BeforeEach
    void initializeBoardGrid(){
        boardGrid = new BoardGrid();
    }

    @Nested
    @DisplayName("set the position as")
    class SetPositionTest{

        @Nested
        @DisplayName("occupied")
        class OccupiedTest{

            @Test
            @DisplayName("when the correct position is given")
            void shouldAddOccupiedPosition(){
                Position position = new Position(2, 5);

                boardGrid.addOccupiedPosition(position);

                assertTrue(boardGrid.isPositionOccupied(position.clone()));
            }

            @Test
            @DisplayName("when other position is already occupied")
            void shouldBeAbleToAddMoreThanOneOccupiedPosition(){
                Position position1 = Position.of(3,2);
                Position position2 = Position.of(2,4);

                boardGrid.addOccupiedPosition(position1);
                boardGrid.addOccupiedPosition(position2);

                assertAll(
                        ()-> assertTrue(boardGrid.isPositionOccupied(position1.clone())),
                        ()-> assertTrue(boardGrid.isPositionOccupied(position2.clone()))
                );
            }
        }

        @Nested
        @DisplayName("free")
        class FreeTest{
            @Test
            @DisplayName("when it is currently occupied")
            void shouldSetPositionFree(){
                Position position = new Position(2,5);

                boardGrid.addOccupiedPosition(position);
                boardGrid.setPositionFree(position.clone());

                assertFalse(boardGrid.isPositionOccupied(position.clone()));
            }
        }
    }

    @Nested
    @DisplayName("check if the position is occupied and return")
    class IsPositionOccupiedTest{
        @Test
        @DisplayName("true when the position is occupied")
        void shouldReturnTrueForOccupiedPosition(){
            Position position = new Position(2,6);

            boardGrid.addOccupiedPosition(position);

            assertTrue(boardGrid.isPositionOccupied(position.clone()));
        }

        @Test
        @DisplayName("false when position is not occupied yet")
        void shouldReturnFalseForFreePosition(){
            Position position = new Position(3, 5);

            boardGrid.addOccupiedPosition(position);

            assertFalse(boardGrid.isPositionOccupied(new Position(2, 3)));
        }
    }

    @Nested
    @DisplayName("check if the position is in range and return")
    class IsPositionInRangeTest{

        @Nested
        @DisplayName("true")
        class TrueTest{

            @Test
            @DisplayName("when x and y of the position are greater/equal to 0")
            void shouldReturnTrueWhenXYGreaterEqualTo0(){
                Position position1 = new Position(0, 0);
                Position position2 = new Position(2, 0);
                Position position3 = new Position(0, 2);

                assertAll(
                        ()-> assertTrue(boardGrid.isPositionInRange(position1)),
                        ()-> assertTrue(boardGrid.isPositionInRange(position2)),
                        ()-> assertTrue(boardGrid.isPositionInRange(position3))
                );
            }

            @Test
            @DisplayName("when x and y of the position are lower than the number of the board fields")
            void shouldReturnTrueWhenXYLowerEqualToBoardFieldsNumber(){
                int highestX = BoardProperties.HORIZONTAL_FIELDS_NUM - 1;
                int highestY = BoardProperties.VERTICAL_FIELDS_NUM - 1;

                Position position1 = new Position(highestX, highestY);
                Position position2 = new Position(highestX, 2);
                Position position3 = new Position(2, highestY);

                assertAll(
                        ()-> assertTrue(boardGrid.isPositionInRange(position1)),
                        ()-> assertTrue(boardGrid.isPositionInRange(position2)),
                        ()-> assertTrue(boardGrid.isPositionInRange(position3))
                );
            }
        }

        @Nested
        @DisplayName("false")
        class FalseTest{
            @Test
            @DisplayName("when x or y of the position are lower than 0")
            void shouldReturnFalseWhenXYLowerThan0(){
                Position position1 = new Position(-1, -1);
                Position position2 = new Position(-1, 0);
                Position position3 = new Position(0, -1);

                assertAll(
                        ()-> assertFalse(boardGrid.isPositionInRange(position1)),
                        ()-> assertFalse(boardGrid.isPositionInRange(position2)),
                        ()-> assertFalse(boardGrid.isPositionInRange(position3))
                );
            }

            @Test
            @DisplayName("when x or y of the position are greater/equal to the number of the board fields")
            void shouldReturnTrueWhenXYLowerEqualToBoardFieldsNumber(){
                int equalX = BoardProperties.HORIZONTAL_FIELDS_NUM;
                int equalY = BoardProperties.VERTICAL_FIELDS_NUM;

                Position position1 = new Position(equalX, equalY);
                Position position2 = new Position(equalX, 2);
                Position position3 = new Position(2, equalY);

                assertAll(
                        ()-> assertFalse(boardGrid.isPositionInRange(position1)),
                        ()-> assertFalse(boardGrid.isPositionInRange(position2)),
                        ()-> assertFalse(boardGrid.isPositionInRange(position3))
                );
            }
        }
    }
}