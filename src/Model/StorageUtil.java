package Model;

import java.sql.ResultSet;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class StorageUtil {
ConnectDBUtil kn = new ConnectDBUtil();
	
	public ResultSet getStorage() {
		ResultSet rs = null;
		String sql = "SELECT * FROM Storage";
		try { 
			kn.ExecuteNonQuery(sql);
			rs = kn.getTable(sql);
	        Alert a = new Alert(AlertType.INFORMATION,"Get successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
		return rs;
	}
	public void insertStorage(int storID,int productID,int numOfProductIn,int quantityInStock,Date dateIn,float totalPrice) {
		String sql = " INSERT INTO Storage VALUES('" + storID + "','" + productID + "','" + numOfProductIn + "','" + quantityInStock + "','"+ dateIn + "','" + totalPrice +"')";
		try {
			kn.ExecuteNonQuery(sql);
			Alert a = new Alert(AlertType.INFORMATION,"Insert successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
	}
	public void updateStorage(int storID,int productID,int numOfProductIn,int quantityInStock,Date dateIn,float totalPrice) {
		String sql = " UPDATE Storage SET StorID='" + storID + "', ProductID='" + productID + "', NumOfProductIn='" + numOfProductIn + "', QuantityInStock='" + quantityInStock + "', DateIn='"+ dateIn +"', TotalPrice='"+ totalPrice + "'WHERE StorID='" +storID+"'";
		try {			
			kn.ExecuteNonQuery(sql);
			Alert a = new Alert(AlertType.INFORMATION,"Update successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
	}
	public void deleteStorage(int storID) {
		String sql = "DELETE Storage WHERE StorID='" + storID + "'";
		try {
			kn.ExecuteNonQuery(sql);
			Alert a = new Alert(AlertType.INFORMATION,"Delete successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
	}
	public ResultSet Search(String condi)
    {
		ResultSet rs = null;
        String sql = "SELECT * FROM Storage WHERE StorID like '%" + condi + "%'";
        try {
        	kn.ExecuteNonQuery(sql);
			rs = kn.getTable(sql);
        }
        catch(Exception e) {       	
        }
        return rs;
    }
	
	public Storage getAvailableByProdID(int ProdID){
	 	Storage sto = new Storage();
        ResultSet rs = null;
        try {
            String sql = "SELECT NumOfProductIn ,QuantityInStock FROM Storage WHERE ProductID='"+ProdID+"'";
            rs = kn.getTable(sql);
            while (rs.next()){   
            	sto.setQuantityInStock(rs.getInt("QuantityInStock"));
            }
        }
        catch (Exception e) {
        }
        return sto;
	}
}
