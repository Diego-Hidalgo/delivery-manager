package model;

import java.io.Serializable;

public class ProductSize implements Serializable {

	public final static long serialVersionUID = 1L;
	
	private String size;
	
	public ProductSize(String size){
		this.size = size;
	}

	public void setSize(String size){
		this.size = size;
	}//End setSize

	public String getSize(){
		return size;
	}//End getSize
	
	public String toString(){
		return size;
	}//End toString
}//End ProductSize
