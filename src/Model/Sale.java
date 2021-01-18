package Model;

import java.util.Date;

public class Sale {
	private int saleID;
	private Date dateSale;
	private int cusID;
	private int staffID;
	private int productID;
	private int numOfProduct;
	private float price;
	private float totalPrice;
	
	public int getSaleID() {
		return saleID;
	}
	public void setSaleID(int SaleID) {
		this.saleID = SaleID;
	}
	public Date getDateSale() {
		return dateSale;
	}
	public void setDateSale(Date DateSale) {
		this.dateSale = DateSale;
	}
	public int getCusID() {
		return cusID;
	}
	public void setCusID(int CusID) {
		this.cusID = CusID;
	}
	public int getStaffID() {
		return staffID;
	}
	public void setStaffID(int StaffID) {
		this.staffID = StaffID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int ProductID) {
		this.productID = ProductID;
	}
	public int getNumOfProduct() {
		return numOfProduct;
	}
	public void setNumOfProduct(int NumOfProduct) {
		this.numOfProduct = NumOfProduct;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float Price) {
		this.price = Price;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float TotalPrice) {
		this.totalPrice = TotalPrice;
	}
	public Sale(int SaleID,Date DateSale,int CusID,int StaffID,int ProductID,int NumOfProduct,float Price,float TotalPrice) {
		this.saleID = SaleID;
		this.dateSale = DateSale;
		this.cusID = CusID;
		this.staffID = StaffID;
		this.productID = ProductID;
		this.numOfProduct = NumOfProduct;
		this.price = Price;
		this.totalPrice = TotalPrice;
	}
}
