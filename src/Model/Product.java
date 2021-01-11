package Model;


public class Product {
	private int productID;
	private String productName;
	private String productType;
	private String productSize;
	private String productDecs;
	private float productInPrice;
	private float productOutPrice;
	private String productPicture;
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int ProductID) {
		this.productID = ProductID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String ProductName) {
		this.productName = ProductName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String ProductType) {
		this.productType = ProductType;
	}
	public String getProductSize() {
		return productSize;
	}
	public void setProductSize(String ProductSize) {
		this.productSize = ProductSize;
	}
	public String getProductDecs() {
		return productDecs;
	}
	public void setProductDecs(String ProductDecs) {
		this.productDecs = ProductDecs;
	}
	public float getProductInPrice() {
		return productInPrice;
	}
	public void setProductInPrice(float ProductInPrice) {
		this.productInPrice = ProductInPrice;
	}
	public float getProductOutPrice() {
		return productOutPrice;
	}
	public void setProductOutPrice(float ProductOutPrice) {
		this.productOutPrice = ProductOutPrice;
	}
	public String getProductPicture() {
		return productPicture;
	}
	public void setProductPicture(String ProductPicture) {
		this.productPicture = ProductPicture;
	}
	public Product(int ProductID,String ProductName,String ProductType,String ProductSize,String ProductDecs,float ProductInPrice,float ProductOutPrice,String ProductPicture) {
		this.productID = ProductID;
		this.productName = ProductName;
		this.productType = ProductType;
		this.productSize = ProductSize;
		this.productDecs = ProductDecs;
		this.productInPrice = ProductInPrice;
		this.productOutPrice = ProductOutPrice;
		this.productPicture = ProductPicture;
	}
}
