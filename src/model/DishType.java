package model;

public class DishType {
	
	private String name;
	
	public PlateType(){
		name = new String();
	}//End Constructor
	public PlateType(String name){
		this.name = name;
	}//End Constructor
	public void setPlateType(String name) {
		this.name = name;
	}//End setPlateType
	public String getPlateType() {
		return name;
	}//End setPlateType
}//End PlateType
