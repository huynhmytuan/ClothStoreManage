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
	public void updateLogin(int loginID,String userName,String passWord,int loginRoleID,int userID) {
		String sql = " UPDATE User SET LoginID='" + loginID + "', UserName='" + userName + "', PassWord='" + passWord + "', LoginRoleID='" + loginRoleID + "', UserID='"+ userID + "'WHERE LoginID='" +loginID+"'";
		try {			
			kn.ExecuteNonQuery(sql);
			Alert a = new Alert(AlertType.INFORMATION,"Update successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
	}
	public void deleteLogin(int loginID) {
		String sql = "DELETE User WHERE LoginID='" + loginID + "'";
		try {
			kn.ExecuteNonQuery(sql);
			Alert a = new Alert(AlertType.INFORMATION,"Delete successfully!");
	        a.show();
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
}
