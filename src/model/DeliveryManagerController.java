package model;
import java.util.List;
import java.util.ArrayList;

public class DeliveryManagerController {
	private List<Order> order;
	private List<User> user;
	private List<Product> product;
	private List<Employee> employee;
	
	public DeliveryManagerController(){
		order = new ArrayList<Order>();
		user = new ArrayList<User>();
		product = new ArrayList<Product>();
		employee = new ArrayList<Employee>();
	}//End DeliveryManagerController
	
}//End DeliveryManagerController
