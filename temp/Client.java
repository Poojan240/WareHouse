//package warehouseStage1;
import java.util.*;
import java.io.*;


public class Client implements Serializable {
	private static final long serialVersionUID=1L;
	private String clientName;
	private String clientAddress;
	private String clientPhone;
	private String clientId;
	private String clientEmail;
	private static final String CLIENT_STRING = "CL";
	private List addProducttoManufacturer = new LinkedList();
	//will have to add transaction may be later
	public Client (String clientName,String clientAddress,String clientPhone, String clientEmail) {
		this.clientName=clientName;
		this.clientAddress=clientAddress;
		this.clientPhone=clientPhone;
		this.clientEmail=clientEmail;
		
		clientId = CLIENT_STRING + (ClientIdServer.instance()).getId();
	}
	
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public String getClientPhone() {
		return clientPhone;
	}
	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public boolean equals(String id) {
		return this.clientId.equals(id);
	}
	
	public String toString() {
		String string = "Client Name " + clientName + " ClientAddress " + clientAddress + " Clientid " + 
	clientId + " ClientPhone " + clientPhone;
		return string;
	}
	
}