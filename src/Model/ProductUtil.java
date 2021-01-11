package Model;

import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ProductUtil {
	ConnectDBUtil kn = new ConnectDBUtil();
	
	public ResultSet getProduct() {
		ResultSet rs = null;
		String sql = "SELECT * FROM Product";
		try { 
			kn.ExecuteNonQuery(sql);
			rs = kn.getTable(sql);
	        Alert a = new Alert(AlertType.INFORMATION,"Get product successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
		return rs;
	}
	public void insertProduct(int productID,String productName,String productType,String productSize,String productDecs,float productInPrice,float productOutPrice,String productPicture) {
		String sql = " INSERT INTO Product VALUES('" + productID + "','" + productName + "','" + productType + "','" + productSize + "','"+ productDecs + "','"+ productInPrice + "','"+ productOutPrice + "','"+ productPicture + "')";
		try {
			kn.ExecuteNonQuery(sql);
			 Alert a = new Alert(AlertType.INFORMATION,"Insert product successfully!");
		        a.show();
		}
		catch(Exception e) {
		}
	}
	public void updatetProduct(int productID,String productName,String productType,String productSize,String productDecs,float productInPrice,float productOutPrice,String productPicture) {
		String sql = " UPDATE Product SET ProductID='" + productID + "', ProductName='" + productName + "', ProductType='" + productType + "', ProductSize='" + productSize + "', ProductDecs='"+ productDecs + "', ProductInPrice='"+ productInPrice + "', ProductOutPrice='"+ productOutPrice + "', ProductPicture='"+ productPicture +"' WHERE ProductID='" +productID+"'";
		try {
			kn.ExecuteNonQuery(sql);
			 Alert a = new Alert(AlertType.INFORMATION,"Uppdate product successfully!");
		        a.show();
		}
		catch(Exception e) {
		}
	}
	
	public void deleteProduct(int productID) {
		String sql = "DELETE Product WHERE ProductID = '" + productID + "'";
		try {
			kn.ExecuteNonQuery(sql);
			Alert a = new Alert(AlertType.INFORMATION,"Delete product successfully!");
	        a.show();
		}
		catch(Exception e) {
		}	
	}
	public ResultSet Search(String condi) {
		ResultSet rs = null;
        String sql = "SELECT * FROM Product WHERE ProductID like '%" + condi + "%' OR ProductName like '%" + condi + "%'";
        try {
        	kn.ExecuteNonQuery(sql);
			rs = kn.getTable(sql);
        }
        catch(Exception e) {       	
        }
        return rs;
    }
	
	//Get data Tableview_Product
    public ObservableList<Product> getDataList(){
        ObservableList<Product> list = FXCollections.observableArrayList();
        ResultSet rs = null;
        try {
            String sql = "SELECT * from Product";
            rs = kn.getTable(sql);
            while (rs.next()){   
                list.add(new Product(Integer.parseInt(rs.getString("ProductID")), rs.getString("ProductName"), rs.getString("ProductType"), rs.getString("ProductSize"), rs.getString("ProductDecs"), rs.getFloat("ProductInPrice"), rs.getFloat("ProductOutPrice") ,rs.getString("ProductPicture")));               
            }
        } 
        catch (Exception e) {
        	Alert a = new Alert(AlertType.INFORMATION,"Database Error: "+e.getMessage());
	        a.show();
        }
        return list;
    }
}
