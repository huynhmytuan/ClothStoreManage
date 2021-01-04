package Model;

public class Login {
	private int loginID;
	private String userName;
	private String passWord;
	private int loginRoleID;
	private int userID;

	public int getLoginID() {
		return loginID;
	}
	public void setLoginID(int LoginID) {
		this.loginID = LoginID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String UserName) {
		this.userName = UserName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String PassWord) {
		this.passWord = PassWord;
	}
	public int getLoginRoleID() {
		return loginRoleID;
	}
	public void setLoginRoleID(int LoginRoleID) {
		this.loginRoleID = LoginRoleID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int UserID) {
		this.userID = UserID;
	}
	
	public void setLogin(int LoginID,String UserName,String PassWord,int LoginRoleID,int UserID ) {
		this.loginID = LoginID;
		this.userName = UserName;
		this.passWord = PassWord;
		this.loginRoleID = LoginRoleID;
		this.userID = UserID;
	}
}
