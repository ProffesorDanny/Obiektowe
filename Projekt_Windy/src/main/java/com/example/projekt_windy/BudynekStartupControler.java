package com.example.projekt_windy;

import controlersClasses.src.Silnik;
import controlersClasses.src.Winda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BudynekStartupControler {
    @FXML
    private ComboBox<String> elevatorChoiceBox;
    @FXML
    private ComboBox<Silnik> engineChoiceBox;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField defaultMassTextField;
    @FXML
    private Button commitButton;
    @FXML
    private TextField massTextField;


    private BudynekKontroler budynekKontroler;
    private ArrayList<Winda> windy;

    public void initialize()
    {
        Silnik si1 = new Silnik(80, 1000, "TowarowySilnk", 10);
        Silnik si2 = new Silnik(500, 5000, "OsobowySilnik", 15);
        engineChoiceBox.getItems().addAll(si1,si2);
        elevatorChoiceBox.getItems().addAll("Winda1", "Winda2");

    }

    public void onCommitButtonClick(ActionEvent actionEvent) {

        if (windy.get(1) == null || windy.get(0) == null)
        {
            System.out.println("Dodaj wszystkie windy");
            return;
        }
        Stage stage = (Stage) commitButton.getScene().getWindow();
        stage.close();

    }

    public void setMainController(BudynekKontroler budynekKontroler) {
        this.budynekKontroler = budynekKontroler;
    }

    public void setWindy(ArrayList<Winda> windy) {
        this.windy = windy;
    }

    public void onCommitElevatorButtonClick(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        try {
            int mass = Integer.valueOf(massTextField.getText());
            int defaultMass = Integer.valueOf(defaultMassTextField.getText());
            Silnik silnik = engineChoiceBox.getValue().Copy();
            if(silnik == null || mass < defaultMass || defaultMass < 0){
                throw new NumberFormatException();
            }


        if (elevatorChoiceBox.getValue().equals("Winda1")) {
            windy.set(0,new Winda(defaultMass,mass,name,silnik));
        }
        else {
            windy.set(1,new Winda(defaultMass,mass,name,silnik));
        }
        }
        catch (NumberFormatException e)
        {
            System.out.println("Złe dane");
        }
    }
}
