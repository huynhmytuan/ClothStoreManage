package Model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SaleUtil {
	ConnectDBUtil kn = new ConnectDBUtil();
	
	public ResultSet getSale() {
		ResultSet rs = null;
		String sql = "SELECT * FROM Sales";
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
	public void insertSale(int saleID,LocalDate dateSale,int cusID,int staffID,int productID,int numOfProduct,float price,float totalPrice) {
		String sql = "";
		if(cusID!=-1) {
			sql = " INSERT INTO Sales VALUES('" + saleID + "','" + dateSale + "','" + cusID + "','" + staffID + "','"+ productID + "','"+ numOfProduct + "','"+ price + "','"+ totalPrice + "')";
		}
		else {
			sql = " INSERT INTO Sales VALUES('" + saleID + "','" + dateSale + "', NULL,'" + staffID + "','"+ productID + "','"+ numOfProduct + "','"+ price + "','"+ totalPrice + "')";
		}
		try {
			kn.ExecuteNonQuery(sql);
		}
		catch(Exception e) {
		}
	}
	public void updatetSale(int saleID,Date dateSale,int cusID,int staffID,int productID,int numOfProduct,float price,float totalPrice) {
		String sql = " UPDATE Sales SET SaleID='" + saleID + "', DateSale='" + dateSale + "', CusID='" + cusID + "', StaffID='" + staffID + "', productID='"+ productID + "', NumOfProduct='"+ numOfProduct +"', Price='"+ price +"', TotalPrice='"+ totalPrice + "' WHERE SaleID='" +saleID+"'";
		try {
			kn.ExecuteNonQuery(sql);
		}
		catch(Exception e) {
		}
	}
	public void deleteSale(int saleID) {
		String sql = "DELETE Sales WHERE SaleID = '" + saleID + "'";
		try {
			kn.ExecuteNonQuery(sql);
		}
		catch(Exception e) {
		}
	}
	public ObservableList<Sale> searchBillByDate(LocalDate dateS){
        ObservableList<Sale> list = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            String sql = "SELECT * from Sales WHERE DateSale ='"+dateS+"'";
            rs = kn.getTable(sql);
            LocalDate date = null;
            while(rs.next()){   
            	date = rs.getDate("DateSale").toLocalDate();
                list.add(new Sale(rs.getInt("SaleID"), date, rs.getInt("CusID"), rs.getInt("StaffID"), rs.getInt("ProductID"), rs.getInt("NumOfProduct"), rs.getFloat("Price"), rs.getFloat("TotalPrice")));               
            }
        } 
        catch (Exception e) {
        	Alert a = new Alert(AlertType.INFORMATION,"Database Error: "+e.getMessage());
	        a.show();
        }
        return list;
    }
	public ObservableList<Sale> searchBillByMonth(Month Month){
        ObservableList<Sale> list = FXCollections.observableArrayList();
        ResultSet rs;
        try {
        	
            String sql = "SELECT * from Sales  WHERE  MONTH(DateSale) = '"+Month.toString()+"'";
            rs = kn.getTable(sql);
            LocalDate date = null;
            while(rs.next()){   
            	date = rs.getDate("DateSale").toLocalDate();
                list.add(new Sale(rs.getInt("SaleID"), date, rs.getInt("CusID"), rs.getInt("StaffID"), rs.getInt("ProductID"), rs.getInt("NumOfProduct"), rs.getFloat("Price"), rs.getFloat("TotalPrice")));               
            }
        } 
        catch (Exception e) {
        	Alert a = new Alert(AlertType.INFORMATION,"Database Error: "+e.getMessage());
	        a.show();
        }
        return list;
    }
	//Get data Tableview_Product
    public ObservableList<Sale> getDataList(){
        ObservableList<Sale> list = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            String sql = "SELECT * from Sales";
            rs = kn.getTable(sql);
            LocalDate date = null;
            while(rs.next()){   
            	date = rs.getDate("DateSale").toLocalDate();
                list.add(new Sale(rs.getInt("SaleID"), date, rs.getInt("CusID"), rs.getInt("StaffID"), rs.getInt("ProductID"), rs.getInt("NumOfProduct"), rs.getFloat("Price"), rs.getFloat("TotalPrice")));               
            }
        } 
        catch (Exception e) {
        	Alert a = new Alert(AlertType.INFORMATION,"Database Error: "+e.getMessage());
	        a.show();
        }
        return list;
    }
    
    public ObservableList<Sale> getBillBySaleID(int SaleID){
        ObservableList<Sale> list = FXCollections.observableArrayList();
        ResultSet rs;
        try {
            String sql = "SELECT * from Sales WHERE SaleID ='"+SaleID+"'";
            rs = kn.getTable(sql);
            LocalDate date = null;
            while(rs.next()){   
            	date = rs.getDate("DateSale").toLocalDate();
                list.add(new Sale(rs.getInt("SaleID"), date, rs.getInt("CusID"), rs.getInt("StaffID"), rs.getInt("ProductID"), rs.getInt("NumOfProduct"), rs.getFloat("Price"), rs.getFloat("TotalPrice")));               
            }
        } 
        catch (Exception e) {
        	Alert a = new Alert(AlertType.INFORMATION,"Database Error: "+e.getMessage());
	        a.show();
        }
        return list;
    }
}
