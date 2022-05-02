package pl.mvasio.snake.domain;

import javafx.scene.image.ImageView;
import pl.mvasio.snake.node.Goodie;
import pl.mvasio.snake.suplier.ImageCell;

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
