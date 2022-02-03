package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;



public class VendorDBControl {
	//Vendor View DB Control
	public static ArrayList<VendorData> getVendorForward(int startPointer) {
		String sql = "SELECT Vendor_ID,Vendor_Name,Vendor_Address,Vendor_Phone,Vendor_Photo FROM `vendor` "
				+ "WHERE Vendor_ID > "+startPointer
				+" ORDER BY Vendor_ID Limit 10";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<VendorData> VendorList = new ArrayList<>();
				while (rs.next()) {
				VendorData vd = new VendorData(rs.getInt("Vendor_ID"),rs.getString("Vendor_Name"),
						rs.getString("Vendor_Address"),rs.getString("Vendor_Phone"), rs.getString("Vendor_Photo"));
				VendorList.add(vd);
					
				}
				rs.close();
				return VendorList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	

	public static ArrayList<VendorData> getVendorBackward(int endPointer) {
		String sql = "SELECT Vendor_ID,Vendor_Name,Vendor_Address,Vendor_Phone,Vendor_Photo FROM `vendor`  "
				+ "WHERE Vendor_ID < "+endPointer
				+" ORDER BY Vendor_ID desc Limit 10";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<VendorData> VendorList = new ArrayList<>();
				while (rs.next()) {
					VendorData vd = new VendorData(rs.getInt("Vendor_ID"),rs.getString("Vendor_Name"),
							rs.getString("Vendor_Address"),rs.getString("Vendor_Phone"), rs.getString("Vendor_Photo"));
					VendorList.add(vd);
					
				}
				rs.close();
				return VendorList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}


	public static ArrayList<VendorData> getVendorInitialize() {
		String sql = "SELECT * from"
				+ " vendorview_forward  Limit 10";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<VendorData> VendorList = new ArrayList<>();
				while (rs.next()) {
					VendorData vd = new VendorData(rs.getInt("Vendor_ID"),rs.getString("Vendor_Name"),
							rs.getString("Vendor_Address"),rs.getString("Vendor_Phone"), rs.getString("Vendor_Photo"));
					VendorList.add(vd);
					
				}
				rs.close();
				return VendorList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	 
	//Refreshing the vendor view 
	public static boolean startup1() { 
		String sql = "CREATE or replace VIEW vendorview_backward as" 
				+ " SELECT Vendor_ID,Vendor_Name,Vendor_Address,Vendor_Phone,Vendor_Photo FROM `vendor` " 
				+ "ORDER by Vendor_ID DESC"; 
		try (Connection con = DBConnection.createConnection(); 
					PreparedStatement ps = con.prepareStatement(sql);){ 
				int row = ps.executeUpdate(); 
				return row>0; 
			 
		} catch (Exception e) { 
			System.out.println("Error: "+ e.toString()); 
			return false; 
		} 
	} 
	public static boolean startup2() { 
		String sql = "CREATE or replace VIEW vendorview_forward as" 
				+ " SELECT Vendor_ID,Vendor_Name,Vendor_Address,Vendor_Phone,Vendor_Photo FROM `vendor` " ; 
		try (Connection con = DBConnection.createConnection(); 
					PreparedStatement ps = con.prepareStatement(sql);){ 
				int row = ps.executeUpdate(); 
				return row>0; 
			 
		} catch (Exception e) { 
			System.out.println("Error: "+ e.toString()); 
			return false; 
		} 
	} 
	//Vendor View DB Control 
	
	//Vendor Database Control
		//Vendor Register,Update,Delete,Search
		public static boolean RegisterVendor(String VendorName,String VendorAddress,String VendorPhone,String VendorPhoto) {
			String sql = "INSERT INTO vendor(Vendor_Name,Vendor_Address,Vendor_Phone,Vendor_Photo) VALUES (?,?,?,?)";
			try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql)){
				psmt.setString(1, VendorName);
				psmt.setString(2, VendorAddress);
				psmt.setString(3, VendorPhone);
				psmt.setString(4, VendorPhoto);
				int row = psmt.executeUpdate();
				return row > 0;
			}catch (Exception e) {
				System.out.println("Error:" + e.toString());
				e.printStackTrace();
				return false;
			}
		}
	public static ArrayList<VendorData> getVendorData() {
			
			String sql = " SELECT Vendor_ID,Vendor_Name,Vendor_Address,Vendor_Phone,Vendor_Photo FROM `vendor` ";
			ArrayList<VendorData> data = new ArrayList<>();
			try (Connection con = DBConnection.createConnection();
						PreparedStatement ps = con.prepareStatement(sql);){
					ResultSet rs = ps.executeQuery();
					int i=1;
					while (rs.next()) {
					VendorData vendordata=new VendorData(rs.getInt("Vendor_ID"),rs.getString("Vendor_Name"),
							rs.getString("Vendor_Address"),rs.getString("Vendor_Phone"),rs.getString("Vendor_Photo"));
						data.add(vendordata);
						i++;
					}
					rs.close();		
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
				e.printStackTrace();
			}
			return data;
		}
		
	
		public static ArrayList<VendorData> VendorsearchFunction(String search) {
			
			String sql = " SELECT Vendor_ID,Vendor_Name,Vendor_Address,Vendor_Phone,Vendor_Photo FROM `vendor` " 
					+ "WHERE lower(Vendor_Name) LIKE ?";
			ArrayList<VendorData> data = new ArrayList<>();
			try (Connection con = DBConnection.createConnection();
						PreparedStatement ps = con.prepareStatement(sql);){
				ps.setString(1, "%"+search.toLowerCase()+"%");
					ResultSet rs = ps.executeQuery();
					int i=1;
					while (rs.next()) {
						VendorData vendordata=new VendorData(i,rs.getString("Vendor_Name"),rs.getString("Vendor_Address")
								,rs.getString("Vendor_Phone"), rs.getString("Vendor_Photo")) ;
						data.add(vendordata);
						i++;
					}
					rs.close();	
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
				e.printStackTrace();
			}
			return data;
		}
		public static int selctedVendorID(String VendorName,String VendorAddress,String VendorPhone,Blob VendorPhoto) {
			String sql = "SELECT Unit_ID from unit where Unit_Code=?,Unit_Name=? and Unit_Remarks=?";
			try(PreparedStatement psmt=DBConnection.createConnection().prepareStatement(sql);){
				psmt.setString(1, VendorName);
				psmt.setString(2, VendorAddress);
				psmt.setString(3, VendorPhone);
				psmt.setBlob(4, VendorPhoto);
				ResultSet rs=psmt.executeQuery();
				rs.next();
				return rs.getInt("Vendor_ID");
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
				e.printStackTrace();
			}
			return 0;
		}
		public static boolean editVendor(int VendorID,String VendorName,String VendorAddress,String VendorPhone,String VendorPhoto) throws Exception {
			String sql = "UPDATE vendor SET Vendor_Name=?,Vendor_Address=?,"
					+ "Vendor_Phone=?,Vendor_Photo=? WHERE Vendor_ID='"+VendorID+"'";
			try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql);){
//				
//				Blob blob = DBConnection.createConnection().createBlob();
//				blob.setBinaryStream(VendorPhoto.read());
				psmt.setString(1, VendorName);
				psmt.setString(2, VendorAddress);
				psmt.setString(3, VendorPhone);
				psmt.setString(4, VendorPhoto);
				int row = psmt.executeUpdate();
				return row > 0;
			}catch (SQLException | FileNotFoundException e) {
				e.printStackTrace();
				return false;
			}
		}
		public static boolean DeleteVendor(int VendorID) {
			String sql = "Delete from vendor WHERE Vendor_ID=?";
			try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql);){
				psmt.setInt(1, VendorID);
				int row = psmt.executeUpdate();
				return row > 0;
			}catch (Exception e) {
				System.out.println("Error:" + e.toString());
				e.printStackTrace();
				return false;
			}
		}
		//Vendor save on action
			
		//Vendor Database Control	
}
