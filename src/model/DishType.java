package model;

public class DishType implements Comparable<DishType>{
	
	private boolean linked;
	private boolean enabled;
	private String name;
	
	public DishType(){
		name = new String();
		linked = false;
	}//End Constructor
	public DishType(String name){
		this.name = name;
	}//End Constructor
	public void setName(String name) {
		this.name = name;
	}//End setPlateType
	public String getName() {
		return name;
	}//End setPlateType
	public void setLinked(boolean linked) {
		this.linked = linked;
	}//End setLinked
	public boolean getLinked() {
		return linked;
	}//End getLinked
	public void setEnable(boolean enabled) {
		this.enabled = enabled;
	}//End setEnabled
	public boolean getEnable() {
		return enabled;
	}//End getEnabled
	@Override
	public int compareTo(DishType dt) {
		return name.compareTo(dt.getName());
	}
}//End PlateType
