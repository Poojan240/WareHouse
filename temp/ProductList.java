//package warehouseStage1;
import java.util.*;
import java.io.*;


public class ProductList implements Serializable {
	private static final long serialVersionUID =1L;
	private List products = new LinkedList();
	private static ProductList productlist;
	public ProductList() {
	}
	
	public static ProductList instance() {
		if (productlist == null) {
			return (productlist = new ProductList());
				
			
		} else {
			return productlist;
		}
	}
	 public Product search(String productID) {
		 for( Iterator iterator = products.iterator(); iterator.hasNext();) {
			 Product product = (Product) iterator.next();
			 if( product.getProductId(). equals(productID)) {
				return product; 
			 }
		 }
		 return null;
		
	 }
	
	public boolean insertProduct(Product product) {
		products.add(product);
		return true;
	}
	
	public Iterator getProducts() {
		return products.iterator();
		
	}
	
	private void writeObject(java.io.ObjectOutputStream output) {	
		try {
			output.defaultWriteObject();
			output.writeObject(productlist);
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
	}
	
	private void readObject(java.io.ObjectInputStream input) {
		try {
			
			if(productlist !=null) {
				return;
			} else {
			input.defaultReadObject();
			if (productlist == null) {
				productlist = (ProductList) input.readObject();				
			} else {
				input.readObject();
			}
		}
	}catch(IOException ioe) {
		ioe.printStackTrace();
	}catch(ClassNotFoundException cnfe) {
		cnfe.printStackTrace();
		}
	}
	public String toString() {
		return products.toString();
	} 

}
