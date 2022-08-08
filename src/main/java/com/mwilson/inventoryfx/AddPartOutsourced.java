package com.mwilson.inventoryfx;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddPartOutsourced implements Initializable {
    public TextField fieldID;
    public TextField fieldName;
    public TextField fieldInv;
    public TextField fieldCost;
    public TextField fieldMax;
    public TextField fieldCompanyName;
    public TextField fieldMin;
    public RadioButton inHouse;
    public RadioButton outsourced;
    public Button save;
    public Button cancel;

    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    public void InHouseSelected(ActionEvent actionEvent) {
    }

    public void OutsourcedSelected(ActionEvent actionEvent) {
    }

    public void OnSaveClicked(ActionEvent actionEvent) {
    }

    public void OnCancelClicked(ActionEvent actionEvent) {
    }
}
