package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CustomerDebtListDBControl {
	
	public static ArrayList<CustomerDebtData> getCustomer(String name) {
		String sql = "SELECT * from customer where Customer_Name LIKE ? AND Debt>0";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ps.setString(1, "%"+name+"%");
				ResultSet rs = ps.executeQuery();
				ArrayList<CustomerDebtData> CustomerDebtList = new ArrayList<>();
				while (rs.next()) {
					CustomerDebtList.add(new CustomerDebtData(rs.getString("Customer_Name"),
							rs.getString("Customer_Phone"),rs.getInt("Debt")));
				}
				rs.close();
				return CustomerDebtList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	

	public static boolean PayDebt(int Customer_ID, int Price) {
		String sql = "UPDATE customer SET Debt=? WHERE Customer_ID=?";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				
				ps.setInt(1, Price);
				ps.setInt(2, Customer_ID);
				int row = ps.executeUpdate();
				return row>0;
			
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}
	
}
