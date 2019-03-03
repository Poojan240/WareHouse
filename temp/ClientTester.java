//package warehouseStage1;

import java.util.Iterator;

public class ClientTester {

	public static void main(String[] args) {
		
		ClientList List1 = new ClientList();
		
		Client c1 = new Client("Amir Shrestha", "523 12 South Street", "2132156789","abc@gmail.com");
		Client c2 = new Client("Poojan Pradhan", "180 University SE", "3202827560","def@gmail.com");
		List1.insertClient(c1);
		List1.insertClient(c2);
		System.out.println(List1.toString());
	

		     

	}

}
