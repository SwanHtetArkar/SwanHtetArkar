package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.*;

public class SalesOrderReportDBControl {
	public static Double MonthlySales(int month,int year) {
			
			String sql = "SELECT * from sales_order_item join sales_order where sales_order.Sales_Order_ID = sales_order_item.Sales_Order_ID and MONTH(Sales_Order_Date_Time) "
					+ "= cast('"+month+"' as integer) and YEAR(Sales_Order_Date_Time) = cast('"+ year+"' as integer)";
			double monthlyProfitTotal = 0;
			try (PreparedStatement ps = DBConnection.createConnection().prepareStatement(sql)){
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						monthlyProfitTotal = monthlyProfitTotal+(rs.getInt("Item_Price")*rs.getInt("Quantity"));
					} 
//					monthlyProfitTotal-=MonthlyUsage(month);
					rs.close();
					
					
				
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
			}
			return monthlyProfitTotal;
		}
	public static ArrayList<SalesOrderReportData> salesItemByYear(int year){
		String sql = "SELECT * from sales_order_item join sales_order join item where sales_order.Sales_Order_ID = sales_order_item.Sales_Order_ID  "
				+ "and item.Item_ID = sales_order_item.Item_ID and YEAR(Sales_Order_Date_Time) = cast('"+ year+"' as integer)";
		try (PreparedStatement ps = DBConnection.createConnection().prepareStatement(sql)){
				ResultSet rs = ps.executeQuery();
				ArrayList<SalesOrderReportData> data = new ArrayList<>();
				while (rs.next()) {
					data.add(new SalesOrderReportData(rs.getString("Item_Name"), rs.getInt("Sales_Order_Item.Quantity"),
							rs.getInt("Sales_Order_Item.Quantity")*rs.getInt("Sales_Order_Item.Item_Price")));
				} 
				rs.close();
				return  data;
				
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
		}
		return null;
	}
	public static ArrayList<SalesOrderReportData> salesItemByMonth(int month ,int year){
		String sql = "SELECT * from sales_order_item join sales_order join item where sales_order.Sales_Order_ID = sales_order_item.Sales_Order_ID  "
				+ "and item.Item_ID = sales_order_item.Item_ID and MONTH(Sales_Order_Date_Time) "
				+ "= cast('"+month+"' as integer) and YEAR(Sales_Order_Date_Time) = cast('"+ year+"' as integer)";
		try (PreparedStatement ps = DBConnection.createConnection().prepareStatement(sql)){
				ResultSet rs = ps.executeQuery();
				ArrayList<SalesOrderReportData> data = new ArrayList<>();
				while (rs.next()) {
					data.add(new SalesOrderReportData(rs.getString("Item_Name"), rs.getInt("Sales_Order_Item.Quantity"),
							rs.getInt("Sales_Order_Item.Quantity")*rs.getInt("Sales_Order_Item.Item_Price")));
				} 
				rs.close();
				return  data;
				
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
		}
		return null;
	}
	public static ArrayList<SalesOrderReportData> salesItemByDate(int day,int month ,int year){
		String sql = "SELECT * from sales_order_item join sales_order join item where sales_order.Sales_Order_ID = sales_order_item.Sales_Order_ID  "
				+ "and item.Item_ID = sales_order_item.Item_ID and DAY(Sales_Order_Date_Time) = cast('"+ day+"' as integer) and MONTH(Sales_Order_Date_Time) "
				+ "= cast('"+month+"' as integer) and YEAR(Sales_Order_Date_Time) = cast('"+ year+"' as integer)";
		try (PreparedStatement ps = DBConnection.createConnection().prepareStatement(sql)){
				ResultSet rs = ps.executeQuery();
				ArrayList<SalesOrderReportData> data = new ArrayList<>();
				while (rs.next()) {
					data.add(new SalesOrderReportData(rs.getString("Item_Name"), rs.getInt("Sales_Order_Item.Quantity"),
							rs.getInt("Sales_Order_Item.Quantity")*rs.getInt("Sales_Order_Item.Item_Price")));
				} 
				rs.close();
				return  data;
				
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
		}
		return null;
	}
	public static ArrayList<SalesOrderReportData> salesItemByYearSearchByItemName(String name,int year){
		String sql = "SELECT * from sales_order_item join sales_order join item where sales_order.Sales_Order_ID = sales_order_item.Sales_Order_ID  "
				+ "and item.Item_ID = sales_order_item.Item_ID and Item_Name LIKE ? and YEAR(Sales_Order_Date_Time) = cast('"+ year+"' as integer)";
		try (PreparedStatement ps = DBConnection.createConnection().prepareStatement(sql)){
			ps.setString(1, "%"+name+"%");
				ResultSet rs = ps.executeQuery();
				ArrayList<SalesOrderReportData> data = new ArrayList<>();
				while (rs.next()) {
					data.add(new SalesOrderReportData(rs.getString("Item_Name"), rs.getInt("Sales_Order_Item.Quantity"),
							rs.getInt("Sales_Order_Item.Quantity")*rs.getInt("Sales_Order_Item.Item_Price")));
				} 
				rs.close();
				return  data;
				
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
		}
		return null;
	}
	public static ArrayList<SalesOrderReportData> salesItemByMonthSearchByItemName(String name,int month ,int year){
		String sql = "SELECT * from sales_order_item join sales_order join item where sales_order.Sales_Order_ID = sales_order_item.Sales_Order_ID  "
				+ "and item.Item_ID = sales_order_item.Item_ID and Item_Name LIKE ? and MONTH(Sales_Order_Date_Time) "
				+ "= cast('"+month+"' as integer) and YEAR(Sales_Order_Date_Time) = cast('"+ year+"' as integer)";
		try (PreparedStatement ps = DBConnection.createConnection().prepareStatement(sql)){
			ps.setString(1, "%"+name+"%");
				ResultSet rs = ps.executeQuery();
				ArrayList<SalesOrderReportData> data = new ArrayList<>();
				while (rs.next()) {
					data.add(new SalesOrderReportData(rs.getString("Item_Name"), rs.getInt("Sales_Order_Item.Quantity"),
							rs.getInt("Sales_Order_Item.Quantity")*rs.getInt("Sales_Order_Item.Item_Price")));
				} 
				rs.close();
				return  data;
				
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
		}
		return null;
	}
	public static ArrayList<SalesOrderReportData> salesItemByDateSearchByItemName(String name,int day,int month ,int year){
		String sql = "SELECT * from sales_order_item join sales_order join item where sales_order.Sales_Order_ID = sales_order_item.Sales_Order_ID  "
				+ "and item.Item_ID = sales_order_item.Item_ID and Item_Name LIKE ? and DAY(Sales_Order_Date_Time) = cast('"+ day+"' as integer) and MONTH(Sales_Order_Date_Time) "
				+ "= cast('"+month+"' as integer) and YEAR(Sales_Order_Date_Time) = cast('"+ year+"' as integer)";
		try (PreparedStatement ps = DBConnection.createConnection().prepareStatement(sql)){
			ps.setString(1, "%"+name+"%");
				ResultSet rs = ps.executeQuery();
				ArrayList<SalesOrderReportData> data = new ArrayList<>();
				while (rs.next()) {
					data.add(new SalesOrderReportData(rs.getString("Item_Name"), rs.getInt("Sales_Order_Item.Quantity"),
							rs.getInt("Sales_Order_Item.Quantity")*rs.getInt("Sales_Order_Item.Item_Price")));
				} 
				rs.close();
				return  data;
				
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
		}
		return null;
	}
}
