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
	@FXML private TextField tIngredientName;
	@FXML private TextField tDishtypeName;
	private final String FOLDER = "fxml/";
	public EmergentGUIController(DeliveryManagerController DMC){
		this.DMC = DMC;
	}//End constructors
	
	@FXML
	public void showRegisterIngredienteScene() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"RegisterIngredientEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		Stage formulario = new Stage();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Agregar ingrediente");
		formulario.setScene(scene);
		formulario.setResizable(false);
		formulario.showAndWait();
	}//End showRegisterIngredienteScene
	@FXML
	public void showRegisterDihstypeScene() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"RegisterDishTypeEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		Stage formulario = new Stage();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Agregar tipo de plato");
		formulario.setScene(scene);
		formulario.setResizable(false);
		formulario.showAndWait();
	}//End showRegisterDihstypeScene
	@FXML
	public void addIngredient(){
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		addInfo.setContentText("No se ha podido agregar el ingrediente, verifica el nombre.");
		if(tIngredientName != null){
			String ingredientName = this.tIngredientName.getText();
			if(!ingredientName.equals("")){
				tIngredientName.setText("");
				boolean added = DMC.addIngredient(ingredientName);
				if(added)
					addInfo.setContentText("Se ha agregado el ingrediente correctamente.");
			}//End if
		}//End if
		addInfo.showAndWait();
	}//End addIngredient
	@FXML
	public void addDishtype(){
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		addInfo.setContentText("No se ha podido agregar el tipo de plato, verifica el nombre.");
		if(tDishtypeName != null){
			String dishtypeName = this.tDishtypeName.getText();
			if(!dishtypeName.equals("")){
				tDishtypeName.setText("");
				boolean added = DMC.addDishType(dishtypeName);
				if(added)
					addInfo.setContentText("Se ha agregado el tipo de plato correctamente.");
			}//End if
		}//End if
		addInfo.showAndWait();
	}//End addDishtype
}//End EmergentGUIController