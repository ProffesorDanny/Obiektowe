package com.example.samochod_gui_2;

import controlersClasses.src.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class SamochodController implements Listener {
        static Samochod currentsam;
        static Pozycja defaultPos = new Pozycja(0,0);
        static Silnik engine1 = new Silnik(100,200,"W40",7000);
        static Sprzeglo s1 = new Sprzeglo(10,3,"ZS1");


    public ComboBox<Samochod> choiceCarBox;
    public VBox mapa;
    ObservableList<Samochod> cars = FXCollections.observableArrayList(
    );


    public void addCarToList(String model, String id, int waga, int maxspeed, Silnik silnik, SkrzyniaBiegow skr) {
        Pozycja pozycja = new Pozycja(defaultPos.getX(),defaultPos.getY());
        Samochod sam = new Samochod(waga,model,pozycja,silnik,skr,id);
        sam.addListener(this);
        choiceCarBox.getItems().add(sam);
        choiceCarBox.getSelectionModel().selectFirst();
        Thread sam_t = new Thread(sam);
        sam_t.start();
        refresh();
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
        refresh();
    }

    public void onEngineStopButtonClick(ActionEvent actionEvent) {
        currentsam.wylacz();
        refresh();
    }

    public void onIncreaseSpeedButtonClick(ActionEvent actionEvent) {
        if (currentsam.getStanWlacznia()) {
            currentsam.DodajGazu();
            SpeedTextBox.setText(String.valueOf(currentsam.getAktpredkosc()));
        }
        refresh();

    }

    public void onClatchUpClick(ActionEvent actionEvent) {
        currentsam.getSkrzyniaBiegow().getSprzeglo().wcisnij();
        refresh();
    }

    public void onClutchDownClick(ActionEvent actionEvent) {
        currentsam.getSkrzyniaBiegow().getSprzeglo().zwolnj();
        refresh();

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
        ModelTextBox.setText(currentsam.getModel());
        MassTextBox.setText(String.valueOf(currentsam.getWaga()));
        SerialNumberTextBox.setText(currentsam.getNrRejestru());
        Platform.runLater(()->{
            carImageView.setTranslateX(currentsam.getPozycja().getX());
            carImageView.setTranslateY(currentsam.getPozycja().getY());
        });

    }
    public void reset()
    {
        clutchMassTextField.setText("");
        clutchNameTextField.setText("");
        clutchPriceTextField.setText("");
        engineMassTextField.setText("");
        enginePriceTextField.setText("");
        engineNameTextField.setText("");
        RadialSpeedBox.setText("");
        runsMassTextField.setText("");
        runsNameTextField.setText("");
        runsPriceTextField.setText("");
        runsRunTextField.setText("");
        SpeedTextBox.setText("");
        ModelTextBox.setText("");
        MassTextBox.setText("");
        SerialNumberTextBox.setText("");
        clutchStateTextField.setText("");
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
        mapa.setOnMouseClicked(event -> {
            double x = event.getX();
            double y = event.getY();
            Pozycja nowaPozycja = new Pozycja(x, y);
            currentsam.setAktualykurs(nowaPozycja);
            refresh();
        });
        choiceCarBox.setItems(cars);
        choiceCarBox.setOnAction(event -> {
            currentsam = choiceCarBox.getSelectionModel().getSelectedItem();
            refresh();
        });
    }

    public void onDecreaseRunClick(ActionEvent actionEvent) {
        currentsam.getSkrzyniaBiegow().zmniejszBieg();
        refresh();
    }

    public void onIncreaseRunClick(ActionEvent actionEvent) {
        currentsam.getSkrzyniaBiegow().zwiekrzBieg();
        refresh();
    }

    public void onDecreaseSpeedButtonClick(ActionEvent actionEvent) {
        if (currentsam.getStanWlacznia()) {
            currentsam.UpuscGazu();
            SpeedTextBox.setText(String.valueOf(currentsam.getAktpredkosc()));
        }
        refresh();

    }


    public void onWipeCarClick(ActionEvent actionEvent) {
        cars.remove(currentsam);
        choiceCarBox.getSelectionModel().selectFirst();
        if (currentsam == null)
        {
            reset();
        }
    }
    public void pokazBlad(String wiadomosc)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Błąd");
        alert.setHeaderText(null);
        alert.setContentText(wiadomosc);
        alert.showAndWait();
    }
    @Override
    public void update() {
        refresh();
    }
}


