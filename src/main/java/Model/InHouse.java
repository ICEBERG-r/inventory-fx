package Model;

/** This class contains a part that is manufactured by the company that is using the application. */
public class InHouse extends Part {
    private int machineId;
/** This is the constructor for an In House part.
 * @param id contains a unique generated id for the part.
 * @param name the name of the part
 * @param price the cost of the part
 * @param stock the amount in stock of this part
 * @param min the minimum stock for this part
 * @param max the maximum stock for this part
 * @param machineId the number assigned to a machine that manufactures this part
 * */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /** Sets the machineId for this part. */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }
    /** Returns the machineId for this part. */
    public int getMachineId(){
        return machineId;
    }
}
