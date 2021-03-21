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

	public void addCustomer(String idCreator, String name, String lastName, String id,String address, String nPhone, String remark){
		User creator = users.get(searchUserPosition(idCreator));
		Customer newCustomer = new Customer(creator, name, lastName, id, address, nPhone, remark);
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

	public void modifyCustomerName(String idModifier, String idCustomer, String newName) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Customer customerToModify = customers.get(searchCustomerPosition(idCustomer));
		customerToModify.setName(newName);
		customerToModify.setModifier(userModifier);
	}//End modifyCustomerNameById

	public void modifyCustomerLastName(String idModifier, String idCustomer, String newLastName) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Customer customerToModify = customers.get(searchCustomerPosition(idCustomer));
		customerToModify.setLastName(newLastName);
		customerToModify.setModifier(userModifier);
	}//End modifyCustomerLastNameById

	public void modifyCustomerId(String idModifier, String idCustomer, String newId) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Customer customerToModify = customers.get(searchCustomerPosition(idCustomer));
		customerToModify.setId(newId);
		customerToModify.setModifier(userModifier);
	}//End modifyCustomerIdById

	public void modifyCustomerAddress(String idModifier, String idCustomer, String newAddress) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Customer customerToModify = customers.get(searchCustomerPosition(idCustomer));
		customerToModify.setAddress(newAddress);
		customerToModify.setModifier(userModifier);
	}//End modifyCustomerAddressById

	public void modifyCustomerPhone(String idModifier, String idCustomer, String newNPhone) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Customer customerToModify = customers.get(searchCustomerPosition(idCustomer));
		customerToModify.setNPhone(newNPhone);
		customerToModify.setModifier(userModifier);
	}//End modifyCustomerPhoneById

	public void modifyCustomerRemark(String idModifier, String idCustomer, String newRemark) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Customer customerToModify = customers.get(searchCustomerPosition(idCustomer));
		customerToModify.setRemark(newRemark);
		customerToModify.setModifier(userModifier);
	}//End modifyCustomerRemarkById

	public void disableCustomer(String userId, String customerId) {
		User userModifier = users.get(searchUserPosition(userId));
		Customer customer = customers.get(searchCustomerPosition(customerId));
		customer.setStatus(false);
		customer.setModifier(userModifier);
	}//End disableCustomerById

	public void removeCustomer(String customerId) {
		customers.remove(searchCustomerPosition(customerId));
	}//End removeCustomerById

	public void addEmployee(String name, String lastName, String id) {
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

	public void addEmployee(String idCreator, String name, String lastName, String id) {
		User creator = users.get(searchUserPosition(idCreator));
		Employee newEmployee = new Employee(creator, name, lastName, id);
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

	public void modifyEmployeeName(String idModifier, String idEmployee, String newName) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Employee employeeToModify = employees.get(searchEmployeePosition(idEmployee));
		employeeToModify.setName(newName);
		employeeToModify.setModifier(userModifier);
		if(searchUserPosition(idEmployee) != -1) {
			User employeeUser = users.get(searchEmployeePosition(idEmployee));
			employeeUser.setName(newName);
			employeeUser.setModifier(userModifier);
		}
	}//End modifyEmployeeName

	public void modifyEmployeeLastName(String idModifier, String idEmployee, String newLastName) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Employee employeeToModify = employees.get(searchEmployeePosition(idEmployee));
		employeeToModify.setLastName(newLastName);
		employeeToModify.setModifier(userModifier);
		if(searchUserPosition(idEmployee) != -1) {
			User employeeUser = users.get(searchEmployeePosition(idEmployee));
			employeeUser.setLastName(newLastName);
			employeeUser.setModifier(userModifier);
		}
	}//End modifyEmployeeLastName

	public void modifyEmployeeId(String idModifier, String idEmployee, String newId) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Employee employeeToModify = employees.get(searchEmployeePosition(idEmployee));
		employeeToModify.setId(newId);
		employeeToModify.setModifier(userModifier);
		if(searchUserPosition(idEmployee) != -1) {
			User employeeUser = users.get(searchEmployeePosition(idEmployee));
			employeeUser.setId(newId);
			employeeUser.setModifier(userModifier);
		}
	}//End modifyEmployeeId

	public void disableEmployee(String userId, String employeeId) {
		User userModifier = users.get(searchUserPosition(userId));
		Employee employee = employees.get(searchEmployeePosition(employeeId));
		employee.setStatus(false);
		employee.setModifier(userModifier);
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

	public void addUser(String idCreator, String idEmployee, String userName, String password) {
		User creator = users.get(searchUserPosition(idCreator));
		Employee employee = employees.get(searchEmployeePosition(idEmployee));
		creator.setLinked(true);
		String name = employee.getName();
		String lastName = employee.getLastName();
		String id = employee.getId();
		User newUser = new User(creator, name, lastName, id, userName, password);
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

	public void disableUser(String userModifierId, String userId) {
		User userModifier = users.get(searchUserPosition(userModifierId));
		User user = users.get(searchUserPosition(userId));
		user.setStatus(false);
		user.setModifier(userModifier);
	}//End disableUser

	public void removeUser(String userId) {
		int index = searchUserPosition(userId);
		users.remove(index);
	}//End removeUser

}//End DeliveryManagerController