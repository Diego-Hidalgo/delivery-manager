package model;

import java.io.Serializable;

public class Ingredient implements Comparable<Ingredient>, Serializable {

	public final static long serialVesionUID = 1;

	private boolean linked;
	private boolean enable;
	private String name;
	
	public Ingredient(){
		name = new String();
		enable = true;
		linked = false;
	}//End Constructor
	public Ingredient(String name){
		this.name = name;
		enable = true;
		linked = false;
	}//End Constructor
	public void setName(String name) {
		this.name = name;
	}//End setIngredients
	public String getName() {
		return name;
	}//End getIngredients
	public void setLinked(boolean linked) {
		this.linked = linked;
	}//End setLinked
	public boolean getLinked() {
		return linked;
	}//End getLinked
	public void setEnable(boolean enable){
		this.enable = enable;
	}//End setEnable
	public boolean getEnable(){
		return enable;
	}//End setEnable
	@Override
	public int compareTo(Ingredient ingredient) {
		return name.compareTo(ingredient.getName());
	}//End compareTo
	
}//End Ingredients
