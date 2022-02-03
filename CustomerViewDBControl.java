package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class CustomerViewDBControl {
	public static ArrayList<CustomerData> getCustomerForward(int startPointer) {
		String sql = "SELECT * from CustomerView_Forward where Customer_ID > "+startPointer
				+" ORDER BY Customer_ID Limit 10";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<CustomerData> customerList = new ArrayList<>();
				while (rs.next()) {
				CustomerData cd = new CustomerData(rs.getInt("Customer_ID"),rs.getString("Customer_Name"),rs.getString("Customer_Address")
							,rs.getString("Customer_Phone"),rs.getString("Township_Name"));
				customerList.add(cd);
					
				}
				rs.close();
				return customerList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	

	public static ArrayList<CustomerData> getCustomerBackward(int endPointer) {
		String sql = "SELECT * from CustomerView_Backward where Customer_ID < "+endPointer
				+" ORDER BY Customer_ID desc Limit 10";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<CustomerData> customerList = new ArrayList<>();
				while (rs.next()) {
				CustomerData cd = new CustomerData(rs.getInt("Customer_ID"),rs.getString("Customer_Name"),rs.getString("Customer_Address")
							,rs.getString("Customer_Phone"),rs.getString("Township_Name"));
				customerList.add(cd);
					
				}
				rs.close();
				return customerList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}


	public static ArrayList<CustomerData> getCustomerInitialize() {
		String sql = "SELECT * from"
				+ " CustomerView_Forward  Limit 10";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<CustomerData> customerList = new ArrayList<>();
				while (rs.next()) {
				CustomerData cd = new CustomerData(rs.getInt("Customer_ID"),rs.getString("Customer_Name"),rs.getString("Customer_Address")
							,rs.getString("Customer_Phone"),rs.getString("Township_Name"));
				customerList.add(cd);
					
				}
				rs.close();
				return customerList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	
	public static CustomerData getSpecificCustomer(int Customer_ID) {
		String sql = "SELECT Customer_ID,Customer_Name,Customer_Address,Customer_Phone,Township_Name from"
				+ " customer join township WHERE customer.Township_ID = township.Township_ID and Customer_ID = "+Customer_ID;
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
				CustomerData cd = new CustomerData(rs.getInt("Customer_ID"),rs.getString("Customer_Name"),rs.getString("Customer_Address")
							,rs.getString("Customer_Phone"),rs.getString("Township_Name"));
					return cd;
					
				}
				rs.close();
				return null;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}

	public static ArrayList<CustomerData> getSpecificCustomer(String Customer_Name) {
		String sql = "SELECT Customer_ID,Customer_Name,Customer_Address,Customer_Phone,Township_Name from"
				+ " customer join township WHERE customer.Township_ID = township.Township_ID and Customer_Name like ?";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
			ps.setString(1, "%"+Customer_Name+"%");
				ResultSet rs = ps.executeQuery();
				ArrayList<CustomerData> list = new ArrayList<>();
				while (rs.next()) {
				CustomerData cd = new CustomerData(rs.getInt("Customer_ID"),rs.getString("Customer_Name"),rs.getString("Customer_Address")
							,rs.getString("Customer_Phone"),rs.getString("Township_Name"));
					
					if (cd != null) {
						list.add(cd);
					}
				}
				rs.close();
				return list;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}

	public static boolean filterForward(String filterTownship) {
		String sql = "CREATE or REPLACE VIEW customerview_forward AS " + 
				"SELECT Customer_ID,Customer_Name,Customer_Address,Customer_Phone,Township_Name " + 
				"FROM customer join township " + 
				"WHERE customer.Township_ID = township.Township_ID and Township_Name = '"+filterTownship+"'";
		try(Connection con = DBConnection.createConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			
			int row = ps.executeUpdate();
			return row > 0;
		}catch (Exception e) {
			System.out.println("Error:" + e.toString());
			return false;
		} 
	}
	public static boolean filterBackward(String filterTownship) {
		String sql = "CREATE or REPLACE VIEW customerview_backward AS " + 
				"SELECT Customer_ID,Customer_Name,Customer_Address,Customer_Phone,Township_Name " + 
				"FROM customer join township " + 
				"WHERE customer.Township_ID = township.Township_ID and Township_Name = '"+filterTownship+ "'"
				+" order by Customer_ID desc";
		try(Connection con = DBConnection.createConnection();
				PreparedStatement ps = con.prepareStatement(sql);){
			
			int row = ps.executeUpdate();
			return row > 0;
		}catch (Exception e) {
			System.out.println("Error:" + e.toString());
			return false;
		} 
	}

	public static ArrayList<String> getTownship() {
		String sql = "SELECT Township_Name from township;";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<String> Township_Names = new ArrayList<>();
				while (rs.next()) {
					Township_Names.add(rs.getString("Township_Name"));
				}
				rs.close();
				return Township_Names;
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}

	public static boolean deleteRow(int selectedID ) {
		String sql = "DELETE FROM customer WHERE Customer_ID = "+selectedID;
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				int row = ps.executeUpdate();
				return row>0;
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return false;
		}
	}
	public static boolean startup1() {
		String sql = "CREATE or replace VIEW customerview_backward as"
				+ " SELECT Customer_ID,Customer_Name,Customer_Address,Customer_Phone,Township_Name "
				+ "from customer join township "
				+ "WHERE customer.Township_ID = township.Township_ID "
				+ "ORDER by customer_ID DESC";
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
		String sql = "CREATE or replace VIEW customerview_forward as"
				+ " SELECT Customer_ID,Customer_Name,Customer_Address,Customer_Phone,Township_Name "
				+ "from customer join township "
				+ "WHERE customer.Township_ID = township.Township_ID";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				int row = ps.executeUpdate();
				return row>0;
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return false;
		}
	}
	
	public static boolean ascending1() {
		String sql = "CREATE or replace VIEW customerview_forward as"
				+ " SELECT Customer_ID,Customer_Name,Customer_Address,Customer_Phone,Township_Name "
				+ "from customer join township "
				+ "WHERE customer.Township_ID = township.Township_ID";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				int row = ps.executeUpdate();
				ascending2();
				return row>0;
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return false;
		}
	}
	public static boolean ascending2() {
		String sql = "CREATE or replace VIEW customerview_backward as"
				+ " SELECT Customer_ID,Customer_Name,Customer_Address,Customer_Phone,Township_Name "
				+ "from customer join township "
				+ "WHERE customer.Township_ID = township.Township_ID "
				+ "ORDER by customer_ID DESC";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				int row = ps.executeUpdate();
				return row>0;
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return false;
		}
	}
	

	public static boolean descending1() {
		String sql = "CREATE or replace VIEW customerview_backward as"
				+ " SELECT Customer_ID,Customer_Name,Customer_Address,Customer_Phone,Township_Name "
				+ "from customer join township "
				+ "WHERE customer.Township_ID = township.Township_ID";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				int row = ps.executeUpdate();
				descending2();
				return row>0;
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return false;
		}
	}
	public static boolean descending2() {
		String sql = "CREATE or replace VIEW customerview_forward as"
				+ " SELECT Customer_ID,Customer_Name,Customer_Address,Customer_Phone,Township_Name "
				+ "from customer join township "
				+ "WHERE customer.Township_ID = township.Township_ID "
				+ "ORDER by customer_ID DESC";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				int row = ps.executeUpdate();
				return row>0;
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return false;
		}
	}
	
}
