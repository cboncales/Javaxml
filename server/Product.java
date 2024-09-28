import java.rmi.RemoteException;

public class Product extends GenProducts {
    //PARENT
    Product (int newProductCode, String newName, String newDescription, String newManufacturer, double newRetailPrice, double newStorePrice, int newQuantity) throws RemoteException{
        super(newProductCode, newName, newDescription, newManufacturer, newRetailPrice, newStorePrice, newQuantity);
    }

    public String viewProducts() throws RemoteException {
        return super.viewProducts();
    }
}
