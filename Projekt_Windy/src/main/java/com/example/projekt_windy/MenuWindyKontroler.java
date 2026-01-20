package com.example.projekt_windy;

import controlersClasses.src.Budynek;
import controlersClasses.src.DataFailureException;
import controlersClasses.src.OpenException;
import controlersClasses.src.Winda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuWindyKontroler {
    @FXML
    private Label elevatorName;
    @FXML
    private ImageView doorImage;
    @FXML
    private TextField actualCargoTextField;
    @FXML
    private TextField elevatorChangeCargoTextField;
    @FXML
    private Label elevatorNumber;
    @FXML
    private Button closeMenu;
    private Budynek budynek;
    private Winda przypisanaWinda;

    public void afterInitialize(){
        przypisanaWinda.setMenuWindyKontroler(this);
        this.actualCargoTextField.setText(String.valueOf(przypisanaWinda.getObciazenie()-przypisanaWinda.getWaga_pod()));
        elevatorName.setText(przypisanaWinda.getNazwa());
    }

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

            budynek.interuptionWindy(przypisanaWinda.getWyskosc(),przypisanaWinda.getId());
            zmienObraz(true);
            przypisanaWinda.setOtwarteDrzwi(true);
    }

    public void onCloseDoorClick(ActionEvent actionEvent) {
        try {
            budynek.resumeWindy(((int) przypisanaWinda.getWyskosc() / 5));
            zmienObraz(false);
            przypisanaWinda.setOtwarteDrzwi(false);
        }
        catch (IndexOutOfBoundsException e) {

        }
    }

    public void zmienObraz(boolean typ) {
        Image carImage;
        if (typ) {
            doorImage.setImage( new Image(getClass().getResource("OtwarteDrzwi.png").toExternalForm()) );
        }
        else {
            doorImage.setImage( new Image(getClass().getResource("ZamknieteDrzwi.png").toExternalForm()) );
        }

    }

    public void zmienObraz(Image carImage) {
        doorImage.setImage(carImage);
    }


    public void setBudynek(Budynek  budynek) {
        this.budynek = budynek;
    }

    public void onCloseMenuClick(ActionEvent actionEvent) {
        przypisanaWinda.setMenuWindyKontroler(null);
        Stage stage = (Stage) closeMenu.getScene().getWindow();
        stage.close();
    }

    public void onRightChangeButtonClick(ActionEvent actionEvent) {
        if (przypisanaWinda.getPredkosc() == 0)
        {   try {
            budynek.zaladunek((int)przypisanaWinda.getWyskosc()/5,false,przypisanaWinda.getId()-1,Integer.parseInt(elevatorChangeCargoTextField.getText()));
            actualCargoTextField.setText(String.valueOf(przypisanaWinda.getObciazenie()-przypisanaWinda.getWaga_pod()));
        }
        catch (DataFailureException | OpenException _) {}

        }
    }

    public void onLeftChangeButtonClick(ActionEvent actionEvent) {
        if (przypisanaWinda.getPredkosc() == 0)
        {   try {
            budynek.zaladunek((int)przypisanaWinda.getWyskosc()/5,true,przypisanaWinda.getId()-1,Integer.parseInt(elevatorChangeCargoTextField.getText()));
            actualCargoTextField.setText(String.valueOf(przypisanaWinda.getObciazenie()-przypisanaWinda.getWaga_pod()));
        }
        catch (DataFailureException | OpenException _) {}

        }
    }
}
