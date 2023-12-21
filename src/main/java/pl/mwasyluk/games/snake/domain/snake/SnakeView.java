package pl.mwasyluk.games.snake.domain.snake;

import lombok.NonNull;
import pl.mwasyluk.games.snake.domain.Position;
import pl.mwasyluk.games.snake.node.SnakePartView;
import pl.mwasyluk.games.snake.suplier.ImageCell;
import java.util.*;

public class SnakeView{
    public static List<SnakePartView> getLisOfSnakePartsView(@NonNull Snake snake){
        List<SnakePartView> snakeViewList = new LinkedList<>();
        List<Position> list = snake.getSnakePosition().getWholeSnakePositions();

        Position tailPosition = list.get(0);
        Position secondPosition = list.get(1);
        if (secondPosition.getPositionX() < tailPosition.getPositionX())
            snakeViewList.add(new SnakePartView(ImageCell.TAIL_LEFT, tailPosition.clone()));
        else if (secondPosition.getPositionX() > tailPosition.getPositionX())
            snakeViewList.add(new SnakePartView(ImageCell.TAIL_RIGHT, tailPosition.clone()));
        else if (secondPosition.getPositionY() > tailPosition.getPositionY())
            snakeViewList.add(new SnakePartView(ImageCell.TAIL_DOWN, tailPosition.clone()));
        else snakeViewList.add(new SnakePartView(ImageCell.TAIL_UP, tailPosition.clone()));


        Position prevPosition = snake.getSnakePosition().getTailPosition();
        Position nextPosition = null;
        int currentlyCheckedPosition = 0;
        for (Position position : list) {
            //skip tail as it is already checked
            if (currentlyCheckedPosition == 0) {
                currentlyCheckedPosition++;
                continue;
            }
            //check head direction if last element is checking
            if (currentlyCheckedPosition == list.size() - 1){
                if (prevPosition.getPositionX() < position.getPositionX())
                    snakeViewList.add(new SnakePartView(ImageCell.HEAD_RIGHT, position.clone()));
                else if (prevPosition.getPositionX() > position.getPositionX())
                    snakeViewList.add(new SnakePartView(ImageCell.HEAD_LEFT, position.clone()));
                else if (prevPosition.getPositionY() < position.getPositionY())
                    snakeViewList.add(new SnakePartView(ImageCell.HEAD_DOWN, position.clone()));
                else snakeViewList.add(new SnakePartView(ImageCell.HEAD_UP, position.clone()));
                break;
            }
            //check body parts direction
            nextPosition = list.get(currentlyCheckedPosition + 1);
            if (prevPosition.getPositionX() == nextPosition.getPositionX())
                snakeViewList.add(new SnakePartView(ImageCell.BODY_VERTICAL, position.clone()));
            else if (prevPosition.getPositionY() == nextPosition.getPositionY())
                snakeViewList.add(new SnakePartView(ImageCell.BODY_HORIZONTAL, position.clone()));

            else if (prevPosition.getPositionX() < position.getPositionX() || nextPosition.getPositionX() < position.getPositionX()){
                if (prevPosition.getPositionY() > position.getPositionY() || nextPosition.getPositionY() > position.getPositionY())
                    snakeViewList.add(new SnakePartView(ImageCell.BODY_BOTTOM_LEFT, position.clone()));
                else snakeViewList.add(new SnakePartView(ImageCell.BODY_TOP_LEFT, position.clone()));
            }
            else {
                if (prevPosition.getPositionY() > position.getPositionY() || nextPosition.getPositionY() > position.getPositionY())
                    snakeViewList.add(new SnakePartView(ImageCell.BODY_BOTTOM_RIGHT, position.clone()));
                else snakeViewList.add(new SnakePartView(ImageCell.BODY_TOP_RIGHT, position.clone()));
            }
            //prepare data for next check
            prevPosition = position;
            currentlyCheckedPosition ++;
        }
        return snakeViewList;
    }
}
