package Model;

import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RoleUtil {
	ConnectDBUtil kn = new ConnectDBUtil();
	
	public ResultSet getRole() {
		ResultSet rs = null;
		String sql = "SELECT * FROM Role";
		try {			 
			kn.ExecuteNonQuery(sql);
			rs = kn.getTable(sql);	        
		}
		catch(Exception e) {
		}
		return rs;
	}
	public void insertRole(int roleID,String roleName) {
		@SuppressWarnings("unused")
		ResultSet rs = null;
		String sql = " INSERT INTO Role VALUES('" + roleID + "','" + roleName + "')";
		try {
			kn.ExecuteNonQuery(sql);
			rs = kn.getTable(sql);
		}
		catch(Exception e) {
		}
	}
	public void updatetRole(int roleID,String roleName) {
		String sql = " UPDATE Role SET RoleID='" + roleID + "', RoleName='" + roleName + "'WHERE RoleID='" +roleID+"'";
		try {
			kn.ExecuteNonQuery(sql);
		}
		catch(Exception e) {
		}
	}
	public void deleteRole(int RoleID) {
		String sql = "DELETE Role WHERE RoleID='" + RoleID + "'";
		try {
			kn.ExecuteNonQuery(sql);
			Alert a = new Alert(AlertType.INFORMATION,"Delete role successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
	}
	public ResultSet Search(String condi) {
		ResultSet rs = null;
        String sql = "SELECT * FROM Role WHERE RoleID like '%" + condi + "%' OR RoleName like '%" + condi + "%'";
        try {
        	kn.ExecuteNonQuery(sql);
			rs = kn.getTable(sql);
        }
        catch(Exception e) {       	
        }
        return rs;
    }
	 public ObservableList<Role> getRoleList(){
	        ObservableList<Role> list = FXCollections.observableArrayList();
	        ResultSet rs = null;
	        try {
	            rs = getRole();
	            while (rs.next()){
	                list.add(new Role(rs.getInt("RoleID"), rs.getString("RoleName")));               
	            }
	        } 
	        catch (Exception e) {
	        	Alert a = new Alert(AlertType.INFORMATION,"Database Error: "+e.getMessage());
		        a.show();
	        }
	        return list;
	   }
}
