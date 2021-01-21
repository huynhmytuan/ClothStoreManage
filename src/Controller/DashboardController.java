package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class DashboardController implements Initializable {
	
	 @FXML
	    private BorderPane mainPane;

	    @FXML
	    private ImageView btnClosed;

	    @FXML
	    private VBox Slidebar;

	    @FXML
	    private AnchorPane btnLogout;

	    @FXML
	    private AnchorPane Content;

	    @FXML
	    void btnBillDetail_Clicked(MouseEvent event) {
	    	LoadUI("BillManagementUI.fxml");
	    }

	    @FXML
	    void btnClosed_Clicked(MouseEvent event) {
	    	System.exit(0);
	    }

	    @FXML
	    void btnCustomerUI_Clicked(MouseEvent event) {
	    	LoadUI("CustomerUI.fxml");
	    }

	    @FXML
	    void btnHome_Clicked(MouseEvent event) {
	    	mainPane.setCenter(Content);
	    }

	    @FXML
	    void btnLogOut_Clicked(MouseEvent event) throws IOException {
	 	    	LoadScene loader = new LoadScene();
	 	    	String frm = "LoginUI.fxml";
	 	    	loader.loadSence(frm, event);
	    }

	    @FXML
	    void btnProductUI_Clicked(MouseEvent event) {
	    	LoadUI("ProductUI.fxml");
	    }

	    @FXML
	    void btnUserDetail_Clicked(MouseEvent event) {
	    	LoadUI("UserManageUI.fxml");
	    }

	    @FXML
	    void btnChat_Clicked(MouseEvent event) {
	    	LoadUI("ChatUI.fxml");
	    }
	    // ham loadUI 
	    private void LoadUI(String UI) {
	    	Parent root = null;
	    	try {
				root = FXMLLoader.load(getClass().getResource("../View/"+ UI));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	mainPane.setCenter(root);
	    }
	    
	    @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	    	
	    }
}
