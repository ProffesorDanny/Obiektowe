package com.example.projekt_windy;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import controlersClasses.src.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.ArrayList;

public class BudynekKontroler implements Listener {
    public ImageView elevatorOne;
    private Silnik silnik;
    private Winda winda1;
    ArrayList<Pietro> pietra = new ArrayList<>();
    public void initialize() {
        silnik = new Silnik(80,1000,"Silneks",10);
        winda1 = new Winda(200,600,"Windeks",silnik);
        winda1.addListener(this);
        for (int i = 0; i < 4; i++) {
            pietra.add(new Pietro("Pietro" + Integer.toString(i),i));
            pietra.get(i).addWinda(winda1);
        }
        Thread t = new Thread(winda1);
        winda1.setThread(t);
        winda1.setPietra(this.pietra);
        t.start();
    }
    @Override
    public void action(Object... args)
    {
        Platform.runLater(()->{
            elevatorOne.setTranslateY(winda1.getWyskosc()*(-50));
        });
    }

    public void onThirdFloorUpButtonClick(ActionEvent actionEvent) {
        pietra.get(3).PrzywolajWinde(true);
    }

    public void onThirdFloorDownButtonClick(ActionEvent actionEvent) {
        pietra.get(3).PrzywolajWinde(false);
    }

    public void onSecondFloorUpButtonClick(ActionEvent actionEvent) {

        pietra.get(2).PrzywolajWinde(true);
    }

    public void onSecondFloorDownButtonClick(ActionEvent actionEvent) {
        pietra.get(2).PrzywolajWinde(false);
    }

    public void onFirstFloorUpButtonClick(ActionEvent actionEvent) {
        pietra.get(1).PrzywolajWinde(true);
    }

    public void onFirstFloorDownButtonClick(ActionEvent actionEvent) {

        pietra.get(1).PrzywolajWinde(false);
    }

    public void onParterDownButtonClick(ActionEvent actionEvent) {

    }

    public void onParterUpButtonClick(ActionEvent actionEvent) {
        pietra.get(0).PrzywolajWinde(true);
    }

    public void onFirstElevatorMenuClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuWindy.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        MenuWindyKontroler secondController = loader.getController();
        secondController.setMainController(this);
        secondController.setPrzypisanaWinda(winda1);
        secondController.setElevatorNumber(winda1.getId());

        stage.setScene(new Scene(root));
        stage.setTitle("MenuWindy");
        stage.show();
    }
}
