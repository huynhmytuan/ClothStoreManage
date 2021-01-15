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
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CustomerController implements Initializable {
	CustomerUtil cu = new CustomerUtil();
	ObservableList<Customer> listM;
    int index = -1;
    ResultSet rs = null;
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
    	
    	cu.insertCustomer(cusID, cusName, cusDOB, cusPhone, cusEmail, cusAddress);
    	listM = cu.getDataList();
    	loadTable(listM);
    }

    @FXML
    void btnDelete_Clicked(MouseEvent event) {
    	int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?", "Delete", JOptionPane.YES_NO_OPTION);
    	if(p==0) {
			int cusID = Integer.parseInt(txtID.getText());
			cu.deleteCustomer(cusID);
			listM = cu.getDataList();
			loadTable(listM);
    	} 	
    }

    @FXML
    void btnSearch_Clicked(MouseEvent event) {
    	String search = txtSearch.getText();
    	if("".equals(search)){
            Alert a = new Alert(AlertType.WARNING, "Please enter your search keyword!");
            a.show();
        }
    	else {
    	 	listM = cu.Search(search);
    	 	loadTable(listM);
    	}
    }

    @FXML
    void btnUpdate_Clicked(MouseEvent event) {
    	int cusID = Integer.parseInt(txtID.getText());
    	String cusName = txtName.getText();
    	LocalDate cusDOB = txtDate.getValue();
    	String cusPhone = txtPhone.getText();
    	String cusEmail = txtEmail.getText();
    	String cusAddress = txtAddress.getText();
  	
    	cu.updateCustomer(cusID, cusName, cusDOB, cusPhone, cusEmail, cusAddress);
    	listM = cu.getDataList();
    	loadTable(listM);
    }
    
    @FXML
    void btnRefresh_Clicked(MouseEvent event) {
    	listM = cu.getDataList();
		loadTable(listM);
		txtID.setText("");
		txtName.setText("");
		txtDate.setPromptText("");
		txtPhone.setText("");
		txtEmail.setText("");
		txtAddress.setText("");
    }
    
    @FXML
    void Row_Clicked(MouseEvent event) {
    	Customer cus = tableCustomer.getSelectionModel().getSelectedItem();
    	txtID.setText(""+ cus.getCusID());
    	txtName.setText(cus.getCusName());
    	txtDate.setValue(cus.getCusDOB());
    	txtPhone.setText(cus.getCusPhone());
    	txtEmail.setText("" + cus.getCusEmail());
    	txtAddress.setText("" + cus.getCusAddress());
    }
    
    public void loadTable(ObservableList<Customer> list) {
    	colID.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("CusID"));
		colName.setCellValueFactory(new PropertyValueFactory<Customer,String>("CusName"));
		colDOB.setCellValueFactory(new PropertyValueFactory<Customer,Date>("CusDOB"));
		colPhone.setCellValueFactory(new PropertyValueFactory<Customer,String>("CusPhone"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Customer,String>("CusEmail"));
		colAddress.setCellValueFactory(new PropertyValueFactory<Customer,String>("CusAddress"));
		tableCustomer.setItems(list);
		System.out.print("Load table");
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listM = cu.getDataList();
		loadTable(listM);
	}

}
