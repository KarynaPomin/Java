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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class HelloApplication extends Application {
    private Stage primaryStage;
    private ObservableList<FilesItem> fileList;
    private TableView<FilesItem> tableView;
    private ObservableList<Integer> getSelectedIndices;
    private File selectedDirectory;
    private List<File> txtFiles;
    private final Person person = new Person("Admin", "Admin", "a1");

    String pathStyle = "file:/home/karynap/IdeaProjects/ExpertImportExport/src/main/resources/pl/infobazasolution/css/Stylesheet.css";
//    String buttonStyle =
//            "-fx-background-color: #3a3838;" +
//            "-fx-border-color: #000000;" +
//            "-fx-text-fill: #ffffff;" +
//            "-fx-padding: 10px;" +
//            "-fx-border-radius: 5px";

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

        InputStream stream;
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
        separator.setMinWidth(50);
        separator.setMaxWidth(240);

        TextField loginField = new TextField();
        loginField.setPromptText("Adres e-mail");
        loginField.getStyleClass().add("loginField");
        loginField.setMinWidth(30);
        loginField.setMaxWidth(200);

        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("loginField");
        passwordField.setPromptText("Hasło");
        passwordField.setMinWidth(30);
        passwordField.setMaxWidth(200);

        Button loginButton = new Button("Zaloguj się");
        loginButton.getStyleClass().setAll("btn");
//        loginButton.setStyle(buttonStyle);

        loginButton.setAlignment(Pos.CENTER);
        loginButton.setMinWidth(50);
        loginButton.setMaxWidth(100);

        Label messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        loginButton.setOnAction(e -> {
//            if (loginField.getText().isBlank() || passwordField.getText().isBlank()) {
//                messageLabel.setText("Pole nie może być puste.");
//            }
//
//            if (loginField.getText().equals(person.login) && passwordField.getText().equals(person.password)) {
//                primaryStage.setScene(mainScene());
//                primaryStage.show();
//            }
//            else {
//                messageLabel.setText("Login lub hasło jest niepoprawne!");
//            }

            primaryStage.setScene(mainScene());
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
        //pathStyle = Objects.requireNonNull(getClass().getResource("/pl/infobazasolution/css/Stylesheet.css")).toExternalForm();
        scene.getStylesheets().add(pathStyle);
//        scene.getStylesheets().add(getClass().getResource(pathStyle).toExternalForm());
//        System.out.println("---" + pathStyle);
//        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }

    private HBox MenuView(){
        InputStream stream;
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

        Button formButton = new Button("Formularz");
        formButton.getStyleClass().add("btn");
//        formButton.getStyleClass().setAll("btn","btn-danger");

        formButton.setOnAction(e -> {
            primaryStage.setScene(catalogScene());
            primaryStage.show();
        });

        Button helpButton = new Button("Pomoc");
        helpButton.getStyleClass().add("btn");
//        helpButton.getStyleClass().setAll("btn","btn-danger");
        helpButton.setOnAction(e -> {
            primaryStage.setScene(helpScene());
            primaryStage.show();
        });

        Button signOutButton = new Button("Sign Out");
        signOutButton.getStyleClass().add("btn");
//        signOutButton.getStyleClass().setAll("btn","btn-danger");
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
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setSpacing(10);

        HBox.setHgrow(menuBox, Priority.ALWAYS);

        return menuBox;
    }

    private HBox LabelMenuView(){
        LocalDate data = LocalDate.now();

        String dayOfWeek = data.getDayOfWeek().toString();
        dayOfWeek = switch (data.getDayOfWeek().getValue()) {
            case 1 -> "poniedziałek";
            case 2 -> "wtorek";
            case 3 -> "średa";
            case 4 -> "czwartek";
            case 5 -> "piątek";
            case 6 -> "subota";
            case 7 -> "niedziela";
            default -> dayOfWeek;
        };

        String dayOfMonth = String.valueOf(data.getDayOfMonth());
        if (data.getDayOfMonth() < 10) {
            dayOfMonth = "0" + dayOfMonth;
        }

        String month = String.valueOf(data.getMonthValue());
        if (data.getMonth().getValue() < 10){
            month = "0" + month;
        }

        Label loginLabel = new Label(
                "Witaj " + person.name + " w Systemie. " +
                "Dziś jest " + dayOfWeek + " " + dayOfMonth + "." + month + "." + data.getYear());
        loginLabel.setWrapText(true);
        loginLabel.setMinWidth(100);

        HBox hBox = new HBox(
                loginLabel
        );
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    private Scene mainScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        HBox menuBox = MenuView();

        HBox loginLabel = LabelMenuView();
        loginLabel.setAlignment(Pos.CENTER);

        Label footer = new Label("© 2025 Wszystkie prawa zastrzeżone.");
        footer.setPadding(new Insets(10));
        footer.setAlignment(Pos.CENTER);

        VBox footerBox = new VBox(footer);
        footerBox.setAlignment(Pos.CENTER);

        root.setTop(menuBox);
        root.setCenter(loginLabel);
        root.setBottom(footerBox);

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(pathStyle);
//        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }

    private Scene catalogScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        HBox menu = MenuView();
        HBox labelMenu = LabelMenuView();
        VBox menuBox = new VBox(
                menu,
                labelMenu
        );

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));

        tableView = new TableView<>();
        tableView.getStyleClass().add("table-view");
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setMinWidth(200);
        fileList = FXCollections.observableArrayList();

        tableView.prefWidthProperty().bind(root.widthProperty().multiply(0.9));
        tableView.prefHeightProperty().bind(root.heightProperty().multiply(0.8));

        Button selectDirectoryButton = new Button("Wybierz folder");
//        selectDirectoryButton.getStyleClass().setAll("btn","btn-danger");
        selectDirectoryButton.getStyleClass().add("btn");
        selectDirectoryButton.setAlignment(Pos.CENTER);

        VBox.setVgrow(tableView, Priority.ALWAYS);

        TableColumn<FilesItem, Integer> idColumn = new TableColumn<>("Id");
        idColumn.setPrefWidth(10);
//        idColumn.getStyleClass().add("column-header");
        TableColumn<FilesItem, String> nameColumn = new TableColumn<>("Nazwa pliku");
//        nameColumn.getStyleClass().add("column-header");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("idFile"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameFile"));

        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(nameColumn);
        tableView.setItems(fileList);

        HBox table = new HBox(tableView);
        table.setSpacing(15);
        table.setPadding(new Insets(20));
        table.setAlignment(Pos.CENTER);

        VBox tableBox = new VBox(
                table
        );
        tableBox.setAlignment(Pos.CENTER);

        selectDirectoryButton.setOnAction(e -> ChooseFolder(primaryStage));

        Button deleteButton = new Button("Usuń");
//        deleteButton.getStyleClass().setAll("btn","btn-danger");
        deleteButton.getStyleClass().add("btn");
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
        showNewListFilesButton.getStyleClass().add("btn");
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

        HBox buttonsBox = new HBox(
                selectDirectoryButton,
                deleteButton,
                showNewListFilesButton,
                messageLabel
        );
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(10);

        Label footer = new Label("© 2025 Wszystkie prawa zastrzeżone.");
        footer.setPadding(new Insets(10));

        VBox buttomBox = new VBox(
                buttonsBox,
                footer
        );
        buttomBox.setAlignment(Pos.CENTER);

        root.setTop(menuBox);
        root.setCenter(tableBox);
        root.setBottom(buttomBox);

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(pathStyle);
//        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
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
        txtFiles = Arrays.stream(Objects.requireNonNull(directory.listFiles()))
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

        HBox menu = MenuView();
        HBox labelMenu = LabelMenuView();
        VBox menuBox = new VBox(
                menu,
                labelMenu
        );

        TableView<FilesItem> tableView1 = new TableView<>();
        tableView1.getStyleClass().add("table-view");
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

        Collections.sort(txtFiles);
        Integer idFile = 1;
        for(Integer item : getSelectedIndices){
            tableView1.getItems().add(new FilesItem(idFile, txtFiles.get(item).getName()));
            idFile++;
        }

        tableView.setItems(fileList);

        HBox table = new HBox(tableView1);
        table.setSpacing(15);
        table.setPadding(new Insets(20));

        VBox tableBox = new VBox(
                table
        );
        tableBox.setAlignment(Pos.CENTER);

        Button backButton = new Button("<<<");
//        backButton.getStyleClass().setAll("btn","btn-danger");
        backButton.getStyleClass().add("btn");

        backButton.setOnAction(e -> {
            selectedDirectory = null;

            primaryStage.setScene(catalogScene());
            primaryStage.show();
        });

        Label footer = new Label("© 2025 Wszystkie prawa zastrzeżone.");
        footer.setPadding(new Insets(10));

        VBox buttomBox = new VBox(
                backButton,
                footer
        );
        buttomBox.setAlignment(Pos.CENTER);

        root.setTop(menuBox);
        root.setCenter(tableBox);
        root.setBottom(buttomBox);

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(pathStyle);
//        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }

    public Scene helpScene() {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));

        primaryStage.setTitle("JavaFX WebView Example");

        WebView webView = new WebView();
        webView.getEngine().load("https://jenkov.com/tutorials/javafx/webview.html");

        HBox menu = MenuView();
        HBox labelMenu = LabelMenuView();
        VBox menuBox = new VBox(
                menu,
                labelMenu
        );

        Separator separator = new Separator();

        VBox topBox = new VBox(menuBox, separator);

        root.setTop(topBox);
        root.setCenter(webView);

        Scene scene = new Scene(root, 500, 400);
        scene.getStylesheets().add(pathStyle);
//        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        return scene;
    }
}