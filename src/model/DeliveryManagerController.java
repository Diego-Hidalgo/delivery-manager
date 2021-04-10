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
	
	public List<Employee> getEmployees() {
		return employees;
	}//End getEmployee

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

	public int getAmountUsers() {
		return users.size();
	}//End getAmountUsers

	public int getAmountEmployees() {
		return employees.size();
	}

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

	public User getLoggedUser(){
		return loggedUser;
	}//End getLoggedUser

	public void logOutUser() {
		this.loggedUser = null;
	}//End logOutUser

	public int countEnabledUsers() {
		int enabled = 0;
		for (User user : users) {
			if (user.getEnabled()) {
				enabled++;
			}//End if
		}//End countEnabledUsers
		return enabled;
	}//End countEnabledUsers

	public boolean validateBlankChars(String fieldToValidate) {
		boolean validate = false;
		for(int i = 0; i < fieldToValidate.length() && !validate; i ++) {
			if(fieldToValidate.charAt(i) != ' ') {
				validate = true;
			}//End if
		}//End for
		return validate;
	}//End validateBlankChats

	public void saveAllData() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH));
		oos.writeObject(this);
		oos.close();
	}//End saveAllData

	public void exportEmployeesData(File employeesData, String separator, Date initialDate, Date finalDate) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(employeesData);
		List<Order> ords = getOrdersInRange(initialDate, finalDate);
		String info = "Reporte creado con los datos recolectados desde " + initialDate + " hasta " + finalDate + "\n";
		pw.write(info);
		String columns = "ID" + separator + "Nombre" + separator + "Apellido" + separator +
				"Pedidos entregados" + separator + "Precio total" + "\n";
		pw.write(columns);
		int totalLines = 0;
		for(int i = 0; i < employees.size(); i ++) {
			int amount = 0;
			int totalPrice = 0;
			for(int j = 0; j < ords.size(); i ++) {
				Employee employee = employees.get(i);
				Order order = ords.get(j);
				if(order.getStatus().equals("ENTREGADO")) {
					if(order.getEmployee().getId().equals(employee.getId())) {
						amount ++;
						totalPrice += order.calculateTotalPrice();
					}//End if
					String toWrite = employee.getId() + separator + employee.getName() + separator +
							employee.getLastName() + separator + amount + separator + totalPrice + "\n";
					pw.write(toWrite);
					totalLines ++;
				}
			}//End for
		}//End for
		String lines = "Lineas totales: " + totalLines;
		pw.write(lines);
		pw.close();
	}//End exportEmployeesData

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

	public boolean getEmployeeEnabledStatus(final String idToSearch) {
		return employees.get(searchEmployeePosition(idToSearch)).getEnabled();
	}//End getEmployeeEnabledStatus

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
			loggedUser.setLinked(true);
		}//End else
		saveAllData();
	}//End addEmployee

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

	public boolean removeEmployee(Employee employee) throws IOException {
		if(!employee.getLinked()) {
			employees.remove(employee);
			saveAllData();
			return true;
		} else {
			return false;
		}//End else
	}//End removeEmployee

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

	public boolean getUserEnabledStatus(final String nameToSearch) {
		return users.get(searchUserPositionByName(nameToSearch)).getEnabled();
	}//End getUserEnabledStatus


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

	public void changeUser(User user, final String userName, final String password) throws IOException {
		user.setUserName(userName);
		user.setPassword(password);
		user.setModifier(loggedUser);
		loggedUser.setLinked(true);
		saveAllData();
	}//End changeUser

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

	public boolean removeUser(User user) throws IOException {
		if(!user.getLinked()) {
			users.remove(user);
			saveAllData();
			return true;
		} else {
			return false;
		}//End else
	}//End removeUser

	public int searchCustomerPosition(final String idToSearch) {
		for(int i = 0; i < customers.size(); i ++) {
			Customer customer = customers.get(i);
			if(customer.getId().equals(idToSearch)) {
				return i;
			}//End if
		}//End for
		return -1;
	}//End searchCustomerPositionById

	public boolean getCustomerEnabledStatus(final String idToSearch) {
		return customers.get(searchCustomerPosition(idToSearch)).getEnabled();
	}//End getCustomerEnabledStatus

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

	public boolean removeCustomer(Customer customer) throws IOException {
		if(!customer.getLinked()) {
			customers.remove(customer);
			saveAllData();
			return true;
		} else {
			return false;
		}//End else
	}//End removeCustomerById

	public void exportProductsData(File productsData, String separator,Date initialDate,Date finishDate) throws FileNotFoundException{
		int totalLines = 0;
		PrintWriter pw = new PrintWriter(productsData);
		List<Order> ords = getOrdersInRange(initialDate, finishDate);
		String columns = "Nombre" + separator + "Tamaño" + separator + "Precio" +
				         separator + "Cantidad de veces pedido" + separator + "Total" + "\n";
		String info = "Reporte creado con los datos recolectados desde " + initialDate + " hasta " + finishDate + "\n";
		pw.write(info);
		pw.write(columns);
		for(int i = 0; i < products.size(); i ++) {
			int times = 0;
			for(int j = 0; j < ords.size(); j ++) {
				Product product = products.get(i);
				Order order = ords.get(j);
				if(order.getStatus().equals("ENTREGADO")) {
					if(order.findProductInOrder(product.getProductBase().getName())) {
						times ++;
					}//End if
				}//End if
				String toWrite = product.getProductBase().getName() + separator + product.getSize() +
						         separator + product.getPrice() + separator + times + separator +
						         (times * product.getPrice()) + "\n";
				pw.write(toWrite);
				totalLines ++;
			}//End for
		}//End for
		String lines = "Lineas totales: " + totalLines;
		pw.write(lines);
		pw.close();
	}//End exportProductsData

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

	public boolean addProduct(final String name,List<String> ingredients,final List<Double> price,final List<String> size,final String type)throws IOException{
		boolean added = false;
		if(findProductBase(name) < 0){
			DishType dishType = dishTypeToAdd(type);
			productBase.add(new ProductBase(getLoggedUser(),name,dishType,ingredientsToAdd(ingredients)));
			createSubproduct(productBase.get(productBase.size() - 1),price,size);
			added = true;
			saveAllData();
		}//End if
		return added;
	}//End addProduct

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
			}//End if
			line = br.readLine();
		}//End while
		br.close();
		return all;
	}//End importProducts

	public List<Double> stringListToDouble(List<String> stringList) {
		List<Double> doubleList = new ArrayList<Double>();
		for(int i = 0; i < stringList.size(); i ++) {
			doubleList.add(Double.parseDouble(stringList.get(i)));
		}//End for
		return doubleList;
	}//End stringListToDouble

	private void createSubproduct(ProductBase pd,List<Double> price,final List<String> size) throws IOException {
		for(int i = 0; i < price.size();i++){
			int sizeIndex = findProductSize(size.get(i));
			ProductSize s;
			if(sizeIndex < 0){
				s = new ProductSize(size.get(i));
				this.sizes.add(s);
			}else
				s = sizes.get(sizeIndex);
			Product p = new Product(pd,s,price.get(i));
			products.add(p);
		}//End for
		saveAllData();
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

	private List<Ingredient> ingredientsToAdd(List<String> ingredients) throws IOException {
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
		saveAllData();
		return ingd;
	}//End ingredientsToAdd

	private DishType dishTypeToAdd(String dish) throws IOException {
		int dtIndex = findDishType(dish);
		DishType t;
		if(dtIndex < 0){
			t = new DishType(getLoggedUser(),dish);
			addDishType(t);
		}else
			t = types.get(dtIndex);
		saveAllData();
		return t;
	}//End dishTypeToAdd

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
			product.changesProductBase(n, ingredientsToAdd(Newingredients), dishTypeToAdd(typeName));
			product.setPrice(prices);
			product.setSize(sizes);
			product.setModifier(loggedUser);
			saveAllData();
		}//End if
		return changed;
	}//End changeProduct


	public void changeEnableProduct(Product product) throws IOException {
		product.setEnable(!product.getEnable());
		saveAllData();
	}//End disableProduct

	public boolean removeProduct(Product product) throws IOException {
		boolean removed = false;
		if(!product.getLinked()){
			products.remove(product);
			removed = true;
			saveAllData();
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

	public List<Ingredient> getIngredients(boolean enable){
		List<Ingredient> enableIngredients = new ArrayList<Ingredient>();
		for(int i = 0; i < ingredients.size();i++){
			if(ingredients.get(i).getEnable() == enable)
				enableIngredients.add(ingredients.get(i));
		}//End for
		return enableIngredients;
	}//End getIngredients

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
			added = true;
		}//End if
		saveAllData();
		return added;
	}//End addIngredient

	private void addIngredient(Ingredient ingredient)throws IOException{
		int j;
		for(j = 0; j < ingredients.size() && ingredients.get(j).compareTo(ingredient) < 0;j++);
			ingredients.add(j,ingredient);
		saveAllData();
	}//End addIngredient

	public boolean changeIngredient(Ingredient ingredient,final String newName) throws IOException {
		boolean changed = false;
		if(findIngredient(newName) < 0){
			ingredient.setName(newName);
			ingredient.setModifier(loggedUser);
			Collections.sort(ingredients);
			changed = true;
			saveAllData();
		}//End if
		return changed;
	}//End changeIngredient

	public void changeEnableIngredient(Ingredient ingredient) throws IOException {
		ingredient.setEnable(!ingredient.getEnable());
		ingredient.setModifier(loggedUser);
		saveAllData();
	}//End changeEnableIngredient

	public boolean removeIngredient(Ingredient ingredient) throws IOException {
		boolean removed = false;
		if(!ingredient.getLinked()){
			ingredients.remove(ingredient);
			removed = true;
			saveAllData();
		}//End if
		return removed;
	}//End removeIngredient

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
	
	public List<DishType> getDishtype(boolean enable){
		List<DishType> enableDishType = new ArrayList<DishType>();
		for(int i = 0; i < types.size();i++ ){
			if(types.get(i).getEnable() == enable){
				enableDishType.add(types.get(i));
			}//End if
		}//End for
		return enableDishType;
	}//End getDishType

	private void addDishType(final DishType dishType)throws IOException{
		int j;
		for(j = 0; j < types.size() && types.get(j).compareTo(dishType) < 0;j++);
		types.add(j,dishType);
		saveAllData();
	}//End addDishType

	public boolean changeDishType(DishType dType,final String newName) throws IOException {
		boolean changed = false;
		if(findDishType(newName) < 0){
			dType.setName(newName);
			dType.setModifier(loggedUser);
			Collections.sort(types);
			changed = true;
			saveAllData();
		}//End if
		return changed;
	}//End changeDishType

	public void changeEnableDishType(DishType dType) throws IOException {
		dType.setEnable(!dType.getEnable());
		dType.setModifier(loggedUser);
		saveAllData();
	}//End changeEnableDishType

	public boolean removeDishType(DishType dType) throws IOException {
		boolean removed = false;
		if(!dType.getLinked()){
			types.remove(dType);
			removed = true;
			saveAllData();
		}//End if
		return removed;
	}//End removeDishType

	public void exportOrdersData(File ordersData, String separator,Date initialDate,Date finishDate) throws FileNotFoundException {
		int totalLines = 0;
		PrintWriter pw = new PrintWriter(ordersData);
		List<Order> ords = getOrdersInRange(initialDate,finishDate);
		String info = "Reporte creado con los datos recolectados desde: " + initialDate + " hasta " + finishDate + "\n";
		pw.write(info);
		String columns = "Nombre del cliente" + separator + "Apellido del cliente" + separator + "Dirección del cliente" +
						  separator + "Telefono del cliente" + separator + "Nombre del empleado" + separator + "Apellido del empleado" +
					      separator + "Fecha" + separator + "Hora" + separator + "Observaciones" + separator + "Nombre del producto" +
						  separator + "Cantidad" + separator +"Precio" + "\n";
		pw.write(columns);
		String report = new String();
		for(int i = 0; i < ords.size();i++){
			List<Product> pds = ords.get(i).getProducts();
			List<Integer> amo = ords.get(i).getAmount();
			report += ords.get(i).getCustomer().getName() + " " + ords.get(i).getCustomer().getLastName() + separator
					+ ords.get(i).getCustomer().getAddress() + separator + ords.get(i).getCustomer().getNPhone() + separator
					+ ords.get(i).getEmployee().getName() + " " + ords.get(i).getEmployee().getLastName() + separator
					+ ords.get(i).getDate() + separator + ords.get(i).getHour() + separator + ords.get(i).getRemark() + separator;
			for(int j = 0; j < ords.size();i++){
				report += pds.get(j).getProductBase().getName() + separator + amo.get(j) + separator + pds.get(j).getPrice();
			}//End for
			report += "\n";
			totalLines ++;
		}//End for
		pw.print(report);
		String lines = "lineas totales: " + totalLines;
		pw.write(lines);
		pw.close();
	}//End exportOrderData

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

	public boolean addOrder(List<Product> nProducts,List<Integer> amount,String remark,String status,String idCustomer,String idEmployee) throws IOException {
		int customerIndex = searchCustomerPosition(idCustomer);
		int employeeIndex = searchEmployeePosition(idEmployee);
		boolean added = false;
		if(customerIndex >= 0 && employeeIndex >= 0){
			orders.add(new Order(nProducts,amount,remark,status,customers.get(customerIndex),employees.get(employeeIndex),getLoggedUser()));
			added = true;
			saveAllData();
		}//End if
		return added;
	}//End addOrder

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
			boolean aux = addOrder(products, amount, remark, status, idCustomer, idEmployee);
			if(!aux) {
				all = false;
			}//End if
			line = br.readLine();
		}//End while
		br.close();
		return all;
	}//End importOrders

	public List<Integer> stringListToInteger(List<String> stringList) {
		List<Integer> integerList = new ArrayList<>();
		for(int i = 0; i < stringList.size(); i ++) {
			integerList.add(Integer.parseInt(stringList.get(i)));
		}
		return integerList;
	}//End stringListTointeger

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

	public Product findProductByNameAndSize(String name, String size) {
		for(int i = 0; i < products.size(); i ++) {
			Product product = products.get(i);
			if(product.getName().equals(name) && product.getSize().equals(size)) {
				return product;
			}
		}
		return null;
	}//End findProductByNameAndSize

	public void changeOrder(Order order,List<Product> nProducts,List<Integer> amount,String remark,String status,String idCustomer,String idEmployee) throws IOException {
		int customerIndex = searchCustomerPosition(idCustomer);
		int employeeIndex = searchEmployeePosition(idEmployee);
		updateProductsNtr(nProducts);
		order.setProduct(nProducts);
		order.setAmount(amount);
		order.setRemark(remark);
		order.setStatus(status);
		order.setCustomer(customers.get(customerIndex));
		order.setEmployee(employees.get(employeeIndex));
		order.setModifier(getLoggedUser());
		saveAllData();
	}//End changeOrder

	private void updateProductsNtr(List<Product> p) throws IOException {
		for(int i = 0; i < p.size(); i++){
			p.get(i).updateNtr();
		}//End for
		saveAllData();
	}//End updateProductsNtr

	public void disableOrder(Order order) throws IOException {
		order.setEnable(false);
		saveAllData();
	}//End disableOrder

	public List<Order> getOrders(){
		return orders;
	}//End getOrders

	public void removeOrder(Order order) throws IOException {
		orders.remove(order);
		saveAllData();
	}//End removeOrder

	public List<Product> getProducts(boolean enable){
		List<Product> enableProducts = new ArrayList<Product>();
		for(int i = 0; i < products.size();i++){
			if(products.get(i).getEnable() == enable)
				enableProducts.add(products.get(i));
		}//End for
		return enableProducts;
	}//End getProducts
	public List<Product> getDisableProducts(){
		List<Product> disableProducts = new ArrayList<Product>();
		for(int i = 0; i < products.size();i++){
			if(!products.get(i).getEnable())
				disableProducts.add(products.get(i));
		}//End for
		return disableProducts;
	}//End getProducts
	public List<ProductSize> getSizes(){
		return sizes;
	}//End getSizes
}//End DeliveryManagerController