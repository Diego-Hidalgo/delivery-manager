package model;

import java.util.List;

public class Product {
	private int ntr;//number of times it has been ordered
	private double price;
	private ProductSize size;
	private ProductBase product;
	private User modifier;
	private boolean linked;
	
	public Product(ProductBase product,ProductSize size,double price){
		this.product = product;
		this.size = size;
		this.price = price;
		ntr = 0;
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
	public void updateNtr(){
		ntr++;
	}//End updateNtr
	public int getNtr(){
		return ntr;
	}//End getNtr
	public void setModifier(User modifier) {
		this.modifier = modifier;
		product.setModifier(modifier);
	}//End setModifier
	public void setLinked(boolean linked) {
		this.linked = linked;
	}//End setLinked
	public boolean getEnable(){
		return product.getEnable();
	}//End getEnable
	public boolean getLinked() {
		return linked;
	}//End getLinked
	public User getModifier() {
		return modifier;
	}//End getModifier

}//End Product
