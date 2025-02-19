package com.example.singletonproperties;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;

public class HelloApplication extends Application {
    private ProgressBar progressBar = new ProgressBar(0);
    private Label progressLabel = new Label("0% (0/0)");
    private List<String> files = Arrays.asList("file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt", "file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt", "file6.txt");
    private int currentFileIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        Button button = new Button("Send");
        button.setOnAction(e -> {
            currentFileIndex = 0;
            processNextFile();
        });

        StackPane processBarStack = new StackPane(progressBar, progressLabel);
        VBox box = new VBox(processBarStack, button);
        box.setSpacing(20);
        box.setAlignment(Pos.CENTER);

        root.setCenter(box);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void processNextFile() {
        if (currentFileIndex >= files.size()) {
            System.out.println("All files are loaded!");
            return;
        }

        String file = files.get(currentFileIndex);
        double currentProgress = (currentFileIndex + 1) / (double) files.size();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), new KeyValue(progressBar.progressProperty(), currentProgress))
        );

        timeline.setOnFinished(e -> {
            Platform.runLater(() -> {
                updateProgressLabel(currentFileIndex + 1, files.size());
                System.out.println("Processing: " + file);
                sendFile(file);
                currentFileIndex++;
                processNextFile();
            });
        });

        timeline.play();
    }

    private void updateProgressLabel(int loaded, int total) {
        int percentage = (int) (((double) loaded / total) * 100);
        progressLabel.setText(percentage + "% (" + loaded + "/" + total + ")");
    }

    private void sendFile(String file){
        System.out.println("-----" + file);
    }

    public static void main(String[] args) {
        launch();
    }
}
