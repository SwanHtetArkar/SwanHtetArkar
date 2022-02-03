package application;

import java.sql.*;
import java.util.ArrayList;

public class InventoryViewDBControl {

	//Inventory View DB Control
	public static ArrayList<InventoryData> getInventoryForward(int startPointer) {
	String sql = "SELECT * FROM inventoryview_forward,unit,unit_category "
				+ "WHERE inventoryview_forward.Unit_ID = unit.Unit_ID and "
				+ "unit.Unit_Category_ID = unit_category.Unit_Category_ID and Item_ID > "+startPointer
				+" ORDER BY Item_ID Limit 10";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<InventoryData> InventoryList = new ArrayList<>();
				while (rs.next()) {
				InventoryData cd = new InventoryData(rs.getInt("Item_ID"),rs.getString("Item_Code"),rs.getString("Item_Name")
						,rs.getString("Description"),rs.getInt("Price_Selling")
						,rs.getInt("Quantity"),rs.getString("Brand_Name"),rs.getString("Unit_Name"),rs.getString("Unit_Category_ID"),"Location");
				InventoryList.add(cd);
					
				}
				rs.close();
				return InventoryList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	

	public static ArrayList<InventoryData> getInventoryBackward(int endPointer) {
	String sql = "SELECT * from" + 
			" inventoryview_backward,unit,unit_category where" + 
			" inventoryview_backward.Unit_ID = unit.Unit_ID" + 
			" and unit.Unit_Category_ID = unit_category.Unit_Category_ID and Item_ID < "+endPointer
			+" ORDER BY Item_ID desc Limit 10";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<InventoryData> InventoryList = new ArrayList<>();
				while (rs.next()) {
				InventoryData cd = new InventoryData(rs.getInt("Item_ID"),rs.getString("Item_Code"),rs.getString("Item_Name")
						,rs.getString("Description"),rs.getInt("Price_Selling")
						,rs.getInt("Quantity"),rs.getString("Brand_Name"),rs.getString("Unit_Name"),rs.getString("Unit_Category_ID"),"Location");;
				InventoryList.add(cd);
					
				}
				rs.close();
				return InventoryList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	public static String getUnitCategoryName(int UnitCategoryID) {
		String sql = "SELECT Unit_Category_Name from unit_category where Unit_Category_ID=?";
		try(PreparedStatement psmt=DBConnection.createConnection().prepareStatement(sql);){
			psmt.setInt(1, UnitCategoryID);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				return rs.getString("Unit_Category_Name");
			}
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			e.printStackTrace();
		}
		return null;
	}


	public static ArrayList<InventoryData> getInventoryInitialize() {
		String sql = "SELECT * from"
				+ " inventoryview_forward,unit,unit_category where"
				+ " inventoryview_forward.Unit_ID = unit.Unit_ID"
				+ " and unit.Unit_Category_ID = unit_category.Unit_Category_ID  Limit 10";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<InventoryData> InventoryList = new ArrayList<>();
				while (rs.next()) {
				InventoryData cd = new InventoryData(rs.getInt("Item_ID"),rs.getString("Item_Code"),rs.getString("Item_Name")
						,rs.getString("Description"),rs.getInt("Price_Selling")
						,rs.getInt("Quantity"),rs.getString("Brand_Name"),rs.getString("Unit_Name"),rs.getString("Unit_Category_ID"),"Location");
				InventoryList.add(cd);
					
				}
				rs.close();
				return InventoryList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	 
	//Refreshing the Inventory view 
	public static boolean startup1() { 
		String sql = "CREATE or replace VIEW Inventoryview_backward " + 
				"as SELECT Item_ID,Item_Code,Item_Name,Description,Price_Selling,Quantity,Brand_Name,Unit_ID "
				+ "from `item`, `brand`  WHERE item.Brand_ID = brand.Brand_ID ORDER by Item_ID DESC"; 
		try (Connection con = DBConnection.createConnection(); 
					PreparedStatement ps = con.prepareStatement(sql);){ 
				int row = ps.executeUpdate(); 
				return row>0; 
			 
		} catch (Exception e) { 
			System.out.println("Error: "+ e.getMessage()); 
			return false; 
		} 
	} 
	public static boolean startup2() { 
		String sql = "CREATE or replace VIEW Inventoryview_forward " + 
				"as SELECT Item_ID,Item_Code,Item_Name,Description,Price_Selling,Quantity,Brand_Name,Unit_ID "
				+ "from `item`,`brand` WHERE item.Brand_ID = brand.Brand_ID"; 
		try (Connection con = DBConnection.createConnection(); 
					PreparedStatement ps = con.prepareStatement(sql);){ 
				int row = ps.executeUpdate(); 
				return row>0; 
			 
		} catch (Exception e) { 
			System.out.println("Error: "+ e.toString()); 
			return false; 
		} 
	} 
	public static boolean startup1(int unitID) { 
		String sql = "CREATE or replace VIEW Inventoryview_backward " + 
				"as SELECT Item_ID,Item_Code,Item_Name,Description,Price_Selling,Quantity,Brand_Name,Unit_ID "
				+ "from `item`, `brand`  WHERE item.Brand_ID = brand.Brand_ID and Unit_ID = "+unitID+" ORDER by Item_ID DESC"; 
		try (Connection con = DBConnection.createConnection(); 
					PreparedStatement ps = con.prepareStatement(sql);){ 
				int row = ps.executeUpdate(); 
				return row>0; 
			 
		} catch (Exception e) { 
			System.out.println("Error: "+ e.getMessage()); 
			return false; 
		} 
	} 
	public static boolean startup2(int unitID) { 
		String sql = "CREATE or replace VIEW Inventoryview_forward " + 
				"as SELECT Item_ID,Item_Code,Item_Name,Description,Price_Selling,Quantity,Brand_Name,Unit_ID "
				+ "from `item`,`brand` WHERE item.Brand_ID = brand.Brand_ID and Unit_ID = "+unitID; 
		try (Connection con = DBConnection.createConnection(); 
					PreparedStatement ps = con.prepareStatement(sql);){ 
				int row = ps.executeUpdate(); 
				return row>0; 
			 
		} catch (Exception e) { 
			System.out.println("Error: "+ e.toString()); 
			return false; 
		} 
	} 
	
	//Inventory View DB Control 
	
	//Inventory Database Control
	public static ItemData getItemData(int itemID) {
		String sql = "SELECT * FROM item WHERE Item_ID = ?";
		try(PreparedStatement psmt=DBConnection.createConnection().prepareStatement(sql);){
			psmt.setInt(1, itemID);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				return new ItemData(rs.getInt("Item_ID"), rs.getString("Item_Code"), rs.getString("Item_Name"),
						rs.getString("Description"), rs.getInt("Price_Purchased"), rs.getInt("price_Selling"),
						rs.getInt("price_Customer"), rs.getInt("quantity"), rs.getString("customized_Quantity"),
						rs.getInt("default_Quantity"), rs.getInt("minimum"), rs.getString("Brand_ID"),
						rs.getString("unit_id"));
						
			}
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getInventoryCategoryID(String InventoryCategoryName) {
		String sql = "SELECT Item_Category_ID from Item_category where Item_Category_Name=?";
		try(PreparedStatement psmt=DBConnection.createConnection().prepareStatement(sql);){
			psmt.setString(1, InventoryCategoryName);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				return rs.getInt("Inventory_Category_ID");
			}
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			e.printStackTrace();
		}
		return 0;
	}
		//Inventory	Register,Update,Delete,Search
		public static boolean RegisterInventory(String InventoryCode,String InventoryName,String InventoryRemark,int InventoryCategoryID) {
			String sql = "INSERT INTO Inventory(Inventory_Code,Inventory_Name,Inventory_Remarks,Inventory_Category_ID) VALUES (?,?,?,?)";
			try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql)){
				psmt.setString(1, InventoryCode);
				psmt.setString(2, InventoryName);
				psmt.setString(3, InventoryRemark);
				psmt.setInt(4, InventoryCategoryID);
				int row = psmt.executeUpdate();
				return row > 0;
			}catch (Exception e) {
				System.out.println("Error:" + e.toString());
				e.printStackTrace();
				return false;
			}
		}
		public static ArrayList<InventoryData> InventorysearchFunction(String search) {
			
			String sql = " SELECT * from"
					+ " inventoryview_forward,unit,unit_category where"
					+ " inventoryview_forward.Unit_ID = unit.Unit_ID"
					+ " and unit.Unit_Category_ID = unit_category.Unit_Category_ID and lower(Item_Name) LIKE ?";
			ArrayList<InventoryData> data = new ArrayList<>();
			try (Connection con = DBConnection.createConnection();
						PreparedStatement ps = con.prepareStatement(sql);){
				ps.setString(1, "%"+search.toLowerCase()+"%");
					ResultSet rs = ps.executeQuery();
					int i=1;
					while (rs.next()) {
						InventoryData Inventorydata=new InventoryData(rs.getInt("Item_ID"),rs.getString("Item_Code"),rs.getString("Item_Name")
								,rs.getString("Description"),rs.getInt("Price_Selling")
								,rs.getInt("Quantity"),rs.getString("Brand_Name"),rs.getString("Unit_Name"),rs.getString("Unit_Category_ID"),"Location"); ;
						data.add(Inventorydata);
						i++;
					}
					rs.close();	
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
				e.printStackTrace();
			}
			return data;
		}
		public static int selctedInventoryID(String InventoryCode,String InventoryName,String InventoryRemark) {
			String sql = "SELECT Inventory_ID from Inventory where Inventory_Code=?,Inventory_Name=? and Inventory_Remarks=?";
			try(PreparedStatement psmt=DBConnection.createConnection().prepareStatement(sql);){
				psmt.setString(1, InventoryCode);
				psmt.setString(2, InventoryName);
				psmt.setString(3, InventoryRemark);
				ResultSet rs=psmt.executeQuery();
				rs.next();
				return rs.getInt("Inventory_Category_ID");
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
				e.printStackTrace();
			}
			return 0;
		}
		public static boolean editInventory(int InventoryID,String InventoryCode,String InventoryName,String InventoryRemark,int InventoryCategoryID) {
			String sql = "UPDATE Inventory SET Inventory_Code=?,Inventory_Name=?,"
					+ "Inventory_Remarks=?,Inventory_Category_ID=? WHERE Inventory_ID='"+InventoryID+"'";
			try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql);){
				psmt.setString(1, InventoryCode);
				psmt.setString(2, InventoryName);
				psmt.setString(3, InventoryRemark);
				psmt.setInt(4, InventoryCategoryID);
				int row = psmt.executeUpdate();
				return row > 0;
			}catch (Exception e) {
				System.out.println("Error:" + e.toString());
				e.printStackTrace();
				return false;
			}
		}
		public static boolean DeleteInventory(int i) {
			String sql = "Delete from Item WHERE Item_ID=?";
			try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql);){
				psmt.setInt(1, i);
				int row = psmt.executeUpdate();
				return row > 0;
			}catch (Exception e) {
				System.out.println("Error:" + e.toString());
				e.printStackTrace();
				return false;
			}
		}
		//Inventory	save on action
		//Inventory code
			public static ArrayList<String> getInventoryCode(){
				String sql="Select Inventory_Code from Inventory";
				try (Connection con = DBConnection.createConnection();
						PreparedStatement ps = con.prepareStatement(sql);){
					ResultSet rs = ps.executeQuery();
					int i = 1;
					ArrayList<String> InventoryCode = new ArrayList<>();
					while (rs.next()) {
						InventoryCode.add(rs.getString("Inventory_Code"));
						i++;
					}
					rs.close();
					return InventoryCode;
					
				
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
				e.printStackTrace();
				return null;
			}
		}
		//Inventory code
			
		//Inventory Database Control	
}
