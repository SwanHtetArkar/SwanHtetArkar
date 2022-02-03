package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemDBControl {
	
//InventoryNewController	
//	combo box
	//save
	public static ArrayList<String> getBrandName() {
		String sql = "SELECT Brand_Name from brand";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<String> BrandName = new ArrayList<>();
				while (rs.next()) {
					BrandName.add(rs.getString("Brand_Name"));
				}
				rs.close();
				return BrandName;
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	
	public static ArrayList<String> getUnitName() {
		String sql = "SELECT Unit_Name from unit";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<String> UnitName = new ArrayList<>();
				while (rs.next()) {
					UnitName.add(rs.getString("Unit_Name"));
				}
				rs.close();
				return UnitName;
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	//save
	//edit
	public static ArrayList<String> getBrandNameForEdit(int Item_ID) {
		String sql = "SELECT Brand_Name FROM `brand`JOIN `item` WHERE item.Brand_ID=unit.Brand_ID"
				+ " AND Item_ID="+Item_ID;
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<String> BrandName = new ArrayList<>();
				while (rs.next()) {
					BrandName.add(rs.getString("Brand_Name"));
				}
				rs.close();
				return BrandName;
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	public static ArrayList<String> getUnitNameForEdit(int Item_ID) {
		String sql = "SELECT Unit_Name FROM `unit`JOIN `item` WHERE item.Unit_ID=unit.Unit_ID"
				+ " AND Item_ID="+Item_ID;
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<String> UnitName = new ArrayList<>();
				while (rs.next()) {
					UnitName.add(rs.getString("Unit_Name"));
				}
				rs.close();
				return UnitName;
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	//edit
//	combo box
//item code
	public static ArrayList<String> getItemCode(){
		String sql="Select Item_Code from item";
		try (Connection con = DBConnection.createConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery();
			int i = 1;
			ArrayList<String> ItemCode = new ArrayList<>();
			while (rs.next()) {
				ItemCode.add(rs.getString("Item_Code"));
				i++;
			}
			rs.close();
			return ItemCode;
			
		
	} catch (Exception e) {
		System.out.println("Error: "+ e.toString());
		return null;
	}
	}
//item code
//	save on action
	//register
	public static boolean saveItem(String Item_Code,String Item_Name,String Description,double Price_Purchased,double Price_Selling
			,double Price_Customer
			,int Quantity,String Customized_Quantity,int Default_Quantity,int Minimum,int Brand_ID,int Unit_ID) {
		String sql = "INSERT INTO item(Item_Code,Item_Name,Description,Price_Purchased,Price_Selling,Price_Customer"
				+ ",Quantity,Customized_Quantity,Default_Quantity,Minimum,Brand_ID,Unit_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql)){
			psmt.setString(1, Item_Code);
			psmt.setString(2, Item_Name);
			psmt.setString(3, Description);
			psmt.setDouble(4, Price_Purchased);
			psmt.setDouble(5, Price_Selling);
			psmt.setDouble(6, Price_Customer);
			psmt.setInt(7, Quantity);
			psmt.setString(8, Customized_Quantity);
			psmt.setInt(9, Default_Quantity);
			psmt.setInt(10, Minimum);
			psmt.setInt(11, Brand_ID);
			psmt.setInt(12, Unit_ID);
			int row = psmt.executeUpdate();
			return row > 0;
		}catch (Exception e) {
			System.out.println("Error:" + e.toString());
			return false;
		}
	}
	public static boolean saveItem(String Item_Code,String Item_Name,String Description,double Price_Purchased,double Price_Selling
			,double Price_Customer
			,int Quantity,int Default_Quantity,int Minimum,int Brand_ID,int Unit_ID) {
		String sql = "INSERT INTO item(Item_Code,Item_Name,Description,Price_Purchased,Price_Selling,Price_Customer"
				+ ",Quantity,Default_Quantity,Minimum,Brand_ID,Unit_ID) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql)){
			psmt.setString(1, Item_Code);
			psmt.setString(2, Item_Name);
			psmt.setString(3, Description);
			psmt.setDouble(4, Price_Purchased);
			psmt.setDouble(5, Price_Selling);
			psmt.setDouble(6, Price_Customer);
			psmt.setInt(7, Quantity);
			psmt.setInt(8, Default_Quantity);
			psmt.setInt(9, Minimum);
			psmt.setInt(10, Brand_ID);
			psmt.setInt(11, Unit_ID);
			int row = psmt.executeUpdate();
			return row > 0;
		}catch (Exception e) {
			System.out.println("Error:" + e.toString());
			return false;
		}
	}
	//register
	//edit
	public static boolean UpdateItem(int selectedID,String Item_Code,String Item_Name,String Description,
			double Price_Purchased,double Price_Selling,double Price_Customer
			,int Quantity,String Customized_Quantity,int Default_Quantity,int Minimum,int Brand_ID,int Unit_ID) {
		String sql = "update item set Item_Code=?,Item_Name=?,Description=?,Price_Purchased=?,Price_Selling=?,"
				+ "Price_Customer=?,Quantity=?,Customized_Quantity=?,Default_Quantity=?,"
				+ "Minimum=?,Brand_ID=?,Unit_ID=? where Item_ID = "+selectedID;
		try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql)){
			psmt.setString(1, Item_Code);
			psmt.setString(2, Item_Name);
			psmt.setString(3, Description);
			psmt.setDouble(4, Price_Purchased);
			psmt.setDouble(5, Price_Selling);
			psmt.setDouble(6, Price_Customer);
			psmt.setInt(7, Quantity);
			psmt.setString(8, Customized_Quantity);
			psmt.setInt(9, Default_Quantity);
			psmt.setInt(10, Minimum);
			psmt.setInt(11, Brand_ID);
			psmt.setInt(12, Unit_ID);
			int row = psmt.executeUpdate();
			return row > 0;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static int getUnitID(String UnitName) {
		String sql = "SELECT Unit_ID from unit where Unit_Name=?";
		try(PreparedStatement psmt=DBConnection.createConnection().prepareStatement(sql);){
			psmt.setString(1, UnitName);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				return rs.getInt("Unit_ID");
			}
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			e.printStackTrace();
		}
		return 0;
	}
	//edit
//	save on action
//inventoryNewController
}
