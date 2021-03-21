package model;

public abstract class Entity {

	private User creator;
	private User modifier;
	private String name;
	private String lastName;
	private String id;
	private boolean status;
	private boolean linked;
	
	public Entity(User creator){
		this.creator = creator;
		this.modifier = null;
		name = new String();
		lastName = new String();
		id = new String();
		status = true;
	}//End constructor

	public Entity(User creator, String name, String lastName, String id){
		this.creator = creator;
		this.modifier = null;
		this.name = name;
		this.lastName = lastName;
		this.id = id;
		status = true;
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

	public void setStatus(boolean status) {
		this.status = status;
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

	public boolean getStatus() {
		return status;
	}

	public void setLinked(boolean linked) {
		this.linked = linked;
	}//End setLinked

	public boolean getLinked() {
		return linked;
	}//End getLinked

}//End entity
