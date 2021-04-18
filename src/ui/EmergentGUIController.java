package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmergentGUIController {
	
	private final String FOLDER = "fxml/";
	final String[] ST = {"SOLICITADO","EN_PROCESO","ENVIADO","ENTREGADO"};
	private DeliveryManagerController DMC;
	private String  size;
	private double price;
	private String ingredientToadd;
	private int amount;
	
	private ObservableList<Ingredient> ingredients;
	private ObservableList<Product> products;
	private DishType dishtype;
	private Product productToChanges;
	private Ingredient ingredientToChange;
	private Product productToAdd;
	private Order registerOrder;
	private String dishTypeToadd;
	private String customerId;
	@FXML private TextField customerToSearch;
	@FXML private ListView<Customer> CustomersFound;
	@FXML private Label receives;
	@FXML private Label delivery;
	@FXML private Label createdby;
	@FXML private Label modifiedby;
	@FXML private ChoiceBox<String> cbStatus;
	@FXML private ComboBox<DishType> cbdishes;
	@FXML private TextField tdish;
	@FXML private MenuItem removeElement;
	@FXML private TextField tAmount;
	@FXML private TextField tIngredientName;
	@FXML private TextField tDishtypeName;
	@FXML private TextField tSize;
	@FXML private TextField tPrice;
	@FXML private TextField tNewIngredientToProduct;
	@FXML private TextField tSelectedIngredient;
	@FXML private ComboBox<Ingredient> cbIngredients;
	@FXML private ComboBox<ProductSize> cbSizes;
	@FXML private ComboBox<Product> cbProducts;
	@FXML private TextField tfPrices;
	@FXML private ChoiceBox<String> reportType;
	@FXML private ListView<String> taIngredientsToChanges;
	@FXML private TextField pathTxt;
	@FXML private DatePicker initialDate;
	@FXML private DatePicker finishDate;
	@FXML private TextField initialHour;
	@FXML private TextField finishHour;
	@FXML private TextField separatorTxt;
	@FXML private TextField tNameToChanges;
	@FXML private TextField tTypeToChanges;
	@FXML private TextField employeeNameTxt;
	@FXML private TextField employeeLastNameTxt;
	@FXML private TextField employeeIdTxt;
	@FXML private Employee employeeToChange;
	@FXML private Customer customerToChange;
	@FXML private TextField customerNameTxt;
	@FXML private TextField customerLastNameTxt;
	@FXML private TextField customerIdTxt;
	@FXML private TextField customerAddressTxt;
	@FXML private TextField customerPhoneTxt;
	@FXML private TextArea customerRemarkTxt;
	@FXML private User userToChange;
	@FXML private TextField userNameTxt;
	@FXML private TextField userPasswordTxt;
	@FXML private TextField passwordConfirmationTxt;
	//Order
	@FXML private Label code;
	@FXML private Label date;
	@FXML private Label status;
	@FXML private TextArea remark;
	@FXML private ListView<Product> Lproducts;
	@FXML private ListView<Integer> LAmount;
	@FXML private Slider statusProgress;
	@FXML private TextField tOrderCustomerId;
	@FXML private TextField tOrderEmployeeId;
	@FXML private TextArea taOrdersRemark;
	@FXML private MenuItem remove;
	@FXML private MenuItem change;
	@FXML private TextField TFAmount;
	//Import data
	@FXML private ChoiceBox<String> importType;
	@FXML private TextField mSeparator;
	@FXML private TextField sSeparator;
	@FXML private TextField tSeparator;
	//Product
	@FXML private Label lpName;
	@FXML private Label lpDish;
	@FXML private Label lpSize;
	@FXML private Label lpCreator;
	@FXML private Label lpPrice;
	@FXML private Label lpModified;
	@FXML private ListView<String> lvpIngredients;
	public EmergentGUIController(DeliveryManagerController DMC){
		this.DMC = DMC;
		size = new String();
	}//End constructors

	@FXML
	public void showImportScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"ImportDataEmergent.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root, null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage form = new Stage();
		setImportElements();
		form.initModality(Modality.APPLICATION_MODAL);
		form.setTitle("Importar datos");
		form.setScene(scene);
		form.setResizable(false);
		form.showAndWait();
	}//End showImportScene

	@FXML
	public void setImportElements() throws IOException {
		importType.getItems().add("Clientes");
		importType.getItems().add("Productos");
		importType.getItems().add("Pedidos");
	}//End setImportElements

	public void chooseImportFilePath() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selecciona ruta del archivo a importar");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
		File f = fileChooser.showOpenDialog(null);
		if(f != null) {
			pathTxt.setText(f.getAbsolutePath());
		}//End if
	}//End chooseImportFilePath

	public void importDataStatusAlert(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Mensaje");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}//End allDataImportedAlert

	@FXML
	public void importData(ActionEvent event) throws IOException {
		if(importType.getValue() != null) {
			if(importType.getValue().equalsIgnoreCase("Clientes")) {
				if(!pathTxt.getText().isEmpty() && !mSeparator.getText().isEmpty()) {
					importCustomersData(event);
				} else {
					emptyFieldAlert();
				}//End else
			} else if(importType.getValue().equalsIgnoreCase("Productos")) {
				if(!pathTxt.getText().isEmpty() && !mSeparator.getText().isEmpty() && !sSeparator.getText().isEmpty()) {
					importProductsData(event);
				} else {
					emptyFieldAlert();
				}//End else
			} else {
				if(!pathTxt.getText().isEmpty() && !mSeparator.getText().isEmpty() && !sSeparator.getText().isEmpty() && !tSeparator.getText().isEmpty()) {
					importOrdersData(event);
				} else {
					emptyFieldAlert();
				}//End else
			}//End else
		} else {
			emptyFieldAlert();
		}//End else
	}//End importData

	public void importCustomersData(ActionEvent event) {
		AtomicReference<String> msg = new AtomicReference<>("Se esta importando la informacion...");
		Runnable r = () -> {
			try {
				DMC.importCustomerData(new File(pathTxt.getText()), mSeparator.getText());
			} catch (IOException exception) {
				msg.set("Ha ocurrido un error");
			}//End catch
		};
		Thread t = new Thread(r);
		t.start();
		importDataStatusAlert(msg.get());
		closeEmergentWindows(event);
	}//End importCustomersData

	public void importProductsData(ActionEvent event) {
		AtomicReference<String> msg = new AtomicReference<>("Se esta importando la informacion...");
		Runnable r = () -> {
			try {
				DMC.importProducts(new File(pathTxt.getText()), mSeparator.getText(), sSeparator.getText());
			} catch (IOException exception) {
				msg.set("Ha ocurrido un error");
			}//End catch
		};
		Thread t = new Thread(r);
		t.start();
		importDataStatusAlert(msg.get());
		closeEmergentWindows(event);
	}//End importProductsData

	public void importOrdersData(ActionEvent event) {
		AtomicReference<String> msg = new AtomicReference<>("Se esta importando la informacion...");
		Runnable r = () -> {
			try {
				DMC.importOrders(new File(pathTxt.getText()), mSeparator.getText(), sSeparator.getText(), tSeparator.getText());
			} catch (IOException exception) {
				msg.set("Ha ocurrido un error");
			}//End catch
		};
		Thread t = new Thread(r);
		t.start();
		importDataStatusAlert(msg.get());
		closeEmergentWindows(event);
	}//End importOrdersData

	@FXML
	public void showExportScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"ExportReportsEmergent.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root, null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage form = new Stage();
		setReportElements();
		form.initModality(Modality.APPLICATION_MODAL);
		form.setTitle("Emitir Reportes");
		form.setScene(scene);
		form.setResizable(false);
		form.showAndWait();
	}//End showExportScene

	@FXML
	public void generateReport(ActionEvent e) throws FileNotFoundException {
		if(reportType.getValue() != null && initialDate.getValue() != null && !initialHour.getText().isEmpty() &&
		finishDate != null && !finishHour.getText().isEmpty() && !pathTxt.getText().isEmpty() && !separatorTxt.getText().isEmpty()) {
			String type = reportType.getValue();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime dateTimeInitial = LocalDateTime.parse((initialDate.getValue().toString() + " " + initialHour.getText()), formatter);
			Date di = toDate(dateTimeInitial);
			LocalDateTime dateTimeFinal = LocalDateTime.parse((finishDate.getValue().toString() + " " + finishHour.getText()), formatter);
			Date df = toDate(dateTimeFinal);
			String separator = separatorTxt.getText();
			switch(type) {
				case "Pedidos":
					DMC.exportOrdersData(new File(pathTxt.getText()), separator, di, df);
					closeEmergentWindows(e);
					break;
				case "Empleados":
					DMC.exportEmployeesData(new File(pathTxt.getText()), separator, di, df);
					closeEmergentWindows(e);
					break;
				case "Productos":
					DMC.exportProductsData(new File(pathTxt.getText()), separator, di, df);
					closeEmergentWindows(e);
					break;
			}//End
		} else {
			emptyFieldAlert();
		}
	}//End generateReport

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
	public Date toDate(LocalDateTime ldt) {
		return java.sql.Timestamp.valueOf(ldt);
	}//End

	@FXML
	public void setReportElements() {
		reportType.getItems().add("Pedidos");
		reportType.getItems().add("Empleados");
		reportType.getItems().add("Productos");
	}//End setReportElements

	@FXML
	public void chooseSavePath() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Seleccionar ruta de guardado");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
		File file = fileChooser.showSaveDialog(null);
		if(file != null) {
			pathTxt.setText(file.getAbsolutePath());
		}//End if
	}//End chooseSavePath
	
	@FXML
	public void showRegisterIngredienteScene() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"RegisterIngredientEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage form = new Stage();
		form.initModality(Modality.APPLICATION_MODAL);
		form.setTitle("Agregar ingrediente");
		form.setScene(scene);
		form.setResizable(false);
		form.showAndWait();
	}//End showRegisterIngredienteScene

	@FXML
	public void showRegisterDihstypeScene() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"RegisterDishTypeEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage form = new Stage();
		form.initModality(Modality.APPLICATION_MODAL);
		form.setTitle("Agregar tipo de plato");
		form.setScene(scene);
		form.setResizable(false);
		form.showAndWait();
	}//End showRegisterDihstypeScene
	@FXML
	public void showSearchAndAddCustomerScene() throws IOException{
		customerId = "";
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"SearchCustomerEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage searchCustomer = new Stage();
		searchCustomer.initModality(Modality.APPLICATION_MODAL);
		searchCustomer.setTitle("Buscar cliente");
		searchCustomer.setScene(scene);
		searchCustomer.setResizable(false);
		searchCustomer.showAndWait();
	}//End showSearchAndAddCustomerScene
	@FXML
	public void searchCustomersAndPutInList(){
		if(!customerToSearch.getText().equals("")){
			initializeCustomersList(DMC.searchAngGetCustomerByName(customerToSearch.getText()));
		}//End if
	}//End searchCustomersAndPutInList
	private void initializeCustomersList(List<Customer> lcustomersFound){
		ObservableList<Customer> customers = FXCollections.observableList(lcustomersFound);
		CustomersFound.setItems(customers);
	}//End initializeCustomersList
	@FXML
	public void selectCustomer(ActionEvent event){
		Customer c = CustomersFound.getSelectionModel().getSelectedItem();
		Alert info = new Alert(AlertType.ERROR);
		info.setHeaderText(null);
		info.setContentText("No hay un cliente seleccionado");
		boolean worked = false;
		if(c != null){
			customerId = c.getId();
			worked = true;
		}
		if(worked)
			closeEmergentWindows(event);
		else
			info.showAndWait();
	}//End selectCustomer
	public String getCustomerIdToAdd(){
		return customerId;
	}//End getCustomerIdToAdd
	@FXML
	public void showAddDishTypeToProduct() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"getDishTypeEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage form = new Stage();
		initializeDishTypesComboBox();
		form.initModality(Modality.APPLICATION_MODAL);
		form.setTitle("Agregar tipo de plato al producto");
		form.setScene(scene);
		form.setResizable(false);
		form.showAndWait();
	}//End showSceneLogin
	@FXML
	public void showChangeDihstypeScene(DishType d) throws IOException{
		dishtype = d;
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"ChangeDishTypeEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage form = new Stage();
		form.initModality(Modality.APPLICATION_MODAL);
		form.setTitle("Cambiar tipo de plato");
		form.setScene(scene);
		form.setResizable(false);
		form.showAndWait();
	}//End showRegisterDihstypeScene

	@FXML
	public void showAddSizeAndPriceScene() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"AddSizeAndPriceEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		initializeSizesComboBox();
		Stage formulario = new Stage();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Agregar tamaño del producto");
		formulario.setScene(scene);
		formulario.setResizable(false);
		formulario.showAndWait();
	}//End showRegisterDihstypeScene

	@FXML
	public void changeIngredientEmergentScene(Ingredient i) throws IOException{
		ingredientToChange = i;
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"changeIngredientEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage changeIngredient = new Stage();
		changeIngredient.initModality(Modality.APPLICATION_MODAL);
		changeIngredient.setTitle("Cambiar ingrediente.");
		changeIngredient.setScene(scene);
		changeIngredient.setResizable(false);
		changeIngredient.showAndWait();
	}//End changeIngredientEmergentScene

	@FXML
	public void changeEmployeeEmergentScene(Employee employee) throws IOException {
		employeeToChange = employee;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"ChangeEmployeeEmergent.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root, null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage changeEmployee = new Stage();
		changeEmployee.initModality(Modality.APPLICATION_MODAL);
		employeeNameTxt.setText(employeeToChange.getName());
		employeeLastNameTxt.setText(employeeToChange.getLastName());
		employeeIdTxt.setText(employeeToChange.getId());
		changeEmployee.setTitle("Modificar empleado");
		changeEmployee.setScene(scene);
		changeEmployee.setResizable(false);
		changeEmployee.showAndWait();
	}//End changeEmployeeEmergentScene

	@FXML
	public void changeUserEmergentScene(User user) throws IOException {
		userToChange = user;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"ChangeUserEmergent.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root, null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage changeUser = new Stage();
		changeUser.initModality(Modality.APPLICATION_MODAL);
		userNameTxt.setText(userToChange.getUserName());
		changeUser.setTitle("Modificar usuario");
		changeUser.setScene(scene);
		changeUser.setResizable(false);
		changeUser.showAndWait();
	}//End changeUserEmergentScene

	@FXML
	public void changeCustomerEmergentScene(Customer customer) throws IOException {
		customerToChange = customer;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"ChangeCustomerEmergent.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root, null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage changeCustomer = new Stage();
		changeCustomer.initModality(Modality.APPLICATION_MODAL);
		customerNameTxt.setText(customerToChange.getName());
		customerLastNameTxt.setText(customerToChange.getLastName());
		customerIdTxt.setText(customerToChange.getId());
		customerPhoneTxt.setText(customerToChange.getNPhone());
		customerAddressTxt.setText(customerToChange.getAddress());
		customerRemarkTxt.setText(customerToChange.getRemark());
		changeCustomer.setTitle("Modificar cliente");
		changeCustomer.setScene(scene);
		changeCustomer.setResizable(false);
		changeCustomer.showAndWait();
	}//End changeCustomerEmergentScene

	@FXML//changesIngredients
	public void showChangeIngredientToProduct() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"changeIngredientsFromProductsEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage formulario = new Stage();
		initializeIngredientsComboBox();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Cambiar ingrediente");
		formulario.setScene(scene);
		formulario.setResizable(false);
		formulario.showAndWait();
	}//End showRegisterDihstypeScene

	@FXML
	public void showAddIngredientToProductScene() throws IOException{
		ingredientToadd = "";
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"AddIngredienteToProductEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage formulario = new Stage();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Agregar tamano del producto");
		formulario.setScene(scene);
		formulario.setResizable(false);
		initializeIngredientsComboBox();
		formulario.showAndWait();
	}//End showRegisterDihstypeScene

	@FXML//changeProductData
	public void showChangeProducts(Product p) throws IOException{
		productToChanges = p; //a
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"ChangeProductEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage formulario = new Stage();
		initializeProductsListView();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Cambiar producto");
		formulario.setScene(scene);
		formulario.setResizable(false);
		initializeForm();
		formulario.showAndWait();
	}//End showRegisterDihstypeScene
	@FXML//changeProductData
	public void showChangeOrder(Order o) throws IOException{
		registerOrder = o;
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"ChangeOrderEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage formulario = new Stage();
		initializeStatusChoiceBox();
		initializeOrderForm();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Cambiar producto");
		formulario.setScene(scene);
		formulario.setResizable(false);
		formulario.showAndWait();
	}//End showRegisterDihstypeScene
	@FXML
	public void showAddProductsToOrderEmergent() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"GetProductEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage formulario = new Stage();
		initializeProductsChoiceBox();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Agregar producto");
		formulario.setScene(scene);
		formulario.setResizable(false);
		formulario.showAndWait();
	}//End showRegisterDihstypeScene
	@FXML
	public void showCompleteProductRegister(Product p) throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"VisualizeCompleteProductWindows.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage form = new Stage();
		initializeProductForm(p);
		form.initModality(Modality.APPLICATION_MODAL);
		form.setTitle("Registro de producto");
		form.setScene(scene);
		form.setResizable(false);
		form.showAndWait();
	}//End showRegisterDihstypeScene
	private void initializeProductForm(Product p){
		lpName.setText(p.getName());
		lpDish.setText(p.getType());
		lpSize.setText(p.getSize());
		lpPrice.setText(String.valueOf(p.getPrice()));
		lpCreator.setText(p.getProductBase().getCreator().getName() + " " + p.getProductBase().getCreator().getLastName());
		String mod = (p.getModifier() != null)?p.getModifier().getName() + " " + p.getModifier().getLastName():"Sin modificar";
		lpModified.setText(mod);
		ObservableList<String> list = FXCollections.observableList(p.getIngredientsList());
		lvpIngredients.setItems(list);
	}//End initializeProductForm
	@FXML
	public void AddProduct(ActionEvent event){
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "Datos erroneos";
		int am = 0;
		boolean worked = false;
		if( cbProducts.getValue() != null && !tAmount.getText().isEmpty()){
			try{
				am = Integer.parseInt(tAmount.getText());
				if(am > 0){
					productToAdd = cbProducts.getValue();
					amount = am;
					worked = true;
				}else
					msg = "La cantidad debe ser un entero positivo";
			}catch(NumberFormatException e){
				msg = "La cantidad debe ser un entero positivo";
			}//End catch
		}//End if
		if(worked)
			closeEmergentWindows(event);
		else {
			addInfo.setContentText(msg);
			addInfo.showAndWait();
		}//End else
	}//End AddProduct
	@FXML
	public void setPriceTextField(){
		if(cbProducts.getValue() != null)
			tfPrices.setText(String.valueOf(cbProducts.getValue().getPrice()));
	}//End setPriceTextField
	@FXML
	public void showCompleteOrderScene(Order o) throws IOException{
		registerOrder = o;
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"VisualizeCompleteOrderEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage registerOrder = new Stage();
		registerOrder.initModality(Modality.APPLICATION_MODAL);
		registerOrder.setTitle("Ver registro");
		registerOrder.setScene(scene);
		registerOrder.setResizable(false);
		initializeRegisterOrder();
		registerOrder.showAndWait();
	}//End showRegisterIngredienteScene
	@FXML
	public void showCompleteOrderScene() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"GetProductInChangeOrderEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage addProduct = new Stage();
		initializeProductsChoiceBox();
		addProduct.initModality(Modality.APPLICATION_MODAL);
		addProduct.setTitle("Ver registro");
		addProduct.setScene(scene);
		addProduct.setResizable(false);
		addProduct.showAndWait();
	}//End showRegisterIngredienteScene
	@FXML
	public void changeProductListInChangeOrder(ActionEvent event){
		Alert info = new Alert(AlertType.INFORMATION);
		info.setHeaderText(null);
		String msg = "Datos erroneos";
		boolean worked = false;
		if(cbProducts.getValue() != null && !tAmount.getText().equals("")){
			try{
				Integer a = Integer.parseInt(tAmount.getText());
				ObservableList<Integer> am = FXCollections.observableList(LAmount.getItems());
				ObservableList<Product> pd = FXCollections.observableList(Lproducts.getItems());
				pd.add(cbProducts.getValue());
				am.add(a);
				LAmount.setItems(am);
				Lproducts.setItems(pd);
				msg = "Producto agregado correctamente";
				worked = true;
			}catch(NumberFormatException e){
				msg = "La cantidad debe ser un numero";
			}//End catch
		}//End if
		info.setContentText(msg);
		info.showAndWait();
		if(worked)
			closeEmergentWindows(event);
	}//End changeProductListInChangeOrder
	public void initializeForm(){
		tNameToChanges.setText(productToChanges.getName());
		tTypeToChanges.setText(productToChanges.getType());
		tSize.setText(productToChanges.getSize());
		tPrice.setText(String.valueOf(productToChanges.getPrice()));
	}//End initializeForm
	public void initializeOrderForm(){ //registerOrder
		tOrderCustomerId.setText(registerOrder.getCustomer().getId());
		tOrderEmployeeId.setText(registerOrder.getEmployee().getId());
		cbStatus.setValue(registerOrder.getStatus());
		taOrdersRemark.setText(registerOrder.getRemark());
		ObservableList<Product> products = FXCollections.observableList(registerOrder.getProducts());
		ObservableList<Integer> amount = FXCollections.observableList(registerOrder.getAmount());
		Lproducts.setItems(products);
		LAmount.setItems(amount);
	}//End initializeForm
	@FXML
	public void ListenChangeProductsFromOrder(){
		Product p = Lproducts.getSelectionModel().getSelectedItem();
		if(p != null){
			LAmount.getSelectionModel().select(Lproducts.getSelectionModel().getSelectedIndex());
			changeMenuItemDisable(false);
		}else
			changeMenuItemDisable(true);
	}//End ListenChangeProductsFromOrder
	@FXML
	public void ListenChangeAmountsFromOrder(MouseEvent mouseEvent) throws IOException{
		Integer i = LAmount.getSelectionModel().getSelectedItem();
		if(i != null){
			Lproducts.getSelectionModel().select(LAmount.getSelectionModel().getSelectedIndex());
			if(mouseEvent.getClickCount() == 2)
				ShowchangeAmountFromOrder();
			changeMenuItemDisable(false);
		}else
			changeMenuItemDisable(true);
	}//End ListenChangeAmountsFromOrder
	@FXML
	public void ShowchangeAmountFromOrder() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"ChangeAmountEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		scene.getStylesheets().add(getClass().getResource("aplication.css").toExternalForm());
		Stage changeAmount = new Stage();
		changeAmount.initModality(Modality.APPLICATION_MODAL);
		changeAmount.setTitle("Cambiar cantidad");
		changeAmount.setScene(scene);
		changeAmount.setResizable(false);
		changeAmount.showAndWait();
	}//End changeAmountFromOrder
	@FXML
	public void changeAmountFromOrder(ActionEvent event){
		Alert info = new Alert(AlertType.INFORMATION);
		info.setHeaderText(null);
		String msg = "Datos erroneos";
		boolean worked = false;
		if(!TFAmount.getText().equals("")){
			try{
				Integer amo = Integer.parseInt(TFAmount.getText());
				ObservableList<Integer> ams = FXCollections.observableList(LAmount.getItems());
				ams.set(LAmount.getSelectionModel().getSelectedIndex(),amo);
				LAmount.setItems(ams);
				worked = true;
				msg = "Se ha modificado con exito"; 
			}catch(NumberFormatException e){
				msg = "La cantidad debe ser un numero";
			}//End catch
		}//End if
		info.setContentText(msg);
		info.showAndWait();
		if(worked)
			closeEmergentWindows(event);
	}//End changeAmountFromOrder
	@FXML
	public void removeProductAndAmount(){
		ObservableList<Product> ps = FXCollections.observableList(Lproducts.getItems());
		ObservableList<Integer> as = FXCollections.observableList(LAmount.getItems());
		ps.remove(Lproducts.getSelectionModel().getSelectedItem());
		as.remove(LAmount.getSelectionModel().getSelectedItem());
		Lproducts.setItems(ps);
		LAmount.setItems(as);
	}//End removeProductAndAmount
	private void changeMenuItemDisable(boolean disable){
		change.setDisable(disable);
		remove.setDisable(disable);
	}
	@FXML
	public void setSizeText(){
		ProductSize ps = cbSizes.getValue();
		if(ps != null)
			tSize.setText(ps.toString());
	}//End setSizeText
	@FXML
	public void changeProductData(ActionEvent event) throws IOException{
		boolean worked = false;
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "Datos erroneos";
		if(!tNameToChanges.getText().isEmpty() && !tTypeToChanges.getText().isEmpty() && 
			!tSize.getText().isEmpty() && !tPrice.getText().isEmpty() 
			&& !taIngredientsToChanges.getItems().isEmpty()){
			try{
				price = Double.parseDouble(tPrice.getText());
				if(price >= 0){
					if(DMC.changeProduct(productToChanges,tNameToChanges.getText(),
							taIngredientsToChanges.getItems(),price,tSize.getText(),
							tTypeToChanges.getText())){
						msg = "Se ha cambiado el producto con exito";
						worked = true;
					}else
						msg = "No se pudo cambiar el producto, ya existe otro con ese nombre";
				}else
					msg = "El precio no puede ser negativo";
			}catch(NumberFormatException e){
				msg = "Precio erroneo";
			}//End catch
		}//End if
		addInfo.setContentText(msg);
		addInfo.showAndWait();
		if(worked)
			closeEmergentWindows(event);
	}//End changeProductData
	@FXML
	public void changesDishType(ActionEvent event) throws IOException{
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "Datos erroneos";
		boolean worked = false;
		if(!tDishtypeName.getText().isEmpty()){
			if(DMC.changeDishType(dishtype, tDishtypeName.getText())){
				msg = "Se ha cambiado el tipo de plato con exito";
				worked = true;
			}else
				msg = "Ya existe ese tipo de plato";
		}//End if
		addInfo.setContentText(msg);
		addInfo.showAndWait();
		if(worked)
			 closeEmergentWindows(event);
	}//End changesDishType

	@FXML
	public void showInfoFromComboBoxSelectedItem(){
		Ingredient ing = cbIngredients.getValue();
		if(ing != null)
			tSelectedIngredient.setText(ing.getName());
	}//End showInfoFromComboBoxSelectedItem
	@FXML
	public void showInfoFromIngredientsComboBoxSelectedItem(){
		Ingredient ing = cbIngredients.getValue();
		if(ing != null)
			tNewIngredientToProduct.setText(ing.getName());
	}//End showInfoFromComboBoxSelectedItem
	@FXML
	public void setIngredientToadd(ActionEvent event){
		boolean worked = false;
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "Ingrediente erroneo";
		if(!tSelectedIngredient.getText().isEmpty()){
			ingredientToadd = tSelectedIngredient.getText();
			worked = true;
		}else {
			addInfo.setContentText(msg);
			addInfo.showAndWait();
		}
		if(worked)
			closeEmergentWindows(event);
	}//End setIngredientoToadd
	@FXML
	public void addIngredientToProductList(ActionEvent event){
		Alert info = new Alert(AlertType.INFORMATION);
		info.setHeaderText(null);
		String msg = "Por favor selecciona o escribe el ingrediente.";
		boolean worked = false;
		if(!tNewIngredientToProduct.getText().isEmpty()){
			if(!checkNewIngredient(tNewIngredientToProduct.getText())){
				ObservableList<String> ing = FXCollections.observableList(taIngredientsToChanges.getItems());
				ing.add(tNewIngredientToProduct.getText());
				taIngredientsToChanges.setItems(ing);
				msg = "Ingrediente agregado con exito!";
				worked = true;
			}else
				msg = "El ingrediente " + tNewIngredientToProduct.getText() + " ya se encuentra en la lista de ingredientes del producto.";
		}//End if
		info.setContentText(msg);
		info.showAndWait();
		if(worked)
			closeEmergentWindows(event);
	}//End addIngredientToProductList
	private boolean checkNewIngredient(String toCheck){
		boolean exist = false;
		ObservableList<String> ing = FXCollections.observableList(taIngredientsToChanges.getItems());
		for(int i = 0; i < ing.size(); i++){
			if(toCheck.equalsIgnoreCase(ing.get(i))){
				exist = true;
			}//End if
		}//End for
		return exist;
	}//End checkNewIngredient
	@FXML
	public void changeUser(ActionEvent event) {
		Alert changeInfo = new Alert(AlertType.INFORMATION);
		changeInfo.setHeaderText(null);
		String msg = "No pueden haber campos vacios";
		String newUserName = userNameTxt.getText();
		String newPassword = userPasswordTxt.getText();
		String confirmation = passwordConfirmationTxt.getText();
		boolean worked = false;
		if(DMC.validateBlankChars(newUserName) && DMC.validateBlankChars(newPassword) && DMC.validateBlankChars(confirmation)) {
			if(newPassword.equals(confirmation)) {
				if(!DMC.validateUserName(newUserName) || newUserName.equals(userToChange.getUserName())) {
					if (newPassword.length() >= 7) {
						try {
							DMC.changeUser(userToChange, newUserName, newPassword);
							msg = "Datos del usuario modificados con exito";
							worked = true;
						} catch (IOException exception) {
							msg = "Ha ocurrido un error inesperado";
						}
					} else {
						msg = "La contraseÃ±a debe contener al menos 7 caracteres";
					}//End else
				} else {
					msg = "El nombre de usuario ya esta en uso";
				}//End else
			} else {
				msg = "Las contraseï¿½as no coinciden";
			}//End else
		}//End if
		changeInfo.setContentText(msg);
		changeInfo.showAndWait();
		if(worked) {
			closeEmergentWindows(event);
		}//End if
	}//End changeUser

	@FXML
	public void changeCustomer(ActionEvent event) {
		Alert changeInfo = new Alert(AlertType.INFORMATION);
		changeInfo.setHeaderText(null);
		String msg = "Debe llenar todos los campos obligatorios";
		String newName = customerNameTxt.getText();
		String newLastName = customerLastNameTxt.getText();
		String newId = customerIdTxt.getText();
		String newNPhone = customerPhoneTxt.getText();
		String newAddress = customerAddressTxt.getText();
		String newRemark = customerRemarkTxt.getText();
		boolean worked = false;
		if(DMC.validateBlankChars(newName) && DMC.validateBlankChars(newLastName) && DMC.validateBlankChars(newId) &&
			DMC.validateBlankChars(newNPhone) && DMC.validateBlankChars(newAddress)) {
			if(DMC.searchCustomerPosition(newId) == -1 || newId.equals(customerToChange.getId())) {
				try {
					DMC.changeCustomer(customerToChange, newName, newLastName, newId, newAddress, newNPhone, newRemark);
					msg = "Datos del cliente modificados con ï¿½xito";
					worked = true;
				} catch (IOException exception) {
					msg = "Ha ocurrido un error inesperado";
				}
			} else {
				msg = "Ya existe un cliente con ese id";
			}//End else
		}//End if
		changeInfo.setContentText(msg);
		changeInfo.showAndWait();
		if(worked) {
			closeEmergentWindows(event);
		}//End if
	}//End changeCustomer

	@FXML
	public void changeEmployee(ActionEvent event) {
		Alert changeInfo = new Alert(AlertType.INFORMATION);
		changeInfo.setHeaderText(null);
		String msg = "No pueden haber campos vacï¿½os";
		String newName = employeeNameTxt.getText();
		String newLastName = employeeLastNameTxt.getText();
		String newId = employeeIdTxt.getText();
		boolean worked = false;
		if(DMC.validateBlankChars(newName) && DMC.validateBlankChars(newLastName) && DMC.validateBlankChars(newId)) {
			if(DMC.searchEmployeePosition(newId) == -1 || newId.equals(employeeToChange.getId())) {
				try {
					DMC.changeEmployee(employeeToChange, newName, newLastName, newId);
					msg = "Datos del empleado modificados con ï¿½xito";
					worked = true;
				} catch (IOException exception) {
					msg = "Ha ocurrido un error inesperado";
				}
			} else {
				msg = "Ya existe un empleado con ese id";
			}//End else
		}//End if
		changeInfo.setContentText(msg);
		changeInfo.showAndWait();
		if(worked) {
			closeEmergentWindows(event);
		}//End if
	}//End changeEmployee
	
	@FXML
	public void changeIngredient(ActionEvent event){
		Alert changeInfo = new Alert(AlertType.INFORMATION);
		changeInfo.setHeaderText(null);
		boolean worked = false;
		String msg = "Datos erroneos";
		if(!tIngredientName.getText().isEmpty()){
			try {
				if(DMC.changeIngredient(ingredientToChange,tIngredientName.getText())){
					msg = "Nombre del ingrediente cambiado con exito";
					worked = true;
				}else
					msg = "Ya existe un ingrediente con ese nombre";
			} catch (IOException e) {
				msg = "Ha ocurrido un error inesperado";
			}//End if
		}//End if
		changeInfo.setContentText(msg);
		changeInfo.showAndWait();
		if(worked)
			closeEmergentWindows(event);
	}//End changeIngredient
	
	@FXML
	public String getIngredientToadd(){
		return ingredientToadd;	
	}//End getIngredientToadd

	@FXML
	public void setSizeAndPrice(ActionEvent event){
		boolean worked = false;
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "Tamaño o precio incorrecto.";
		if(!tSize.getText().isEmpty() && !tPrice.getText().isEmpty()){
			try{
				price = Double.parseDouble(tPrice.getText());
				size = tSize.getText();
				worked = true;
			}catch(NumberFormatException e){
				msg = "Valor numerico incorrecto";
				addInfo.setContentText(msg);	
				addInfo.showAndWait();
			}//End catch
		}else{
			addInfo.setContentText(msg);	
			addInfo.showAndWait();
		}//End else
		if(worked)
			closeEmergentWindows(event);
	}//End setSize
	
	public String getSize(){
		return size;
	}//end getSize

	public double getPrice(){
		return price;
	}//End getPrice

	public Product getProduct(){
		return productToAdd;
	}//End getProduct

	public int getAmount(){
		return amount;
	}//End getAmount
	public String getDishTypeToAdd(){
		return dishTypeToadd;
	}
	public void clearDishTypeToAdd(){
		dishTypeToadd = null;
	}//End clearDishTypeToAdd
	@FXML
	public void addDishTypeToProduct(ActionEvent event){
		Alert info = new Alert(AlertType.INFORMATION);
		info.setHeaderText(null);
		boolean worked = false;
		String msg = "Datos erroneos";
		if(!tdish.getText().equals("")){
			dishTypeToadd = tdish.getText();
			msg = "Agregado con exito";
			worked = true;
		}//End ifs
		info.setContentText(msg);
		info.showAndWait();
		if(worked)
			closeEmergentWindows(event);
	}//End addDishTypeToProduct
	@FXML
	public void setDishTypeInTextField(){
		tdish.setText((cbdishes.getValue()!= null)?cbdishes.getValue().getName():"");
	}//End setDishTypeInTextField
	@FXML
	public void changeOrder(ActionEvent event) throws IOException{
		boolean worked = false;
		Alert info = new Alert(AlertType.INFORMATION);
		info.setHeaderText(null);
		String msg = "Datos erroneos";
		if(!tOrderCustomerId.getText().equals("") && !tOrderEmployeeId.getText().equals("")
		   && !cbStatus.getValue().equals("") && !taOrdersRemark.getText().equals("") &&
		   !Lproducts.getItems().isEmpty() && !LAmount.getItems().isEmpty()){
			if(checkCustomer(tOrderCustomerId.getText()) && checkEmployee(tOrderEmployeeId.getText())){ 
				List<Product> products = new ArrayList<Product>();
				List<Integer> amounts = new ArrayList<Integer>();
				products.addAll(Lproducts.getItems());
				amounts.addAll(LAmount.getItems());
				DMC.changeOrder(registerOrder,products,amounts,taOrdersRemark.getText(),
				cbStatus.getValue(),tOrderCustomerId.getText(),tOrderEmployeeId.getText());
				worked = true;
				msg = "Pedido cambiado con exito";
			}else
				msg = "Id del empleado o cliente erroneo, es posible que el id este deshabilitado";
		}//End if
		info.setContentText(msg);
		info.showAndWait();
		if(worked)
			closeEmergentWindows(event);
	}//End changeOrder
