package model;

import java.io.Serializable;

public class DishType implements Comparable<DishType>, Serializable {

	public final static long serialVersionUID = 1;

	private User creator;
	private User modifier;
	private boolean linked;
	private boolean enabled;
	private String name;
	
	public DishType(User creator){
		name = new String();
		this.creator= creator;
		linked = false;
	}//End Constructor

	public DishType(User creator,String name){
		this.creator= creator;
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

	@Override
	public int compareTo(DishType dt) {
		return name.toLowerCase().compareTo(dt.getName().toLowerCase());
	}
}//End PlateType
