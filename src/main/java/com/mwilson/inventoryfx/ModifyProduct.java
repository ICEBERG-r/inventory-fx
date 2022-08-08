package com.mwilson.inventoryfx;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyProduct implements Initializable {
    public TextField fieldID;
    public TextField fieldName;
    public TextField fieldInv;
    public TextField fieldCost;
    public TextField fieldMax;
    public TextField fieldMin;
    public Button add;
    public Button removeAssociatedPart;
    public Button save;
    public Button cancel;
    public TextField fieldSearch;

    public void initialize(URL url, ResourceBundle resourceBundle){

    }

    public void OnAddButtonClicked(ActionEvent actionEvent) {
    }

    public void OnRemoveAssociatedPartClicked(ActionEvent actionEvent) {
    }

    public void OnSaveClicked(ActionEvent actionEvent) {
    }

    public void OnCancelClicked(ActionEvent actionEvent) {
    }
}
