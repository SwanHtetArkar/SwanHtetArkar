package application;

public class UnitCategoryData {
	private int UnitCategoryID;
	private String UnitCategoryName;
	private String UnitCategoryRemark;
	public UnitCategoryData(int unitCategoryID, String unitCategoryName, String unitCategoryRemark) {
		super();
		UnitCategoryID = unitCategoryID;
		UnitCategoryName = unitCategoryName;
		UnitCategoryRemark = unitCategoryRemark;
	}
	public int getUnitCategoryID() {
		return UnitCategoryID;
	}
	public void setUnitCategoryID(int unitCategoryID) {
		UnitCategoryID = unitCategoryID;
	}
	public String getUnitCategoryName() {
		return UnitCategoryName;
	}
	public void setUnitCategoryName(String unitCategoryName) {
		UnitCategoryName = unitCategoryName;
	}
	public String getUnitCategoryRemark() {
		return UnitCategoryRemark;
	}
	public void setUnitCategoryRemark(String unitCategoryRemark) {
		UnitCategoryRemark = unitCategoryRemark;
	}
	
}
