import java.rmi.RemoteException;

public class Type extends Product{

    private String type;

    Type (int newProductCode, String newName, String newDescription, String newManufacturer, double newRetailPrice, double newStorePrice, int newQuantity, String Type) throws RemoteException{
        super(newProductCode, newName, newDescription, newManufacturer, newRetailPrice, newStorePrice, newQuantity);
        this.type = Type;
    }

    public String getType() throws RemoteException {
        return type;
    }

    public String viewProducts() throws RemoteException {
        return super.viewProducts() + "\nType: " + type + "\n";
    }
}
