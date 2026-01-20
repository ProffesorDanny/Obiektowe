package com.example.projekt_windy;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import controlersClasses.src.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.util.ArrayList;

public class BudynekKontroler implements Listener {
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
    public void initialize() {
        elevatorText = new TextField[] {elevatorParterPackegesTextField, elevatorFirstFloorPackagesTextField, elevatorSecondFloorPackegesTextField,elevatorThirdFloorPackegesTextField};
        rightText = new TextField[] {rightParterFloorPackegesTextField, rightFirstFloorPackagesTextField, rightSecondFloorPackegesTextField, rightThirdFloorPackegesTextField};
        obrazyWind = new ImageView[] {elevatorOne, elevatorTwo};
        budynek = new Budynek(2,4,this);
        for (int i = 0; i < 4; i++) {
            final int index = i;
            rightText[i].setOnAction(event -> {
                try {
                    if (Integer.parseInt(rightText[index].getText()) < 0) {
                        throw new NumberFormatException();
                    }
                    budynek.setTowardoprzeniesieniaPietra(Integer.parseInt(rightText[index].getText()), index);
                    System.out.println("Cos");
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

    public void onThirdFloorUpButtonClick(ActionEvent actionEvent) {
       // budynek.przywolajWinde(true,3);
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

    public void onParterDownButtonClick(ActionEvent actionEvent) {

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
