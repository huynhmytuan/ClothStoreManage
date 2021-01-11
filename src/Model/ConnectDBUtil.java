package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ConnectDBUtil {
	Connection cnn;
	
    public Connection getConnect() throws SQLException{
        try{
            String uRl ="jdbc:sqlserver://Localhost:1433;databaseName=ClothingStore"; 
            String user = "sa";
            String pass = "sa";
            cnn = DriverManager.getConnection(uRl,user,pass);
        }
        catch(Exception e){
        }
        return cnn;       
    }
    public ResultSet getTable(String sql) {
    	ResultSet rs = null;
    	try {
    		Connection cn = getConnect();
	        Statement stm = cn.createStatement();
	        rs = stm.executeQuery(sql);
    	}
    	catch(SQLException e) {
    		Alert a = new Alert(AlertType.ERROR, "Sql Error");
    		a.show();
    	}
    	return rs;
    }
    
    public void ExecuteNonQuery(String sql) {
    	try {
    		Connection cn = getConnect();
	        Statement stm = cn.createStatement();
	        stm.executeUpdate(sql);
	        cn.close();
	        stm.close();
    	}
    	catch(SQLException e) {
    		Alert a = new Alert(AlertType.ERROR, "Sql Error");
    		a.show();
    	}
    }
}
