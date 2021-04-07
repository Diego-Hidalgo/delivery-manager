package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DeliveryManagerController;

import java.io.File;

public class Main extends Application{

	private DeliveryManagerController DMC;
	private MainGUIController MGC;
	private EmergentGUIController EGC;
	private final String FOLDER = "fxml/";

	public Main(){
		DMC = new DeliveryManagerController();
		EGC = new EmergentGUIController(DMC);
		MGC = new MainGUIController(DMC,EGC);
	}//End Constructor

	public static void main(String[] args){
		launch(args);
	}//End main

	@Override
	public void start(Stage window) throws Exception {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"MainWindows.fxml"));
		fxml.setController(MGC);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		window.setTitle("Bienvenido");
		window.setScene(scene);
		window.show();
		DMC.loadAllData();
		MGC.showFirstScene();
	}//End start

}//End Main