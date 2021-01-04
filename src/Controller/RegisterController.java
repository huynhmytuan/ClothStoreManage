package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RegisterController implements Initializable{

    @FXML
    private AnchorPane rootpane;

    @FXML
    private JFXButton btnRegister;

    @FXML
    private JFXButton btnLoginNow;

    @FXML
    private ImageView btnClosed_RegisUI;
    
    @FXML
    void btnRegister_Clicked(MouseEvent event) {

    }
    
    @FXML
    void btnClosed_Clicked(MouseEvent event) {
    	System.exit(0);
    }
    
    @FXML
    void btnLoginNow(MouseEvent event) throws IOException {
    	LoadScene loader = new LoadScene();
    	String frmName = "LoginUI.fxml";
    	loader.loadSence(frmName, event);
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
}