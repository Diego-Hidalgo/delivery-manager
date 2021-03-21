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
	public boolean addIngredient(final String ingredient){
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
	private void addIngredient(final Ingredient ingredient){
		int j = 0;
		while(ingredients.get(j).compareTo(ingredient) < 0){j++;}//End while
		ingredients.add(j,ingredient);
	}//End addIngredient
	public int findIngredient(final String ingredient){
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
	public boolean addDishType(final String dishType){
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
	private void addDishType(final DishType dishType){
		int j = 0;
		while(types.get(j).compareTo(dishType) < 0){j++;}//End while
			types.add(dishType);
	}//End addProduct
	public int findDishType(final String dishType){
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

	public void changeIngredient(final String oldName,final String newName){
		int index = findIngredient(oldName);
		if(index >= 0 && findIngredient(newName) < 0)
			ingredients.get(index).setName(newName);
	}//End changeIngredient
	public boolean removeIngredient(final String name){
		int index = findIngredient(name);
		boolean removed = false;
		if(index >= 0)
			if(!ingredients.get(index).getLinked()){
				ingredients.remove(index);
				removed = true;
			}//End if
		return removed;
	}//End removeIngredient
	public void changeDishType(final String oldName,final String newName){
		int index = findDishType(oldName);
		if(index >= 0 && findDishType(newName) < 0)
			types.get(index).setName(newName);
	}//End changeDishType
	public boolean removeDishType(final String name){
		int index = findDishType(name);
		boolean removed = false;
		if(index >= 0)
			if(!types.get(index).getLinked()){
				types.remove(index);
				removed = true;
			}//End if
		return removed;
	}//End removeDishType
	public void disableIngredient(final String name){
		int index = findIngredient(name);
		if(index >= 0)
			ingredients.get(index).setEnable(false);
	}//End disableIngredient
	public void disableDishType(final String name){
		int index = findDishType(name);
		if(index >= 0)
			types.get(index).setEnable(false);
	}//End disableIngredient
	public void addProduct(final String name,final List<Double> price,final List<String> size,final String type){
		if(findProduct(name) < 0){
			if(product.isEmpty()){
				product.add(new Product(name,price,size,type));
			}else{
				double tp = 0;
				for(int i = 0; i < price.size(); i++){tp += price.get(i);}//End for
				Product pd = new Product(name,price,size,type);
				int j = 0;
				while(product.get(j).compareTo(tp) < 0){j++;}//End while
				product.add(j,pd);
			}//End else
		}//End if
	}//End addProduct
	private int findProduct(final String name){
		boolean found = false;
		int index = -1;
		for(int i = 0; i < product.size() && !found; i++) {
			if(product.get(i).getName().equalsIgnoreCase(name)) {
				index = i;
				found = true;
			}//End if
		}//End for
		return index;
	}//End findProduct
	public void changeProduct(final String oldName,final String newName,final List<String> Newingredients,final List<Double> prices,final List<String> sizes,final String typeName){
		int productIndex = findProduct(oldName);
		int dishIndex = findDishType(typeName);
		if(productIndex >= 0){
			product.get(productIndex).setName(newName);
			product.get(productIndex).setPrice(prices);
			product.get(productIndex).setSize(sizes);
			changeDishType(dishIndex,productIndex,typeName);
			changeIngredient(Newingredients,productIndex);
		}//End if
	}//End changeProduct
	private void changeDishType(final int dishIndex,final int productIndex,final String typeName){
		if(dishIndex < 0){
			DishType dish = new DishType(typeName);
			addDishType(dish);
			product.get(productIndex).setType(dish);
		}else
			product.get(productIndex).setType(types.get(dishIndex));
	}//End changeDishType
	private void changeIngredient(final List<String> Newingredients,final int productIndex){
		List<Ingredient> newIngredients = new ArrayList<Ingredient>();
		for(int i = 0; i < Newingredients.size(); i++){
			int ingredientIndex = findIngredient(Newingredients.get(i));
			if(ingredientIndex < 0){
				Ingredient ingredient = new Ingredient(Newingredients.get(i));
				addIngredient(ingredient);
				newIngredients.add(ingredient);
			}else
				newIngredients.add(ingredients.get(ingredientIndex));
		}//End for
		product.get(productIndex).setIngredient(newIngredients);
	}//End changeIngredient
	public boolean removeProduct(String productName){
		boolean removed = false;
		int productIndex = findProduct(productName);
		if(productIndex >= 0)
			if(!product.get(productIndex).getLinked()){
				product.remove(productIndex);
				removed = true;
			}//End if
		return removed;
	}//removeProduct
	public void disableProduct(String productName){
		int productIndex = findProduct(productName);
		if(productIndex >= 0){
			product.get(productIndex).setEnable(false);
		}//End if
	}//End disableProduct
}//End DeliveryManagerController
