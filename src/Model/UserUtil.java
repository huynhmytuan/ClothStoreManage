package Model;
import java.sql.ResultSet;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserUtil {
	
	ConnectDBUtil kn = new ConnectDBUtil();

	
	public ResultSet getUser() {
		ResultSet rs = null;
		String sql = "SELECT * FROM Users";
		try { 
			kn.ExecuteNonQuery(sql);
			rs = kn.getTable(sql);
			System.out.println("Get user successfully!");
		}
		catch(Exception e) {
		}
		return rs;
	}
	
	public void insertUser(int userID,String userName,LocalDate userDOB,String userPhone,String userEmail,String userAddress) {
		String sql = " INSERT INTO Users VALUES('" + userID + "','" + userName + "','" + userDOB + "','" + userPhone + "','"+ userEmail + "','"+ userAddress + "')";
		try {
			kn.ExecuteNonQuery(sql);
			System.out.println("Insert user successfully!");
		}
		catch(Exception e) {
		}
	}
	
	public void updatetUser(int userID,String userName,LocalDate userDOB,String userPhone,String userEmail,String userAddress) {
		String sql = " UPDATE Users SET UserID='" + userID + "', UserName='" + userName + "', UserDOB='" + userDOB + "', UserPhone='" + userPhone + "', UserEmail='"+ userEmail + "', UserAddress='"+ userAddress + "' WHERE UserID='" +userID+"'";
		try {
			kn.ExecuteNonQuery(sql);
			 Alert a = new Alert(AlertType.INFORMATION,"Uppdate user successfully!");
		        a.show();
		}
		catch(Exception e) {
		}
	}
	public void deleteUser(int userID) {
		String sql = "DELETE Users WHERE UserID = '" + userID + "'";
		try {
			kn.ExecuteNonQuery(sql);
			Alert a = new Alert(AlertType.INFORMATION,"Delete user successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
	}
	
	public ObservableList<User> Search(String condi) {
		ObservableList<User> list = FXCollections.observableArrayList();
		ResultSet rs = null;
        String sql = "SELECT * FROM Users WHERE UserID like '%" + condi + "%' OR UserName like '%" + condi + "%'";
        try {
			rs = kn.getTable(sql);
			LocalDate date = null;
            while (rs.next()){
            	date = rs.getDate("UserDOB").toLocalDate();
            	list.add(new User(rs.getInt("UserID"), rs.getString("UserName"), date, rs.getString("UserPhone"), rs.getString("UserEmail"), rs.getString("UserAddress")));               
            }
        }
        catch(Exception e) {  
        	Alert a = new Alert(AlertType.INFORMATION,"Database Error: "+e.getMessage());
        	a.show();
        	System.out.print(e.getMessage());
        }
        return list;
    }
	
	 public ObservableList<User> getDataList(){
	        ObservableList<User> list = FXCollections.observableArrayList();
	        ResultSet rs = null;
	        try {
	            rs = getUser();
	            LocalDate date = null;
	            while (rs.next()){   
	            	date = rs.getDate("UserDOB").toLocalDate();
	                list.add(new User(rs.getInt("UserID"), rs.getString("UserName"), date, rs.getString("UserPhone"), rs.getString("UserEmail"), rs.getString("UserAddress")));               
	            }
	        } 
	        catch (Exception e) {
	        	Alert a = new Alert(AlertType.INFORMATION,"Database Error: "+e.getMessage());
		        a.show();
	        }
	        return list;
	   }
	 public User getUserByID(int UserID){
		 	ResultSet rs = null;
		 	User user = null;
	        try {
	            rs = getUser();
	            LocalDate date = null;
	            while (rs.next()){   
	            	date = rs.getDate("UserDOB").toLocalDate();
	            	if(rs.getInt("UserID")==UserID) {
	            		user  = new User(rs.getInt("UserID"), rs.getString("UserName"), date, rs.getString("UserPhone"), rs.getString("UserEmail"), rs.getString("UserAddress"));           
	            	}
	            }
	        } 
	        catch (Exception e) {
	        	Alert a = new Alert(AlertType.INFORMATION,"Database Error: "+e.getMessage());
		        a.show();
	        }
	        return user;
	   }
}

