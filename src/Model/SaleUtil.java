package Model;

import java.sql.ResultSet;
import java.util.Date;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SaleUtil {
	ConnectDBUtil kn = new ConnectDBUtil();
	
	public ResultSet getSale() {
		ResultSet rs = null;
		String sql = "SELECT * FROM Sale";
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
	public void insertSale(int saleID,Date dateSale,int cusID,int staffID,int productID,int numOfProduct,float price,float totalPrice) {
		String sql = " INSERT INTO Sale VALUES('" + saleID + "','" + dateSale + "','" + cusID + "','" + staffID + "','"+ productID + "','"+ numOfProduct + "','"+ price + "','"+ totalPrice + "')";
		try {
			kn.ExecuteNonQuery(sql);
			Alert a = new Alert(AlertType.INFORMATION,"Insert customer successfully!");
	        a.show();
		}
		catch(Exception e) {
		}
	}
	public void updatetSale(int saleID,Date dateSale,int cusID,int staffID,int productID,int numOfProduct,float price,float totalPrice) {
		String sql = " UPDATE Sale SET SaleID='" + saleID + "', DateSale='" + dateSale + "', CusID='" + cusID + "', StaffID='" + staffID + "', productID='"+ productID + "', NumOfProduct='"+ numOfProduct +"', Price='"+ price +"', TotalPrice='"+ totalPrice + "' WHERE SaleID='" +saleID+"'";
		try {
			kn.ExecuteNonQuery(sql);
			 Alert a = new Alert(AlertType.INFORMATION,"Uppdate successfully!");
		        a.show();
		}
		catch(Exception e) {
		}
	}
	public void deleteSale(int saleID) {
		String sql = "DELETE Sale WHERE SaleID = '" + saleID + "'";
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
        String sql = "SELECT * FROM Sale WHERE SaleID like '%"+ condi + "%'";
        try {
        	kn.ExecuteNonQuery(sql);
			rs = kn.getTable(sql);
        }
        catch(Exception e) {       	
        }
        return rs;
    }
}
