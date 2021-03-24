package model;

import java.io.Serializable;

public abstract class Entity implements Serializable {

	public final static long serialVesionUID = 1;

	private User creator;
	private User modifier;
	private String name;
	private String lastName;
	private String id;
	private boolean enabled;
	private boolean linked;
	
	public Entity(User creator){
		this.creator = creator;
		this.modifier = null;
		name = new String();
		lastName = new String();
		id = new String();
		enabled = true;
	}//End constructor

	public Entity(User creator, String name, String lastName, String id){
		this.creator = creator;
		this.modifier = null;
		this.name = name;
		this.lastName = lastName;
		this.id = id;
		enabled = true;
	}//End constructor2

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getCreator() {
		return creator;
	}

	public void setModifier(User modifier) {
		this.modifier = modifier;
	}

	public User getModifier() {
		return modifier;
	}

	public void setName(String name){
		this.name = name;
	}//End setName

	public void setLastName(String lastName){
		this.lastName = lastName;
	}//End setLastName

	public void setId(String id){
		this.id = id;
	}//End setId

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName(){
		return name;
	}//End getName

	public String getLastName(){
		return lastName;
	}//End getName

	public String getId(){
		return id;
	}//End getName

	public boolean getEnabled() {
		return enabled;
	}

	public void setLinked(boolean linked) {
		this.linked = linked;
	}//End setLinked

	public boolean getLinked() {
		return linked;
	}//End getLinked

}//End entity
