package com.example.samochod_gui_2;

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
    private ComboBox engineChoice;
    @FXML
    private ComboBox runsChoice;

    public void setMainController(SamochodController controller) {

        this.mainController = controller;
    }

    public void onCommitButtonClick(ActionEvent actionEvent) {
        String model = this.model.getText();
        String registrationNumber = this.idNumber.getText();
        int weight;
        int maxSpeed;
        try {
            weight = Integer.parseInt(this.weight.getText());
            maxSpeed = Integer.parseInt(this.maxSpeed.getText());
        }
        catch (NumberFormatException e) {
            System.out.println("Nieprawidłowe dane.");
            return;
        }

        mainController.addCarToList(model,registrationNumber,weight,maxSpeed);
        Stage stage = (Stage) commitButton.getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonClick(ActionEvent actionEvent) {
        Stage stage = (Stage) commitButton.getScene().getWindow();
        stage.close();
    }
}
