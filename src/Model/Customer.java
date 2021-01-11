package Model;


import java.time.LocalDate;
public class Customer {
	private int cusID;
	private String cusName;
	private LocalDate cusDOB;
	private String cusPhone;
	private String cusEmail;
	private String cusAddress;
	

	public int getCusID() {
		return cusID;
	}
	public void setCusID(int CusID) {
		this.cusID = CusID;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String CusName) {
		this.cusName = CusName;
	}
	public LocalDate getCusDOB() {
		return cusDOB;
	}
	public void setCusDOB(LocalDate CusDOB) {
		this.cusDOB = CusDOB;
	}
	public String getCusPhone() {
		return cusPhone;
	}
	public void setCusPhone(String CusPhone) {
		this.cusPhone = CusPhone;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String CusEmail) {
		this.cusEmail = CusEmail;
	}
	public String getCusAddress() {
		return cusAddress;
	}
	public void setCusAddress(String CusAddress) {
		this.cusAddress = CusAddress;
	}
	public Customer(int CusID,String CusName, LocalDate CusDOB,String CusPhone,String CusEmail,String CusAddress) {
		this.cusID = CusID;
		this.cusName = CusName;
		this.cusDOB = CusDOB;
		this.cusPhone = CusPhone;
		this.cusEmail = CusEmail;
		this.cusAddress = CusAddress;
	}
}
