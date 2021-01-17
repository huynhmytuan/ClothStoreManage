package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginUtil {
	ConnectDBUtil kn = new ConnectDBUtil();
	
	public ResultSet getLogin(String username, String pass) {
		ResultSet rs = null;
		Connection cn =null;
		PreparedStatement ptm = null;
		String sql = "SELECT * FROM [Login] WHERE Username=? AND Password=?";
		try { 
			cn = kn.getConnect();
			ptm = cn.prepareStatement(sql);
			ptm.setString(1, username);
			ptm.setString(2, pass);
			rs = ptm.executeQuery();
	        
		}
		catch(Exception e) {
			
		}
		return rs;
	}
	public void insertLogin(int loginID,String userName,String passWord,int loginRoleID,int userID) {
		String sql = " INSERT INTO Login VALUES('" + loginID + "','" + userName + "','" + passWord + "','" + loginRoleID + "','"+ userID + "')";
		try {
			kn.ExecuteNonQuery(sql);
			System.out.println("Insert user successfully!");
		}
		catch(Exception e) {
		}
	}
	public void updateUsernameNRoleID(String userName, int loginRoleID,int userID) {
		String sql = "UPDATE Login SET  UserName='" + userName +  "', LoginRoleID='" + loginRoleID + "'WHERE UserID='" +userID+"'";
		try {			
			kn.ExecuteNonQuery(sql);
		}
		catch(Exception e) {
		}
	}
	
	public void changePass(String newPass,int userID) {
		String sql = "UPDATE Login SET Password='" + newPass + "'WHERE UserID='" +userID+"'";
		try {			
			kn.ExecuteNonQuery(sql);
		}
		catch(Exception e) {
		}
	}
	
	public void deleteLogin(int userID) {
		String sql = "DELETE Login WHERE UserID='" + userID + "'";
		try {
			kn.ExecuteNonQuery(sql);
		}
		catch(Exception e) {
		}
	}
	public ObservableList<Login> getDataList(){
        ObservableList<Login> list = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            String sql = "SELECT LoginID, Username FROM Login";
            rs = kn.getTable(sql);
            while (rs.next()){   	
                list.add(new Login(rs.getInt("LoginID"), rs.getString("Username")));               
            }
        } 
        catch (Exception e) {
        	Alert a = new Alert(AlertType.INFORMATION,"Database Error: "+e.getMessage());
	        a.show();
        }
        return list;
   }
	
	public ObservableList<Login> getListByID(int id){
        ObservableList<Login> list = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            String sql = "SELECT LoginID, Username, LoginRoleID FROM Login WHERE UserID='"+id+"'";
            rs = kn.getTable(sql);
            while (rs.next()){   	
                list.add(new Login(rs.getInt("LoginID"), rs.getString("Username"), rs.getInt("LoginRoleID")));               
            }
        } 
        catch (Exception e) {
        	Alert a = new Alert(AlertType.INFORMATION,"Database Error: "+e.getMessage());
	        a.show();
        }
        return list;
   }
}
