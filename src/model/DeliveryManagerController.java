package model;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class DeliveryManagerController {
	private List<Order> orders;
	private List<Customer> customers;
	private List<User> users;
	private List<Product> products;
	private List<Employee> employees;
	
	public DeliveryManagerController(){
		orders = new ArrayList<Order>();
		customers = new ArrayList<Customer>();
		users = new ArrayList<User>();
		products = new ArrayList<Product>();
		employees = new ArrayList<Employee>();
	}//End DeliveryManagerController
	public void addCustomer(String name, String lastName, String address, String nPhone, String remark) {
		Customer newCustomer = new Customer(name, lastName, address, nPhone, remark);
		if(customers.isEmpty()) {
			customers.add(newCustomer);
		} else {
			Comparator<Customer> lastNameAndNameComparator = new LastNameAndNameComparator();
			int i = 0;
			while(i < customers.size() && lastNameAndNameComparator.compare(newCustomer, customers.get(i)) < 0){
				i ++;
			}
			customers.add(i, newCustomer);
		}
	}//End addCustomer
	public void addCustomer(String name, String lastName, String id,String address, String nPhone, String remark){
		Customer newCustomer = new Customer(name, lastName, id, address, nPhone, remark);
		if(customers.isEmpty()) {
			customers.add(newCustomer);
		} else {
			Comparator<Customer> lastNameAndNameComparator = new LastNameAndNameComparator();
			int i = 0;
			while(i < customers.size() && lastNameAndNameComparator.compare(newCustomer, customers.get(i)) < 0){
				i ++;
			}
			customers.add(i, newCustomer);
		}
	}//End addCustomer

}//End DeliveryManagerController
