package model;
import java.util.List;
import java.util.ArrayList;

public class DeliveryManagerController {
	private List<Order> order;
	private List<User> user;
	private List<Product> product;
	private List<Employee> employee;
	private List<DishType> types;
	public DeliveryManagerController(){
		order = new ArrayList<Order>();
		user = new ArrayList<User>();
		product = new ArrayList<Product>();
		employee = new ArrayList<Employee>();
		types = new ArrayList<DishType>();
	}//End DeliveryManagerController
	
	public boolean addProduct(String dishType){
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
	
	public int findDishType(String dishType){
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
	public void changeDishType(String oldName, String newName){
		int index = findDishType(oldName);
		if(index >= 0 && findDishType(newName) < 0)
			types.get(index).setName(newName);
	}//End changeIngredient
	
}//End DeliveryManagerController
