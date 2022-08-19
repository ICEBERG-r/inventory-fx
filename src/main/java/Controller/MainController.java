package Controller;

import Model.Inventory;
import Model.Part;
import Model.Product;
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

/** This class creates the main window for the application. */
public class MainController implements Initializable {

    public Button addPartButton;
    public Button modifyPartButton;
    public Button deletePartButton;
    public Button addProductButton;
    public Button modifyProductButton;
    public Button deleteProductButton;
    public TextField partSearchButton;
    public TextField productSearchButton;
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

    /** Filters the part table based on a search query. The user can search by part ID or by the part name. */
    public void getPartSearchResults(ActionEvent actionEvent) {

        try {
            int x = Integer.parseInt(partSearchButton.getText());

            ObservableList<Part> part = Inventory.lookupPart(x);

            if (part.isEmpty()){
                displayInfoAlert("Part not found", "Search term returned no results");
                partSearchButton.setText("");
                return;
            }

            partTable.setItems(part);

            partSearchButton.setText("");
        }
        catch (Exception e) {
            String q = partSearchButton.getText();

            ObservableList<Part> part = Inventory.lookupPart(q);

            if (part.isEmpty()){
                displayInfoAlert("Part not found", "Search term returned no results");
                partSearchButton.setText("");
                return;
            }

            partTable.setItems(part);

            partSearchButton.setText("");
        }
    }


    /** Filters the product table based on a search query. The user can search by product ID or by the product name.*/
    public void getProductSearchResults(ActionEvent actionEvent){
        try {
            int x = Integer.parseInt(productSearchButton.getText());

            ObservableList<Product> product = Inventory.lookupProduct(x);

            if (product.isEmpty()){
                displayInfoAlert("Product not found", "Search term returned no results");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                productSearchButton.setText("");
                return;
            }
            productTable.setItems(product);

            productSearchButton.setText("");
        }
        catch (Exception e) {
            String q = productSearchButton.getText();

            ObservableList<Product> product = Inventory.lookupProduct(q);

            if (product.isEmpty()){
                displayInfoAlert("Product not found", "Search term returned no results");
                productSearchButton.setText("");
                return;
            }

            productTable.setItems(product);

            productSearchButton.setText("");
        }
    }

    /** Exits the program when the 'Exit' button is clicked. Method confirms the action before exiting the program.*/
    public void OnExitClicked(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)){
            System.exit(0);
        }
    }

    /** Takes the user to the AddPart window when the 'Add' button under the part table is clicked. */
    public void OnAddPartClicked(ActionEvent actionEvent) throws IOException {
        //loads AddPart scene
        Parent root = FXMLLoader.load(getClass().getResource("/src/main/java/View/AddPart.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Part");
        stage.setScene(scene);
        stage.show();
    }

    /** Takes the user to the ModifyPart window when the 'Modify' button under the part table is clicked. */

    public void OnModifyPartClicked(ActionEvent actionEvent) throws IOException {
        //loads Modify Part scene
        ModifyPartController.selectedPart = partTable.getSelectionModel().getSelectedItem();
        Parent root = FXMLLoader.load(getClass().getResource("/src/main/java/View/ModifyPart.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Part");
        stage.setScene(scene);
        stage.show();
    }

    /** Deletes the selected part from the inventory when the 'Delete' button is clicked.
     * User is asked for confirmation before deletion. */
    public void OnDeletePartClicked(ActionEvent actionEvent) {
        Part selectedPart = partTable.getSelectionModel().getSelectedItem();
        if (selectedPart == null){
            displayInfoAlert("ERROR", "A part must be selected in order to delete it");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Part?");
        alert.setHeaderText("Are you sure you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.OK)){
            Inventory.deletePart(selectedPart);
        }
    }

    /** Takes the user to the AddProduct window when the 'Add' button under the product table is clicked. */
    public void OnAddProductClicked(ActionEvent actionEvent) throws IOException {
        //loads AddProduct scene
        Parent root = FXMLLoader.load(getClass().getResource("/src/main/java/View/AddProduct.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Product");
        stage.setScene(scene);
        stage.show();
    }

    /** Takes the user to the ModifyProduct window when the 'Modify' button under the product table is clicked. */
    public void OnModifyProductClicked(ActionEvent actionEvent) throws IOException {
        //loads ModifyProduct scene
        ModifyProductController.selectedProduct = productTable.getSelectionModel().getSelectedItem();
        Parent root = FXMLLoader.load(getClass().getResource("/src/main/java/View/ModifyProduct.fxml"));
        Stage stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Modify Product");
        stage.setScene(scene);
        stage.show();
    }

    /** Deletes the selected product from the inventory when the 'Delete' button under the product table is clicked.
     * User is asked for confirmation before deletion. A product cannot be removed if it has parts associated with it. */
    public void OnDeleteProductClicked(ActionEvent actionEvent) {
        //needs confirmation dialog prompt
        Product selectedProduct = productTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null){
            displayInfoAlert("ERROR", "A product must be selected in order to delete it");
        }
        else if (!selectedProduct.getAllAssociatedParts().isEmpty())
        {
            displayInfoAlert("ERROR","A product cannot be deleted if it has associated parts");
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product");
            alert.setHeaderText("Are you sure you want to delete this product?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().equals(ButtonType.OK)){
                Inventory.deleteProduct(selectedProduct);
            }

        }



    }
    /** Displays an informational alert to the user.
     * @param title This is a string that will appear both in the title and in the header text.
     * @param elaboration This is an explanation of the alert. */
    public static void displayInfoAlert(String title, String elaboration){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(elaboration);
        alert.showAndWait();
    }
}