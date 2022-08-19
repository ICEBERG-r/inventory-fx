package Model;

/* Mark Wilson
*  Student ID: 010314264
*
*  Error elaboration is located within the initialize method of AddProductController.
*
*  -Future Improvements-
*   The program should store inventory data beyond the runtime of the application.
*   Develop a method of storing commonly bundled products in Inventory.
*   The ability to store and display a short description of the part or product.
*   A field for storing and displaying notes related to the part or product.
*
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/** This class creates an inventory management application. This application allows the user to store information about Parts and Products.*/
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Inventory.addPart(new InHouse(1,"whoozit", 22.00, 44, 40, 101, 10003));
        Inventory.addPart(new Outsourced(2, "noway", 22.00, 40, 1, 100, "Stray Catz"));
        Inventory.addProduct(new Product(1001, "Yooooo", 35.00, 20, 5, 70));
        Inventory.addProduct(new Product(1002, "Aw Yea", 420.69, 420, 69, 420));

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/src/main/java/View/MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}