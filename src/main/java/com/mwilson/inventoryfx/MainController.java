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
            String q = partSearch.getText();

            ObservableList<Part> parts = searchByPartName(q);

            partTable.setItems(parts);

            partSearch.setText("");
    }

    private ObservableList<Part> searchByPartName(String partialName){
        ObservableList<Part> parts = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part part : allParts){
            if(part.getName().contains(partialName)){
                parts.add(part);
            }
        }

        return parts;
    }

    public void getProductSearchResults(ActionEvent actionEvent){
        String q = productSearch.getText();

        ObservableList<Product> products = searchByProductName(q);

        productTable.setItems(products);

        productSearch.setText("");
    }

    private ObservableList<Product> searchByProductName(String partialName){
        ObservableList<Product> products = FXCollections.observableArrayList();

        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for(Product product : allProducts){
            if(product.getName().contains(partialName)){
                products.add(product);
            }
        }

        return products;
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