package ui;

import java.io.IOException;
import java.util.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.*;

public class MainGUIController implements Runnable{
	//Product
	@FXML private TableView<Product> productTable;
	@FXML private TableColumn<Product,String> productName;
	@FXML private TableColumn<Product,String> productType;
	@FXML private TableColumn<Product,String> productSize;
	@FXML private TableColumn<Product,Double> productPrice;
	@FXML private TableColumn<Product,String> productIngredients;
	@FXML private TextField tProductName;
	@FXML private TextField tDishtype;
	@FXML private TextArea tSizesAndPices;
	@FXML private ListView<String> lIngredients;
	@FXML private MenuItem DisableElement;
	@FXML private MenuItem removeElement;
	@FXML private MenuItem showList;
	//Ingredient
	@FXML private TableView<Ingredient> ingredientTable;
	@FXML private TableColumn<Ingredient,String> ingredientName;
	@FXML private Label listTitle;
	private String title;
	private String menuText;
	private boolean enableList;
	//DishType
	@FXML private TableView<DishType> dishTypeTable;
	@FXML private TableColumn<DishType,String> dishTypeName;
	
	//Order
	@FXML private ChoiceBox<String> cbStatus;
	@FXML private TextField tIdEmployee;
	@FXML private TextField tIdCustomer;
	@FXML private TextArea taRemark;
	@FXML private TextArea taProducsAmount;
	@FXML private TableView<Order> orderTable;
	@FXML private TableColumn<Order,String> orderCode;
	@FXML private TableColumn<Order,String> orderDate;
	@FXML private TableColumn<Order,String> orderStatus;
	@FXML private TableColumn<Order,String> orderProducts;
	@FXML private TableColumn<Order,String> orderRemark;
	@FXML private MenuItem showCompleteRegister;
	private ObservableList<String> status;
	
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
	@FXML private MenuItem disable;
	@FXML private MenuItem change;
	@FXML private MenuItem delete;

	@FXML private Thread dateThread;
	@FXML private Label dateLbl;
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minutes;
	private int seconds;

	private DeliveryManagerController DMC;
	private EmergentGUIController EGC;
	
	private final String FOLDER = "fxml/";
	private List<Product> product;
	private List<Integer> amo;

	public MainGUIController(DeliveryManagerController DMC,EmergentGUIController EGC) {
		this.DMC = DMC;
		this.EGC = EGC;
		product = new ArrayList<Product>();
		amo = new ArrayList<Integer>();
		title = new String();
		enableList = true;
		menuText = "Ver elementos no disponibles";
		dateThread = new Thread(this);
		dateThread.setDaemon(true);
	}//End constructor

	@Override
	public void run() {
		dateThread = new Thread(()-> {
			while (true) {
				Platform.runLater(() -> {
					Calendar cal = new GregorianCalendar();
					cal.setTime(new Date());
					day = cal.get(Calendar.DAY_OF_MONTH);
					month = cal.get(Calendar.MONTH) + 1;
					year = cal.get(Calendar.YEAR);
					hour = cal.get(Calendar.HOUR_OF_DAY);
					minutes = cal.get(Calendar.MINUTE);
					seconds = cal.get(Calendar.SECOND);
					dateLbl.setText(day+"/"+month+"/"+year+"  "+hour+":"+minutes+":"+seconds);
				});
				try {
					Thread.sleep(250);
				} catch (InterruptedException ie) {}
			}//End while
		});
		dateThread.start();
	}//End run

	@FXML
	public void listenCustomerMouseEvent(MouseEvent me) throws IOException {
		change.setDisable(false);
		disable.setDisable(false);
		delete.setDisable(false);
		if(me.getClickCount() == 2) {
				listenChangeCustomerEvent();
		}//End if
		if(me.getButton() == MouseButton.SECONDARY) {
			Customer selection = customersTable.getSelectionModel().getSelectedItem();
			if(selection != null) {
				if(selection.getEnabled()) {
					disable.setText("Deshabilitar");
				} else {
					disable.setText("Habilitar");
				}//End else
			} else {
				change.setDisable(true);
				disable.setDisable(true);
				delete.setDisable(true);
			}//End else
		}//End if
	}//End setCustomersTableMenuItemText

	@FXML
	public void listenEmployeeMouseEvent(MouseEvent me) throws IOException {
		change.setDisable(false);
		disable.setDisable(false);
		delete.setDisable(false);
		if(me.getClickCount() == 2) {
			listenChangeEmployeeEvent();
		}//End if
		if(me.getButton() == MouseButton.SECONDARY) {
			Employee selection = employeesTable.getSelectionModel().getSelectedItem();
			if(selection != null) {
				if(selection.getEnabled()) {
					disable.setText("Deshabilitar");
				} else {
					disable.setText("Habilitar");
				}//End else
			} else {
				change.setDisable(true);
				disable.setDisable(true);
				delete.setDisable(true);
			}//End else
		}//End if
	}//End setEmployeeContextMenuItems

