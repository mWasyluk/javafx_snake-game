package pl.mwasyluk.games.snake;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class SnakeApplication extends Application {

    public static void main(String[] arguments){
        Application.launch(arguments);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/board.fxml")));
        stage.setResizable(false);
        stage.setTitle("Snake Game");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/apple-icon.png")).toString()));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }
}
