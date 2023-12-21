package pl.mwasyluk.games.snake.node;

import javafx.scene.layout.Pane;

import static pl.mwasyluk.games.snake.suplier.BoardProperties.*;

public class Board extends Pane {
    public Board (){
        super();
        this.setPrefWidth(BOARD_WIDTH);
        this.setPrefHeight(BOARD_HEIGHT);
    }
}
