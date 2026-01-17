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
    private Silnik silnik;
    private Winda winda1;
    ArrayList<Pietro> pietra = new ArrayList<>();
    public void initialize() {
        elevatorText = new TextField[] {elevatorParterPackegesTextField, elevatorFirstFloorPackagesTextField, elevatorSecondFloorPackegesTextField,elevatorThirdFloorPackegesTextField};
        rightText = new TextField[] {rightParterFloorPackegesTextField, rightFirstFloorPackagesTextField, rightSecondFloorPackegesTextField, rightThirdFloorPackegesTextField};
        silnik = new Silnik(80,1000,"Silneks",10);
        winda1 = new Winda(200,600,"Windeks",silnik);
        winda1.addListener(this);
        for (int i = 0; i < 4; i++) {
            pietra.add(new Pietro("Pietro" + Integer.toString(i),i));
            pietra.get(i).addWinda(winda1);
            Thread t1 = new Thread(pietra.get(i));
            t1.start();
        }
        Thread t = new Thread(winda1);
        winda1.setThread(t);
        winda1.setPietra(this.pietra);
        t.start();
        for (int i = 0; i < 4; i++) {
            final int index = i;
            rightText[i].setOnAction(event -> {
                try {
                    if (Integer.parseInt(rightText[index].getText()) < 0) {
                        throw new NumberFormatException();
                    }
                    pietra.get(index).setTowardoprzeniesienia(Integer.parseInt(rightText[index].getText()));
                    System.out.println("Cos");
                } catch (NumberFormatException e) {
                    System.out.println("Nieprawidłowe dane");
                    rightText[index].setText(String.valueOf(pietra.get(index).getTowardoprzeniesienia()));
                }

            });
            rightText[i].focusedProperty().addListener((observable, wasFocused, isNowFocused) -> {
                if (!isNowFocused) {
                    pietra.get(index).setEdytowanie(false);
                }
            });
            rightText[i].setOnMouseClicked(event -> {
                pietra.get(index).setEdytowanie(true);
            });
        }
    }
    @Override
    public void action(Object... args)
    {
        Platform.runLater(()->{
            elevatorOne.setTranslateY(winda1.getWyskosc()*(-50));

        for (int i = 0 ; i<4;i++) {
            elevatorText[i].setText(String.valueOf(pietra.get(i).getTowar()));
            if (!pietra.get(i).getEdytowanie()) {
                rightText[i].setText(String.valueOf(pietra.get(i).getTowardoprzeniesienia()));
            }
        }

        });


    }

    public void onThirdFloorUpButtonClick(ActionEvent actionEvent) {
        pietra.get(3).PrzywolajWinde(true);
    }

    public void onThirdFloorDownButtonClick(ActionEvent actionEvent) {
        pietra.get(3).PrzywolajWinde(false);
    }

    public void onSecondFloorUpButtonClick(ActionEvent actionEvent) {

        pietra.get(2).PrzywolajWinde(true);
    }

    public void onSecondFloorDownButtonClick(ActionEvent actionEvent) {
        pietra.get(2).PrzywolajWinde(false);
    }

    public void onFirstFloorUpButtonClick(ActionEvent actionEvent) {
        pietra.get(1).PrzywolajWinde(true);
    }

    public void onFirstFloorDownButtonClick(ActionEvent actionEvent) {

        pietra.get(1).PrzywolajWinde(false);
    }

    public void onParterDownButtonClick(ActionEvent actionEvent) {

    }

    public void onParterUpButtonClick(ActionEvent actionEvent) {
        pietra.get(0).PrzywolajWinde(true);
    }

    public void onFirstElevatorMenuClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuWindy.fxml"));
        Stage stage = new Stage();
        Parent root = loader.load();
        MenuWindyKontroler secondController = loader.getController();
        secondController.setMainController(this);
        secondController.setPrzypisanaWinda(winda1);
        secondController.setElevatorNumber(winda1.getId());
        secondController.setActualCargoTextField(winda1.getObciazenie()-winda1.getWaga_pod());

        stage.setScene(new Scene(root));
        stage.setTitle("MenuWindy");
        stage.show();
    }

    public void onFirstFloorRightMoveButtonClick(ActionEvent actionEvent) {
        pietra.get(1).setKierunekZaladunku(false);
    }

    public void onFirstFloorLeftMoveButtonClick(ActionEvent actionEvent) {
        pietra.get(1).setKierunekZaladunku(true);
    }


    public void onParterFloorLeftMoveButtonClick(ActionEvent actionEvent) {
        pietra.get(0).setKierunekZaladunku(true);
    }

    public void onParterFloorRightMoveButtonClick(ActionEvent actionEvent) {
        pietra.get(0).setKierunekZaladunku(false);
    }

    public void onSecondFloorRightMoveButtonClick(ActionEvent actionEvent) {
        pietra.get(2).setKierunekZaladunku(false);
    }

    public void onSecondFloorLeftMoveButtonClick(ActionEvent actionEvent) {
        pietra.get(2).setKierunekZaladunku(true);
    }

    public void onThirdFloorLeftMoveButtonClick(ActionEvent actionEvent) {
        pietra.get(3).setKierunekZaladunku(true);
    }

    public void onThirdFloorRightMoveButtonClick(ActionEvent actionEvent) {
        pietra.get(3).setKierunekZaladunku(false);
    }
}
