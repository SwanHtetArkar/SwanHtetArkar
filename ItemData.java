package application;

import javafx.scene.control.SpinnerValueFactory;

public class ItemData {

	private int Item_ID;
	private String Item_Code;
	private String Item_Name;
	private String Description;
	private int Price_Purchased;
	private int Price_Selling;
	private int Price_Customer;
	private int Quantity;
	private String Customize_Quantity;
	private int Default_Quantity;
	private int Minimum;
	private String Brand_Name;
	private String Unit_Name;
	
	public ItemData(int item_ID, String item_Code, String item_Name, String description, int price_Purchased,
			int price_Selling, int price_Customer, int quantity, String customize_Quantity,
			int default_Quantity,
			int minimum, String brand_Name, String unit_Name) {
		super();
		Item_ID = item_ID;
		Item_Code = item_Code;
		Item_Name = item_Name;
		Description = description;
		Price_Purchased = price_Purchased;
		Price_Selling = price_Selling;
		Price_Customer = price_Customer;
		Quantity = quantity;
		Customize_Quantity = customize_Quantity;
		Default_Quantity = default_Quantity;
		Minimum = minimum;
		Brand_Name = brand_Name;
		Unit_Name = unit_Name;
	}

	public int getItem_ID() {
		return Item_ID;
	}

	public void setItem_ID(int item_ID) {
		Item_ID = item_ID;
	}

	public String getItem_Code() {
		return Item_Code;
	}

	public void setItem_Code(String item_Code) {
		Item_Code = item_Code;
	}

	public String getItem_Name() {
		return Item_Name;
	}

	public void setItem_Name(String item_Name) {
		Item_Name = item_Name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getPrice_Purchased() {
		return Price_Purchased;
	}

	public void setPrice_Purchased(int price_Purchased) {
		Price_Purchased = price_Purchased;
	}

	public int getPrice_Selling() {
		return Price_Selling;
	}

	public void setPrice_Selling(int price_Selling) {
		Price_Selling = price_Selling;
	}

	public int getPrice_Customer() {
		return Price_Customer;
	}

	public void setPrice_Customer(int price_Customer) {
		Price_Customer = price_Customer;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public String getCustomize_Quantity() {
		return Customize_Quantity;
	}

	public void setCustomize_Quantity(String customize_Quantity) {
		Customize_Quantity = customize_Quantity;
	}

	public int getDefault_Quantity() {
		return Default_Quantity;
	}

	public void setDefault_Quantity(int default_Quantity) {
		Default_Quantity = default_Quantity;
	}

	public int getMinimum() {
		return Minimum;
	}

	public void setMinimum(int minimum) {
		Minimum = minimum;
	}

	public String getBrand_Name() {
		return Brand_Name;
	}

	public void setBrand_Name(String brand_Name) {
		Brand_Name = brand_Name;
	}

	public String getUnit_Name() {
		return Unit_Name;
	}

	public void setUnit_Name(String unit_Name) {
		Unit_Name = unit_Name;
	}
	
	
}
