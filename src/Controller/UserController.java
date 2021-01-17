package Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Model.*;
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
    private TableColumn<User, String> colPhone;

    @FXML
    private TableColumn<User, String> colEmail;

    @FXML
    private TableColumn<User, String> colAddress;

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
    private JFXTextField txtAddress;

    @FXML
    private DatePicker txtDate;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private ImageView btnSearch;

    @FXML
    void Row_Clicked(MouseEvent event) {
    	User us = tableUser.getSelectionModel().getSelectedItem();
    	txtID.setText(""+ us.getUserID());
    	txtName.setText(us.getUserName());
    	txtDate.setValue(us.getUserDOB());
    	txtPhone.setText(us.getUserPhone());
    	txtEmail.setText("" + us.getUserEmail());
    	txtAddress.setText("" + us.getUserAddress());
    }

    @FXML
    void btnAdd_Clicked(MouseEvent event) {
    	int userID = Integer.parseInt(txtID.getText());
    	String userName = txtName.getText();
    	LocalDate userDOB = txtDate.getValue();
    	String userPhone = txtPhone.getText();
    	String userEmail = txtEmail.getText();
    	String userAddress = txtAddress.getText();
    	
    	us.insertUser(userID, userName, userDOB, userPhone, userEmail, userAddress);
    	listM = us.getDataList();
    	loadTable(listM);
    }

    @FXML
    void btnDelete_Clicked(MouseEvent event) {
    	int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?", "Delete", JOptionPane.YES_NO_OPTION);
    	if(p==0) {
			int userID = Integer.parseInt(txtID.getText());
			us.deleteUser(userID);
			listM = us.getDataList();
			loadTable(listM);
    	} 	
    }

    @FXML
    void btnRefresh_Clicked(MouseEvent event) {
    	listM = us.getDataList();
		loadTable(listM);
		txtID.setText("");
		txtName.setText("");
		txtDate.setPromptText("");
		txtPhone.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
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
    	int userID = Integer.parseInt(txtID.getText());
    	String userName = txtName.getText();
    	LocalDate userDOB = txtDate.getValue();
    	String userPhone = txtPhone.getText();
    	String userEmail = txtEmail.getText();
    	String userAddress = txtAddress.getText();
  	
    	us.updatetUser(userID, userName, userDOB, userPhone, userEmail, userAddress);
    	listM = us.getDataList();
    	loadTable(listM);
    }

    public void loadTable(ObservableList<User> list) {
    	colID.setCellValueFactory(new PropertyValueFactory<User,Integer>("UserID"));
		colName.setCellValueFactory(new PropertyValueFactory<User,String>("UserName"));
		colDOB.setCellValueFactory(new PropertyValueFactory<User,Date>("UserDOB"));
		colPhone.setCellValueFactory(new PropertyValueFactory<User,String>("UserPhone"));
		colEmail.setCellValueFactory(new PropertyValueFactory<User,String>("UserEmail"));
		colAddress.setCellValueFactory(new PropertyValueFactory<User,String>("UserAddress"));
		tableUser.setItems(list);
		System.out.print("Load table");
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listM = us.getDataList();
		loadTable(listM);
	}
}
