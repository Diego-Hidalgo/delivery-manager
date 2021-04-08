package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmergentGUIController {
	
	private final String FOLDER = "fxml/";
	
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
	@FXML private TextField tAmount;
	@FXML private TextField tIngredientName;
	@FXML private TextField tDishtypeName;
	@FXML private TextField tSize;
	@FXML private TextField tPrice;
	@FXML private TextField tNewIngredientToProduct;
	@FXML private TextField tSelectedIngredient;
	@FXML private ComboBox<Ingredient> cbIngredients;
	@FXML private ComboBox<Product> cbProducts;
	@FXML private ChoiceBox<String> reportType;
	@FXML private TextField pathTxt;
	@FXML private DatePicker initialDate;
	@FXML private DatePicker finishDate;
	@FXML private TextField initialHour;
	@FXML private TextField finishHour;
	@FXML private TextField separatorTxt;
	@FXML private TextField tNameToChanges;
	@FXML private TextField tTypeToChanges;
	@FXML private TextArea taIngredientsToChanges;
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
	//Import data
	@FXML private ChoiceBox<String> importType;
	@FXML private TextField mSeparator;
	@FXML private TextField sSeparator;
	@FXML private TextField tSeparator;
	
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

	public void importData(ActionEvent event) throws IOException {
		if(importType.getValue() != null) {
			if(importType.getValue().equals("Clientes")) {
				importCustomersData(event);
			} else if (importType.getValue().equals("Productos")){
				importProductsData(event);
			} else {
				importOrdersData(event);
			}//End else
		} else {
			emptyFieldAlert();
		}//End else
	}//End importData

	public void importCustomersData(ActionEvent event) throws IOException {
		if(!pathTxt.getText().isEmpty() && DMC.validateBlankChars(mSeparator.getText())) {
			if(DMC.importCustomerData(new File(pathTxt.getText()), mSeparator.getText())) {
				importDataStatusAlert("Se ha importado toda la información del archivo correctamente,");
			} else {
				importDataStatusAlert("No se ha podido importar todos los datos del archivo.");
			}//End else
			closeEmergentWindows(event);
		} else {
			emptyFieldAlert();
		}//End else
	}//End importCustomersData

	public void importProductsData(ActionEvent event) throws IOException {
		if(!pathTxt.getText().isEmpty() && DMC.validateBlankChars(mSeparator.getText()) && DMC.validateBlankChars(sSeparator.getText())) {
			if(DMC.importProducts(new File(pathTxt.getText()), mSeparator.getText(), sSeparator.getText())) {
				importDataStatusAlert("Se ha importado toda la información del archivo correctamente,");
			} else {
				importDataStatusAlert("No se ha podido importar todos los datos del archivo.");
			}//End else
			closeEmergentWindows(event);
		} else {
			emptyFieldAlert();
		}//End else
	}//End importProductsData

	public void importOrdersData(ActionEvent event) throws IOException {
		if(!pathTxt.getText().isEmpty() && DMC.validateBlankChars(mSeparator.getText()) && DMC.validateBlankChars(sSeparator.getText()) &&
			DMC.validateBlankChars(tSeparator.getText())) {
			if(DMC.importOrders(new File(pathTxt.getText()), mSeparator.getText(), sSeparator.getText(), tSeparator.getText())) {
				importDataStatusAlert("Se ha importado toda la información del archivo correctamente,");
			} else {
				importDataStatusAlert("No se ha podido importar todos los datos del archivo.");
			}//End else
			closeEmergentWindows(event);
		} else {
			emptyFieldAlert();
		}//End else
	}//End importOrdersData

	@FXML
	public void showExportScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"ExportReportsEmergent.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root, null);
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
		Stage form = new Stage();
		form.initModality(Modality.APPLICATION_MODAL);
		form.setTitle("Agregar tipo de plato");
		form.setScene(scene);
		form.setResizable(false);
		form.showAndWait();
	}//End showRegisterDihstypeScene

	@FXML
	public void showChangeDihstypeScene(DishType d) throws IOException{
		dishtype = d;
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"ChangeDishTypeEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
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
		Stage formulario = new Stage();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Agregar tama�o del producto");
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

	@FXML
	public void showChangeIngredientToProduct() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"changeIngredientsFromProductsEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		Stage formulario = new Stage();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Cambiar ingrediente");
		formulario.setScene(scene);
		formulario.setResizable(false);
		formulario.showAndWait();
	}//End showRegisterDihstypeScene

	@FXML
	public void showAddIngredientToProductScene() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"AddIngredienteToProductEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		Stage formulario = new Stage();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Agregar tama�o del producto");
		formulario.setScene(scene);
		formulario.setResizable(false);
		initializeIngredientsComboBox();
		formulario.showAndWait();
	}//End showRegisterDihstypeScene

	@FXML
	public void showChangeProducts(Product p) throws IOException{
		productToChanges = p;
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"ChangeProductEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		Stage formulario = new Stage();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Cambiar producto");
		formulario.setScene(scene);
		formulario.setResizable(false);
		initializeForm();
		formulario.showAndWait();
	}//End showRegisterDihstypeScene

	@FXML
	public void showAddProductsToOrderEmergent() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"GetProductEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		Stage formulario = new Stage();
		initializeProductsComboBox();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Agregar producto");
		formulario.setScene(scene);
		formulario.setResizable(false);
		formulario.showAndWait();
	}//End showRegisterDihstypeScene

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
	public void showCompleteOrderScene(Order o) throws IOException{
		registerOrder = o;
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"VisualizeCompleteOrderEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		Stage registerOrder = new Stage();
		registerOrder.initModality(Modality.APPLICATION_MODAL);
		registerOrder.setTitle("Ver registro");
		registerOrder.setScene(scene);
		registerOrder.setResizable(false);
		initializeRegisterOrder();
		registerOrder.showAndWait();
	}//End showRegisterIngredienteScene
	public void initializeForm(){
		tNameToChanges.setText(productToChanges.getName());
		tTypeToChanges.setText(productToChanges.getType());
		tSize.setText(productToChanges.getSize());
		tPrice.setText(String.valueOf(productToChanges.getPrice()));
	}//End initializeForm

	@FXML
	public void changeProductData(ActionEvent event) throws IOException{
		boolean worked = false;
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		String msg = "Datos erroneos";
		if(!tNameToChanges.getText().isEmpty() && !tTypeToChanges.getText().isEmpty() && 
			!tSize.getText().isEmpty() && !tPrice.getText().isEmpty() 
			&& !taIngredientsToChanges.getText().isEmpty()){
			try{
				price = Double.parseDouble(tPrice.getText());
				if(price >= 0){
					if(DMC.changeProduct(productToChanges,tNameToChanges.getText(),
							Arrays.asList(taIngredientsToChanges.getText().split("\n")),
							price,tSize.getText(),tTypeToChanges.getText())){
						msg = "Se ha cambiado el producto con exito";
						worked = true;
					}else
						msg = "No se pud� cambiar el producto, ya existe otro con ese nombre";
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
	public void changesIngredients(ActionEvent event){
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		boolean worked = false;
		String currentIngredients = taIngredientsToChanges.getText();
		if(!tNewIngredientToProduct.getText().isEmpty()){
			currentIngredients += (!currentIngredients.isEmpty())?"\n" + tNewIngredientToProduct.getText(): tNewIngredientToProduct.getText();
			taIngredientsToChanges.setText(currentIngredients);
			worked = true;
		}else {
			addInfo.setContentText("Datos erroneos");
			addInfo.showAndWait();
		}
		if(worked)
		 closeEmergentWindows(event);
	}//End changesIngredients

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
	public void setIngredientToadd(ActionEvent event){
		boolean worked = false;
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		ingredientToadd = "";
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
						msg = "La contraseña debe contener al menos 7 caracteres";
					}//End else
				} else {
					msg = "El nombre de usuario ya est� en uso";
				}//End else
			} else {
				msg = "Las contrase�as no coinciden";
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
					msg = "Datos del cliente modificados con �xito";
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
		String msg = "No pueden haber campos vac�os";
		String newName = employeeNameTxt.getText();
		String newLastName = employeeLastNameTxt.getText();
		String newId = employeeIdTxt.getText();
		boolean worked = false;
		if(DMC.validateBlankChars(newName) && DMC.validateBlankChars(newLastName) && DMC.validateBlankChars(newId)) {
			if(DMC.searchEmployeePosition(newId) == -1 || newId.equals(employeeToChange.getId())) {
				try {
					DMC.changeEmployee(employeeToChange, newName, newLastName, newId);
					msg = "Datos del empleado modificados con �xito";
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
		String msg = "Tama�o o precio incorrecto.";
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

	private void initializeProductsComboBox(){
		products = FXCollections.observableArrayList(DMC.getProducts(true));
		cbProducts.setItems(products);
	}//End initializeIngredientsComboBox
	private void initializeRegisterOrder(){
		ObservableList<Product> pd = FXCollections.observableArrayList(registerOrder.getProducts());
		ObservableList<Integer> amo = FXCollections.observableArrayList(registerOrder.getAmount());
		Lproducts.setItems(pd);
		LAmount.setItems(amo);
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
	private void initializeIngredientsComboBox(){
		ingredients = FXCollections.observableArrayList(DMC.getIngredients(true));
		cbIngredients.setItems(ingredients);
	}//End initializeIngredientsComboBox

}//End EmergentGUIController