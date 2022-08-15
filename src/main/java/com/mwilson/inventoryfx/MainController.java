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
    public TableView<Part> partTable;
    public TableColumn<Object, Object> partIdColumn;
    public TableColumn<Object, Object> partNameColumn;
    public TableColumn<Object, Object> partInventoryColumn;
    public TableColumn<Object, Object> partCostColumn;
    public TableView<Product> productTable;
    public TableColumn<Object, Object> productIdColumn;
    public TableColumn<Object, Object> productNameColumn;
    public TableColumn<Object, Object> productInventoryColumn;
    public TableColumn<Object, Object> productCostColumn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        partTable.setItems(Inventory.getAllParts());
        productTable.setItems(Inventory.getAllProducts());

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productCostColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    public void getPartSearchResults(ActionEvent actionEvent) {

        try {
            int x = Integer.parseInt(partSearch.getText());

            ObservableList<Part> part = Inventory.lookupPart(x);

            if (part.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Part not found");
                alert.setHeaderText("Part not found");
                alert.setContentText("Search term returned no results");
                alert.showAndWait();
                partSearch.setText("");
                return;
            }

            partTable.setItems(part);

            partSearch.setText("");
        }
        catch (Exception e) {
            String q = partSearch.getText();

            ObservableList<Part> part = Inventory.lookupPart(q);

            if (part.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Part not found");
                alert.setHeaderText("Part not found");
                alert.setContentText("Search term returned no results");
                alert.showAndWait();
                partSearch.setText("");
                return;
            }

            partTable.setItems(part);

            partSearch.setText("");
        }
    }



    public void getProductSearchResults(ActionEvent actionEvent){
        try {
            int x = Integer.parseInt(productSearch.getText());

            ObservableList<Product> product = Inventory.lookupProduct(x);

            if (product.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Product not found");
                alert.setHeaderText("Product not found");
                alert.setContentText("Search term returned no results");
                alert.showAndWait();
                productSearch.setText("");
                return;
            }
            productTable.setItems(product);

            productSearch.setText("");
        }
        catch (Exception e) {
            String q = productSearch.getText();

            ObservableList<Product> product = Inventory.lookupProduct(q);

            if (product.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Product not found");
                alert.setHeaderText("Product not found");
                alert.setContentText("Search term returned no results");
                alert.showAndWait();
                productSearch.setText("");
                return;
            }

            productTable.setItems(product);

            productSearch.setText("");
        }
    }

    public void OnExitClicked(ActionEvent actionEvent) {
        System.exit(0);
        //exits the program
        //needs confirmation dialog
    }

    public void OnAddPartClicked(ActionEvent actionEvent) throws IOException {
        //loads AddPart scene
        Parent root = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    public void OnModifyPartClicked(ActionEvent actionEvent) throws IOException {
        //loads Modify Part scene
        Parent root = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    public void OnDeletePartClicked(ActionEvent actionEvent) {
        //needs confirmation dialog prompt
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null){
            return;
        }
        Inventory.deletePart(selectedPart);
    }

    public void OnAddProductClicked(ActionEvent actionEvent) throws IOException {
        //loads AddProduct scene
        Parent root = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    public void OnModifyProductClicked(ActionEvent actionEvent) throws IOException {
        //loads ModifyProduct scene
        Parent root = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }

    public void OnDeleteProductClicked(ActionEvent actionEvent) {
        //needs confirmation dialog prompt
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null){
            return;
        }

        Inventory.deleteProduct(selectedProduct);

    }
}