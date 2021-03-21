package model;

public class Employee extends Entity{
	
	public Employee(User creator, User modifier){
		super(creator, modifier);
	}//End constructor1

	public Employee(User creator, User modifier, String name, String lastName, String id){
		super(creator, modifier, name,lastName,id);
	}//End constructor2
}//End Employee
