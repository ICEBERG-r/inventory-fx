package com.mwilson.inventoryfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public ToggleGroup tgroup;

    private int partIndex;

    public static Part selectedPart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setParts(selectedPart);
    }
    public void setParts(Part selectedPart) {
        partIndex = Inventory.getAllParts().indexOf(selectedPart);
        fieldID.setText(Integer.toString(selectedPart.getId()));
        fieldName.setText(selectedPart.getName());
        fieldInv.setText(Integer.toString(selectedPart.getStock()));
        fieldCost.setText(Double.toString(selectedPart.getPrice()));
        fieldMax.setText(Integer.toString(selectedPart.getMax()));
        fieldMin.setText(Integer.toString(selectedPart.getMin()));
        if(selectedPart instanceof InHouse){
            inHouseRadio.setSelected(true);
            this.machineIdCompanyNameLabel.setText("Machine ID");
            fieldMachineId.setText(Integer.toString(((InHouse) selectedPart).getMachineId()));
        }
        else{
            Outsourced outsourced = (Outsourced) selectedPart;
            outsourcedRadio.setSelected(true);
            this.machineIdCompanyNameLabel.setText("Company Name");
            fieldMachineId.setText(outsourced.getCompanyName());
        }
    }
    public void InHouseSelected(ActionEvent actionEvent) {
        machineIdCompanyNameLabel.setText("Machine ID");

    }

    public void OutsourcedSelected(ActionEvent actionEvent) {
        machineIdCompanyNameLabel.setText("Company Name");

    }

    public void OnSaveClicked(ActionEvent actionEvent) {
        try {
            int inventory = Integer.parseInt(fieldInv.getText());
            int min = Integer.parseInt(fieldMin.getText());
            int max = Integer.parseInt(fieldMax.getText());
            if (max < min){
                MainController.displayInfoAlert("Input Error","Part minimum must be less than maximum");
            }
            else if (inventory < min || inventory > max){
                MainController.displayInfoAlert("Input Error","Part inventory must be between minimum and maximum");
            }
            else {
                int id = Integer.parseInt(fieldID.getText());
                String name = fieldName.getText();
                double price = Double.parseDouble(fieldCost.getText());
                if (inHouseRadio.isSelected()){
                    int machId = Integer.parseInt(fieldMachineId.getText());
                    InHouse inHouse = new InHouse(id, name, price, inventory, min, max, machId);
                    Inventory.updatePart(partIndex, inHouse);
                }
                else {
                    String coName = fieldMachineId.getText();
                    Outsourced outsourced = new Outsourced(id, name, price, inventory, min, max, coName);
                    Inventory.updatePart(partIndex, outsourced);
                }
                Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
                Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();

            }
        } catch (Exception e){
            MainController.displayInfoAlert("Input Error","Inventory, Cost, Min, Max, and Machine ID fields must contain numerical values");
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
