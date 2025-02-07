package pl.infobazasolution.expertimportexport;


import Classes.FileManager;
import Classes.FilesItem;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;


public class HelloApplication extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        primaryStage.setTitle("Aplikacja JavaFX");

        // Startujemy od ekranu logowania
        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    private Scene createScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        InputStream stream = null;
        try {
            stream = new FileInputStream("/home/karynap/IdeaProjects/ExpertImportExport/src/images/logo.jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(stream);

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Label title = new Label("Logowanie");
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        Separator separator = new Separator(Orientation.HORIZONTAL);

        TextField loginField = new TextField();
        loginField.setPromptText("Adres e-mail");
        loginField.setPrefWidth(50);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Hasło");
        passwordField.setPrefWidth(50);

        Button loginButton = new Button("Zaloguj się");
        loginButton.getStyleClass().setAll("btn","btn-danger");

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        loginButton.setOnAction(e -> {
//            if (loginField.getText().isBlank() || passwordField.getText().isBlank()) {
//                messageLabel.setText("Pole nie może być puste.");
//            }
//
//            if (loginField.getText().equals("Admin") && passwordField.getText().equals("admin1")) {
//                primaryStage.setScene(secondScene());
//                primaryStage.show();
//            }
//            else {
//                messageLabel.setText("Login lub hasło jest niepoprawne!");
//            }

            primaryStage.setScene(secondScene());
            primaryStage.show();
        });

        VBox formBox = new VBox(
                imageView,
                title,
                separator,
                loginField,
                passwordField,
                loginButton,
                messageLabel
        );
        formBox.setAlignment(Pos.CENTER);
        formBox.setSpacing(10);

        Label footer = new Label("© 2025 Wszystkie prawa zastrzeżone.");
        footer.setPadding(new Insets(10));
        footer.setAlignment(Pos.CENTER);

        root.setCenter(formBox);
        root.setBottom(footer);
        BorderPane.setAlignment(footer, Pos.CENTER);

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }

    private Scene secondScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        InputStream stream = null;
        try {
            stream = new FileInputStream("/home/karynap/IdeaProjects/ExpertImportExport/src/images/logo.jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(stream);

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);

        String userName = "Admin";
        LocalDate data = LocalDate.now();
        Label loginLabel = new Label("Witaj " + userName + " w Systemie. Dziś jest " + data.toString() + ".");
        loginLabel.setWrapText(true);
        loginLabel.setMinWidth(100);

        Button helpButton = new Button("Pomoc");
        helpButton.getStyleClass().setAll("btn","btn-danger");
        helpButton.setOnAction(e -> {
            primaryStage.setScene(fourthScene());
            primaryStage.show();
        });

        Hyperlink signOutButton = new Hyperlink("Sign Out");
        signOutButton.getStyleClass().setAll("btn","btn-danger");
        signOutButton.setWrapText(true);

        signOutButton.setOnAction(e -> {
            primaryStage.setScene(createScene());
            primaryStage.show();
        });

        HBox topBox = new HBox(
                imageView,
                loginLabel,
                helpButton,
                signOutButton
        );
        topBox.setAlignment(Pos.TOP_CENTER);
        topBox.setSpacing(10);

        Label topLabel = new Label("Wypełnij ankietę");
        topLabel.setFont(Font.font(20));

        Separator separator = new Separator(Orientation.HORIZONTAL);

        Label userNameLabel = new Label("Imię: ");
        userNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        TextField userNameField = new TextField();
        VBox nameBox = new VBox(userNameLabel, userNameField);

        Label userSecondnameLabel = new Label("Nazwisko: ");
        userSecondnameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        TextField userSecondnameField = new TextField();
        VBox secondnameBox = new VBox(userSecondnameLabel, userSecondnameField);
        secondnameBox.setSpacing(15);

        Label genderLabel = new Label("Płeć: ");
        genderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        RadioButton genderFemaleRadioButton = new RadioButton("K");
        RadioButton genderMaleRadioButton = new RadioButton("M");
        HBox genderBox = new HBox(genderLabel, genderFemaleRadioButton, genderMaleRadioButton);
        genderBox.setSpacing(15);

        Label dateBirthLabel = new Label("Data urodzenia: ");
        dateBirthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        dateBirthLabel.setWrapText(true);
        dateBirthLabel.setMinWidth(50);

        DatePicker dateBirthField = new DatePicker();

        HBox dateBox = new HBox(dateBirthLabel, dateBirthField);
        dateBox.setSpacing(15);

        Label choiceBoxLabel = new Label("Kolor oczu: ");
        choiceBoxLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("szare", "brązowe", "zielone", "niebieskie");

        HBox colorEyesBox = new HBox(choiceBoxLabel, choiceBox);
        colorEyesBox.setSpacing(15);

        Label hobbyLabel = new Label("Zainteresowania: ");
        hobbyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        CheckBox hobbyCheckBox1 = new CheckBox("Czytanie");
        CheckBox hobbyCheckBox2 = new CheckBox("Programowanie");
        CheckBox hobbyCheckBox3 = new CheckBox("Rysowanie");
        CheckBox hobbyCheckBox4 = new CheckBox("Inne");

        VBox hobbyBox = new VBox(hobbyLabel, hobbyCheckBox1, hobbyCheckBox2, hobbyCheckBox3, hobbyCheckBox4);
        hobbyBox.setSpacing(15);

        VBox formBox = new VBox(
                topLabel,
                separator,
                nameBox,
                secondnameBox,
                dateBox,
                genderBox,
                colorEyesBox,
                hobbyBox
        );
        formBox.setAlignment(Pos.TOP_LEFT);
        formBox.setSpacing(15);

        Button sendButton = new Button("Wyślij");
        sendButton.getStyleClass().setAll("btn","btn-danger");
        sendButton.setAlignment(Pos.CENTER);

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        sendButton.setOnAction(e -> {
//            if (
//                    userNameField.getText().isBlank()
//                    || userSecondnameField.getText().isBlank()
//                    || !(genderFemaleRadioButton.isSelected() || genderMaleRadioButton.isSelected())
//                    || !(hobbyCheckBox1.isSelected() || hobbyCheckBox2.isSelected() || hobbyCheckBox3.isSelected() || hobbyCheckBox4.isSelected())
//            ){
//                messageLabel.setText("Żadne pole nie może być puste.");
//            }
//            else {
//                primaryStage.setScene(thirdScene());
//                primaryStage.show();
//            }

            primaryStage.setScene(thirdScene());
            primaryStage.show();
        });

        HBox bottomBox = new HBox(
                messageLabel,
                sendButton
        );
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(10);

        root.setTop(topBox);
        root.setCenter(formBox);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 500, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }

    private Scene thirdScene() {
        InputStream stream = null;
        try {
            stream = new FileInputStream("/home/karynap/IdeaProjects/ExpertImportExport/src/images/logo.jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(stream);

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);

        String userName = "Admin";
        LocalDate data = LocalDate.now();
        Label loginLabel = new Label("Witaj " + userName + " w Systemie. Dziś jest " + data.toString() + ".");
        loginLabel.setWrapText(true);
        loginLabel.setMinWidth(100);

        Button helpButton = new Button("Pomoc");
        helpButton.getStyleClass().setAll("btn","btn-danger");
        helpButton.setOnAction(e -> {
            primaryStage.setScene(fourthScene());
            primaryStage.show();
        });

        Hyperlink signOutButton = new Hyperlink("Sign Out");
        signOutButton.getStyleClass().setAll("btn","btn-danger");
        signOutButton.setWrapText(true);

        signOutButton.setOnAction(e -> {
            primaryStage.setScene(createScene());
            primaryStage.show();
        });

        HBox topBox = new HBox(
                imageView,
                loginLabel,
                helpButton,
                signOutButton
        );
        topBox.setAlignment(Pos.TOP_CENTER);
        topBox.setSpacing(10);

        FileManager fileManager = new FileManager();

        TableView<FilesItem> tableView = new TableView();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox.setVgrow(tableView, Priority.ALWAYS);

        TableColumn<FilesItem, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setPrefWidth(10);
        TableColumn<FilesItem, String> nameColumn = new TableColumn<>("Nazwa pliku");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("idFile"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameFile"));

        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(nameColumn);

        for (int i = 0; i < 5; i++) {
            Integer id = i + 1;
            String fileName = "file" + id;
            FilesItem newFile = new FilesItem(id, fileName);
            tableView.getItems().add(newFile);
            fileManager.addFiles(newFile);
        }

        Button deleteButton = new Button("Usuń");
        deleteButton.getStyleClass().setAll("btn","btn-danger");
        deleteButton.setAlignment(Pos.CENTER);

        deleteButton.setOnAction(e -> {
            Object col = tableView.getSelectionModel().getSelectedItem();
            tableView.getItems().remove(col);
//            tableView.getItems().removeAll(tableView.getItems());

            fileManager.removeFiles((FilesItem) col);

            List<FilesItem> filesList = fileManager.filesList;
            for (FilesItem file : filesList) {
                tableView.getItems().add(file);
            }
        });

        HBox bottomBox = new HBox(
            deleteButton
        );

        VBox vbox = new VBox(topBox, tableView, bottomBox);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        Scene scene = new Scene(vbox, 500, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }

    public Scene fourthScene() {
        primaryStage.setTitle("JavaFX WebView Example");

        WebView webView = new WebView();

        webView.getEngine().load("https://jenkov.com/tutorials/javafx/webview.html");

        VBox webViewBox = new VBox(webView);
        Scene scene = new Scene(webViewBox, 960, 600);

        primaryStage.show();
        return scene;
    }
}