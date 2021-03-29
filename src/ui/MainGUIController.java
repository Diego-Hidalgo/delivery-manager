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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.*;

public class MainGUIController {
	
	@FXML private Pane mainPanel;
	@FXML private TextField tProductName;
	@FXML private TextField tDishtype;
	@FXML private TextArea tSizesAndPices;
	@FXML private TextArea tIngredients;
	private DeliveryManagerController DMC;
	private EmergentGUIController EGC;
	private final String FOLDER = "fxml/";
	public MainGUIController(DeliveryManagerController DMC,EmergentGUIController EGC){
		this.DMC = DMC;
		this.EGC = EGC;
	}//End constructor
	
	@FXML
	public void showSceneLogin() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"PantallaDePruebas.fxml"));
		fxml.setController(this);
		Parent loginWindow = fxml.load();
		mainPanel.getChildren().setAll(loginWindow);
		Stage st = (Stage) loginWindow.getScene().getWindow();
		st.setHeight(400);
		st.setWidth(366);
		st.setResizable(false);
	}//End showSceneLogin
	@FXML
	public void showSceneRegisterProduct() throws IOException{
		FXMLLoader fxml = new FXMLLoader(getClass().getResource(FOLDER+"RegisterProductWindows.fxml"));
		fxml.setController(this);
		Parent registerProduct = fxml.load();
		mainPanel.getChildren().setAll(registerProduct);
		Stage st = (Stage) registerProduct.getScene().getWindow();
		st.setTitle("Registrar productos");
		st.setHeight(570);
		st.setWidth(440);
		st.setResizable(false);
	}//End showSceneRegisterProduct
	@FXML
	public void addProduct(){
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
		String msg = "El tamaño y precio ingresado ya existen para este producto";
		EGC.showAddSizeAndPriceScene();
		String sizesAndPrices = tSizesAndPices.getText();
		String sizeAndPrice = (!EGC.getSize().isEmpty())?EGC.getSize()+ "-" + EGC.getPrice():"";
		if(!checkSizeAndPrice(sizeAndPrice)){
			sizesAndPrices += (tSizesAndPices.getText().isEmpty())?sizeAndPrice:"\n"+sizeAndPrice;
			tSizesAndPices.setText(sizesAndPrices);
			msg = "Tamaño y  precio agregados con exito";
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
	}@FXML 
	public void showDishTypes(){
		System.out.println(DMC.getDishtype());
	}
}//End MainGUIController
