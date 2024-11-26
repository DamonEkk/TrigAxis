module com.example.trigaxis {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.trigaxis to javafx.fxml;
    exports com.example.trigaxis;
}