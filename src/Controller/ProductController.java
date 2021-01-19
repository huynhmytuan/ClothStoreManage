package Controller;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Random;
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
	StorageUtil su = new StorageUtil();
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
    private JFXTextField txtNumberOfProductIn;

    @FXML
    private JFXTextField txtQuantityInStock;

    public int getRandomStorID() {
    	ObservableList<Storage> inputList = su.getDataList();
    	int storID;
    	Random rand = new Random();
    	storID = rand.nextInt(10000);//Random a new numID
		int[] storIDArr = new int[listM.size()]; //Create a list to store ID in database
		int n=0;
		for(Storage lid : inputList) {
			storIDArr[n] = lid.getStorID();
			n++;
		}
		return storID;
    }
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
    	int numIn = Integer.parseInt(txtNumberOfProductIn.getText());
    	int numLeft = Integer.parseInt(txtQuantityInStock.getText());
    	int storID = getRandomStorID();
    	LocalDate today = LocalDate.now();
		System.out.println("Current Date="+today);
		
		float totalPrice =  (float)numIn * Float.parseFloat(productInPrice);
    	try {
			
			su.insertStorage(storID, productID, numIn, numLeft, today, totalPrice);
			Alert a = new Alert(AlertType.INFORMATION,"Insert product successfully!");
		    a.show();
		    listM = pu.getDataList();
		    loadTable(listM);
		}
		catch(Exception e) {
		}
    	
    }

    @FXML
    void btnDelete_Clicked(MouseEvent event) {
    	int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?", "Delete", JOptionPane.YES_NO_OPTION);
    	if(p==0) {
    		String id = txtID.getText().replace(" ", "");
			int productID = Integer.parseInt(id);
			try {
				pu.deleteProduct(productID);
				su.deleteStorage(productID);
				Alert a = new Alert(AlertType.INFORMATION,"Delete product successfully!");
				a.show();
			
			}
			catch(Exception e) {
				
			}
			
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
		
		
		int numIn = Integer.parseInt(txtNumberOfProductIn.getText());
    	int numLeft = Integer.parseInt(txtQuantityInStock.getText());
    	LocalDate today = LocalDate.now();
		System.out.println("Current Date="+today);
		float totalPrice =  (float)numIn * Float.parseFloat(productInPrice);
		try {
			pu.updatetProduct(productID, productName, productType, productSize, productDecs, Float.parseFloat(productInPrice), Float.parseFloat(productOutPrice), productPicture);
			su.updateStorage(productID, numIn, numLeft, totalPrice);

			Alert a = new Alert(AlertType.INFORMATION,"Update product successfully!");
		    a.show();
		}
		catch(Exception e) {
		}
    	
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
		txtNumberOfProductIn.setText("");
		txtQuantityInStock.setText("");
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
    	
    	Storage sto = su.getAvailableByProdID(pro.getProductID());
    	txtNumberOfProductIn.setText(""+sto.getNumOfProductIn());
    	txtQuantityInStock.setText(""+ sto.getQuantityInStock());
    }
    
    public void loadTable(ObservableList<Product> list) {
    	colID.setCellValueFactory(new PropertyValueFactory<Product,Integer>("ProductID"));
		colName.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductName"));
		colType.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductType"));
		colSize.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductSize"));
		colOutPrice.setCellValueFactory(new PropertyValueFactory<Product,Float>("ProductOutPrice"));
		
		tableProduct.setItems(list);
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
