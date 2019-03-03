//package warehouseStage1;
import java.util.*;
import java.io.*;

public class Product implements Serializable{
	private static final long serialVersionUID=1L;
	private String productId;
	private String productName;
	private String productDesc;
	private String unitPrice;
	private Manufacturer manufacturer;
	private List<Manufacturer> productToManf = new LinkedList<Manufacturer>();
	private List<Double> productCostPrice = new LinkedList<Double>();
	
	public Product(String productId,String productName, String productDesc, String unitPrice)
	{
		this.productId = productId;
		this.productName = productName;
		this.productDesc = productDesc;
		this.unitPrice = unitPrice;
	}
	
	public boolean assign(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
		return true;
		
	}
	
	public int checkManufacturerList(Manufacturer manufacturer) {
		int flag;
		for(flag = 0; flag <= productToManf.size()-1; ++flag)
		{
			if((productToManf.get(flag)) == manufacturer)
			{
				System.out.println("To avoid duplication mapping");
				return flag;
			}
		}
		flag = -1;
		return flag;
	}
	
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public boolean map(Manufacturer manufacturer) {
		if (productToManf.add(manufacturer))
			return true;
		else
			return false;
	}
	
	public boolean unmap(Manufacturer manufacturer) {
		if (productToManf.remove(manufacturer))
			return true;
		else
			return false;
	}
	
	public boolean addCostPrice(double costPrice) {
		if (productCostPrice.add(costPrice))
			return true;
		else
			return false;
		
	}

	public Iterator<Double> getPrice(){
		return productCostPrice.iterator();
	}
	public Iterator<Manufacturer> getManufacturers(){
		return productToManf.iterator();
	}
	
	public String toString() {
		String string = " Product Name: " + productName + " Product ID: " + productId + " Product Description: " + 
	productDesc + " Product price:  " + unitPrice;
		return string;
	}

	
	
}
