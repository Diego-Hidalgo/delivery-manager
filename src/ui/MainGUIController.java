package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

public class MainGUIController {

	@FXML private BorderPane mainPane;
	@FXML private BorderPane secondaryPane;
	private DeliveryManagerController DMC;
	private EmergentGUIController EGC;

	public MainGUIController(DeliveryManagerController DMC,EmergentGUIController EGC){
		this.DMC = DMC;
		this.EGC = EGC;
	}//End constructor

	@FXML
	public void showLoginWindows() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("LoginWindows.fxml"));
		fxml.setController(this);
		Parent loginScene = fxml.load();
		mainPane.getChildren().setAll(loginScene);
		Stage st = (Stage) loginScene.getScene().getWindow();
		st.setHeight(343);
		st.setWidth(338);
		st.setResizable(false);
	}//End showLoginWindows

}//End MainGUIController