package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Excpt.PasswordException;
import Excpt.PasswordValidator;
import Excpt.PhoneException;
import Excpt.PhoneValidator;
import Model.Customer;
import Model.Login;
import Model.LoginUtil;
import Model.User;
import Model.UserUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RegisterController implements Initializable{
		LoginUtil lu = new LoginUtil();
		UserUtil uu = new UserUtil();
		int loginID = -1;
    	int userID = -1;
		@FXML
	    private AnchorPane rootpane;

	    @FXML
	    private JFXTextField tbUsername;

	    @FXML
	    private JFXPasswordField tbPassword;

	    @FXML
	    private JFXPasswordField tbConfirmPassword;

	    @FXML
	    private JFXTextField tbFullname;

	    @FXML
	    private DatePicker tbDate;

	    @FXML
	    private JFXTextField tbPhone;

	    @FXML
	    private JFXTextArea tbAddress;

	    @FXML
	    private JFXTextField tbEmail;

	    @FXML
	    private JFXButton btnRegister;

	    @FXML
	    private JFXButton btnLoginNow;

	    @FXML
	    private ImageView btnClosed_RegisUI;
 
	    @FXML
	    public void btnRegister_Clicked(MouseEvent event){
	    	//Get all data which user insert.
	    	String userName = "";
	    	userName = tbUsername.getText();
	    	String fullname = "";
	    	fullname = tbFullname.getText();
	    	String address = "";
	    	address	= tbAddress.getText();
	    	LocalDate userDOB = null;
	    	userDOB = tbDate.getValue();
	    	String userPhone = "";
	    	userPhone = tbPhone.getText();
	    	String userEmail = "";
	    	userEmail = tbEmail.getText();
	    	String pass = "";
	    	pass = String.valueOf(tbPassword.getText());
	    	String pass1 = "";
	    	pass1 = String.valueOf(tbConfirmPassword.getText());
	    	//Create 2 list to store all login and user in database
	    	ObservableList<Login> listM = lu.getDataList();
	    	ObservableList<User> listN = uu.getDataList();
	    	//Check if user don't insert all informations
	    	if( userName.equals("") || 
	    		fullname.equals("") || 
	    		address.equals ("") ||
	    		userPhone.equals("")|| 
	    		userEmail.equals("")||
	    		pass.equals    ("")|| 
	    		userDOB == null ||
	    		pass1.equals   ("")){					
	    		Alert a = new Alert(AlertType.ERROR,"Your information can't be empty!");
		        a.show();
	    	}
	    	else{
	    		boolean usernameCheck = true;//Check variable for check username exited or not
	    		for(Login one : listM) {
	    			String TempUsername = one.getUserName();
	    			if(userName.equals(TempUsername)){
	    				Alert a = new Alert(AlertType.ERROR,"Username has already! Please choose another username!");
	    		        a.show();
	    		        usernameCheck = false;
	    		        break;
	    			}
	    		}
	    		if(usernameCheck) {
	    			//Get phone number and check it
	    			int number = -1;
					number = Integer.parseInt(userPhone);
					if(number == -1) {
						Alert a = new Alert(AlertType.ERROR,"Phone number must be numbers!");
						a.show();
					}
					else {
						//check match re-entered password
						if(!pass.equals(pass1))
	    				{
	    					Alert a = new Alert(AlertType.ERROR,"Password does not match! Please re-enter");
		    		        a.show();
	    				}
	    				else
	    				{
	    					try {
	    						PasswordValidator.isValid(pass1);
	    						PhoneValidator.isValid(userPhone);
	    						Random rand = new Random();
		    		    		loginID = rand.nextInt(10000);//Random a new loginID
		    		    		userID = rand.nextInt(10000);//Random a new User ID
		    		    		int[] loginIDArr = new int[listM.size()]; //Create a list to store loginID in database
		    		    		int[] userIDArr = new int[listN.size()];//Create a list to store userID in database
		    		    		int n=0;
		    		    		for(Login lid : listM) {
		    		    			loginIDArr[n] = lid.getLoginID();
		    		    			n++;
		    		    		}
		    		    		 n=0;
		    		    		 for(User uid : listN) {
		    		    			 userIDArr[n] = uid.getUserID();
		    		    			 n++;
		    		    		 }
		    		    		 boolean check = true;
		    		    		 do{
		    		    			 check  = IntStream.of(loginIDArr).anyMatch(x -> x == loginID);
		    		    			 if(check){
		    		    				 loginID = rand.nextInt(10000);
		    		    			 }
		    		    			 else {
		    		    				 break;
		    		    			 }
		    		    		 }while(check);
		    		    		 do {
		    		    			 check  = IntStream.of(loginIDArr).anyMatch(x -> x == userID);
		    		    			 if(check){
		    		    				 userID = rand.nextInt(10000);
		    		    			 }
		    		    			 else {
		    		    				 break;
		    		    			 }
		    		    		 }while(check);
	    		    			 uu.insertUser(userID, fullname, userDOB, userPhone, userEmail, address);
	    		    			 lu.insertLogin(loginID, userName, pass, 2, userID);	
	    		    			 Alert a = new Alert(AlertType.INFORMATION,"Register successfully! Login Now!");
	    		        	     a.show();
	    					}
	    					catch(PasswordException e) {
	    						Alert a = new Alert(AlertType.ERROR,e.printMessage());
	    		        	    a.show();
	    					}
	    		    		catch(PhoneException e) {
	    		    			Alert a = new Alert(AlertType.ERROR,e.printMessage());
	    		        	    a.show();
	    		    		}
	    				}
					}
				}
	    	}
	    }	
	    @FXML
	    void btnClosed_Clicked(MouseEvent event) {
	    	System.exit(0);
	    }
    
	    @FXML
	    void btnLoginNow(MouseEvent event) throws IOException {
	    	LoadScene loader = new LoadScene();
	    	String frmName = "LoginUI.fxml";
	    	loader.loadSence(frmName, event);
	    }
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	    	// TODO Auto-generated method stub
		
	    }
}