package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

public class MainGUIController {

	//Pane
	@FXML private BorderPane mainPane;
	@FXML private BorderPane secondaryPane;

	//Employee
	@FXML private TextField employeeNameTxt;
	@FXML private TextField employeeLastNameTxt;
	@FXML private TextField employeeIdTxt;

	//User
	@FXML private TextField userIdTxt;
	@FXML private TextField userNameTxt;
	@FXML private PasswordField userPasswordTxt;
	@FXML private PasswordField passwordConfirmationTxt;

	//Customer
	@FXML private TextField customerNameTxt;
	@FXML private TextField customerLastNameTxt;
	@FXML private TextField customerIdTxt;
	@FXML private TextField customerAddressTxt;
	@FXML private TextField customerPhoneTxt;
	@FXML private TextField customerRemarkTxt;

	private DeliveryManagerController DMC;
	private EmergentGUIController EGC;

	private final String FOLDER = "fxml/";

	public MainGUIController(DeliveryManagerController DMC,EmergentGUIController EGC){
		this.DMC = DMC;
		this.EGC = EGC;
	}//End constructor

	@FXML
	public void showFirstScene() throws IOException {
		if(DMC.getAmountEmployees() == 0 && DMC.getAmountUsers() == 0) {
			showRegisterFirstEmployeesScene();
			showRegisterFirstUserScene();
		} else {
			showLoginScene();
		}//End else
	}//End showFirstScene

	@FXML
	public void emptyFieldAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("DEBEN LLENARSE TODOS LOS CAMPOS");
		alert.setContentText("Rellene todos los campos y vuelva a intentarlo");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End emptyFieldAlert

	@FXML
	public void idAlreadyInUseAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("LA ID YA SE ENCUENTRA EN USA");
		alert.setContentText("Prueba con una distinta y vuelva a intentar");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End idAlreadyInUseAlert

	@FXML
	public void showRegisterFirstEmployeesScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"RegisterEmployeeWindows.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(registerScene);
	}//End showRegisterEmployeesWindow

	@FXML
	public void addFirstEmployee() throws IOException {
		String name = employeeNameTxt.getText();
		String lastName = employeeLastNameTxt.getText();
		String id = employeeIdTxt.getText();
		if(!name.equals("") || !lastName.equals("") || !id.equals("")) {
			DMC.addFirstEmployee(name, lastName, id);
		} else {
			emptyFieldAlert();
		}//End else
	}//End addFirstEmployee

	@FXML
	public void addEmployee() throws IOException {
		String name = employeeNameTxt.getText();
		String lastName = employeeLastNameTxt.getText();
		String id = employeeIdTxt.getText();
		if(!name.equals("") || !lastName.equals("") || !id.equals("")) {
			if(DMC.searchEmployeePosition(id) != -1) {
				DMC.addEmployee(name, lastName, id);
			} else {
				idAlreadyInUseAlert();
			}//End else
		} else {
			emptyFieldAlert();
		}//End else
	}//End addEmployee

	@FXML
	public void showRegisterFirstUserScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"RegisterUserWindows.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(registerScene);
	}//End showRegisterFirstUserScene

	@FXML
	public void showLoginScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"LoginWindows.fxml"));
		fxmlLoader.setController(this);
		Parent loginScene = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(loginScene);
	}//End showLoginScene

	@FXML
	public void showSceneLogin() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"PantallaDePruebas.fxml"));
		fxml.setController(this);
		Parent loginScene = fxml.load();
		mainPane.getChildren().setAll(loginScene);
		Stage st = (Stage) loginScene.getScene().getWindow();
		st.setHeight(343);
		st.setWidth(338);
		st.setResizable(false);
	}//End showSceneLogin

	@FXML
	public void show() throws IOException{
		EGC.showRegisterIngredienteScene();
	}

	@FXML
	public void showdish() throws IOException{
		EGC.showRegisterDihstypeScene();
	}

	@FXML
	public void showIngredients(){
		System.out.println(DMC.getIngredients());
	}

	@FXML
	public void showDishTypes(){
		System.out.println(DMC.getDishtype());
	}

}//End MainGUIController