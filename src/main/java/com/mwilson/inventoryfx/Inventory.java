package com.mwilson.inventoryfx;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    public static ObservableList<Part> lookupPart(int partId){

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> part = FXCollections.observableArrayList();

        for (Part p : allParts){
            if (p.getId() == partId){
                part.add(p);
            }
        }

        return part;
    }
    public static ObservableList<Product> lookupProduct(int productID){
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> product = FXCollections.observableArrayList();

        for (Product p : allProducts){
            if (p.getId() == productID){
                product.add(p);
            }
        }

        return product;
    }
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> parts = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();

        for (Part part : allParts) {

            if(part.getName().contains(partName)){
                parts.add(part);
            }
        }

        return parts;
    }
    public static ObservableList<Product> lookupProduct(String productName){

        ObservableList<Product> products = FXCollections.observableArrayList();

        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for(Product product : allProducts){
            if(product.getName().contains(productName)){
                products.add(product);
            }
        }

        return products;
    }
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }
    public static boolean deleteProduct(Product selectedProduct){
        return allProducts.remove(selectedProduct);
    }
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
