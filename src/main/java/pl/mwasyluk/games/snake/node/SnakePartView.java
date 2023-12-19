package pl.mwasyluk.games.snake.node;

import javafx.scene.image.ImageView;
import lombok.NonNull;
import pl.mwasyluk.games.snake.domain.Position;

public class SnakePartView extends GameNode {
    public SnakePartView(@NonNull ImageView imageView, @NonNull Position position) {
        super(imageView);
        this.setPosition(position);
    }
}
