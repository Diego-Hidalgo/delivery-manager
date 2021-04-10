package model;

public class User extends Employee {

	private static final long serialVersionUID = 1L;

	//attributes
	private String userName; //User's user name
	private String password; //User's password

	/**
	 * Constructor of the User class. <br>
	 *     <b>pre:</b> parameters are initialized. <br>
	 *     <b>post:</b> a new User's object has been created. <br>
	 * @param creator
	 */
	public User(User creator){
		super(creator);
		userName = new String();
		password = new String();
	}//End constructor1

	/**
	 * Constructor of the User class. <br>
	 *     <b>pre:</b> parameters are initialized. <br>
	 *     <b>post:</b> a new User's object has been created. <br>
	 * @param creator User that created the object.
	 * @param name User's name.
	 * @param lastName User's last name.
	 * @param id User's identification.
	 * @param userName User's username.
	 * @param password User's password.
	 */
	public User(User creator, String name, String lastName, String id, String userName, String password){
		super(creator, name, lastName, id);
		this.userName = userName;
		this.password = password;
	}//End constructor2

	/**
	 * changes the username. <br>
	 *     <b>pre:</b> <br>
	 *     <b>post:</b> the username has been changed. <br>
	 * @param userName New User's username.
	 */
	public void setUserName(String userName){
		this.userName = userName;
	}//End setUserName

	/**
	 * returns the User's username. <br>
	 *     <b>pre:</b> <br>
	 *     <b>post:</b> returns the User's username<br>
	 * @return username.
	 */
	public String getUserName(){
		return userName;
	}//End getUserName

	/**
	 * changes the User's password. <br>
	 *     <b>pre:</b> <br>
	 *     <b>post:</b> User's password has been changed. <br>
	 * @param password New User's password.
	 */
	public void setPassword(String password){
		this.password = password;
	}//End setUserName

	/**
	 * returns the User's password. <br>
	 *     <b>pre:</b> <br>
	 *     <b>post:</b> returns the User's password<br>
	 * @return User's password.
	 */
	public String getPassword(){
		return password;
	}//End getUserName

}//End User
