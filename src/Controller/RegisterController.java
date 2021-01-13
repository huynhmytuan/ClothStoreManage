package Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Model.LoginUtil;
import Model.User;
import Model.UserUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class RegisterController implements Initializable{
		
		@FXML
	    private AnchorPane rootpane;

	    @FXML
	    private JFXButton btnRegister;

	    @FXML
	    private JFXTextField tbUsername;

	    @FXML
	    private JFXTextField tbEmail;

	    @FXML
	    private JFXTextField tbPhone;

	    @FXML
	    private JFXPasswordField tbPassword;

	    @FXML
	    private JFXButton btnLoginNow;

	    @FXML
	    private ImageView btnClosed_RegisUI;

	    @FXML
	    private JFXTextField tbUserID;

	    @FXML
	    private JFXPasswordField tbConfirmPassword;

	    @FXML
	    private DatePicker txtDate;
	    
	    @FXML
	    public void btnRegister_Clicked(MouseEvent event) {
	    	
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