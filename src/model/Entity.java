package model;

public class Entity {
	private String name;
	private String lastName;
	private String id;
	
	public Entity(){
		name = new String();
		lastName = new String();
		id = new String();
	}//End constructor1
	public Entity(String name, String lastName, String id){
		this.name = name;
		this.lastName = lastName;
		this.id = id;
	}//End constructor2
	public void setName(String name){
		this.name = name;
	}//End setName
	public void setLastName(String lastName){
		this.lastName = lastName;
	}//End setLastName
	public void setId(String id){
		this.id = id;
	}//End setId
	public String getName(){
		return name;
	}//End getName
	public String getLastName(){
		return lastName;
	}//End getName
	public String getId(){
		return id;
	}//End getName
}//End entity
