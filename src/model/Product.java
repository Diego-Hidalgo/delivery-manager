package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {

	public final static long serialVersionUID = 1L;

	private double price;
	private ProductSize size;
	private ProductBase product;
	private User modifier;
	private boolean linked;
	private boolean enable;
	
	public Product(ProductBase product,ProductSize size,double price){
		this.product = product;
		this.size = size;
		this.price = price;
		enable = true;
	}//End Product

	public void changesProductBase(String name,List<Ingredient> ingredients,DishType type){
		product.setName(name);
		product.setIngredient(ingredients);
		product.setType(type);
	}//End setProduct

	public ProductBase getProductBase(){
		return product;
	}//End getProductBase

	public void setPrice(double price){
		this.price = price;
	}//End setPrice

	public void setSize(String size){
		this.size.setSize(size);
	}//End setSize

	public double getPrice(){
		return price;
	}//End getPrice

	public String getSize(){
		return size.getSize();
	}//End getPrice
	public String getIngredients(){
		return product.getIngredients();
	}//End getIngredients
	public List<String> getIngredientsList(){
		List<String> il = new ArrayList<String>();
		List<Ingredient> i = product.getIngredientsList();
		for(int j = 0; j < i.size() ;j++){
			il.add(i.get(j).getName());
		}//End for
		return il;
	}//End getIngredientsList
	public void setModifier(User modifier) {
		this.modifier = modifier;
		product.setModifier(modifier);
	}//End setModifier

	public void setLinked(boolean linked) {
		this.linked = linked;
	}//End setLinked
	public void setEnable(boolean enable){
		 this.enable = enable;
	}
	public boolean getEnable(){
		return enable;
	}//End getEnable

	public boolean getLinked() {
		return linked;
	}//End getLinked
	public String getName(){
		return product.getName();
	}//End getName
	public String getType(){
		return product.getType();
	}//End getName
	public User getModifier() {
		return modifier;
	}//End getModifier
	@Override
	public String toString(){
		String info = getName() + " x " + price;
		return info;
	}//End toString
}//End Product
