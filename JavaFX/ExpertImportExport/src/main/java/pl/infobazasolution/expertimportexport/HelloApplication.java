package pl.infobazasolution.expertimportexport;

import Classes.FilesItem;
import Classes.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.LinkOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HelloApplication extends Application {
    private Stage primaryStage;
    private ObservableList<FilesItem> fileList;
    private TableView<FilesItem> tableView;
    private ObservableList<Integer> getSelectedIndices;
    private File selectedDirectory;
    private List<File> txtFiles;
    private final Person person = new Person("Admin", "Admin", "a1");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        primaryStage.setTitle("Aplikacja JavaFX");

        primaryStage.setScene(loginScene());
        primaryStage.show();
    }

    private Scene loginScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        InputStream stream = null;
        try {
//            stream = new FileInputStream("/home/karynap/IdeaProjects/ExpertImportExport/src/images/logo.jpg");
            stream = new FileInputStream("C:\\JavaFX\\Table_FX\\src\\main\\java\\Images\\logo.jpg");
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
        separator.setMinWidth(50);
        separator.setMaxWidth(240);

        TextField loginField = new TextField();
        loginField.setPromptText("Adres e-mail");
        loginField.setMinWidth(30);
        loginField.setMaxWidth(200);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Hasło");
        passwordField.setMinWidth(30);
        passwordField.setMaxWidth(200);

        Button loginButton = new Button("Zaloguj się");
        loginButton.getStyleClass().setAll("btn","btn-danger");
        loginButton.setAlignment(Pos.CENTER);
        loginButton.setMinWidth(50);
        loginButton.setMaxWidth(100);

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        loginButton.setOnAction(e -> {
            if (loginField.getText().isBlank() || passwordField.getText().isBlank()) {
                messageLabel.setText("Pole nie może być puste.");
            }

            if (loginField.getText().equals(person.login) && passwordField.getText().equals(person.password)) {
                primaryStage.setScene(mainScene());
                primaryStage.show();
            }
            else {
                messageLabel.setText("Login lub hasło jest niepoprawne!");
            }

//            primaryStage.setScene(mainScene());
//            primaryStage.show();
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

    private HBox MenuView(){
        InputStream stream = null;
        try {
//            stream = new FileInputStream("/home/karynap/IdeaProjects/ExpertImportExport/src/images/logo.jpg");
            stream = new FileInputStream("C:\\JavaFX\\Table_FX\\src\\main\\java\\Images\\logo.jpg");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(stream);

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);

        Button formButton = new Button("Formularz");
        formButton.getStyleClass().setAll("btn","btn-danger");

        formButton.setOnAction(e -> {
//            primaryStage.setScene(formScene());
            primaryStage.setScene(catalogScene());
            primaryStage.show();
        });

        Button helpButton = new Button("Pomoc");
        helpButton.getStyleClass().setAll("btn","btn-danger");
        helpButton.setOnAction(e -> {
            primaryStage.setScene(helpScene());
            primaryStage.show();
        });

        Hyperlink signOutButton = new Hyperlink("Sign Out");
        signOutButton.getStyleClass().setAll("btn","btn-danger");
        signOutButton.setWrapText(true);

        signOutButton.setOnAction(e -> {
            primaryStage.setScene(loginScene());
            primaryStage.show();
        });

        HBox menuBox = new HBox(
                imageView,
                formButton,
                helpButton,
                signOutButton
        );
        menuBox.setAlignment(Pos.TOP_LEFT);
        menuBox.setSpacing(10);

        HBox.setHgrow(menuBox, Priority.ALWAYS);

        return menuBox;
    }

    private Scene mainScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        HBox menuBox = MenuView();

        String userName = "Admin";
        LocalDate data = LocalDate.now();
        Label loginLabel = new Label("Witaj " + userName + " w Systemie. Dziś jest " + data.toString() + ".");
        loginLabel.setWrapText(true);
        loginLabel.setMinWidth(100);

        root.setTop(menuBox);
        root.setCenter(loginLabel);

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }

    private Scene formScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        HBox menuBox = MenuView();

        Label topLabel = new Label("Wypełnij ankietę");
        topLabel.setFont(Font.font(20));

        Separator separator = new Separator(Orientation.HORIZONTAL);

        Label userNameLabel = new Label("Imię: ");
        userNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        TextField userNameField = new TextField();
        VBox nameBox = new VBox(
                userNameLabel,
                userNameField);

        Label userSecondnameLabel = new Label("Nazwisko: ");
        userSecondnameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        TextField userSecondnameField = new TextField();
        VBox secondnameBox = new VBox(
                userSecondnameLabel,
                userSecondnameField);
        secondnameBox.setSpacing(15);

        Label genderLabel = new Label("Płeć: ");
        genderLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        RadioButton genderFemaleRadioButton = new RadioButton("K");
        RadioButton genderMaleRadioButton = new RadioButton("M");
        HBox genderBox = new HBox(genderLabel,
                genderFemaleRadioButton, 
                genderMaleRadioButton);
        genderBox.setSpacing(15);

        Label dateBirthLabel = new Label("Data urodzenia: ");
        dateBirthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        dateBirthLabel.setWrapText(true);
        dateBirthLabel.setMinWidth(50);

        DatePicker dateBirthField = new DatePicker();

        HBox dateBox = new HBox(
                dateBirthLabel, 
                dateBirthField);
        dateBox.setSpacing(15);

        Label choiceBoxLabel = new Label("Kolor oczu: ");
        choiceBoxLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        ChoiceBox choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll("szare", "brązowe", "zielone", "niebieskie");

        HBox colorEyesBox = new HBox(
                choiceBoxLabel, 
                choiceBox);
        colorEyesBox.setSpacing(15);

        Label hobbyLabel = new Label("Zainteresowania: ");
        hobbyLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));

        CheckBox hobbyCheckBox1 = new CheckBox("Czytanie");
        CheckBox hobbyCheckBox2 = new CheckBox("Programowanie");
        CheckBox hobbyCheckBox3 = new CheckBox("Rysowanie");
        CheckBox hobbyCheckBox4 = new CheckBox("Inne");

        VBox hobbyBox = new VBox(
                hobbyLabel, 
                hobbyCheckBox1, 
                hobbyCheckBox2, 
                hobbyCheckBox3, 
                hobbyCheckBox4);
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

            primaryStage.setScene(catalogScene());
            primaryStage.show();
        });

        HBox bottomBox = new HBox(
                messageLabel,
                sendButton
        );
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setSpacing(10);

        root.setTop(menuBox);
        root.setCenter(formBox);
        root.setBottom(bottomBox);

        Scene scene = new Scene(root,500, 400);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }

    private Scene catalogScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        HBox menuBox = MenuView();

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));

        tableView = new TableView<FilesItem>();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setMinWidth(200);
        fileList = FXCollections.observableArrayList();

        tableView.prefWidthProperty().bind(root.widthProperty().multiply(0.9));
        tableView.prefHeightProperty().bind(root.heightProperty().multiply(0.8));

        Button selectDirectoryButton = new Button("Wybierz folder");
        selectDirectoryButton.getStyleClass().setAll("btn","btn-danger");
        selectDirectoryButton.setAlignment(Pos.CENTER);

        VBox.setVgrow(tableView, Priority.ALWAYS);

        TableColumn<FilesItem, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setPrefWidth(10);
        TableColumn<FilesItem, String> nameColumn = new TableColumn<>("Nazwa pliku");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("idFile"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameFile"));

        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(nameColumn);
        tableView.setItems(fileList);

        HBox tableBox = new HBox(tableView);
        tableBox.setSpacing(15);
        tableBox.setPadding(new Insets(20));
        tableBox.setAlignment(Pos.CENTER);

        selectDirectoryButton.setOnAction(e -> {
            ChooseFolder(primaryStage);
        });

        Button deleteButton = new Button("Usuń");
        deleteButton.getStyleClass().setAll("btn","btn-danger");
        deleteButton.setAlignment(Pos.CENTER);

        deleteButton.setOnAction(e -> {
            Object col = tableView.getSelectionModel().getSelectedItem();
            tableView.getItems().remove(col);

            for (int i = 0; i < fileList.size(); i++) {
                fileList.get(i).setIdFile(i+1);
            }
            tableView.setItems(fileList);
        });

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");
        messageLabel.setFont(Font.font(16));

        Button showNewListFilesButton = new Button("Wyświetl liste");
        showNewListFilesButton.getStyleClass().setAll("btn","btn-danger");
        showNewListFilesButton.setAlignment(Pos.CENTER);
        showNewListFilesButton.setOnAction(e -> {
            if (selectedDirectory == null){
                messageLabel.setText("Wybierz folder");
            }
            else {
                getSelectedIndices = tableView.getSelectionModel().getSelectedIndices();
                primaryStage.setScene(tableViewScene());
                primaryStage.show();
            }
        });

        HBox buttomBox = new HBox(
                selectDirectoryButton, 
                deleteButton, 
                showNewListFilesButton,
                messageLabel
        );
        buttomBox.setSpacing(10);

        root.setTop(menuBox);
        root.setCenter(tableBox);
        root.setBottom(buttomBox);

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }

    private void ChooseFolder(Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        selectedDirectory = directoryChooser.showDialog(stage);

        if (selectedDirectory != null) {
            LoadFiles(selectedDirectory);
        }
    }

    private void LoadFiles(File directory) {
        txtFiles = Arrays.stream(directory.listFiles())
                .filter(file -> file.isFile() && file.getName().endsWith(".txt")) // .xml
                .collect(Collectors.toList());

        Collections.sort(txtFiles);
        fileList.clear();
        for (int i = 0; i < txtFiles.size(); i++) {
            fileList.add(new FilesItem(i + 1, txtFiles.get(i).getName()));
        }
    }

    public Scene tableViewScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        HBox menuBox = MenuView();

        TableView<FilesItem> tableView1 = new TableView<>();
        tableView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView1.setMinWidth(200);

        tableView1.prefWidthProperty().bind(root.widthProperty().multiply(0.9));
        tableView1.prefHeightProperty().bind(root.heightProperty().multiply(0.8));

        TableColumn<FilesItem, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setPrefWidth(10);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idFile"));

        TableColumn<FilesItem, String> nameColumn = new TableColumn<>("Nazwa pliku");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameFile"));

        tableView1.getColumns().add(idColumn);
        tableView1.getColumns().add(nameColumn);

        ObservableList<Object> fileList1 = FXCollections.observableArrayList();

        Collections.sort(txtFiles);
        Integer idFile = 1;
        for(Integer item : getSelectedIndices){
            tableView1.getItems().add(new FilesItem(idFile, txtFiles.get(item).getName()));
            idFile++;
        }

        tableView.setItems(fileList);
        HBox tableBox = new HBox(tableView1);
        tableBox.setSpacing(15);
        tableBox.setPadding(new Insets(20));

        Button backButton = new Button("<<<");
        backButton.getStyleClass().setAll("btn","btn-danger");
        backButton.setAlignment(Pos.CENTER);

        backButton.setOnAction(e -> {
            selectedDirectory = null;

            primaryStage.setScene(catalogScene());
            primaryStage.show();
        });

        root.setTop(menuBox);
        root.setCenter(tableBox);
        root.setBottom(backButton);

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }

    public Scene helpScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        primaryStage.setTitle("JavaFX WebView Example");

        WebView webView = new WebView();
        webView.getEngine().load("https://jenkov.com/tutorials/javafx/webview.html");

        HBox menuBox = MenuView();
        Separator separator = new Separator();

        VBox topBox = new VBox(menuBox, separator);

        root.setTop(topBox);
        root.setCenter(webView);

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }
}
