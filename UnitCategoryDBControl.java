package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UnitCategoryDBControl {
		public static boolean RegisterUnitCategory(String UnitCategoryName,String UnitCategoryRemark) {
			String sql = "INSERT INTO unit_category (Unit_Category_Name,Unit_Category_Remarks) VALUES (?,?)";
			try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql)){
				psmt.setString(1, UnitCategoryName);
				psmt.setString(2, UnitCategoryRemark);
				int row = psmt.executeUpdate();
				return row > 0;
			}catch (Exception e) {
				System.out.println("Error:" + e.toString());
				return false;
			}
		}
		public static ArrayList<UnitCategoryData> getUnitCategoryData() {
			
			String sql = "SELECT * from unit_category";
			ArrayList<UnitCategoryData> data = new ArrayList<>();
			try (Connection con = DBConnection.createConnection();
						PreparedStatement ps = con.prepareStatement(sql);){
					ResultSet rs = ps.executeQuery();
					int i=1;
					while (rs.next()) {
						UnitCategoryData unitcategorydata=new UnitCategoryData(rs.getInt("Unit_Category_ID"),rs.getString("Unit_Category_Name")
								,rs.getString("Unit_Category_Remarks"));
						data.add(unitcategorydata);
						i++;
					}
					rs.close();		
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
			}
			return data;
		}
		

		public static ArrayList<UnitCategoryData> UnitCategorysearchFunction(String search) {
			
			String sql = "SELECT * from unit_category where lower(Unit_Category_Name) LIKE ?";
			ArrayList<UnitCategoryData> data = new ArrayList<>();
			try (Connection con = DBConnection.createConnection();
						PreparedStatement ps = con.prepareStatement(sql);){
				ps.setString(1, "%"+search.toLowerCase()+"%");
					ResultSet rs = ps.executeQuery();
					int i=1;
					while (rs.next()) {
						UnitCategoryData unitcategorydata=new UnitCategoryData(rs.getInt("Unit_Category_ID"), 
								rs.getString("Unit_Category_Name"), rs.getString("Unit_Category_Remarks"));
						data.add(unitcategorydata);
						i++;
					}
					rs.close();	
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
			}
			return data;
		}
		public static int selctedUnitCategoryID(String UnitCategoryName,String UnitCategoryRemark) {
			String sql = "SELECT Unit_Category_ID from unit_category where Unit_Category_Name = ? and Unit_Category_Remarks = ?";
			try(PreparedStatement psmt=DBConnection.createConnection().prepareStatement(sql);){
				psmt.setString(1, UnitCategoryName);
				psmt.setString(2, UnitCategoryRemark);
				ResultSet rs=psmt.executeQuery();
				rs.next();
				return rs.getInt("Unit_Category_ID");
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
			}
			return 0;
		}
		public static boolean editUnitCategory(int UnitCategoryID,String UnitCategoryName,String UnitCategoryRemark) {
			String sql = "UPDATE unit_category SET Unit_Category_Name=?,"
					+ "Unit_Category_Remarks=? WHERE Unit_Category_ID='"+UnitCategoryID+"'";
			try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql);){
				psmt.setString(1, UnitCategoryName);
				psmt.setString(2, UnitCategoryRemark);
				int row = psmt.executeUpdate();
				return row > 0;
			}catch (Exception e) {
				System.out.println("Error:" + e.toString());
				e.printStackTrace();
				return false;
			}
		}
		public static boolean DeleteUnitCategory(int UnitCategoryID) {
			String sql = "Delete from unit_category WHERE Unit_Category_ID=?";
			try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql);){
				psmt.setInt(1, UnitCategoryID);
				int row = psmt.executeUpdate();
				return row > 0;
			}catch (Exception e) {
				System.out.println("Error:" + e.toString());
				return false;
			}
		}
		//Unit Category name
			public static ArrayList<String> getUnitCategoryName(){
				String sql="Select Unit_Category_Name from unit_category";
				try (Connection con = DBConnection.createConnection();
						PreparedStatement ps = con.prepareStatement(sql);){
					ResultSet rs = ps.executeQuery();
					int i = 1;
					ArrayList<String> UnitCategoryName = new ArrayList<>();
					while (rs.next()) {
						UnitCategoryName.add(rs.getString("Unit_Category_Name"));
						i++;
					}
					rs.close();
					return UnitCategoryName;
					
				
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
				return null;
			}
		}
		//Unit Category name
	
}
