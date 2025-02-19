package com.example.singletonproperties;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
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
    private List<String> files = Arrays.asList("file1.txt", "file2.txt", "file3.txt", "file4.txt", "file5.txt");
    private int currentFileIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        Button button = new Button("Send");
        button.setOnAction(e -> loadFileProgress()); // Fixed method name

        StackPane bar = new StackPane(
                progressBar,
                progressLabel
        );

        VBox box = new VBox(
                bar, button
        );
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);

        root.setCenter(box);

        updateProgressLabel();

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadFileProgress() {
        if (currentFileIndex >= files.size()) {
            System.out.println("All files are loaded!");
            return;
        }

        double progressValue = (currentFileIndex + 1) / (double) files.size();

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(progressBar.progressProperty(), progressValue))
        );

        timeline.setOnFinished(e -> {
            System.out.println("Loaded: " + files.get(currentFileIndex));
            currentFileIndex++;
            updateProgressLabel();
            loadFileProgress();
        });

        timeline.play();
    }

    private void updateProgressLabel() {
        int total = files.size();
        int loaded = currentFileIndex;
        int percentage = (int) ((loaded / (double) total) * 100);

        progressLabel.setText(percentage + "% --- " + loaded + "/" + total);
    }

    public static void main(String[] args) {
        launch();
    }
}
