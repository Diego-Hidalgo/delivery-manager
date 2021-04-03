package model;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class ProductBase implements Serializable {//Comparable<ProductBase>

	public final static long serialVersionUID = 1;

	private User creator;
	private User modifier;
	private String name;
	private List<Ingredient> ingredients;
	private DishType type;
	
	public ProductBase(User creator){
		name = new String();
		type = new DishType(creator);
		this.creator= creator;
		ingredients = new ArrayList<Ingredient>();
	}//End constructor1

	public ProductBase(User creator,String name,DishType type,List<Ingredient> ingredients){
		this.name = name;
		this.creator= creator;
		this.type = type;
		this.ingredients = ingredients;
	}//End constructor2

	public void addIngredient(Ingredient ingredient){
		ingredients.add(ingredient);
	}//End addIngredients
	public String getIngredients(){
		String ing = new String();
		for(int i = 0; i < ingredients.size();i++){
			ing += ( (i+1) < ingredients.size() )?ingredients.get(i) + ",":ingredients.get(i);
			if((i+1)%3 == 0)
				ing += "\n";
		}//End for
		return ing;
	}//End getIngredients
	public void setIngredient(List<Ingredient> ingredient){
		ingredients = ingredient;
	}//End addIngredients

	public void setName(String name){
		this.name = name;
	}//End setName

	public void setType(DishType type){
		this.type = type;
	}//End setName

	public String getName(){
		return name;
	}//End getName

	public String getType(){
		return type.getName();
	}//End getName

	public void setCreator(User creator) {
		this.creator = creator;
	}//End setCreator

	public User getCreator() {
		return creator;
	}//End getCreator

	public void setModifier(User modifier) {
		this.modifier = modifier;
	}//End setModifier

	public User getModifier() {
		return modifier;
	}//End getModifier
	public String toString(){
		String info = "Nombre: " + name + "\nIngredientes:";
		for(int i = 0; i < ingredients.size();i++)
			info += ingredients.get(i).getName()+"\n";
		return info;
	}
	/**@Override
	public int compareTo(ProductBase tPrice) {
		return (getTotalPrice()).compareTo(tPrice.getTotalPrice());
	}//End compareTo*/
}//End Product
