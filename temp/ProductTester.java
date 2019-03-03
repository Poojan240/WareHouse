//package warehouseStage1;

public class ProductTester {

	public static void main(String[] args) {
		ProductList List1 = new ProductList();
		Product p1 = new Product("P1", "Pen", "Stationary","2");
		Product p2 = new Product("P2", "Shirt", "Wardrobe","20");
		List1.insertProduct(p1);
	    List1.insertProduct(p2);
	    System.out.println(List1.toString());

	}

}
