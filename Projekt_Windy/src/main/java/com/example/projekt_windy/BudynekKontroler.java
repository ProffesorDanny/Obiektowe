package com.example.projekt_windy;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import controlersClasses.src.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.WindowAdapter;
import java.util.ArrayList;

public class BudynekKontroler implements Listener {
    public ImageView elevatorOne;
    Silnik silnik;
    Winda winda1;
    ArrayList<Pietro> pietra = new ArrayList<>();
    public void initialize() {
        silnik = new Silnik(80,1000,"Silneks",10);
        winda1 = new Winda(200,600,"Windeks",silnik);
        winda1.addListener(this);
        for (int i = 0; i < 4; i++) {
            pietra.add(new Pietro("Pietro" + Integer.toString(i),i));
            pietra.get(i).addListener(winda1);
        }
        Thread t = new Thread(winda1);
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
}
