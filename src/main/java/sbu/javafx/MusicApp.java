package sbu.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MusicApp extends Application {

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(MusicApp.class.getResource("App-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Music Player");
        stage.setScene(scene);
        //TODO: add icon to the stage
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}
