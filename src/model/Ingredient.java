package model;

import java.io.Serializable;

public class Ingredient implements Comparable<Ingredient>, Serializable {

	public final static long serialVersionUID = 1L;

	private User creator;
	private User modifier;
	private boolean linked;
	private boolean enable;
	private String name;
	private int numberOflinks;
	public Ingredient(User creator){
		name = new String();
		this.creator= creator;
		enable = true;
		linked = false;
	}//End Constructor

	public Ingredient(User creator,String name){
		this.creator= creator;
		this.name = name;
		enable = true;
		linked = false;
	}//End Constructor
	public void updateNumberOfLinks(int n){
		numberOflinks += n; 
	}//End updateNumberOfLinks
	
	public void updateLinkStatus(){
		linked = (numberOflinks > 0)?true:false;
	}//end updateLinkStatus
	public int getNumberOfLinks(){
		return numberOflinks;
	}
	public void setName(String name) {
		this.name = name;
	}//End setIngredients

	public String getName() {
		return name;
	}//End getIngredients
	public boolean getLinked() {
		return linked;
	}//End getLinked

	public void setEnable(boolean enable){
		this.enable = enable;
	}//End setEnable

	public boolean getEnable(){
		return enable;
	}//End setEnable

	public void setCreator(User creator) {
		this.creator = creator;
	}//End setCreator

	public User getCreator() {
		return creator;
	}//End getCreator

	public void setModifier(User modifier) {
		this.modifier = modifier;
	}//End setModifier

	public User getModifier() {
		return modifier;
	}//End getModifier
	public String toString(){
		return name;
	}//End toString
	@Override
	public int compareTo(Ingredient ingredient) {
		return name.toLowerCase().compareTo(ingredient.getName().toLowerCase());
	}//End compareTo
	
}//End Ingredients
