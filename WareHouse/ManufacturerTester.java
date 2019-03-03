//package warehouseStage1;

public class ManufacturerTester {

	public static void main(String[] args) {
		ManufacturerList List1 = new ManufacturerList();
		Manufacturer m1 = new Manufacturer("M1", "Walmart", "St.Cloud","3203203202","abc@walmart.com");
		Manufacturer m2 = new Manufacturer("P2", "Shirt", "Wardrobe","2102102101","def@gmail.com");
		List1.insertManufacturer(m1);
	    List1.insertManufacturer(m2);
	    System.out.println(List1.toString());
	}

}
