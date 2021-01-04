package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController  implements Initializable{
    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXButton btnRegister;
    
     @FXML
    private ImageView btnClosed;
     
    @FXML
    public void btnRegister_Clicked (MouseEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("../View/RegisterUI.fxml"));
    	Scene registerScence = new Scene(root);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	registerScence.setFill(Color.TRANSPARENT);
        window.setScene(registerScence);
        window.show();
    }
    
    @FXML
    private JFXButton btnLogin;
    
    @FXML
    public void btnLogin_Clicked(MouseEvent event) {
    	//code
    }

    @FXML
    public void btnClosed_Clicked(MouseEvent event) {
    	System.exit(0);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
    
}
