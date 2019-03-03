//package warehouseStage1;
import java.util.*;
import java.io.*;

public class Manufacturer implements Serializable{
	private static final long serialVersionUID=1L;
	private String manufacturerId;
	private String manufacturerName;
	private String manufacturerAddress;
	private String manufacturerPhone;
	private String manufacturerEmail;
	private Product product;
	private List<Product> manuToProduct = new LinkedList<Product>();
	
	public Manufacturer(String manufacturerId,String manufacturerName, String manufacturerAddress, String manufacturerPhone, String manufacturerEmail)
	{
		this.manufacturerId = manufacturerId;
		this.manufacturerName = manufacturerName;
		this.manufacturerAddress = manufacturerAddress;
		this.manufacturerPhone = manufacturerPhone;
		this.manufacturerEmail = manufacturerEmail;
	}
	
	public boolean assign(Product product) {
		this.product = product;
		return true;
	}
	public boolean map(Product product) {
		if (manuToProduct.add(product))
			return true;
		else
			return false;
 }
	
	public boolean unmap(Product product) {
		if (manuToProduct.remove(product))
			return true;
		else
			return false;
 }
	
	public String toString() {
		String string = " Manufacturer Name: " + manufacturerName + " Manufacturer ID: " + manufacturerId + " Manufacturer Address " + 
	manufacturerAddress + " Manufacturer Phone " + manufacturerPhone + " Manufacturer Email: "+ manufacturerEmail;
		return string;
	}


	public String getManufacturerId() {
		return manufacturerId;
	}


	public void setManufacturerId(String manufacturerId) {
		this.manufacturerId = manufacturerId;
	}


	public String getManufacturerName() {
		return manufacturerName;
	}


	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}


	public String getManufacturerAddress() {
		return manufacturerAddress;
	}


	public void setManufacturerAddress(String manufacturerAddress) {
		this.manufacturerAddress = manufacturerAddress;
	}


	public String getManufacturerPhone() {
		return manufacturerPhone;
	}


	public void setManufacturerPhone(String manufacturerPhone) {
		this.manufacturerPhone = manufacturerPhone;
	}


	public String getManufacturerEmail() {
		return manufacturerEmail;
	}


	public void setManufacturerEmail(String manufacturerEmail) {
		this.manufacturerEmail = manufacturerEmail;
	}
	
	public Iterator<Product> getProducts(){
		return manuToProduct.iterator();
	}


	
	
	
	
}