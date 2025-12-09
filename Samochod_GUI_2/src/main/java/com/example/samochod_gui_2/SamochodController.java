package com.example.samochod_gui_2;

import controlersClasses.src.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SamochodController {
        static Samochod currentsam;
    static{
        Pozycja defaultPos = new Pozycja(0,0);
        Silnik engine1 = new Silnik(100,200,"W40",7000);
        Sprzeglo s1 = new Sprzeglo(10,3,"ZS1");
        SkrzyniaBiegow b1 = new SkrzyniaBiegow(5,20,200,"ZSB12",s1);
        currentsam = new Samochod(1200,"BMW67",defaultPos,engine1,b1,15001900);
    }

    public TextField SpeedTextBox;
    public TextField MassTextBox;
    public TextField SerialNumberTextBox;
    public TextField ModelTextBox;
    public TextField runsNameTextField;
    public TextField runsMassTextField;
    public TextField runsPriceTextField;
    public TextField runsRunTextField;
    public TextField engineNameTextField;
    public TextField enginePriceTextField;
    public TextField engineMassTextField;
    public TextField RadialSpeedBox;
    public Button IncreaseSpeedButton;
    public TextField clutchNameTextField;
    public TextField clutchMassTextField;
    public TextField clutchPriceTextField;
    public TextField clutchStateTextField;


    public void onEngineStartClick(ActionEvent actionEvent) {

        currentsam.wlacz();
    }

    public void onEngineStopButtonClick(ActionEvent actionEvent) {
        currentsam.wylacz();
    }

    public void onIncreaseSpeedButtonClick(ActionEvent actionEvent) {
        if (currentsam.getStanWlacznia()) {
            currentsam.DodajGazu();
            SpeedTextBox.setText(String.valueOf(currentsam.getAktpredkosc()));
        }

    }

    public void onClatchUpClick(ActionEvent actionEvent) {
        currentsam.getSkrzyniaBiegow().getSprzeglo().wcisnij();
    }

    public void onClutchDownClick(ActionEvent actionEvent) {
        currentsam.getSkrzyniaBiegow().getSprzeglo().zwolnj();

    }

    public void onAddCarClick(ActionEvent actionEvent) {
    }

    public void refresh()
    {
        clutchMassTextField.setText(String.valueOf(currentsam.getSkrzyniaBiegow().getSprzeglo().getWaga()));
        clutchNameTextField.setText(currentsam.getSkrzyniaBiegow().getSprzeglo().getNazwa());
        clutchPriceTextField.setText(String.valueOf(currentsam.getSkrzyniaBiegow().getSprzeglo().getCena()));
        if (currentsam.getSkrzyniaBiegow().getSprzeglo().getStanSprzejla())
        {
            clutchStateTextField.setText("Up");
        }
        else {
            clutchStateTextField.setText("Down");
        }
        engineMassTextField.setText(String.valueOf(currentsam.getSilnik().getWaga()));
        enginePriceTextField.setText(String.valueOf(currentsam.getSilnik().getCena()));
        engineNameTextField.setText(String.valueOf(currentsam.getSilnik().getNazwa()));
        RadialSpeedBox.setText(String.valueOf(currentsam.getSilnik().getObroty()));
        runsMassTextField.setText(String.valueOf(currentsam.getSkrzyniaBiegow().getWaga()));
        runsNameTextField.setText(currentsam.getSkrzyniaBiegow().getNazwa());
        runsPriceTextField.setText(String.valueOf(currentsam.getSkrzyniaBiegow().getCena()));
        runsRunTextField.setText(String.valueOf(currentsam.getSkrzyniaBiegow().getAktualnyBieg()));
        SpeedTextBox.setText(String.valueOf(currentsam.getAktpredkosc()));
    }

    public void onDecreaseRunClick(ActionEvent actionEvent) {
        currentsam.getSkrzyniaBiegow().zmniejszBieg();
    }

    public void onIncreaseRunClick(ActionEvent actionEvent) {
        currentsam.getSkrzyniaBiegow().zwiekrzBieg();
    }

    public void onDecreaseSpeedButtonClick(ActionEvent actionEvent) {
        if (currentsam.getStanWlacznia()) {
            currentsam.UpuscGazu();
            SpeedTextBox.setText(String.valueOf(currentsam.getAktpredkosc()));
        }

    }
}


