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
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
    private double widthScene = 500;
    private double heightScene = 400;
    private final ObservableList<Person> zusDraPersons = FXCollections.observableArrayList();
    private final ObservableList<Person> infdpPersons = FXCollections.observableArrayList();
    File selectedDirectory;
    private final User user = new User("Admin", "Admin", "a1");
    ObservableList<Person> selectedPersonList = FXCollections.observableArrayList();

//    private final String FILE_INF_DP = "/home/karynap/IdeaProjects/ExpertImportExport/src/main/java/Text/plikiXML/INF_DP/";
//    private final String FILE_ZUS_DRA = "/home/karynap/IdeaProjects/ExpertImportExport/src/main/java/Text/plikiXML/ZUS_DRA/";

    private String FILE_INF_DP = null;
    private String FILE_ZUS_DRA = null;

    private boolean foldeIsSelected = false;

    String pathStyle = "file:/home/karynap/IdeaProjects/ExpertImportExport/src/main/resources/pl/infobazasolution/css/Stylesheet.css";
    String pathImgLogo = "/home/karynap/IdeaProjects/ExpertImportExport/src/images/logo.jpg";


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

    public void SizeScene(Scene scene) {
//        scene.setOnMouseDragged(m -> {
//
//        });

//        scene.widthProperty().addListener(new ChangeListener<Number>() {
//            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
//                System.out.println("Width: " + newSceneWidth);
//                widthScene = newSceneWidth.doubleValue();
//            }
//        });
//        scene.heightProperty().addListener(new ChangeListener<Number>() {
//            @Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
//                System.out.println("Height: " + newSceneHeight);
//                heightScene = newSceneHeight.doubleValue();
//            }
//        });
    }

    private Scene loginScene() {
        BorderPane root = new BorderPane();

        InputStream stream;
        try {
            stream = new FileInputStream(pathImgLogo);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(stream);

        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);

        Label title = new Label("Logowanie");
        title.getStyleClass().add("title");

        Separator separator = new Separator(Orientation.HORIZONTAL);
        separator.setMinWidth(50);
        separator.setMaxWidth(240);

        TextField loginField = new TextField();
        loginField.getStyleClass().add("loginField");
        loginField.setPromptText("Adres e-mail");

        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("loginField");
        passwordField.setPromptText("Hasło");

        Button loginButton = new Button("Zaloguj się");

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

        Label footer = new Label("© 2025 Wszystkie prawa zastrzeżone.");
        footer.getStyleClass().add("footer-title");

        root.setCenter(formBox);
        root.setBottom(footer);
        BorderPane.setAlignment(footer, Pos.CENTER);

        Scene scene = new Scene(root, widthScene, heightScene);
//        scene.getStylesheets().add(pathStyle);
        scene.getStylesheets().add(getClass().getResource(pathStyle).toExternalForm());

        return scene;
    }

    private HBox MenuView(){
        InputStream stream;
        try {
            stream = new FileInputStream(pathImgLogo);
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
        menuBox.getStyleClass().add("menu-box");

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

        HBox hBox = new HBox(
                loginLabel
        );

        return hBox;
    }

    private Scene mainScene() {
        widthScene = primaryStage.getWidth();
        heightScene = primaryStage.getHeight();
        primaryStage.setWidth(widthScene);
        primaryStage.setHeight(heightScene);

        BorderPane root = new BorderPane();

        HBox menuBox = MenuView();

        HBox loginLabel = LabelMenuView();

        Label footer = new Label("© 2025 Wszystkie prawa zastrzeżone.");

        VBox footerBox = new VBox(footer);

        root.setTop(menuBox);
        root.setCenter(loginLabel);
        root.setBottom(footerBox);

        Scene scene = new Scene(root, widthScene, heightScene);
//        scene.getStylesheets().add(pathStyle);
        scene.getStylesheets().add(getClass().getResource(pathStyle).toExternalForm());

        return scene;
    }

    private void RemoveColumn(TableView<Person> tableView, ObservableList<Person> list) {
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

        Label messageLabel = new Label();
        messageLabel.getStyleClass().add("message-label");
        messageLabel.setWrapText(true);

        VBox messageBox = new VBox(messageLabel);

        Button zusDraSelectDirectoryButton = new Button("Nowe");
        zusDraSelectDirectoryButton.setOnAction(e -> {
            ChooseFolder(primaryStage, zusDraPersons, "zus");
            messageLabel.setText("Katalog nowy: " + FILE_ZUS_DRA);
            messageLabel.getStyleClass().remove("message-label-red");
            messageLabel.getStyleClass().add("message-label");
        });

        Button infDpSelectDirectoryButton = new Button("Wysłane");
        infDpSelectDirectoryButton.setOnAction(e -> {
            ChooseFolder(primaryStage, infdpPersons, "inf");
            messageLabel.setText("Katalog wysłany: " + FILE_INF_DP);
            messageLabel.getStyleClass().remove("message-label-red");
            messageLabel.getStyleClass().add("message-label");
        });

        Button showNewListFilesButton = new Button("Wyświetl liste");
        showNewListFilesButton.setOnAction(e -> {
            messageLabel.getStyleClass().remove("message-label");

            if (!foldeIsSelected){
                messageLabel.setText("Wybierz foldery");
                messageLabel.getStyleClass().add("message-label-red");
            }
            else if (FILE_ZUS_DRA == null){
                messageLabel.setText("Dodaj nowy folder");
                messageLabel.getStyleClass().add("message-label-red");
            }
            else if (FILE_INF_DP == null){
                messageLabel.setText("Dodaj folder wysłane");
                messageLabel.getStyleClass().add("message-label-red");
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
                showNewListFilesButton
        );

        Label footer = new Label("© 2025 Wszystkie prawa zastrzeżone.");

        VBox buttomBox = new VBox(
                buttonsBox,
                footer
        );

        root.setTop(menuBox);
        root.setCenter(messageBox);
        root.setBottom(buttomBox);

        Scene scene = new Scene(root, widthScene, heightScene);
//        scene.getStylesheets().add(pathStyle);
        scene.getStylesheets().add(getClass().getResource(pathStyle).toExternalForm());

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

        TableView<Person> tableView = new TableView<>();
        tableView.getStyleClass().add("table-view");
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.prefWidthProperty().bind(root.widthProperty().multiply(0.9));
        tableView.prefHeightProperty().bind(root.heightProperty().multiply(0.8));
        VBox.setVgrow(tableView, Priority.ALWAYS);

        tableView.setEditable(true);

        TableColumn<Person, Boolean> activeColumn = new TableColumn<>("Active");
        activeColumn.setCellValueFactory(cd -> cd.getValue().activeProperty());
        activeColumn.setCellFactory(CheckBoxTableCell.forTableColumn(activeColumn));
        activeColumn.getStyleClass().add("column-header");
        activeColumn.setPrefWidth(10);

        zusDraPersons.forEach(person -> {
            person.activeProperty().addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    if (!selectedPersonList.contains(person)) {
                        selectedPersonList.add(person);
                    }
                } else {
                    selectedPersonList.remove(person);
                }
            });
        });

        TableColumn<Person, Integer> idColumn = new TableColumn<>("Lp");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.getStyleClass().add("column-header");
        idColumn.setPrefWidth(10);

        TableColumn<Person, String> peselColumn = new TableColumn<>("PESEL");
        peselColumn.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        peselColumn.getStyleClass().add("column-header");
        peselColumn.setPrefWidth(30);

        TableColumn<Person, String> nameColumn = new TableColumn<>("Imię i nazwisko");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        nameColumn.getStyleClass().add("column-header");

        List<String> peselInInfdp = infdpPersons.stream().map(Person::getPesel).toList();

        zusDraPersons.forEach(person -> {
            if(peselInInfdp.contains(person.getPesel())) {
                person.setIsInList(true);
            }
        });

        tableView.setRowFactory(tbl -> new TableRow<Person>() {

            @Override
            protected void updateItem(Person item, boolean empty) {
                super.updateItem(item, empty);

                if(item == null || empty) {
                    setStyle("");
                    setTooltip(null);
                } else {
                    if(item.getIsInList()) {
                        getStyleClass().add("white-row");
                        setTooltip(null);
                    } else {
                        getStyleClass().add("red-row");
                    }
                }
            }
        });

        tableView.getColumns().setAll(activeColumn, idColumn, peselColumn, nameColumn);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        UpdateId(zusDraPersons);
        tableView.setItems(zusDraPersons);

        HBox table = new HBox(tableView);

        VBox tableBox = new VBox(
                table
        );

        Button selectedPersonButton = new Button("Nowa lista");
        selectedPersonButton.setOnAction(e -> {
            if (selectedPersonList.isEmpty()) {
                TableView.TableViewSelectionModel<Person> personSelectionModel = tableView.getSelectionModel();
                selectedPersonList.clear();
                selectedPersonList.addAll(personSelectionModel.getSelectedItems());
            }

            primaryStage.setScene(tableSelectedViewScene());
            primaryStage.show();
        });

        HBox buttonsBox = new HBox(
                selectedPersonButton
        );

        Label footer = new Label("© 2025 Wszystkie prawa zastrzeżone.");

        VBox footerBox = new VBox(
                buttonsBox,
                footer
        );

        root.setTop(menuBox);
        root.setCenter(tableBox);
        root.setBottom(footerBox);

        Scene scene = new Scene(root, widthScene, heightScene);
//        scene.getStylesheets().add(pathStyle);
        scene.getStylesheets().add(getClass().getResource(pathStyle).toExternalForm());

        return scene;
    }

    public Scene tableSelectedViewScene() {
        BorderPane root = new BorderPane();
        HBox menu = MenuView();

        TableView<Person> tableView = new TableView<>();
        tableView.getStyleClass().add("table-view");
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.prefWidthProperty().bind(root.widthProperty().multiply(0.9));
        tableView.prefHeightProperty().bind(root.heightProperty().multiply(0.8));
        VBox.setVgrow(tableView, Priority.ALWAYS);

        tableView.prefWidthProperty().bind(root.widthProperty().multiply(0.9));
        tableView.prefHeightProperty().bind(root.heightProperty().multiply(0.8));

        TableColumn<Person, Integer> idColumn = new TableColumn<>("Lp");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.getStyleClass().add("column-header");
        idColumn.setPrefWidth(10);

        TableColumn<Person, String> peselColumn = new TableColumn<>("PESEL");
        peselColumn.setCellValueFactory(new PropertyValueFactory<>("pesel"));
        peselColumn.getStyleClass().add("column-header");
        peselColumn.setPrefWidth(30);

        TableColumn<Person, String> nameColumn = new TableColumn<>("Imię i nazwisko");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        nameColumn.getStyleClass().add("column-header");

        TableColumn<Person, Boolean> isInListColumn = new TableColumn<>("Nowy");
        isInListColumn.setCellValueFactory(new PropertyValueFactory<>("isInList"));
        isInListColumn.getStyleClass().add("column-header");

        isInListColumn.setCellFactory(col -> new TableCell<Person, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "Nie" : "Tak");
                }
            }
        });

        tableView.getColumns().add(idColumn);
        tableView.getColumns().add(peselColumn);
        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(isInListColumn);

        tableView.setRowFactory(tbl -> new TableRow<Person>() {

            @Override
            protected void updateItem(Person item, boolean empty) {
                super.updateItem(item, empty);
                getStyleClass().add("white-row");
            }
        });

