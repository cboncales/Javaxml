import java.rmi.RemoteException;

public class GenProducts implements ProductInterface{
    //GRANDPARENT
    // Attributes of product
    private int productCode;
    private String name;
    private String description;
    private String manufacturer;
    private double retailPrice;
    private double storePrice;
    private int quantity;

    GenProducts(int newProductCode, String newName, String newDescription, String newManufacturer, double newRetailPrice, double newStorePrice, int newQuantity) throws RemoteException {
        this.productCode = newProductCode;
        this.name = newName;
        this.description = newDescription;
        this.manufacturer = newManufacturer;
        this.retailPrice = newRetailPrice;
        this.storePrice = newStorePrice;
        this.quantity = newQuantity;
    }

    public int getProductCode() throws RemoteException {
        return this.productCode;
    }

    public String getName() throws RemoteException {
        return this.name;
    }

    public String getDescription() throws RemoteException {
        return this.description;
    }

    public String getManufacturer() throws RemoteException {
        return this.manufacturer;
    }

    public double getRetailPrice() throws RemoteException {
        return this.retailPrice;
    }

    public double getStorePrice() throws RemoteException {
        return this.storePrice;
    }

    public int getQuantity() throws RemoteException {
        return this.quantity;
    }

    public void changeProductName(String newName) throws RemoteException {
        this.name = newName;
    }

    public String viewProducts() throws RemoteException {
        return "Product code: " + productCode
                + "\nName: " + name
                + "\nDescription: " + description
                + "\nManufacturer: " + manufacturer
                + "\nRetail Price: " + retailPrice
                + "\nStore Price: " + storePrice
                + "\nQuantity: " + quantity + "\n============================";
    }
}
