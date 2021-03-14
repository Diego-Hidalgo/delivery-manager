package model;

public class DishType {
	
	private boolean linked;
	private String name;
	
	public DishType(){
		name = new String();
		linked = false;
	}//End Constructor
	public DishType(String name){
		this.name = name;
	}//End Constructor
	public void setDishType(String name) {
		this.name = name;
	}//End setPlateType
	public String getDishType() {
		return name;
	}//End setPlateType
	public void setLinked(boolean linked) {
		this.linked = linked;
	}//End setLinked
	public boolean getLinked() {
		return linked;
	}//End getLinked
}//End PlateType
