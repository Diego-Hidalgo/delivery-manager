package model;

import java.io.Serializable;

public class ProductSize implements Serializable {

	public final static long serialVersionUID = 1L;

	//Attributes
	private String size;

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param size
	 */
	public ProductSize(String size){
		this.size = size;
	}

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @param size
	 */
	public void setSize(String size){
		this.size = size;
	}//End setSize

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public String getSize(){
		return size;
	}//End getSize

	/**
	 * <br>
	 *     <b>pre:</b>
	 *     <b>post:</b>
	 * @return
	 */
	public String toString(){
		return size;
	}//End toString

}//End ProductSize