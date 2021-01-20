package Model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
		}
		catch(Exception e) {
		}
		return rs;
	}
	public void insertStorage(int storID,int productID,int numOfProductIn,int quantityInStock,LocalDate dateIn,float totalPrice) {
		String sql = " INSERT INTO Storage VALUES('" + storID + "','" + productID + "','" + numOfProductIn + "','" + quantityInStock + "','"+ dateIn + "','" + totalPrice +"')";
		try {
			kn.ExecuteNonQuery(sql);
		}
		catch(Exception e) {
		}
	}
	public void updateStorage(int productID, int numOfProductIn, int quantityInStock, float totalPrice) {
		String sql = " UPDATE Storage SET NumOfProductIn='" + numOfProductIn + "', QuantityInStock='" + quantityInStock + "', TotalPrice='"+ totalPrice + "'WHERE ProductID='" +productID+"'";
		try {			
			kn.ExecuteNonQuery(sql);
		}
		catch(Exception e) {
		}
	}
	public void deleteStorage(int prodID) {
		String sql = "DELETE Storage WHERE ProductID='" + prodID + "'";
		try {
			kn.ExecuteNonQuery(sql);
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
        ResultSet rs;
        try {
            String sql = "SELECT NumOfProductIn ,QuantityInStock FROM Storage WHERE ProductID='"+ProdID+"'";
            rs = kn.getTable(sql);
            while (rs.next()){   
            	sto.setNumOfProductIn(rs.getInt("NumOfProductIn"));
            	sto.setQuantityInStock(rs.getInt("QuantityInStock"));
            }
        }
        catch (Exception e) {
        }
        return sto;
	}
	
	public ObservableList<Storage> getDataList(){
        ObservableList<Storage> list = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            rs = getStorage();
            LocalDate date = null;
            while (rs.next()){   
            	date = rs.getDate("DateIn").toLocalDate();
                list.add(new Storage(rs.getInt("StorID"),rs.getInt("ProductId"),rs.getInt("NumOfProductIn"), rs.getInt("QuantityInStock"), date, rs.getFloat("TotalPrice")));               
            }
        } 
        catch (Exception e) {
        	Alert a = new Alert(AlertType.INFORMATION,"Database Error: "+e.getMessage());
	        a.show();
        }
        return list;
   }
}
