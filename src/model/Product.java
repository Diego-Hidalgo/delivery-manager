package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable, Comparable<Double> {

	public final static long serialVersionUID = 1L;

	//Attributes
	private double price;
	private ProductSize size;
	private ProductBase product;
	private User modifier;
	private int numberOflinks;
	private boolean linked;
	private boolean enable;

	/**
	 * constructor of the Product class. Creates a specific product from a ProductBase. <br>
	 *     <b>pre:</b> parameters are initialized. <br>
	 *     <b>post:</b> a new Object of the Product class has been created. <br>
	 * @param product the base product of the product. product != null.
	 * @param size the size of the product.
	 * @param price the price of the product. price > 0.
	 */
	public Product(ProductBase product,ProductSize size,double price){
		this.product = product;
		this.size = size;
		this.price = price;
		enable = true;
		linked = false;
		numberOflinks = 0;
	}//End Product

	/**
	 * changes the attributes of the product base of the product. <br>
	 *     <b>pre:</b> the object that calls the method is not null, parameters are initialized. <br>
	 *     <b>post:</b> the attributes of the product base have been changed. <br>
	 * @param name the new name of the product base.
	 * @param ingredients the new list of ingredients of the product base. <br>
	 * @param type the new type of the product base. <br>
	 */
	public void changesProductBase(String name,List<Ingredient> ingredients,DishType type){
		product.setName(name);
		product.setIngredient(ingredients);
		product.setType(type);
	}//End setProduct

	/**
	 * returns the product base of the specific product. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the product base of the product. <br>
	 */
	public ProductBase getProductBase(){
		return product;
	}//End getProductBase

	/**
	 * changes the price of the product. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>s
	 *     <b>post:</b> the price has been changed. <br>
	 * @param price the new price of the product. price > 0.
	 */
	public void setPrice(double price){
		this.price = price;
	}//End setPrice

	/**
	 * returns the size of the product.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the size of the product. <br>
	 * @param size the new size of the product. <br>
	 */
	public void setSize(String size){
		this.size.setSize(size);
	}//End setSize

	/**
	 * returns the price of the product. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the price of the product. <br>
	 */
	public double getPrice(){
		return price;
	}//End getPrice

	/**
	 * changes the number of links of the product.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the number of links have been updated. <br>
	 * @param n the number of links to be added or subtracted. <br>
	 */
	public void updateNumberOfLinks(int n){
		numberOflinks += n; 
	}//End updateNumberOfLinks

	/**
	 * changes the linked status of the product<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> true if number of links is greater than zero, false if not. <br>
	 */
	public void updateLinkStatus(){
		linked = (numberOflinks > 0)?true:false;
	}//end updateLinkStatus

	/**
	 * returns the number of links of the object. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> a number corresponding to the amount of links of the product. <br>
	 */
	public int getNumberOfLinks(){
		return numberOflinks;
	}//End getNumberOfLinks

	/**
	 * returns the size of the size of the product.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the size of the product. <br>
	 */
	public String getSize(){
		return size.getSize();
	}//End getSize

	/**
	 * returns the ingredients of the ProductBase.<br>
	 *    <b>pre:</b> the object that calls the method is not null. <br>
	 *    <b>post:</b> the list of ingredients of the ProductBase. <br>
	 */
	public String getIngredients(){
		return product.getIngredients();
	}//End getIngredients

	/**
	 * returns the list of the ingredients of the product. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the list of the ingredients in the product. <br>
	 */
	public List<String> getIngredientsList(){
		List<String> il = new ArrayList<String>();
		List<Ingredient> i = product.getIngredientsList();
		for(int j = 0; j < i.size() ;j++){
			il.add(i.get(j).getName());
		}//End for
		return il;
	}//End getIngredientsList

	/**
	 * changes the last user that modified the product.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the modifier has been changed. <br>
	 * @param modifier the new modifier of the object. <br>
	 */
	public void setModifier(User modifier) {
		this.modifier = modifier;
		product.setModifier(modifier);
	}//End setModifier

	/**
	 * changes the enabled status of the product.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the enabled status has been changed. <br>
	 * @param enable the new enabled status of the product. <br>
	 */
	public void setEnable(boolean enable){
		 this.enable = enable;
	}//End setEnable

	/**
	 * returns the enabled status of the product.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> true if enabled, false if not. <br>
	 */
	public boolean getEnable(){
		return enable;
	}//End getEnable

	/**
	 * returns the linked status of the product.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> true if linked, false if not. <br>
	 */
	public boolean getLinked() {
		return linked;
	}//End getLinked

	/**
	 * returns the name of the product. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the name of the product. <br>
	 */
	public String getName(){
		return product.getName();
	}//End getName

	/**
	 * return the type of the product. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the type of the product. <br>
	 */
	public String getType(){
		return product.getType();
	}//End getName

	/**
	 * returns the user modifier of the object. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the modifier of the object. <br>
	 */
	public User getModifier() {
		return modifier;
	}//End getModifier

	/**
	 * returns the name of the product and the size in a String.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> a String that contains the name and size of the product. <br>
	 */
	@Override
	public String toString(){
		String info = getName() + " | " + getSize();
		return info;
	}//End toString

	/**
	 * compares the price of the current object with other price.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> returns a number corresponding to the comparison made by Double.compareTo(). <br>
	 * @param price
	 */
	@Override
	public int compareTo(Double price) {
		return price.compareTo(this.price);
	}//End compareTo

}//End Product class
