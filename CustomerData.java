package application;

public class CustomerData {
	private int customer_ID;
	private String customer_Name;
	private String customer_Address;
	private String customer_Phone;
	private String townshipName;
	public int getCustomer_ID() {
		return customer_ID;
	}
	public void setCustomer_ID(int customer_ID) {
		this.customer_ID = customer_ID;
	}
	public String getCustomer_Name() {
		return customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}
	public String getCustomer_Address() {
		return customer_Address;
	}
	public void setCustomer_Address(String customer_Address) {
		this.customer_Address = customer_Address;
	}
	public String getCustomer_Phone() {
		return customer_Phone;
	}
	public void setCustomer_Phone(String customer_Phone) {
		this.customer_Phone = customer_Phone;
	}
	public String getTownshipName() {
		return townshipName;
	}
	public void setTownshipName(String townshipName) {
		this.townshipName = townshipName;
	}
	public CustomerData(int customer_ID, String customer_Name, String customer_Address, String customer_Phone,
			String townshipName) {
		super();
		this.customer_ID = customer_ID;
		this.customer_Name = customer_Name;
		this.customer_Address = customer_Address;
		this.customer_Phone = customer_Phone;
		this.townshipName = townshipName;
	}
	

}
