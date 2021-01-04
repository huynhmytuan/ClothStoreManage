package Model;

import java.util.Date;

public class Stogare {
	private int storID;
	private int productID;
	private int numOfProductIn;
	private int quantityInStock;
	private Date dateIn;
	private float totalPrice;
	public int getStorID() {
		return storID;
	}
	public void setStorID(int StorID) {
		this.storID = StorID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int ProductID) {
		this.productID = ProductID;
	}
	public int getNumOfProductIn() {
		return numOfProductIn;
	}
	public void setNumOfProductIn(int NumOfProductIn) {
		this.numOfProductIn = NumOfProductIn;
	}
	public int getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(int QuantityInStock) {
		this.quantityInStock = QuantityInStock;
	}
	public Date getDateIn() {
		return dateIn;
	}
	public void setDateIn(Date DateIn) {
		this.dateIn = DateIn;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float TotalPrice) {
		this.totalPrice = TotalPrice;
	}
	public void setStogare(int StorID,int ProductID,int NumOfProductIn,int QuantityInStock,Date DateIn,float TotalPrice) {
		this.storID = StorID;
		this.productID = ProductID;
		this.numOfProductIn = NumOfProductIn;
		this.quantityInStock = QuantityInStock;
		this.dateIn = DateIn;
		this.totalPrice = TotalPrice;
	}
	
}
