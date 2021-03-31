package ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	private String productName;
	private String productType;
	private String ingredientToadd;
	private int amount;
	private ObservableList<Ingredient> ingredients;
	private ObservableList<Product> products;
	private Product productToChanges;
	private Product productToAdd;
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

	public EmergentGUIController(DeliveryManagerController DMC){
		this.DMC = DMC;
		size = new String();
	}//End constructors

	@FXML
	public void showExportScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FOLDER+"ExportReportsEmergent.fxml"));
		fxmlLoader.setController(this);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root, null);
		Stage form = new Stage();
		setReportElements();
		form.initModality(Modality.APPLICATION_MODAL);
		form.setTitle("Generar Reportes");
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
		alert.setTitle("Campo Vacío");
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
					msg = "Producto agregado correctamente a la orden";
					worked = true;
				}else
					msg = "La cantidad debe ser un entero positivo";
			}catch(NumberFormatException e){
				msg = "La cantidad debe ser un entero positivo";
			}//End catch
		}//End if
		addInfo.setContentText(msg);
		addInfo.showAndWait();
		if(worked)
			closeEmergentWindows(event);
	}//End AddProduct
	public void initializeForm(){
		tNameToChanges.setText(productToChanges.getName());
		tTypeToChanges.setText(productToChanges.getType());
		tSize.setText(productToChanges.getSize());
		tPrice.setText(String.valueOf(productToChanges.getPrice()));
	}//End initializeForm
	@FXML
	public void changeProductData(ActionEvent event){
		boolean worked = false;
		Alert addInfo = new Alert(AlertType.INFORMATION);
		addInfo.setHeaderText(null);
		addInfo.setContentText("Datos erroneos");
		if(!tNameToChanges.getText().isEmpty() && !tTypeToChanges.getText().isEmpty() && 
			!tSize.getText().isEmpty() && !tPrice.getText().isEmpty() 
			&& !taIngredientsToChanges.getText().isEmpty()){
			try{
				productName = tNameToChanges.getText();
				productType = tTypeToChanges.getText();
				size = tSize.getText();
				ingredientToadd = taIngredientsToChanges.getText();
				price = Double.parseDouble(tPrice.getText());
				tNameToChanges.setText("");
				tTypeToChanges.setText("");
				tSize.setText("");
				taIngredientsToChanges.setText("");
				tPrice.setText("");
				worked = true;
			}catch(NumberFormatException e){
				addInfo.showAndWait();
			}//End catch
		}else 
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
	public String getIngredientToadd(){
		return ingredientToadd;	
	}//End 
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
	public String getProductName(){
		return productName;
	}//End getProductName
	public String getProductType(){
		return productType;
	}//End getProductName
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
	private void initializeProductsComboBox(){
		products = FXCollections.observableArrayList(DMC.getProducts());
		cbProducts.setItems(products);
	}//End initializeIngredientsComboBox
	private void initializeIngredientsComboBox(){
		ingredients = FXCollections.observableArrayList(DMC.getIngredients());
		cbIngredients.setItems(ingredients);
	}//End initializeIngredientsComboBox
}//End EmergentGUIController