package model;
import java.util.List;
import java.util.ArrayList;

public class Product {
	private String name;
	private List<Double> price;
	private List<String> size;
	private List<Ingredient> ingredients;
	private DishType type;
	private boolean linked;
	
	public Product(){
		name = new String();
		price = new ArrayList<Double>();
		size = new ArrayList<String>();
		type = new DishType();
	}//End constructor1
	public Product(String name, List<Double> price, List<String> size,String type){
		this.name = name;
		this.price = price;
		this.size = size;
		this.type = new DishType(type);
	}//End constructor2
	public void addIngredients(String name){
		ingredients.add(new Ingredient(name));
	}//End addIngredients
	public void setName(String name){
		this.name = name;
	}//End setName
	public void setPrice(List<Double> price){
		this.price = price;
	}//End setName
	public void setSize(List<String> size){
		this.size = size;
	}//End setName
	public void setType(String type){
		this.type.setDishType(type);
	}//End setName
	public String getName(){
		return name;
	}//End getName
	public List<Double> getPrice(){
		return price;
	}//End getName
	public List<String> getSize(){
		return size;
	}//End getName
	public String getType(){
		return type.toString();
	}//End getName
	public void setLinked(boolean linked) {
		this.linked = linked;
	}//End setLinked
	public boolean getLinked() {
		return linked;
	}//End getLinked
}//End Product
