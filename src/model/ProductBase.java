package model;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class ProductBase implements Serializable {

	public final static long serialVersionUID = 1L;

	//Attributes
	private User creator;
	private User modifier;
	private String name;
	private List<Ingredient> ingredients;
	private DishType type;
	private int numberOfSubproducts;

	/**
	 * constructor of the ProductBase class. <br>
	 *     <b>pre:</b> the parameters are initialized. <br>
	 *     <b>post:</b> a new object of the class ProductBase has been created. <br>
	 * @param creator the user creator of the ProductBase.
	 */
	public ProductBase(User creator){
		name = new String();
		type = new DishType(creator);
		this.creator= creator;
		ingredients = new ArrayList<Ingredient>();
		numberOfSubproducts = 0;
	}//End constructor1

	/**
	 * constructor of the ProductBase class. <br>
	 *     <b>pre:</b> the parameters are initialized. <br>
	 *     <b>post:</b> a new object of the class ProductBase has been created. <br>
	 * @param creator the user creator of the object.
	 * @param name the name of the ProductBase.
	 * @param type the type of the ProductBase.
	 * @param ingredients the list of ingredients of the ProductBase.
	 */
	public ProductBase(User creator,String name,DishType type,List<Ingredient> ingredients){
		this.name = name;
		this.creator= creator;
		this.type = type;
		this.ingredients = ingredients;
		numberOfSubproducts = 0;
	}//End constructor2

	/**
	 * changes the amount of created subproducts of the object.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>s
	 *     <b>post:</b> the number of created subproducts has been changed. <br>
	 * @param n the number of subproducts to add.
	 */
	public void updateNumberOfSubProducts(int n){
		numberOfSubproducts += n;
	}//End updateNumberOfSubProducts

	/**
	 * adds a new ingredient to the list of ingredients.<br>
	 *     <b>pre:</b> the object that calls the method is not null. ingredient is initialized. <br>
	 *     <b>post:</b> a new ingredient has been added to the ProductBase. <br>
	 * @param ingredient the new ingredient to add. ingredient != null.
	 */
	public void addIngredient(Ingredient ingredient){
		ingredients.add(ingredient);
	}//End addIngredients

	/**
	 * returns the ingredients of the object in a String.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> a String with the ingredients. <br>
	 */
	public String getIngredients(){
		String ing = new String();
		for(int i = 0; i < ingredients.size();i++){
			ing += ( (i+1) < ingredients.size() )?ingredients.get(i) + ",":ingredients.get(i);
			if((i+1)%3 == 0)
				ing += "\n";
		}//End for
		return ing;
	}//End getIngredients

	/**
	 * returns the list of ingredients of the ProductBase.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the list of ingredients. <br>
	 */
	public List<Ingredient> getIngredientsList(){
		return ingredients;
	}//End getIngredientsList 

	/**
	 * changes the list of ingredients. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the list of ingredients has been changed. <br>
	 * @param ingredient the new list of ingredients.
	 */
	public void setIngredient(List<Ingredient> ingredient){
		ingredients = ingredient;
	}//End addIngredients

	/**
	 * changes the name of the ProductBase.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the name has been changed. <br>
	 * @param name the new name of the ProductBase.
	 */
	public void setName(String name){
		this.name = name;
	}//End setName

	/**
	 * changes the type of the ProductBase object.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the type has been changed. <br>
	 * @param type the new type of the ProductBase.
	 */
	public void setType(DishType type){
		this.type = type;
	}//End setName

	/**
	 * returns the name of the ProductBase. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the name of the ProductBase. <br>
	 */
	public String getName(){
		return name;
	}//End getName

	/**
	 * returns the type of the ProductBase. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the type of the ProductBase. <br>
	 */
	public String getType(){
		return type.getName();
	}//End getName

	/**
	 * returns the dish type of the ProductBase. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the dish type of the ProductBase. <br>
	 */
	public DishType getDishType(){
		return type;
	}//End getName

	/**
	 * changes the user creator of the ProductBase.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the creator has been changed. <br>
	 * @param creator the new creator of the object. <br>
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}//End setCreator

	/**
	 * returns the user creator of the ProductBase.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the creator of the ProductBase. <br>
	 */
	public User getCreator() {
		return creator;
	}//End getCreator

	/**
	 * changes the modifier of the ProductBase object. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the modifier of the object has been changed. <br>
	 * @param modifier the new modifier of the object.
	 */
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}//End setModifier

	/**
	 * returns the last user modifier of the object.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the modifier of the object. <br>
	 */
	public User getModifier() {
		return modifier;
	}//End getModifier

	/**
	 * returns the name of the ProductBase.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the name of the ProductBase. <br>
	 */
	public String toString(){
		return name;
	}//End toString

	/**
	 * changes the number of subproducts<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the amount of subproducts has been changed. <br>
	 * @param n the new number of subproducts. n > 0
	 */
	public void setNumberOfSubproducts(int n){
		numberOfSubproducts = n;
	}//End setNumberOfSubproducts

	/**
	 * returns the number of created subproducts.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the number of subproducts. <br>
	 */
	public int getNumberOfSubproducts(){
		return numberOfSubproducts;
	}//End getNumberOfSubProducts

}//End Product class