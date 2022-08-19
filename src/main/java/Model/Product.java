package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** This class contains a product that can be comprised of different parts. */
public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    /** This is the constructor for a Product.
     * @param id contains a unique generated id for the product.
     * @param name the name of the product.
     * @param price the cost of the product.
     * @param stock the amount in stock of this product.
     * @param min the minimum stock for this product.
     * @param max the maximum stock for this product.
     * */
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;

    }
    /** Sets an ID for the product. */
    public void setID(int id){
        this.id = id;
    }
    /** Sets a name for the product. */
    public void setName(String name){
        this.name = name;
    }
    /** Sets a price for the product. */
    public void setPrice(double price){
        this.price = price;
    }
    /** Sets the stock of the product. */
    public void setStock(int stock){
        this.stock = stock;
    }
    /** Sets the minimum stock for the product. */
    public void setMin(int min){
        this.min = min;
    }
    /** Sets the maximum stock for the product. */
    public void setMax(int max){
        this.max = max;
    }
    /** Returns the ID of the product. */
    public int getId(){
        return id;
    }
    /** Returns the name of the product. */
    public String getName(){
        return name;
    }
    /** Returns the price of the product. */
    public double getPrice(){
        return price;
    }
    /** Returns the stock of the product. */
    public int getStock(){
        return stock;
    }
    /** Returns the minimum stock of the product. */
    public int getMin(){
        return min;
    }
    /** Returns the maximum stock of the product. */
    public int getMax(){
        return max;
    }
    /** Adds associated parts to a product.
     * @param part the list of parts to be associated with the product. */
    public void addAssociatedPart(ObservableList<Part> part){
        this.associatedParts.addAll(part);
    }
    /** Removes an associated part from the product.*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){

        associatedParts.remove(selectedAssociatedPart);
        return true;
    }
    /** Returns an observable list of the parts that are associated with the product. */
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

}