	@FXML
	public void listenUserMouseEvent(MouseEvent me) throws IOException {
		change.setDisable(false);
		disable.setDisable(false);
		delete.setDisable(false);
		if(me.getClickCount() == 2) {
			listenChangeUserEvent();
		}//End if
		if(me.getButton() == MouseButton.SECONDARY) {
			User selection = usersTable.getSelectionModel().getSelectedItem();
			if(selection != null) {
				if(selection.getEnabled()) {
					disable.setText("Deshabilitar");
				} else {
					disable.setText("Habilitar");
				}//End else
			} else {
				change.setDisable(true);
				disable.setDisable(true);
				delete.setDisable(true);
			}//End else
		}//End if
	}//End setUserContextMenuItems

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
		window.setTitle("Bienvenido");
		welcomeLabel.setText("Bienvenido " + DMC.getLoggedUser().getUserName() +
	             ". Acceda al menu para usar las funciones del sistema");
		if(!dateThread.isAlive()) {
			dateThread.start();
		}//End if
		window.show();
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
		alert.setTitle("Accion exitosa");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		ButtonType confirmation = new ButtonType("ACEPTAR");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End successfulActionAlert

	@FXML
	public void emptyFieldAlert() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Campo Vacio");
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
	public void listenChangeUserEvent() throws IOException {
		if(usersTable.getSelectionModel().getSelectedItem() != null) {
			User user = usersTable.getSelectionModel().getSelectedItem();
			if(DMC.getLoggedUser() == user) {
				EGC.changeUserEmergentScene(user);
				showVisualizeUsers();
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle("Acción denegada");
				alert.setContentText("Solo el usuario original puede realizar esta acción");
				alert.showAndWait();
			}//Emd else
		}//End if
	}//End listenChangeUserEvent

	@FXML
	public void listenChangeEmployeeEvent() throws IOException {
		if(employeesTable.getSelectionModel().getSelectedItem() != null) {
			Employee employee = employeesTable.getSelectionModel().getSelectedItem();
			EGC.changeEmployeeEmergentScene(employee);
			showVisualizeEmployees();
		}//End if
	}//End listenChangeEmployeeEvent

	@FXML
	public void couldNotCompleteActionAlert(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("No se pudo completar la acción");
		alert.setContentText(msg);
		alert.showAndWait();
	}//End couldNotDisableAlert

	@FXML
	public void listenChangeEmployeeStatusEvent() throws IOException {
		String msg = "";
		if(employeesTable.getSelectionModel().getSelectedItem() != null) {
			Employee employee = employeesTable.getSelectionModel().getSelectedItem();
			if(employee.getEnabled()) {
				msg = "¿Está seguro que desea deshabilitar al empleado seleccionado? " +
						"Se deshabilitará también al usuario asociado en caso de haber uno";
			} else {
				msg = "¿Está seguro que desea habilitar al empleado seleccionado?";
			}//End else
			if(confirmActionAlert(msg)) {
				if(DMC.countEnabledUsers() == 1 && employee.getId().equals(DMC.getLoggedUser().getId())) {
					msg = "No se puede deshabilitar al empleado porque su usuario asociado es el único habilitado";
					couldNotCompleteActionAlert(msg);
				} else {
					if(DMC.changeEmployeeEnabledStatus(employee)) {
						msg = "Se ha habilitado al empleado correctamente";
					} else {
						msg = "Se ha deshabilitado al empleado correctamente";
						if(employee.getId().equals(DMC.getLoggedUser().getId())) {
							switchToMainPane();
							showLoginScene();
							DMC.logOutUser();
						}//End if
					}//End else
					successfulActionAlert(msg);
					showVisualizeEmployees();
				}//End else
			}//End if
		}//End if
	}//End listenChangeEmployeeStatusEvent

	@FXML
	public void listenChangeCustomerStatusEvent() throws IOException {
		String msg = "¿Está seguro que desea cambiar el estado del cliente?";
		if(customersTable.getSelectionModel().getSelectedItem() != null) {
			Customer customer = customersTable.getSelectionModel().getSelectedItem();
			if (confirmActionAlert(msg)) {
				if (DMC.changeCustomerEnabledStatus(customer)) {
					msg = "Se ha habilitado al cliente correctamente";
				} else {
					msg = "Se ha deshabilitado al cliente correctamente";
				}//End else
				successfulActionAlert(msg);
				showVisualizeCustomers();
			}//End if
		}//End if
	}//End listenChangeCustomerStatusEvent

	@FXML
	public void listenChangeCustomerEvent() throws IOException {
		if(customersTable.getSelectionModel().getSelectedItem() != null) {
			Customer customer = customersTable.getSelectionModel().getSelectedItem();
			EGC.changeCustomerEmergentScene(customer);
			showVisualizeCustomers();
		}//End if
	}//End listenChangeCustomerEvent

