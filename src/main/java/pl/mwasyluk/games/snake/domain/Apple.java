package pl.mwasyluk.games.snake.domain;

import javafx.scene.image.ImageView;
import pl.mwasyluk.games.snake.node.Goodie;
import pl.mwasyluk.games.snake.suplier.ImageCell;

public class Apple extends Goodie {
    static private final ImageView appleImageView = ImageCell.APPLE;
    private final int pointAmount;

    public Apple (){
        super(appleImageView.getImage());
        this.setViewport(appleImageView.getViewport());
        this.pointAmount = 1;
    }

    public int getPointAmount() {
        return pointAmount;
    }
}
