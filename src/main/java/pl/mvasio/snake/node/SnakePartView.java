package pl.mvasio.snake.node;

import javafx.scene.image.ImageView;
import lombok.NonNull;
import pl.mvasio.snake.domain.Position;

public class SnakePartView extends GameNode {
    public SnakePartView(@NonNull ImageView imageView, @NonNull Position position) {
        super(imageView);
        this.setPosition(position);
    }
}
