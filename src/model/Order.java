package model;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
public class Order implements Serializable, Comparable<Order>{

	private static final long serialVersionUID = 1L;

	//Attributes
	private User creator;
	private User modifier;
	private String code;
	private List<Integer> amount;
	private List<Product> products;
	private List<Double> productsPrices;
	private String remark;
	private Status status;
	private Date date;
	private Customer customer;
	private Employee employee;
	private boolean enable;

	/**
	 * Constructor of the Order class<br>
	 *     <b>pre:</b> parameters are initialized. <br>
	 *     <b>post:</b> a new order object has been created. <br>
	 * @param creator the user that created the object. <br>
	 */
	public Order(User creator){
		amount = new ArrayList<Integer>();
		remark = new String();
		status = Status.SOLICITADO;
		date = new Date();
		generateCode();
		enable = true;
	}//End constructor1

	/**
	 * Constructor of the Order class. <br>
	 *     <b>pre:</b> parameters are initialized. <br>
	 *     <b>post:</b> a new order object has been created. <br>
	 * @param products the products of the order. products != null.
	 * @param amount the amount of each product. amount is not empty.
	 * @param remark the field for remarks. remark != null.
	 * @param status the status of the order. status corresponds to one of the defined in the Status enum.
	 * @param customer the customer to deliver the order to. customer != null.
	 * @param employee the employee that delivers the order. employee != null.
	 * @param creator the user that created the order. creator != null.
	 */
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
		enable = true;
	}//End constructor2

	/**
	 * sets the code to the return of two methods getPrefix() and getSuffix().<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> a new code has been generated. <br>
	 */
	private void generateCode(){
		code = getPrefix()+getSuffix();
	}//End generateCode

	/**
	 * returns the suffix for the order code using a set of letters, and the date hashcode. <br>
	 *     <b>pre:</b> tbe object that calls the method is not null. <br>
	 *     <b>post:</b> the suffix for the code has been generated. <br>
	 */
	private String getSuffix(){
		final String[] b = {"F","A","B","J","M","L","T","R","Z","Y"};
        int hashCode = date.hashCode();
        String code = new String();
        int length = String.valueOf(hashCode).length();
        for(int i = 0; i < length - 1;i++){
        	int index = (hashCode> 0)?(hashCode % 10):(hashCode % 10)*-1;
            code += b[index];
            hashCode /= 10;
        }//End for
		return code;
	}//End generateMiddleCode

	/**
	 * generates a prefix for the code using a set of predefined symbols.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> a new prefix for the code of the order has been generated. <br>
	 */
	private String getPrefix(){
		String symbols[] = {"-","/","?","#","&",".",","};
		Random s = new Random();
		String prefix = String.valueOf(creator.getName().charAt(0) + creator.getLastName().charAt(0));
		prefix += (s.nextInt(50)+1) + symbols[s.nextInt(symbols.length)];
		return prefix;
	}//End getPrefix

	/**
	 * returns a number corresponding to the comparison of the current date with another date. <br>
	 *     <b>pre:</b> dateToCompare != null.
	 *     <b>post:</b> a number corresponding to the comparison. <br>
	 * @param dateToCompare the other date to compare to. dateToCompare != null.
	 */
	public int compareDate(Date dateToCompare){
		int compare = 0;
		if(date.after(dateToCompare)){
			compare = 1;
		}else if(date.before(dateToCompare))
			compare = -1;
		return compare;
	}//End compareDate

	/**
	 * returns the date of the order in a specific format.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the date of the order. <br>
	 */
	@SuppressWarnings("deprecation")
	public String getDate(){
		return String.valueOf(date.getDate()+"/"+(date.getMonth()+1)+"/"+(date.getYear()+1900));
	}//End getDate

	/**
	 * returns the hour of the order. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the hour of the order. <br>
	 */
	@SuppressWarnings("deprecation")
	public String getHour(){
		return String.valueOf(date.getHours()+":"+date.getMinutes()+":"+date.getSeconds());
	}//End getDates

	/**
	 * returns the full date of the order.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the date of the order.<br>
	 */
	public Date getCompleteDate(){
		return date;
	}//End

	/**
	 * Changes the list of amount of products.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the amount of products has been changed. <br>
	 * @param amount the new amount of solicited products.
	 */
	public void setAmount(List<Integer> amount){
		this.amount = amount;
	}//End setAmount

	/**
	 * changes the list of products of the order. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the list of products has been changed. <br>
	 * @param products the new list of products.
	 */
	public void setProduct(List<Product> products){
		this.products = products;
	}//End setAmount

	/**
	 * changes the remark field of the order. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the remark field has been changed. <br>
	 * @param remark the new remark field.
	 */
	public void setRemark(String remark){
		this.remark = remark;
	}//End setRemark

	/**
	 * changes the status of the order. <br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the status of the order has been changed. <br>
	 * @param status the new status of the order.
	 */
	public void setStatus(String status){
		this.status = Status.valueOf(status);
	}//End setStatus

	/**
	 * changes the customer that solicited the order. <br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the customer has been changed. <br>
	 * @param customer the new customer of the order.
	 */
	public void setCustomer(Customer customer){
		this.customer = customer;
	}//End setCustomer

	/**
	 * changes the employee that will deliver the order. <br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the employee has been changed. <br>
	 * @param employee the new employee of the order.
	 */
	public void setEmployee(Employee employee){
		this.employee = employee;
	}//End setCustomer

	/**
	 * returns the code of the order.<br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the code of the order. <br>
	 */
	public String getCode(){
		return code;
	}//End getCode

	/**
	 * changes the enabled status of the order.<br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the enabled status of the order has been changed. <br>
	 * @param enable the new enabled status of the order.
	 */
	public void setEnable(boolean enable){
		this.enable = enable;
	}//End setEnable

	/**
	 * returns the enabled status of the order. <br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> true if enabled, false if not. <br>
	 */
	public boolean getEnable(){
		return enable;
	}//End getEnable

	/**
	 * returns the list of amount of solicited products for each product.<br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the list of amounts. <br>
	 */
	public List<Integer> getAmount(){
		return amount;
	}//End getCode

	/**
	 * returns the remark field of the order.<br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the remark of the order
	 */
	public String getRemark(){
		return remark;
	}//End getCode

	/**
	 * returns the status of the order.<br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the current status of the order. <br>
	 */
	public String getStatus(){
		return status.toString();
	}//End getCode

	/**
	 * changes the user creator of the order.<br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the creator of the order has been changed. <br>
	 * @param creator the new creator of the order. <br>
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}//End setCreator

	/**
	 * returns the user that created the order.<br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the creator of the order. <br>
	 */
	public User getCreator() {
		return creator;
	}//End getCreator

	/**
	 * changes the user that modified the order.<br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the user modifier has been changed. <br>
	 * @param modifier the new modifier of the order.
	 */
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}//End setModifier

	/**
	 * returns the last user that modified the order. <br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the user modifier of the order. <br>
	 */
	public User getModifier() {
		return modifier;
	}//End getModifier

	/**
	 * returns the customer that solicited the order. <br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the customer of the order. <br>
	 */
	public Customer getCustomer(){
		return customer;
	}//End getCustomer

	/**
	 * returns the employee that will deliver the order.<br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> the employee of the order.<br>
	 */
	public Employee getEmployee(){
		return employee;
	}//End getEmployee

	/**
	 * returns the list of products of the order.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the list of products of the order. <br>
	 */
	public List<Product> getProducts(){
		return products;
	}//End getProducts

	/**
	 * returns the solicited amount of a specific product. <br>
	 *     <b>pre:</b> the object that calls the method is not null. parameters are not null.<br>
	 *     <b>post:</b> the amount of a specific product if found, zero if not found. <br>
	 * @param p the specific product to search the amount in the order. p != null.
	 */
	public int getAmountByProduct(Product p){
		int am = 0;
		boolean found = false;
		for(int i = 0; i < products.size() && !found;i++){
			if(products.get(i) == p){
				am = amount.get(i);
				found = true;
			}//End if
		}//End for
		return am;
	}//End getAmountByProduct

	/**
	 * returns a String with the products in an order.<br>
	 *     <b>pre:</b>  the object that calls the method is not null. <br>
	 *     <b>post:</b> String with the name of the products.
	 *     if the amount of products of the order is greater than 3, then only the first 3 will be included in the String. <br>
	 */
	public String getProductsList(){
		String products = new String();
		int size = this.products.size();
		int limit = (size > 3)?3:size;
		for(int i = 0; i < limit; i++){
			products += this.products.get(i).getName() + " x " + amount.get(i) + "\n";
		}//End for
		products += (limit == 3)?"...\n":"";
		return products;
	}//End getProductsList

	/**
	 * changes the prices of the current products. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the prices of the products have been changed. <br>
	 * @param prices the new list of prices of the products.
	 */
	public void setProductsPrices(List<Double> prices){
		productsPrices = prices;
	}//End setProductsPrices

	/**
	 * returns the list of the prices of the products.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the list of prices of the products. <br>
	 */
	public List<Double> getProductsPrices(){
		return productsPrices;
	}//End getProductsPrices

	/**
	 * calculates the total price of the order using the price of the products and the amounts. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the total amount of money to pay for the order. <br>
	 */
	public double calculateTotalPrice() {
		double totalPrice = 0;
		for(int i = 0; i < products.size(); i ++) {
			totalPrice += products.get(i).getPrice() * amount.get(i);
		}//End for
		return totalPrice;
	}//End calculateTotalPrice

	/**
	 * determines if a specific product is in the order.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the index of the product using its name and size or -1 if not found. <br>
	 * @param productName the name of the specific product to search.
	 * @param productSize the size of the specific product to search.
	 */
	public int findProductInOrder(String productName, String productSize) {
		boolean found = false;
		for(int i = 0; i < products.size() && !found; i ++) {
			Product product = products.get(i);
			if(product.getName().equals(productName) && product.getSize().equals(productSize)) {
				found = true;
				return i;
			}//End if
		}//End if
		return -1;
	}//End findProductOrder

	/**
	 * returns the amount of products of a specific product using its index. <br>
	 *     <b>pre:</b>  productIndex >= 0. The object that calls the method is not null. <br>
	 *     <b>post:</b> the amount corresponding to an specific product. <br>
	 * @param productIndex index of the product to search. productIndex >= 0.
	 */
	public int findAmountProduct(int productIndex) {
		return amount.get(productIndex);
	}//End findAmountProduct

	/**
	 * Compares the date of the order with the date of other order<br>
	 *     <b>pre:</b> parameter is initialized. <br>
	 *     <b>post:</b> a number corresponding to the comparison made by Date.compareTo
	 * @param dateToCompare the other order to compare the date to. dateToCompare != null.
	 */
	@Override
	public int compareTo(Order dateToCompare) {
		return date.compareTo(dateToCompare.getCompleteDate());
	}//End compareTo

}//End Order class