package pl.mwasyluk.games.snake.node;

import javafx.scene.layout.*;
import javafx.scene.paint.*;

import static pl.mwasyluk.games.snake.suplier.BoardProperties.*;

public class Board extends Pane {
    private static final Background BACKGROUND = new Background(new BackgroundFill(new RadialGradient(
            10,40,40,40,5,true, CycleMethod.REPEAT,
            new Stop(0.1f, Color.rgb(25, 140, 0, .6)),
            new Stop(1.0f, Color.rgb(0, 0, 0, .3))),
            new CornerRadii(5), null));

    public Board (){
        super();
        this.setBackground(BACKGROUND);
        this.setPrefWidth(BOARD_WIDTH);
        this.setPrefHeight(BOARD_HEIGHT);
    }
}
