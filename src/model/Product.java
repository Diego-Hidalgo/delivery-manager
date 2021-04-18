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
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param product
	 * @param size
	 * @param price
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
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param name
	 * @param ingredients
	 * @param type
	 */
	public void changesProductBase(String name,List<Ingredient> ingredients,DishType type){
		product.setName(name);
		product.setIngredient(ingredients);
		product.setType(type);
	}//End setProduct

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public ProductBase getProductBase(){
		return product;
	}//End getProductBase

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param price
	 */
	public void setPrice(double price){
		this.price = price;
	}//End setPrice

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param size
	 */
	public void setSize(String size){
		this.size.setSize(size);
	}//End setSize

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public double getPrice(){
		return price;
	}//End getPrice

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param n
	 */
	public void updateNumberOfLinks(int n){
		numberOflinks += n; 
	}//End updateNumberOfLinks

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 */
	public void updateLinkStatus(){
		linked = (numberOflinks > 0)?true:false;
	}//end updateLinkStatus

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public int getNumberOfLinks(){
		return numberOflinks;
	}//End getNumberOfLinks

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public String getSize(){
		return size.getSize();
	}//End getPrice

	/**
	 * <br>
	 *    <b>pre:</b>
	 *    <b>post:</b>
	 * @return
	 */
	public String getIngredients(){
		return product.getIngredients();
	}//End getIngredients

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
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
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param modifier
	 */
	public void setModifier(User modifier) {
		this.modifier = modifier;
		product.setModifier(modifier);
	}//End setModifier

	/*public void setLinked(boolean linked) {
		this.linked = linked;
	}//End setLinked*/

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param enable
	 */
	public void setEnable(boolean enable){
		 this.enable = enable;
	}//End setEnable

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public boolean getEnable(){
		return enable;
	}//End getEnable

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public boolean getLinked() {
		return linked;
	}//End getLinked

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public String getName(){
		return product.getName();
	}//End getName

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public String getType(){
		return product.getType();
	}//End getName

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
	@Override
	public String toString(){
		String info = getName() + " | " + getSize();
		return info;
	}//End toString

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param price
	 * @return
	 */
	@Override
	public int compareTo(Double price) {
		return price.compareTo(this.price);
	}//End compareTo

}//End Product
