package Model;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/** This class contains the inventory for the program. */
public class Inventory {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** Adds a part to an observable list, allParts. */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    /** Adds a product to an observable list, allProducts. */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    /** Looks for a specific part based on its id.
     * @return returns the found part as an observable list. */
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
    /** Looks for a specific product based on its id.
     * @return returns the found product as an observable list. */
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
    /** Looks up a part based on its name.
     * @return returns the found part as an observable list. */
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
    /** Looks for a product based on its name.
     * @return returns found product as an observable list.*/
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
    /** Updates a part's information if it has been changed.
     * @param index this is the location of the selected part in the observable list.
     * @param selectedPart this is the part to be updated. */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }
    /** Updates a product's information if it has been changed.
     * @param index this is the location of the selected part in the observable list
     * @param selectedProduct this is the product to be updated. */
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }
    /** Deletes the selected part from the observable list. */
    public static boolean deletePart(Part selectedPart){
        return allParts.remove(selectedPart);
    }
    /** Deletes the selected product from the observable list. */
    public static boolean deleteProduct(Product selectedProduct){
      return allProducts.remove(selectedProduct);
    }
    /** Returns the observable list, allParts. */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }
    /** Returns the observable list, allProducts. */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }
}
