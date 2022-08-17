package com.mwilson.inventoryfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    public TableView<Part> allPartsTable;
    public TableColumn<Object, Object> allPartIdColumn;
    public TableColumn<Object, Object> allPartNameColumn;
    public TableColumn<Object, Object> allPartInventoryColumn;
    public TableColumn<Object, Object> allPartCostColumn;
    public TableView<Part> associatedPartsTable;
    public TableColumn<Object, Object> associatedPartIdColumn;
    public TableColumn<Object, Object> associatedPartNameColumn;
    public TableColumn<Object, Object> associatedPartInventoryColumn;
    public TableColumn<Object, Object> associatedPartCostColumn;

    public static Product selectedProduct;
    private int productIndex;

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle){
        setProduct(selectedProduct);
        allParts.setAll(Inventory.getAllParts());
        allPartsTable.setItems(allParts);

        associatedPartsTable.setItems(associatedParts);

        allPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        allPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        allPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        allPartCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    public void setProduct(Product selectedProduct){
        productIndex = Inventory.getAllProducts().indexOf(selectedProduct);
        fieldID.setText(Integer.toString(selectedProduct.getId()));
        fieldName.setText(selectedProduct.getName());
        fieldInv.setText(Integer.toString(selectedProduct.getStock()));
        fieldCost.setText(Double.toString(selectedProduct.getPrice()));
        fieldMax.setText(Integer.toString(selectedProduct.getMax()));
        fieldMin.setText(Integer.toString(selectedProduct.getMin()));
    }
    public void OnAddButtonClicked(ActionEvent actionEvent) {
        Part part = allPartsTable.getSelectionModel().getSelectedItem();

        if (part == null){
            MainController.displayInfoAlert("Error","A part must be selected before association");
        }

        allParts.remove(part);
        associatedParts.add(part);
    }

    public void OnRemoveAssociatedPartClicked(ActionEvent actionEvent) {
        Part part = associatedPartsTable.getSelectionModel().getSelectedItem();

        if (part == null){
            MainController.displayInfoAlert("Error","A part must be selected before removal");
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove part?");
        alert.setHeaderText("Are you sure you want to remove this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)){
            selectedProduct.deleteAssociatedPart(part);
            associatedParts.remove(part);
            allParts.add(part);
        }

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
                selectedProduct.setID(id);
                selectedProduct.setStock(inventory);
                selectedProduct.setMin(min);
                selectedProduct.setMax(max);
                selectedProduct.setName(name);
                selectedProduct.setPrice(price);
                selectedProduct.getAllAssociatedParts().clear();
                selectedProduct.addAssociatedPart(associatedParts);
                Inventory.updateProduct(productIndex, selectedProduct);

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
    public void GetPartSearchResults(ActionEvent actionEvent) {

        try {
            int x = Integer.parseInt(fieldSearch.getText());

            ObservableList<Part> part = Inventory.lookupPart(x);

            if (part.isEmpty()){
                MainController.displayInfoAlert("Product not found", "Search term returned no results");
                fieldSearch.setText("");
                return;
            }

            allPartsTable.setItems(part);

            fieldSearch.setText("");
        }
        catch (Exception e) {
            String q = fieldSearch.getText();

            ObservableList<Part> part = Inventory.lookupPart(q);

            if (part.isEmpty()){
                MainController.displayInfoAlert("Product not found", "Search term returned no results");
                fieldSearch.setText("");
                return;
            }

            allPartsTable.setItems(part);

            fieldSearch.setText("");
        }
    }
}
