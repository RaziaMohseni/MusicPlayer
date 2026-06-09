package sbu.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MusicApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MusicApp.class.getResource("App-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Music Player");


        try {

            var iconUrl = MusicApp.class.getResource("/icon.png");
            if (iconUrl != null) {
                stage.getIcons().add(new Image(iconUrl.toExternalForm()));
            } else {
                System.out.println("فایل آیکون در پوشه resources پیدا نشد!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Image icon = new Image(Objects.requireNonNull(MusicApp.class.getResourceAsStream("/icon.png")));
            stage.getIcons().add(icon);
        } catch (Exception e) {
            System.out.println("Icon not found. Please add icon.png to resources.");
        }

        stage.setScene(scene);
        stage.show();
    }
}
