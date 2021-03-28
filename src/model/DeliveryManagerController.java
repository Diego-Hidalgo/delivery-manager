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
	private List<ProductBase> productBase;
	private List<Product> products;
	private List<ProductSize> sizes;
	private List<DishType> types;
	private List<Ingredient> ingredients;
	private List<Order> orders;

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

	public void addFirstEmployee(String name, String lastName, String id) {
		Employee newEmployee = new Employee(null, name, lastName, id);
		employees.add(newEmployee);
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

	public void changeEmployee(Employee employee, final String name, final String lastName, final String id) {
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
		}//End if
	}//End changeEmployee

	public void disableEmployee(String employeeId) {
		Employee employee = employees.get(searchEmployeePosition(employeeId));
		employee.setEnabled(false);
		employee.setModifier(loggedUser);
	}//End disableEmployee

	public void removeEmployee(String employeeId) {
		int index = searchEmployeePosition(employeeId);
		employees.remove(index);
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

	public void addFirstUser(String idEmployee, String userName, String password) {
		Employee employee = employees.get(searchEmployeePosition(idEmployee));
		employee.setLinked(true);
		String name = employee.getName();
		String lastName = employee.getLastName();
		String id = employee.getId();
		User newUser = new User(null, name, lastName, id, userName, password);
		users.add(newUser);
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

	public void changeUser(User user, final String userName, final String password) {
		user.setUserName(userName);
		user.setPassword(password);
		user.setModifier(loggedUser);
	}//End changeUser

	public void disableUser(String userId) {
		User user = users.get(searchUserPosition(userId));
		user.setEnabled(false);
		user.setModifier(loggedUser);
	}//End disableUser

	public void removeUser(String userId) {
		int index = searchUserPosition(userId);
		users.remove(index);
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

	public void addCustomer(String name, String lastName, String id, String address, String nPhone, String remark){
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

	public void changeCustomer(Customer customer, String name, String lastName, String id, String address, String nPhone, String remark) {
		customer.setName(name);
		customer.setLastName(lastName);
		customer.setId(id);
		customer.setAddress(address);
		customer.setNPhone(nPhone);
		customer.setRemark(remark);
		customer.setModifier(getLoggedUser());
		Comparator<Customer> lastNameAndNameComparator = new LastNameAndNameComparator();
		Collections.sort(customers, lastNameAndNameComparator);
	}//End changeCustomer

	public void disableCustomer(String customerId) {
		Customer customer = customers.get(searchCustomerPosition(customerId));
		customer.setEnabled(false);
		customer.setModifier(loggedUser);
	}//End disableCustomerById

	public void removeCustomer(String customerId) {
		customers.remove(searchCustomerPosition(customerId));
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

	public void addProduct(final String name,List<String> ingredients,final List<Double> price,final List<String> size,final String type){
		if(findProductBase(name) < 0){
			DishType dishType = dishTypeToAdd(type);
			List<Ingredient> ingd = ingredientsToAdd(ingredients);
			ProductBase pd = new ProductBase(getLoggedUser(),name,dishType,ingd);
			productBase.add(pd);
			createSubproduct(pd,price,size);
		}//End if
	}//End addProduct
	private void createSubproduct(ProductBase pd,List<Double> price,final List<String> size){
		for(int i = 0; i < price.size();i++){
			int sizeIndex = findProductSize(size.get(i));
			ProductSize s;
			if(sizeIndex < 0){
				s = new ProductSize(size.get(i));
				this.sizes.add(s);
			}else
				s = sizes.get(sizeIndex);
			products.add(new Product(pd,s,price.get(i)));
		}//End for
	}//End createSubproduct
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
	private List<Ingredient> ingredientsToAdd(List<String> ingredients){
		List<Ingredient> ingd = new ArrayList<Ingredient>();
		for(int i = 0; i < ingredients.size();i++){
			int ingdIndex = findIngredient(ingredients.get(i));
			if(ingdIndex < 0){
				Ingredient in = new Ingredient(getLoggedUser(),ingredients.get(i));
				addIngredient(in);
				ingd.add(in);
			}else
				ingd.add(this.ingredients.get(ingdIndex));
		}//End for
		return ingd;
	}//End ingredientsToAdd
	private DishType dishTypeToAdd(String dish){
		int dtIndex = findDishType(dish);
		DishType t;
		if(dtIndex < 0){
			t = new DishType(getLoggedUser(),dish);
			addDishType(t);
		}else
			t = types.get(dtIndex);
		return t;
	}//End dishTypeToAdd
	public void changeProduct(Product product,final String newName,final List<String> Newingredients,final double prices,final String sizes,final String typeName){
		int productIndex = findProductBase(newName);
		String n = (productIndex >= 0)?newName: (product.getProductBase()).getName();
		product.changesProductBase(n, ingredientsToAdd(Newingredients), dishTypeToAdd(typeName));
		products.get(productIndex).setPrice(prices);
		products.get(productIndex).setSize(sizes);
		products.get(productIndex).setModifier(loggedUser);
	}//End changeProduct

	public void disableProduct(String productName){
		int productIndex = findProductBase(productName);
		if(productIndex >= 0){
			productBase.get(productIndex).setEnable(false);
			productBase.get(productIndex).setModifier(loggedUser);
		}//End if
	}//End disableProduct

	public boolean removeProduct(final String productName){
		boolean removed = false;
		int productIndex = findProductBase(productName);
		boolean linked = false;
		if(productIndex >= 0){
			List<Integer> index = findSubProducts(productName);
			for(int i = 0; i < index.size() && !linked;i++){
				linked = linked || products.get(index.get(i)).getEnable();
			}//End for
			if(!linked){
				for(int i = 0; i < index.size() && !linked;i++)
					products.remove( (int) index.get(i));
				productBase.remove(productIndex);
				removed = true;
			}//End if
		}//End if
		return removed;
	}//removeProduct
	public List<Integer> findSubProducts(String name){
		List<Integer> index = new ArrayList<Integer>();
		for(int i = 0; i < products.size();i++){
			if(name.equals((products.get(i).getProductBase()).getName())){
				index.add((Integer) i);
			}//End if
		}//End for
		return index;
	}//End findSubProducts
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

	public boolean addIngredient(final String ingredient){
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
		return added;
	}//End addIngredient

	private void addIngredient(Ingredient ingredient){
		int j = 0;
		while(ingredients.get(j).compareTo(ingredient) < 0){j++;}//End while
		ingredients.add(j,ingredient);
	}//End addIngredient

	public String changeIngredient(Ingredient ingredient,final String newName){
		String msg = "Tal parece que ya existe un ingrediente con ese nombre.";
		if(findIngredient(newName) < 0){
			ingredient.setName(newName);
			ingredient.setModifier(loggedUser);
			Collections.sort(ingredients);
			msg = "Ingrediente modificado";
		}//End if
		return msg;
	}//End changeIngredient
	public void disableIngredient(Ingredient ingredient){
		ingredient.setEnable(false);
		ingredient.setModifier(loggedUser);
	}//End disableIngredient

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

	public boolean addDishType(final String dishType){
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
		return added;
	}//End addProduct

	private void addDishType(final DishType dishType){
		int j = 0;
		while(types.get(j).compareTo(dishType) < 0){j++;}//End while
		types.add(dishType);
	}//End addProduct

	public void changeDishType(DishType dType,final String newName){
		if(findDishType(newName) < 0){
			dType.setName(newName);
			dType.setModifier(loggedUser);
			Collections.sort(types);
		}//End if
	}//End changeDishType
	public void disableDishType(DishType dType){
		dType.setEnable(false);
		dType.setModifier(loggedUser);
	}//End disableIngredient

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

	public void addOrder(List<Product> nProducts,List<Integer> amount,String remark,String status,String idCustomer,String idEmployee){
		int customerIndex = searchCustomerPosition(idCustomer);
		int employeeIndex = searchEmployeePosition(idEmployee);
		orders.add(new Order(nProducts,amount,remark,status,customers.get(customerIndex),employees.get(employeeIndex),getLoggedUser()));
	}//End addOrder

	public void changeOrder(Order order,List<Product> nProducts,List<Integer> amount,String remark,String status,String idCustomer,String idEmployee){
		int customerIndex = searchCustomerPosition(idCustomer);
		int employeeIndex = searchEmployeePosition(idEmployee);
		order.setProduct(nProducts);
		order.setAmount(amount);
		order.setRemark(remark);
		order.setStatus(status);
		order.setCustomer(customers.get(customerIndex));
		order.setEmployee(employees.get(employeeIndex));
		order.setModifier(getLoggedUser());
	}//End changeOrder

	public void disableOrder(Order order){
		order.setEnable(false);
	}//End disableOrder

	public void removeOrder(Order order){
		orders.remove(order);
	}//End removeOrder
	public void exportOrderData(File ordersData, String separator) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(ordersData);
		pw.close();
	}//End exportOrderData

}//End DeliveryManagerController