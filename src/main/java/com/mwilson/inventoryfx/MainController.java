package com.mwilson.inventoryfx;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public Label TheLabel;
    public int count = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized.");
    }

    public void OnExitClicked(ActionEvent actionEvent) {
        //exits the program
    }
}