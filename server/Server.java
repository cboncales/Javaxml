import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//dom Parser Packages
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class Server{
	public static void main(String[] args) {
		try {
			System.setProperty("java.rmi.server.hostname", "127.0.0.1");
			// You don't have to run in console > start rmiregistry 9100
			Registry startRMI = LocateRegistry.createRegistry(9100);
			System.out.println("Server has been started...");

			File xmlFile = new File("products.xml");

			// Get Document Builder
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			// Build Document
			Document document = dBuilder.parse(xmlFile);
			// Normalize the XML Structure
			document.getDocumentElement().normalize();

			Cart myCart = new Cart(5);
			CartInterface stub_myCart = (CartInterface) UnicastRemoteObject.exportObject(myCart, 0);
			Registry registry = LocateRegistry.getRegistry("127.0.0.1", 9100);
			registry.rebind("myCart", stub_myCart);

			// Get all products
			NodeList productList = document.getElementsByTagName("product");
			System.out.println("============================");

			for (int i = 0; i < productList.getLength(); i++) {
				Element productElement = (Element) productList.item(i);

				int code = Integer.parseInt(productElement.getElementsByTagName("code").item(0).getTextContent());
				String name = productElement.getElementsByTagName("name").item(0).getTextContent();
				String description = productElement.getElementsByTagName("description").item(0).getTextContent();
				String manufacturer = productElement.getElementsByTagName("manufacturer").item(0).getTextContent();
				double retailPrice = Double.parseDouble(productElement.getElementsByTagName("retailPrice").item(0).getTextContent());
				double storePrice = Double.parseDouble(productElement.getElementsByTagName("storePrice").item(0).getTextContent());
				int quantity = Integer.parseInt(productElement.getElementsByTagName("quantity").item(0).getTextContent());
				String type = productElement.getElementsByTagName("type").item(0).getTextContent();
				
				// Create Product object
				// Export Product object
				Type types = new Type(code, name, description, manufacturer, retailPrice, storePrice, quantity, type);
				ProductInterface stub_product = (ProductInterface) UnicastRemoteObject.exportObject(types, 0);
				startRMI.rebind(String.valueOf(code), stub_product);
				System.out.println(types.viewProducts());
			}

			System.out.println("Exporting and binding of Objects has been completed...");
		} catch (Exception e) {
			System.out.println("Some server error..." + e);
		}
	}
}
