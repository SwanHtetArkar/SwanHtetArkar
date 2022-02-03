package application;

public class InventoryData {
	private int item_ID;
	private String Item_Code;
	private String Item_Name;
	private String Description;
	private int Price_Selling;
	private int Quantity;
	private String Brand_Name;
	private String Unit;
	private String Unit_Type;
	private String Location;
	public InventoryData(int item_ID, String item_Code, String item_Name, String description, int price_Selling,
			int quantity, String brand_Name, String unit, String unit_Type, String location) {
		super();
		this.item_ID = item_ID;
		Item_Code = item_Code;
		Item_Name = item_Name;
		Description = description;
		Price_Selling = price_Selling;
		Quantity = quantity;
		Brand_Name = brand_Name;
		Unit = unit;
		Unit_Type= InventoryViewDBControl.getUnitCategoryName(Integer.parseInt(unit_Type));
		Location = location;
	}
	public int getItem_ID() {
		return item_ID;
	}
	public void setItem_ID(int item_ID) {
		this.item_ID = item_ID;
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
	public int getPrice_Selling() {
		return Price_Selling;
	}
	public void setPrice_Selling(int price_Selling) {
		Price_Selling = price_Selling;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getBrand_Name() {
		return Brand_Name;
	}
	public void setBrand_Name(String brand_Name) {
		Brand_Name = brand_Name;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
	}
	public String getUnit_Type() {
		return Unit_Type;
	}
	public void setUnit_Type(String unit_Type) {
		Unit_Type = unit_Type;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	
}