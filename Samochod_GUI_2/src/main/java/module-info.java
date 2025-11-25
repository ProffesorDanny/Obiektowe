module com.example.samochod_gui_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.samochod_gui_2 to javafx.fxml;
    exports com.example.samochod_gui_2;
}