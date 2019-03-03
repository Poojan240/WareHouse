//package warehouseStage1;
import java.util.*;
import java.io.*;


public class ManufacturerList implements Serializable {
	private static final long serialVersionUID =1L;
	private List manufacturers = new LinkedList();
	private static ManufacturerList manufacturerlist;

	public ManufacturerList() {
	}
	
	public static ManufacturerList instance() {
		if (manufacturerlist == null) {
			return (manufacturerlist = new ManufacturerList());
				
			
		} else {
			return manufacturerlist;
		}
	}
	
	 public Manufacturer search(String manufacturerID) {
		 for( Iterator iterator = manufacturers.iterator(); iterator.hasNext();) {
			 Manufacturer manufacturer = (Manufacturer) iterator.next();
			 if( manufacturer.getManufacturerId(). equals(manufacturerID)) {
				return manufacturer; 
			 }
		 }
		 return null;
		
	 }
	 
	 
	public boolean insertManufacturer(Manufacturer manufacturer) {
		manufacturers.add(manufacturer);
		return true;
	}
	
	public Iterator getmanufacturers() {
		return manufacturers.iterator();
		
	}
	
	private void writeObject(java.io.ObjectOutputStream output) {	
		try {
			output.defaultWriteObject();
			output.writeObject(manufacturerlist);
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
	}
	
	private void readObject(java.io.ObjectInputStream input) {
		try {
			
			if(manufacturerlist !=null) {
				return;
			} else {
			input.defaultReadObject();
			if (manufacturerlist == null) {
				manufacturerlist = (ManufacturerList) input.readObject();				
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
		return manufacturers.toString();
	} 

}
