package com.mwilson.inventoryfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddProduct implements Initializable {
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

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle){

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

    public void OnAddButtonClicked(ActionEvent actionEvent) {
        Part part = allPartsTable.getSelectionModel().getSelectedItem();

        if (part == null){
            return;
        }

        allParts.remove(part);
        associatedParts.add(part);

    }

    public void OnRemoveAssociatedPartClicked(ActionEvent actionEvent) {
        Part part = associatedPartsTable.getSelectionModel().getSelectedItem();

        if (part == null){
            return;
        }

        associatedParts.remove(part);
        allParts.add(part);
    }

    public void OnSaveClicked(ActionEvent actionEvent) {
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

            allPartsTable.setItems(part);

            fieldSearch.setText("");
        }
        catch (Exception e) {
            String q = fieldSearch.getText();

            ObservableList<Part> parts = Inventory.lookupPart(q);

            allPartsTable.setItems(parts);

            fieldSearch.setText("");
        }
    }

}