//changeOrder(String idCustomer,String idEmployee)
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
	public void addIngredient(ActionEvent event) throws IOException{
		boolean worked = false;
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "No se ha podido agregar el ingrediente, nombre incorrecto.";
		if(tIngredientName != null){
			String ingredientName = this.tIngredientName.getText();
			if(!ingredientName.equals("")){
				tIngredientName.setText("");
				boolean added = DMC.addIngredient(ingredientName);
				if(added){
					msg = "Se ha agregado el ingrediente correctamente.";
					worked = true;
				}
				else
					msg = "Ya existe un ingrediente con ese nombre.";
			}//End if
		}//End if
		addInfo.setContentText(msg);
		addInfo.showAndWait();
		if(worked)
			closeEmergentWindows(event);
	}//End addIngredient

	@FXML
	public void addDishtype(ActionEvent event)throws IOException {
		boolean worked = false;
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		addInfo.setContentText("No se ha podido agregar el tipo de plato, verifica el nombre.");
		if(tDishtypeName != null){
			String dishtypeName = this.tDishtypeName.getText();
			if(!dishtypeName.equals("")){
				tDishtypeName.setText("");
				boolean added = DMC.addDishType(dishtypeName);
				if(added){
					addInfo.setContentText("Se ha agregado el tipo de plato correctamente.");
					worked = true;
				}
			}//End if
		}//End if
		addInfo.showAndWait();
		if(worked)
			closeEmergentWindows(event);
	}//End addDishtype

	@FXML
	private void closeEmergentWindows(ActionEvent event) {
	    Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
	}//End closeEmergentWindowss

	public void clearChangeIngredientData(){
		ingredientToChange = null;
	}//End clearChangeIngredientData
	public void clearAddProductData(){
		productToAdd = null;
		amount = 0;
	}//End
	public void clearChangeDishTypeData(){
		dishtype = null;
	}//End clearChangeDishTypeData

	private void initializeProductsChoiceBox(){
		products = FXCollections.observableArrayList(DMC.getProducts(true));
		cbProducts.setItems(products);
	}//End initializeIngredientsComboBox
	private void initializeRegisterOrder(){
		ObservableList<Product> pd = FXCollections.observableArrayList(registerOrder.getProducts());
		ObservableList<Integer> amo = FXCollections.observableArrayList(registerOrder.getAmount());
		Lproducts.setItems(pd);
		LAmount.setItems(amo);
		receives.setText(registerOrder.getCustomer().getName()+ " "+ registerOrder.getCustomer().getLastName());
		delivery.setText(registerOrder.getEmployee().getName()+ " "+ registerOrder.getCustomer().getLastName());
		createdby.setText(registerOrder.getCreator().getName()+ " "+registerOrder.getCreator().getLastName());
		modifiedby.setText((registerOrder.getModifier()!=null)?registerOrder.getModifier().getName()+ " "+registerOrder.getModifier().getLastName():"No ha sido modificado");
		code.setText(registerOrder.getCode());
		date.setText(registerOrder.getDate());
		status.setText(registerOrder.getStatus());
		statusProgress.adjustValue(initializeSliderProgress(status.getText()));
		remark.setText(registerOrder.getRemark());
	}//End initializeRegisterOrder
	private int initializeSliderProgress(String status){
		int progress = 0;
		switch(status){
		case "SOLICITADO": progress = 25;break;
		case "EN_PROCESO": progress = 50; break;
		case "ENVIADO": progress = 75; break;
		case "ENTREGADO": progress = 100; break;
		}//End switch
		return progress;
	}//End initializeSliderProgress
	@FXML
	public void ListenRemoveIngredientFromProduct(){
		String ing = taIngredientsToChanges.getSelectionModel().getSelectedItem();
		if(ing == null)
			removeElement.setDisable(true);
		else
			removeElement.setDisable(false);
	}//End ListenRemoveIngredientFromProduct
	@FXML
	public void removeIngredientFromChangeProduct(){
		String ing = taIngredientsToChanges.getSelectionModel().getSelectedItem();
		ObservableList<String> curretnIngredients = FXCollections.observableList(taIngredientsToChanges.getItems());
		curretnIngredients.remove(ing);
		taIngredientsToChanges.setItems(curretnIngredients);
	}//End removeIngredientFromChangeProduct
	
	private void initializeProductsListView(){
		ObservableList<String> curretnIngredients = FXCollections.observableList(productToChanges.getIngredientsList());
		taIngredientsToChanges.setItems(curretnIngredients);
	}//End initializeProductsListView
	private void initializeIngredientsComboBox(){
		ingredients = FXCollections.observableArrayList(DMC.getIngredients(true));
		cbIngredients.setItems(ingredients);
	}//End initializeIngredientsComboBox
	private void initializeSizesComboBox(){
		ObservableList<ProductSize> sizes = FXCollections.observableArrayList(DMC.getSizes());
		cbSizes.setItems(sizes);
	}//End initializeSizesComboBox
	private void initializeStatusChoiceBox(){
		ObservableList<String> status = FXCollections.observableArrayList();
		for(int i = findStatus(); i < ST.length; i++){
			status.add(ST[i]);
		}//End for
		cbStatus.setItems(status);
	}//End initializeIngredientsComboBox
	private void initializeDishTypesComboBox(){
		ObservableList<DishType> dish = FXCollections.observableList(DMC.getDishtype(true));
		cbdishes.setItems(dish);
	}//End initializeDishTypesComboBox
	private int findStatus(){
		int index = 0;
		boolean found = false;
		for(int i = 0; i < ST.length && !found;i++){
			if(registerOrder.getStatus().equals(ST[i])){
				index = i;
				found = true;
			}//End if
		}//End for
		return index;
	}//End findStatus

}//End EmergentGUIController