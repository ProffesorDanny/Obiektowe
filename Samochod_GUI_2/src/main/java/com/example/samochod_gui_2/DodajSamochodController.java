package com.example.samochod_gui_2;

import controlersClasses.src.Silnik;
import controlersClasses.src.SkrzyniaBiegow;
import controlersClasses.src.Sprzeglo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class DodajSamochodController {
    private SamochodController mainController;
    public Button commitButton;
    @FXML
    private TextField idNumber;
    @FXML
    private TextField weight;
    @FXML
    private TextField model;
    @FXML
    private TextField maxSpeed;
    @FXML
    private ComboBox<Silnik> engineChoice;
    @FXML
    private ComboBox<SkrzyniaBiegow> runsChoice;

    public void initialize()
    {
        Sprzeglo s1 = new Sprzeglo(200,50,"s1");
        Sprzeglo s2 = new Sprzeglo(2000,80,"s2");
        SkrzyniaBiegow b1 = new SkrzyniaBiegow(5,210,800,"b1",0.044,s1);
        SkrzyniaBiegow b2 = new SkrzyniaBiegow(6,240,1200,"b2",0.033,s2);
        Silnik si1 = new Silnik(400,2000,"si1",1000);
        Silnik si2 = new Silnik(500,5000,"si2",2000);
        engineChoice.getItems().add(si1);
        engineChoice.getItems().add(si2);
        runsChoice.getItems().add(b1);
        runsChoice.getItems().add(b2);
    }

    public void setMainController(SamochodController controller) {

        this.mainController = controller;
    }

    public void onCommitButtonClick(ActionEvent actionEvent) {
        String model = this.model.getText();
        String registrationNumber = this.idNumber.getText().toUpperCase();
        int weight;
        int maxSpeed;
        Silnik silnik = this.engineChoice.getValue();
        SkrzyniaBiegow skr = this.runsChoice.getValue();
        try {
            weight = Integer.parseInt(this.weight.getText());
            maxSpeed = Integer.parseInt(this.maxSpeed.getText());
            if (model.isEmpty() || registrationNumber.isEmpty() || weight <= 0 || maxSpeed <= 0) {
                throw new NumberFormatException();
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Nieprawidłowe dane.");
            return;
        }
        try {
            mainController.addCarToList(model,registrationNumber,weight,maxSpeed,silnik,skr);
        }
        catch (NullPointerException e) {
            System.out.println("Nieprawidłowe dane.");
            return;
        }
        Stage stage = (Stage) commitButton.getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) commitButton.getScene().getWindow();
        stage.close();
    }

}
