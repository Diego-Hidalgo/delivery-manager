package model;

public class User extends Employee {
	private String userName;
	private String password;
	
	public User(User creator, User modifier){
		super(creator, modifier);
		userName = new String();
		password = new String();
	}//End constructor1

	public User(User creator, User modifier, String name, String lastName, String id, String userName, String password){
		super(creator, modifier, name,lastName,id);
		this.userName = userName;
		this.password = password;
	}//End constructor2
	
	public void setUserName(String userName){
		this.userName = userName;
	}//End setUserName

	public void setPassword(String password){
		this.password = password;
	}//End setUserName

	public String getUserName(){
		return userName;
	}//End getUserName

	public String getPassword(){
		return password;
	}//End getUserName

}//End User
