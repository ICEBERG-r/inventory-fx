package Model;

/** This class contains a part that is manufactured an outside vendor. */
public class Outsourced extends Part {
    private String companyName;

    /** This is the constructor for an Outsourced part.
     * @param id contains a unique generated id for the part.
     * @param name the name of the part
     * @param price the cost of the part
     * @param stock the amount in stock of this part
     * @param min the minimum stock for this part
     * @param max the maximum stock for this part
     * @param companyName the name of the company that manufactures the part
     * */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    /** Sets the company name for the part. */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    /** Returns the company name associated with the part. */
    public String getCompanyName(){
        return companyName;
    }
}
