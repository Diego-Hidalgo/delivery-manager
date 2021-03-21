package model;
import java.util.List;
import java.util.ArrayList;

public class DeliveryManagerController {
	private List<Order> order;
	private List<User> user;
	private List<Product> product;
	private List<Employee> employee;
	private List<Ingredient> ingredients;
	private List<DishType> types;

	public DeliveryManagerController(){
		order = new ArrayList<Order>();
		user = new ArrayList<User>();
		product = new ArrayList<Product>();
		employee = new ArrayList<Employee>();
		types = new ArrayList<DishType>();
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
	public boolean addProduct(String dishType){
		boolean added = false;
		if(findDishType(dishType) < 0){
			if(types.isEmpty()){
				types.add(new DishType(dishType));
			}else {
				DishType dt = new DishType(dishType);
				int j = 0;
				while(types.get(j).compareTo(dt) < 0){j++;}//End while
				types.add(dt);
			}//End else
			added = true;
		}//end if
		return added;
	}//End addProduct
	public int findDishType(String dishType){
		int index = -1;
		int start = 0;
		int end = types.size() - 1;
		while( start <= end) {
			int half = (start - end)/2;
			if(types.get(half).getName().compareTo(dishType) == 0){
				index = half;
			}else if(types.get(half).getName().compareTo(dishType) > 0){
				end = half;
			}else{
				start = half;
			}//End else
		}//End while
		return index;
	}//End findDishType

	public void changeIngredient(String oldName, String newName){
		int index = findIngredient(oldName);
		if(index >= 0 && findIngredient(newName) < 0)
			ingredients.get(index).setName(newName);
	}//End changeIngredient
	public boolean removeIngredient(String name){
		int index = findIngredient(name);
		boolean removed = false;
		if(index >= 0)
			if(!ingredients.get(index).getLinked()){
				ingredients.remove(index);
				removed = true;
			}//End if
		return removed;
	}//End removeIngredient
	public void changeDishType(String oldName, String newName){
		int index = findDishType(oldName);
		if(index >= 0 && findDishType(newName) < 0)
			types.get(index).setName(newName);
	}//End changeDishType
	public boolean removeDishType(String name){
		int index = findDishType(name);
		boolean removed = false;
		if(index >= 0)
			if(!types.get(index).getLinked()){
				types.remove(index);
				removed = true;
			}//End if
		return removed;
	}//End removeDishType
	public void disableIngredient(String name){
		int index = findIngredient(name);
		if(index >= 0)
			ingredients.get(index).setEnable(false);
	}//End disableIngredient
	public void disableDishType(String name){
		int index = findDishType(name);
		if(index >= 0)
			types.get(index).setEnable(false);
	}//End disableIngredient
}//End DeliveryManagerController
