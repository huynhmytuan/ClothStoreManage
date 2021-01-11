package Controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProductController implements Initializable {
	ProductUtil pu = new ProductUtil();

    @FXML
    private AnchorPane productUI;

    @FXML
    private TableView<Product> tableProduct;

    @FXML
    private TableColumn<Product, Integer> colID;

    @FXML
    private TableColumn<Product, String> colName;

    @FXML
    private TableColumn<Product, String> colType;

    @FXML
    private TableColumn<Product, String> colSize;

    @FXML
    private TableColumn<Product, Float> colOutPrice;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnRefresh;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private ImageView btnSearch;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtSize;

    @FXML
    private JFXTextField txtInPrice;

    @FXML
    private JFXTextField txtOutPrice;

    @FXML
    private JFXTextField txtPicture;

    @FXML
    private JFXTextField txtDecs;
    
	@FXML
    void btnAdd_Clicked(MouseEvent event) {
    	int productID = Integer.parseInt(txtID.getText());
    	String productName = txtName.getText();
    	String productType = txtType.getText();
    	String productSize = txtSize.getText();
    	String productDecs = "";
    	productDecs = txtDecs.getText();
    	String productInPrice = txtInPrice.getText();
    	String productOutPrice = txtOutPrice.getText();
    	String productPicture = "";
		productPicture = txtPicture.getText();
    	
    	pu.insertProduct(productID, productName, productType, productSize, productDecs, Float.parseFloat(productInPrice), Float.parseFloat(productOutPrice), productPicture);
    	
    	loadTable();
    }

    @FXML
    void btnDelete_Clicked(MouseEvent event) {
    	int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?", "Delete", JOptionPane.YES_NO_OPTION);
    	if(p==0) {
			int productID = Integer.parseInt(txtID.getText());
			pu.deleteProduct(productID);
			loadTable();
    	} 	
    }

    @FXML
    void btnUpdate_Clicked(MouseEvent event) {

    }

    @FXML
    void btnRefresh_Clicked(MouseEvent event) {
    	txtID.setText("");
    	txtName.setText("");
    	txtType.setText("");
    	txtSize.setText("");
    	txtDecs.setText("");
    	txtInPrice.setText("");
    	txtOutPrice.setText("");
    	txtPicture.setText("");
    }

    @FXML
    void btnSearch_Clicked(MouseEvent event) {
    	
    }
    ObservableList<Product> listM;
    int index = -1;
    Connection cnn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    public void loadTable() {
    	colID.setCellValueFactory(new PropertyValueFactory<Product,Integer>("ProductID"));
		colName.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductName"));
		colType.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductType"));
		colSize.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductSize"));
		colOutPrice.setCellValueFactory(new PropertyValueFactory<Product,Float>("ProductOutPrice"));
		
		listM = pu.getDataList();
		tableProduct.setItems(listM);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadTable();	
	}

}
