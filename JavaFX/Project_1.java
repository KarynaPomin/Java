package pl.infobazasolution.expertimportexport;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;


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

        Label title = new Label("Logowanie");
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        Separator separator = new Separator(Orientation.HORIZONTAL);

        VBox formBox = new VBox(10);
        formBox.setAlignment(Pos.CENTER);

        TextField loginField = new TextField();
        loginField.setPromptText("Adres e-mail");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Hasło");

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

        formBox.getChildren().addAll(title, separator, loginField, passwordField, loginButton, messageLabel);

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

        HBox topBox = new HBox();
        topBox.setAlignment(Pos.TOP_CENTER);
        topBox.setSpacing(10);

        String userName = "Admin";
        LocalDate data = LocalDate.now();
        Label loginLabel = new Label("Witaj " + userName + " w Systemie. Dziś jest " + data.toString() + ".");
        loginLabel.setWrapText(true);

        Button signOutButton = new Button("Wyloguj");
        signOutButton.getStyleClass().setAll("btn","btn-danger");

        Button helpButton = new Button("Pomoc");
        helpButton.getStyleClass().setAll("btn","btn-danger");
        helpButton.setOnAction(e -> {
            primaryStage.setScene(fourthScene());
        });

        topBox.getChildren().addAll(loginLabel, signOutButton, helpButton);

        VBox formBox = new VBox();
        formBox.setAlignment(Pos.TOP_LEFT);
        formBox.setSpacing(15);

        Label topLabel = new Label("Wypełnij ankietę");
        topLabel.setFont(Font.font(20));

        Separator separator = new Separator(Orientation.HORIZONTAL);

        Label userNameLabel = new Label("Imię: ");
        userNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        TextField userNameField = new TextField();
        VBox nameBox = new VBox(userNameLabel, userNameField);

        Label userSecondnameLabel = new Label("Nazwsko: ");
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

        DatePicker dateBirthField = new DatePicker();

        HBox dateBox = new HBox(dateBirthLabel, dateBirthField);
        dateBox.setSpacing(15);

        Label menuLabel = new Label("Kolor oczu: ");
        menuLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("szare", "brązowe", "zielone", "niebieskie");

        HBox colorEyesBox = new HBox(menuLabel, choiceBox);
        colorEyesBox.setSpacing(15);

        Label hobbyLabel = new Label("Zainteresowania: ");
        hobbyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        CheckBox hobbyCheckBox1 = new CheckBox("Czytanie");
        CheckBox hobbyCheckBox2 = new CheckBox("Programowanie");
        CheckBox hobbyCheckBox3 = new CheckBox("Rysowanie");
        CheckBox hobbyCheckBox4 = new CheckBox("Inne");

        VBox hobbyBox = new VBox(hobbyLabel, hobbyCheckBox1, hobbyCheckBox2, hobbyCheckBox3, hobbyCheckBox4);
        hobbyBox.setSpacing(15);

        formBox.getChildren().addAll(topLabel, separator, nameBox, secondnameBox, dateBox, genderBox, colorEyesBox, hobbyBox);

        HBox bottomBox = new HBox();
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(10);

        Button sendButton = new Button("Wyślij");
        sendButton.getStyleClass().setAll("btn","btn-danger");
        sendButton.setAlignment(Pos.CENTER);

        sendButton.setOnAction(e -> {
            primaryStage.setScene(thirdScene());
            primaryStage.show();
        });

        bottomBox.getChildren().addAll(sendButton);

        root.setTop(topBox);
        root.setCenter(formBox);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root, 500, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }

    private Scene thirdScene() {
        BorderPane root = new BorderPane();

        HBox topBox = new HBox();
        topBox.setAlignment(Pos.TOP_CENTER);
        topBox.setSpacing(10);

        String userName = "Admin";
        LocalDate data = LocalDate.now();
        Label loginLabel = new Label(data.toString());
        loginLabel.setWrapText(true);

        Button returnButton = new Button("Główne");
        returnButton.getStyleClass().setAll("btn","btn-danger");

        returnButton.setOnAction(e -> {
            primaryStage.setScene(secondScene());
            primaryStage.show();
        });

        Button helpButton = new Button("Pomoc");
        helpButton.getStyleClass().setAll("btn","btn-danger");

        helpButton.setOnAction(e -> {
            primaryStage.setScene(fourthScene());
        });

        topBox.getChildren().addAll(loginLabel, returnButton, helpButton);

        VBox formBox = new VBox();
        formBox.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Wyślij wiadomość");
        titleLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        Separator separator = new Separator(Orientation.HORIZONTAL);

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(4);
        grid.setVgap(8);

        Label emailLabel = new Label("Email");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        TextField emailField = new TextField();

        Label priorityLabel = new Label("Priority");
        priorityLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        ChoiceBox priorityChoiceBox = new ChoiceBox();
        priorityChoiceBox.getItems().addAll("Wysoki", "Średni", "Niski");

        Label descriptionLabel = new Label("Description");

        TextArea descriptionArea = new TextArea();

        grid.add(emailLabel, 0, 0); grid.add(emailField, 1, 0);
        grid.add(priorityLabel, 0, 1); grid.add(priorityChoiceBox, 1, 1);
        grid.add(descriptionLabel, 0, 2); grid.add(descriptionArea, 1, 2);

        GridPane.setColumnSpan( emailField, 3 );
        GridPane.setColumnSpan( descriptionArea, 5 );

        formBox.getChildren().addAll(titleLabel, separator, grid);

        root.setTop(topBox);
        root.setCenter(formBox);

        Scene scene = new Scene(root, 500, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }

    Scene fourthScene() {
        BorderPane root = new BorderPane();

        Scene scene = new Scene(root, 500, 600);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }
}
