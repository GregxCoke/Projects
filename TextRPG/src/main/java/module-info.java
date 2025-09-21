module com.example.gregfinalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gregfinalproject to javafx.fxml;
    exports com.example.gregfinalproject;
}