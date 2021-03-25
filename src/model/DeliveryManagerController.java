package model;
import java.io.*;
import java.util.*;

public class DeliveryManagerController {

	private final static String EMPLOYEES_SAVEFILE_PATH = "save-files/employees-saveFile.csv";
	private final static String USERS_SAVEFILE_PATH = "save-files/users-saveFile.csv";
	private final static String CUSTOMERS_SAVEFILE_PATH = "save-files/customers-saveFile.csv";
	private final static String PRODUCTS_SAVEFILE_PATH = "save-files/products-saveFile.csv";
	private final static String TYPES_SAVEFILE_PATH = "save-files/types-saveFile.csv";
	private final static String INGREDIENTS_SAVEFILE_PATH = "save-files/ingredients-saveFile.csv";
	private final static String ORDERS_SAVEFILE_PATH = "save-files/orders-saveFile.csv";

	private User loggedUser;
	private List<Employee> employees;
	private List<User> users;
	private List<Customer> customers;
	private List<Product> products;
	private List<DishType> types;
	private List<Ingredient> ingredients;
	private List<Order> orders;

	public DeliveryManagerController(){
		loggedUser = null;
		employees = new ArrayList<Employee>();
		users = new ArrayList<User>();
		customers = new ArrayList<Customer>();
		products = new ArrayList<Product>();
		types = new ArrayList<DishType>();
		ingredients = new ArrayList<Ingredient>();
		orders = new ArrayList<Order>();
	}//End DeliveryManagerController

	public void setLoggedUser(final String idLoggedUser) {
		this.loggedUser = users.get(searchUserPosition(idLoggedUser));
	}//End setLoggedUser

	public void logOutUser() {
		this.loggedUser = null;
	}//End logOutUser

	public User getLoggedUser(){
		return loggedUser;
	}//End getLoggedUser

	public void loadAllData() throws IOException, ClassNotFoundException {
		loadEmployeesData();
		loadUsersData();
		loadCustomersData();
		loadProductsData();
		loadIngredientsData();
		loadTypesData();
		loadOrdersData();
	}//End loadAllData

