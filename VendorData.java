package application;

import java.sql.Blob;

public class VendorData {
	private int VendorID;
	private String VendorName;
	private String VendorAddress;
	private String VendorPhone;
	private String VendorPhoto;
	
	public VendorData(int vendorID, String vendorName, String vendorAddress, String vendorPhone,String vendorPhoto) {
		super();
		VendorID = vendorID;
		VendorName = vendorName;
		VendorAddress = vendorAddress;
		VendorPhone = vendorPhone;
		VendorPhoto = vendorPhoto;
	}

		// TODO Auto-generated constructor stub
	public int getVendorID() {
		return VendorID;
	}
	public void setVendorID(int vendorID) {
		VendorID = vendorID;
	}
	public String getVendorName() {
		return VendorName;
	}
	public String getVendorPhoto() {
		return VendorPhoto;
	}
	public void setVendorPhoto(String vendorPhoto) {
		VendorPhoto = vendorPhoto;
	}
	public void setVendorName(String vendorName) {
		VendorName = vendorName;
	}
	public String getVendorAddress() {
		return VendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		VendorAddress = vendorAddress;
	}
	public String getVendorPhone() {
		return VendorPhone;
	}
	public void setVendorPhone(String vendorPhone) {
		VendorPhone = vendorPhone;
	}
	
}
