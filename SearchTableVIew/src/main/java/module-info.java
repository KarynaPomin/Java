module com.example.searchtableview {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.searchtableview to javafx.fxml;
    exports com.example.searchtableview;
}