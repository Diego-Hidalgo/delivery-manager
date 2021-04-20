package model;

import java.io.Serializable;

public abstract class Entity implements Serializable {

	private static final long serialVersionUID = 1L;

	//attributes
	private User creator; //User creator of the instance.
	private User modifier; //Last user that modified the instance.
	private String name; //Name of the instance.
	private String lastName; //Last name of the instance.
	private String id; //Identification of the instance.
	private boolean enabled; //Enabled status of the instance.
	private boolean linked; //Linked status of the instance.

	/**
	 * constructor of the Entity class. <br>
	 *     <b>pre:</b> parameters are initialized. <br>
	 *     <b>post:</b> Entity class instance created. <br>
	 * @param creator User creator of the instance.
	 */
	public Entity(User creator){
		this.creator = creator;
		this.modifier = null;
		name = new String();
		lastName = new String();
		id = new String();
		enabled = true;
	}//End constructor

	/**
	 * constructor of the Entity class. <br>
	 *     <b>pre:</b> parameters are initialized. <br>
	 *     <b>post:</b> Entity class instance created. <br>
	 * @param creator User creator of the instance.
	 * @param name Name of the entity instance.
	 * @param lastName Last Name of the entity instance.
	 * @param id Identification of the entity instance.
	 */
	public Entity(User creator, String name, String lastName, String id){
		this.creator = creator;
		this.modifier = null;
		this.name = name;
		this.lastName = lastName;
		this.id = id;
		enabled = true;
	}//End constructor2

	/**
	 * returns the name of the user that created the instance. <br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the name of the creator to be used in the TableView
	 */
	public String getCreatorName() {
		if(creator != null) {
			return creator.getUserName();
		} else {
			return "";
		}//End else
	}//End getCreator

	/**
	 * returns the name of the last user that modified the instnace. <br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the name of the modifier to be used in the TableView
	 */
	public String getModifierName() {
		if(modifier != null) {
			return modifier.getUserName();
		} else {
			return "";
		}//End else
	}//End getModifierName

	/**
	 * changes the creator of the instance. <br>
	 *     <b>pre:</b> creator is initialized. <br>
	 *     <b>post:</b> The Entity creator has been changed. <br>
	 * @param creator New user creator of the instance.
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	 * returns the creator of the instance. <br>
	 *     <b>pre:</b> creator is initialized. <br>
	 *     <b>post:</b> Returns the creator of the instance. <br>
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * changes the last user that modified the Entity instance. <br>
	 *     <b>pre:</b> modifier is initialized. <br>
	 *     <b>post:</b> the user modifier has been change. <br>
	 * @param modifier Most recent user that modified the instance.
	 */
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}

	/**
	 * returns the creator of the instance. <br>
	 *     <b>pre:</b> modifier is initialized. <br>
	 *     <b>post:</b> returns the user that created the instance. <br>
	 */
	public User getModifier() {
		return modifier;
	}

	/**
	 * changes the name of the instance. <br>
	 *     <b>pre:</b> name is initialized. <br>
	 *     <b>post:</b> the name of the instance has been changed. <br>
	 * @param name The new name of the Entity instance.
	 */
	public void setName(String name){
		this.name = name;
	}//End setName

	/**
	 * returns the name of the instance. <br>
	 *     <b>pre:</b> the name is initialized. <br>
	 *     <b>post:</b> returns the name of the instance. <br>
	 */
	public String getName(){
		return name;
	}//End getName

	/**
	 * changes the last name of the instance. <br>
	 *     <b>pre:</b> the lastName is initialized. <br>
	 *     <b>post:</b> the lastName of the instance has been changed. <br>
	 * @param lastName The new last name of the Entity instance.
	 */
	public void setLastName(String lastName){
		this.lastName = lastName;
	}//End setLastName

	/**
	 * returns the lastName of the instance. <br>
	 *     <b>pre:</b> the lastName is initialized. <br>
	 *     <b>post:</b> returns the lastName of the instance. <br>
	 */
	public String getLastName(){
		return lastName;
	}//End getName

	/**
	 * changes the id of the instance. <br>
	 *     <b>pre:</b> the id is initialized. <br>
	 *     <b>post:</b> the id of the instance has been changed. <br>
	 * @param id The new id of the Entity instance.
	 */
	public void setId(String id){
		this.id = id;
	}//End setId

	/**
	 * returns the id of the instance. <br>
	 *     <b>pre:</b> the id is initialized. <br>
	 *     <b>post:</b> returns the id of the instance. <br>
	 */
	public String getId(){
		return id;
	}//End getName

	/**
	 * changes the status of the enabled attribute. <br>
	 *     <b>pre:</b> the enabled is initialized. <br>
	 *     <b>post:</b> the enabled has been changed. <br>
	 * @param enabled the new enabled status of the Entity instance.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}//End setEnabled

	/**
	 * returns the enabled status of the instance. <br>
	 *     <b>pre:</b> the enabled is initialized. <br>
	 *     <b>post:</b> returns the enabled status of the instance. <br>
	 */
	public boolean getEnabled() {
		return enabled;
	}//End getEnabled

	/**
	 * changes the linked status of the instance. <br>
	 *     <b>pre:</b> the linked attribute is initialized. <br>
	 *     <b>post:</b> the linked status of the instance has been changed. <br>
	 * @param linked The linked status of the Entity instance.
	 */
	public void setLinked(boolean linked) {
		this.linked = linked;
	}//End setLinked

	/**
	 * returns the linked status of the instance. <br>
	 *     <b>pre:</b> the linked attribute is initialized. <br>
	 *     <b>post:</b> returns the linked status of the instance. <br>
	 */
	public boolean getLinked() {
		return linked;
	}//End getLinked

}//End Entity class