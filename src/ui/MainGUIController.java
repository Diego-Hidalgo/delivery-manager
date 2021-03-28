package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;

public class MainGUIController {
	
	@FXML private Pane mainPanel;
	private DeliveryManagerController DMC;
	private EmergentGUIController EGC;
	public MainGUIController(DeliveryManagerController DMC,EmergentGUIController EGC){
		this.DMC = DMC;
		this.EGC = EGC;
	}//End constructor
	
	@FXML
	public void showSceneLogin() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("LoginWindows.fxml"));
		fxml.setController(this);
		Parent loginWindow = fxml.load();
		mainPanel.getChildren().setAll(loginWindow);
		Stage st = (Stage) loginWindow.getScene().getWindow();
		st.setHeight(400);
		st.setWidth(366);
		st.setResizable(false);
	}//End showSceneLogin
	@FXML
	public void show() throws IOException{
		EGC.showRegisterIngredienteScene();
	}
}//End MainGUIController