	@FXML
	public void listenChangeUserStatusEvent() throws IOException {
		String msg = "";
		if(usersTable.getSelectionModel().getSelectedItem() != null) {
			User user = usersTable.getSelectionModel().getSelectedItem();
			if(user.getEnabled()) {
				msg = "¿Está seguro que desea deshabilitar el usuario?";
			} else {
				msg = "¿Está seguro que desea habilitar el usuario? " +
						"También se habilitará al empleado asociado a la cuenta";
			}//End else
			if(confirmActionAlert(msg)) {
				if(DMC.countEnabledUsers() == 1 && user.getId().equals(DMC.getLoggedUser().getId())) {
					String info = "No se puede deshabilitar al usuario porque es el único existente";
					couldNotCompleteActionAlert(info);
				} else {
					if(DMC.changeUserEnabledStatus(user)) {
						msg = "Se ha habilitado al usuario correctamente";
					} else {
						msg = "Se ha deshabilitado al usuario correctamente";
						if(user.getId().equals(DMC.getLoggedUser().getId())) {
							switchToMainPane();
							showLoginScene();
							DMC.logOutUser();
						}//End if
					}//End else
					successfulActionAlert(msg);
					showVisualizeUsers();
				}//End else
			}//End if
		}//End if
	}//End listenChangeUserStatusEvent

	@FXML
	public void listenRemoveCustomerEvent() throws IOException {
		if(customersTable.getSelectionModel().getSelectedItem() != null) {
			Customer customer = customersTable.getSelectionModel().getSelectedItem();
			String msg = "¿Está seguro que desea remover al cliente?";
			if(confirmActionAlert(msg)) {
				if(DMC.removeCustomer(customer)) {
					msg = "Cliente removido correctamente.";
					successfulActionAlert(msg);
					showVisualizeCustomers();
				} else {
					msg = "No se pudo remover al cliente.";
					couldNotCompleteActionAlert(msg);
				}//End else
			}//End if
		}//End listenRemoveCustomerEvent
	}//End listenRemoveCustomerEvent

	@FXML
	public void listenRemoveEmployeeEvent() throws IOException {
		if(employeesTable.getSelectionModel().getSelectedItem() != null) {
			Employee employee = employeesTable.getSelectionModel().getSelectedItem();
			String msg = "¿Está seguro que desea remover al empleado?";
			if(confirmActionAlert(msg)) {
				if(DMC.removeEmployee(employee)) {
					msg = "Empleado removido correctamente.";
					successfulActionAlert(msg);
					showVisualizeEmployees();
				} else {
					msg = "No se pudo remover al empleado.";
					couldNotCompleteActionAlert(msg);
				}//End else
			}//End if
		}//End if
	}//End listenRemoveEmployeeEvent

	@FXML
	public void listenRemoveUserEvent() throws IOException {
		if(usersTable.getSelectionModel().getSelectedItem() != null) {
			User user = usersTable.getSelectionModel().getSelectedItem();
			String msg = "Está seguro que desea remover al empleado?";
			if(confirmActionAlert(msg)) {
				if(DMC.removeUser(user)) {
					showVisualizeUsers();
					if(user.getId().equals(DMC.getLoggedUser().getId())) {
						switchToMainPane();
						showLoginScene();
						DMC.logOutUser();
					}//End if
					msg = "Usuario removido correctamente.";
					successfulActionAlert(msg);
				} else {
					msg = "No se pudo remover al usuario.";
					couldNotCompleteActionAlert(msg);
				}//End else
			}//End if
		}//End if
	}//End listenRemoveUserEvent

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
		alert.setContentText("Las contrase{as deben ser iguales, vuelva a intentarlo");
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
				if(DMC.getEmployeeEnabledStatus(userId)) {
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
					entityDisabledAlert("empleado");
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
		stage.setTitle("Iniciar sesion");
		stage.setHeight(440);
		stage.setResizable(false);
	}//End showLoginScene

	@FXML
	public void incorrectCredentials() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("");
		alert.setHeaderText(null);
		alert.setContentText("Verifique las credenciales de inicio de sesion");
		ButtonType confirmation = new ButtonType("Aceptar");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End incorrectCredentials

