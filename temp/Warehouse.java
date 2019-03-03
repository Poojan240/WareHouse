//package warehouseStage1;
import java.util.*;
import java.io.*;

public class Warehouse implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int CLIENT_NOT_FOUND = 1;
	public static final int PRODUCT_NOT_FOUND = 2;
	public static final int MANUFACTURER_NOT_FOUND = 3;
	public static final int OPERATION_COMPLETED = 4;
	public static final int OPERATION_FAILED = 5;
	
	private ClientList clientlist;
 	private ProductList productlist;  // catalog like that
 	private ManufacturerList manufacturerlist;
 	
	
	private static Warehouse warehouse;
	private Warehouse() {
		clientlist = ClientList.instance();
		productlist = ProductList.instance();
		manufacturerlist = ManufacturerList.instance();
	}
	
	public static Warehouse instance() {
		if (warehouse == null) {
			ClientIdServer.instance();   // instantiate all singletons automatic generation id
			return (warehouse = new Warehouse());
			
		} else {
			return warehouse;
		}
		
	}
	
	public Client addClient ( String clientName, String clientAddress, String clientPhone, String clientEmail) {
		Client client = new Client(clientName,clientAddress,clientPhone,clientEmail);
		if (clientlist.insertClient(client)) {
			return (client);
		}
		return null;
		}
	
	public Product addProducts ( String productID, String productName,String productDescription, String productUnitprice) {
		Product product  = new Product( productID,  productName, productDescription, productUnitprice);
		if (productlist.insertProduct(product)) {
			return (product);
		}
		return null;
		}
	public Manufacturer addManufacturer ( String manufacturerID,String manufacturerName,String manufacturerAddress,String manufacturerPhone,String manufacturerEmail) {
		Manufacturer manufacturer  = new Manufacturer( manufacturerID,manufacturerName,manufacturerAddress,manufacturerPhone,manufacturerEmail);
		if (manufacturerlist.insertManufacturer(manufacturer)) {
			return (manufacturer);
		}
		return null;
		} 
	
	public Product assignProductToManufacturer(String manufacturerID,String productID,String productPrice) {
		int flag_noduplicate = 0;
		Product product = productlist.search(productID);
				if (product == null) {
					System.out.println("Please enter proper credentials");
					return  null;
				}
		Manufacturer manufacturer = manufacturerlist.search(manufacturerID);
				if (manufacturer == null) {
					
					System.out.println("Please enter proper credentials");
					return null;
				}
		flag_noduplicate = product.checkManufacturerList(manufacturer);
		if (flag_noduplicate != -1)
			return null;
		double thisproductPrice;
		thisproductPrice=Double.parseDouble(productPrice);
		boolean value = product.map(manufacturer);
		value = product.addCostPrice(thisproductPrice);
		value = manufacturer.map(product);
		if(value) 
			return product;
		else
			return null;
		
	}
	
	public Product unassignProductToManufacturer(String manufacturerID,String productID) {
		
		Product product = productlist.search(productID);
				if (product == null) {
					System.out.println("Please enter proper credentials");
					return  null;
				}
		Manufacturer manufacturer = manufacturerlist.search(manufacturerID);
				if (manufacturer == null) {
					System.out.println("Please enter proper credentials");
					return null;
				}
		
		boolean value = product.unmap(manufacturer);
		value = manufacturer.unmap(product);
		if(value) 
			return product;
		else
			return null;
		
	}
	public void showSupplierByProductWithPrice(String productID) {
		Product product = productlist.search(productID);
		if(product != null) {
			Manufacturer manufacturer1;
		 double price;
			Iterator<Manufacturer> manufacturer_iterator = product.getManufacturers();
			Iterator<Double> price_iterator = product.getPrice();
			while(manufacturer_iterator.hasNext()!=false)
			{
				manufacturer1=manufacturer_iterator.next();
				price=price_iterator.next();
					System.out.print( manufacturer1.getManufacturerName());
					System.out.println(":"+price);
				
			}
			
		}
		else
			System.out.println("Product Does not exist");
	}
	
	public void showProductByManufacturer(String manufacturerID) {
		Manufacturer manufacturer = manufacturerlist.search(manufacturerID);
		if(manufacturer != null) {
			Product product1;
			Iterator<Product> product_iterator = manufacturer.getProducts();
			while(product_iterator.hasNext()!=false)
			{
				product1=product_iterator.next();
					System.out.println( product1.getProductName());
			}
			
		}
		else
			System.out.println("Manufacturer Does not exist");
	}
	public Iterator getClients() {
		return clientlist.getClients();
	}
	public Iterator getProducts()
	{
		return productlist.getProducts();
	}
	public Iterator getManufacturers()
	{
		return manufacturerlist.getmanufacturers();
	} 
 
	
	
	public static Warehouse retrieve() {
		try {
			FileInputStream file = new FileInputStream("WarehouseData");
			ObjectInputStream input = new ObjectInputStream(file);
			input.readObject();
			ClientIdServer.retrieve(input);
			return warehouse;
		} catch(IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	} 
	public static boolean save() {
		try {
			
			FileOutputStream file = new FileOutputStream("WarehouseData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(warehouse);
			output.writeObject(ClientIdServer.instance());
			return true;
		}catch(IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}
	
	private void writeObject(java.io.ObjectOutputStream output) {	
		try {
			output.defaultWriteObject();
			output.writeObject(warehouse);
			}catch(IOException ioe) {
				System.out.println(ioe);
			}
	}
	private void readObject(java.io.ObjectInputStream input) {
		try {
			input.defaultReadObject();
			if(warehouse==null) {
					warehouse = (Warehouse) input.readObject();
		}else {
			input.readObject();
		}
	}catch(IOException ioe) {
		ioe.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
		}
	}
/*	public String toString() {
		//return productlist + "\n" + clientlist;
		return clientlist;
	}  */
}

