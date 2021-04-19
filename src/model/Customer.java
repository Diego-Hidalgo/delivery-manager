package model;

public class Customer extends Entity {

	private static final long serialVersionUID = 1L;

	//attributes
	private String address; //Customer's address
	private String nPhone; //Customer's phone number
	private String remark; //Customer's remark

	/**
	 * Class Customer constructor. <br>
	 *     <b>pre:</b> parameters are initialized. <br>
	 *     <b>post:</b> a Customer object has been created. <br>
	 * @param creator The user that created the object.
	 */
	public Customer(User creator){
		super(creator);
		address = new String();
		nPhone = new String();
		remark = new String();
	}//End Constructor1

	/**
	 * Class Customer constructor. <br>
	 *     <b>pre:</b> parameters are initialized. <br>
	 *     <b>post:</b> a Customer object has been created. <br>
	 * @param creator The user that created the object.
	 * @param name Customer's name.
	 * @param lastName Customer's last name.
	 * @param id Customer's identification.
	 * @param address Customer's address.
	 * @param nPhone Customer's phone number.
	 * @param remark Customer's remark field.
	 */
	public Customer(User creator, String name, String lastName, String id, String address, String nPhone, String remark){
		super(creator, name, lastName,id);
		this.address = address;
		this.nPhone = nPhone;
		this.remark = remark;
	}//End Constructor2

	/**
	 * Changes the Customer's address. <br>
	 *     <b>pre:</b> <br>
	 *     <b>post:</b> the Customer's address has been changed. <br>
	 * @param address The new Customer's address.
	 */
	public void setAddress(String address){
		this.address = address;
	}//End setAddress.

	/**
	 * returns the Customer's address. <br>
	 *     <b>pre:</b> address is initialized. <br>
	 *     <b>post:</b> returns the Customer's address. <br>
	 */
	public String getAddress(){
		return address;
	}//End getAddress

	/**
	 * changes the Customer's phone number. <br>
	 *     <b>pre:</b> <br>
	 * 	   <b>post:</b> the Customer's number phone has been changed. <br>
	 * @param nPhone The new Customer's phone.
	 */
	public void setNPhone(String nPhone){
		this.nPhone = nPhone;
	}//End setAddress.

	/**
	 * returns the Customer's phone number. <br>
	 *     <b>pre:</b> <br>
	 *     <b>post:</b> returns the phone number. <br>
	 */
	public String getNPhone(){
		return nPhone;
	}//End getAddress

	/**
	 * changes the Customer's remark
	 *     <b>pre:</b> <br>
	 *     <b>post:</b> the Customer's remark has been changed. <br>
	 * @param remark The new Customer's remark field.
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}//End setAddress.

	/**
	 * returns the Customer's remark
	 *     <b>pre:</b> remark is initialized. <br>
	 *     <b>post:</b> returns the Customer's remark<br>
	 */
	public String getRemark(){
		return remark;
	}//End getAddress

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 */
	public String toString(){
		return super.getName() + " " + super.getLastName() + " con id:  "+ super.getId();
	}//End toString

}//End Customer class