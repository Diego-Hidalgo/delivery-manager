package model;
import java.util.List;
import java.util.ArrayList;

public class DeliveryManagerController {
	private List<Order> order;
	private List<User> user;
	private List<Product> product;
	private List<Employee> employee;
	
	public DeliveryManagerController(){
		order = new ArrayList<Order>();
		user = new ArrayList<User>();
		product = new ArrayList<Product>();
		employee = new ArrayList<Employee>();
	}//End DeliveryManagerController
	
	public void addProduct(String name, List<Double> price, List<String> size,String type){
		if(findProduct(name) < 0){
			if(product.isEmpty()){
				product.add(new Product(name,price,size,type));
			}else{
				double tp = 0;
				for(int i = 0; i < price.size(); i++){tp += price.get(i);}//End for
				Product pd = new Product(name,price,size,type);
				int j = 0;
				while(product.get(j).compareTo(tp) < 0){j++;}//End while
				product.add(j,pd);
			}//End else
		}//End if
	}//End addProduct
	private int findProduct(String name){
		boolean found = false;
		int index = -1;
		for(int i = 0; i < product.size() && !found; i++) {
			if(product.get(i).getName().equalsIgnoreCase(name)) {
				index = i;
				found = true;
			}//End if
		}//End for
		return index;
	}//End findProduct
	public void changeProduct(String oldName,String newName,List<Double> prices, List<String> sizes,String typeName){
		int index = findProduct(oldName);
		if(index >= 0){
			product.get(index).setName(newName);
			product.get(index).setPrice(prices);
			product.get(index).setSize(sizes);
			product.get(index).setType( new DishType(typeName));
		}//End if
	}//End changeProduct
}//End DeliveryManagerController
