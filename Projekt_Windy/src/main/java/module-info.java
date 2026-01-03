module com.example.projekt_windy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;


    opens com.example.projekt_windy to javafx.fxml;
    exports com.example.projekt_windy;
}