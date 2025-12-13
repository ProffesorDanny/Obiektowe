package com.example.samochod_gui_2;

import controlersClasses.src.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;


public class SamochodController {
        static Samochod currentsam;
        static Pozycja defaultPos = new Pozycja(0,0);
        static Silnik engine1 = new Silnik(100,200,"W40",7000);
        static Sprzeglo s1 = new Sprzeglo(10,3,"ZS1");
        static SkrzyniaBiegow b1 = new SkrzyniaBiegow(5,20,200,"ZSB12",s1);
    static{
        currentsam = new Samochod(1200,"BMW67",defaultPos,engine1,b1,"15001900");
    }

    public ComboBox<Samochod> choiceCarBox;
    ObservableList<Samochod> animals = FXCollections.observableArrayList(
    );


    public void addCarToList(String model, String id, int waga, int maxspeed){
        Samochod sam = new Samochod(waga,model,defaultPos,engine1,b1,id);
        choiceCarBox.getItems().add(sam);
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
    public ImageView carImageView;


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

    public void onAddCarClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DodajSamochod.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        DodajSamochodController secondController = loader.getController();
        secondController.setMainController(this);

        stage.setScene(new Scene(root));
        stage.setTitle("Dodaj Samochod");
        stage.show();
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
    public void initialize() {
        System.out.println("HelloController initialized");
        // Load and set the car image
        Image carImage = new Image(getClass().getResource("SAM.png").toExternalForm());
        System.out.println("Image width: " + carImage.getWidth() + ", height: " + carImage.getHeight());
        carImageView.setImage(carImage);
        carImageView.setFitWidth(30); // Set appropriate dimensions for your image
        carImageView.setFitHeight(20);
        carImageView.setTranslateX(0);
        carImageView.setTranslateY(0);
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


