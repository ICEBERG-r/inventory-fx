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
    public static Part lookupPart(int partId){

        ObservableList<Part> allParts = Inventory.getAllParts();

        Part foundPart = null;

        for (Part part : allParts) {

            if(part.getId() == partId){
                foundPart = part;
            }
        }
        return foundPart;
    }
    public static Product lookupProduct(int productID){
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        Product foundProduct = null;

        for (Product product : allProducts) {

            if(product.getId() == productID){
                foundProduct = product;
            }
        }
        return foundProduct;
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
    public static void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
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
