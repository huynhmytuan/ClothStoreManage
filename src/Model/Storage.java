package Model;

import java.time.LocalDate;

public class Storage {
	private int storID;
	private int productID;
	private int numOfProductIn;
	private int quantityInStock;
	private LocalDate dateIn;
	private float totalPrice;
	
	public Storage(int StorID,int ProductID,int NumOfProductIn,int QuantityInStock,LocalDate DateIn,float TotalPrice) {
		this.storID = StorID;
		this.productID = ProductID;
		this.numOfProductIn = NumOfProductIn;
		this.quantityInStock = QuantityInStock;
		this.dateIn = DateIn;
		this.totalPrice = TotalPrice;
	}
	public Storage() {
		this.storID = 0;
		this.productID = 0;
		this.numOfProductIn = 0;
		this.quantityInStock = 0;
		this.dateIn = null;
		this.totalPrice = 0;
	}
	
	
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
	public LocalDate getDateIn() {
		return dateIn;
	}
	public void setDateIn(LocalDate DateIn) {
		this.dateIn = DateIn;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float TotalPrice) {
		this.totalPrice = TotalPrice;
	}
	
}
