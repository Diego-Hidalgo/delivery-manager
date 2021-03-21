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
	private List<Ingredient> ingredients;
	private List<DishType> types;
	
	public DeliveryManagerController(){
		loggedUser = null;
		orders = new ArrayList<Order>();
		customers = new ArrayList<Customer>();
		users = new ArrayList<User>();
		products = new ArrayList<Product>();
		employees = new ArrayList<Employee>();
		types = new ArrayList<DishType>();
		ingredients = new ArrayList<Ingredient>();
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

	private int findProduct(final String name){
		boolean found = false;
		int index = -1;
		for(int i = 0; i < products.size() && !found; i++) {
			if(products.get(i).getName().equalsIgnoreCase(name)) {
				index = i;
				found = true;
			}//End if
		}//End for
		return index;
	}//End findProduct

	public int findDishType(final String dishType){
		int index = -1;
		int start = 0;
		int end = types.size() - 1;
		while( start <= end) {
			int half = (start - end)/2;
			if(types.get(half).getName().compareTo(dishType) == 0){
				index = half;
			}else if(types.get(half).getName().compareTo(dishType) > 0){
				end = half;
			}else{
				start = half;
			}//End else
		}//End while
		return index;
	}//End findDishType

	public int findIngredient(final String ingredient){
		int index = -1;
		int start = 0;
		int end = ingredients.size() - 1;
		while( start <= end) {
			int half = (start - end)/2;
			if(ingredients.get(half).getName().compareTo(ingredient) == 0){
				index = half;
			}else if(ingredients.get(half).getName().compareTo(ingredient) > 0){
				end = half;
			}else{
				start = half;
			}//End else
		}//End while
		return index;
	}//End findIngredient

	private void changeIngredient(final List<String> Newingredients,final int productIndex){
		List<Ingredient> newIngredients = new ArrayList<Ingredient>();
		for(int i = 0; i < Newingredients.size(); i++){
			int ingredientIndex = findIngredient(Newingredients.get(i));
			if(ingredientIndex < 0){
				Ingredient ingredient = new Ingredient(Newingredients.get(i));
				addIngredient(ingredient);
				newIngredients.add(ingredient);
			}else
				newIngredients.add(ingredients.get(ingredientIndex));
		}//End for
		products.get(productIndex).setIngredient(newIngredients);
	}//End changeIngredient

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

	public void addProduct(final String name,final List<Double> price,final List<String> size,final String type){
		if(findProduct(name) < 0){
			if(products.isEmpty()){
				products.add(new Product(name,price,size,type));
			}else{
				double tp = 0;
				for(int i = 0; i < price.size(); i++){tp += price.get(i);}//End for
				Product pd = new Product(name,price,size,type);
				int j = 0;
				while(products.get(j).compareTo(tp) < 0){j++;}//End while
				products.add(j,pd);
			}//End else
		}//End if
	}//End addProduct

	public void changeProduct(final String oldName,final String newName,final List<String> Newingredients,final List<Double> prices,final List<String> sizes,final String typeName){
		int productIndex = findProduct(oldName);
		int dishIndex = findDishType(typeName);
		if(productIndex >= 0){
			products.get(productIndex).setName(newName);
			products.get(productIndex).setPrice(prices);
			products.get(productIndex).setSize(sizes);
			changeDishType(dishIndex,productIndex,typeName);
			changeIngredient(Newingredients,productIndex);
		}//End if
	}//End changeProduct

	public boolean removeProduct(String productName){
		boolean removed = false;
		int productIndex = findProduct(productName);
		if(productIndex >= 0)
			if(!products.get(productIndex).getLinked()){
				products.remove(productIndex);
				removed = true;
			}//End if
		return removed;
	}//removeProduct
	public void disableProduct(String productName){
		int productIndex = findProduct(productName);
		if(productIndex >= 0){
			products.get(productIndex).setEnable(false);
		}//End if
	}//End disableProduct

	public boolean addDishType(final String dishType){
		boolean added = false;
		if(findDishType(dishType) < 0){
			if(types.isEmpty()){
				types.add(new DishType(dishType));
			}else {
				DishType dt = new DishType(dishType);
				int j = 0;
				while(types.get(j).compareTo(dt) < 0){j++;}//End while
				types.add(dt);
			}//End else
			added = true;
		}//end if
		return added;
	}//End addProduct

	private void addDishType(final DishType dishType){
		int j = 0;
		while(types.get(j).compareTo(dishType) < 0){j++;}//End while
		types.add(dishType);
	}//End addProduct

	public void changeDishType(final String oldName,final String newName){
		int index = findDishType(oldName);
		if(index >= 0 && findDishType(newName) < 0)
			types.get(index).setName(newName);
	}//End changeDishType

	private void changeDishType(final int dishIndex,final int productIndex,final String typeName){
		if(dishIndex < 0){
			DishType dish = new DishType(typeName);
			addDishType(dish);
			products.get(productIndex).setType(dish);
		}else
			products.get(productIndex).setType(types.get(dishIndex));
	}//End changeDishType

	public boolean removeDishType(final String name){
		int index = findDishType(name);
		boolean removed = false;
		if(index >= 0)
			if(!types.get(index).getLinked()){
				types.remove(index);
				removed = true;
			}//End if
		return removed;
	}//End removeDishType

	public void disableDishType(final String name){
		int index = findDishType(name);
		if(index >= 0)
			types.get(index).setEnable(false);
	}//End disableIngredient

	public boolean addIngredient(final String ingredient){
		boolean added = false;
		if(findIngredient(ingredient) < 0){
			if(ingredients.isEmpty()){
				ingredients.add(new Ingredient(ingredient));
			}else {
				Ingredient in = new Ingredient(ingredient);
				int j = 0;
				while(ingredients.get(j).compareTo(in) < 0){j++;}//End while
				ingredients.add(j,in);
			}//End else
			added = true;
		}//End if
		return added;
	}//End addIngredient

	private void addIngredient(final Ingredient ingredient){
		int j = 0;
		while(ingredients.get(j).compareTo(ingredient) < 0){j++;}//End while
		ingredients.add(j,ingredient);
	}//End addIngredient

	public void changeIngredient(final String oldName,final String newName){
		int index = findIngredient(oldName);
		if(index >= 0 && findIngredient(newName) < 0)
			ingredients.get(index).setName(newName);
	}//End changeIngredient

	public boolean removeIngredient(final String name){
		int index = findIngredient(name);
		boolean removed = false;
		if(index >= 0)
			if(!ingredients.get(index).getLinked()){
				ingredients.remove(index);
				removed = true;
			}//End if
		return removed;
	}//End removeIngredient

	public void disableIngredient(final String name){
		int index = findIngredient(name);
		if(index >= 0)
			ingredients.get(index).setEnable(false);
	}//End disableIngredient

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
		customer.setEnabled(false);
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
		employee.setEnabled(false);
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
		user.setEnabled(false);
		user.setModifier(loggedUser);
	}//End disableUser

	public void removeUser(String userId) {
		int index = searchUserPosition(userId);
		users.remove(index);
	}//End removeUser

}//End DeliveryManagerController