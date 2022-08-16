package com.mwilson.inventoryfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        //Inventory.addPart(new InHouse(1,"whoozit", 22.00, 44, 40, 101, 10003));
        //Inventory.addPart(new Outsourced(2, "noway", 22.00, 40, 1, 100, "Stray Catz"));
        //Inventory.addProduct(new Product(1001, "Yooooo", 35.00, 20, 5, 70));
        //Inventory.addProduct(new Product(1002, "Aw Yea", 420.69, 420, 69, 420));

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}