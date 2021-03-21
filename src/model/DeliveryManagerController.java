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

	public int searchCustomerPositionById(String idToSearch) {
		for(int i = 0; i < customers.size(); i ++) {
			Customer customer = customers.get(i);
			if(customer.getId().equals(idToSearch)) {
				return i;
			}
		}
		return -1;
	}//End searchCustomerPositionById

	public int searchUserPosition(String idToSearch) {
		int start = 0;
		int end = users.size() - 1;
		while(start <= end) {
			int mid = (start + end) / 2;
			if (users.get(mid).getId().compareTo(idToSearch) == 0) {
				return mid;
			} else if (users.get(mid).getId().compareTo(idToSearch) < 0) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}

	public int searchEmployeePosition(String idTosearch) {
		int start = 0;
		int end = employees.size() - 1;
		while(start <= end) {
			int mid = (start + end) / 2;
			if(employees.get(mid).getId().compareTo(idTosearch) == 0) {
				return mid;
			} else if (employees.get(mid).getId().compareTo(idTosearch) < 0) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return -1;
	}

	public void addCustomer(String idCreator, String name, String lastName, String address, String nPhone, String remark) {
		User creator = users.get(searchUserPosition(idCreator));
		Customer newCustomer = new Customer(creator, null, name, lastName, address, nPhone, remark);
		creator.setLinked(true);
		if(customers.isEmpty()) {
			customers.add(newCustomer);
		} else {
			Comparator<Customer> lastNameAndNameComparator = new LastNameAndNameComparator();
			int i = 0;
			while(i < customers.size() && lastNameAndNameComparator.compare(newCustomer, customers.get(i)) < 0){
				i ++;
			}//End while
			customers.add(i, newCustomer);
		}
	}//End addCustomer

	public void addCustomer(String idCreator, String name, String lastName, String id,String address, String nPhone, String remark){
		User creator = users.get(searchUserPosition(idCreator));
		Customer newCustomer = new Customer(creator, null, name, lastName, id, address, nPhone, remark);
		creator.setLinked(true);
		if(customers.isEmpty()) {
			customers.add(newCustomer);
		} else {
			Comparator<Customer> lastNameAndNameComparator = new LastNameAndNameComparator();
			int i = 0;
			while(i < customers.size() && lastNameAndNameComparator.compare(newCustomer, customers.get(i)) < 0){
				i ++;
			}//End while
			customers.add(i, newCustomer);
		}
	}//End addCustomer

	public void disableCustomerById(String customerId) {
		boolean stop = false;
		for(int i = 0; i < customers.size() && !stop; i ++) {
			Customer customer = customers.get(i);
			if(customer.getId().equals(customerId)) {
				customer.setStatus(false);
				stop = true;
			}
		}
	}//End disableCustomerById

	public void disableCustomerByPhone(String customerNPhone) {
		boolean stop = false;
		for(int i = 0; i < customers.size() && !stop; i ++) {
			Customer customer = customers.get(i);
			if(customer.getNPhone().equals(customerNPhone)) {
				customer.setStatus(false);
				stop = true;
			}
		}
	}//End disableCustomerByPhone

	public void removeCustomerById(String customerId) {
		boolean stop = false;
		for(int i = 0; i < customers.size() && !stop; i ++) {
			Customer customer = customers.get(i);
			if(customer.getId().equals(customerId)) {
				customers.remove(i);
				stop = true;
			}
		}
	}//End removeCustomerById

	public void removeCustomerByPhone(String customerNPhone) {
		boolean stop = false;
		for(int i = 0; i < customers.size() && !stop; i ++) {
			Customer customer = customers.get(i);
			if(customer.getNPhone().equals(customerNPhone)) {
				customers.remove(i);
				stop = true;
			}
		}
	}//End removeCustomerByPhone

	public void addEmployee(String name, String lastName, String id) {
		Employee newEmployee = new Employee(null, null, name, lastName, id);
		if(employees.isEmpty()) {
			employees.add(newEmployee);
		} else {
			int i = 0;
			while(i < employees.size() && employees.get(i).getId().compareTo(newEmployee.getId()) < 0) {
				i ++;
			}
			employees.add(i, newEmployee);
		}
	}//End addEmployee

	public void addEmployee(String idCreator, String name, String lastName, String id) {
		User creator = users.get(searchUserPosition(idCreator));
		Employee newEmployee = new Employee(creator, null, name, lastName, id);
		creator.setLinked(true);
		if(employees.isEmpty()) {
			employees.add(newEmployee);
		} else {
			int i = 0;
			while(i < employees.size() && employees.get(i).getId().compareTo(newEmployee.getId()) < 0) {
				i ++;
			}//End while
			employees.add(i, newEmployee);
		}
	}//End addEmployee

	public void disableEmployee(String employeeId) {
		Employee employee = employees.get(searchEmployeePosition(employeeId));
		employee.setStatus(false);
	}//End disableEmployee

	public void removeEmployee(String employeeId) {
		int index = searchEmployeePosition(employeeId);
		employees.remove(index);
	}//End removeEmployee

	public void addUser(String idEmployee, String userName, String password) {
		Employee employee = employees.get(searchEmployeePosition(idEmployee));
		employee.setLinked(true);
		String name = employee.getName();
		String lastName = employee.getLastName();
		String id = employee.getId();
		User newUser = new User(null, null, name, lastName, id, userName, password);
		if(users.isEmpty()) {
			users.add(newUser);
		} else {
			int i = 0;
			while(i < users.size() && users.get(i).getId().compareTo(newUser.getId()) < 0) {
				i ++;
			}
			users.add(i, newUser);
		}
	}//End addUser

	public void addUser(String idCreator, String idEmployee, String userName, String password) {
		User creator = users.get(searchUserPosition(idCreator));
		Employee employee = employees.get(searchEmployeePosition(idEmployee));
		employee.setLinked(true);
		String name = employee.getName();
		String lastName = employee.getLastName();
		String id = employee.getId();
		User newUser = new User(creator, null, name, lastName, id, userName, password);
		if(users.isEmpty()) {
			users.add(newUser);
		} else {
			int i = 0;
			while(i < users.size() && users.get(i).getId().compareTo(newUser.getId()) < 0) {
				i ++;
			}//End while
			users.add(i, newUser);
		}
	}//End addUser

	public void disableUser(String userId) {
		User user = users.get(searchUserPosition(userId));
		user.setStatus(false);
	}//End disableUser

	public void removeUser(String userId) {
		int index = searchUserPosition(userId);
		users.remove(index);
	}//End removeUser

}//End DeliveryManagerController
