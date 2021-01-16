package Controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProductController implements Initializable {
	ProductUtil pu = new ProductUtil();
	ObservableList<Product> listM;
    int index = -1;
    ResultSet rs = null;

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
    private JFXComboBox<String> cmbCategory;

    @FXML
    private JFXComboBox<String> cmbSize;

    
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
    	listM = pu.getDataList();
    	loadTable(listM);
    }

    @FXML
    void btnDelete_Clicked(MouseEvent event) {
    	int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?", "Delete", JOptionPane.YES_NO_OPTION);
    	if(p==0) {
    		String id = txtID.getText().replace(" ", "");
			int productID = Integer.parseInt(id);
			pu.deleteProduct(productID);
			listM = pu.getDataList();
			loadTable(listM);
    	} 	
    }

    @FXML
    void btnUpdate_Clicked(MouseEvent event) {
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
		pu.updatetProduct(productID, productName, productType, productSize, productDecs, Float.parseFloat(productInPrice), Float.parseFloat(productOutPrice), productPicture);
		listM = pu.getDataList();
    	loadTable(listM);
    }

    @FXML
    void btnRefresh_Clicked(MouseEvent event) {
    	listM = pu.getDataList();
		loadTable(listM);
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
    	String search = txtSearch.getText();
    	if("".equals(search)){
            Alert a = new Alert(AlertType.WARNING, "Please enter your search keyword!");
            a.show();
        }
    	else {
    	 	listM = pu.Search(search);
    	 	loadTable(listM);
    	}
    }
    
    @FXML
    void Row_Clicked(MouseEvent event) {
    	Product pro = tableProduct.getSelectionModel().getSelectedItem();
    	txtID.setText(""+ pro.getProductID());
    	txtName.setText(pro.getProductName());
    	txtType.setText(pro.getProductType());
    	txtSize.setText(pro.getProductSize());
    	txtInPrice.setText("" + pro.getProductInPrice());
    	txtOutPrice.setText("" + pro.getProductOutPrice());
    }
    
    public void loadTable(ObservableList<Product> list) {
    	colID.setCellValueFactory(new PropertyValueFactory<Product,Integer>("ProductID"));
		colName.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductName"));
		colType.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductType"));
		colSize.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductSize"));
		colOutPrice.setCellValueFactory(new PropertyValueFactory<Product,Float>("ProductOutPrice"));
		
		tableProduct.setItems(list);
		System.out.print("Load table");
    }
    
    public ObservableList<String> loadCategory() {
    	listM = pu.getDataList();
    	ObservableList<String> typeList = FXCollections.observableArrayList();
		for(Product one : listM) {
			typeList.add(one.getProductType());
		}
		ObservableList<String> listCategory = FXCollections.observableArrayList();
		listCategory.add(typeList.get(0));
		for(String i : typeList) {
			boolean check= false;
			for(String j : listCategory) {
				if(i.equals(j)) {
					check = false;
					break;
				}
				else {
					check = true;
				}
			}
			if(check) {
				listCategory.add(i);
			}
		}
		return listCategory;
    }
    @FXML
    public void cmbCategory_Selected(ActionEvent event) {
    	String category = cmbCategory.getValue();
    	listM = pu.Search(category);
	 	loadTable(listM);
    	
    }
    
    public ObservableList<String> loadSize() {
    	listM = pu.getDataList();
    	ObservableList<String> sizeList = FXCollections.observableArrayList();
		for(Product one : listM) {
			sizeList.add(one.getProductSize());
		}
		ObservableList<String> listSize = FXCollections.observableArrayList();
		listSize.add(sizeList.get(0));
		for(String i : sizeList) {
			boolean check= false;
			for(String j : listSize) {
				if(i.equals(j)) {
					check = false;
					break;
				}
				else {
					check = true;
				}
			}
			if(check) {
				listSize.add(i);
			}
		}
		return listSize;
    }
    
    
    @FXML
    public void cmbSize_Selected(ActionEvent event) {
    	String size = cmbSize.getValue();
    	listM = pu.Search(size);
	 	loadTable(listM);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listM = pu.getDataList();
		loadTable(listM);
		ObservableList<String> listCategory = loadCategory();
		cmbCategory.setItems(listCategory);
		ObservableList<String> listSize = loadSize();
		cmbSize.setItems(listSize);
	}

}
