package sbu.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicController {

    @FXML private Label statusLabel;
    private static final ObservableList<String> playlist = FXCollections.observableArrayList();
    private MediaPlayer mediaPlayer;

    @FXML
    public void handlePlayAction(ActionEvent event) {

        Button clickedButton = (Button) event.getSource();
        String songName = (String) clickedButton.getUserData();
        statusLabel.setText("Now Playing: " + songName);

        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }

        // پیدا کردن فایل آهنگ و پخش آن
        String fileName = mapSongToFileName(songName);
        try {
            // نکته: فایل‌ها باید در پوشه resources باشند
            String path = getClass().getResource("/" + fileName).toExternalForm();
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);

            // فراخوانی متد تنظیم تایمر
            setupTimer();

            mediaPlayer.play();
        } catch (Exception e) {
            statusLabel.setText("خطا در پخش فایل");
            e.printStackTrace();
        }
    }


    private void setupTimer() {
        mediaPlayer.setOnReady(() -> {
            Duration duration = mediaPlayer.getTotalDuration();
            String time = String.format("%d:%02d",
                    (int) duration.toMinutes(),
                    (int) duration.toSeconds() % 60);


            String songName = statusLabel.getText().replace("Now Playing: ", "");
            statusLabel.setText(songName + " (" + time + ")");
        });
    }

    @FXML
    public void handleAddAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String songName = (String) clickedButton.getUserData();

        if (songName != null) {
            playlist.add(songName);
            statusLabel.setText("وضعیت: " + songName + " به لیست اضافه شد.");
        }
    }

    @FXML
    public void openPlaylistWindow(ActionEvent event) {
        Stage playlistStage = new Stage();
        ListView<String> listView = new ListView<>(playlist);
        listView.setStyle("-fx-background-color: #1e1e2e; -fx-control-inner-background: #1e1e2e; -fx-text-fill: white;");

        VBox root = new VBox(listView);
        root.setStyle("-fx-background-color: #1e1e2e; -fx-padding: 10;");

        playlistStage.setScene(new Scene(root, 300, 400));
        playlistStage.setTitle("لیست پخش من");
        playlistStage.show();
    }

    private String mapSongToFileName(String songName) {
        if (songName == null) return "mp3.mp3";
        if (songName.contains("اول")) return "mp3.mp3";
        if (songName.contains("دوم")) return "mp4.mp3";
        return "mp5.mp3";
    }
}