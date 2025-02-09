package com.example.table_fx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TableViewExample extends Application {
    private TableView<FileEntry> tableView;
    private ObservableList<FileEntry> fileList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button chooseFolderButton = new Button("Choose Folder");
        tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        fileList = FXCollections.observableArrayList();

        TableColumn<FileEntry, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());

        TableColumn<FileEntry, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<FileEntry, Button> removeColumn = new TableColumn<>("Remove");
        removeColumn.setCellValueFactory(cellData -> cellData.getValue().buttonProperty());

        tableView.getColumns().addAll(idColumn, nameColumn, removeColumn);
        tableView.setItems(fileList);

        chooseFolderButton.setOnAction(e -> chooseFolder(primaryStage));

        VBox vbox = new VBox(10, chooseFolderButton, tableView);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPrefSize(500, 400);

        primaryStage.setScene(new Scene(vbox));
        primaryStage.setTitle("TableView Example");
        primaryStage.show();
    }

    private void chooseFolder(Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            loadFiles(selectedDirectory);
        }
    }

    private void loadFiles(File directory) {
        List<File> txtFiles = Arrays.stream(directory.listFiles())
                .filter(file -> file.isFile() && file.getName().endsWith(".txt"))
                .collect(Collectors.toList());

        fileList.clear();
        for (int i = 0; i < txtFiles.size(); i++) {
            fileList.add(new FileEntry(i + 1, txtFiles.get(i).getName()));
        }
    }

    public class FileEntry {
        private final javafx.beans.property.IntegerProperty id;
        private final javafx.beans.property.StringProperty name;
        private final javafx.beans.property.ObjectProperty<Button> button;

        public FileEntry(int id, String name) {
            this.id = new javafx.beans.property.SimpleIntegerProperty(id);
            this.name = new javafx.beans.property.SimpleStringProperty(name);
            this.button = new javafx.beans.property.SimpleObjectProperty<>(new Button("Remove"));

            this.button.get().setOnAction(e -> {
                fileList.remove(this);
                updateIds();
            });
        }

        public javafx.beans.property.IntegerProperty idProperty() { return id; }
        public javafx.beans.property.StringProperty nameProperty() { return name; }
        public javafx.beans.property.ObjectProperty<Button> buttonProperty() { return button; }

        private void updateIds() {
            for (int i = 0; i < fileList.size(); i++) {
                fileList.get(i).id.set(i + 1);
            }
        }
    }
}
