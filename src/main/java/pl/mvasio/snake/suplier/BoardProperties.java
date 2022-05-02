package pl.mvasio.snake.suplier;

public interface BoardProperties {
    int BOARD_WIDTH = 800;
    int BOARD_HEIGHT = 800;
    int HORIZONTAL_FIELDS_NUM = 25;
    int VERTICAL_FIELDS_NUM = 25;
    int FIELD_WIDTH = BOARD_WIDTH/HORIZONTAL_FIELDS_NUM;
    int FIELD_HEIGHT = BOARD_HEIGHT / VERTICAL_FIELDS_NUM;

    String SCORE_LABEL_PREFIX = "Wynik: ";
}
