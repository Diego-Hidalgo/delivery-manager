package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.DeliveryManagerController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main extends Application{

	private DeliveryManagerController DMC;
	private MainGUIController MGC;
	private EmergentGUIController EGC;
	private final String FOLDER = "fxml/";
	private final String ICONPATH = "/ui/ico/logo.jpg";
	private static final String SAVE_PATH = "src/save-files/save-file.dm";

	public Main() {
		try {
			loadAllData();
		} catch(IOException | ClassNotFoundException e) {
			DMC = new DeliveryManagerController();
		}
		EGC = new EmergentGUIController(DMC);
		MGC = new MainGUIController(DMC, EGC);
	}//End Constructor

	public static void main(String[] args){
		launch(args);
	}//End main

	public void loadAllData() throws IOException, ClassNotFoundException {
		File f = new File(SAVE_PATH);
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			DMC = (DeliveryManagerController)ois.readObject();
			ois.close();
		}//End if
	}//End loadAllData

	@Override
	public void start(Stage window) throws Exception {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"MainWindows.fxml"));
		fxml.setController(MGC);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		window.setTitle("Bienvenido");
		window.setScene(scene);
		window.getIcons().add(new Image(ICONPATH));
		window.show();
		MGC.showFirstScene();
	}//End start

}//End Main