package com.example.projekt_windy;

import controlersClasses.src.Winda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuWindyKontroler {

    @FXML
    private Label elevatorNumber;
    @FXML
    private Button closeMenu;
    private BudynekKontroler mainCotroler;
    private Winda przypisanaWinda;


    public void setPrzypisanaWinda(Winda przypisanaWinda) {
        this.przypisanaWinda = przypisanaWinda;
    }

    public void setElevatorNumber(int id) {
        this.elevatorNumber.setText(String.valueOf(id));
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
        //inplementacja tego po implementacji piętra
    }

    public void onCloseDoorClick(ActionEvent actionEvent) {
        przypisanaWinda.uruchom();
    }

    public void setMainController(BudynekKontroler budynekKontroler) {
        this.mainCotroler = budynekKontroler;
    }

    public void onCloseMenuClick(ActionEvent actionEvent) {
        Stage stage = (Stage) closeMenu.getScene().getWindow();
        stage.close();
    }
}
