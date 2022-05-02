package pl.mvasio.snake.node;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.NonNull;

public abstract class Goodie extends GameNode {

    public Goodie(@NonNull ImageView imageView) {
        super(imageView);
    }

    public Goodie(@NonNull Image image) {
        super(image);
    }

    abstract public int getPointAmount();
}
