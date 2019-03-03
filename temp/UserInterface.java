//package warehouseStage1;
import java.util.*;
import java.text.*;
import java.io.*;



public class UserInterface {
	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Warehouse warehouse;
	private static final int EXIT = 0;
	private static final int ADD_CLIENT = 1;
	private static final int ADD_PRODUCTS = 2;
	private static final int ADD_MANUFACTURER = 3;
	private static final int ASSIGN_PRODUCT_TO_MANUFACTURER = 4;
	private static final int UNASSIGN_PRODUCT_TO_MANUFACTURER = 5;
	private static final int SHOW_CLIENTS = 6;
	private static final int SHOW_PRODUCTS = 7;
	private static final int SHOW_MANUFACTURERS = 8;
	private static final int SHOW_SUPPLIERS_FOR_PRODUCT_WITH_PRICE = 9;
	private static final int SHOW_PRODUCTS_BY_MANUFACTURER = 10;
	private static final int SAVE = 11;
	private static final int RETRIEVE = 12;
	private static final int HELP = 13;
	
	private UserInterface() {
		if (yesOrNo("Look for saved data and use it?")) {
			
			retrieve();
		} else {
			warehouse = Warehouse.instance();
		}
		
	}
	
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}
	
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
					}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}
	
	private boolean yesOrNo(String prompt) {
		 String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		 if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
		    return false;
		    }
		    return true;
	}
		  
	public int getNumber(String prompt) {
		 do {
		      try {
		    	  String item = getToken(prompt);
		    	  Integer num = Integer.valueOf(item);
		    	  return num.intValue();
		      } catch (NumberFormatException nfe) {
		        System.out.println("Please input a number ");
		      }
		    } while (true);
	}
	 
	public int getCommand() {
		    do {
		      try {
		        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
		        if (value >= EXIT && value <= HELP) {
		          return value;
		        }
		      } catch (NumberFormatException nfe) {
		        System.out.println("Enter a number");
		      }
		    } while (true);
	}

	public void help() {
		System.out.println("Enter a number between 0 and 13 as explained below:");
		System.out.println(EXIT + " to Exit");
		System.out.println(ADD_CLIENT + " to add a Client");
		System.out.println(ADD_PRODUCTS + " to  add Product");
		System.out.println(ADD_MANUFACTURER + " to  add Manufacturer");
		System.out.println(ASSIGN_PRODUCT_TO_MANUFACTURER + " to  assign product to a manufacturer ");
		System.out.println(UNASSIGN_PRODUCT_TO_MANUFACTURER + " to  unassign product to a manufacturer ");
		System.out.println(SHOW_CLIENTS + " to  Show Clients");
		System.out.println(SHOW_PRODUCTS + " to  Show Products");
		System.out.println(SHOW_MANUFACTURERS + " to  Show Manufacturer");
		System.out.println(SHOW_SUPPLIERS_FOR_PRODUCT_WITH_PRICE + " to  show suppliers for the product");
		System.out.println(SHOW_PRODUCTS_BY_MANUFACTURER + " to  show the list of products by manufacturer");
		System.out.println(SAVE + " to  save data");
		System.out.println(RETRIEVE + " to  retrieve");
		System.out.println(HELP + " for help");
	}

	public void addClient() {
		    String clientName = getToken("Enter Client name");
		    String clientAddress = getToken("Enter address");
		    String clientPhone = getToken("Enter phone");
		    String clientEmail = getToken("Enter emai address");
		    Client result;
		    result = warehouse.addClient(clientName, clientAddress, clientPhone,clientEmail);
		    if (result == null) {
		      System.out.println("Could not add client");
		    }
		    System.out.println(result);
	}

	public void addProducts() {
		    Product result;
		    do {
		      String productID = getToken("Enter id");
		      String productName = getToken("Enter  Name");
		      String productDescription = getToken("Enter product description");
		      String productUnitprice = getToken("Enter the price");
		      result = warehouse.addProducts(productID, productName, productDescription,productUnitprice);
		      if (result != null) {
		        System.out.println(result);
		      } else {
		        System.out.println("Product could not be added");
		      }
		      if (!yesOrNo("Add more Products?")) {
		        break;
		      }
		    } while (true);
	}
	
	public Calendar getDate(String prompt) {
	    do {
	      try {
	        Calendar date = new GregorianCalendar();
	        String item = getToken(prompt);
	        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
	        date.setTime(df.parse(item));
	        return date;
	      } catch (Exception fe) {
	        System.out.println("Please input a date as mm/dd/yy");
	      }
	    } while (true);
	  }

		public void addManufacturer() {
		String manufacturerID = getToken("Enter id");
	    String manufacturerName = getToken("Enter Manufacturer name");
	    String manufacturerAddress = getToken("Enter address");
	    String manufacturerPhone = getToken("Enter phone");
	    String manufacturerEmail = getToken("Enter email");
	    Manufacturer result;
	    result = warehouse.addManufacturer(manufacturerID,manufacturerName,manufacturerAddress,manufacturerPhone,manufacturerEmail);
	    if (result == null) {
	      System.out.println("Could not add manufacturer");
	    }
	    System.out.println(result);
		}
		
		public void assignProductToManufacturer(){
			String manufacturerID = getToken("Enter Manufacturer id:");
		    String productID = getToken("Enter Product id:");
		    String productPrice= getToken("Enter the price:");
		    warehouse.assignProductToManufacturer(manufacturerID,productID,productPrice);
		   
		}
		
		public void unassignProductToManufacturer(){
			String manufacturerID = getToken("Enter Manufacturer id:");
		    String productID = getToken("Enter Product id:");
		     warehouse.unassignProductToManufacturer(manufacturerID,productID);
		   
		}
		  

		  public void showClients() {
		      Iterator allClients = warehouse.getClients();
		      while (allClients.hasNext()){
			  Client client = (Client)(allClients.next());
		          System.out.println(client.toString());
		      }
		  }
		  
		  public void showProducts() {
		      Iterator allProducts = warehouse.getProducts();
		      while (allProducts.hasNext()){
			  Product product = (Product)(allProducts.next());
		          System.out.println(product.toString());
		      }
		  }
		  
		  public void showManufacturers() {
		      Iterator allManufacturers = warehouse.getManufacturers();
		      while (allManufacturers.hasNext()){
			  Manufacturer manufacturer = (Manufacturer)(allManufacturers.next());
		          System.out.println(manufacturer.toString());
		      }
		  }
		  
		  public void showSupplierByProductWithPrice() {
			  String productID = getToken("Enter Product id:");
			  warehouse.showSupplierByProductWithPrice(productID);
		  }
		  
		  public void showProductByManufacturer() {
			  String manufacturerID = getToken("Enter Manufacturer id:");
			  warehouse.showProductByManufacturer(manufacturerID);
		      
		  
		  }

	  private void save() {
		    if (warehouse.save()) {
		      System.out.println(" The warehouse has been successfully saved in the file WarehouseData \n" );
		    } else {
		      System.out.println(" There has been an error in saving \n" );
		    }
		  } 
		  private void retrieve() {
		    try {
		      Warehouse tempWareHouse = Warehouse.retrieve();
		      if (tempWareHouse != null) {
		        System.out.println(" The Warehouse has been successfully retrieved from the file WareHouseData \n" );
		        warehouse = tempWareHouse;
		      } else {
		        System.out.println("File doesnt exist; creating new WareHouse" );
		        warehouse = Warehouse.instance();
		      }
		    } catch(Exception cnfe) {
		    	
		      cnfe.printStackTrace();
		    }
		  }
	
	public void process() {
		    int command;
		    help();
		    while ((command = getCommand()) != EXIT) {
		      switch (command) {
		        case ADD_CLIENT:        addClient();
		                                break;
		       case ADD_PRODUCTS:         addProducts();
		                                break;
		       case ADD_MANUFACTURER:       addManufacturer();
		                                break;
		       case ASSIGN_PRODUCT_TO_MANUFACTURER:      assignProductToManufacturer();
		                                break;
		       case UNASSIGN_PRODUCT_TO_MANUFACTURER:      unassignProductToManufacturer();
               							break;
		       case SHOW_CLIENTS:	showClients();
		                                break; 		
		        case SHOW_PRODUCTS:		showProducts();
		                                break; 	
		        case SHOW_MANUFACTURERS:	showManufacturers();
                							break; 
		        case SHOW_SUPPLIERS_FOR_PRODUCT_WITH_PRICE: showSupplierByProductWithPrice();
		        								break; 
		        case SHOW_PRODUCTS_BY_MANUFACTURER: showProductByManufacturer();
		        									break; 
		        case SAVE:              save();
                						break;
		        case RETRIEVE:          retrieve();
                						break;
		      case HELP:              help();
		                                break; 
		      }
		    }
		  }
	
	

	public static void main(String[] args) {
		UserInterface.instance().process();

	}

}
