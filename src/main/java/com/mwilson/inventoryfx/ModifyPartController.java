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

public class ModifyPartController implements Initializable {
    public TextField idField;
    public TextField nameField;
    public TextField invField;
    public TextField priceField;
    public TextField maxField;
    public TextField machineIdCompanyNameField;
    public TextField minField;
    public RadioButton inHouseRadio;
    public RadioButton outsourcedRadio;
    public Button saveButton;
    public Button cancelButton;
    public Label machineIdCompanyNameLabel;
    public ToggleGroup toggleGroup;

    private int partIndex;

    public static Part selectedPart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setPart(selectedPart);
    }
    public void setPart(Part selectedPart) {
        partIndex = Inventory.getAllParts().indexOf(selectedPart);
        idField.setText(Integer.toString(selectedPart.getId()));
        nameField.setText(selectedPart.getName());
        invField.setText(Integer.toString(selectedPart.getStock()));
        priceField.setText(Double.toString(selectedPart.getPrice()));
        maxField.setText(Integer.toString(selectedPart.getMax()));
        minField.setText(Integer.toString(selectedPart.getMin()));
        if(selectedPart instanceof InHouse){
            inHouseRadio.setSelected(true);
            this.machineIdCompanyNameLabel.setText("Machine ID");
            machineIdCompanyNameField.setText(Integer.toString(((InHouse) selectedPart).getMachineId()));
        }
        else{
            Outsourced outsourced = (Outsourced) selectedPart;
            outsourcedRadio.setSelected(true);
            this.machineIdCompanyNameLabel.setText("Company Name");
            machineIdCompanyNameField.setText(outsourced.getCompanyName());
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
            int inventory = Integer.parseInt(invField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());
            if (max < min){
                MainController.displayInfoAlert("Input Error","Part minimum must be less than maximum");
            }
            else if (inventory < min || inventory > max){
                MainController.displayInfoAlert("Input Error","Part inventory must be between minimum and maximum");
            }
            else {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                if (name.equals("")) {
                    MainController.displayInfoAlert("Input Error", "Part name cannot be blank");
                    return;
                }
                double price = Double.parseDouble(priceField.getText());
                if (inHouseRadio.isSelected()){
                    int machId = Integer.parseInt(machineIdCompanyNameField.getText());
                    InHouse inHouse = new InHouse(id, name, price, inventory, min, max, machId);
                    Inventory.updatePart(partIndex, inHouse);
                }
                else {
                    String coName = machineIdCompanyNameField.getText();
                    if (coName.equals("")) {
                        MainController.displayInfoAlert("Input Error", "Company name cannot be blank");
                        return;
                    }
                    Outsourced outsourced = new Outsourced(id,name,price,inventory,min,max,coName);
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
