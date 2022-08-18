package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
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

public class AddProductController implements Initializable {
    public TextField idField;
    public TextField nameField;
    public TextField invField;
    public TextField priceField;
    public TextField maxField;
    public TextField minField;
    public Button addButton;
    public Button removeAssociatedPartButton;
    public Button saveButton;
    public Button cancelButton;
    public TextField searchField;

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
            MainController.displayInfoAlert("ERROR", "Part must be selected");
            return;
        }

        allParts.remove(part);
        associatedParts.add(part);

    }

    public void OnRemoveAssociatedPartClicked(ActionEvent actionEvent) {
        Part part = associatedPartsTable.getSelectionModel().getSelectedItem();

        if (part == null){
            MainController.displayInfoAlert("ERROR", "Part must be selected");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Remove Part?");
        alert.setHeaderText("Are you sure you want remove this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)){
            associatedParts.remove(part);
            allParts.add(part);
        }

    }
    public static int getNewID(){
        int id = 1;
        for (int i = 0; i < Inventory.getAllProducts().size(); i++){
            id++;
        }
        return id;

    }
    public void OnSaveClicked(ActionEvent actionEvent) {
        try {
            int inv = Integer.parseInt(invField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());
            if (max < min){
                MainController.displayInfoAlert("Input Error","Product minimum must be less than maximum");
            }
            else if (inv < min || inv > max){
                MainController.displayInfoAlert("Input Error","Product inventory must be between minimum and maximum");
            }
            else {
                int id = getNewID();
                String name = nameField.getText();
                if (name.equals("")) {
                    MainController.displayInfoAlert("Input Error", "Product name cannot be blank");
                    return;
                }
                double price = Double.parseDouble(priceField.getText());
                Product product = new Product(id,name,price,inv,min,max);
                product.addAssociatedPart(associatedParts);
                Inventory.addProduct(product);
                Parent root = FXMLLoader.load(getClass().getResource("/src/main/java/View/MainWindow.fxml"));
                Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setTitle("Inventory Management System");
                stage.setScene(scene);
                stage.show();

            }
        } catch (Exception e){
            MainController.displayInfoAlert("Input Error","Inventory, Price, Min and Max fields must contain numerical values");
        }
    }

    public void OnCancelClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/src/main/java/View/MainWindow.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }
    public void GetPartSearchResults(ActionEvent actionEvent) {

        try {
            int x = Integer.parseInt(searchField.getText());

            ObservableList<Part> part = Inventory.lookupPart(x);

            if (part.isEmpty()){
                MainController.displayInfoAlert("Product not found","Search term returned no results");
                searchField.setText("");
                return;
            }

            allPartsTable.setItems(part);

            searchField.setText("");
        }
        catch (Exception e) {
            String q = searchField.getText();

            ObservableList<Part> part = Inventory.lookupPart(q);

            if (part.isEmpty()){
                MainController.displayInfoAlert("Product not found","Search term returned no results");
                searchField.setText("");
                return;
            }

            allPartsTable.setItems(part);

            searchField.setText("");
        }
    }

}
