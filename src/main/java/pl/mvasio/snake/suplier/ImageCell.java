package pl.mvasio.snake.suplier;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ImageCell extends ImageView {

    public static final ImageCell BODY_BOTTOM_RIGHT = new ImageCell(0, 0);
    public static final ImageCell BODY_TOP_RIGHT = new ImageCell(0, 1);
    public static final ImageCell APPLE = new ImageCell(0, 3);
    public static final ImageCell BODY_HORIZONTAL = new ImageCell(1, 0);
    public static final ImageCell EMPTY = new ImageCell(1, 1);
    public static final ImageCell BODY_BOTTOM_LEFT = new ImageCell(2, 0);
    public static final ImageCell BODY_VERTICAL = new ImageCell(2, 1);
    public static final ImageCell BODY_TOP_LEFT = new ImageCell(2, 2);
    public static final ImageCell HEAD_UP = new ImageCell(3, 0);
    public static final ImageCell HEAD_LEFT = new ImageCell(3, 1);
    public static final ImageCell TAIL_UP = new ImageCell(3, 2);
    public static final ImageCell TAIL_LEFT = new ImageCell(3, 3);
    public static final ImageCell HEAD_RIGHT = new ImageCell(4, 0);
    public static final ImageCell HEAD_DOWN = new ImageCell(4, 1);
    public static final ImageCell TAIL_RIGHT = new ImageCell(4, 2);
    public static final ImageCell TAIL_DOWN = new ImageCell(4, 3);

    private static final int xNumCells = 5;
    private static final int yNumCells = 4;

    public ImageCell(int i, int j) {
        super(new Image(Objects.requireNonNull(ImageCell.class.getResource("/snake.png")).toString()));

        double cellWidth  = getImage().getWidth() / xNumCells;
        double cellHeight  = getImage().getHeight() / yNumCells;
        Rectangle2D rect = new Rectangle2D(
                i * cellWidth, j * cellHeight,
                cellWidth, cellHeight
        );
        setViewport(rect);
    }
}
