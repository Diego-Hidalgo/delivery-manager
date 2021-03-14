package model;

public class Ingredient {
	
	private boolean linked;
	private String name;
	
	public Ingredient(){
		name = new String();
	}//End Constructor
	public Ingredient(String name){
		this.name = name;
	}//End Constructor
	public void setIngredient(String name) {
		this.name = name;
	}//End setIngredients
	public String getIngredient() {
		return name;
	}//End getIngredients
	public void setLinked(boolean linked) {
		this.linked = linked;
	}//End setLinked
	public boolean getLinked() {
		return linked;
	}//End getLinked
}//End Ingredients
