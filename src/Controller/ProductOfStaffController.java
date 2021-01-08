package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import Model.*;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductOfStaffController implements Initializable {
	ProductUtil pu = new ProductUtil();
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
    private TableColumn<Product, String> col_decs;

    @FXML
    private TableColumn<Product, Float> col_inprice;

    @FXML
    private TableColumn<Product, Float> col_outprice;

    @FXML
    private TableColumn<Product, String> col_picture;

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

    
    ObservableList<Product> listM;
    int index = -1;
    Connection cnn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		col_id.setCellValueFactory(new PropertyValueFactory<Product,Integer>("ProductID"));
		col_name.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductName"));
		col_type.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductType"));
		col_size.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductSize"));
		col_decs.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductDecs"));
		col_inprice.setCellValueFactory(new PropertyValueFactory<Product,Float>("ProductInPrice"));
		col_outprice.setCellValueFactory(new PropertyValueFactory<Product,Float>("ProductOutPrice"));
		col_picture.setCellValueFactory(new PropertyValueFactory<Product,String>("ProductPicture"));
		
		listM = pu.getDataList();
		table_Product.setItems(listM);
		
	}
}
