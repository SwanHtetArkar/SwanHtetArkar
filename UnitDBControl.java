package application;

import java.sql.*;
import java.util.ArrayList;

public class UnitDBControl {
	//Unit View DB Control
	public static ArrayList<UnitData> getUnitForward(int startPointer) {
		String sql = "SELECT Unit_ID,Unit_Code,Unit_Name,Unit_Remarks,Unit_Category_Name FROM `unit`,`unit_category` "
				+ "WHERE unit.Unit_Category_ID=unit_category.Unit_Category_ID and Unit_ID > "+startPointer
				+" ORDER BY Unit_ID Limit 10";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<UnitData> UnitList = new ArrayList<>();
				while (rs.next()) {
				UnitData cd = new UnitData(rs.getInt("Unit_ID"),rs.getString("Unit_Code"),rs.getString("Unit_Name")
						,rs.getString("Unit_Remarks"),rs.getString("Unit_Category_Name"));
				UnitList.add(cd);
					
				}
				rs.close();
				return UnitList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	

	public static ArrayList<UnitData> getUnitBackward(int endPointer) {
		String sql = "SELECT Unit_ID,Unit_Code,Unit_Name,Unit_Remarks,Unit_Category_Name FROM `unit`,`unit_category` "
				+ "WHERE unit.Unit_Category_ID=unit_category.Unit_Category_ID and Unit_ID < "+endPointer
				+" ORDER BY Unit_ID desc Limit 10";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<UnitData> UnitList = new ArrayList<>();
				while (rs.next()) {
				UnitData cd = new UnitData(rs.getInt("Unit_ID"),rs.getString("Unit_Code"),rs.getString("Unit_Name")
						,rs.getString("Unit_Remarks"),rs.getString("Unit_Category_Name"));
				UnitList.add(cd);
					
				}
				rs.close();
				return UnitList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}


	public static ArrayList<UnitData> getUnitInitialize() {
		String sql = "SELECT * from"
				+ " unitview_forward  Limit 10";
		try (Connection con = DBConnection.createConnection();
					PreparedStatement ps = con.prepareStatement(sql);){
				ResultSet rs = ps.executeQuery();
				ArrayList<UnitData> UnitList = new ArrayList<>();
				while (rs.next()) {
				UnitData cd = new UnitData(rs.getInt("Unit_ID"),rs.getString("Unit_Code"),rs.getString("Unit_Name")
						,rs.getString("Unit_Remarks"),rs.getString("Unit_Category_Name"));
				UnitList.add(cd);
					
				}
				rs.close();
				return UnitList;
				 
				
			
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			return null;
		}
	}
	 
	//Refreshing the unit view 
	public static boolean startup1() { 
		String sql = "CREATE or replace VIEW unitview_backward as" 
				+ " SELECT Unit_ID,Unit_Code,Unit_Name,Unit_Remarks,Unit_Category_Name FROM `unit`,`unit_category` " 
				+ "WHERE unit.Unit_Category_ID=unit_category.Unit_Category_ID " 
				+ "ORDER by Unit_ID DESC"; 
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
		String sql = "CREATE or replace VIEW unitview_forward as" 
				+ " SELECT Unit_ID,Unit_Code,Unit_Name,Unit_Remarks,Unit_Category_Name FROM `unit`,`unit_category` " 
				+ "WHERE unit.Unit_Category_ID=unit_category.Unit_Category_ID"; 
		try (Connection con = DBConnection.createConnection(); 
					PreparedStatement ps = con.prepareStatement(sql);){ 
				int row = ps.executeUpdate(); 
				return row>0; 
			 
		} catch (Exception e) { 
			System.out.println("Error: "+ e.toString()); 
			return false; 
		} 
	} 
	//Unit View DB Control 
	
	//Unit Database Control
	public static int getUnitCategoryID(String UnitCategoryName) {
		String sql = "SELECT Unit_Category_ID from unit_category where Unit_Category_Name=?";
		try(PreparedStatement psmt=DBConnection.createConnection().prepareStatement(sql);){
			psmt.setString(1, UnitCategoryName);
			ResultSet rs=psmt.executeQuery();
			while(rs.next()) {
				return rs.getInt("Unit_Category_ID");
			}
		} catch (Exception e) {
			System.out.println("Error: "+ e.toString());
			e.printStackTrace();
		}
		return 0;
	}
		//Unit	Register,Update,Delete,Search
		public static boolean RegisterUnit(String UnitCode,String UnitName,String UnitRemark,int unitCategoryID) {
			String sql = "INSERT INTO unit(Unit_Code,Unit_Name,Unit_Remarks,Unit_Category_ID) VALUES (?,?,?,?)";
			try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql)){
				psmt.setString(1, UnitCode);
				psmt.setString(2, UnitName);
				psmt.setString(3, UnitRemark);
				psmt.setInt(4, unitCategoryID);
				int row = psmt.executeUpdate();
				return row > 0;
			}catch (Exception e) {
				System.out.println("Error:" + e.toString());
				e.printStackTrace();
				return false;
			}
		}
	public static ArrayList<UnitData> getUnitData() {
			
			String sql = "SELECT Unit_ID,Unit_Code,Unit_Name,Unit_Remarks,Unit_Category_Name FROM `unit`,`unit_category` "
					+ "WHERE unit.Unit_Category_ID=unit_category.Unit_Category_ID";
			ArrayList<UnitData> data = new ArrayList<>();
			try (Connection con = DBConnection.createConnection();
						PreparedStatement ps = con.prepareStatement(sql);){
					ResultSet rs = ps.executeQuery();
					int i=1;
					while (rs.next()) {
						UnitData unitdata=new UnitData(rs.getInt("Unit_ID"),rs.getString("Unit_Code"),rs.getString("Unit_Name"),
										  rs.getString("Unit_Remarks"), rs.getString("Unit_Category_Name"));
						data.add(unitdata);
						i++;
					}
					rs.close();		
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
				e.printStackTrace();
			}
			return data;
		}
		
	
		public static ArrayList<UnitData> UnitsearchFunction(String search) {
			
			String sql = "SELECT Unit_Code,Unit_Name,Unit_Remarks,Unit_Category_Name"
					+ " from unit,unit_category WHERE unit.Unit_Category_ID=unit_category.Unit_Category_ID "
					+ "and lower(Unit_Name) LIKE ?";
			ArrayList<UnitData> data = new ArrayList<>();
			try (Connection con = DBConnection.createConnection();
						PreparedStatement ps = con.prepareStatement(sql);){
				ps.setString(1, "%"+search.toLowerCase()+"%");
					ResultSet rs = ps.executeQuery();
					int i=1;
					while (rs.next()) {
						UnitData unitdata=new UnitData(i,rs.getString("Unit_Code"),rs.getString("Unit_Name")
								,rs.getString("Unit_Remarks"),rs.getString("Unit_Category_Name")) ;
						data.add(unitdata);
						i++;
					}
					rs.close();	
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
				e.printStackTrace();
			}
			return data;
		}
		public static int selctedUnitID(String UnitCode,String UnitName,String UnitRemark) {
			String sql = "SELECT Unit_ID from unit where Unit_Code=?,Unit_Name=? and Unit_Remarks=?";
			try(PreparedStatement psmt=DBConnection.createConnection().prepareStatement(sql);){
				psmt.setString(1, UnitCode);
				psmt.setString(2, UnitName);
				psmt.setString(3, UnitRemark);
				ResultSet rs=psmt.executeQuery();
				rs.next();
				return rs.getInt("Unit_Category_ID");
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
				e.printStackTrace();
			}
			return 0;
		}
		public static boolean editUnit(int unitID,String UnitCode,String UnitName,String UnitRemark,int UnitCategoryID) {
			String sql = "UPDATE unit SET Unit_Code=?,Unit_Name=?,"
					+ "Unit_Remarks=?,Unit_Category_ID=? WHERE Unit_ID='"+unitID+"'";
			try(PreparedStatement psmt = DBConnection.createConnection().prepareStatement(sql);){
				psmt.setString(1, UnitCode);
				psmt.setString(2, UnitName);
				psmt.setString(3, UnitRemark);
				psmt.setInt(4, UnitCategoryID);
				int row = psmt.executeUpdate();
				return row > 0;
			}catch (Exception e) {
				System.out.println("Error:" + e.toString());
				e.printStackTrace();
				return false;
			}
		}
		public static boolean DeleteUnit(int i) {
			String sql = "Delete from unit WHERE Unit_ID=?";
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
		//Unit	save on action
		//Unit code
			public static ArrayList<String> getUnitCode(){
				String sql="Select Unit_Code from unit";
				try (Connection con = DBConnection.createConnection();
						PreparedStatement ps = con.prepareStatement(sql);){
					ResultSet rs = ps.executeQuery();
					int i = 1;
					ArrayList<String> UnitCode = new ArrayList<>();
					while (rs.next()) {
						UnitCode.add(rs.getString("Unit_Code"));
						i++;
					}
					rs.close();
					return UnitCode;
					
				
			} catch (Exception e) {
				System.out.println("Error: "+ e.toString());
				e.printStackTrace();
				return null;
			}
		}
		//Unit code
			
		//Unit Database Control	
}
