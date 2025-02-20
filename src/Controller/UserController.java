package Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Excpt.FullnameException;
import Excpt.FullnameValidator;
import Excpt.PasswordException;
import Excpt.PhoneException;
import Excpt.PhoneValidator;
import Model.*;
import Task.SoundTrack;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class UserController implements Initializable {
	UserUtil us = new UserUtil();
	ObservableList<User> listM;
	LoginUtil lu = new LoginUtil();
	RoleUtil ru = new RoleUtil();
    int index = -1;
    ResultSet rs = null;

    @FXML
    private AnchorPane customerUI;

    @FXML
    private TableView<User> tableUser;

    @FXML
    private TableColumn<User, Integer> colID;

    @FXML
    private TableColumn<User, String> colName;

    @FXML
    private TableColumn<User, Date> colDOB;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnRefresh;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhone;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private DatePicker txtDate;

    @FXML
    private JFXTextArea txtAddress;

    @FXML
    private JFXTextField txtUserName;
    
    @FXML
    private JFXTextField txtRole;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private ImageView btnSearch;
    
    @FXML
    private TableView<Role> tableRole;

    @FXML
    private TableColumn<Role, Integer> colRoleID;

    @FXML
    private TableColumn<Role, String> colRoleName;
    
    @FXML
    void Row_Clicked(MouseEvent event) {
    	User us = tableUser.getSelectionModel().getSelectedItem();
    	txtID.setText(""+ us.getUserID());
    	txtName.setText(us.getUserName());
    	txtDate.setValue(us.getUserDOB());
    	txtPhone.setText(us.getUserPhone());
    	txtEmail.setText("" + us.getUserEmail());
    	txtAddress.setText("" + us.getUserAddress());
    	
    	ObservableList<Login> listLogin  = lu.getListByID(us.getUserID());
    	Login user = listLogin.get(0);
    	String userName = user.getUserName();
    	txtUserName.setText(userName);
    	int loginRoleID = user.getLoginRoleID();
    	txtRole.setText(Integer.toString(loginRoleID));
    }
    public int getRandomLoginID() {
    	ObservableList<Login> inputList = lu.getDataList();
    	int numID;
    	Random rand = new Random();
		numID = rand.nextInt(10000);//Random a new numID
		int[] loginIDArr = new int[listM.size()]; //Create a list to store ID in database
		int n=0;
		for(Login lid : inputList) {
			loginIDArr[n] = lid.getLoginID();
			n++;
		}
		boolean check = false;
		 do{
			 //Check if storID already in storIDArr
			 for(int num :	loginIDArr) {
				 if(num == numID) {
					 check = true;
					 break;
				 }
			 }
			 if(check){
				 numID = rand.nextInt(10000);
			 }
			 else {
				 return numID;
			 }
		 }while(check);
		return numID;
    }
    public int getRandomUserID() {
    	ObservableList<User> inputList = us.getDataList();
    	int numID;
    	Random rand = new Random();
		numID = rand.nextInt(10000);//Random a new numID
		int[] userIDArr = new int[listM.size()]; //Create a list to store ID in database
		int n=0;
		for(User lid : inputList) {
			userIDArr[n] = lid.getUserID();
			n++;
		}
		boolean check = false;
		 do{
			 //Check if storID already in storIDArr
			 for(int num : userIDArr) {
				 if(num == numID) {
					 check = true;
					 break;
				 }
			 }
			 if(check){
				 numID = rand.nextInt(10000);
			 }
			 else {
				 return numID;
			 }
		 }while(check);
		return numID;
    }
    @FXML
    void btnAdd_Clicked(MouseEvent event) {
    	try {
	    	int userID = getRandomUserID();
	    	String userName = txtName.getText();
	    	FullnameValidator.isValid(userName);
	    	LocalDate userDOB = txtDate.getValue();
	    	String userPhone = txtPhone.getText();
	    	PhoneValidator.isValid(userPhone);
	    	String userEmail = txtEmail.getText();
	    	String userAddress = txtAddress.getText();
	    	us.insertUser(userID, userName, userDOB, userPhone, userEmail, userAddress);//Create new User	
	    	int loginID = getRandomLoginID();
	    	String username = txtUserName.getText();
	    	int roleID = Integer.parseInt(txtRole.getText());
	    	lu.insertLogin(loginID, username, "123456", roleID, userID);//Create new account with default password 123456	    	
	    	listM = us.getDataList();
	    	loadTable(listM);
	    	String path = "src\\Music\\success-sound.wav";
	    	SoundTrack successSound = new SoundTrack(path);
	    	successSound.start();
    	}
    	catch(PhoneException e){
    		String path = "src\\Music\\warning-sound.wav";
    		SoundTrack warnSound = new SoundTrack(path);
    		warnSound.start();
    		Alert a = new Alert(AlertType.WARNING, e.printMessage());
            a.show();
    	}
    	catch(FullnameException e) {
    		String path = "src\\Music\\warning-sound.wav";
    		SoundTrack warnSound = new SoundTrack(path);
    		warnSound.start();
    		Alert a = new Alert(AlertType.WARNING, e.printMessage());
            a.show();
    	}
    }

    @FXML
    void btnDelete_Clicked(MouseEvent event) {
    	try {
	    	int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?", "Delete", JOptionPane.YES_NO_OPTION);
	    	if(p==0) {
				int userID = Integer.parseInt(txtID.getText());
				us.deleteUser(userID);
				lu.deleteLogin(userID);//Thread
				String path = "src\\Music\\success-sound.wav";
				SoundTrack successSound = new SoundTrack(path);
				successSound.start();
	    	}
	    	btnRefresh_Clicked(event);
	    	}
    	catch (Exception e) {
			Alert a = new Alert(AlertType.ERROR, "Error: "+"\n"+e.getMessage());
        	String path = "src\\Music\\error-noti-sound.wav";
    	 	SoundTrack error = new SoundTrack(path);
            error.start();
            a.show();
		}
    }

    @FXML
    void btnRefresh_Clicked(MouseEvent event) {
    	listM = us.getDataList();
		loadTable(listM);
		txtID.setText("");
		txtName.setText("");
		txtDate.setValue(null);
		txtPhone.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
		txtRole.setText("");
		txtUserName.setText("");
    }

    @FXML
    void btnSearch_Clicked(MouseEvent event) {
    	String search = txtSearch.getText();
    	if("".equals(search)){
            Alert a = new Alert(AlertType.WARNING, "Please enter your search keyword!");
            a.show();
        }
    	else {
    	 	listM = us.Search(search);
    	 	loadTable(listM);
    	}
    }

    @FXML
    void btnUpdate_Clicked(MouseEvent event) {
    	
    	try {
	    	int userID = Integer.parseInt(txtID.getText());
	    	String userName = txtName.getText();
	    	FullnameValidator.isValid(userName);
	    	LocalDate userDOB = txtDate.getValue();
	    	String userPhone = "";
	    	userPhone = txtPhone.getText();
	    	PhoneValidator.isValid(userPhone);	  	
	    	String userEmail = txtEmail.getText();
	    	String userAddress = txtAddress.getText();
	    	us.updatetUser(userID, userName, userDOB, userPhone, userEmail, userAddress);//Update A current User  	
	    	String username = txtUserName.getText();
	    	int roleID = Integer.parseInt(txtRole.getText());
	    	lu.updateUsernameNRoleID(username, roleID, userID);//Update username and role ID of user's Account.   	
	    	listM = us.getDataList();
	    	loadTable(listM);
	    	String path = "src\\Music\\success-sound.wav";
	    	SoundTrack successSound = new SoundTrack(path);
	    	successSound.start();
    	}
    	catch(PhoneException e){
    		String path = "src\\Music\\warning-sound.wav";
    		SoundTrack warnSound = new SoundTrack(path);
    		warnSound.start();
    		Alert a = new Alert(AlertType.WARNING, e.printMessage());
            a.show();
    	}
    	catch(FullnameException e) {
    		String path = "src\\Music\\warning-sound.wav";
    		SoundTrack warnSound = new SoundTrack(path);
    		warnSound.start();
    		Alert a = new Alert(AlertType.WARNING, e.printMessage());
            a.show();
    	}
    }

    public void loadTable(ObservableList<User> list) {
    	colID.setCellValueFactory(new PropertyValueFactory<User,Integer>("UserID"));
		colName.setCellValueFactory(new PropertyValueFactory<User,String>("UserName"));
		colDOB.setCellValueFactory(new PropertyValueFactory<User,Date>("UserDOB"));
		tableUser.setItems(list);
		System.out.print("Load table");
    }
    public void loadRole() {
    	ObservableList<Role> listR = ru.getRoleList();
    	colRoleID.setCellValueFactory(new PropertyValueFactory<Role,Integer>("RoleID"));
		colRoleName.setCellValueFactory(new PropertyValueFactory<Role,String>("RoleName"));
		tableRole.setItems(listR);
		System.out.print("Load table");
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listM = us.getDataList();
		txtID.setEditable(false);
		loadTable(listM);
		loadRole();
		
	}
}
