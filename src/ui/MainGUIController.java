package ui;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

public class MainGUIController{
	@FXML private TextField tProductName;
	@FXML private TextField tDishtype;
	@FXML private TextArea tSizesAndPices;
	@FXML private TextArea tIngredients;
	//Login
	@FXML private TextField logInName;
	@FXML private PasswordField logInPassword;
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
			showRegisterEmployeesScene();
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
	public void showRegisterEmployeesScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"RegisterEmployeeWindows.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(registerScene);
	}//End showRegisterEmployeesWindow

	@FXML
	public void addEmployee() throws IOException {
		String name = employeeNameTxt.getText();
		String lastName = employeeLastNameTxt.getText();
		String id = employeeIdTxt.getText();
		if(!name.isEmpty() && !lastName.isEmpty() && !id.isEmpty()) {
			if(DMC.searchEmployeePosition(id) == -1) {
				DMC.addEmployee(name, lastName, id);
				if(DMC.getAmountEmployees() == 1) {
					showRegisterUserScene();
				}//End if
			} else {
				idAlreadyInUseAlert();
			}//End else
		} else {
			emptyFieldAlert();
		}//End else
	}//End addEmployee

	@FXML
	public void showRegisterUserScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"RegisterUserWindows.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(registerScene);
	}//End showRegisterFirstUserScene

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
		alert.setHeaderText("EL NOMBRE DE USUARIO YA ESTa EN USO");
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
	public void logInUser() {
		String userName = logInName.getText();
		String password = logInPassword.getText();
		if(!userName.isEmpty() && !password.isEmpty()) {
			if (DMC.validateCredentials(userName, password)) {
				DMC.setLoggedUser(userName);
			} else {
				incorrectCredentials();
			}//End else
		} else {
			emptyFieldAlert();
		}
	}//End logInUser

	@FXML
	public void logOutUser() {
		DMC.logOutUser();
	}//End logOutUser

	@FXML
	public void showSceneLogin() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"PantallaDePruebas.fxml"));
		fxml.setController(this);
		Parent loginScene = fxml.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(loginScene);
		Stage st = (Stage) loginScene.getScene().getWindow();
		st.setHeight(400);
		st.setWidth(366);
		st.setResizable(false);
	}//End showSceneLogin

	@FXML
	public void showSceneRegisterProduct() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"RegisterProductWindows.fxml"));
		fxml.setController(this);
		Parent registerProduct = fxml.load();
		mainPane.getChildren().clear();
		mainPane.setCenter(registerProduct);
		Stage st = (Stage) registerProduct.getScene().getWindow();
		st.setTitle("Registrar productos");
		st.setHeight(570);
		st.setWidth(440);
		st.setResizable(false);
	}//End showSceneRegisterProduct
	@FXML
	public void addProduct() throws IOException{
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "No se ha podido agregar el producto, llena todos los campos.";
		if( !tProductName.getText().isEmpty() && !tDishtype.getText().isEmpty() 
				&& !tSizesAndPices.getText().isEmpty() && !tIngredients.getText().isEmpty()){
			boolean added = DMC.addProduct(tProductName.getText(),getIngredientsToAdd(),getPrices(),getSizes(),tDishtype.getText());
			msg = (added)?"Se ha agregado exitosamente.":"Ya existe un producto con ese nombre.";
		}//End if
		addInfo.setContentText(msg);
	}//End addProduct
	@FXML
	public void getSizeAndPriceFromAddSizeAndPriceEmergent() throws IOException{
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "El tama�o y precio ingresado ya existen para este producto";
		EGC.showAddSizeAndPriceScene();
		String sizesAndPrices = tSizesAndPices.getText();
		String sizeAndPrice = (!EGC.getSize().isEmpty())?EGC.getSize()+ "-" + EGC.getPrice():"";
		if(!checkSizeAndPrice(sizeAndPrice)){
			sizesAndPrices += (tSizesAndPices.getText().isEmpty())?sizeAndPrice:"\n"+sizeAndPrice;
			tSizesAndPices.setText(sizesAndPrices);
			msg = "Tama�o y  precio agregados con exito";
		}//End if
		addInfo.setContentText(msg);
		addInfo.showAndWait();
	}//End showAddSizeEmergentScene
	@FXML
	public void getIngredientsFromAddIngredientsToProduct() throws IOException{
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "El ingrediente elegido ya se encuentra en la lista de ingredientes";
		EGC.showAddIngredientToProductScene();
		String ingredientSelected = EGC.getIngredientToadd();
		String currentIngredients = new String();
		if(!checkIngredientToAdd(ingredientSelected) && !ingredientSelected.isEmpty()){
			currentIngredients += (tIngredients.getText().isEmpty())?ingredientSelected:tIngredients.getText()+"\n"+ingredientSelected;
			tIngredients.setText(currentIngredients);
			msg = "Se agrego el ingrediente";
		}//End if
		addInfo.setContentText(msg);
		addInfo.showAndWait();
	}//End getIngredientsFromAddIngredientsToProduct
	private boolean checkIngredientToAdd(String toCheck){
		boolean exist = false;
		String[] ingredients = tIngredients.getText().split("\n");
		for(int i = 0; i < ingredients.length && !exist; i++){
			if(toCheck.equalsIgnoreCase(ingredients[i]))
				exist = true;
		}//End for
 		return exist;
	}//End checkSizeAndPrice
	private List<String> getIngredientsToAdd(){
		return Arrays.asList(tIngredients.getText().split("\n"));
	}
	private List<String> getSizes(){
		List<String> sizes = new ArrayList<String>();
		String[] pricesAndSizes = tSizesAndPices.getText().split("\n"); 
		for(int i = 0; i < pricesAndSizes.length; i++){
			String[] size = pricesAndSizes[i].split("-");
			sizes.add(size[0]);
		}//End for
		return sizes;
	}//End getSizes
	private List<Double> getPrices(){
		List<Double> prices = new ArrayList<Double>();
		String[] pricesAndSizes = tSizesAndPices.getText().split("\n"); 
		for(int i = 0; i < pricesAndSizes.length; i++){
			String[] price = pricesAndSizes[i].split("-");
			prices.add(Double.parseDouble(price[1]));
		}//End for
		return prices;
	}//End getPrices
	private boolean checkSizeAndPrice(String toCheck){
		boolean exist = false;
		String[] pricesAndSizes = tSizesAndPices.getText().split("\n");
		for(int i = 0; i < pricesAndSizes.length && !exist; i++){
			if(toCheck.equalsIgnoreCase(pricesAndSizes[i]))
				exist = true;
		}//End for
 		return exist;
	}//End checkSizeAndPrice
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