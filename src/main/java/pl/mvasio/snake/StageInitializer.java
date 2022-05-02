package pl.mvasio.snake;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import pl.mvasio.snake.SnakeFXApplication.StageReadyEvent;
import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Value("classpath:/board.fxml")
    private Resource boardResource;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(boardResource.getURL());
            Parent parent = fxmlLoader.load();
            Stage stage = event.getStage();
            stage.setResizable(false);
            stage.setTitle("Snake Game");
            stage.getIcons().add(new Image(String.valueOf(StageInitializer.class.getResource("/apple-icon.png"))));
//            stage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}