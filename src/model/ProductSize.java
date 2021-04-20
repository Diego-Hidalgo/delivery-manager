package model;

import java.io.Serializable;

public class ProductSize implements Serializable {

	public final static long serialVersionUID = 1L;

	//Attributes
	private String size;

	/**
	 * Constructor of the ProductSize<br>
	 *     <b>pre:</b> parameters are initialized. <br>
	 *     <b>post:</b> a new ProductSize object has been created. <br>
	 * @param size the size of the products size.
	 */
	public ProductSize(String size){
		this.size = size;
	}

	/**
	 * changes the size of the ProductSize object.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the size of the product has been changed. <br>
	 * @param size the new size of the product.
	 */
	public void setSize(String size){
		this.size = size;
	}//End setSize

	/**
	 * returns the size of the product. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the size of the product. <br>
	 */
	public String getSize(){
		return size;
	}//End getSize

	/**
	 * returns the size of the product. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the size of the product. <br>
	 */
	public String toString(){
		return size;
	}//End toString

}//End ProductSize class