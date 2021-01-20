package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;
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
import javafx.scene.text.Text;

public class SaleController implements Initializable{
	ProductUtil pu = new ProductUtil();
	CustomerUtil cu = new CustomerUtil();
	StorageUtil su = new StorageUtil();
	SaleUtil sa = new SaleUtil();
	Customer curCus = new Customer();
	ObservableList<Product> listM = FXCollections.observableArrayList();
	ObservableList<Sale> listSale = FXCollections.observableArrayList();
    int index = -1;
    
    @FXML
    private Text txtTotalBill;

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
    private TableView<Sale> tableSale;

    @FXML
    private TableColumn<Sale, Integer> colName;

    @FXML
    private TableColumn<Sale, Integer> colQuanlity;

    @FXML
    private TableColumn<Sale, Float> colPrice;

    @FXML
    private TableColumn<Sale, Float> colTotal;
    
    @FXML
    private JFXButton btnAddItems;
    
    @FXML
    private JFXButton btnPayment;

    public void loadTable(ObservableList<Product> list) {
    	col_id.setCellValueFactory(new PropertyValueFactory<Product,Integer>("ProductID"));
		col_name.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductName"));
		col_type.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductType"));
		col_size.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductSize"));
		col_outprice.setCellValueFactory(new PropertyValueFactory<Product,Float>("ProductOutPrice"));
		tableProduct.setItems(list);
		System.out.print("Load table");
    }
    public void loadSale(ObservableList<Sale> list) {
    	colName.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("ProductID"));
    	colQuanlity.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("NumOfProduct"));
    	colPrice.setCellValueFactory(new PropertyValueFactory<Sale,Float>("Price"));
    	colTotal.setCellValueFactory(new PropertyValueFactory<Sale,Float>("TotalPrice"));
    	tableSale.setItems(list);
    	float totalBill = 0;
    	for(Sale one:list) {
    		totalBill +=one.getTotalPrice();
    	}
    	txtTotalBill.setText(""+totalBill+" $");
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
    		txtUName.setText("");
    		txtUPhone.setText("");
    		txtUAddress.setText("");
            Alert a = new Alert(AlertType.WARNING, "Please enter Customer ID!");
            a.show();
        }
    	else {
    		int cusID = Integer.parseInt(search);
   	 		curCus = cu.getCusByID(cusID);
   	 		txtUName.setText(curCus.getCusName());
   	 		txtUPhone.setText(curCus.getCusPhone());
   	 		txtUAddress.setText(curCus.getCusAddress());
    	}
    }
    
    public int getRandomSaleID() {
    	ObservableList<Sale> inputList = sa.getDataList();
    	int numID;
    	Random rand = new Random();
		numID = rand.nextInt(10000);//Random a new numID
		int[] loginIDArr = new int[listM.size()]; //Create a list to store ID in database
		int n=0;
		for(Sale lid : inputList) {
			loginIDArr[n] = lid.getSaleID();
			n++;
		}
		return numID;
    }
    
    @FXML
    void btnAddItems_Clicked(MouseEvent event) {
    	Product prod = tableProduct.getSelectionModel().getSelectedItem();
    	if(prod == null) {
    		Alert a = new Alert(AlertType.WARNING, "Please choose an item to add!");
            a.show();
    	}
    	else {
    		TextInputDialog td = new TextInputDialog(); 
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
        			inputValue = td.getEditor().getText();
        			inputNum = Integer.parseInt(inputValue);
            		LocalDate today = LocalDate.now();
            		int saleID = getRandomSaleID();
            		int staffID = LoginController.userID;
            		Float totalprice = (float)inputNum * prod.getProductOutPrice();
        			listSale.add(new Sale(saleID, today, curCus.getCusID(), staffID, prod.getProductID(), inputNum, prod.getProductOutPrice(),totalprice));   
        			loadSale(listSale);
            	}
            	else {
            		LocalDate today = LocalDate.now();
            		int saleID = getRandomSaleID();
            		int staffID = LoginController.userID;
            		Float totalprice = (float)inputNum * prod.getProductOutPrice();
        			listSale.add(new Sale(saleID, today, curCus.getCusID(), staffID, prod.getProductID(), inputNum, prod.getProductOutPrice(),totalprice));   
        			loadSale(listSale);
            	}
        	}
    	}
    }
    
    @FXML
    void btnEditBill_Clicked(MouseEvent event) {
    	Optional<String> result;
    	if(listSale.isEmpty()) {
    		Alert a = new Alert(AlertType.WARNING, "Bill is empty!");
            a.show();
    	}
    	else {
        	Sale curSale = tableSale.getSelectionModel().getSelectedItem();
    		if(curSale == null) {
    			Alert a = new Alert(AlertType.WARNING, "Please choose an item to Edit!");
                a.show();
    		}
    		else {
    			int index= listSale.indexOf(curSale);
    			TextInputDialog td = new TextInputDialog();
    	    	td.setHeaderText("Insert quantity of item:");
    	    	result = td.showAndWait();
    	    	
    	    		String inputStr = td.getEditor().getText();
        			if(inputStr.equals("")) {
        				do {
        					td.setContentText("Quantity can't be empty! Please enter: ");
        					result = td.showAndWait();
        					inputStr = td.getEditor().getText();
        					if(!result.isPresent()) {
        						break;
        					}
        				}while(inputStr.equals(""));
        				if(result.isPresent()) {
        					int inputNum = Integer.parseInt(inputStr);
	    	    			Storage sto = su.getAvailableByProdID(curSale.getProductID());
	        	    		int availNum = sto.getQuantityInStock();
	    	    			if(inputNum <=0 || inputNum > availNum){
	    	    				do {
	    	    					td.setContentText("Quantity invalid! Please re-enter: ");
	    	    					td.showAndWait();
	    	    					inputStr = td.getEditor().getText();
	    	    					inputNum = Integer.parseInt(inputStr);
	    	    				}while(inputNum <=0 || inputNum > availNum);
	    		    			listSale.remove(index);
	    		    			curSale.setNumOfProduct(inputNum);
	    		    			listSale.add(curSale);
	    		    			loadSale(listSale);
	        	        	}
	        	    		else {
	        	    			listSale.remove(index);
	        	    			curSale.setNumOfProduct(inputNum);
	        	    			listSale.add(curSale);
	        	    			loadSale(listSale);
	        	    		}
        				}
    	    			
        	    	}
        	    	else {
        	    		int inputNum = Integer.parseInt(inputStr);
    	    			Storage sto = su.getAvailableByProdID(curSale.getProductID());
        	    		int availNum = sto.getQuantityInStock();
    	    			if(inputNum <=0 || inputNum > availNum){
    	    				do {
    	    					td.setContentText("Quantity invalid! Please re-enter: ");
    	    					result = td.showAndWait();
    	    					if(!result.isPresent()) {
    	    						break;
    	    					}
    	    					inputStr = td.getEditor().getText();
    	    					inputNum = Integer.parseInt(inputStr);
    	    				}while(inputNum <=0 || inputNum > availNum);
    	    				if(result.isPresent()) {
    	    					listSale.remove(index);
	    		    			curSale.setNumOfProduct(inputNum);
	    		    			listSale.add(curSale);
	    		    			loadSale(listSale);
    	    				}
        	        	}
        	    		else {
        	    			listSale.remove(index);
        	    			curSale.setNumOfProduct(inputNum);
        	    			listSale.add(curSale);
        	    			loadSale(listSale);
        	    		}
        	    	}
    	    	}
    	    		
    		}
    	}
    
    @FXML
    void btnDleteItem_Clicked(MouseEvent event) {
    	ObservableList<Sale> items = tableSale.getItems();
    	Sale pro = tableSale.getSelectionModel().getSelectedItem();
    	int index= listSale.indexOf(pro);
    	if(items.isEmpty()) {
    		Alert a = new Alert(AlertType.WARNING, "Bill is empty!");
            a.show();
    	}
    	else {
    		if(pro == null) {
    			Alert a = new Alert(AlertType.WARNING, "Please choose an item to Delete!");
                a.show();
    		}
    		else {
    			listSale.remove(index);
    	    	loadSale(listSale);
    		}
    	}
    }
    
    @FXML
    void btnPayment_Clicked(MouseEvent event) {
    	ObservableList<Sale> items = tableSale.getItems();
    	if(items.isEmpty()) {
    		Alert a = new Alert(AlertType.WARNING, "Bill is empty!");
            a.show();
    	}
    	else {
    		//code add bill vao csdl
    		for(Sale one: listSale) {
    			sa.insertSale(one.getSaleID(), one.getDateSale(), one.getCusID(), one.getStaffID(), one.getProductID(), one.getNumOfProduct(), one.getPrice(), one.getTotalPrice());
    		}
      		Alert a = new Alert(AlertType.INFORMATION, "Payment success!");
            a.show();
            //clear table view
            listSale.clear();
        	loadSale(listSale);
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
