package Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SaleController implements Initializable{
	ProductUtil pu = new ProductUtil();
	CustomerUtil cu = new CustomerUtil();
	StorageUtil su = new StorageUtil();
	ObservableList<Product> listM;
	ObservableList<Sale> listSale;
    int index = -1;
    ResultSet rs = null;
    
	@FXML
    private TableView<Product> tableProduct;

    @FXML
    private TableColumn<Product, Integer> col_id;

    @FXML
    private TableColumn<Product, String> col_name;

    @FXML
    private TableColumn<Product, String> col_type;

    @FXML
    private TableColumn<Product, String> col_size;

    @FXML
    private TableColumn<Product, Float> col_outprice;

    @FXML
    private ImageView btn_search;
    
    @FXML
    private JFXTextField txt_search;
    
    @FXML
    private JFXButton btnRefresh;
    
    @FXML
    private JFXTextField txtCusSearch;

    @FXML
    private JFXButton btnCheck;
    
    @FXML
    private TextField txtUName;

    @FXML
    private TextField txtUPhone;

    @FXML
    private TextField txtUAddress;
    
    @FXML
    private TableView<Product> tableSale;

    @FXML
    private TableColumn<Product, String> colName;

    @FXML
    private TableColumn<Product, Integer> colQuanlity;

    @FXML
    private TableColumn<Product, Float> colPrice;

    @FXML
    private TableColumn<Product, Float> colTotal;
    
    @FXML
    private JFXButton btnAddItems;

    public void loadTable(ObservableList<Product> list) {
    	col_id.setCellValueFactory(new PropertyValueFactory<Product,Integer>("ProductID"));
		col_name.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductName"));
		col_type.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductType"));
		col_size.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductSize"));
		col_outprice.setCellValueFactory(new PropertyValueFactory<Product,Float>("ProductOutPrice"));
		tableProduct.setItems(list);
		System.out.print("Load table");
    }
    
    //Search
    @FXML
  public void btnSearch_Clicked(MouseEvent event) {
    	String search = txt_search.getText();
    	if("".equals(search)){
            Alert a = new Alert(AlertType.WARNING, "Please enter your search keyword!");
            a.show();
        }
    	else {
   	 	listM = pu.Search(search);
   	 	loadTable(listM);
    	}
    }
    
  //ComboBox
    @FXML
    private JFXComboBox<String> cmbCategory;
    
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
    
    @FXML
    void btnRefresh_Clicked(MouseEvent event) {
    	listM = pu.getDataList();
		loadTable(listM);
    }
    
    @FXML
    void btnCheck_Clicked(MouseEvent event) {
    	String search = txtCusSearch.getText();
    	System.out.println(search);
    	if(search.equals("")){
    		System.out.println("If in");
            Alert a = new Alert(AlertType.WARNING, "Please enter Customer ID!");
            a.show();
        }
    	else {
    		System.out.println("Else in");
    		int cusID = Integer.parseInt(search);
   	 		Customer cus = cu.getCusByID(cusID);
   	 		txtUName.setText(cus.getCusName());
   	 		txtUPhone.setText(cus.getCusPhone());
   	 		txtUAddress.setText(cus.getCusAddress());
    	}
    }
    
    @FXML
    void btnAddItems_Clicked(MouseEvent event) {
    	Product prod = tableProduct.getSelectionModel().getSelectedItem();
    	if(prod == null) {
    		Alert a = new Alert(AlertType.WARNING, "Please choose an item to add!");
            a.show();
    	}
    	else {
    		TextInputDialog td = new TextInputDialog(); ;
        	td.setHeaderText("Insert quantity of item:");
        	td.showAndWait();
        	String inputValue = td.getEditor().getText();
        	if(inputValue.equals("")){
        		td.setContentText("Quantity can't be empty! Please enter: ");
                td.showAndWait();
        	}
        	else {
            	int inputNum = Integer.parseInt(inputValue);
            	Storage sto = su.getAvailableByProdID(prod.getProductID());
            	int availNum = sto.getQuantityInStock();
            	if(inputNum <=0 || inputNum > availNum){
            		td.setContentText("Quantity invalid! Please re-enter: ");
                    td.showAndWait();
            	}
            	else {
            		listSale.add(new Sale(availNum, null, availNum, availNum, availNum, availNum, inputNum, availNum));               
            	}
        	}
    	}
 
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		listM = pu.getDataList();
		loadTable(listM);
		ObservableList<String> listCategory = loadCategory();
		cmbCategory.setItems(listCategory);
	}
}
