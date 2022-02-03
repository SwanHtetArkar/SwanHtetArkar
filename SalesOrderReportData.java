package application; 

public class SalesOrderReportData {
	String Item_Name;
	int Quantity;
	int Price;
	public SalesOrderReportData(String item_Name, int quantity, int price) {
		super();
		Item_Name = item_Name;
		Quantity = quantity;
		Price = price;
	}
	public String getItem_Name() {
		return Item_Name;
	}
	public void setItem_Name(String item_Name) {
		Item_Name = item_Name;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}

}
