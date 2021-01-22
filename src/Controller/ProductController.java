package Controller;


import java.awt.FileDialog;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import Excpt.FullnameException;
import Excpt.FullnameValidator;
import Model.*;
import Task.SoundTrack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ProductController implements Initializable {
	ProductUtil pu = new ProductUtil();
	StorageUtil su = new StorageUtil();
	ObservableList<Product> listM;
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
    private JFXTextField txtDecs;

    @FXML
    private JFXButton btnimport;

    @FXML
    private JFXTextField tbimageURL;
    
    @FXML
    private JFXComboBox<String> cmbCategory;

    @FXML
    private JFXComboBox<String> cmbSize;

    @FXML
    private JFXTextField txtNumberOfProductIn;

    @FXML
    private JFXTextField txtQuantityInStock;

    @FXML
    private ImageView imgItems;
    
    public int getRandomStorID() {
    	ObservableList<Storage> inputList = su.getDataList();
    	int storID = -1;
    	Random rand = new Random();
    	storID = rand.nextInt(10000);//Random a new numID
		int[] storIDArr = new int[listM.size()]; //Create a list to store ID in database
		int n=0;
		for(Storage lid : inputList) {
			storIDArr[n] = lid.getStorID();
			n++;
		}
		boolean check = false;
		 do{
			 //Check if storID already in storIDArr
			 for(int num : storIDArr) {
				 if(num == storID) {
					 check = true;
					 break;
				 }
			 }
			 if(check){
				 storID = rand.nextInt(10000);
			 }
			 else {
				 return storID;
			 }
		}while(check);
		return storID;
    }
	@FXML
    void btnAdd_Clicked(MouseEvent event) {
    	try {
			int productID = Integer.parseInt(txtID.getText());
	    	String productName = txtName.getText();
	    	FullnameValidator.isValid(productName);
	    	String productType = txtType.getText();
	    	String productSize = txtSize.getText();
	    	String productDecs = "";
	    	productDecs = txtDecs.getText();
	    	String productInPrice = txtInPrice.getText();
	    	String productOutPrice = txtOutPrice.getText();
	    	String productPicture = "";
			productPicture = tbimageURL.getText();
			pu.insertProduct(productID, productName, productType, productSize, productDecs, Float.parseFloat(productInPrice), Float.parseFloat(productOutPrice), productPicture);
	    	int numIn = Integer.parseInt(txtNumberOfProductIn.getText());
	    	int numLeft = Integer.parseInt(txtQuantityInStock.getText());
	    	int storID = getRandomStorID();
	    	LocalDate today = LocalDate.now();
			System.out.println("Current Date="+today);
			float totalPrice =  (float)numIn * Float.parseFloat(productInPrice);				
			su.insertStorage(storID, productID, numIn, numLeft, today, totalPrice);
			Alert a = new Alert(AlertType.INFORMATION,"Insert product successfully!");
		    a.show();
		    listM = pu.getDataList();
		    loadTable(listM);
		    String path = "src\\Music\\success-sound.wav";
		    SoundTrack successSound = new SoundTrack(path);
		    successSound.start();
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
    		int p = JOptionPane.showConfirmDialog(null, "Do you really want to delete ?", "Delete", JOptionPane.YES_NO_OPTION);
    		if(p==0) {
	    		String id = txtID.getText().replace(" ", "");
				int productID = Integer.parseInt(id);
			try {			
				String path = "src\\Music\\success-sound.wav";
				SoundTrack successSound = new SoundTrack(path);
				successSound.start();
				pu.deleteProduct(productID);
				su.deleteStorage(productID);
				Alert a = new Alert(AlertType.INFORMATION,"Delete product successfully!");
				a.show();
			}
			catch(Exception e) {
				Alert a = new Alert(AlertType.ERROR, "Error: "+"\n"+e.getMessage());
            	String path = "src\\Music\\error-noti-sound.wav";
        	 	SoundTrack error = new SoundTrack(path);
                error.start();
                a.show();
			}
			listM = pu.getDataList();
			loadTable(listM);
    	} 	
    }

    @FXML
    void btnUpdate_Clicked(MouseEvent event) {
    	try {
	    	int productID = Integer.parseInt(txtID.getText());
	    	String productName = txtName.getText();
	    	FullnameValidator.isValid(productName);
	    	String productType = txtType.getText();
	    	String productSize = txtSize.getText();
	    	String productDecs = "";
	    	productDecs = txtDecs.getText();
	    	String productInPrice = txtInPrice.getText();
	    	String productOutPrice = txtOutPrice.getText();
	    	String productPicture = "";
			productPicture = tbimageURL.getText();
			int numIn = Integer.parseInt(txtNumberOfProductIn.getText());
	    	int numLeft = Integer.parseInt(txtQuantityInStock.getText());
	    	LocalDate today = LocalDate.now();
			System.out.println("Current Date="+today);
			float totalPrice =  (float)numIn * Float.parseFloat(productInPrice);
			pu.updatetProduct(productID, productName, productType, productSize, productDecs, Float.parseFloat(productInPrice), Float.parseFloat(productOutPrice), productPicture);
			su.updateStorage(productID, numIn, numLeft, totalPrice);
			Alert a = new Alert(AlertType.INFORMATION,"Update product successfully!");
		    a.show();
			listM = pu.getDataList();
	    	loadTable(listM);
			String path = "src\\Music\\success-sound.wav";
			SoundTrack successSound = new SoundTrack(path);
			successSound.start();
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
		tbimageURL.setText("");
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
    	tbimageURL.setText(pro.getProductPicture());
    	String path = "";
    	path = tbimageURL.getText();
    	if((path == null)) {
    		File file = new File("src/ProductImage/Error-icon.png");
	        Image image = new Image(file.toURI().toString());
	        imgItems.setImage(image);
    	}
    	else {
    		File file = new File(path);
	        Image image = new Image(file.toURI().toString());
	        imgItems.setImage(image);
    	}
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
    
    @FXML
    void btnImport_Clicked(MouseEvent event) {
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	File file;
    	FileChooser fileChooser = new FileChooser();
		file = fileChooser.showOpenDialog(window);
    	if(file != null) {
    		tbimageURL.setText(file.getAbsolutePath());
    	}
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
