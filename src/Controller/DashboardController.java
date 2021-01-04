package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DashboardController implements Initializable {

	    @FXML
	    private JFXButton btnProduct;

	    @FXML
	    private JFXButton btnCustomer;

	    @FXML
	    private JFXButton btnSatistics;

	    @FXML
	    private AnchorPane btnLogout;

	    @FXML
	    private JFXButton btnLogOut;

	    @FXML
	    private ImageView btnClosed;

	    @FXML
	    private AnchorPane contentView;

	    @FXML
	    void btnClosed_Clicked(MouseEvent event) {
	    	System.exit(0);
	    }

	    @FXML
	    void btnCustomer_Clicked(MouseEvent event) {

	    }

	    @FXML
	    void btnLogOut_Clicked(MouseEvent event) {

	    }

	    @FXML
	    void btnProduct_Clicked(MouseEvent event) {

	    }

	    @FXML
	    void btnStatistics_Clicked(MouseEvent event) {

	    }

	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	    	
	    }
}
