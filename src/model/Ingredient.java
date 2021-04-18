package model;

import java.io.Serializable;

public class Ingredient implements Comparable<Ingredient>, Serializable {

	public final static long serialVersionUID = 1L;

	//Attributes
	private User creator;
	private User modifier;
	private boolean linked;
	private boolean enable;
	private String name;
	private int numberOflinks;

	/**
	 * Creates a new object of the Ingredient class. <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param creator
	 */
	public Ingredient(User creator){
		name = new String();
		this.creator= creator;
		enable = true;
		linked = false;
	}//End Constructor

	/**
	 * Creates a new object of the ingredient class. <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param creator
	 * @param name
	 */
	public Ingredient(User creator,String name){
		this.creator= creator;
		this.name = name;
		enable = true;
		linked = false;
	}//End Constructor

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
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}//End setIngredients

	/**
	 * <br>
	 *     <b>pre</b>
	 *     <b>post:</b>
	 * @return
	 */
	public String getName() {
		return name;
	}//End getIngredients

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
	}//End setEnable

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param creator
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}//End setCreator

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public User getCreator() {
		return creator;
	}//End getCreator

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param modifier
	 */
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}//End setModifier

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
	public String toString(){
		return name;
	}//End toString

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param ingredient
	 * @return
	 */
	@Override
	public int compareTo(Ingredient ingredient) {
		return name.toLowerCase().compareTo(ingredient.getName().toLowerCase());
	}//End compareTo
	
}//End Ingredients
