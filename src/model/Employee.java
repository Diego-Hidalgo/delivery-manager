package model;

public class Employee extends Entity  implements Comparable<Employee> {
	
	public Employee(User creator){
		super(creator);
	}//End constructor1

	public Employee(User creator, String name, String lastName, String id){
		super(creator, name, lastName, id);
	}//End constructor2

	@Override
	public int compareTo(Employee otherUser) {
		return getId().compareTo(otherUser.getId());
	}//End compareTo

}//End Employee
