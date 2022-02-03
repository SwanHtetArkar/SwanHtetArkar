package application;

import java.sql.PreparedStatement;

public class CustomerUpdateDBControl {
//	save on action
	public static boolean UpdateCustomer(int selectedID, String CustomerName,String CustomerAddress,String CustomerPhone) {
		String sql = "update customer set Customer_Name= ?,Customer_Address = ?,Customer_Phone=? where Customer_ID = "+selectedID;
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
