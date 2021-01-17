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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ProductOfStaffController implements Initializable {
	ProductUtil pu = new ProductUtil();
	ObservableList<Product> listM;
    int index = -1;
    ResultSet rs = null;
	@FXML
    private TableView<Product> table_Product;

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
    private JFXTextField txt_id;

    @FXML
    private JFXTextField txt_proname;

    @FXML
    private JFXTextField txt_protype;

    @FXML
    private JFXTextField txt_prosize;

    @FXML
    private JFXTextField txt_proinprice;

    @FXML
    private JFXTextField txt_prooutprice;
    
    @FXML
    private JFXTextField txt_search;

    @FXML
    private ImageView btn_search;
    
    @FXML
    private JFXButton btnRefresh;
    
    //ComboBox
    @FXML
    private JFXComboBox<String> cmbCategory;

    @FXML
    private JFXComboBox<String> cmbSize;
    
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
  
    public void loadTable(ObservableList<Product> list) {
    	col_id.setCellValueFactory(new PropertyValueFactory<Product,Integer>("ProductID"));
		col_name.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductName"));
		col_type.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductType"));
		col_size.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductSize"));
		col_outprice.setCellValueFactory(new PropertyValueFactory<Product,Float>("ProductOutPrice"));
		table_Product.setItems(list);
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
    
    //Detail
    @FXML
    public void Row_Clicked(MouseEvent event) {
        Product pro = table_Product.getSelectionModel().getSelectedItem();
    	txt_id.setText(""+ pro.getProductID());
    	txt_proname.setText(pro.getProductName());
    	txt_protype.setText(pro.getProductType());
    	txt_prosize.setText(pro.getProductSize());
    	txt_proinprice.setText("" + pro.getProductInPrice());
    	txt_prooutprice.setText("" + pro.getProductOutPrice());
    }
    
    @FXML
    void btnRefresh_Clicked(MouseEvent event) {
    	listM = pu.getDataList();
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
