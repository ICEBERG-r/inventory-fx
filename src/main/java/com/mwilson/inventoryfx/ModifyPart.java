package com.mwilson.inventoryfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPart implements Initializable {
    public TextField fieldID;
    public TextField fieldName;
    public TextField fieldInv;
    public TextField fieldCost;
    public TextField fieldMax;
    public TextField fieldMachineId;
    public TextField fieldMin;
    public RadioButton inHouseRadio;
    public RadioButton outsourcedRadio;
    public Button save;
    public Button cancel;
    public Label machineIdCompanyNameLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void InHouseSelected(ActionEvent actionEvent) {
        machineIdCompanyNameLabel.setText("Machine ID");

    }

    public void OutsourcedSelected(ActionEvent actionEvent) {
        machineIdCompanyNameLabel.setText("Company Name");

    }

    public void OnSaveClicked(ActionEvent actionEvent) {
        if (inHouseRadio.isSelected()){

        }
        else if (outsourcedRadio.isSelected()) {

        }
        else {
            System.out.println("You broke my radio!");
        }
    }

    public void OnCancelClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
}
