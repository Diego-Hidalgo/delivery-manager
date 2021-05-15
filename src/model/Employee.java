package model;

public class Employee extends Entity  implements Comparable<Employee> {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the Employee class. <br>
	 *     <b>pre:</b> parameters are initialized. <br>
	 *     <b>post:</b> a new Employee object has been created. <br>
	 * @param creator User creator of the object
	 */
	public Employee(User creator){
		super(creator);
	}//End constructor1

	/**
	 * Constructor of the Employee class. <br>
	 *     <b>pre:</b> parameters are initialized. <br>
	 * 	   <b>post:</b> a new Employee object has been created. <br>
	 * @param creator User that created the object.
	 * @param name Employee's name.
	 * @param lastName Employee's last name.
	 * @param id Employee's identification.
	 */
	public Employee(User creator, String name, String lastName, String id) {
		super(creator, name, lastName, id);
	}//End constructor2

	/**
	 * Compares the id of the current object with other employee.<br>
	 *     <b>pre;</b>
	 *     <b>post:</b>
	 * @param otherEmployee Employee to compare with.
	 */
	@Override
	public int compareTo(Employee otherEmployee) {
		return getId().compareTo(otherEmployee.getId());
	}//End compareTo

	public String toString() {
		return super.getName() + " " + super.getLastName() + " con id:  "+ super.getId();
	}//End toString

}//End Employee class
