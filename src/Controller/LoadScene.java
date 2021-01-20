package Controller;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoadScene {
	double xOffset, yOffset;
	public void loadSence(String formName, MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../View/"+formName));
    	Scene scence = new Scene(root);
    	Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	scence.setFill(Color.TRANSPARENT);
        window.setScene(scence);
        window.centerOnScreen();
        window.show();
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				window.setX(event.getScreenX() - xOffset);
				window.setY(event.getScreenY() - yOffset);
			}
		});
	}
}

