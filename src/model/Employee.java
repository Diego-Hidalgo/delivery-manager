package model;

public class Employee extends Entity{
	
	public Employee(User creator){
		super(creator);
	}//End constructor1

	public Employee(User creator, String name, String lastName, String id){
		super(creator, name, lastName, id);
	}//End constructor2

}//End Employee
