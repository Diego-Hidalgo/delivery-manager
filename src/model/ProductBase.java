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
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param creator
	 */
	public ProductBase(User creator){
		name = new String();
		type = new DishType(creator);
		this.creator= creator;
		ingredients = new ArrayList<Ingredient>();
		numberOfSubproducts = 0;
	}//End constructor1

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param creator
	 * @param name
	 * @param type
	 * @param ingredients
	 */
	public ProductBase(User creator,String name,DishType type,List<Ingredient> ingredients){
		this.name = name;
		this.creator= creator;
		this.type = type;
		this.ingredients = ingredients;
		numberOfSubproducts = 0;
	}//End constructor2

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param n
	 */
	public void updateNumberOfSubProducts(int n){
		numberOfSubproducts += n;
	}//End updateNumberOfSubProducts

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param ingredient
	 */
	public void addIngredient(Ingredient ingredient){
		ingredients.add(ingredient);
	}//End addIngredients

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
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
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public List<Ingredient> getIngredientsList(){
		return ingredients;
	}//End getIngredientsList 

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param ingredient
	 */
	public void setIngredient(List<Ingredient> ingredient){
		ingredients = ingredient;
	}//End addIngredients

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}//End setName

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param type
	 */
	public void setType(DishType type){
		this.type = type;
	}//End setName

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public String getName(){
		return name;
	}//End getName

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public String getType(){
		return type.getName();
	}//End getName

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public DishType getDishType(){
		return type;
	}//End getName

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param creator
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}//End setCreator

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public User getCreator() {
		return creator;
	}//End getCreator

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param modifier
	 */
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}//End setModifier

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public User getModifier() {
		return modifier;
	}//End getModifier

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public String toString(){
		return name;
	}

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param n
	 */
	public void setNumberOfSubproducts(int n){
		numberOfSubproducts = n;
	}//End setNumberOfSubproducts

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public int getNumberOfSubproducts(){
		return numberOfSubproducts;
	}//End getNumberOfSubProducts

}//End Product