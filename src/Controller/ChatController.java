package Controller;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import Model.User;
import Model.UserUtil;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.*;

public class ChatController implements Initializable, Runnable{

	BufferedWriter writer;
    BufferedReader reader;
    int ID = LoginController.userID;
    UserUtil uu = new UserUtil();
    @FXML
    private static JFXTextArea taChatBox;

    @FXML
    private JFXTextField txtChatText;

    @FXML
    private JFXButton btnSend;

    @FXML
    void btnSend_Clicked(Event event) {
    	User curUser = uu.getUserByID(ID);
    	String str = curUser.getUserName() + "\n"+txtChatText.getText();
        try{
            writer.write(str);
            writer.write("\r\n");
            writer.flush();
        }catch(Exception e){}
        txtChatText.setText("");
    }

    @FXML
    void txtChatText_EnterPress(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER) {
    		btnSend_Clicked(event);
    	}
    }
    
    @Override
	public void run() {
		// TODO Auto-generated method stub
    	try{
            String msg = "";
            while((msg = reader.readLine()) != null){
            	taChatBox.appendText(msg + "\n");
            }
        }catch(Exception e){}
	}
    
    public static boolean hostAvailabilityCheck() { 
        try (Socket s = new Socket("localhost", 2003)) {
            return true;
        } catch (IOException ex) {
            /* ignore */
        }
        return false;
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(hostAvailabilityCheck()) {
			
		}
		try{ 
	           Socket socketClient = new Socket("localhost", 2003);
	           writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
	           reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
		    }catch(Exception e){}
			Thread t1 = new Thread();
	        t1.start();
		
	}

	

}
