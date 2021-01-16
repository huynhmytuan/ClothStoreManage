package Model;

import java.time.LocalDate;

public class User {
	private int userID;
	private String userName;
	private LocalDate userDOB;
	private String userPhone;
	private String userEmail;
	private String userAddress;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int UserID) {
		this.userID = UserID;
	}		
	public String getUserName() {
		return userName;
	}
	public void setUserName(String UserName) {
		this.userName = UserName;
	}
	public LocalDate getUserDOB() {
		return userDOB;
	}
	public void setUserDOB(LocalDate UserDOB) {
		this.userDOB = UserDOB;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String UserPhone) {
		this.userPhone = UserPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String UserEmail) {
		this.userEmail = UserEmail;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String UserAddress) {
		this.userAddress = UserAddress;
	}
	
	public User(int UserID,String UserName,LocalDate UserDOB,String UserPhone,String UserEmail,String UserAddress) {
		this.userID = UserID;
		this.userName = UserName;
		this.userDOB = UserDOB;
		this.userPhone = UserPhone;
		this.userEmail = UserEmail;
		this.userAddress = UserAddress;
	}
}
