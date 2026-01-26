package com.example.projekt_windy;

import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.event.ActionEvent;
import controlersClasses.src.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.ArrayList;

public class BudynekKontroler implements Listener {
    @FXML
    private TextField symulationSpeed;
    @FXML
    private ImageView elevatorOne;
    @FXML
    private ImageView elevatorTwo;
    @FXML
    private TextField rightThirdFloorPackegesTextField;
    @FXML
    private TextField elevatorThirdFloorPackegesTextField;
    @FXML
    private TextField rightSecondFloorPackegesTextField;
    @FXML
    private TextField elevatorSecondFloorPackegesTextField;
    @FXML
    private TextField elevatorParterPackegesTextField;
    @FXML
    private TextField rightParterFloorPackegesTextField;
    @FXML
    private TextField elevatorFirstFloorPackagesTextField;
    @FXML
    private TextField rightFirstFloorPackagesTextField;
    private TextField[] rightText;
    private TextField[] elevatorText;
    private ImageView[] obrazyWind;
    private Budynek budynek;

    public void initialize() throws IOException {
        elevatorText = new TextField[] {elevatorParterPackegesTextField, elevatorFirstFloorPackagesTextField, elevatorSecondFloorPackegesTextField,elevatorThirdFloorPackegesTextField};
        rightText = new TextField[] {rightParterFloorPackegesTextField, rightFirstFloorPackagesTextField, rightSecondFloorPackegesTextField, rightThirdFloorPackegesTextField};
        obrazyWind = new ImageView[] {elevatorOne, elevatorTwo};
        ArrayList<Winda> windy = new ArrayList<>();
        setup(windy);
        budynek = new Budynek(windy,4,this,10);
        budynek.addListener(this);
        for (int i = 0; i < 4; i++) {
            final int index = i;
            rightText[i].setOnAction(event -> {
                try {
                    if (Integer.parseInt(rightText[index].getText()) < 0) {
                        throw new NumberFormatException();
                    }
                    budynek.setTowardoprzeniesieniaPietra(Integer.parseInt(rightText[index].getText()), index);
                } catch (NumberFormatException e) {
                    System.out.println("Nieprawidłowe dane");
                    rightText[index].setText(String.valueOf(budynek.getTowarDoprzeniesieniaWindy(index)));
                }

            });
            rightText[i].focusedProperty().addListener((observable, wasFocused, isNowFocused) -> {
                if (!isNowFocused) {
                    budynek.zmienEdytowaniePietra(false,index);
                }
            });
            rightText[i].setOnMouseClicked(event -> {
                budynek.zmienEdytowaniePietra(true,index);
            });
        }
        symulationSpeed.setOnAction(event -> {
            try {
                if (Integer.parseInt(symulationSpeed.getText()) < 0) {
                    throw new NumberFormatException();
                }
                budynek.setSzybkoscSymulacji(Integer.parseInt(symulationSpeed.getText()));
            }
            catch (NumberFormatException e) {
                System.out.println("Nieprawidłowe dane");
            }
        });
    }
    @Override
    public void action(Object... args)
    {
        StanBudynku stanBudynku = budynek.getAktualnyStan();
        Platform.runLater(()->{
            for (int i=0 ; i < stanBudynku.wysokosci.size() ; i++) {
                obrazyWind[i].setTranslateY(stanBudynku.wysokosci.get(i)*(-50));
            }

        for (int i = 0 ; i<4;i++) {
            elevatorText[i].setText(String.valueOf(stanBudynku.towary.get(i)));
            if (!stanBudynku.stany.get(i)) {
                rightText[i].setText(String.valueOf(budynek.getTowarDoprzeniesieniaWindy(i)));
            }
        }

        });

    }

    private void setup(ArrayList<Winda> windy) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BudynekStartup.fxml"));
        Parent root = loader.load();
        BudynekStartupControler controller = loader.getController();
        controller.setMainController(this);
        windy.add(null);
        windy.add(null);
        controller.setWindy(windy);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.showAndWait();
        System.out.println("Okno zamknięte, działam dalej!");
    }



    public void onThirdFloorDownButtonClick(ActionEvent actionEvent) {
        budynek.przywolajWinde(false,3);
    }

    public void onSecondFloorUpButtonClick(ActionEvent actionEvent) {

        budynek.przywolajWinde(true,2);
    }

    public void onSecondFloorDownButtonClick(ActionEvent actionEvent) {
        budynek.przywolajWinde(false,2);
    }

    public void onFirstFloorUpButtonClick(ActionEvent actionEvent) {
        budynek.przywolajWinde(true,1);
    }

    public void onFirstFloorDownButtonClick(ActionEvent actionEvent) {

        budynek.przywolajWinde(false,1);
    }


    public void onParterUpButtonClick(ActionEvent actionEvent) {
        budynek.przywolajWinde(true,0);
    }

    public void onFirstElevatorMenuClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuWindy.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        MenuWindyKontroler secondController = loader.getController();
        secondController.setBudynek(budynek);
        budynek.ustawWindeNaKontroler(1,secondController);
        secondController.afterInitialize();
        stage.setScene(new Scene(root));
        stage.setTitle("MenuWindy");
        stage.show();
    }

    public void onSecondElevatorMenuClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuWindy.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        MenuWindyKontroler secondController = loader.getController();
        secondController.setBudynek(budynek);
        budynek.ustawWindeNaKontroler(2,secondController);
        secondController.afterInitialize();
        stage.setScene(new Scene(root));
        stage.setTitle("MenuWindy");
        stage.show();
    }

    public void onFirstFloorRightMoveButtonClick(ActionEvent actionEvent) {
        budynek.setKierunekZaladunkuPietra(false,1);
    }

    public void onFirstFloorLeftMoveButtonClick(ActionEvent actionEvent) {
        budynek.setKierunekZaladunkuPietra(true,1);
    }


    public void onParterFloorLeftMoveButtonClick(ActionEvent actionEvent) {
        budynek.setKierunekZaladunkuPietra(true,0);
    }

    public void onParterFloorRightMoveButtonClick(ActionEvent actionEvent) {
        budynek.setKierunekZaladunkuPietra(false,0);
    }

    public void onSecondFloorRightMoveButtonClick(ActionEvent actionEvent) {
        budynek.setKierunekZaladunkuPietra(false,2);
    }

    public void onSecondFloorLeftMoveButtonClick(ActionEvent actionEvent) {
        budynek.setKierunekZaladunkuPietra(true,2);
    }

    public void onThirdFloorLeftMoveButtonClick(ActionEvent actionEvent) {
        budynek.setKierunekZaladunkuPietra(true,3);
    }

    public void onThirdFloorRightMoveButtonClick(ActionEvent actionEvent) {
        budynek.setKierunekZaladunkuPietra(false,3);
    }


}
