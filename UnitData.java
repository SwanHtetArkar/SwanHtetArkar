package application;

public class UnitData {
	private int UnitID;
	private String UnitCode;
	private String UnitName;
	private String UnitRemark;
	private String UnitCategoryName;
	public UnitData(int unitID, String unitCode, String unitName, String unitRemark, String unitCategoryName) {
		super();
		UnitID = unitID;
		UnitCode = unitCode;
		UnitName = unitName;
		UnitRemark = unitRemark;
		UnitCategoryName = unitCategoryName;
	}
	public int getUnitID() {
		return UnitID;
	}
	public void setUnitID(int unitID) {
		UnitID = unitID;
	}
	public String getUnitCode() {
		return UnitCode;
	}
	public void setUnitCode(String unitCode) {
		UnitCode = unitCode;
	}
	public String getUnitName() {
		return UnitName;
	}
	public void setUnitName(String unitName) {
		UnitName = unitName;
	}
	public String getUnitRemark() {
		return UnitRemark;
	}
	public void setUnitRemark(String unitRemark) {
		UnitRemark = unitRemark;
	}
	public String getUnitCategoryName() {
		return UnitCategoryName;
	}
	public void setUnitCategoryName(String unitCategoryName) {
		UnitCategoryName = unitCategoryName;
	}
	
}
