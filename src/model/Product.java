package model;

public class Product {
	private String name;
	private double price;
	private String size;
	private Type type;
	public Product(){
		name = new String();
		price = 0;
		size = new String();
		type = Type.PLATO_PRINCIPAL;
	}//End constructor1
	public Product(String name, double price, String size,String type){
		this.name = name;
		this.price = price;
		this.size = size;
		this.type = Type.valueOf(type);
	}//End constructor2
	
	public void setName(String name){
		this.name = name;
	}//End setName
	public void setPrice(double price){
		this.price = price;
	}//End setName
	public void setSize(String size){
		this.size = size;
	}//End setName
	public void setType(String type){
		this.type = Type.valueOf(type);
	}//End setName
	public String getName(){
		return name;
	}//End getName
	public double getPrice(){
		return price;
	}//End getName
	public String getSize(){
		return size;
	}//End getName
	public String getType(){
		return type.toString();
	}//End getName
}
