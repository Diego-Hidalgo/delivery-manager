package model;

public class Customer extends Entity{

	private static final long serialVersionUID = 1;
	private String address;
	private String nPhone;
	private String remark;
	
	public Customer(User creator){
		super(creator);
		address = new String();
		nPhone = new String();
		remark = new String();
	}//End Constructor1

	public Customer(User creator, String name, String lastName, String id, String address, String nPhone, String remark){
		super(creator, name, lastName,id);
		this.address = address;
		this.nPhone = nPhone;
		this.remark = remark;
	}//End Constructor2

	public void setAddress(String address){
		this.address = address;
	}//End setAddress.

	public void setNPhone(String nPhone){
		this.nPhone = nPhone;
	}//End setAddress.

	public void setRemark(String remark){
		this.remark = remark;
	}//End setAddress.

	public String getAddress(){
		return address;
	}//End getAddress

	public String getNPhone(){
		return nPhone;
	}//End getAddress

	public String getRemark(){
		return remark;
	}//End getAddress

}//End Customer
