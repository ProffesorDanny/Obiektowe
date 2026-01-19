package com.example.projekt_windy;

import controlersClasses.src.Budynek;
import controlersClasses.src.Winda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuWindyKontroler {
    @FXML
    private TextField actualCargoTextField;
    @FXML
    private TextField elevatorChangeCargoTextField;
    @FXML
    private Label elevatorNumber;
    @FXML
    private Button closeMenu;
    private BudynekKontroler mainCotroler;
    private Budynek budynek;
    private Winda przypisanaWinda;


    public void setPrzypisanaWinda(Winda przypisanaWinda) {
        this.przypisanaWinda = przypisanaWinda;
    }

    public void setElevatorNumber(int id) {
        this.elevatorNumber.setText(String.valueOf(id));
    }

    public void setActualCargoTextField(int waga) {
        this.actualCargoTextField.setText(String.valueOf(waga));
    }

    public void onThirdFloorSelectClick(ActionEvent actionEvent) {
        przypisanaWinda.setNewTarget(3,true);
    }

    public void onSecondFloorSelectClick(ActionEvent actionEvent) {
        przypisanaWinda.setNewTarget(2,true);
    }

    public void onfirstFloorSelectClick(ActionEvent actionEvent) {
        przypisanaWinda.setNewTarget(1,true);
    }

    public void onParterSelectClick(ActionEvent actionEvent) {
        przypisanaWinda.setNewTarget(0,true);
    }

    public void onOpenDoorClick(ActionEvent actionEvent) {

            budynek.interuptionWindy(przypisanaWinda.getWyskosc(),przypisanaWinda.getId());
    }

    public void onCloseDoorClick(ActionEvent actionEvent) {
        try {
            budynek.resumeWindy(((int) przypisanaWinda.getWyskosc() / 5));
        }
        catch (IndexOutOfBoundsException e) {

        }
    }
    //Test

    public void setMainController(BudynekKontroler budynekKontroler) {
        this.mainCotroler = budynekKontroler;
    }

    public void setBudynek(Budynek  budynek) {
        this.budynek = budynek;
    }

    public void onCloseMenuClick(ActionEvent actionEvent) {
        Stage stage = (Stage) closeMenu.getScene().getWindow();
        stage.close();
    }

    public void onRightChangeButtonClick(ActionEvent actionEvent) {
        if (przypisanaWinda.getPredkosc() == 0)
        {   try {
            budynek.zaladunek((int)przypisanaWinda.getWyskosc()/5,false,przypisanaWinda.getId()-1,Integer.parseInt(elevatorChangeCargoTextField.getText()));
            actualCargoTextField.setText(String.valueOf(przypisanaWinda.getObciazenie()-przypisanaWinda.getWaga_pod()));
        }
        catch (Exception e) {
            System.out.println("Nieprawidłowe dane");
        }

        }
    }

    public void onLeftChangeButtonClick(ActionEvent actionEvent) {
        if (przypisanaWinda.getPredkosc() == 0)
        {   try {
            budynek.zaladunek((int)przypisanaWinda.getWyskosc()/5,true,przypisanaWinda.getId()-1,Integer.parseInt(elevatorChangeCargoTextField.getText()));
            actualCargoTextField.setText(String.valueOf(przypisanaWinda.getObciazenie()-przypisanaWinda.getWaga_pod()));
        }
        catch (Exception e) {
            System.out.println("Nieprawidłowe dane");
        }

        }
    }
}
