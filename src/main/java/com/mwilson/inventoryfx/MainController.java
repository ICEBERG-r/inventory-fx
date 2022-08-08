package com.mwilson.inventoryfx;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public Button addPart;
    public Button modifyPart;
    public Button deletePart;
    public Button addProduct;
    public Button modifyProduct;
    public Button deleteProduct;
    public TextField partSearch;
    public TextField productSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("I am initialized.");
    }

    public void OnExitClicked(ActionEvent actionEvent) {
        //exits the program
    }

    public void OnAddPartClicked(ActionEvent actionEvent) {
        //loads AddPart scene
    }

    public void OnModifyPartClicked(ActionEvent actionEvent) {
        //loads Modify Part scene
    }

    public void OnDeletePartClicked(ActionEvent actionEvent) {
        //opens Y/N prompt. deletes part if 'Y'
    }

    public void OnAddProductClicked(ActionEvent actionEvent) {
        //loads AddProduct scene
    }

    public void OnModifyProductClicked(ActionEvent actionEvent) {
        //loads ModifyProduct scene
    }

    public void OnDeleteProductClicked(ActionEvent actionEvent) {
        //opens y/n prompt. deletes product if 'Y'
    }
}