	@FXML
	public void entityDisabledAlert(String entity) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("");
		alert.setHeaderText(null);
		alert.setContentText("No se puede realizar la accion porque el " + entity + " se encuentra deshabilitado");
		ButtonType confirmation = new ButtonType("Aceptar");
		alert.getButtonTypes().setAll(confirmation);
		alert.showAndWait();
	}//End entityDisabledAlert

	@FXML
	public void logInUser(Event e) throws IOException {
		String userName = logInName.getText();
		String password = logInPassword.getText();
		if(!userName.isEmpty() && !password.isEmpty()) {
			if (DMC.validateCredentials(userName, password)) {
				if(DMC.getUserEnabledStatus(userName)) {
					DMC.setLoggedUser(userName);
					switchToSecondaryPane(e);
				} else {
					entityDisabledAlert("usuario");
				}
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
		alert.setTitle("Confirmar Accion");
		alert.setHeaderText(null);
		alert.setContentText(text);
		ButtonType acceptBtn = new ButtonType("Aceptar");
		ButtonType cancelBtn = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(acceptBtn, cancelBtn);
		Optional<ButtonType> result = alert.showAndWait();
		return result.get() == acceptBtn;
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
				DMC.validateBlankChars(nPhone)) {
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
		stage.setHeight(550);
		title = (enableList)?"Empleados habilitados":"Empleados deshabilitados";
		menuText = (enableList)?"Ver empleados deshabilitados":"Ver empleados habilitados";
		listTitle.setText(title);
		showList.setText(menuText);
		setEmployeesTable();
		stage.setResizable(false);
	}//End showVisualizeEmployees

	@FXML
	public void setEmployeesTable() {
		ObservableList<Employee> content = FXCollections.observableArrayList(DMC.getEmployees(enableList));
		employeesTable.setItems(content);
		employeeNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		employeeLastNameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
		employeeIdColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("id"));
		employeeCreatorColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("creatorName"));
		employeeModifierColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("modifierName"));
	}//End setEmployeesTable

	public void listenChangeEmployeesTable() throws IOException {
		enableList = !enableList;
		showVisualizeEmployees();
	}//End listenChangeEmployeesTable

	@FXML
	public void showVisualizeUsers() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"VisualizeUsersWindows.fxml"));
		fxmlLoader.setController(this);
		Parent visualizer = fxmlLoader.load();
		secondaryPane.setCenter(visualizer);
		Stage stage = (Stage) secondaryPane.getScene().getWindow();
		stage.setTitle("Lista De Usuarios");
		stage.setWidth(800);
		stage.setHeight(550);
		title = (enableList)?"Usuarios habilitados":"Usuarios deshabilitados";
		menuText = (enableList)?"Ver usuarios deshabilitados":"Ver usuarios habilitados";
		listTitle.setText(title);
		showList.setText(menuText);
		setUsersTable();
		stage.setResizable(false);
	}//End showVisualizeUsers

	public void listenChangeUsersTable() throws IOException {
		enableList = !enableList;
		showVisualizeUsers();
	}//End listenChangeUsersTable

	@FXML
	public void setUsersTable() throws IOException {
		ObservableList<User> content = FXCollections.observableArrayList(DMC.getUsers(enableList));
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
		stage.setHeight(550);
		title = (enableList)?"Clientes habilitados":"Clientes deshabilitados";
		menuText = (enableList)?"Ver clientes deshabilitados":"Ver clientes habilitados";
		listTitle.setText(title);
		showList.setText(menuText);
		setCustomersTable();
		stage.setResizable(false);
	}//End showVisualizeCustomers

	@FXML
	public void setCustomersTable() throws IOException {
		ObservableList<Customer> content = FXCollections.observableArrayList(DMC.getCustomers(enableList));
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
	public void listenChangeCustomersTable() throws IOException {
		enableList = !enableList;
		showVisualizeCustomers();
	}//End listenChangeCustomersTable

	@FXML
	public void showImportDataScene() throws IOException {
		EGC.showImportScene();
	}//End showImportDataScene

	@FXML
	public void showGenerateReportScene() throws IOException {
		EGC.showExportScene();
	}//End showGenerateReportScene

	@FXML
	public void showSceneRegisterProduct() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"RegisterProductWindows.fxml"));
		fxml.setController(this);
		Parent registerProduct = fxml.load();
		secondaryPane.setCenter(registerProduct);
		Stage st = (Stage) secondaryPane.getScene().getWindow();
		st.setTitle("Registrar productos");
		st.setHeight(570);
		st.setWidth(550);
		st.setResizable(false);
	}//End showSceneRegisterProduct

	@FXML
	public void showProductsList() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"VisualizeProductsWindows.fxml"));
		fxml.setController(this);
		Parent productsListScene = fxml.load();
		secondaryPane.setCenter(productsListScene);
		initializeProductsList();
		title = (enableList)?"Productos disponibles":"Productos no disponibles";
		menuText = (enableList)?"Ver elementos no disponibles":"Ver elementos disponibles";
		listTitle.setText(title);
		showList.setText(menuText);
		Stage st = (Stage) secondaryPane.getScene().getWindow();
		st.setTitle("Lista de pedidos");
		st.setHeight(570);
		st.setWidth(800);
		st.setResizable(false);
	}//End showProductsList
	@FXML
	public void showSceneOrdersList() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"VisualizeOrdersWindows.fxml"));
		fxml.setController(this);
		Parent visualizeOrders = fxml.load();
		secondaryPane.setCenter(visualizeOrders);
		initializeOrdersList();
		title = (enableList)?"Pedidos disponibles":"Pedidos no disponibles";
		menuText = (enableList)?"Ver elementos no disponibles":"Ver elementos disponibles";
		listTitle.setText(title);
		showList.setText(menuText);
		Stage st = (Stage) secondaryPane.getScene().getWindow();
		st.setTitle("Registros de pedido");
		st.setHeight(520);
		st.setWidth(900);
		st.setResizable(false);
	}//End showSceneLogin
	@FXML
	public void showIngredientsList() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"VisualizeIngredientsWindows.fxml"));
		fxml.setController(this);
		Parent ingredientsListScene = fxml.load();
		secondaryPane.setCenter(ingredientsListScene);
		Stage st = (Stage) secondaryPane.getScene().getWindow();
		initializeIngredientsList();
		title = (enableList)?"Ingredientes disponibles":"Ingredientes no disponibles";
		menuText = (enableList)?"Ver elementos no disponibles":"Ver elementos disponibles";
		listTitle.setText(title);
		showList.setText(menuText);
		st.setTitle("Lista de ingredientes");
		st.setHeight(500);
		st.setWidth(550);
		st.setResizable(false);
	}//End showIngredientsList

	@FXML
	public void showDishTypeList() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"VisualizeDishTypeWindows.fxml"));
		fxml.setController(this);
		Parent dishTypeListScene = fxml.load();
		secondaryPane.setCenter(dishTypeListScene);
		Stage st = (Stage) secondaryPane.getScene().getWindow();
		initializeDishtypeList();
		title = (enableList)?"Tipos de platos disponibles":"Tipos de platos no disponibles";
		menuText = (enableList)?"Ver elementos no disponibles":"Ver elementos disponibles";
		listTitle.setText(title);
		showList.setText(menuText);
		st.setTitle("Lista de tipos de platos");
		st.setHeight(500);
		st.setWidth(550);
		st.setResizable(false);
	}//End showDishTypeList

	@FXML
	public void showSceneRegisterOrder() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"RegisterOrderWindows.fxml"));
		fxml.setController(this);
		Parent registerOrder = fxml.load();
		secondaryPane.setCenter(registerOrder);
		Stage st = (Stage) secondaryPane.getScene().getWindow();
		initializeStatusComboBox();
		st.setTitle("Registrar pedido");
		st.setHeight(610);
		st.setWidth(550);
		st.setResizable(false);
	}//End showSceneLogin

	@FXML
	public void addProduct() throws IOException{
		Alert addInfo = new Alert(Alert.AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "No se ha podido agregar el producto, llena todos los campos.";
		if( !tProductName.getText().isEmpty() && !tDishtype.getText().isEmpty() 
				&& !tSizesAndPices.getText().isEmpty() && lIngredients.getItems() != null){
			boolean added = DMC.addProduct(tProductName.getText(),lIngredients.getItems(),getPrices(),getSizes(),tDishtype.getText());
			msg = (added)?"Se ha agregado exitosamente.":"Ya existe un producto con ese nombre.";
			tProductName.setText("");
			tDishtype.setText("");
			tSizesAndPices.setText("");
			lIngredients.setItems(FXCollections.observableArrayList(""));
		}//End if
		addInfo.setContentText(msg);
		addInfo.showAndWait();
	}//End addProduct

	@FXML
	public void getSizeAndPriceFromAddSizeAndPriceEmergent() throws IOException{
		Alert addInfo = new Alert(Alert.AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "El tamano y precio ingresado ya existen para este producto";
		EGC.showAddSizeAndPriceScene();
		String sizesAndPrices = tSizesAndPices.getText();
		String sizeAndPrice = (!EGC.getSize().isEmpty())?EGC.getSize()+ "-" + EGC.getPrice():"";
		if(!checkSizeAndPrice(sizeAndPrice)){
			sizesAndPrices += (tSizesAndPices.getText().isEmpty())?sizeAndPrice:"\n"+sizeAndPrice;
			tSizesAndPices.setText(sizesAndPrices);
			msg = "Tamano y  precio agregados con exito";
		}//End if
		addInfo.setContentText(msg);
		addInfo.showAndWait();
	}//End showAddSizeEmergentScene

	@FXML //tIngredients
	public void getIngredientsFromAddIngredientsToProduct() throws IOException{
		Alert addInfo = new Alert(Alert.AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "El ingrediente elegido ya se encuentra en la lista de ingredientes";
		EGC.showAddIngredientToProductScene();
		String ingredientSelected = EGC.getIngredientToadd();
		ObservableList<String> currentIngredients = FXCollections.observableList(lIngredients.getItems());
		if(!ingredientSelected.equals("")){
			if(!checkIngredientToAdd(ingredientSelected)){
				currentIngredients.add(ingredientSelected);
				lIngredients.setItems(currentIngredients);
				msg = "Se agrego el ingrediente";
			}//End if
		}else
			msg = "No se selecciono ningun ingrediente";
		addInfo.setContentText(msg);
		addInfo.showAndWait();
	}//End getIngredientsFromAddIngredientsToProduct

	private boolean checkIngredientToAdd(String toCheck){
		boolean exist = false;
		ObservableList<String> currentIngredients = FXCollections.observableList(lIngredients.getItems());
		for(int i = 0; i < currentIngredients.size() && !exist; i++){
			if(toCheck.equalsIgnoreCase(currentIngredients.get(i)))
				exist = true;
		}//End for
 		return exist;
	}//End checkSizeAndPrice
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
	public void registerOrder() throws IOException{
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		boolean worked = false;
		String msg = "Datos erroneos.";
		if( !tIdEmployee.getText().isEmpty() && !tIdCustomer.getText().isEmpty() &&
				cbStatus.getValue() != null && !taProducsAmount.getText().isEmpty() && !taRemark.getText().isEmpty()){
			if(checkCustomer(tIdCustomer.getText()) && checkEmployee(tIdEmployee.getText())){
				DMC.addOrder(product, amo, taRemark.getText(), cbStatus.getValue(), tIdCustomer.getText(), tIdEmployee.getText()); 
				msg = "Pedido registrado con �xito";
				tIdEmployee.setText("");
				tIdCustomer.setText("");
				taProducsAmount.setText("");
				taRemark.setText("");
				product = null;
				amo = null;
				worked = true;
			}//End if
		}//End if
		addInfo.setContentText(msg);
		addInfo.showAndWait();
		if(worked){
			product = new ArrayList<Product>();
			amo = new ArrayList<Integer>();
		}//End if
	}//End registerOrder
	
	private boolean checkCustomer(String id){
		boolean exist = false;
		if(DMC.searchCustomerPosition(id) >= 0)
			if(DMC.getCustomerEnabledStatus(id))
				exist = true;
		return exist;
	}//End checkCustomer
	private boolean checkEmployee(String id){
		boolean exist = false;
		if(DMC.searchEmployeePosition(id) >= 0)
			if(DMC.getEmployeeEnabledStatus(id))
				exist = true;
		return exist;
	}//End checkEmployee

	@FXML
	public void addProductToOrder()throws IOException{
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "No se ha podido a�adir el producto al pedido";
		EGC.showAddProductsToOrderEmergent();
		String amountAndProducts = taProducsAmount.getText();
		if(EGC.getProduct() != null){
			if(!checkProductToAdd(EGC.getProduct()+" ")) {
				amountAndProducts += EGC.getProduct()+" x "+EGC.getAmount() + "\n";
				product.add(EGC.getProduct());
				amo.add(EGC.getAmount());
				msg = "Producto ha sido añadido correctamente al pedido";
			}//End if
		}//End if
		EGC.clearAddProductData();
		addInfo.setContentText(msg);
		addInfo.showAndWait();
		taProducsAmount.setText(amountAndProducts);
	}//End addProductToOrder

	private boolean checkProductToAdd(String np){
		boolean exist = false;
		String[] p =  taProducsAmount.getText().split("\n");
		if(p != null){
			for(int i = 0; i < p.length; i++){
				if(np.equalsIgnoreCase((p[i].split("x"))[0])){
					exist = true;
				}//End if
			}//End for
		}//End if
		return exist;
	}//End checkProductToAdd

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
		Product p = productTable.getSelectionModel().getSelectedItem();
		if(p != null){
			if(mouseEvent.getClickCount() == 2){
				EGC.showChangeProducts(p);
				showProductsList();	
			}//End if
			changeMainItemsContextMenuState(false);
		}else
			changeMainItemsContextMenuState(true);
		
	}//End ListenChangeProductEvent

	private void changeMainItemsContextMenuState(boolean state){
		DisableElement.setDisable(state);
		removeElement.setDisable(state);
	}//End changeContextMenuState

	@FXML
	public void ListenChangesEnableProduct(){
		Product p = productTable.getSelectionModel().getSelectedItem();
		Alert changeEnableInfo = new Alert(AlertType.INFORMATION);
		changeEnableInfo.setHeaderText(null);
		String msg = "No hay elementos seleccionados";
		if(p!= null){
		try {
			DMC.changeEnableProduct(p);
			String enable = (p.getEnable())?"habilitado":"deshabilitado";
			showProductsList();
			msg = "Se ha cambiado la disponibilidad del elemento a " + enable;
		} catch (IOException e) {
			msg = "Ha ocurrido un error inesperado";
		}
		}//End if
		changeEnableInfo.setContentText(msg);
		changeEnableInfo.showAndWait();
	}//End ListenChangesEnableProduct

	@FXML
	public void ListenChangeProductList() throws IOException{
		enableList = !enableList;
		showProductsList();
	}//End ListenChangeProductList

	@FXML
	public void ListenRemoveProduct(){
		Product p = productTable.getSelectionModel().getSelectedItem();
		Alert removeInfo = new Alert(AlertType.INFORMATION);
		removeInfo.setHeaderText(null);
		String msg = "No hay elementos seleccionados";
		if(p!= null){
			try {
				if(DMC.removeProduct(p)) {
					msg = "Se ha eliminado el producto";
					showProductsList();
				}else
					msg = "No se ha podido eliminar el producto";
			} catch (IOException e){
				msg = "Ha ocurrido un error inesperado";
			} //End catch
		}//End if
		removeInfo.setContentText(msg);
		removeInfo.showAndWait();
	}//End ListenChangesEnableProduct

	@FXML
	public void ListenChangeIngredientEvent(MouseEvent mouseEvent) throws IOException{
		Ingredient i = ingredientTable.getSelectionModel().getSelectedItem();
		if(i != null){
			if(mouseEvent.getClickCount() == 2){
				EGC.changeIngredientEmergentScene(i);
				EGC.clearChangeIngredientData();
				showIngredientsList();
			}//End if
			changeMainItemsContextMenuState(false);
		}else
			changeMainItemsContextMenuState(true);
	}//End ListenChangeProductEvent

	@FXML
	public void ListenChangeIngredientList() throws IOException{
		enableList = !enableList;
		showIngredientsList();
	}//End ListenChangeProductList

	@FXML
	public void ListenChangesEnableIngredient(){
		Ingredient i = ingredientTable.getSelectionModel().getSelectedItem();
		Alert changeEnableInfo = new Alert(AlertType.INFORMATION);
		changeEnableInfo.setHeaderText(null);
		String msg = "No hay elementos seleccionados";
		if(i!= null){
		try {
			DMC.changeEnableIngredient(i);
			String enable = (i.getEnable())?"habilitado":"deshabilitado";
			showIngredientsList();
			msg = "Se ha cambiado la disponibilidad del elemento a " + enable;
		} catch (IOException e) {
			msg = "Ha ocurrido un error inesperado";
		}
		}//End if
		changeEnableInfo.setContentText(msg);
		changeEnableInfo.showAndWait();
	}//End ListenChangesEnableProduct

	@FXML
	public void ListenRemoveIngredient(){
		Ingredient i = ingredientTable.getSelectionModel().getSelectedItem();
		Alert removeInfo = new Alert(AlertType.INFORMATION);
		removeInfo.setHeaderText(null);
		String msg = "No hay elementos seleccionados";
		if(i!= null){
			try {
				if(DMC.removeIngredient(i)) {
					msg = "Se ha eliminado el producto";
					showIngredientsList();
				}else
					msg = "No se ha podido eliminar el producto";
			} catch (IOException e){
				msg = "Ha ocurrido un error inesperado";
			} //End catch
		}//End if
		removeInfo.setContentText(msg);
		removeInfo.showAndWait();
	}//End ListenChangesEnableProduct

	@FXML
	public void ListenChangeDishTypeEvent(MouseEvent mouseEvent) throws IOException{
		DishType d = dishTypeTable.getSelectionModel().getSelectedItem();
		if(d != null){
			if(mouseEvent.getClickCount() == 2){
				EGC.showChangeDihstypeScene(d);
				EGC.clearChangeDishTypeData();
				showDishTypeList();
			}//End if
			changeMainItemsContextMenuState(false);
		}else
			changeMainItemsContextMenuState(true);
	}//End ListenChangeProductEvent

	@FXML
	public void ListenChangeDishTypeList() throws IOException{
		enableList = !enableList;
		showDishTypeList();
	}//End ListenChangeProductList

	@FXML
	public void ListenChangesEnableDishType(){
		DishType d = dishTypeTable.getSelectionModel().getSelectedItem();
		Alert changeEnableInfo = new Alert(AlertType.INFORMATION);
		changeEnableInfo.setHeaderText(null);
		String msg = "No hay elementos seleccionados";
		if(d!= null){
		try {
			DMC.changeEnableDishType(d);
			String enable = (d.getEnable())?"habilitado":"deshabilitado";
			showDishTypeList();
			msg = "Se ha cambiado la disponibilidad del elemento a " + enable;
		} catch (IOException e) {
			msg = "Ha ocurrido un error inesperado";
		}
		}//End if
		changeEnableInfo.setContentText(msg);
		changeEnableInfo.showAndWait();
	}//End ListenChangesEnableProduct

	@FXML
	public void ListenRemoveDishType(){
		DishType d = dishTypeTable.getSelectionModel().getSelectedItem();
		Alert removeInfo = new Alert(AlertType.INFORMATION);
		removeInfo.setHeaderText(null);
		String msg = "No hay elementos seleccionados";
		if(d!= null){
			try {
				if(DMC.removeDishType(d)) {
					msg = "Se ha eliminado el producto";
					showDishTypeList();
				}else
					msg = "No se ha podido eliminar el producto";
			} catch (IOException e){
				msg = "Ha ocurrido un error inesperado";
			} //End catch
		}//End if
		removeInfo.setContentText(msg);
		removeInfo.showAndWait();
	}//End ListenChangesEnableProduct
	@FXML
	public void ListenOrderEvents(MouseEvent mouseEvent) throws IOException{
		Order o = orderTable.getSelectionModel().getSelectedItem();
		if(o != null){
			if(mouseEvent.getClickCount() == 2){
				EGC.showChangeOrder(o);
				showSceneOrdersList();
			}//End if
			showCompleteRegister.setDisable(false);
			DisableElement.setDisable(false);
			removeElement.setDisable(false);
		}else {
			showCompleteRegister.setDisable(true);
			DisableElement.setDisable(true);
			removeElement.setDisable(true);
		}//End else
	}//End ListenOrderEvents
	@FXML
	public void ListenShowOrderRegister() throws IOException{
		Order o = orderTable.getSelectionModel().getSelectedItem();
		EGC.showCompleteOrderScene(o);
	}//End ListenShowOrderRegister
	@FXML
	public void ListenRemoveOrder() throws IOException{
		Order o = orderTable.getSelectionModel().getSelectedItem();
		DMC.removeOrder(o);
		showSceneOrdersList();
	}//End ListenRomeveOrder
	@FXML
	public void ListenChangeEnableOrder(){
		Alert info = new Alert(AlertType.INFORMATION);
		info.setHeaderText(null);
		String msg = new String();
		Order o = orderTable.getSelectionModel().getSelectedItem();
		try {
			DMC.changeEnableOrder(o);
			String enable = (o.getEnable())?"habilitado":"deshabilitado";
			showSceneOrdersList();
			msg = "Se ha cambiado la disponibilidad del elemento a " + enable;
		} catch (IOException e) {
			msg = "Ha ocurrido un error inesperado";
		}
		info.setContentText(msg);
		info.showAndWait();
	}//End ListenChangeEnableOrder
	@FXML
	public void ListenChangeOrderList() throws IOException{
		enableList = !enableList;
		showSceneOrdersList();
	}//End ListenChangeProductList
	@FXML
	public void ListenAddIngredientToProductList(){
		String i = lIngredients.getSelectionModel().getSelectedItem();
		if(i == null)
			removeElement.setDisable(true);
		else
			removeElement.setDisable(false);
	}//End ListenAddIngredientToProductList

	@FXML
	public void removeIngredientFromAddIngredientToProductList(){
		String i = lIngredients.getSelectionModel().getSelectedItem();
		ObservableList<String> currentIngredients = FXCollections.observableList(lIngredients.getItems());
		currentIngredients.remove(i);
		lIngredients.setItems(currentIngredients);
	}//End removeIngredientFromAddIngredientToProductList

	public void initializeProductsList(){
		ObservableList<Product> productsList = FXCollections.observableArrayList(DMC.getProducts(enableList));
		productTable.setItems(productsList);
		productName.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
		productType.setCellValueFactory(new PropertyValueFactory<Product,String>("type"));
		productSize.setCellValueFactory(new PropertyValueFactory<Product,String>("size"));
		productPrice.setCellValueFactory(new PropertyValueFactory<Product,Double>("price"));
		productIngredients.setCellValueFactory(new PropertyValueFactory<Product,String>("ingredients"));
	}//End initializeProductsList
	
	public void initializeOrdersList(){
		ObservableList<Order> orderList = FXCollections.observableArrayList(DMC.getOrders(enableList));
		orderTable.setItems(orderList);
		orderCode.setCellValueFactory(new PropertyValueFactory<Order,String>("code"));
		orderDate.setCellValueFactory(new PropertyValueFactory<Order,String>("date"));
		orderStatus.setCellValueFactory(new PropertyValueFactory<Order,String>("status"));
		orderProducts.setCellValueFactory(new PropertyValueFactory<Order,String>("productsList"));
		orderRemark.setCellValueFactory(new PropertyValueFactory<Order,String>("remark"));
		orderTable.refresh();
	}//End initializeOrdersList
	
	public void initializeIngredientsList(){
		ObservableList<Ingredient> ingredientList = FXCollections.observableArrayList(DMC.getIngredients(enableList));
		ingredientTable.setItems(ingredientList);
		ingredientName.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("name"));
	}//End initializeIngredientsList
	
	public void initializeDishtypeList(){
		ObservableList<DishType> dishTypeList = FXCollections.observableArrayList(DMC.getDishtype(enableList));
		dishTypeTable.setItems(dishTypeList);
		dishTypeName.setCellValueFactory(new PropertyValueFactory<DishType,String>("name"));
	}//End initializeDishtypeList

	private void initializeStatusComboBox(){
		status = FXCollections.observableArrayList();
		status.add("SOLICITADO");
		status.add("EN_PROCESO");
		status.add("ENVIADO");
		status.add("ENTREGADO");
		cbStatus.setItems(status);
	}//End initializeIngredientsComboBox

}//End MainGUIController