	public void saveEmployeesData() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EMPLOYEES_SAVEFILE_PATH));
		oos.writeObject(employees);
		oos.close();
	}//End saveEmployeesData

	@SuppressWarnings("unchecked")
	public void loadEmployeesData() throws IOException, ClassNotFoundException {
		File f = new File(EMPLOYEES_SAVEFILE_PATH);
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			employees = (ArrayList<Employee>) ois.readObject();
			ois.close();
		}//End if
	}//End loadEmployeesData

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
	}

	public void addFirstEmployee(final String name, final String lastName, final String id) throws IOException {
		Employee newEmployee = new Employee(null, name, lastName, id);
		employees.add(newEmployee);
		saveEmployeesData();
	}//End addEmployee

	public void addEmployee(final String name, final String lastName, final String id) throws IOException{
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
		saveEmployeesData();
	}//End addEmployee

	public void changeEmployee(Employee employee, final String name, final String lastName, final String id) throws IOException{
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
			saveUsersData();
		}//End if
		saveEmployeesData();
	}//End changeEmployee

	public void disableEmployee(final String employeeId) throws IOException {
		Employee employee = employees.get(searchEmployeePosition(employeeId));
		employee.setEnabled(false);
		employee.setModifier(loggedUser);
		saveEmployeesData();
	}//End disableEmployee

	public void removeEmployee(final String employeeId) throws IOException {
		int index = searchEmployeePosition(employeeId);
		employees.remove(index);
		saveEmployeesData();
	}//End removeEmployee

	public void saveUsersData() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_SAVEFILE_PATH));
		oos.writeObject(users);
		oos.close();
	}//End saveUsersData

	@SuppressWarnings("unchecked")
	public void loadUsersData() throws IOException, ClassNotFoundException {
		File f = new File(USERS_SAVEFILE_PATH);
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			users = (ArrayList<User>)ois.readObject();
			ois.close();
		}//End if
	}//End loadUsersData

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
	}//End searchUserPositon

	public void addFirstUser(final String idEmployee, final String userName, final String password) throws IOException {
		Employee employee = employees.get(searchEmployeePosition(idEmployee));
		employee.setLinked(true);
		String name = employee.getName();
		String lastName = employee.getLastName();
		String id = employee.getId();
		User newUser = new User(null, name, lastName, id, userName, password);
		users.add(newUser);
		saveUsersData();
	}//End addUser

	public void addUser(final String idEmployee, final String userName, final String password) throws IOException {
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
		saveUsersData();
	}//End addUser

	public void changeUser(User user, final String userName, final String password) throws IOException {
		user.setUserName(userName);
		user.setPassword(password);
		user.setModifier(loggedUser);
		saveUsersData();
	}//End changeUser

	public void disableUser(final String userId) throws IOException {
		User user = users.get(searchUserPosition(userId));
		user.setEnabled(false);
		user.setModifier(loggedUser);
		saveUsersData();
	}//End disableUser

	public void removeUser(final String userId) throws IOException {
		int index = searchUserPosition(userId);
		users.remove(index);
		saveUsersData();
	}//End removeUser

	public void saveCustomersData() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CUSTOMERS_SAVEFILE_PATH));
		oos.writeObject(customers);
		oos.close();
	}//End saveCustomersData

	@SuppressWarnings("unchecked")
	public void loadCustomersData() throws IOException, ClassNotFoundException {
		File f = new File(CUSTOMERS_SAVEFILE_PATH);
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			customers = (ArrayList<Customer>) ois.readObject();
			ois.close();
		}//End if
	}//End loadCustomersData

	public int searchCustomerPosition(final String idToSearch) {
		for(int i = 0; i < customers.size(); i ++) {
			Customer customer = customers.get(i);
			if(customer.getId().equals(idToSearch)) {
				return i;
			}//End if
		}//End for
		return -1;
	}//End searchCustomerPositionById

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
		}
		saveCustomersData();
	}//End addCustomer

	public void changeCustomer(Customer customer, String name, String lastName, String id, String address, String nPhone, String remark) throws IOException {
		customer.setName(name);
		customer.setLastName(lastName);
		customer.setId(id);
		customer.setAddress(address);
		customer.setNPhone(nPhone);
		customer.setRemark(remark);
		customer.setModifier(getLoggedUser());
		Comparator<Customer> lastNameAndNameComparator = new LastNameAndNameComparator();
		Collections.sort(customers, lastNameAndNameComparator);
		saveCustomersData();
	}//End changeCustomer

	public void disableCustomer(final String customerId) throws IOException {
		Customer customer = customers.get(searchCustomerPosition(customerId));
		customer.setEnabled(false);
		customer.setModifier(loggedUser);
		saveCustomersData();
	}//End disableCustomerById

	public void removeCustomer(final String customerId) throws IOException {
		customers.remove(searchCustomerPosition(customerId));
		saveCustomersData();
	}//End removeCustomerById

	public void saveProductsData() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PRODUCTS_SAVEFILE_PATH));
		oos.writeObject(products);
		oos.close();
	}//End saveProductsData

	@SuppressWarnings("unchecked")
	public void loadProductsData() throws IOException, ClassNotFoundException {
		File f = new File(PRODUCTS_SAVEFILE_PATH);
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			products = (ArrayList<Product>) ois.readObject();
			ois.close();
		}//End if
	}//End loadProductsData

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

	public void addProduct(final String name,final List<Double> price,final List<String> size,final String type) throws IOException {
		if(findProduct(name) < 0){
			int dtIndex = findDishType(type);
			DishType t;
			if(dtIndex < 0){
				t = new DishType(getLoggedUser(),type);
				addDishType(t);
			}else{t = types.get(dtIndex);}//End else
			if(products.isEmpty()){
				products.add(new Product(getLoggedUser(),name,price,size,t));
			}else{
				Product pd = new Product(getLoggedUser(),name,price,size,t);
				int j = 0;
				while(products.get(j).compareTo(pd) < 0){j++;}//End while
				products.add(j,pd);
			}//End else
		}//End if
		saveProductsData();
	}//End addProduct

	public void changeProduct(Product product,final String newName,final List<String> Newingredients,final List<Double> prices,final List<String> sizes,final String typeName) throws IOException {
		int productIndex = findProduct(newName);
		int dishIndex = findDishType(typeName);
		if(productIndex >= 0)
			products.get(productIndex).setName(newName);
		products.get(productIndex).setPrice(prices);
		products.get(productIndex).setSize(sizes);
		changeDishType(dishIndex,productIndex,typeName);
		changeIngredient(Newingredients,productIndex);
		products.get(productIndex).setModifier(loggedUser);
		Collections.sort(this.products);
		saveProductsData();
	}//End changeProduct

	public void disableProduct(String productName) throws IOException {
		int productIndex = findProduct(productName);
		if(productIndex >= 0){
			products.get(productIndex).setEnable(false);
			products.get(productIndex).setModifier(loggedUser);
		}//End if
		saveProductsData();
	}//End disableProduct

	public boolean removeProduct(final String productName) throws IOException {
		boolean removed = false;
		int productIndex = findProduct(productName);
		if(productIndex >= 0)
			if(!products.get(productIndex).getLinked()){
				products.remove(productIndex);
				removed = true;
			}//End if
		saveProductsData();
		return removed;
	}//removeProduct

	public void saveIngredientsData() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INGREDIENTS_SAVEFILE_PATH));
		oos.writeObject(ingredients);
		oos.close();
	}//End saveIngredientsData

	@SuppressWarnings("unchecked")
	public void loadIngredientsData() throws IOException, ClassNotFoundException {
		File f = new File(INGREDIENTS_SAVEFILE_PATH);
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			ingredients = (ArrayList<Ingredient>)ois.readObject();
			ois.close();
		}//End if
	}//End loadIngredientsData

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

	public boolean addIngredient(final String ingredient) throws IOException {
		boolean added = false;
		if(findIngredient(ingredient) < 0){
			if(ingredients.isEmpty()){
				ingredients.add(new Ingredient(getLoggedUser(),ingredient));
			}else {
				Ingredient in = new Ingredient(getLoggedUser(),ingredient);
				int j = 0;
				while(ingredients.get(j).compareTo(in) < 0){j++;}//End while
				ingredients.add(j,in);
			}//End else
			added = true;
		}//End if
		saveIngredientsData();
		return added;
	}//End addIngredient

	private void addIngredient(Ingredient ingredient) throws IOException {
		int j = 0;
		while(ingredients.get(j).compareTo(ingredient) < 0){j++;}//End while
		ingredients.add(j,ingredient);
		saveIngredientsData();
	}//End addIngredient

	public String changeIngredient(Ingredient ingredient,final String newName) throws IOException {
		String msg = "Tal parece que ya existe un ingrediente con ese nombre.";
		if(findIngredient(newName) < 0){
			ingredient.setName(newName);
			ingredient.setModifier(loggedUser);
			Collections.sort(ingredients);
			msg = "Ingrediente modificado";
		}//End if
		saveIngredientsData();
		return msg;
	}//End changeIngredient

	private void changeIngredient(final List<String> Newingredients,final int productIndex) throws IOException {
		List<Ingredient> newIngredients = new ArrayList<>();
		for(int i = 0; i < Newingredients.size(); i++){
			int ingredientIndex = findIngredient(Newingredients.get(i));
			if(ingredientIndex < 0){
				Ingredient ingredient = new Ingredient(getLoggedUser(),Newingredients.get(i));
				addIngredient(ingredient);
				newIngredients.add(ingredient);
			}else
				newIngredients.add(ingredients.get(ingredientIndex));
		}//End for
		products.get(productIndex).setIngredient(newIngredients);
		products.get(productIndex).setModifier(loggedUser);
		saveIngredientsData();
	}//End changeIngredient

	public void disableIngredient(Ingredient ingredient) throws IOException {
		ingredient.setEnable(false);
		ingredient.setModifier(loggedUser);
		saveIngredientsData();
	}//End disableIngredient

	public boolean removeIngredient(final String name) throws IOException {
		int index = findIngredient(name);
		boolean removed = false;
		if(index >= 0)
			if(!ingredients.get(index).getLinked()){
				ingredients.remove(index);
				removed = true;
			}//End if
		saveIngredientsData();
		return removed;
	}//End removeIngredient

	public void saveTypesData() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TYPES_SAVEFILE_PATH));
		oos.writeObject(types);
		oos.close();
	}//End saveTypesData

	@SuppressWarnings("unchecked")
	public void loadTypesData() throws IOException, ClassNotFoundException {
		File f = new File(TYPES_SAVEFILE_PATH);
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			types = (ArrayList<DishType>) ois.readObject();
			ois.close();
		}//End if
	}//End loadTypesData

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

	public boolean addDishType(final String dishType) throws IOException {
		boolean added = false;
		if(findDishType(dishType) < 0){
			if(types.isEmpty()){
				types.add(new DishType(getLoggedUser(),dishType));
			}else {
				DishType dt = new DishType(getLoggedUser(),dishType);
				int j = 0;
				while(types.get(j).compareTo(dt) < 0){j++;}//End while
				types.add(dt);
			}//End else
			added = true;
		}//end if
		saveTypesData();
		return added;
	}//End addProduct

	private void addDishType(final DishType dishType) throws IOException {
		int j = 0;
		while(types.get(j).compareTo(dishType) < 0){j++;}//End while
		types.add(dishType);
		saveTypesData();
	}//End addProduct

	public void changeDishType(DishType dType,final String newName) throws IOException {
		if(findDishType(newName) < 0){
			dType.setName(newName);
			dType.setModifier(loggedUser);
			Collections.sort(types);
		}//End if
		saveTypesData();
	}//End changeDishType

	private void changeDishType(final int dishIndex,final int productIndex,final String typeName) throws IOException {
		if(dishIndex < 0){
			DishType dish = new DishType(getLoggedUser(),typeName);
			addDishType(dish);
			products.get(productIndex).setType(dish);
		}else{
			products.get(productIndex).setType(types.get(dishIndex));
		}//End else
		saveTypesData();
	}//End changeDishType

	public void disableDishType(DishType dType) throws IOException {
		dType.setEnable(false);
		dType.setModifier(loggedUser);
		saveTypesData();
	}//End disableIngredient

	public boolean removeDishType(final String name) throws IOException {
		int index = findDishType(name);
		boolean removed = false;
		if(index >= 0)
			if(!types.get(index).getLinked()){
				types.remove(index);
				removed = true;
			}//End if
		saveTypesData();
		return removed;
	}//End removeDishType

	public void saveOrdersData() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDERS_SAVEFILE_PATH));
		oos.writeObject(orders);
		oos.close();
	}//End saveOrdersData

	@SuppressWarnings("unchecked")
	public void loadOrdersData() throws IOException, ClassNotFoundException {
		File f = new File(ORDERS_SAVEFILE_PATH);
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			orders = (ArrayList<Order>)ois.readObject();
			ois.close();
		}//End if
	}//End loadOrdersData

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
	
	public void addOrder(final List<String> nProducts, List<Integer> amount, String remark, String status, String idCustomer, String idEmployee) throws IOException {
		int customerIndex = searchCustomerPosition(idCustomer);
		int employeeIndex = searchEmployeePosition(idEmployee);
		List<Product> ps = new ArrayList<Product>();
		for(int i = 0; i < nProducts.size(); i++){
			ps.add(this.products.get(findProduct(nProducts.get(i))));
		}//End for
		orders.add(new Order(ps,amount,remark,status,customers.get(customerIndex),employees.get(employeeIndex),getLoggedUser()));
		saveOrdersData();
	}//End addOrder

	public void changeOrder(Order order, List<String> nProducts, List<Integer> amount, String remark, String status, String idCustomer, String idEmployee) throws IOException {
		int customerIndex = searchCustomerPosition(idCustomer);
		int employeeIndex = searchEmployeePosition(idEmployee);
		List<Product> ps = new ArrayList<Product>();
		for(int i = 0; i < nProducts.size(); i++){
			ps.add(this.products.get(findProduct(nProducts.get(i))));
		}//End for
		order.setProduct(ps);
		order.setAmount(amount);
		order.setRemark(remark);
		order.setStatus(status);
		order.setCustomer(customers.get(customerIndex));
		order.setEmployee(employees.get(employeeIndex));
		order.setModifier(getLoggedUser());
		saveOrdersData();
	}//End changeOrder

	public void disableOrder(Order order) throws IOException {
		order.setEnable(false);
		saveOrdersData();
	}//End disableOrder

	public void removeOrder(Order order) throws IOException {
		orders.remove(order);
		saveOrdersData();
	}//End removeOrder

}//End DeliveryManagerController