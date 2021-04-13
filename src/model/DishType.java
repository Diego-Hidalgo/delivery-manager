package model;

import java.io.Serializable;

public class DishType implements Comparable<DishType>, Serializable {

	public final static long serialVersionUID = 1L;

	private User creator;
	private User modifier;
	private boolean linked;
	private boolean enabled;
	private String name;
	private int numberOflinks;
	/**
	 * Constructor for DishType class.<br>
	 *     <b>pre:</b> parameter is initialized. <br>
	 *     <b>post:</b> A new DishType object has been created. <br>
	 * @param creator The user that created the instance
	 */
	public DishType(User creator){
		name = new String();
		this.creator= creator;
		linked = false;
		enabled = true;
	}//End Constructor
	/**
	 * Constructor for DishType class. <br>
	 *     <b> pre:</b> parameters are initialized. <br>
	 *     <b>post:</b> A new DishType object has been created. <br>
	 * @param creator The user that created de instance.
	 * @param name name of the dish type
	 */
	public DishType(User creator,String name){
		this.creator= creator;
		this.name = name;
		enabled = true;
	}//End Constructor
	public void updateNumberOfLinks(int n){
		numberOflinks += n; 
	}//End updateNumberOfLinks
	
	public void updateLinkStatus(){
		linked = (numberOflinks > 0)?true:false;
	}//end updateLinkStatus
	public int getNumberOfLinks(){
		return numberOflinks;
	}
	/**
	 * Changes the name of the DishType object. <br>
	 *     <b>pre:</b> <br>
	 *     <b>post:</b> The DishType name has been changed. <br>
	 * @param name New name for the dish type.
	 */
	public void setName(String name) {
		this.name = name;
	}//End setPlateType

	/**
	 * Returns the name of the DishType. <br>
	 *     <b>pre:</b> the object is initialized. <br>
	 *     <b>post:</b> name of the object. <br>
	 * @return the name of the dish type instance.
	 */
	public String getName() {
		return name;
	}//End setPlateType

	/**
	 * Changes the link status of the DishType. <br>
	 *     <b>pre:</b><br>
	 *     <b>post:</b> The DishTyoe linked statys has been changed. <br>
	 * @param linked the new status for the linked attribute.
	 */
	public void setLinked(boolean linked) {
		this.linked = linked;
	}//End setLinked

	/**
	 * returns the link status of the DishType. <br>
	 *     <b>pre:</b> the object is initialized. <br>
	 *     <b>post:</b> true if linked, false if no. <br>
	 * @return The link status of the dish type
	 */
	public boolean getLinked() {
		return linked;
	}//End getLinked

	/**
	 * changes the enabled status for the DishType. <br>
	 *     <b>pre:</b>
	 *     <b>post:</b> the enabled status of the object has been changed. <br>
	 * @param enabled new status for enabled attribute.
	 */
	public void setEnable(boolean enabled) {
		this.enabled = enabled;
	}//End setEnabled

	/**
	 * returns the enabled status for the DishType. <br>
	 *     <b>pre:</b> the object is initialized. <br>
	 *     <b>post:</b> the enabled status.
	 * @return enabled status. true if enabled, false if not.
	 */
	public boolean getEnable() {
		return enabled;
	}//End getEnabled

	/**
	 * changes the creator of the instance. <br>
	 *     <b>pre:</b> user is initialized. <br>
	 *     <b>post:</b> the user creator has been changed. <br>
	 * @param creator user that created the instance.
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}//End setCreator

	/**
	 * returns the creator of the instance. <br>
	 *     <b>pre:</b> the DishType object is initialized and creator != null. <br>
	 *     <b>post:</b> returns the creator. <br>
	 * @return the creator of the instance.
	 */
	public User getCreator() {
		return creator;
	}//End getCreator

	/**
	 * changes the last user that modified the DishType instance. <br>
	 *     <b>pre:</b> changes the user that modified the instance. <br>
	 *     <b>post:</b> the user modifier has been changed. <br>
	 * @param modifier the new modifier of the instance.
	 */
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}//End setModifier

	/**
	 * returns the last user that modified the DishType instance. <br>
	 *     <b>pre:</b> object is initialized. <br>
	 *     <b>post:</b> returns the user that modified the instance. <br>
	 * @return the modifier of the instance.
	 */
	public User getModifier() {
		return modifier;
	}//End getModifier

	/**
	 * compares the actual DishType to other DishType
	 *     <b>pre:</b> parameter is initialized. <br>
	 *     <b>post:</b> a number used to sort the DishType list. <br>
	 * @param dt the other DishType to compare. dt is initialized and.
	 * @return A number according to the comparison using the name as parameter.
	 */
	@Override
	public int compareTo(DishType dt) {
		return name.toLowerCase().compareTo(dt.getName().toLowerCase());
	}
	public String toString(){
		return name;
	}//End toString
}//End PlateType
