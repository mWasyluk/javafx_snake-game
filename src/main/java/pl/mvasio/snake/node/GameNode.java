package pl.mvasio.snake.node;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.NonNull;
import pl.mvasio.snake.domain.Position;
import pl.mvasio.snake.suplier.BoardProperties;

public abstract class GameNode extends ImageView {
    Position position;

    public GameNode(@NonNull ImageView imageView){
        super(imageView.getImage());
        this.setViewport(imageView.getViewport());

        this.position = Position.of(0, 0);
        this.setFitHeight(BoardProperties.FIELD_WIDTH);
        this.setFitWidth(BoardProperties.FIELD_HEIGHT);
    }

    public GameNode(@NonNull Image image){
        super(image);

        this.position = Position.of(0, 0);
        this.setFitHeight(BoardProperties.FIELD_WIDTH);
        this.setFitWidth(BoardProperties.FIELD_HEIGHT);
    }
    public Position getPosition(){
        return position;
    }

    public void setPosition(@NonNull Position position){
        this.position = position;
        Platform.runLater(() -> {
            this.setX(position.getPositionXAsWidth());
            this.setY(position.getPositionYAsHeight());
        });
    }
}
