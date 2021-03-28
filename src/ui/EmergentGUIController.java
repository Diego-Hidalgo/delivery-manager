package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DeliveryManagerController;

public class EmergentGUIController {
	
	private DeliveryManagerController DMC;
	@FXML private TextField ingredientName;
	
	public EmergentGUIController(DeliveryManagerController DMC){
		this.DMC = DMC;
	}//End constructors
	
	@FXML
	public void showRegisterIngredienteScene() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("RegisterIngredientEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		Stage formulario = new Stage();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setScene(scene);
		formulario.showAndWait();
	}//End showRegisterIngredienteScene
	
	@FXML
	public void addIngredient(){
		String ingredientName = this.ingredientName.getText();
		if(!ingredientName.equals("") && ingredientName != null){
			Alert addInfo = new Alert(AlertType.INFORMATION);
			addInfo.setHeaderText(null);
			boolean added = DMC.addIngredient(ingredientName);
			if(added){
				addInfo.setContentText("Se ha agregado el ingrediente correctamente.");
			}else
				addInfo.setContentText("No se ha podido agregar el ingrediente, verifica el nombre.");
			addInfo.showAndWait();
		}//End if
	}//End addIngredient
}//End EmergentGUIController