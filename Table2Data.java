package application;

public class Table2Data {
	private int Id;
	private String Name;
	private int Unit;
	private String ShowUnit;
	private String UnitName;
	private int Price;
	private String ShowPrice;
	private String ShowExtPrice;
	private String ShowCustomerPrice;
	private String ShowExtCustomerPrice;
	private int ExtPrice;
	private int CustomerPrice;
	private int extCustomerPrice;
	private String customizeQuantity;
	public Table2Data(int id,String name, String Unitname, int unit, int price, int customerPrice,String customizequantity) {
		super();
		Id = id;
		Name = name;
		Unit = unit;
		UnitName = Unitname;
		ShowUnit= unit+" "+Unitname;
		Price = price;
		ShowPrice = price+"Ks";
		ExtPrice = price*unit; 
		ShowExtPrice = (price*unit)+"Ks";
		CustomerPrice = customerPrice;
		ShowCustomerPrice = customerPrice+"Ks";
		ShowExtCustomerPrice = (customerPrice*unit)+"Ks";
		extCustomerPrice = customerPrice*unit;
		customizeQuantity = customizequantity;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getUnit() {
		return Unit;
	}
	public void setUnit(int unit) {
		Unit = unit;
	}
	public String getShowUnit() {
		return ShowUnit;
	}
	public void setShowUnit(String showUnit) {
		ShowUnit = showUnit;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public String getShowPrice() {
		return ShowPrice;
	}
	public void setShowPrice(String showPrice) {
		ShowPrice = showPrice;
	}
	public String getShowExtPrice() {
		return ShowExtPrice;
	}
	public void setShowExtPrice(String showextPrice) {
		ShowExtPrice = showextPrice;
	}
	public int getCustomerPrice() {
		return CustomerPrice;
	}
	public void setCustomerPrice(int customerPrice) {
		CustomerPrice = customerPrice;
	}
	public String getShowCustomerPrice() {
		return ShowCustomerPrice;
	}
	public void setShowCustomerPrice(String showCustomerPrice) {
		ShowCustomerPrice = showCustomerPrice;
	}
	public int getExtPrice() {
		return ExtPrice;
	}
	public void setExtPrice(int extPrice) {
		ExtPrice = extPrice;
	}
	public String getShowExtCustomerPrice() {
		return ShowExtCustomerPrice;
	}
	public void setShowExtCustomerPrice(String showExtCustomerPrice) {
		ShowExtCustomerPrice = showExtCustomerPrice;
	}
	public int getExtCustomerPrice() {
		return extCustomerPrice;
	}
	public void setExtCustomerPrice(int extCustomerPrice) {
		this.extCustomerPrice = extCustomerPrice;
	}
	public String getUnitName() {
		return UnitName;
	}
	public void setUnitName(String unitName) {
		UnitName = unitName;
	}
	public String getCustomizeQuantity() {
		return customizeQuantity;
	}
	public void setCustomizeQuantity(String customizeQuantity) {
		this.customizeQuantity = customizeQuantity;
	}
	

}
