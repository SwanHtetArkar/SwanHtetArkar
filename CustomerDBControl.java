package application;

import java.sql.PreparedStatement;

public class CustomerDBControl {
//	save on action
	public static boolean RegisterCustomer(String CustomerName,String CustomerAddress,String CustomerPhone) {
		String sql = "INSERT INTO customer(Customer_Name,Customer_Address,Customer_Phone) VALUES (?,?,?)";
		try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql)){
			psmt.setString(1, CustomerName);
			psmt.setString(2, CustomerAddress);
			psmt.setString(3, CustomerPhone);
			int row = psmt.executeUpdate();
			return row > 0;
		}catch (Exception e) {
			System.out.println("Error:" + e.toString());
			return false;
		}
	}
//	save on action
}
