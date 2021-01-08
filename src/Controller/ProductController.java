package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ProductController {

    @FXML
    private AnchorPane productUI;

    @FXML
    private TableView<product> table_product;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_name;

    @FXML
    private TableColumn<?, ?> col_type;

    @FXML
    private TableColumn<?, ?> col_size;

    @FXML
    private TableColumn<?, ?> col_outprice;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_edit;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXButton btn_refresh;

    @FXML
    private JFXTextField txt_search;

    @FXML
    private ImageView btn_search;

    @FXML
    private JFXTextField txt_id;

    @FXML
    private JFXTextField txt_name;

    @FXML
    private JFXTextField txt_type;

    @FXML
    private JFXTextField txt_size;

    @FXML
    private JFXTextField txt_inprice;

    @FXML
    private JFXTextField txt_outprice;

    @FXML
    void btn_search_Clicked(MouseEvent event) {

    }

}
