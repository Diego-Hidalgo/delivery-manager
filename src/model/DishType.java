package model;

public class DishType {
	
	private String name;
	
	public DishType(){
		name = new String();
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
}//End PlateType
