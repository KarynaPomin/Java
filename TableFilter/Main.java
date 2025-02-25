package com.example.searchtableview;

import Classes.Person;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        ObservableList<Person> personList = FXCollections.observableArrayList(
                new Person("Alice", "Smith", 25),
                new Person("Bob", "Johnson", 30),
                new Person("Charlie", "Brown", 35),
                new Person("Diana", "Williams", 28)
        );

        TableView<Person> tableView = new TableView<>();
        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");
        ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());

        tableView.getColumns().addAll(firstNameCol, lastNameCol, ageCol);
        tableView.setItems(personList);

        TextField searchField = new TextField();
        searchField.setPromptText("Search...");

        ComboBox<String> columnSelectorComboBox = new ComboBox<>();
        columnSelectorComboBox.getItems().addAll("First Name", "Last Name", "Age");
        columnSelectorComboBox.setPromptText("Select Column");

        /// ///
        FilteredList<Person> filteredPersonList = new FilteredList<>(personList, p -> true);
        tableView.setItems(filteredPersonList);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            String selectedColumn = columnSelectorComboBox.getValue();

            filteredPersonList.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) return true;

                String lowerCaseFilter = newValue.toLowerCase();

                if (selectedColumn == null) { 
                    return person.getFirstName().toLowerCase().contains(lowerCaseFilter)
                            || person.getLastName().toLowerCase().contains(lowerCaseFilter)
                            || String.valueOf(person.getAge()).contains(lowerCaseFilter);
                } else {
                    switch (selectedColumn) {
                        case "First Name":
                            return person.getFirstName().toLowerCase().contains(lowerCaseFilter);
                        case "Last Name":
                            return person.getLastName().toLowerCase().contains(lowerCaseFilter);
                        case "Age":
                            return String.valueOf(person.getAge()).contains(lowerCaseFilter);
                    }
                }
                return false;
            });
        });

        VBox root = new VBox(10, searchField, columnSelectorComboBox, tableView);
        root.setPadding(new Insets(10));
        
        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add("C:\\JavaFX\\SearchTableVIew\\src\\main\\resources\\style.css");
        stage.setScene(scene);
        stage.setTitle("Searchable TableView");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
