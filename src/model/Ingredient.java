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
	 * Updates the number of links of the object Ingredient. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the number of links have been updated. <br>
	 * @param n the number of links to be added.
	 */
	public void updateNumberOfLinks(int n){
		numberOflinks += n; 
	}//End updateNumberOfLinks

	/**
	 * updates the links status.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the new status is true if the number of links is zero, otherwise is false. <br>
	 */
	public void updateLinkStatus(){
		linked = (numberOflinks > 0)?true:false;
	}//end updateLinkStatus

	/**
	 * returns the number of links. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the number of links. <br>
	 */
	public int getNumberOfLinks(){
		return numberOflinks;
	}//End getNumberOfLinks

	/**
	 * changes the name.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the name has been changed. <br>
	 * @param name the new name of the object.
	 */
	public void setName(String name) {
		this.name = name;
	}//End setIngredients

	/**
	 * returns the name of the object.<br>
	 *     <b>pre</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the name of the object. <br>
	 */
	public String getName() {
		return name;
	}//End getIngredients

	/**
	 * returns the linked status of the object. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the linked status. <br>
	 */
	public boolean getLinked() {
		return linked;
	}//End getLinked

	/**
	 * changes the enabled status. <br>
	 *     <b>pre:</b> the object that calls the method is not null.<br>
	 *     <b>post:</b> the enabled status has been changed. <br>
	 * @param enable the new enabled status. true if enabled, false if not.
	 */
	public void setEnable(boolean enable){
		this.enable = enable;
	}//End setEnable

	/**
	 * returns the enabled status of the object<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the enabled status.
	 */
	public boolean getEnable(){
		return enable;
	}//End setEnable

	/**
	 * changes the creator of the object. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the creator has been changed. <br>
	 * @param creator the new creator of the object. creator != null.
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}//End setCreator

	/**
	 * returns the creator of the object. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the creator of the class. <br>
	 */
	public User getCreator() {
		return creator;
	}//End getCreator

	/**
	 * changes the last user that modified the object.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the modifier of the object has been changed. <br>
	 * @param modifier the new modifier of the object. modifier != null.
	 */
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}//End setModifier

	/**
	 * returns the modifier of the object. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the modifier of the object. <br>
	 */
	public User getModifier() {
		return modifier;
	}//End getModifier

	/**
	 * returns the name of the object.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the name of the object. <br>
	 */
	public String toString(){
		return name;
	}//End toString

	/**
	 * compares the name of the current object to ather Ingredient object. <br>
	 *     <b>pre:</b> the method and the parameter are not null. <br>
	 *     <b>post:</b> a number corresponding to the comparison usgin String.compareTo. <br>
	 * @param ingredient the other object to compare to. ingredient != null.
	 */
	@Override
	public int compareTo(Ingredient ingredient) {
		return name.toLowerCase().compareTo(ingredient.getName().toLowerCase());
	}//End compareTo
	
}//End Ingredients class
