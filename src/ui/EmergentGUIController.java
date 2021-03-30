package ui;

import java.io.IOException;
import model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
	private ObservableList<Ingredient> ingredients;
	private Product productToChanges;
	@FXML private TextField tIngredientName;
	@FXML private TextField tDishtypeName;
	@FXML private TextField tSize;
	@FXML private TextField tPrice;
	@FXML private TextField tNewIngredientToProduct;
	@FXML private TextField tSelectedIngredient;
	@FXML private ComboBox<Ingredient> cbIngredients;
	@FXML private TextField tNameToChanges;
	@FXML private TextField tTypeToChanges;
	@FXML private TextArea taIngredientsToChanges;
	public EmergentGUIController(DeliveryManagerController DMC){
		this.DMC = DMC;
		size = new String();
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
	public void showAddSizeAndPriceScene() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"AddSizeAndPriceEmergent.fxml"));
		fxml.setController(this);
		Parent root = fxml.load();
		Scene scene = new Scene(root,null);
		Stage formulario = new Stage();
		formulario.initModality(Modality.APPLICATION_MODAL);
		formulario.setTitle("Agregar tamaño del producto");
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
		formulario.setTitle("Agregar tamaño del producto");
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
	public String getProductName(){
		return productName;
	}//End getProductName
	public String getProductType(){
		return productType;
	}//End getProductName
	public double getPrice(){
		return price;
	}//End getPrice
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
	private void initializeIngredientsComboBox(){
		ingredients = FXCollections.observableArrayList(DMC.getIngredients());
		cbIngredients.setItems(ingredients);
	}//End initializeIngredientsComboBox
}//End EmergentGUIController