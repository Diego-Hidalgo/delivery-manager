package model;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class DeliveryManagerController {
	private User loggedUser;
	private List<Order> orders;
	private List<Customer> customers;
	private List<User> users;
	private List<Product> products;
	private List<Employee> employees;
	
	public DeliveryManagerController(){
		loggedUser = null;
		orders = new ArrayList<Order>();
		customers = new ArrayList<Customer>();
		users = new ArrayList<User>();
		products = new ArrayList<Product>();
		employees = new ArrayList<Employee>();
	}//End DeliveryManagerController

	public void setLoggedUser(String idLoggedUser) {
		this.loggedUser = users.get(searchUserPosition(idLoggedUser));
	}//End setLoggedUser

	public void logOutUser() {
		this.loggedUser = null;
	}//End logOutUser

	public User getLoggedUser() {
		return loggedUser;
	}//End getLoggedUser

	public int searchCustomerPosition(String idToSearch) {
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

	public void addCustomer(String name, String lastName, String id,String address, String nPhone, String remark){
		Customer newCustomer = new Customer(loggedUser, name, lastName, id, address, nPhone, remark);
		loggedUser.setLinked(true);
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

	public void modifyCustomerName(String idCustomer, String newName) {
		Customer customerToModify = customers.get(searchCustomerPosition(idCustomer));
		customerToModify.setName(newName);
		customerToModify.setModifier(loggedUser);
	}//End modifyCustomerNameById

	public void modifyCustomerLastName(String idCustomer, String newLastName) {
		Customer customerToModify = customers.get(searchCustomerPosition(idCustomer));
		customerToModify.setLastName(newLastName);
		customerToModify.setModifier(loggedUser);
	}//End modifyCustomerLastNameById

	public void modifyCustomerId(String idCustomer, String newId) {
		Customer customerToModify = customers.get(searchCustomerPosition(idCustomer));
		customerToModify.setId(newId);
		customerToModify.setModifier(loggedUser);
	}//End modifyCustomerIdById

	public void modifyCustomerAddress(String idCustomer, String newAddress) {
		Customer customerToModify = customers.get(searchCustomerPosition(idCustomer));
		customerToModify.setAddress(newAddress);
		customerToModify.setModifier(loggedUser);
	}//End modifyCustomerAddressById

	public void modifyCustomerPhone(String idCustomer, String newNPhone) {
		Customer customerToModify = customers.get(searchCustomerPosition(idCustomer));
		customerToModify.setNPhone(newNPhone);
		customerToModify.setModifier(loggedUser);
	}//End modifyCustomerPhoneById

	public void modifyCustomerRemark(String idCustomer, String newRemark) {
		Customer customerToModify = customers.get(searchCustomerPosition(idCustomer));
		customerToModify.setRemark(newRemark);
		customerToModify.setModifier(loggedUser);
	}//End modifyCustomerRemarkById

	public void disableCustomer(String customerId) {
		Customer customer = customers.get(searchCustomerPosition(customerId));
		customer.setStatus(false);
		customer.setModifier(loggedUser);
	}//End disableCustomerById

	public void removeCustomer(String customerId) {
		customers.remove(searchCustomerPosition(customerId));
	}//End removeCustomerById

	public void addFirstEmployee(String name, String lastName, String id) {
		Employee newEmployee = new Employee(null, name, lastName, id);
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

	public void addEmployee(String name, String lastName, String id) {
		Employee newEmployee = new Employee(loggedUser, name, lastName, id);
		loggedUser.setLinked(true);
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

	public void modifyEmployeeName(String idEmployee, String newName) {
		Employee employeeToModify = employees.get(searchEmployeePosition(idEmployee));
		employeeToModify.setName(newName);
		employeeToModify.setModifier(loggedUser);
		if(searchUserPosition(idEmployee) != -1) {
			User employeeUser = users.get(searchEmployeePosition(idEmployee));
			employeeUser.setName(newName);
			employeeUser.setModifier(loggedUser);
		}
	}//End modifyEmployeeName

	public void modifyEmployeeLastName(String idEmployee, String newLastName) {
		Employee employeeToModify = employees.get(searchEmployeePosition(idEmployee));
		employeeToModify.setLastName(newLastName);
		employeeToModify.setModifier(loggedUser);
		if(searchUserPosition(idEmployee) != -1) {
			User employeeUser = users.get(searchEmployeePosition(idEmployee));
			employeeUser.setLastName(newLastName);
			employeeUser.setModifier(loggedUser);
		}
	}//End modifyEmployeeLastName

	public void modifyEmployeeId(String idEmployee, String newId) {
		Employee employeeToModify = employees.get(searchEmployeePosition(idEmployee));
		employeeToModify.setId(newId);
		employeeToModify.setModifier(loggedUser);
		if(searchUserPosition(idEmployee) != -1) {
			User employeeUser = users.get(searchEmployeePosition(idEmployee));
			employeeUser.setId(newId);
			employeeUser.setModifier(loggedUser);
		}
	}//End modifyEmployeeId

	public void disableEmployee(String employeeId) {
		Employee employee = employees.get(searchEmployeePosition(employeeId));
		employee.setStatus(false);
		employee.setModifier(loggedUser);
	}//End disableEmployee

	public void removeEmployee(String employeeId) {
		int index = searchEmployeePosition(employeeId);
		employees.remove(index);
	}//End removeEmployee

	public void addFirstUser(String idEmployee, String userName, String password) {
		Employee employee = employees.get(searchEmployeePosition(idEmployee));
		employee.setLinked(true);
		String name = employee.getName();
		String lastName = employee.getLastName();
		String id = employee.getId();
		User newUser = new User(null, name, lastName, id, userName, password);
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

	public void addUser(String idEmployee, String userName, String password) {
		Employee employee = employees.get(searchEmployeePosition(idEmployee));
		loggedUser.setLinked(true);
		String name = employee.getName();
		String lastName = employee.getLastName();
		String id = employee.getId();
		User newUser = new User(loggedUser, name, lastName, id, userName, password);
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
		user.setModifier(loggedUser);
	}//End disableUser

	public void removeUser(String userId) {
		int index = searchUserPosition(userId);
		users.remove(index);
	}//End removeUser

}//End DeliveryManagerController