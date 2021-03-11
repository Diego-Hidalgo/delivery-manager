package model;
import java.util.List;
import java.util.ArrayList;
public class Order {
	
	private String code;
	private int amount;
	private String remark;
	private Status status;
	private Date date;
	private Customer customer;
	private Employee employee;
	private List<Product> products;
	public Order(){
		code = new String();
		amount = 0;
		remark = new String();
		status = Status.SOLICITADO;
		date = new Date();
	}//End constructor1
	public Order(String code,int amount,String remark,String status){
		this.code = code;
		this.amount = amount;
		this.remark = remark;
		this.status = Status.valueOf(status);
		date = new Date();
	}//End constructor2
	
	public void addDate(int day, int month, int year, int hour, int minute){
		date = new Date(day,month,year,hour,minute);
	}//End addDate
	
	public void setCode(String code){
		this.code = code;
	}//End setCode
	public void setAmount(int amount){
		this.amount = amount;
	}//End setAmount
	public void setRemark(String remark){
		this.remark = remark;
	}//End setRemark
	public void setStatus(String status){
		this.status = Status.valueOf(status);
	}//End setStatus
	public String getCode(){
		return code;
	}//End getCode
	public int getAmount(){
		return amount;
	}//End getCode
	public String getRemark(){
		return remark;
	}//End getCode
	public String getStatus(){
		return status.toString();
	}//End getCode
}//End Order
