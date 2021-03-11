package Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

import javax.management.relation.Relation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import Model.Product;
import Model.Sale;
import Model.SaleUtil;
import Model.Storage;
import Task.SoundTrack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

public class BillController implements Initializable{
	SaleUtil su = new SaleUtil();
	ObservableList<Sale> listSaleAll = su.getDataList();
	ObservableList<Sale> billList = getBillList(listSaleAll);
    @FXML
    private TableView<Sale> tableBill;

    @FXML
    private TableColumn<Sale, Integer> colBillID;

    @FXML
    private TableColumn<Sale, LocalDate> colDate;

    @FXML
    private TableColumn<Sale, Integer> colStaffID;

    @FXML
    private TableView<Sale> tableProduct;

    @FXML
    private TableColumn<Sale, Integer> colProductID;

    @FXML
    private TableColumn<Sale, Integer> colQuantity;

    @FXML
    private TableColumn<Sale, Float> colTotal;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXComboBox<String> cmbSearch;

    @FXML
    private JFXButton btnRefresh;
    @FXML
    private JFXButton btnCheck;
    
    @FXML
    private DatePicker datePicker;
    
    @FXML
    private Label txtPrice;

    public void loadBill(ObservableList<Sale> list) {
    	colBillID.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("SaleID"));
		colDate.setCellValueFactory(new PropertyValueFactory<Sale,LocalDate>("DateSale"));
		colStaffID.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("StaffID"));
		tableBill.setItems(list);
    }
    public void loadProduct(ObservableList<Sale> list) {
    	colProductID.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("ProductID"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<Sale,Integer>("NumOfProduct"));
		colTotal.setCellValueFactory(new PropertyValueFactory<Sale,Float>("TotalPrice"));
		tableProduct.setItems(list);
    }    	
    
    public ObservableList<Sale> getBillList(ObservableList<Sale> listM) {
    	ObservableList<Sale> billList = FXCollections.observableArrayList();
		billList.add(listM.get(0));
		for(Sale i : listM) {
			boolean check= false;
			for(Sale j : billList) {
				if(i.getSaleID() == j.getSaleID()) {
					check = false;
					break;
				}
				else {
					check = true;
				}
			}
			if(check) {
				billList.add(i);
			}
		}
		return billList;
    }
    @FXML
    void Row_Clicked(MouseEvent event) {
    	Sale curBill = tableBill.getSelectionModel().getSelectedItem();
    	int billID = curBill.getSaleID();
    	ObservableList<Sale> proList = su.getBillBySaleID(billID);
    	loadProduct(proList);
    	String price = totalPrice();
    	txtPrice.setText(price);
    }
    
    @FXML
    public void btnCheck_Selected(MouseEvent event) {
    	String category = cmbSearch.getValue();
    	if(category==null) {
    		Alert a = new Alert(AlertType.INFORMATION,"Please choose one of search type!");
		    a.show();
    	}
    	else {
    		ObservableList<Sale> bill = FXCollections.observableArrayList();
        	if(category.equals("Search By Date")) {
        		LocalDate date = datePicker.getValue();
        		bill = su.searchBillByDate(date);
        		ObservableList<Sale> billList = getBillList(bill); 
        		loadBill(billList);
        	}
        	else {
        		LocalDate date = datePicker.getValue();
        		int month = date.getMonthValue();
        		bill = su.searchBillByMonth(month);
        		ObservableList<Sale> billList = getBillList(bill); 
        		loadBill(billList);
        	}
        	loadProduct(null);
    	}
    }
    @FXML
    public void btnRefresh_Clicked(MouseEvent event) {
    	loadBill(billList);
    	tableProduct.setItems(null);
    }
    
    @FXML
    public void btnDelete_Clicked(MouseEvent event) {
    	try {
    		ObservableList<Sale> items = tableBill.getItems();
	    	Sale pro = tableBill.getSelectionModel().getSelectedItem();
	    	if(items.isEmpty()) {
	    		String path = "src\\Music\\error-noti-sound.wav";
	    		SoundTrack errorSound = new SoundTrack(path);
	    		errorSound.start();
	    		Alert a = new Alert(AlertType.WARNING, "List of bill is empty!");
	            a.show();
	    	}
	    	else {
	    		if(pro == null) {
	    			String path = "src\\Music\\error-noti-sound.wav";
	        		SoundTrack errorSound = new SoundTrack(path);
	        		errorSound.start();
	    			Alert a = new Alert(AlertType.WARNING, "Please choose a Bill to Delete!");
	                a.show();
	    		}
	    		else {
	    			String path = "src\\Music\\success-sound.wav";
	    			SoundTrack successSound = new SoundTrack(path);
	    			successSound.start();
	    			su.deleteSale(pro.getSaleID());
	    			ObservableList<Sale> listM = su.getDataList();
	    			billList = getBillList(listM);
	    	    	loadBill(billList);
	    		}
	    	}
    	}
    	catch(Exception e) {
    		String path = "src\\Music\\error-noti-sound.wav";
    		SoundTrack errorSound = new SoundTrack(path);
    		errorSound.start();
    		Alert a = new Alert(AlertType.ERROR, "Error: "+"\n"+e.getMessage());
	        a.show();
    	}
    	
    }
    public String totalPrice() {
    	float totalPrice = 0;
    	ObservableList<Sale> pro = tableProduct.getItems();
    	if(pro == null) {
    		return totalPrice + " $";
    	}
    	else {
    		totalPrice = 0;
    		for(Sale one : pro){
    			totalPrice += one.getTotalPrice();
    		}
    	}
    	return totalPrice + " $";
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cmbSearch.getItems().addAll("Search By Date", "Search By Month");
		loadBill(billList);
	}
}

