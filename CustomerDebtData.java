package application;

public class CustomerDebtData {
	private String Customer_Name;
	private String Customer_Phone_Number;
	private int Debt_Amount;
	public CustomerDebtData(String customer_Name, String customer_Phone_Number, int debt_Amount) {
		super();
		Customer_Name = customer_Name;
		Customer_Phone_Number = customer_Phone_Number;
		Debt_Amount = debt_Amount;
	}
	public String getCustomer_Name() {
		return Customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		Customer_Name = customer_Name;
	}
	public String getCustomer_Phone_Number() {
		return Customer_Phone_Number;
	}
	public void setCustomer_Phone_Number(String customer_Phone_Number) {
		Customer_Phone_Number = customer_Phone_Number;
	}
	public int getDebt_Amount() {
		return Debt_Amount;
	}
	public void setDebt_Amount(int debt_Amount) {
		Debt_Amount = debt_Amount;
	}
	
	
}
