package ui;

import java.io.IOException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

public class MainGUIController{
	@FXML private TableView<Product> productTable;
	@FXML private TableColumn<Product,String> productName;
	@FXML private TableColumn<Product,String> productType;
	@FXML private TableColumn<Product,String> productSize;
	@FXML private TableColumn<Product,Double> productPrice;
	@FXML private TableColumn<Product,String> productIngredients;
	@FXML private ComboBox<String> cbStatus;

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
	@FXML private MenuBar menuBar;
	//Employee
	@FXML private TextField employeeNameTxt;
	@FXML private TextField employeeLastNameTxt;
	@FXML private TextField employeeIdTxt;
	@FXML private TableView<Employee> employeesTable;
	@FXML private TableColumn<Employee, String> employeeNameColumn;
	@FXML private TableColumn<Employee, String> employeeLastNameColumn;
	@FXML private TableColumn<Employee, String> employeeIdColumn;
	@FXML private TableColumn<Employee, String> employeeCreatorColumn;
	@FXML private TableColumn<Employee, String> employeeModifierColumn;
	//User
	@FXML private Button goBackBtn;
	@FXML private Label welcomeLabel;
	@FXML private TextField userIdTxt;
	@FXML private TextField userNameTxt;
	@FXML private PasswordField userPasswordTxt;
	@FXML private PasswordField passwordConfirmationTxt;
	@FXML private TableView<User> usersTable;
	@FXML private TableColumn<User, String> userNameColumn;
	@FXML private TableColumn<User, String> userLastNameColumn;
	@FXML private TableColumn<User, String> userIdColumn;
	@FXML private TableColumn<User, String> userUserNameColumn;
	@FXML private TableColumn<User, String> userCreatorColumn;
	@FXML private TableColumn<User, String> userModifierColumn;
	//Customer
	@FXML private TextField customerNameTxt;
	@FXML private TextField customerLastNameTxt;
	@FXML private TextField customerIdTxt;
	@FXML private TextField customerAddressTxt;
	@FXML private TextField customerPhoneTxt;
	@FXML private TextArea customerRemarkTxt;
	@FXML private TableView<Customer> customersTable;
	@FXML private TableColumn<Customer, String> customerNameColumn;
	@FXML private TableColumn<Customer, String> customerLastNameColumn;
	@FXML private TableColumn<Customer, String> customerIdColumn;
	@FXML private TableColumn<Customer, String> customerAddressColumn;
	@FXML private TableColumn<Customer, String> customerPhoneColumn;
	@FXML private TableColumn<Customer, String> customerCreatorColumn;
	@FXML private TableColumn<Customer, String> customerModifierColumn;

	private DeliveryManagerController DMC;
	private EmergentGUIController EGC;
	private ObservableList<String> status;
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
	public void successfulActionAlert(String msg) throws IOException {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Acción exitosa");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End successfulActionAlert

	@FXML
	public void emptyFieldAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Campo Vacío");
		alert.setHeaderText("DEBEN LLENARSE TODOS LOS CAMPOS");
		alert.setContentText("Rellene todos los campos y vuelva a intentarlo");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End emptyFieldAlert

	@FXML
	public void idAlreadyInUseAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Id No Disponible");
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
		Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setTitle("Registrar empleado");
		stage.setHeight(450);
		stage.setResizable(false);
	}//End showRegisterEmployeesWindow

