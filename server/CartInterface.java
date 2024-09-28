import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CartInterface extends Remote {
	// Lets us define API
	public boolean addProducts(ProductInterface product) throws RemoteException;
	public String[] viewAddedProducts() throws RemoteException;
	public void removeAllProducts() throws RemoteException;
}
