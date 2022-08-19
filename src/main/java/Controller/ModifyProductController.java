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

/** Creates the Modify Product window of the application. */

public class ModifyProductController implements Initializable {
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

    /** Takes the attributes of the selected product and fills in the text fields with that information.
     * Also sets the index of the product so the information can be updated correctly in the Inventory. */
    public void setProduct(Product selectedProduct){
        productIndex = Inventory.getAllProducts().indexOf(selectedProduct);
        idField.setText(Integer.toString(selectedProduct.getId()));
        nameField.setText(selectedProduct.getName());
        invField.setText(Integer.toString(selectedProduct.getStock()));
        priceField.setText(Double.toString(selectedProduct.getPrice()));
        maxField.setText(Integer.toString(selectedProduct.getMax()));
        minField.setText(Integer.toString(selectedProduct.getMin()));
    }

    /** Adds a selected part to the associated parts list and removes it from the local allParts list when the 'Add' button is clicked.
     * A part must be selected before it can be associated with a product. */
    public void OnAddButtonClicked(ActionEvent actionEvent) {
        Part part = allPartsTable.getSelectionModel().getSelectedItem();

        if (part == null){
            MainController.displayInfoAlert("Error","A part must be selected before association");
        }

        allParts.remove(part);
        associatedParts.add(part);
    }

    /** Removes a selected part from the associated parts list.
     * A part must be selected before it can be disassociated. */
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

    /** Updates a product with the given information and saves it to the inventory.
     * All fields must be filled with the proper input.
     * User must confirm before product is saved.*/
    public void OnSaveClicked(ActionEvent actionEvent) {
        try {
            int inv = Integer.parseInt(invField.getText());
            int min = Integer.parseInt(minField.getText());
            int max = Integer.parseInt(maxField.getText());
            if (max < min){
                MainController.displayInfoAlert("Input Error","Part minimum must be less than maximum");
            }
            else if (inv < min || inv > max){
                MainController.displayInfoAlert("Input Error","Part inventory must be between minimum and maximum");
            }
            else {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                if (name.equals("")) {
                    MainController.displayInfoAlert("Input Error", "Product name cannot be blank");
                    return;
                }
                double price = Double.parseDouble(priceField.getText());
                selectedProduct.setID(id);
                selectedProduct.setStock(inv);
                selectedProduct.setMin(min);
                selectedProduct.setMax(max);
                selectedProduct.setName(name);
                selectedProduct.setPrice(price);
                selectedProduct.getAllAssociatedParts().clear();
                selectedProduct.addAssociatedPart(associatedParts);
                Inventory.updateProduct(productIndex, selectedProduct);

                Parent root = FXMLLoader.load(getClass().getResource("/src/main/java/View/MainWindow.fxml"));
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

    /** Returns the user to the Main Window when the 'Cancel' button is clicked. */
    public void OnCancelClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/src/main/java/View/MainWindow.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    /** Filters the part table based on a search query. The user can search by part ID or by the part name. */
    public void GetPartSearchResults(ActionEvent actionEvent) {

        try {
            int x = Integer.parseInt(searchField.getText());

            ObservableList<Part> part = Inventory.lookupPart(x);

            if (part.isEmpty()){
                MainController.displayInfoAlert("Product not found", "Search term returned no results");
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
                MainController.displayInfoAlert("Product not found", "Search term returned no results");
                searchField.setText("");
                return;
            }

            allPartsTable.setItems(part);

            searchField.setText("");
        }
    }
}