	@FXML
	public void showRegisterEmployeesSceneInSecondaryPane() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"RegisterEmployeeWindows.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		secondaryPane.setCenter(registerScene);
		Stage stage = (Stage) secondaryPane.getScene().getWindow();
		stage.setTitle("Registrar empleado");
		stage.setHeight(450);
		stage.setResizable(false);
	}//End showRegisterEmployeesSceneInSecondaryPane

	@FXML
	public void addEmployee() throws IOException {
		String name = employeeNameTxt.getText();
		String lastName = employeeLastNameTxt.getText();
		String id = employeeIdTxt.getText();
		if(DMC.validateBlankChars(name) && DMC.validateBlankChars(lastName) && DMC.validateBlankChars(id)) {
			if(DMC.searchEmployeePosition(id) == -1) {
				DMC.addEmployee(name, lastName, id);
				employeeNameTxt.clear();
				employeeLastNameTxt.clear();
				employeeIdTxt.clear();
				successfulActionAlert("Empleado registrado correctamente");
				if(DMC.getLoggedUser() == null) {
					showLoginScene();
				}
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
		Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setTitle("Registrar usuario");
		stage.setHeight(500);
		if(DMC.getAmountEmployees() == 0 && DMC.getLoggedUser() == null) {
			goBackBtn.setDisable(true);
		}//End else
		stage.setResizable(false);
	}//End showRegisterFirstUserScene

	@FXML
	public void showRegisterUserSceneInSecondaryPane() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"RegisterUserWindows.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		secondaryPane.setCenter(registerScene);
		Stage stage = (Stage) secondaryPane.getScene().getWindow();
		stage.setTitle("Registrar usuario");
		stage.setHeight(500);
		if(DMC.getLoggedUser() != null) {
			goBackBtn.setDisable(true);
		}//End if
		stage.setResizable(false);
	}//End showRegisterUserSceneInSecondaryPane

	@FXML
	public void passwordMisMatchAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Verificar Contraseñas");
		alert.setHeaderText("LAS CONTRASEÑAS NO COINCIDEN");
		alert.setContentText("Las contraseñas deben ser iguales, vuelva a intentarlo");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End passwordMisMatchAlert

	@FXML
	public void idNotFoundAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("La Id No Se Encuentra");
		alert.setHeaderText("LA ID INGRESADA NO EXISTE");
		alert.setContentText("La id ingresada no coincide con ningún empleado, intente con otra o cree un nuevo empleado");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End idNotFoundAlert

	@FXML
	public void userNameAlreadyInUseAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Nombre No Disponible");
		alert.setHeaderText("EL NOMBRE DE USUARIO YA ESTA EN USO");
		alert.setTitle("");
		alert.setContentText("Vuelva a intentarlo con un nuevo nombre");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End userNameAlreadyInUseAlert

	@FXML
	public void employeeAlreadyHasAnUserAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Empleado Ya Vinculado");
		alert.setHeaderText("EL EMPLEADO YA TIENE UN USUARIO ASOCIADO");
		alert.setContentText("Vuelva a intentarlo con un nuevo empleado");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End employeeAlreadyHasAnUserAlert

	@FXML
	public void passwordTooShortAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Contraseña inválida");
		alert.setHeaderText("LA CONTRASEÑA ES DEMASIADO CORTA");
		alert.setContentText("La contraseña debe tener por lo menos 7 caracteres, intente con otra");
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End passwordTooShortAlert

	@FXML
	public void addUser() throws IOException {
		String userId = userIdTxt.getText();
		String userName = userNameTxt.getText();
		String password = userPasswordTxt.getText();
		String pwConfirmation = passwordConfirmationTxt.getText();
		if(DMC.validateBlankChars(userId) && DMC.validateBlankChars(userName) && DMC.validateBlankChars(password)) {
			if(DMC.searchEmployeePosition(userId) != -1) {
				if(DMC.searchUserPosition(userId) == -1) {
					if (password.equals(pwConfirmation)) {
						if(password.length() >= 7) {
							if (!DMC.validateUserName(userName)) {
								DMC.addUser(userId, userName, password);
								userIdTxt.clear();
								userNameTxt.clear();
								userPasswordTxt.clear();
								passwordConfirmationTxt.clear();
								successfulActionAlert("Usuario registrado correctamente");
								if (DMC.getAmountUsers() == 1) {
									showLoginScene();
								}//End if
							} else {
								userNameAlreadyInUseAlert();
							}//End else
						} else {
							passwordTooShortAlert();
						}
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
		Stage stage = (Stage) mainPane.getScene().getWindow();
		stage.setTitle("Iniciar sesión");
		stage.setHeight(440);
		stage.setResizable(false);
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
		}//End else
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
		}//End else
	}//End confirmationAlert

	@FXML
	public void showAddCustomerScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"RegisterCustomerWindows.fxml"));
		fxmlLoader.setController(this);
		Parent registerScene = fxmlLoader.load();
		secondaryPane.setCenter(registerScene);
		Stage stage = (Stage) secondaryPane.getScene().getWindow();
		stage.setTitle("Registrar empleado");
		stage.setHeight(520);
		stage.setResizable(false);
	}//End showAddEmployeeScene

	@FXML
	public void addCustomer() throws IOException {
		String name = customerNameTxt.getText();
		String lastName = customerLastNameTxt.getText();
		String id = customerIdTxt.getText();
		String address = customerAddressTxt.getText();
		String nPhone = customerPhoneTxt.getText();
		String remark = customerRemarkTxt.getText();
		if(DMC.validateBlankChars(name) && DMC.validateBlankChars(lastName) &&
		DMC.validateBlankChars(id) && DMC.validateBlankChars(address) &&
				DMC.validateBlankChars(nPhone) && DMC.validateBlankChars(remark)) {
			if(DMC.searchCustomerPosition(id) == -1) {
				DMC.addCustomer(name, lastName, id, address, nPhone, remark);
				successfulActionAlert("Cliente registrado correctamente");
				customerNameTxt.clear();
				customerLastNameTxt.clear();
				customerIdTxt.clear();
				customerAddressTxt.clear();
				customerPhoneTxt.clear();
				customerRemarkTxt.clear();
			} else {
				idAlreadyInUseAlert();
			}//End else
		} else {
			emptyFieldAlert();
		}//End else
	}//End addCustomer

	@FXML
	public void showVisualizeEmployees() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"VisualizeEmployeesWindows.fxml"));
		fxmlLoader.setController(this);
		Parent visualizer = fxmlLoader.load();
		secondaryPane.setCenter(visualizer);
		Stage stage = (Stage) secondaryPane.getScene().getWindow();
		stage.setTitle("Lista De Empleados");
		stage.setWidth(700);
		stage.setHeight(510);
		setEmployeesTable();
		stage.setResizable(false);
	}//End showVisualizeEmployees

	@FXML
	public void setEmployeesTable() {
		ObservableList<Employee> content = FXCollections.observableArrayList(DMC.getEmployees());
		employeesTable.setItems(content);
		employeeNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		employeeLastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
		employeeIdColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
		employeeCreatorColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("creatorName"));
		employeeModifierColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("modifierName"));
	}//End setEmployeesTable

	@FXML
	public void showVisualizeUsers() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"VisualizeUsersWindows.fxml"));
		fxmlLoader.setController(this);
		Parent visualizer = fxmlLoader.load();
		secondaryPane.setCenter(visualizer);
		Stage stage = (Stage) secondaryPane.getScene().getWindow();
		stage.setTitle("Lista De Usuarios");
		stage.setWidth(800);
		stage.setHeight(510);
		setUsersTable();
		stage.setResizable(false);
	}//End showVisualizeUsers

	@FXML
	public void setUsersTable() throws IOException {
		ObservableList<User> content = FXCollections.observableArrayList(DMC.getUsers());
		usersTable.setItems(content);
		userNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
		userLastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		userIdColumn.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
		userUserNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
		userCreatorColumn.setCellValueFactory(new PropertyValueFactory<User, String>("creatorName"));
		userModifierColumn.setCellValueFactory(new PropertyValueFactory<User, String>("modifierName"));
	}//End setUsersTable

	@FXML
	public void showVisualizeCustomers() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"VisualizeCustomersWindows.fxml"));
		fxmlLoader.setController(this);
		Parent visualizer = fxmlLoader.load();
		secondaryPane.setCenter(visualizer);
		Stage stage = (Stage) secondaryPane.getScene().getWindow();
		stage.setTitle("Lista De Clientes");
		stage.setWidth(950);
		stage.setHeight(510);
		setCustomersTable();
		stage.setResizable(false);
	}//End showVisualizeCustomers

	@FXML
	public void setCustomersTable() throws IOException {
		ObservableList<Customer> content = FXCollections.observableArrayList(DMC.getCustomers());
		customersTable.setItems(content);
		customerNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
		customerLastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
		customerIdColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
		customerAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
		customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("NPhone"));
		customerCreatorColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("creatorName"));
		customerModifierColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("modifierName"));
	}//End setCustomerTable

	@FXML
	public void showSceneLogin() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"PantallaDePruebas.fxml"));
		fxml.setController(this);
		Parent loginScene = fxml.load();
		secondaryPane.setCenter(loginScene);
		Stage st = (Stage) secondaryPane.getScene().getWindow();
		st.setHeight(400);
		st.setWidth(500);
		st.setResizable(false);
	}//End showSceneLogin

	@FXML
	public void showSceneRegisterProduct() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"RegisterProductWindows.fxml"));
		fxml.setController(this);
		Parent registerProduct = fxml.load();
		secondaryPane.setCenter(registerProduct);
		Stage st = (Stage) secondaryPane.getScene().getWindow();
		st.setTitle("Registrar productos");
		st.setHeight(570);
		st.setWidth(700);
		st.setResizable(false);
	}//End showSceneRegisterProduct

	@FXML
	public void showProductsList() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"VisualizeProductsWindows.fxml"));
		fxml.setController(this);
		Parent productsListScene = fxml.load();
		secondaryPane.setCenter(productsListScene);
		initializeProductsList();
		Stage st = (Stage) secondaryPane.getScene().getWindow();
		st.setTitle("Lista de pedidos");
		st.setHeight(450);
		st.setWidth(700);
		st.setResizable(false);
	}//End showSceneLogin
	@FXML
	public void showSceneRegisterOrder() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"RegisterOrderWindows.fxml"));
		fxml.setController(this);
		Parent registerOrder = fxml.load();
		secondaryPane.setCenter(registerOrder);
		initializeStatusComboBox();
		Stage st = (Stage) secondaryPane.getScene().getWindow();
		st.setTitle("Registrar pedido");
		st.setHeight(560);
		st.setWidth(560);
		st.setResizable(false);
	}//End showSceneLogin
	@FXML
	public void addProduct() throws IOException{
		Alert addInfo = new Alert(Alert.AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "No se ha podido agregar el producto, llena todos los campos.";
		if( !tProductName.getText().isEmpty() && !tDishtype.getText().isEmpty() 
				&& !tSizesAndPices.getText().isEmpty() && !tIngredients.getText().isEmpty()){
			boolean added = DMC.addProduct(tProductName.getText(),getIngredientsToAdd(),getPrices(),getSizes(),tDishtype.getText());
			msg = (added)?"Se ha agregado exitosamente.":"Ya existe un producto con ese nombre.";
			tProductName.setText("");
			tDishtype.setText("");
			tSizesAndPices.setText("");
			tIngredients.setText("");
		}//End if
		addInfo.setContentText(msg);
		addInfo.showAndWait();
	}//End addProduct

	@FXML
	public void getSizeAndPriceFromAddSizeAndPriceEmergent() throws IOException{
		Alert addInfo = new Alert(Alert.AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "El tamaño y precio ingresado ya existen para este producto";
		EGC.showAddSizeAndPriceScene();
		String sizesAndPrices = tSizesAndPices.getText();
		String sizeAndPrice = (!EGC.getSize().isEmpty())?EGC.getSize()+ "-" + EGC.getPrice():"";
		if(!checkSizeAndPrice(sizeAndPrice)){
			sizesAndPrices += (tSizesAndPices.getText().isEmpty())?sizeAndPrice:"\n"+sizeAndPrice;
			tSizesAndPices.setText(sizesAndPrices);
			msg = "Tamañoo y  precio agregados con exito";
		}//End if
		addInfo.setContentText(msg);
		addInfo.showAndWait();
	}//End showAddSizeEmergentScene

	@FXML
	public void getIngredientsFromAddIngredientsToProduct() throws IOException{
		Alert addInfo = new Alert(Alert.AlertType.INFORMATION);
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
	}//End getIngredientsToAdd

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
	public void showSceneRegisterIngredient() throws IOException{
		EGC.showRegisterIngredienteScene();
	}//End showSceneRegisterIngredient

	@FXML
	public void showSceneRegisterDishtype() throws IOException{
		EGC.showRegisterDihstypeScene();
	}//End showSceneRegisterDishtype
	@FXML
	public void ListenChangeProductEvent(MouseEvent mouseEvent) throws IOException{
		if(mouseEvent.getClickCount() == 2){
			Product p = productTable.getSelectionModel().getSelectedItem();
			EGC.showChangeProducts(p);
			DMC.changeProduct(p,EGC.getProductName(),Arrays.asList(EGC.getIngredientToadd().split("\n")),EGC.getPrice(),EGC.getSize(),EGC.getProductType());
		}//End if
	}//End ListenChangeProductEvent
	public void initializeProductsList(){
		ObservableList<Product> productsList = FXCollections.observableArrayList(DMC.getProducts());
		productTable.setItems(productsList);
		productName.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
		productType.setCellValueFactory(new PropertyValueFactory<Product,String>("type"));
		productSize.setCellValueFactory(new PropertyValueFactory<Product,String>("size"));
		productPrice.setCellValueFactory(new PropertyValueFactory<Product,Double>("price"));
		productIngredients.setCellValueFactory(new PropertyValueFactory<Product,String>("ingredients"));
	}//End initializeProductsList
	private void initializeStatusComboBox(){
		status = FXCollections.observableArrayList();
		status.add("SOLICITADO");
		status.add("EN_PROCESO");
		status.add("ENVIADO");
		status.add("ENTREGADO");
		cbStatus.setItems(status);
	}//End initializeIngredientsComboBox
}//End MainGUIController