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

	public int searchCustomerPositionByPhone(String nPhoneToSeach) {
		for(int i = 0; i < customers.size(); i ++) {
			Customer customer = customers.get(i);
			if(customer.getNPhone().equals(nPhoneToSeach)) {
				return i;
			}
		}
		return -1;
	}//End searchCustomerPositionByPhone

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

	public void modifyCustomerNameById(String idModifier, String idCustomer, String newName) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Customer customerToModify = customers.get(searchCustomerPositionById(idCustomer));
		customerToModify.setName(newName);
		customerToModify.setModifier(userModifier);
	}//End modifyCustomerNameById

	public void modifyCustomerNameByPhone(String idModifier, String nPhoneCustomer, String newName) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Customer customerToModify = customers.get(searchCustomerPositionByPhone(nPhoneCustomer));
		customerToModify.setName(newName);
		customerToModify.setModifier(userModifier);
	}//End modifyCustomerNameByPhone

	public void modifyCustomerLastNameById(String idModifier, String idCustomer, String newLastName) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Customer customerToModify = customers.get(searchCustomerPositionById(idCustomer));
		customerToModify.setLastName(newLastName);
		customerToModify.setModifier(userModifier);
	}//End modifyCustomerLastNameById

	public void modifyCustomerLastNameByPhone(String idModifier, String nPhoneCustomer, String newLastName) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Customer customerToModify = customers.get(searchCustomerPositionByPhone(nPhoneCustomer));
		customerToModify.setLastName(newLastName);
		customerToModify.setModifier(userModifier);
	}//End modifyCustomerLastNameById

	public void modifyCustomerIdById(String idModifier, String idCustomer, String newId) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Customer customerToModify = customers.get(searchCustomerPositionById(idCustomer));
		customerToModify.setId(newId);
		customerToModify.setModifier(userModifier);
	}//End modifyCustomerIdById

	public void modifyCustomerIdByPhone(String idModifier, String nPhoneCustomer, String newId) {
		User userModifier = users.get(searchUserPosition(idModifier));
		Customer customerToModify = customers.get(searchCustomerPositionByPhone(nPhoneCustomer));
		customerToModify.setId(newId);
		customerToModify.setModifier(userModifier);
	}//End modifyCustomerIdByPhone

	public void disableCustomerById(String userId, String customerId) {
		User userModifier = users.get(searchUserPosition(userId));
		Customer customer = customers.get(searchCustomerPositionById(customerId));
		customer.setStatus(false);
		customer.setModifier(userModifier);
	}//End disableCustomerById

	public void disableCustomerByPhone(String userId, String customerNPhone) {
		User userModifier = users.get(searchUserPosition(userId));
		Customer customer = customers.get(searchCustomerPositionByPhone(customerNPhone));
		customer.setStatus(false);
		customer.setModifier(userModifier);
	}//End disableCustomerByPhone

	public void removeCustomerById(String customerId) {
		customers.remove(searchCustomerPositionById(customerId));
	}//End removeCustomerById

	public void removeCustomerByPhone(String customerNPhone) {
		customers.remove(searchCustomerPositionByPhone(customerNPhone));
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
		creator.setLinked(true);
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
