package Controller;

import java.awt.TextArea;
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
    public ChatController() {
		// TODO Auto-generated constructor stub
//        taChatBox.setEditable(false);
    	
	}
    @FXML
    private static TextArea taChatBox;

    @FXML
    private JFXTextField txtChatText;

    @FXML
    private JFXButton btnSend;

    @FXML
    private JFXTextField ohNO;

	@FXML
    void btnSend_Clicked(Event event) {
    	User curUser = uu.getUserByID(ID);
    	String name = curUser.getUserName();
    	String str = "Kaleen Bhaiya\n"+txtChatText.getText();
//    	ohNO.setText(str);
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
    		System.out.println("Received 1 ");
    		String msg = "";
            while((msg = reader.readLine()) != null){
            	msg = reader.readLine();
            	ohNO.setText(msg);
//                taChatBox.append(msg + "\n");
            }
        }catch(Exception e){}
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		taChatBox.setText("Hello chat");

//    	taChatBox.setText("Hello");
		try{
            
            Socket socketClient = new Socket("localhost", 2003);
            writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        }catch(Exception e){}
		ChatController chat = new ChatController();
		Thread a = new Thread(chat);
		a.start();
	}

	

}
