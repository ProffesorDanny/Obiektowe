package com.example.projekt_windy;

import controlersClasses.src.Silnik;
import controlersClasses.src.Winda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BudynekStartupControler {
    @FXML
    private Button commitButton;
    @FXML
    private ComboBox engineOne;
    @FXML
    private ComboBox elevaorOne;
    @FXML
    private ComboBox engineTwo;
    @FXML
    private ComboBox elevatorTwo;

    private BudynekKontroler budynekKontroler;
    private ArrayList<Winda> windy;

    public void onCommitButtonClick(ActionEvent actionEvent) {
        for(int i = 0; i < 2; i++){
            Silnik silnik = new Silnik(80, 1000, "DeafultSilnik", 10);
            windy.add(new Winda(200,600,"DefaultWinda", silnik));
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
}