//        FXCollections.sort(selectedPersonList, Comparator.comparing(Person::getPesel));
        UpdateId(selectedPersonList);
        tableView.setItems(selectedPersonList);

        Button backButton = new Button("<<<");
        backButton.setAlignment(Pos.CENTER);
        backButton.setOnAction(e -> {
            if (!selectedPersonList.isEmpty()){
                selectedPersonList.clear();
                zusDraPersons.forEach(person -> person.activeProperty().set(false));
            }

            primaryStage.setScene(tableViewScene());
            primaryStage.show();
        });

//        Button deleteButton = new Button("Usuń");
//        deleteButton.setAlignment(Pos.CENTER);
//        deleteButton.setOnAction(e ->{
//            RemoveColumn(tableView, tableView.getItems());
//        });

        HBox buttonsBox = new HBox(
                backButton
//                ,deleteButton
        );
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(10);

        Label footer = new Label("© 2025 Wszystkie prawa zastrzeżone.");
        footer.setPadding(new Insets(10));

        VBox footerBox = new VBox(
                buttonsBox,
                footer
        );
        footerBox.setAlignment(Pos.CENTER);

        root.setTop(menu);
        root.setCenter(tableView);
        root.setBottom(footerBox);

        Scene scene = new Scene(root, widthScene, heightScene);
//        scene.getStylesheets().add(pathStyle);
        scene.getStylesheets().add(getClass().getResource(pathStyle).toExternalForm());

        return scene;
    }

    public void UpdateId(ObservableList<Person> persons) {
        int id = 0;
        for(Person person : persons) {
            person.setId(id + 1);
            id++;
        }
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

        Scene scene = new Scene(root, widthScene, heightScene);
//        scene.getStylesheets().add(pathStyle);
        scene.getStylesheets().add(getClass().getResource(pathStyle).toExternalForm());

        return scene;
    }
}
