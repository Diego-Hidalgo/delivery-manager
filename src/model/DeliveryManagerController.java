package model;
import java.io.*;
import java.util.*;

public class DeliveryManagerController implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SAVE_PATH = "src/save-files/save-file.dm";

	private User loggedUser;
	private List<Employee> employees;
	private List<User> users;
	private List<Customer> customers;
	private List<ProductBase> productBase;
	private List<Product> products;
	private List<ProductSize> sizes;
	private List<DishType> types;
	private List<Ingredient> ingredients;
	private List<Order> orders;
	private transient double binarySearchTime;

	/**
	 * constructor of the DeliveryManagerController. This is the main class of the model.<br>
	 *     <b>pre:</b> <br>
	 *     <b>post:</b> a new DeliveryManagerController class object has been created. <br>
	 */
	public DeliveryManagerController(){
		loggedUser = null;
		employees = new ArrayList<Employee>();
		users = new ArrayList<User>();
		customers = new ArrayList<Customer>();
		products = new ArrayList<Product>();
		productBase = new ArrayList<ProductBase>();
		types = new ArrayList<DishType>();
		ingredients = new ArrayList<Ingredient>();
		orders = new ArrayList<Order>();
		sizes = new ArrayList<ProductSize>();
	}//End DeliveryManagerController

	/**
	 * changes the search time made by the methods that search a customer given a condition.<br>
	 *     <b>pre:</b> the object that calls this method is not null. <br>
	 *     <b>post:</b> the binarySearchTime has been changed. <br>
	 * @param binarySearchTime the new binarySearchTime.
	 */
	public void setBinarySearchTime(double binarySearchTime) {
		this.binarySearchTime = binarySearchTime;
	}//End setBinarySearchTime

	/**
	 * returns the search time made by the methods that search a customer given a condition.<br>
	 *     <b>pre:</b> the object that calls this method is not null. <br>
	 *     <b>post:</b> the binarySearchTime. <br>
	 */
	public double getBinarySearchTime() {
		return binarySearchTime;
	}//End getBinarySearchTime

	/**
	 * returns a list of the employees that meet the boolean enabled condition.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> a list with enabled employees if enabled is true, a list with disabled employees if enabled is false. <br>
	 * @param enabled the condition that will be used to fill the objects in the list.
	 */
	public List<Employee> getEmployees(boolean enabled) {
		List<Employee> employeeList = new ArrayList<>();
		for(int i = 0; i < employees.size(); i ++) {
			Employee employee = employees.get(i);
			if(employee.getEnabled() == enabled) {
				employeeList.add(employee);
			}//End if
		}//End for
		return employeeList;
	}//End getEmployee

	/**
	 * returns a list of the users that meet the boolean enabled condition.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> a list with enabled users if enabled is true, a list with disabled users if enabled is false. <br>
	 * @param enabled the condition that will be used to fill the objects in the list.
	 */
	public List<User> getUsers(boolean enabled) {
		List<User> userList = new ArrayList<>();
		for(int i = 0; i < users.size(); i ++) {
			User user = users.get(i);
			if(user.getEnabled() == enabled) {
				userList.add(user);
			}//End if
		}//End for
		return userList;
	}//End getUsers

	/**
	 * returns a list of the customers  that meet the boolean enabled condition.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> a list with enabled customers if enabled is true, a list with disabled customers if enabled is false. <br>
	 * @param enabled the condition that will be used to fill the objects in the list.
	 */
	public List<Customer> getCustomers(boolean enabled) {
		List<Customer> customerList = new ArrayList<>();
		for(int i = 0; i < customers.size(); i ++) {
			Customer customer = customers.get(i);
			if(customer.getEnabled() == enabled) {
				customerList.add(customer);
			}//End if
		}//End for
		return customerList;
	}//End getCustomers

	/**
	 * returns the amount of users in the users list.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the amount of total users. <br>
	 */
	public int getAmountUsers() {
		return users.size();
	}//End getAmountUsers

	/**
	 * returns the amount of employees in the employees list<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the amount of total users. <br>
	 */
	public int getAmountEmployees() {
		return employees.size();
	}

	/**
	 * changes the loggedUser.<br>
	 *     <b>pre:</b> the object that calls the method is not null.<br>
	 *     <b>post:</b> successful login or could not log in. <br>
	 * @param nameLoggedUser the name of the user that is trying to log in. <br>
	 */
	public void setLoggedUser(final String nameLoggedUser) {
		Boolean stop = false;
		for(int i = 0; i < users.size() && !stop; i ++) {
			User user = (User) users.get(i);
			if(user.getUserName().equals(nameLoggedUser)) {
				this.loggedUser = user;
				stop = true;
			}//End if
		}//for
	}//End setLoggedUser

	/**
	 * returns the current loggedUser.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the loggedUser in the system. <br>
	 */
	public User getLoggedUser(){
		return loggedUser;
	}//End getLoggedUser

	/**
	 * logs out an user from the system.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the reference of the loggedUser has been changed to null. <br>
	 */
	public void logOutUser() {
		this.loggedUser = null;
	}//End logOutUser

	/**
	 * Returns the address of a customer given its identification. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the address of the customer if the id is in use, an empty text if not. <br>
	 * @param id the id of the customer to search and return its id.
	 */
	public String getCustomerAddressById(String id) {
		int index = searchCustomerPosition(id);
		if(index != -1) {
			return customers.get(index).getAddress();
		} else {
			return "";
		}//End if/else
	}//End getCustomerAddressById

	/**
	 * counts the amount of users that are enabled.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the amount of enabled users. <br>
	 */
	public int countEnabledUsers() {
		int enabled = 0;
		for (User user : users) {
			if (user.getEnabled()) {
				enabled++;
			}//End if
		}//End countEnabledUsers
		return enabled;
	}//End countEnabledUsers

	/**
	 * returns a boolean indicating if a given String contains blank spaces. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> true if contains blank chars, false if not. <br>
	 * @param fieldToValidate the String that will be validated. <br>
	 */
	public boolean validateBlankChars(String fieldToValidate) {
		boolean validate = false;
		for(int i = 0; i < fieldToValidate.length() && !validate; i ++) {
			if(fieldToValidate.charAt(i) != ' ') {
				validate = true;
			}//End if
		}//End for
		return validate;
	}//End validateBlankChats

	/**
	 * serializes all the information of the attributes in a plane text file. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the data has been saved. <br>
	 */
	public void saveAllData() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH));
		oos.writeObject(this);
		oos.close();
	}//End saveAllData

	/**
	 * adds a new employee and a new user<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> an employee and its corresponding user has been successfully added with the given information. <br>
	 * @param name the name of the new employee and user to be added.
	 * @param lastName the last name of the new employee and user to be added.
	 * @param id the id of the new employee and user to be added.
	 * @param userName the name of the new user name to be added.
	 * @param password the password of the new user to be added.
	 */
	public void addEmployeeAndUser(String name, String lastName, String id, String userName, String password) throws IOException {
		addEmployee(name, lastName, id);
		addUser(id, userName, password);
	}//End addEmployeeAndUser

	/**
	 * writes a defined set of information about employees in a file with .csv extension. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the information has been saved in the file. <br>
	 * @param file the target file to write the information.
	 * @param s the String to use as a separator of the information.
	 * @param initialDate the initial date for the range to generate the report.
	 * @param finishDate the final date for the range to generate the report.
	 */
	public void exportEmployeesData(File file, String s, Date initialDate, Date finishDate) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(file);
		List<Order> ords = getOrdersInRange(initialDate, finishDate);
		String columns = "Nombre"+s+"Apellido"+s+"Identificacion"+s+"Total pedidos entregados"+s+"Total pago de pedidos" + "\n";
		pw.write(columns);
		double total = 0;
		int delivered = 0;
		for(int i = 0; i < employees.size(); i ++) {
			Employee employee = employees.get(i);
			String name = employee.getName();
			String lastName = employee.getLastName();
			String id = employee.getId();
			int totalOrders = 0;
			double totalPaid = 0;
			for(int j = 0; j < ords.size(); j ++) {
				Order order = ords.get(j);
				if(order.getEmployee().getId().equalsIgnoreCase(id) && order.getStatus().equalsIgnoreCase("ENTREGADO")) {
					totalOrders ++;
					totalPaid += order.calculateTotalPrice();
					delivered ++;
				}//End if
			}//End for
			String toWrite = name+s+lastName+s+id+s+totalOrders+s+"$"+totalPaid + "\n";
			total += totalPaid;
			pw.write(toWrite);
		}//End for
		String totalPaidOrders = s+s+s+delivered+s+"$" + total + "\n";
		pw.write(totalPaidOrders);
		String rows = "Registros totales: " + employees.size() + "\n";
		pw.write(rows);
		pw.close();
	}//End exportEmployeesData

	/**
	 * searches an employee using its id in the employees list. Uses binary search <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the index of the employee in the list if found, -1 if not found. <br>
	 * @param idTosearch the id of the Employee that will be searched.
	 */
	public int searchEmployeePosition(final String idTosearch) {
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
	}//End searchEmployeePosition

	/**
	 * returns the enabled status of a specific employee.<br>
	 *     <b>pre:</b> the object that calls the method is not null. the id is being used by an employee <br>
	 *     <b>post:</b> the enabled status of a specific employee. <br>
	 * @param idToSearch the id of the employee to search. This id is associated with an employee.
	 */
	public boolean getEmployeeEnabledStatus(final String idToSearch) {
		return employees.get(searchEmployeePosition(idToSearch)).getEnabled();
	}//End getEmployeeEnabledStatus

	/**
	 * adds a new employee to the system. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> a new employee has been added. <br>
	 * @param name the name of the employee.
	 * @param lastName the last name of the employee.
	 * @param id the id of the employee.
	 */
	public void addEmployee(String name, String lastName, String id) throws IOException {
		Employee newEmployee = new Employee(loggedUser, name, lastName, id);
		if(employees.isEmpty()) {
			employees.add(newEmployee);
		} else {
			int i = 0;
			while(i < employees.size() && employees.get(i).getId().compareTo(newEmployee.getId()) < 0) {
				i ++;
			}//End while
			employees.add(i, newEmployee);
			if(loggedUser != null) {
				loggedUser.setLinked(true);
			}//End if
		}//End else
		saveAllData();
	}//End addEmployee

	/**
	 * changes the information of an employee.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the information of the employee has been changed. <br>
	 * @param employee the reference to the employee to change.
	 * @param name the new name for the given employee.
	 * @param lastName the new last name for the given employee.
	 * @param id the new id for the given employee
	 */
	public void changeEmployee(Employee employee, final String name, final String lastName, final String id) throws IOException {
		String oldId = employee.getId();
		employee.setName(name);
		employee.setLastName(lastName);
		employee.setId(id);
		employee.setModifier(getLoggedUser());
		Collections.sort(employees);
		if(searchUserPosition(oldId) != -1) {
			User user = users.get(searchUserPosition(oldId));
			user.setName(name);
			user.setLastName(lastName);
			user.setId(id);
			user.setModifier(getLoggedUser());
			Collections.sort(users);
			saveAllData();
		}//End if
		loggedUser.setLinked(true);
		saveAllData();
	}//End changeEmployee

	/**
	 * changes the enabled status of a given employee.<br>
	 *     <b>pre:</b> the object that calls the method is not null. the employee exists in the system. <br>
	 *     <b>post:</b> the status of the employee has been changed. <br>
	 * @param employee the reference of the employee to change the enabled status. <br>
	 */
	public boolean changeEmployeeEnabledStatus(Employee employee) throws IOException {
		if(employee.getEnabled()) {
			employee.setEnabled(false);
			employee.setModifier(loggedUser);
			loggedUser.setLinked(true);
			if(searchUserPosition(employee.getId()) != -1) {
				User user = users.get(searchEmployeePosition(employee.getId()));
				user.setEnabled(false);
				user.setModifier(loggedUser);
				loggedUser.setLinked(true);
			}//End if
			saveAllData();
			return false;
		} else {
			employee.setEnabled(true);
			employee.setModifier(loggedUser);
			loggedUser.setLinked(true);
			saveAllData();
			return true;
		}//End enableEmployee
	}//End enableEmployeeEnabledStatus

	/**
	 * removes a given employee<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> if the employee is not linked, the employee will be removed. if linked, the employee will not be removed. <br>
	 * @param employee reference to the employee to be removed from the system.
	 */
	public boolean removeEmployee(Employee employee) throws IOException {
		if(!employee.getLinked()) {
			employees.remove(employee);
			saveAllData();
			return true;
		} else {
			return false;
		}//End else
	}//End removeEmployee

	/**
	 * validates if a given username is already in use. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> true if is already in use, false if not. <br>
	 * @param userNameToValidate the username to search.
	 */
	public boolean validateUserName(String userNameToValidate) {
		boolean found = false;
		for(int i = 0; i < users.size() && !found; i ++) {
			User user = users.get(i);
			if(user.getUserName().equals(userNameToValidate)) {
				found = true;
			}//End if
		}//End for
		return found;
	}//End validateUserName

	/**
	 * validates the credentials for login of an user.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> true if credentials correspond to a real user in the system, false if not. <br>
	 * @param userName the username of the user.
	 * @param password the password of the user.
	 */
	public boolean validateCredentials(String userName, String password) {
		boolean validate = false;
		for(int i = 0; i < users.size() && !validate; i ++) {
			User user = users.get(i);
			if(user.getUserName().equals(userName) && user.getPassword().equals(password)) {
				validate = true;
			}//End if
		}//End for
		return validate;
	}//End validatePassword

	/**
	 * searches an user given its username.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the index of the user if found, -1 if not found. <br>
	 * @param nameToSearch username of the user to search.
	 */
	public int searchUserPositionByName(final String nameToSearch) {
		boolean validate = false;
		for(int i = 0; i < users.size() && !validate; i ++) {
			User user = users.get(i);
			if(user.getUserName().equals(nameToSearch)) {
				validate = true;
				return i;
			}//End if
		}//End for
		return -1;
	}//End searchUserPositionByName

	/**
	 * searches an user given its id. Uses binary search. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the index of the user in the list if found, -1 if not found. <br>
	 * @param idToSearch the id of the user to search.
	 */
	public int searchUserPosition(final String idToSearch) {
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
			}//End else
		}//End while
		return -1;
	}//End searchUserPosition

	/**
	 * returns the enabled status of an user given its username.<br>
	 *     <b>pre:</b> the name to search corresponds to a real user in the system. <br>
	 *     <b>post:</b> the enabled status of the given user. <br>
	 * @param nameToSearch the name of the user to get the enabled status. This name corresponds to a real user in the system.
	 */
	public boolean getUserEnabledStatus(final String nameToSearch) {
		return users.get(searchUserPositionByName(nameToSearch)).getEnabled();
	}//End getUserEnabledStatus

	/**
	 * adds a new user to the system. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> a new user has been added to the system. <br>
	 * @param idEmployee the id of the employee.
	 * @param userName the username of the new user.
	 * @param password the password of the new user.
	 */
	public void addUser(String idEmployee, String userName, String password) throws IOException {
		Employee employee = employees.get(searchEmployeePosition(idEmployee));
		String name = employee.getName();
		String lastName = employee.getLastName();
		String id = employee.getId();
		User newUser = new User(loggedUser, name, lastName, id, userName, password);
		employee.setLinked(true);
		if(users.isEmpty()) {
			users.add(newUser);
		} else {
			int i = 0;
			while(i < users.size() && users.get(i).getId().compareTo(newUser.getId()) < 0) {
				i ++;
			}//End while
			users.add(i, newUser);
			if(loggedUser != null) {
				loggedUser.setLinked(true);
			}//End if
		}//End else
		saveAllData();
	}//End addUser

	/**
	 * changes the values of the attributes of a given user. <br>
	 *     <b>pre:</b> the user exists in the system. <br>
	 *     <b>post:</b> the information has been changed. <br>
	 * @param user reference of the user to be changed.
	 * @param userName the new username of the user.
	 * @param password the new password of the user.
	 */
	public void changeUser(User user, final String userName, final String password) throws IOException {
		user.setUserName(userName);
		user.setPassword(password);
		user.setModifier(loggedUser);
		loggedUser.setLinked(true);
		saveAllData();
	}//End changeUser

	/**
	 * changes the enabled status of an user. <br>
	 *     <b>pre:</b> the user exists in the system. <br>
	 *     <b>post:</b> the enabled status has been changed. <br>
	 * @param user the reference of the user to change the enabled status.
	 */
	public boolean changeUserEnabledStatus(User user) throws IOException {
		if(user.getEnabled()) {
			user.setEnabled(false);
			user.setModifier(loggedUser);
			loggedUser.setLinked(true);
			saveAllData();
			return false;
		} else {
			user.setEnabled(true);
			user.setModifier(loggedUser);
			Employee employee = employees.get(searchEmployeePosition(user.getId()));
			employee.setEnabled(true);
			employee.setModifier(loggedUser);
			loggedUser.setLinked(true);
			saveAllData();
			return true;
		}//End else
	}//End changeUserEnabledStatus

	/**
	 * removes an user from the system.<br>
	 *     <b>pre:</b> the user exists in the system. the object that calls the method is not null. <br>
	 *     <b>post:</b> if the user is not linked, the user will be removed, if linked, the user will not be removed. <br>
	 * @param user the reference of the user to remove.
	 */
	public boolean removeUser(User user) throws IOException {
		if(!user.getLinked()) {
			users.remove(user);
			saveAllData();
			return true;
		} else {
			return false;
		}//End else
	}//End removeUser

	/**
	 * searches a customer in the list given its id.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the index of the customer if found. -1 if not found. <br>
	 * @param idToSearch the id of the customer to be searched.
	 */
	public int searchCustomerPosition(final String idToSearch) {
		for(int i = 0; i < customers.size(); i ++) {
			Customer customer = customers.get(i);
			if(customer.getId().equals(idToSearch)) {
				return i;
			}//End if
		}//End for
		return -1;
	}//End searchCustomerPositionById

	/**
	 * calls the method that search the customers that meet a given condition.<br>
	 *     <b>pre:</b> the object that calls this method is not null. <br>
	 *     <b>post:</b> returns a list that contains the customers that meet the condition. <br>
	 * @param condition the condition used to made the multiple binary searches.
	 */
	public List<Customer> searchCustomersByCondition(String condition) {
		long start = System.currentTimeMillis();
		List<Customer> coincidents = new ArrayList<>();
		coincidents.addAll(searchCustomersByName(condition.toLowerCase()));
		coincidents.addAll(searchCustomersByLastName(condition.toLowerCase()));
		coincidents.addAll(searchCustomersByFullName(condition.toLowerCase()));
		long end = System.currentTimeMillis();
		double searchTime = (double) (end - start) / 1000;
		setBinarySearchTime(searchTime);
		return coincidents;
	}//End searchCustomersByName

	/**
	 * searches the customers whose name is equal to the given.<br>
	 *     <b>pre:</b> the object that calls this method is not null. <br>
	 *     <b>post:</b> returns a list with the customers that meet the given name condition. <br>
	 * @param name the name of the customers to be searched.
	 */
	private List<Customer> searchCustomersByName(String name) {
		List<Customer> aux = new ArrayList<>(customers);
		List<Customer> added = new ArrayList<>();
			int start = 0;
			int end = aux.size() - 1;
			while(start <= end) {
				int mid = (start + end) / 2;
				if(aux.get(mid).getName().toLowerCase().compareTo(name) == 0) {
					added.add(aux.get(mid));
				} else if (aux.get(mid).getName().toLowerCase().compareTo(name) < 0) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
				aux.remove(aux.get(mid));
				start = 0;
				end = aux.size() - 1;
			}//End while
		return added;
	}//End searchByName

	/**
	 * searches the customers whose last name is equal to the given<br>
	 *     <b>pre:</b> the object that calls this method is not null. <br>
	 *     <b>post:</b> returns a list with the customers that meet the given last name condition. <br>
	 * @param lastName the last name of the customers to be searched.
	 */
	private List<Customer> searchCustomersByLastName(String lastName) {
		List<Customer> aux = new ArrayList<>(customers);
		List<Customer> added = new ArrayList<>();
			int start = 0;
			int end = aux.size() - 1;
			while(start <= end) {
				int mid = (start + end) / 2;
				if(aux.get(mid).getLastName().toLowerCase().compareTo(lastName) == 0) {
					added.add(aux.get(mid));
				} else if (aux.get(mid).getLastName().toLowerCase().compareTo(lastName) < 0) {
					start = mid + 1;
				} else {
					end = mid - 1;
				}
				aux.remove(aux.get(mid));
				start = 0;
				end = aux.size() - 1;
			}//End while
		return added;
	}//End searchByLastName

	/**
	 * searches the customers whose full name is equal to the given<br>
	 *     <b>pre:</b> the object that calls this method is not null. <br>
	 *     <b>post:</b> returns a list with the customers that meet the given full name condition. <br>
	 * @param fullName the full name of the customers to be changed.
	 */
	private List<Customer> searchCustomersByFullName(String fullName) {
		List<Customer> aux = new ArrayList<>(customers);
		List<Customer> added = new ArrayList<>();
		int start = 0;
		int end = aux.size() - 1;
		while(start <= end) {
			int mid = (start + end) / 2;
			String auxFullName = aux.get(mid).getName().toLowerCase() + " " + aux.get(mid).getLastName().toLowerCase();
			if(auxFullName.compareTo(fullName) == 0) {
				added.add(aux.get(mid));
			} else if (auxFullName.compareTo(fullName) < 0) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
			aux.remove(aux.get(mid));
			start = 0;
			end = aux.size() - 1;
		}//End while
		return added;
	}//End searchByFullName

	/**
	 * returns the enabled status of a customer given its id.<br>
	 *     <b>pre:</b> the id is currently being used by a customer in the system. <br>
	 *     <b>post:</b> the enabled status of the customer. <br>
	 * @param idToSearch the id of the customer to be searched. this id is currently being used by a customer.
	 */
	public boolean getCustomerEnabledStatus(final String idToSearch) {
		return customers.get(searchCustomerPosition(idToSearch)).getEnabled();
	}//End getCustomerEnabledStatus

	/**
	 * Imports users from a file and adds them to the system.<br>
	 *     <b>pre:</b> the file exists. the object that calls the method is not null. <br>
	 *     <b>post:</b> if the id of the customers is not being used, the customers will be added. <br>
	 * @param file the file that will be used to import from. file exists.
	 * @param separator the separator that will be used to separate the data of each line of the file.
	 */
	public boolean importCustomerData(final File file, final String separator) throws IOException {
		boolean all = true;
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		while(line != null) {
			String[] parts = line.split(separator);
			String id = parts[2];
			if(searchCustomerPosition(id) == -1) {
				String name = parts[0];
				String lastName = parts[1];
				String address = parts[3];
				String nPhone = parts[4];
				String remark = parts[5];
				addCustomer(name, lastName, id, address, nPhone, remark);
			} else {
				all = false;
			}//End else
			line = br.readLine();
		}//End while
		br.close();
		return all;
	}//End importCustomerData

	/**
	 * adds a new customer to the system. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> a new customer has been added to the system. <br>
	 * @param name the name of the new customer.
	 * @param lastName the last name of the new customer.
	 * @param id the id of the new customer.
	 * @param address the address of the new customer.
	 * @param nPhone the phone number of the new customer.
	 * @param remark the remark field of the new customer.
	 */
	public void addCustomer(String name, String lastName, String id, String address, String nPhone, String remark) throws IOException {
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
		}//End else
		saveAllData();
	}//End addCustomer

	/**
	 * changes the value of the attributes of a given customer. <br>
	 *     <b>pre:</b> the given customer must exist in the system. <br>
	 *     <b>post:</b> the information of the customer has been changed. <br>
	 * @param customer the reference of the customer to be changed. customer must exist in the system.
	 * @param name the new name of the customer.
	 * @param lastName the new last name of the customer.
	 * @param id the new id of the customer.
	 * @param address the new address of the customer.
	 * @param nPhone the new phone number of the customer.
	 * @param remark the new remark field of the customer.
	 */
	public void changeCustomer(Customer customer, String name, String lastName, String id, String address, String nPhone, String remark) throws IOException {
		customer.setName(name);
		customer.setLastName(lastName);
		customer.setId(id);
		customer.setAddress(address);
		customer.setNPhone(nPhone);
		customer.setRemark(remark);
		customer.setModifier(loggedUser);
		Comparator<Customer> lastNameAndNameComparator = new LastNameAndNameComparator();
		Collections.sort(customers, lastNameAndNameComparator);
		saveAllData();
	}//End changeCustomer

	/**
	 * changes the enabled status of a given customer<br>
	 *     <b>pre:</b> the customer must exist in the system. <br>
	 *     <b>post:</b> the enabled status has been changed. <br>
	 * @param customer the reference of the customer to change the enabled status. customer must exist.
	 */
	public boolean changeCustomerEnabledStatus(Customer customer) throws IOException {
		if(customer.getEnabled()) {
			customer.setEnabled(false);
			customer.setModifier(loggedUser);
			loggedUser.setLinked(true);
			saveAllData();
			return false;
		} else {
			customer.setEnabled(true);
			customer.setModifier(loggedUser);
			loggedUser.setLinked(true);
			saveAllData();
			return true;
		}//End else
	}//End changeCustomerEnabledStatus

	/**
	 * removes a customer from the system.<br>
	 *     <b>pre:</b> the customer must exist in the system. <br>
	 *     <b>post:</b> the customer has been removed from the system. <br>
	 * @param customer the reference of the customer to be removed.
	 */
	public boolean removeCustomer(Customer customer) throws IOException {
		if(!customer.getLinked()) {
			customers.remove(customer);
			saveAllData();
			return true;
		} else {
			return false;
		}//End else
	}//End removeCustomerById

	/**
	 * writes a defined set of information about the products in the system on a given file. <br>
	 *     <b>pre:</b> the object that calls the method is not null. the file must exist.<br>
	 *     <b>post:</b> the information has been written in the given file. <br>
	 * @param file the target file to use during the process. file must exist.
	 * @param s the separator to use to separate the different fields.
	 * @param initialDate the initial date in the range to export the information.
	 * @param finishDate the final date in the range to export the information.
	 */
	public void exportProductsData(File file, String s, Date initialDate, Date finishDate) throws FileNotFoundException {
		double totalPaid = 0;
		int delivered = 0;
		PrintWriter pw = new PrintWriter(file);
		List<Order> ords = getOrdersInRange(initialDate, finishDate);
		String columns = "Nombre"+s+"Tamanio"+s+"Precio"+s+"Cantidad de veces pedido"+s+"Total"+"\n";
		pw.write(columns);
		for(int i = 0; i < products.size(); i ++) {
			Product product = products.get(i);
			String name = product.getName();
			String size = product.getSize();
			double price = product.getPrice();
			int amount = 0;
			for(int j = 0; j < ords.size(); j ++) {
				Order order = ords.get(j);
				int index = order.findProductInOrder(name, size);
				if(index != -1 && order.getStatus().equalsIgnoreCase("ENTREGADO")) {
					amount += order.findAmountProduct(index);
				}//End if
			}//End for
			String toWrite = name + s + size + s + "$" + price + s + amount + s + "$" + (price*amount) + "\n";
			totalPaid += (price*amount);
			delivered += amount;
			pw.write(toWrite);
		}//End for
		String total = s + s + s + delivered + s + "$" + totalPaid + "\n";
		pw.write(total);
		String rows = "Registros totales: " + products.size() + "\n";
		pw.write(rows);
		pw.close();
	}//End exportProductsData

	/**
	 * searches a ProductBase in the list of productBase given its name. <br>
	 *     <b>pre:</b> the object that calls the method is not null.<br>
	 *     <b>post:</b> returns the index of the productBase in the list if found, -1 if not. <br>
	 * @param name the name of the product base to search.
	 */
	private int findProductBase(final String name){
		boolean found = false;
		int index = -1;
		for(int i = 0; i < productBase.size() && !found; i++) {
			if(productBase.get(i).getName().equalsIgnoreCase(name)) {
				index = i;
				found = true;
			}//End if
		}//End for
		return index;
	}//End findProduct

	/**
	 * adds a new product base to the system.<br>
	 *     <b>pre:</b> the object that calls the method is not null.<br>
	 *     <b>post:</b> adds the product if the name is not found. <br>
	 * @param name the name of the new product base.
	 * @param ingredients the list of ingredients of the product base.
	 * @param price the list of prices of the product base.
	 * @param size the list of sizes of the product base.
	 * @param type the type of the product base.
	 */
	public boolean addProduct(final String name,List<String> ingredients,final List<Double> price,final List<String> size,final String type)throws IOException{
		boolean added = false;
		if(findProductBase(name) < 0){
			DishType dishType = dishTypeToAdd(type,price.size());
			productBase.add(new ProductBase(getLoggedUser(),name,dishType,ingredientsToAdd(ingredients,price.size())));
			createSubproduct(productBase.get(productBase.size() - 1),price,size);
			added = true;
			loggedUser.setLinked(true);
			saveAllData();
		}//End if
		return added;
	}//End addProduct

	/**
	 * imports products from a file using two separators.<br>
	 *     <b>pre:</b> the file exists. The object that calls the method is not null.<br>
	 *     <b>post:</b> all the products that are not in the system will be added. <br>
	 * @param file the file to import the information from.
	 * @param mSeparator the main separator to use.
	 * @param sSeparator the secondary separator to use.
	 */
	public boolean importProducts(File file, String mSeparator, String sSeparator) throws IOException {
		boolean all = true;
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		while(line != null) {
			String[] parts = line.split(mSeparator);
			String name = parts[0];
			List<String> ingredients = Arrays.asList(parts[1].split(sSeparator));
			List<Double> prices = stringListToDouble(Arrays.asList(parts[2].split(sSeparator)));
			List<String> sizes = Arrays.asList(parts[3].split(sSeparator));
			String type = parts[4];
			boolean aux = addProduct(name, ingredients, prices, sizes, type);
			if(!aux) {
				all = false;
			} else {
				loggedUser.setLinked(true);
			}
			line = br.readLine();
		}//End while
		br.close();
		return all;
	}//End importProducts

	/**
	 * converts a list of String elemnts to a list of Double elements<br>
	 *     <b>pre:</b> the String list contains elements that can be parsed to Double.<br>
	 *     <b>post:</b> returns the list with the elements parsed. <br>
	 * @param stringList the list to be converted to Double. Contains elements that can be converted to Double.
	 */
	public List<Double> stringListToDouble(List<String> stringList) {
		List<Double> doubleList = new ArrayList<Double>();
		for(int i = 0; i < stringList.size(); i ++) {
			doubleList.add(Double.parseDouble(stringList.get(i)));
		}//End for
		return doubleList;
	}//End stringListToDouble

	/**
	 * creates the subproducts from the ProductsBase.<br>
	 *     <b>pre:</b> the object that calls the method is not null.<br>
	 *     <b>post:</b> the new subproducts have been created from the product base.<br>
	 * @param pd the ProductBase to create the subproducts from. pd != null.
	 * @param price the list of prices to use to create the subproducts.
	 * @param size the list of sizes to use to create the subproducts.
	 */
	public void createSubproduct(ProductBase pd,List<Double> price,final List<String> size) throws IOException {
		for(int i = 0; i < price.size();i++){
			int sizeIndex = findProductSize(size.get(i));
			ProductSize s;
			if(sizeIndex < 0){
				s = new ProductSize(size.get(i));
				this.sizes.add(s);
			}else
				s = sizes.get(sizeIndex);
			Product p = new Product(pd,s,price.get(i));
			pd.updateNumberOfSubProducts(1);
			products.add(p);
			loggedUser.setLinked(true);
		}//End for
		saveAllData();
	}//End createSubproduct

	/**
	 * <br>
	 *     <b>pre:</b> the object that calls the method is not null.<br>
	 *     <b>post:</b> the index of the size in the list if found. -1 if not. <br>
	 * @param s the size to search.
	 */
	private int findProductSize(final String s){
		boolean found = false;
		int index = -1;
		for(int i = 0; i < sizes.size() && !found;i++){
			if(s.equalsIgnoreCase(sizes.get(i).getSize())){
				index = i;
				found = true;
			}//End if
		}//End
		return index;
	}//End findProductSize

	/**
	 * converts a list of Strings to a list of Ingredients.<br>
	 *     <b>pre:</b> the object that calls the method is not null.<br>
	 *     <b>post:</b> the list of Ingredients converted using the list of Stirngs. <br>
	 * @param ingredients the list of Strings to use to convert.
	 * @param numberLinks the number of links to add to the ingredients.
	 */
	private List<Ingredient> ingredientsToAdd(List<String> ingredients, int numberLinks) throws IOException {
		List<Ingredient> ingd = new ArrayList<Ingredient>();
		for(int i = 0; i < ingredients.size();i++){
			int ingdIndex = findIngredient(ingredients.get(i));
			if(ingdIndex < 0){
				Ingredient in = new Ingredient(getLoggedUser(),ingredients.get(i));
				in.updateNumberOfLinks(numberLinks);
				in.updateLinkStatus();
				addIngredient(in);
				ingd.add(in);
				loggedUser.setLinked(true);
			}else{
				this.ingredients.get(ingdIndex).updateNumberOfLinks(numberLinks);
				this.ingredients.get(ingdIndex).updateLinkStatus();
				ingd.add(this.ingredients.get(ingdIndex));
			}
		}//End for
		saveAllData();
		return ingd;
	}//End ingredientsToAdd

	/**
	 * creates a new dish type to be added to the products.<br>
	 *     <b>pre:</b> the object that calls the method is not null.<br>
	 *     <b>post:</b> a new dish type has been created if this did not exist. <br>
	 * @param dish the name of the new dish to add.
	 * @param numberLinks the number of links to be used to update in the dish type.
	 */
	private DishType dishTypeToAdd(String dish,int numberLinks) throws IOException {
		int dtIndex = findDishType(dish);
		DishType t;
		if(dtIndex < 0){
			t = new DishType(getLoggedUser(),dish);
			addDishType(t);
		}else
			t = types.get(dtIndex);
		t.updateNumberOfLinks(numberLinks);
		t.updateLinkStatus();
		saveAllData();
		return t;
	}//End dishTypeToAdd

	/**
	 * changes the information of a specific product.<br>
	 *     <b>pre:</b> the product must exist in the system. <br>
	 *     <b>post:</b> the product has been changed. <br>
	 * @param product the reference of the product to be changed. The product must exist.
	 * @param newName the new name of the product.
	 * @param Newingredients the new list of ingredients of the product.
	 * @param prices the new price of the product.
	 * @param sizes the new size of the product.
	 * @param typeName the new type of the product.
	 */
	public boolean changeProduct(Product product,final String newName,final List<String> Newingredients,final double prices,final String sizes,final String typeName) throws IOException {
		boolean changed = false;
		String n = product.getName();
		if(!product.getName().equalsIgnoreCase(newName)){
			int productIndex = findProductBase(newName);
			if(productIndex < 0){
				n = newName;
				changed = true;
			}//End if
		}else
			changed = true;
		if(changed){
			updateProductsOldObjectsLinks(product);
			product.changesProductBase(n, ingredientsToAdd(Newingredients,product.getProductBase().getNumberOfSubproducts()), dishTypeToAdd(typeName,product.getProductBase().getNumberOfSubproducts()));
			product.setPrice(prices);
			product.setSize(sizes);
			product.setModifier(loggedUser);
			loggedUser.setLinked(true);
			saveAllData();
		}//End if
		return changed;
	}//End changeProduct

	/**
	 * updates the links of the old objects linked to a product.<br>
	 *     <b>pre:</b> the product must exist. <br>
	 *     <b>post:</b> the number of links of the old objects have been updated. <br>
	 * @param product reference of the product to update the links.
	 */
	private void updateProductsOldObjectsLinks(Product product){
		List<Ingredient> oldIng =  product.getProductBase().getIngredientsList();
		DishType oldDish = product.getProductBase().getDishType();
		for(int i = 0; i < oldIng.size();i++){
			oldIng.get(i).updateNumberOfLinks(-product.getProductBase().getNumberOfSubproducts());
			oldIng.get(i).updateLinkStatus();
		}//End for
		oldDish.updateNumberOfLinks((-product.getProductBase().getNumberOfSubproducts()));
		oldDish.updateLinkStatus();
	}//End updateProductsObjectsLinks

	/**
	 * changes the enabled status of a given product.<br>
	 *     <b>pre:</b> the product must exist. <br>
	 *     <b>post:</b> the enabled status has been changed.<br>
	 * @param product reference of the product to change the enabled status.
	 */
	public void changeEnableProduct(Product product) throws IOException {
		product.setEnable(!product.getEnable());
		loggedUser.setLinked(true);
		saveAllData();
	}//End disableProduct

	/**
	 * removes a product from the list. <br>
	 *     <b>pre:</b> the product must exist.
	 *     <b>post:</b> the product was removed if it was not linked.
	 * @param product reference of the product to be removed.
	 */
	public boolean removeProduct(Product product) throws IOException {
		boolean removed = false;
		if(!product.getLinked()){
			updateProductsOldObjectsLinks(product);
			product.getProductBase().updateNumberOfSubProducts(-1);
			products.remove(product);
			removed = true;
			saveAllData();
		}//End if
		return removed;
	}//removeProduct

	/**
	 * searches subproducts given their name<br>
	 *     <b>pre:</b> the object that calls the method is not null.<br>
	 *     <b>post:</b> the list of subproducts that have that name. <br>
	 * @param name the name of the subproducts so be searched.
	 */
	public List<Integer> findSubProducts(String name){
		List<Integer> index = new ArrayList<Integer>();
		for(int i = 0; i < products.size();i++){
			if(name.equals((products.get(i).getProductBase()).getName())){
				index.add((Integer) i);
			}//End if
		}//End for
		return index;
	}//End findSubProducts

	/**
	 * searches an ingredient in the list given its name. Uses binary search.<br>
	 *     <b>pre:</b> the object that calls the method is not null.<br>
	 *     <b>post:</b> the index of the ingredient in the list if found. -1 if not found. <br>
	 * @param ingredient the name of the ingredient to be searched.
	 */
	public int findIngredient(final String ingredient){
		int index = -1;
		int start = 0;
		int end = ingredients.size() - 1;
		while( start <= end && index < 0) {
			int half = (end + start)/2;
			if(ingredients.get(half).getName().toLowerCase().compareTo(ingredient.toLowerCase()) == 0){
				index = half;
			}else if(ingredients.get(half).getName().toLowerCase().compareTo(ingredient.toLowerCase()) > 0){
				end = half - 1;
			}else{
				start = half + 1;
			}//End else
		}//End while
		return index;
	}//End findIngredient

	/**
	 * searches for the ingredients that meet the boolean enable.<br>
	 *     <b>pre:</b> the object that calls the method is not null.<br>
	 *     <b>post:</b> the list of ingredients that meet the condition given by the boolean enable. <br>
	 * @param enable the condition to be used.
	 */
	public List<Ingredient> getIngredients(boolean enable){
		List<Ingredient> enableIngredients = new ArrayList<Ingredient>();
		for(int i = 0; i < ingredients.size();i++){
			if(ingredients.get(i).getEnable() == enable)
				enableIngredients.add(ingredients.get(i));
		}//End for
		return enableIngredients;
	}//End getIngredients

	/**
	 * adds a new ingredient to the system. <br>
	 *     <b>pre:</b> the object that calls the method is not null.<br>
	 *     <b>post:</b> adds a new ingredient if this did not exist before.
	 * @param ingredient the name of the ingredient to add.
	 */
	public boolean addIngredient(final String ingredient) throws IOException {
		boolean added = false;
		if(findIngredient(ingredient) < 0){
			if(ingredients.isEmpty()){
				ingredients.add(new Ingredient(getLoggedUser(),ingredient));
			}else {
				Ingredient in = new Ingredient(getLoggedUser(),ingredient);
				int j;
				for(j = 0; j < ingredients.size() && ingredients.get(j).compareTo(in) < 0;j++);
					ingredients.add(j,in);
			}//End else
			loggedUser.setLinked(true);
			added = true;
		}//End if
		saveAllData();
		return added;
	}//End addIngredient

	/**
	 * Adds a new ingredient to the list using an Ingredient object.<br>
	 *     <b>pre:</b> the object that calls the method is not null.<br>
	 *     <b>post:</b> a new ingredient has been added to the list. <br>
	 * @param ingredient the new ingredient to add. ingredient != null.
	 */
	private void addIngredient(Ingredient ingredient)throws IOException{
		int j;
		for(j = 0; j < ingredients.size() && ingredients.get(j).compareTo(ingredient) < 0;j++);
			ingredients.add(j,ingredient);
		loggedUser.setLinked(true);
		saveAllData();
	}//End addIngredient

	/**
	 * changes the information of a  given ingredient.<br>
	 *     <b>pre:</b> the ingredient exists in the system. <br>
	 *     <b>post:</b> the information of the ingredient has been changed. <br>
	 * @param ingredient
	 * @param newName
	 */
	public boolean changeIngredient(Ingredient ingredient,final String newName) throws IOException {
		boolean changed = false;
		if(findIngredient(newName) < 0){
			ingredient.setName(newName);
			loggedUser.setLinked(true);
			ingredient.setModifier(loggedUser);
			Collections.sort(ingredients);
			changed = true;
			saveAllData();
		}//End if
		return changed;
	}//End changeIngredient

	/**
	 * changes the enabled status of a given ingredient.<br>
	 *     <b>pre:</b> the ingredient exists. <br>
	 *     <b>post:</b> the enabled status of the ingredient has been changed. <br>
	 * @param ingredient the reference of the ingredient to be changed.
	 */
	public void changeEnableIngredient(Ingredient ingredient) throws IOException {
		ingredient.setEnable(!ingredient.getEnable());
		ingredient.setModifier(loggedUser);
		loggedUser.setLinked(true);
		saveAllData();
	}//End changeEnableIngredient

	/**
	 * removes a given ingredient from the system. <br>
	 *     <b>pre:</b> the ingredient exists in the system. <br>
	 *     <b>post:</b> the ingredient has been removed if it was not linked. <br>
	 * @param ingredient the ingredient to be removed from the list.
	 */
	public boolean removeIngredient(Ingredient ingredient) throws IOException {
		boolean removed = false;
		if(!ingredient.getLinked()){
			ingredients.remove(ingredient);
			removed = true;
			saveAllData();
		}//End if
		return removed;
	}//End removeIngredient

	/**
	 * searches for the index of a dishtype given its name<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the index of the dishtype if found. -1 if not. <br>
	 * @param dishType the name of the dishtype to search.
	 */
	public int findDishType(final String dishType){
		int index = -1;
		int start = 0;
		int end = types.size() - 1;
		while( start <= end && index < 0) {
			int half = (end+start)/2;
			if(types.get(half).getName().toLowerCase().compareTo(dishType.toLowerCase()) == 0){
				index = half;
			}else if(types.get(half).getName().toLowerCase().compareTo(dishType.toLowerCase()) > 0){
				end = half - 1;
			}else{
				start = half + 1;
			}//End else
		}//End while
		return index;
	}//End findDishType

	/**
	 * adds a nex dishtype to the system.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> adds the dishtype if it was not before in the system. <br>
	 * @param dishType the name of the dishtype to be added.
	 */
	public boolean addDishType(final String dishType) throws IOException {
		boolean added = false;
		if(findDishType(dishType) < 0){
			if(types.isEmpty()){
				types.add(new DishType(getLoggedUser(),dishType));
			}else {
				DishType dt = new DishType(getLoggedUser(),dishType);
				int j = 0;
				for(j = 0; j < types.size() && types.get(j).compareTo(dt) < 0;j++);
				types.add(j,dt);
			}//End else
			added = true;
		}//end if
		saveAllData();
		return added;
	}//End addDishType

	/**
	 * returns a list that contains the dishtype that meet the given boolean condition.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> a list with the elements that meet the enable condition. <br>
	 * @param enable the condition of the elements of the list.
	 */
	public List<DishType> getDishtype(boolean enable){
		List<DishType> enableDishType = new ArrayList<DishType>();
		for(int i = 0; i < types.size();i++ ){
			if(types.get(i).getEnable() == enable){
				enableDishType.add(types.get(i));
			}//End if
		}//End for
		return enableDishType;
	}//End getDishType

	/**
	 * adds a new dishtype to the list given a DishType object. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the dishtype was successfully added. <br>
	 * @param dishType the new dishtype to be added.
	 */
	private void addDishType(final DishType dishType)throws IOException{
		int j;
		for(j = 0; j < types.size() && types.get(j).compareTo(dishType) < 0;j++);
		types.add(j,dishType);
		loggedUser.setLinked(true);
		saveAllData();
	}//End addDishType

	/**
	 * changes the information of a given dishtype.<br>
	 *     <b>pre:</b> the dishtype exists in the system. <br>
	 *     <b>post:</b> the dishtype was successfully changed. <br>
	 * @param dType the reference of the dishtype to change.
	 * @param newName the new name of the dishtype
	 */
	public boolean changeDishType(DishType dType,final String newName) throws IOException {
		boolean changed = false;
		if(findDishType(newName) < 0){
			dType.setName(newName);
			loggedUser.setLinked(true);
			dType.setModifier(loggedUser);
			Collections.sort(types);
			changed = true;
			saveAllData();
		}//End if
		return changed;
	}//End changeDishType

	/**
	 * changes the enabled status of a given dishtype. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the enabled status of the dishtype has been changed. <br>
	 * @param dType the reference of the dishtype to be changed. dishType exists.
	 */
	public void changeEnableDishType(DishType dType) throws IOException {
		dType.setEnable(!dType.getEnable());
		dType.setModifier(loggedUser);
		loggedUser.setLinked(true);
		saveAllData();
	}//End changeEnableDishType

	/**
	 * removes a given dishtype from the system.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> if the dishtype is not linked it will be removed. <br>
	 * @param dType the reference of the dishtype to be removed. dType exists.
	 */
	public boolean removeDishType(DishType dType) throws IOException {
		boolean removed = false;
		if(!dType.getLinked()){
			types.remove(dType);
			removed = true;
			saveAllData();
		}//End if
		return removed;
	}//End removeDishType

	/**
	 * writes a defined set of information on a given file using a given separator and in a range of dates.<br>
	 *     <b>pre:</b> the file exists. the object that calls the method is not null. <br>
	 *     <b>post:</b> the information has been written in the target file using the given separator. <br>
	 * @param file the target file where the information will be written.
	 * @param s the separator to use to separate the information.
	 * @param initialDate the initial date of the range.
	 * @param finishDate the final date of the range.
	 */
	public void exportOrdersData(File file, String s,Date initialDate,Date finishDate) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(file);
		List<Order> ords = getOrdersInRange(initialDate,finishDate);
		int max = 0;
		String toWrite = "";
		String columns = "Fecha"+s+"Hora"+s+"Nombre del cliente"+s+"Apellido del cliente"+s+ "Direccion del cliente" +
						  s+"Telefono del cliente"+s+"Nombre del empleado"+s+"Apellido del empleado"+s+
				         "Observaciones" +s+"Estado";
		for(int i = 0; i < ords.size(); i ++) {
			Order order = ords.get(i);
			List<Product> products = order.getProducts();
			if(products.size() > max) {
				max = products.size();
			}//End if
			List<Integer> amounts = order.getAmount();
			String date = order.getDate();
			String hour = order.getHour();
			String customerName = order.getCustomer().getName();
			String customerLastName = order.getCustomer().getLastName();
			String address = order.getCustomer().getAddress();
			String nPhone = order.getCustomer().getNPhone();
			String employeeName = order.getEmployee().getName();
			String employeeLastName = order.getEmployee().getLastName();
			String remark = order.getRemark();
			String status = order.getStatus();
			toWrite += date+s+hour+s+customerName+s+customerLastName+s+address+s+nPhone+s+
					         employeeName+s+employeeLastName+s+remark+s+status;
			for(int j = 0; j < products.size(); j ++) {
				Product product = products.get(j);
				String productName = product.getName();
				double price = product.getPrice();
				int amount = amounts.get(j);
				toWrite += s+productName + s + "$" +price + s +amount;
			}//End for
			toWrite += "\n";
		}//End for
		for(int i = 0; i < max; i ++) {
			columns += s+"Producto"+s+"Precio"+s+"Cantidad";
		}//End for
		columns += "\n";
		pw.write(columns);
		pw.write(toWrite);
		pw.close();
	}//End exportOrderData

	/**
	 * searches the index of an order given its code.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> returns the index in the list if found, -1 if not. <br>
	 * @param code the code of the order to search for.
	 */
	public int findOrder(final String code){
		int index = -1;
		boolean found = false;
		for(int i = 0; i < orders.size() && !found;i++){
			if(orders.get(i).getCode().equals(code)){
				index = i;
				found = true;
			}//End if
		}//End for
		return index;
	}//End findOrder

	/**
	 * returns the list of products base.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the list of products base. <br>
	 */
	public List<ProductBase> getProductsBase(){
		return productBase;
	}//End getProductsBase

	/**
	 * searches the orders that meet the given condition of the range of dates.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the list with the orders that are in the given range of dates. <br>
	 * @param initialDate the inital date of the range.
	 * @param finishDate the final date of the range.
	 */
	private List<Order> getOrdersInRange(Date initialDate, Date finishDate){
		List<Order> or = new ArrayList<Order>();
		for(int i = 0; i < orders.size();i++){
			if( orders.get(i).compareDate(initialDate) >= 0 && orders.get(i).compareDate(finishDate) <= 0){
				or.add(orders.get(i));
			}//End if
		}//End for
		Collections.sort(or);
		return or;
	}//End getOrdersInRange

	/**
	 * adds a new order to the system. <br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> a new order has been added. <br>
	 * @param nProducts the list of products of the new list.
	 * @param amount the amount solicited for each product of the order.
	 * @param remark the remark field of the new order.
	 * @param status the current status of the new order.
	 * @param idCustomer the id of the customer that solicited the order.
	 * @param idEmployee the id of the employee that will deliver the order.
	 */
	public void addOrder(List<Product> nProducts,List<Integer> amount,String remark,String status,String idCustomer,String idEmployee) throws IOException {
		int customerIndex = searchCustomerPosition(idCustomer);
		int employeeIndex = searchEmployeePosition(idEmployee);
		setOrderObjectsLinks(nProducts,1);
		orders.add(new Order(nProducts,amount,remark,status,customers.get(customerIndex),employees.get(employeeIndex),getLoggedUser()));
		loggedUser.setLinked(true);
		saveAllData();
	}//End addOrder

	/**
	 * Changes the amount of links of the elements of the order.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the number of links have been updated. <br>
	 * @param nProducts the products to update the links.
	 * @param numberLink the number of links to be added.
	 */
	private void setOrderObjectsLinks(List<Product> nProducts,int numberLink){
		for(int i = 0; i < nProducts.size();i++){
			nProducts.get(i).updateNumberOfLinks(numberLink);
			nProducts.get(i).updateLinkStatus();
		}//End for
	}//End updateOrderObjectsLinks

	/**
	 * imports information from a given file using three separators.<br>
	 *     <b>pre:</b> the file exists. the object that calls the method is not null. <br>
	 *     <b>post:</b> if the information in the file corresponds with information on the system, the orders will be added. <br>
	 * @param file the file from where the information will be imported.
	 * @param mSeparator the first separator to be used.
	 * @param sSeparator the second separator to be used.
	 * @param tSeparator the third separator to be used.
	 */
	public boolean importOrders(File file, String mSeparator, String sSeparator, String tSeparator) throws IOException {
		boolean all = true;
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();
		while(line != null) {
			String[] parts = line.split(mSeparator);
			List<String> productsParts = Arrays.asList(parts[0].split(sSeparator));
			List<Product> products = stringListToProduct(productsParts, tSeparator);
			List<Integer> amount = stringListToInteger(Arrays.asList(parts[1].split(sSeparator)));
			String remark = parts[2];
			String status = parts[3].toUpperCase();
			String idCustomer = parts[4];
			String idEmployee = parts[5];
			if(!products.isEmpty() && !amount.isEmpty() && searchEmployeePosition(idEmployee) != -1 && searchCustomerPosition(idCustomer) != -1) {
				addOrder(products, amount, remark, status, idCustomer, idEmployee);
				loggedUser.setLinked(true);
			} else {
				all = false;
			}
			line = br.readLine();
		}//End while
		br.close();
		return all;
	}//End importOrders

	/**
	 * parses the elements from a list of String to a list of Integer<br>
	 *     <b>pre:</b> the elements on the list can be parsed to Integer using Integer.parseInt() <br>
	 *     <b>post:</b> returns a list with the elements from the String list parsed to Integer.
	 * @param stringList the list that contains the elements to be parsed to Integer.
	 */
	public List<Integer> stringListToInteger(List<String> stringList) {
		List<Integer> integerList = new ArrayList<>();
		for(int i = 0; i < stringList.size(); i ++) {
			integerList.add(Integer.parseInt(stringList.get(i)));
		}
		return integerList;
	}//End stringListTointeger

	/**
	 * uses a separator to get information from a list of string a returns a list of products.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> returns a list of products using the information given by the list of String. <br>
	 * @param stringList the list to use to get the information from.
	 * @param separator the separator that will be used to separate the info of the elements of the String list.
	 *                   sepaarates the name and size of the products.
	 */
	public List<Product> stringListToProduct(List<String> stringList, String separator) {
		List<Product> productList = new ArrayList<>();
		for(int i = 0; i < stringList.size(); i ++) {
			String[] parts = stringList.get(i).split(separator);
			Product product = findProductByNameAndSize(parts[0], parts[1]);
			if(product != null) {
				productList.add(product);
			}//End if
		}//End for
		return productList;
	}//End stringArrayToProduct

	/**
	 * searches a product given its name and size.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> returns the product if found, null if not.
	 * @param name the name of the product to search.
	 * @param size the size of the product to search.
	 */
	public Product findProductByNameAndSize(String name, String size) {
		for(int i = 0; i < products.size(); i ++) {
			Product product = products.get(i);
			if(product.getName().equals(name) && product.getSize().equals(size)) {
				return product;
			}
		}
		return null;
	}//End findProductByNameAndSize

	/**
	 * changes the information of a given order.<br>
	 *     <b>pre:</b> the object that calls the method is not null. The order exists in the system.<br>
	 *     <b>post:</b> the information of the order has been successfully changed. <br>
	 * @param order the reference of the order to be changed. order != null.
	 * @param nProducts the new list of products of the order.
	 * @param amount the new amount of solicited products of the order.
	 * @param remark the new remark field of the order.
	 * @param status the new status of the order.
	 * @param idCustomer the new id of the customer of the order.
	 * @param idEmployee the new id of the employee that will deliver the order.
	 */
	public void changeOrder(Order order,List<Product> nProducts,List<Integer> amount,String remark,String status,String idCustomer,String idEmployee) throws IOException {
		int customerIndex = searchCustomerPosition(idCustomer);
		int employeeIndex = searchEmployeePosition(idEmployee);
		setOrderObjectsLinks(order.getProducts(),-1);
		order.setProduct(nProducts);
		setOrderObjectsLinks(order.getProducts(),1);
		order.setAmount(amount);
		order.setRemark(remark);
		order.setStatus(status);
		order.setCustomer(customers.get(customerIndex));
		order.setEmployee(employees.get(employeeIndex));
		order.setModifier(getLoggedUser());
		loggedUser.setLinked(true);
		saveAllData();
	}//End changeOrder

	/**
	 * changes the enabled status of the given order.<br>
	 *     <b>pre:</b> the order must exist in the system. <br>
	 *     <b>post:</b> the enabled status of the order has been changed. <br>
	 * @param order the order to be removed from the system.
	 */
	public void changeEnableOrder(Order order) throws IOException {
		order.setEnable(!order.getEnable());
		loggedUser.setLinked(true);
		saveAllData();
	}//End disableOrder

	/**
	 * returns a list of orders thar meet the given boolean condition.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the list of orders within the condition. <br>
	 * @param enable the condition of the elements of the list.
	 */
	public List<Order> getOrders(boolean enable){
		List<Order> enableOrders = new ArrayList<Order>();
		for(int i = 0; i < orders.size();i++){
			if(orders.get(i).getEnable() == enable)
				enableOrders.add(orders.get(i));
		}//End for
		return enableOrders;
	}//End getOrders

	/**
	 * removes a given order from the system.<br>
	 *     <b>pre:</b> the order exists in the system. <br>
	 *     <b>post:</b> the order has been removed. <br>
	 * @param order the order to be removed. must exist in the system.
	 */
	public void removeOrder(Order order) throws IOException {
		setOrderObjectsLinks(order.getProducts(),-1);
		orders.remove(order);
		saveAllData();
	}//End removeOrder

	/**
	 * returns a list of products that meet the given coniditon.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the list of products that meet the boolean enable condition. <br>
	 * @param enable the condition for the elements of the list.
	 */
	public List<Product> getProducts(boolean enable){
		List<Product> enableProducts = new ArrayList<Product>();
		for(int i = 0; i < products.size();i++){
			if(products.get(i).getEnable() == enable)
				enableProducts.add(products.get(i));
		}//End for
		return enableProducts;
	}//End getProducts

	/**
	 * returns the disabled products.<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> returns the list of products that are disabled. <br>
	 */
	public List<Product> getDisableProducts(){
		List<Product> disableProducts = new ArrayList<Product>();
		for(int i = 0; i < products.size();i++){
			if(!products.get(i).getEnable())
				disableProducts.add(products.get(i));
		}//End for
		return disableProducts;
	}//End getProducts

	/**
	 * returns the list of sizes<br>
	 *     <b>pre:</b> the object that calls the method is not null. <br>
	 *     <b>post:</b> the list of sizes. <br>
	 */
	public List<ProductSize> getSizes(){
		return sizes;
	}//End getSizes

}//End DeliveryManagerController class