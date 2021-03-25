package model;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
public class Order implements Serializable {

	private static final long serialVersionUID = 1;

	private User creator;
	private User modifier;
	private String code;
	private List<Integer> amount;
	private List<Product> products;
	private String remark;
	private Status status;
	private Date date;
	private Customer customer;
	private Employee employee;
	private boolean enable;

	public Order(){
		amount = new ArrayList<Integer>();
		remark = new String();
		status = Status.SOLICITADO;
		date = new Date();
		generateCode();
	}//End constructor1

	public Order(List<Product> products,List<Integer> amount,String remark,String status,Customer customer,Employee employee,User creator){
		this.products = products;
		this.amount = amount;
		this.remark = remark;
		this.status = Status.valueOf(status);
		this.customer = customer;
		this.employee = employee;
		this.creator = creator;
		date = new Date();
		generateCode();
	}//End constructor2

	public void generateCode(){
		code = getPrefix()+getSuffix();
	}//End generateCode

	private String getSuffix(){
		final String[] b = {"F","A","B","J","M","L","T","R","Z","Y"};
        int hashCode = date.hashCode();
        String code = new String();
        int length = String.valueOf(hashCode).length();
        for(int i = 0; i < length;i++){
            code += b[(hashCode % 10)];
            hashCode /= 10;
        }//End for
		return code;
	}//End generateMiddleCode

	private String getPrefix(){
		String symbols[] = {"-","/","?","#","&",".",","};
		Random s = new Random();
		String prefix = String.valueOf(creator.getName().charAt(0) + creator.getLastName().charAt(0));
		prefix += (s.nextInt(100)+1) + symbols[s.nextInt(symbols.length)];
		return prefix;
	}//End getPrefix

	public void setAmount(List<Integer> amount){
		this.amount = amount;
	}//End setAmount

	public void setProduct(List<Product> products){
		this.products = products;
	}//End setAmount

	public void setRemark(String remark){
		this.remark = remark;
	}//End setRemark

	public void setStatus(String status){
		this.status = Status.valueOf(status);
	}//End setStatus

	public void setCustomer(Customer customer){
		this.customer = customer;
	}//End setCustomer

	public void setEmployee(Employee employee){
		this.employee = employee;
	}//End setCustomer

	public String getCode(){
		return code;
	}//End getCode

	public void setEnable(boolean enable){
		this.enable = enable;
	}//End setEnable

	public boolean getEnable(){
		return enable;
	}//End getEnable

	public List<Integer> getAmount(){
		return amount;
	}//End getCode

	public String getRemark(){
		return remark;
	}//End getCode

	public String getStatus(){
		return status.toString();
	}//End getCode

	public void setCreator(User creator) {
		this.creator = creator;
	}//End setCreator

	public User getCreator() {
		return creator;
	}//End getCreator

	public void setModifier(User modifier) {
		this.modifier = modifier;
	}//End setModifier

	public User getModifier() {
		return modifier;
	}//End getModifier

	public Customer getCustomer(){
		return customer;
	}//End getCustomer

	public Employee getEmployee(){
		return employee;
	}//End getEmployee

	public List<Product> getProducts(){
		return products;
	}//End getProducts

}//End Order
