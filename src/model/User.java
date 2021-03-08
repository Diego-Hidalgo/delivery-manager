package model;

public class User extends Employee {
	private String userName;
	private String password;
	private int idNumber;
	
	public User(){
		super();
		userName = new String();
		password = new String();
		idNumber = 0;
	}//End constructor1
	public User(String name, String lastName, String id,String userName, String password,int idNumber){
		super(name,lastName,id);
		this.userName = userName;
		this.password = password;
		this.idNumber = idNumber;
	}//End constructor1
	
	public void setUserName(String userName){
		this.userName = userName;
	}//End setUserName
	public void setPassword(String password){
		this.password = password;
	}//End setUserName
	public void setIdNumber(int idNumber){
		this.idNumber = idNumber;
	}//End setUserName
	public String getUserName(){
		return userName;
	}//End getUserName
	public String getPassword(){
		return password;
	}//End getUserName
	public int getIdNumber(){
		return idNumber;
	}//End getUserName
}//End User
