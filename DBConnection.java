package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection createConnection() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost/thapyay__ni","root","");
	}

	public static void main(String[] args) {
		try {
			System.out.println(createConnection());
		} catch (Exception e) {
			// TODO: handle exception
		}
				

	}

}
