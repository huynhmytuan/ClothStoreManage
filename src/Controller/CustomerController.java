package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CustomerController implements Initializable {
	CustomerUtil cu = new CustomerUtil();

    @FXML
    private AnchorPane customerUI;

    @FXML
    private TableView<Customer> tableCustomer;

    @FXML
    private TableColumn<Customer, Integer> colID;

    @FXML
    private TableColumn<Customer, String> colName;

    @FXML
    private TableColumn<Customer, Date> colDOB;

    @FXML
    private TableColumn<Customer, String> colPhone;

    @FXML
    private TableColumn<Customer, String> colEmail;

    @FXML
    private TableColumn<Customer, String> colAddress;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private ImageView btnSearch;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private DatePicker txtDate;

    @FXML
    private JFXTextField txtPhone;
    
    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtAddress;
    
    @FXML
    private JFXButton btnRefresh;

    @FXML
    void btnAdd_Clicked(MouseEvent event) {
    	int cusID = Integer.parseInt(txtID.getText());
    	String cusName = txtName.getText();
    	LocalDate cusDOB = txtDate.getValue();
    	String cusPhone = txtPhone.getText();
    	String cusEmail = txtEmail.getText();
    	String cusAddress = txtAddress.getText();
    	CustomerUtil kn = new CustomerUtil();
    	
    	kn.insertCustomer(cusID, cusName, cusDOB, cusPhone, cusEmail, cusAddress);
    	loadTable();
    }

    @FXML
    void btnDelete_Clicked(MouseEvent event) {
    	int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?", "Delete", JOptionPane.YES_NO_OPTION);
    	if(p==0) {
			int cusID = Integer.parseInt(txtID.getText());
			cu.deleteCustomer(cusID);
			loadTable();
    	} 	
    }

    @FXML
    void btnSearch_Clicked(MouseEvent event) {

    }

    @FXML
    void btnUpdate_Clicked(MouseEvent event) {

    }
    
    @FXML
    void btnRefresh_Clicked(MouseEvent event) {
    	txtID.setText("");
    	txtName.setText("");
    	txtDate.setPromptText("");
    	txtPhone.setText("");
    	txtEmail.setText("");
    	txtAddress.setText("");
    }
    
    ObservableList<Customer> listM;
    int index = -1;
    Connection cnn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void loadTable() {
    	colID.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("CusID"));
		colName.setCellValueFactory(new PropertyValueFactory<Customer,String>("CusName"));
		colDOB.setCellValueFactory(new PropertyValueFactory<Customer,Date>("CusDOB"));
		colPhone.setCellValueFactory(new PropertyValueFactory<Customer,String>("CusPhone"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Customer,String>("CusEmail"));
		colAddress.setCellValueFactory(new PropertyValueFactory<Customer,String>("CusAddress"));
		listM = cu.getDataList();
		tableCustomer.setItems(listM);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadTable();
	}

}
