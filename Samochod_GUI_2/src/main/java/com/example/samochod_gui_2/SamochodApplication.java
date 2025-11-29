package com.example.samochod_gui_2;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SamochodApplication extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SamochodApplication.class.getResource("Samochod.fxml"));
        Scene scene = new Scene((Parent)fxmlLoader.load(), (double)320.0F, (double)240.0F);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
