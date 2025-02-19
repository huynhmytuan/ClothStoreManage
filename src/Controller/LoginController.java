package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Model.Login;
import Model.LoginUtil;
import Task.SoundTrack;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginController  implements Initializable{
	public final Login curUser = new Login();
	static int userID = 0;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXButton btnRegister;
    
    @FXML
    private ImageView btnClosed;
     
    @FXML
     private JFXTextField tbUsername;

    @FXML
     private JFXPasswordField tbPassword;

    @FXML
    public void btnRegister_Clicked (MouseEvent event) throws IOException {
    	LoadScene loader = new LoadScene();
    	String frmName = "RegisterUI.fxml";
    	loader.loadSence(frmName, event);
    }
    
    @FXML
    private JFXButton btnLogin;
    
    @FXML
    public void btnLogin_Clicked(Event event) {
    	String username = tbUsername.getText();
        String pass = String.valueOf(tbPassword.getText());
        if("".equals(username) || "".equals(pass)){
            Alert a = new Alert(AlertType.WARNING, "Username or password can't be empty!");
            a.show();
        }
        else{
            ResultSet rs = null;
            try{
            	LoginUtil lu = new LoginUtil();
                rs = lu.getLogin(username, pass);
                if(rs.next()){
                	curUser.setLogin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),rs.getInt(5));
                	userID = curUser.getUserID();
        	        if(rs.getInt(4)== 1) {
        	        	String frmName = "DashboardUI.fxml";
        	        	 LoadScene loader = new LoadScene();
        	        	 String path = "src\\Music\\welcome-bg-music.wav";
        	        	 SoundTrack welcome = new SoundTrack(path);
                         welcome.start();
                         loader.loadSence(frmName, event);
        	        }
        	        else {
        	        	String frmName = "DashboardStaffUI.fxml";
        	        	 LoadScene loader = new LoadScene();
        	        	 String path = "src\\Music\\welcome-bg-music.wav";
        	        	 SoundTrack welcome = new SoundTrack(path);
                         welcome.start();
                         loader.loadSence(frmName, event);
                         
        	        }
                }
                else{
                	Alert a = new Alert(AlertType.WARNING, "Username or password is incorect!");
                	String path = "src\\Music\\error-noti-sound.wav";
   	        	 	SoundTrack error = new SoundTrack(path);
                    error.start();
                    a.show();
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "Database Error!" + e.getMessage());
            }
        }
    }

    @FXML
    void Login_Pressed(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		btnLogin_Clicked(event);
    	}
    }
    
    @FXML
    public void btnClosed_Clicked(MouseEvent event) {
    	System.exit(0);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
    
}
