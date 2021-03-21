package model;
import java.util.List;
import java.util.ArrayList;

public class Product implements Comparable<Double>{
	private String name;
	private List<Double> price;
	private List<String> size;
	private List<Ingredient> ingredients;
	private DishType type;
	private boolean linked;
	private boolean enable;
	
	public Product(){
		name = new String();
		price = new ArrayList<Double>();
		size = new ArrayList<String>();
		type = new DishType();
		linked = false;
		enable = true;
	}//End constructor1
	public Product(String name, List<Double> price, List<String> size,String type){
		this.name = name;
		this.price = price;
		this.size = size;
		this.type = new DishType(type);
		linked = false;
		enable = true;
	}//End constructor2
	public void addIngredient(Ingredient ingredient){
		ingredients.add(ingredient);
	}//End addIngredients
	public void setIngredient(List<Ingredient> ingredient){
		ingredients = ingredient;
	}//End addIngredients
	public void setName(String name){
		this.name = name;
	}//End setName
	public void addPrice(double price){
		this.price.add(price);
	}//End addPrice
	public void setPrice(List<Double> price){
		this.price = price;
	}//End setName
	public List<Double> getPrice(){
		return price;
	}//End getName
	public void addSize(String size){
		this.size.add(size);
	}//End addPrice
	public void addSize(final List<String> size){
		for(int i = 0; i < size.size(); i++)
		 this.size.add(size.get(i));
	}//End addPrice
	public void setSize(List<String> size){
		this.size = size;
	}//End setName
	public List<String> getSize(){
		return size;
	}//End getName
	public void setType(DishType type){
		this.type = type;
	}//End setName
	public String getName(){
		return name;
	}//End getName
	public String getType(){
		return type.getName();
	}//End getName
	public void setLinked(boolean linked) {
		this.linked = linked;
	}//End setLinked
	public boolean getLinked() {
		return linked;
	}//End getLinked
	public void setEnable(boolean enable) {
		this.enable = enable;
	}//End setLinked
	public boolean getEnable() {
		return enable;
	}//End getLinked
	@Override
	public int compareTo(Double tPrice) {
		double p = 0;
		for(int i = 0; i < price.size(); i++){p += price.get(i);}//End for
		tPrice.compareTo(p);
		return 0;
	}
}//End Product
