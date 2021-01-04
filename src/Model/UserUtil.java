package Model;
import java.sql.ResultSet;
import java.util.Date;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserUtil {
	
	ConnectDBUtil kn = new ConnectDBUtil();

	
	public ResultSet getUser() {
		ResultSet rs = null;
		String sql = "SELECT * FROM User";
		try { 
			kn.ExecuteNonQuery(sql);
			rs = kn.getTable(sql);
	        Alert a = new Alert(AlertType.INFORMATION,"Get users successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
		return rs;
	}
	
	public void insertUser(int userID,String userName,Date userDOB,String userPhone,String userEmail,String userAddress) {
		String sql = " INSERT INTO User VALUES('" + userID + "','" + userName + "','" + userDOB + "','" + userPhone + "','"+ userEmail + "','"+ userAddress + "')";
		try {
			kn.ExecuteNonQuery(sql);
			Alert a = new Alert(AlertType.INFORMATION,"Insert user successfully!");
		        a.show();
		}
		catch(Exception e) {
		}
	}
	public void updatetUser(int userID,String userName,Date userDOB,String userPhone,String userEmail,String userAddress) {
		String sql = " UPDATE User SET UserID='" + userID + "', UserName='" + userName + "', UserDOB='" + userDOB + "', UserPhone='" + userPhone + "', UserEmail='"+ userEmail + "', UserAddress='"+ userAddress + "' WHERE UserID='" +userID+"'";
		try {
			kn.ExecuteNonQuery(sql);
			 Alert a = new Alert(AlertType.INFORMATION,"Uppdate user successfully!");
		        a.show();
		}
		catch(Exception e) {
		}
	}
	public void deleteUser(int userID) {
		String sql = "DELETE User WHERE UserID = '" + userID + "'";
		try {
			kn.ExecuteNonQuery(sql);
			Alert a = new Alert(AlertType.INFORMATION,"Delete user successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
	}
	public ResultSet Search(String condi)
    {
		ResultSet rs = null;
        String sql = "SELECT * FROM User WHERE UserID like '%" + condi + "%' OR UserName like '%" + condi + "%'";
        try {
        	kn.ExecuteNonQuery(sql);
			rs = kn.getTable(sql);
        }
        catch(Exception e) {       	
        }
        return rs;
    }
}

