package ui;

import java.io.IOException;
import java.util.Optional;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

public class MainGUIController {

	//Login
	@FXML private TextField logInName;
	@FXML private PasswordField logInPassword;

	//Pane
	@FXML private BorderPane mainPane;
	@FXML private BorderPane secondaryPane;
	@FXML private MenuBar menuBar;

	//Employee
	@FXML private TextField employeeNameTxt;
	@FXML private TextField employeeLastNameTxt;
	@FXML private TextField employeeIdTxt;

	//User
	@FXML private Button goBackBtn;
	@FXML private Label welcomeLabel;
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
	public void switchToMainPane() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"MainWindows.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Stage window = (Stage) menuBar.getScene().getWindow();
		Scene scene = new Scene(root, null);
		window.setScene(scene);
		window.show();
	}//End switchToMainPane

	@FXML
	public void switchToSecondaryPane(Event e) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"SecondaryWindows.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root, null);
		window.setScene(scene);
		window.show();
		welcomeLabel.setText("Bienvenido " + DMC.getLoggedUser().getUserName() + ". Acceda al menú para usar las opciones del sistema");
	}//End switchToSecondaryPane

	@FXML
	public void showFirstScene() throws IOException {
		if(DMC.getAmountEmployees() == 0 && DMC.getAmountUsers() == 0) {
			showRegisterEmployeesSceneInMainPane();
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
		alert.setHeaderText("LA ID YA SE ENCUENTRA EN USO");
		alert.setContentText("Prueba con una distinta y vuelva a intentar");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End idAlreadyInUseAlert

	@FXML
	public void showRegisterEmployeesSceneInMainPane() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"RegisterEmployeeWindows.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(registerScene);
	}//End showRegisterEmployeesWindow

	@FXML
	public void showRegisterEmployeesSceneInSecondaryPane() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"RegisterEmployeeWindows.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		secondaryPane.setCenter(registerScene);
	}//End showRegisterEmployeesSceneInSecondaryPane

	@FXML
	public void addEmployee() throws IOException {
		String name = employeeNameTxt.getText();
		String lastName = employeeLastNameTxt.getText();
		String id = employeeIdTxt.getText();
		if(!name.isEmpty() && !lastName.isEmpty() && !id.isEmpty()) {
			if(DMC.searchEmployeePosition(id) == -1) {
				DMC.addEmployee(name, lastName, id);
				if(DMC.getAmountEmployees() == 1) {
					showRegisterUserSceneInMainPane();
				}//End if
			} else {
				idAlreadyInUseAlert();
			}//End else
		} else {
			emptyFieldAlert();
		}//End else
	}//End addEmployee

	@FXML
	public void showRegisterUserSceneInMainPane() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"RegisterUserWindows.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(registerScene);
		if(DMC.getLoggedUser() != null) {
			goBackBtn.setDisable(true);
		}//End if
	}//End showRegisterFirstUserScene

	@FXML
	public void showRegisterUserSceneInSecondaryPane() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"RegisterUserWindows.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		secondaryPane.setCenter(registerScene);
		if(DMC.getLoggedUser() != null) {
			goBackBtn.setDisable(true);
		}//End if
	}//End showRegisterUserSceneInSecondaryPane

	@FXML
	public void passwordMisMatchAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("LAS CONTRASEÑAS NO COINCIDEN");
		alert.setContentText("Las contraseñas deben ser iguales, vuelva a intentarlo");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End passwordMisMatchAlert

	@FXML
	public void idNotFoundAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("LA ID INGRESADA NO EXISTE");
		alert.setContentText("La id ingresada no coincide con ningún empleado, intente con otra o cree un nuevo empleado");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End idNotFoundAlert

	@FXML
	public void userNameAlreadyInUseAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("EL NOMBRE DE USUARIO YA ESTÁ EN USO");
		alert.setContentText("Vuelva a intentarlo con un nuevo nombre");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End userNameAlreadyInUseAlert

	@FXML
	public void employeeAlreadyHasAnUserAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText("EL EMPLEADO YA TIENE UN USUARIO ASOCIADO");
		alert.setContentText("Vuelva a intentarlo con un nuevo empleado");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End employeeAlreadyHasAnUserAlert

	@FXML
	public void addUser() throws IOException {
		String userId = userIdTxt.getText();
		String userName = userNameTxt.getText();
		String password = userPasswordTxt.getText();
		String pwConfirmation = passwordConfirmationTxt.getText();
		if(!userId.isEmpty() && !userName.isEmpty() && !password.isEmpty() && !pwConfirmation.isEmpty()) {
			if(DMC.searchEmployeePosition(userId) != -1) {
				if(DMC.searchUserPosition(userId) == -1) {
					if (password.equals(pwConfirmation)) {
						if (!DMC.validateUserName(userName)) {
							DMC.addUser(userId, userName, password);
							if (DMC.getAmountUsers() == 1) {
								showLoginScene();
							}//End if
						} else {
							userNameAlreadyInUseAlert();
						}//End else
					} else {
						passwordMisMatchAlert();
					} //End else
				} else {
					employeeAlreadyHasAnUserAlert();
				}//End else
			} else {
				idNotFoundAlert();
			}//End else
		} else {
			emptyFieldAlert();
		}//End else
	}//End addFirstUser

	@FXML
	public void showLoginScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"LoginWindows.fxml"));
		fxmlLoader.setController(this);
		Parent loginScene = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(loginScene);
	}//End showLoginScene

	@FXML
	public void incorrectCredentials() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("");
		alert.setHeaderText(null);
		alert.setContentText("Verifique las credenciales de inicio de sesión");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End incorrectCredentials

	@FXML
	public void logInUser(Event e) throws IOException {
		String userName = logInName.getText();
		String password = logInPassword.getText();
		if(!userName.isEmpty() && !password.isEmpty()) {
			if (DMC.validateCredentials(userName, password)) {
				DMC.setLoggedUser(userName);
				switchToSecondaryPane(e);
			} else {
				incorrectCredentials();
			}//End else
		} else {
			emptyFieldAlert();
		}
	}//End logInUser

	@FXML
	public void logOutUser() throws IOException {
		if(confirmActionAlert("¿Está seguro de salir del sistema?")) {
			switchToMainPane();
			showLoginScene();
			DMC.logOutUser();
		}//End if
	}//End logOutUser

	@FXML
	public boolean confirmActionAlert(String text) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmar Acción");
		alert.setHeaderText(null);
		alert.setContentText(text);
		ButtonType acceptBtn = new ButtonType("Aceptar");
		ButtonType cancelBtn = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(acceptBtn, cancelBtn);
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == acceptBtn) {
			return true;
		} else {
			return false;
		}//End confirmationAlert
	}

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