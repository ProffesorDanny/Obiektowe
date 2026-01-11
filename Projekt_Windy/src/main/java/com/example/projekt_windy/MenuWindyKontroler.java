package com.example.projekt_windy;

import controlersClasses.src.Winda;
import javafx.event.ActionEvent;

public class MenuWindyKontroler {

    private BudynekKontroler mainCotroler;
    private Winda przypisanaWinda;

    public void setPrzypisanaWinda(Winda przypisanaWinda) {
        this.przypisanaWinda = przypisanaWinda;
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
    }

    public void onCloseDoorClick(ActionEvent actionEvent) {
    }

    public void setMainController(BudynekKontroler budynekKontroler) {
        this.mainCotroler = budynekKontroler;
    }
}
