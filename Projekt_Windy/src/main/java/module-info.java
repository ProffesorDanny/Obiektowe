module com.example.projekt_windy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projekt_windy to javafx.fxml;
    exports com.example.projekt_windy;
}