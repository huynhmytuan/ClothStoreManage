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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Pair;

public class BillController implements Initializable{
	SaleUtil su = new SaleUtil();
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
    private DatePicker datePicker;

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
    public ObservableList<Sale> getBillList() {
    	ObservableList<Sale> listM = su.getDataList();
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
    	System.out.print("Get data");
    	Sale curBill = tableBill.getSelectionModel().getSelectedItem();
    	int billID = curBill.getSaleID();
    	ObservableList<Sale> proList = su.getBillBySaleID(billID);
    	loadProduct(proList);
    }
    
    
    public void cmbSearch_Selected(ActionEvent event) {
    	System.out.println("Event ne");
    	String category = cmbSearch.getValue();
    	ObservableList<Sale> bill = FXCollections.observableArrayList();
    	if(category.equals("Search By Date")) {
    		LocalDate date = datePicker.getValue();
    		bill = su.searchBillByDate(date);
    		loadBill(bill);
    	}
    	else {
    		LocalDate date = datePicker.getValue();
    		Month month = date.getMonth();
    		bill = su.searchBillByMonth(month);
    		loadBill(bill);
    	}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		cmbSearch.getItems().addAll("Search By Date", "Search By Month");
		ObservableList<Sale> billList = getBillList();
		loadBill(billList);
	}
}

