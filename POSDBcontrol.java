package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.*;

public class POSDBcontrol {
	
	public static ArrayList<ItemData> getItemData(String itemName){
		String sql = "SELECT * from item join brand where Item_Name LIKE ? and item.Brand_ID = brand.Brand_ID";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ps.setString(1, "%"+itemName+"%");
				ResultSet rs = ps.executeQuery();
				ArrayList<ItemData> itemList = new ArrayList<>();
				while (rs.next()) {
				itemList.add(new ItemData(rs.getInt("Item_ID"), rs.getString("Item_Code"), rs.getString("Item_Name"),
						rs.getString("Description"),rs.getInt("Price_Purchased"), rs.getInt("Price_Selling"),
						rs.getInt("Price_Customer"), rs.getInt("Quantity"),rs.getString("Customized_Quantity"),
						rs.getInt("Default_Quantity"), rs.getInt("Minimum"), rs.getString("Brand_Name"), rs.getString("Unit_ID")));
				}
				rs.close();  
				return itemList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	public static ItemData getItemData(int item_ID){
		String sql = "SELECT * from item join brand where Item_ID LIKE ? and item.Brand_ID = brand.Brand_ID";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ps.setInt(1, item_ID);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
				 return new ItemData(rs.getInt("Item_ID"), rs.getString("Item_Code"), rs.getString("Item_Name"),
						rs.getString("Description"),rs.getInt("Price_Purchased"), rs.getInt("Price_Selling"),
						rs.getInt("Price_Customer"), rs.getInt("Quantity"),rs.getString("Customized_Quantity"),
						rs.getInt("Default_Quantity"), rs.getInt("Minimum"), rs.getString("Brand_Name"), rs.getString("Unit_ID"));
				}
				rs.close();  
				return null;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	
	public static ArrayList<String> getCustomer(String name) {
		String sql = "SELECT * from customer where Customer_Name LIKE ? ";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ps.setString(1, "%"+name+"%");
				ResultSet rs = ps.executeQuery();
				ArrayList<String> customerList = new ArrayList<>();
				while (rs.next()) {
				customerList.add(rs.getString("Customer_Name") +":" +rs.getString("Customer_Phone"));
				}
				rs.close();
				return customerList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}

	public static ArrayList<String> getVendor(String name) {
		String sql = "SELECT * from vendor where Vendor_Name LIKE ? ";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ps.setString(1, "%"+name+"%");
				ResultSet rs = ps.executeQuery();
				ArrayList<String> customerList = new ArrayList<>();
				while (rs.next()) {
				customerList.add(rs.getString("Vendor_Name") +":" +rs.getString("Vendor_Phone"));
				}
				rs.close();
				return customerList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	public static String getUnit(int UnitID) {
		String sql = "SELECT Unit_Name from Unit where Unit_ID = ? ";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ps.setInt(1, UnitID);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
				return rs.getString("Unit_Name");
				}
				rs.close();
				return null;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	
	public static int InvoiceNumber() {
		String sql = "SELECT Sales_Order_ID FROM sales_order ORDER BY Sales_Order_ID DESC LIMIT 1";
		try (Connection con = DBConnection.createConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			return rs.getInt(1);
			}
			rs.close();
			return 0;		
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return 0;
		}
	}
	
	public static ArrayList<String> getSalesOrderTypes(){
		String sql = "SELECT * FROM sales_order_type;";
		try (Connection con = DBConnection.createConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery();
			ArrayList<String> data = new ArrayList<>();
			while (rs.next()) {
				data.add(rs.getString("Sales_Order_Type"));
			}
			rs.close();
			return data;		
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	public static boolean SaveSale(String Invoice_Code,
			int total_Price, int left_to_be_paid, int Sales_Order_Type_ID, int Customer_ID,int vendor_ID, String description) {
		String sql = "INSERT INTO sales_order(Invoice_Code,"
				+ "Total_Price, Left_To_Be_Paid, Sales_Order_Type_ID, Customer_ID,"
				+ "Vendor_ID,Description) VALUES"
				+ "(?,?,?,?,?,?,?)";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ps.setString(1, Invoice_Code);
				ps.setInt(2, total_Price);
				ps.setInt(3, left_to_be_paid);
				ps.setInt(4, Sales_Order_Type_ID);
				ps.setInt(5, Customer_ID);
				ps.setInt(6, vendor_ID);
				ps.setString(7, description);
				
				System.out.println("Output");
				int row = ps.executeUpdate();
				return row > 0;
				
			
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}
	public static boolean SaveSale(String Invoice_Code,
			int total_Price, int left_to_be_paid, int Sales_Order_Type_ID, String description) {
		String sql = "INSERT INTO sales_order(Invoice_Code,"
				+ "Total_Price, Left_To_Be_Paid, Sales_Order_Type_ID,"
				+ "Description) VALUES"
				+ "(?,?,?,?,?)";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ps.setString(1, Invoice_Code);
				ps.setInt(2, total_Price);
				ps.setInt(3, left_to_be_paid);
				ps.setInt(4, Sales_Order_Type_ID);
				ps.setString(5, description);
				
				int row = ps.executeUpdate();
				System.out.println(row);
				return row > 0;
				
			
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}
	public static boolean SaveDebt(int Customer_ID, int Price) {
		String sql = "Select Debt from customer where Customer_ID = ?";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ps.setInt(1, Customer_ID);
				ResultSet rs = ps.executeQuery();
				boolean flag ;
				while(rs.next()) {
					flag = AddToDebt(Customer_ID, Price, rs.getInt("Debt"));
				}
			return false;	
			
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}
	public static boolean AddToDebt(int Customer_ID, int Price,int OriginalDebt) {
		String sql = "UPDATE customer SET Debt=? WHERE Customer_ID=?";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				int debt = OriginalDebt+Price;
				
				ps.setInt(1, debt);
				ps.setInt(2, Customer_ID);
				int row = ps.executeUpdate();
				return row>0;
			
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}

	public static int getCustomerID(String name,String phone){
		String sql = "SELECT * FROM customer where Customer_Name Like '"+name+"' and Customer_Phone Like '"+phone+"';";
		try (Connection con = DBConnection.createConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("Customer_ID");
			}
			rs.close();
			return 0;
		} catch (Exception e) {
			e.getMessage();
			return 0;
		}
	}
	public static int getVendorID(String name,String phone){
		String sql = "SELECT * FROM vendor where Vendor_Name Like '"+name+"' and Vendor_Phone Like '"+phone+"';";
		try (Connection con = DBConnection.createConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("Vendor_ID");
			}
			rs.close();
			return 0;
		} catch (Exception e) {
			e.getMessage();
			return 0;
		}
	}
	public static boolean saveItem(int salesOrderID,Table2Data data) {
		String sql = "INSERT INTO sales_order_item(Sales_Order_ID, Item_ID, Quantity, Item_Price) VALUES (?,?,?,?)";
		try (Connection con = DBConnection.createConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			ps.setInt(1, salesOrderID);
			ps.setInt(2, data.getId());
			ps.setInt(3, data.getUnit());
			ps.setInt(4, data.getPrice());
			int row = ps.executeUpdate();

	    	if (getCustomizeUnit(data.getId())==null) {
	    		POSDBcontrol.reduceItem(PosController.selected.getItem_ID(), data.getUnit());
			}else {
				POSDBcontrol.saveCustomizeUnit(PosController.selected.getItem_ID(), CustomizedQuantityController.CustomizeUnit);
			}
			return row > 0;
		
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
		
	}
	public static boolean saveCustomerItem(int salesOrderID,Table2Data data) {
		String sql = "INSERT INTO sales_order_item(Sales_Order_ID, Item_ID, Quantity, Item_Price) VALUES (?,?,?,?)";
		try (Connection con = DBConnection.createConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			ps.setInt(1, salesOrderID);
			ps.setInt(2, data.getId());
			ps.setInt(3, data.getUnit());
			ps.setInt(4, data.getCustomerPrice());
			int row = ps.executeUpdate();
	    	if (getCustomizeUnit(data.getId())==null) {
	    		POSDBcontrol.reduceItem(PosController.selected.getItem_ID(), data.getUnit());
			}else {
				POSDBcontrol.saveCustomizeUnit(PosController.selected.getItem_ID(), CustomizedQuantityController.CustomizeUnit);
			}
		
			return row > 0;

		} catch (Exception e) {
			e.getMessage();
			return false;
		}
		
	}
	public static boolean reduceItem(int itemID,int soldQuanitity) {
		String sql = "Update item set Quantity= ? where Item_ID=?";
		try (Connection con = DBConnection.createConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			ps.setInt(1, getResidualItem(itemID)-soldQuanitity);
			ps.setInt(2, itemID);
			int row = ps.executeUpdate();
			System.out.println(row>0);
			return row > 0;
			
		
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
		
	}

	public static int getResidualItem(int itemID) {
		String sql = "select * from item where Item_ID = ?";
		try (Connection con = DBConnection.createConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			ps.setInt(1, itemID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt("Quantity");
				
			}
			return 0;
		
		} catch (Exception e) {
			e.getMessage();
			return 0;
		}
		
	}

	public static boolean saveCustomizeUnit(int Item_ID, String customized_quantity) {
		String sql = "UPDATE item SET Customized_Quantity=? WHERE Item_ID=?";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				
				ps.setString(1, customized_quantity);
				ps.setInt(2, Item_ID);
				int row = ps.executeUpdate();
				return row>0;
			
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}
	public static String getCustomizeUnit(int Item_ID) {
		String sql = "select * from item where Item_ID = ?";
		try (Connection con = DBConnection.createConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			ps.setInt(1, Item_ID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString("Customized_Quantity");
				
			}
			return null;
		
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}
		
}

