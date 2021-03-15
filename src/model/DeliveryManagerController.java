package model;
import java.util.List;
import java.util.ArrayList;

public class DeliveryManagerController {
	private List<Order> order;
	private List<User> user;
	private List<Product> product;
	private List<Employee> employee;
	private List<Ingredient> ingredients;
	
	public DeliveryManagerController(){
		order = new ArrayList<Order>();
		user = new ArrayList<User>();
		product = new ArrayList<Product>();
		employee = new ArrayList<Employee>();
		ingredients = new ArrayList<Ingredient>();
	}//End DeliveryManagerController
	
	public boolean addIngredient(String ingredient){
		boolean added = false;
		if( findIngredient(ingredient) < 0){
			if(ingredients.isEmpty()){
				ingredients.add(new Ingredient(ingredient));
			}else {
				Ingredient in = new Ingredient(ingredient);
				int j = 0;
				while(ingredients.get(j).compareTo(in) < 0){j++;}//End while
				ingredients.add(j,in);
			}//End else
			added = true;
		}//End if
		return added;
	}//End addIngredient
	
	public int findIngredient(String ingredient){
		int index = -1;
		int start = 0;
		int end = ingredients.size() - 1;
		while( start <= end) {
			int half = (start - end)/2;
			if(ingredients.get(half).getName().compareTo(ingredient) == 0){
				index = half;
			}else if(ingredients.get(half).getName().compareTo(ingredient) > 0){
				end = half;
			}else{
				start = half;
			}//End else
		}//End while
		return index;
	}//End findIngredient
	
	public void changeIngredient(String oldName, String newName){
		int index = findIngredient(oldName);
		if(index >= 0)
			ingredients.get(index).setName(newName);
	}//End changeIngredient
	
	public boolean removeIngredient(String name){
		int index = findIngredient(name);
		boolean removed = false;
		if(index >= 0)
			if(!ingredients.get(index).getLinked()){
				ingredients.remove(index);
				removed = true;
			}
		return removed;
	}//End deleteIngredient
	
}//End DeliveryManagerController
