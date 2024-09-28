import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.rmi.RemoteException;

public class Client {
	public static void main(String[] args) {
		Scanner c = new Scanner(System.in);
		try {
			// locate the registry.
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);

			// Get the references of exported object from the RMI Registry...
			String[] productCode = {  "101","102","103" };
			ProductInterface[] products = new ProductInterface[productCode.length];
			for (int i = 0; i < productCode.length; i++) {
				products[i] = (ProductInterface) registry.lookup(productCode[i]);
			}
			CartInterface cart = (CartInterface) registry.lookup("myCart");

			while (true) {
				System.out.println("\n[1] View Products\n" +
						"[2] View Cart\n" +
						"[3] Add to Cart\n" +
						"[4] Exit\n");
				System.out.print("Enter choice: ");
				int choice = c.nextInt();

				switch (choice) {
					case 1:
						for (ProductInterface product : products) {
							System.out.println(product.viewProducts());
						}
						break;
					case 2:
						try {
							String[] cartProducts = cart.viewAddedProducts();
							System.out.println("\nProducts in Cart: ");
							for (String productInfo : cartProducts) {
								System.out.println(productInfo);
							}
						} catch (RemoteException e) {
							System.out.println("Error viewing cart: " + e.getMessage());
						}
						break;
					case 3:
						System.out.print("Enter Product code: ");
						int code = c.nextInt();

						ProductInterface selectedProduct = null;

						// Search for the product with the entered code
						for (ProductInterface product : products) {
							try {
								if (product.getProductCode() == code) {
									selectedProduct = product;
									break;
								}
							} catch (RemoteException e) {
								System.out.println("Error accessing product: " + e.getMessage());
							}
						}

						if (selectedProduct != null) {
							if (cart.addProducts(selectedProduct)) {
								System.out.println("Product added to cart.");
							} else {
								System.out.println("Cannot add more products, the cart is full.");
							}
						} else {
							System.out.println("Product not found.");
						}
						break;
					case 4:
						System.exit(0);
						break;
					default:
						System.out.println("Invalid choice.");
						break;
				}
			}
		} catch (Exception e) {
			System.out.println("Client side error..." + e);
		}
	}
}
