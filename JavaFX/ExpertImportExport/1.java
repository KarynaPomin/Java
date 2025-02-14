package pl.infobazasolution.expertimportexport;

import Classes.Person;
import Classes.User;
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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class HelloApplication extends Application {
    private Stage primaryStage;
    private final ObservableList<Person> zusDraPersons = FXCollections.observableArrayList();
    private final ObservableList<Person> infdpPersons = FXCollections.observableArrayList();
    private TableView<Person> tableView;
    File selectedDirectory;
    private final User user = new User("Admin", "Admin", "a1");

    String pathStyle = "file:/home/karynap/IdeaProjects/ExpertImportExport/src/main/resources/pl/infobazasolution/css/Stylesheet.css";

//    private final String FILE_INF_DP = "/home/karynap/IdeaProjects/ExpertImportExport/src/main/java/Text/plikiXML/INF_DP/";
//    private final String FILE_ZUS_DRA = "/home/karynap/IdeaProjects/ExpertImportExport/src/main/java/Text/plikiXML/ZUS_DRA/";

    private String FILE_INF_DP = null;
    private String FILE_ZUS_DRA = null;

    private boolean foldeIsSelected = false;
//    private DirectoryChooser directoryChooser;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        primaryStage.setTitle("Aplikacja JavaFX");

//        zusDraPersons = FXCollections.observableArrayList();
//        LoadFiles(new File(FILE_ZUS_DRA), zusDraPersons);
//
//        infdpPersons = FXCollections.observableArrayList();
//        LoadFiles(new File(FILE_INF_DP), infdpPersons);

        primaryStage.setScene(loginScene());
        primaryStage.show();
    }

    private Scene loginScene() {
        BorderPane root = new BorderPane();

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
//        imageView.getStyleClass().setAll("imageView-login");
        imageView.setPreserveRatio(true);

        Label title = new Label("Logowanie");
        title.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        Separator separator = new Separator(Orientation.HORIZONTAL);
        separator.setMinWidth(50);
        separator.setMaxWidth(240);

        TextField loginField = new TextField();
        loginField.setPromptText("Adres e-mail");
        loginField.getStyleClass().setAll("loginField");
        loginField.setMinWidth(30);
        loginField.setMaxWidth(200);

        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().setAll("loginField");
        passwordField.setPromptText("Hasło");
        passwordField.setMinWidth(30);
        passwordField.setMaxWidth(200);

        Button loginButton = new Button("Zaloguj się");

        loginButton.setAlignment(Pos.CENTER);
        loginButton.setMinWidth(50);
        loginButton.setMaxWidth(100);

        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("message-label");

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
        imageView.getStyleClass().setAll("imageView");
        imageView.setImage(image);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);

        Button formButton = new Button("Formularz");

        formButton.setOnAction(e -> {
            primaryStage.setScene(catalogScene());
            primaryStage.show();
        });

        Button helpButton = new Button("Pomoc");
        helpButton.setOnAction(e -> {
            primaryStage.setScene(helpScene());
            primaryStage.show();
        });

        Button signOutButton = new Button("Sign Out");
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
                "Witaj " + user.name + " w Systemie. " +
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

    private void RemoveColumn(ObservableList<Person> list){
        Object col = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(col);

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setId(i+1);
        }
        tableView.setItems(list);
    }

    private Scene catalogScene() {
        BorderPane root = new BorderPane();

        HBox menu = MenuView();
        HBox labelMenu = LabelMenuView();
        VBox menuBox = new VBox(
                menu,
                labelMenu
        );

        tableView = new TableView<>();
        tableView.getStyleClass().setAll("table-view");
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView.prefWidthProperty().bind(root.widthProperty().multiply(0.9));
        tableView.prefHeightProperty().bind(root.heightProperty().multiply(0.8));

        VBox.setVgrow(tableView, Priority.ALWAYS);

        TableColumn<Person, Integer> idColumn = new TableColumn<>("Lp");
        idColumn.getStyleClass().setAll("column-header");
        idColumn.setPrefWidth(10);

        TableColumn<Person, Integer> peselColumn = new TableColumn<>("PESEL");
        peselColumn.getStyleClass().setAll("column-header");
        peselColumn.setPrefWidth(30);

        TableColumn<Person, String> nameColumn = new TableColumn<>("Imię i nazwisko");
        nameColumn.getStyleClass().setAll("column-header");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(peselColumn);
        tableView.getColumns().add(nameColumn);

        HBox table = new HBox(tableView);
        table.setAlignment(Pos.CENTER);

        VBox tableBox = new VBox(
                table
        );
        tableBox.setAlignment(Pos.CENTER);

        Button zusDraSelectDirectoryButton = new Button("Nowe");
        zusDraSelectDirectoryButton.setAlignment(Pos.CENTER);
        zusDraSelectDirectoryButton.setOnAction(e -> {
            ChooseFolder(primaryStage, zusDraPersons, "zus");
            tableView.setItems(zusDraPersons);
        });

        Button infDpSelectDirectoryButton = new Button("Wysłane");
        infDpSelectDirectoryButton.setAlignment(Pos.CENTER);
        infDpSelectDirectoryButton.setOnAction(e -> {
            ChooseFolder(primaryStage, infdpPersons, "inf");
            tableView.setItems(infdpPersons);
        });

        Button deleteButton = new Button("Usuń");
        deleteButton.setAlignment(Pos.CENTER);

        deleteButton.setOnAction(e -> RemoveColumn(tableView.getItems()));

        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("message-label");

        Button showNewListFilesButton = new Button("Wyświetl liste");
        showNewListFilesButton.setAlignment(Pos.CENTER);
        showNewListFilesButton.setOnAction(e -> {
            if (!foldeIsSelected){
                messageLabel.setText("Wybierz foldery");
            }
            else if (FILE_ZUS_DRA == null){
                messageLabel.setText("Dodaj nowy folder");
            }
            else if (FILE_INF_DP == null){
                messageLabel.setText("Dodaj folder wysłane");
            }
            else {
                System.out.println(FILE_ZUS_DRA);
                System.out.println(FILE_INF_DP);
                primaryStage.setScene(tableViewScene());
                primaryStage.show();
            }
        });

        HBox buttonsBox = new HBox(
                zusDraSelectDirectoryButton,
                infDpSelectDirectoryButton,
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
        return scene;
    }

    private void ChooseFolder(Stage stage, ObservableList<Person> FileList, String nameFile) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        selectedDirectory = directoryChooser.showDialog(stage);

        if (nameFile.equals("zus"))
            FILE_ZUS_DRA = selectedDirectory.getAbsolutePath();
        else if (nameFile.equals("inf"))
            FILE_INF_DP = selectedDirectory.getAbsolutePath();

        if (selectedDirectory != null) {
            LoadFiles(selectedDirectory, FileList);
        }
        foldeIsSelected = true;
    }

    private void LoadFiles(File directory, ObservableList<Person> FileList) {
        List<File> xmlFiles = Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .filter(file -> file.isFile() && file.getName().endsWith(".xml"))
                .collect(Collectors.toList());

        Collections.sort(xmlFiles);
        FileList.clear();
        for (int i = 0; i < xmlFiles.size(); i++) {
            Person user = new Person();
            Map<String, String> userDictionary = OpenFile(xmlFiles.get(i).getPath());
            user.id = i + 1;
            user.pesel = String.valueOf(userDictionary.get("pesel"));
            user.name = String.valueOf(userDictionary.get("name"));
            user.secondname = String.valueOf(userDictionary.get("secondname"));

            FileList.add(user);
        }
    }

    private Map<String, String> OpenFile(String pathFile) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Map<String, String> userDictionatry = new HashMap<>();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(pathFile));

            doc.getDocumentElement().normalize();

            // get staff
            NodeList nodeList = doc.getElementsByTagName("Dane");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String pesel = element.getElementsByTagName("PESEL").item(0).getTextContent();
                    userDictionatry.put("pesel", pesel);

                    String name = element.getElementsByTagName("Imie").item(0).getTextContent();
                    userDictionatry.put("name", name);

                    String secondname = element.getElementsByTagName("Nazwisko").item(0).getTextContent();
                    userDictionatry.put("secondname", secondname);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return userDictionatry;
    }

    public Scene tableViewScene() {
        BorderPane root = new BorderPane();

        HBox menu = MenuView();
        HBox labelMenu = LabelMenuView();
        VBox menuBox = new VBox(
                menu,
                labelMenu
        );

        TableView<Person> tableView1 = new TableView<>();
        tableView1.getStyleClass().setAll("table-view bg-red");
        tableView1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        tableView1.prefWidthProperty().bind(root.widthProperty().multiply(0.9));
        tableView1.prefHeightProperty().bind(root.heightProperty().multiply(0.8));

        VBox.setVgrow(tableView1, Priority.ALWAYS);

        TableColumn<Person, Integer> idColumn = new TableColumn<>("Lp");
        idColumn.setPrefWidth(10);
        idColumn.getStyleClass().setAll("column-header");

        TableColumn<Person, String> peselColumn = new TableColumn<>("PESEL");
        peselColumn.getStyleClass().setAll("column-header");
        peselColumn.setPrefWidth(30);

        TableColumn<Person, String> nameColumn = new TableColumn<>("Imię i nazwisko");
        nameColumn.getStyleClass().setAll("column-header");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        peselColumn.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));

        tableView1.getColumns().add(idColumn);
        tableView1.getColumns().add(peselColumn);
        tableView1.getColumns().add(nameColumn);

        List<String> peselInInfdp = infdpPersons.stream().map(Person::getPesel).toList();

        zusDraPersons.forEach(person -> {
            if(peselInInfdp.contains(person.getPesel())) {
                person.setIsInList(true);
            }
        });


        tableView1.setRowFactory(tbl -> new TableRow<Person>() {

            @Override
            protected void updateItem(Person item, boolean empty) {
                super.updateItem(item, empty);

                if(item == null || empty) {
                    setStyle(getStyle());
                    setTooltip(null);
                } else {
                    if(item.getIsInList()) {
                        setStyle(getStyle());
                        setTooltip(null);
                    } else {
                        setStyle("-fx-background-color: #ffcccc; -fx-text-fill: #ffffff;");
                    }
                }
            }
        });

        tableView1.getColumns().setAll(idColumn, peselColumn, nameColumn);
        tableView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableView1.setItems(zusDraPersons);

        HBox table = new HBox(tableView1);
        table.setAlignment(Pos.CENTER);

        VBox tableBox = new VBox(
                table
        );
        tableBox.setAlignment(Pos.CENTER);

        Button backButton = new Button("<<<");

        backButton.setOnAction(e -> {
            selectedDirectory = null;
            FILE_INF_DP = null;
            FILE_ZUS_DRA = null;

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
        return scene;
    }

    public Scene helpScene() {
        BorderPane root = new BorderPane();

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
