package com.example.samochod_gui_2;

import controlersClasses.src.*;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class SamochodController {
        static Samochod sam1;
    static{
        Pozycja defaultPos = new Pozycja(0,0);
        Silnik engine1 = new Silnik(100,200,"W40",7000);
        Sprzeglo s1 = new Sprzeglo(10,3,"ZS1");
        SkrzyniaBiegow b1 = new SkrzyniaBiegow(5,20,200,"ZSB12",s1);
        sam1 = new Samochod(1200,"BMW67",defaultPos,engine1,b1,15001900);
    }

    public TextField SpeedTextBox;

    public void onEngineStartClick(ActionEvent actionEvent) {

        sam1.wlacz();
    }

    public void onEngineStopButtonClick(ActionEvent actionEvent) {
        sam1.wylacz();
    }

    public void onIncreaseSpeedButtonClick(ActionEvent actionEvent) {
        if (sam1.getStanWlacznia()) {
            sam1.DodajGazu();
            SpeedTextBox.setText(String.valueOf(sam1.getAktpredkosc()));
        }

    }
}
