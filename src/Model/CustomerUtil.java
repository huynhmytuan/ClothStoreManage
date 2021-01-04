package Model;

import java.sql.ResultSet;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CustomerUtil {
	ConnectDBUtil kn = new ConnectDBUtil();
	
	public ResultSet getCustomer() {
		
		ResultSet rs = null;
		String sql = "SELECT * FROM Customer";
		try {
			kn.ExecuteNonQuery(sql); 
	        rs = kn.getTable(sql);
	        Alert a = new Alert(AlertType.INFORMATION,"Get customers successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
		return rs;
	}
	public void insertCustomer(int cusID,String cusName,Date cusDOB,String cusPhone,String cusEmail,String cusAddress) {
		String sql = " INSERT INTO Customer VALUES('" + cusID + "','" + cusName + "','" + cusDOB + "','" + cusPhone + "','"+ cusEmail + "','"+ cusAddress + "')";
		try {
			kn.ExecuteNonQuery(sql);
			Alert a = new Alert(AlertType.INFORMATION,"Insert customer successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
	}
	public void updatetCustomer(int cusID,String cusName,Date cusDOB,String cusPhone,String cusEmail,String cusAddress) {
		String sql = " UPDATE Customer SET CusID='" + cusID + "', CusName='" + cusName + "', CusDOB='" + cusDOB + "', CusPhone='" + cusPhone + "', CusEmail='"+ cusEmail + "', CusAddress='"+ cusAddress + "' WHERE CusID='" +cusID+"'";
		try {
			kn.ExecuteNonQuery(sql);
			 Alert a = new Alert(AlertType.INFORMATION,"Uppdate customer successfully!");
		        a.show();
		}
		catch(Exception e) {
		}
	}
	public void deleteCustomer(int cusID) {
		String sql = "DELETE Customer WHERE CusID = '" + cusID + "'";
		try {
			kn.ExecuteNonQuery(sql);
			Alert a = new Alert(AlertType.INFORMATION,"Delete customer successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
	}
	public ResultSet Search(String condi)
    {
		ResultSet rs = null;
        String sql = "SELECT * FROM Customer WHERE CusID like '%" + condi + "%' OR CusName like '%" + condi + "%'";
        try {
        	kn.ExecuteNonQuery(sql);
			rs = kn.getTable(sql);
        }
        catch(Exception e) {       	
        }
        return rs;
    }
}
