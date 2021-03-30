package model;

import java.util.Comparator;

public class LastNameAndNameComparator implements Comparator<Customer>{

    /**
     * compares two customers according to two parameters: Last name and name. <br>
     *     <b>pre:</b> parameters are initialized. <br>
     *     <b>post:</b> The number according to the comparison used to sort the Customer list. <br>
     * @param c1 Current customer to compare with. c1 is initialized and name != null and lastName != null
     * @param c2 Other customer to compare with. c2 is initialized and name != null and lastName != null
     * @return a number according to the comparison
     */
    @Override
    public int compare(Customer c1, Customer c2){
        if(c2.getLastName().compareTo(c1.getLastName()) != 0){
            return c2.getLastName().compareTo(c1.getLastName());
        } else{
            return c2.getName().compareTo(c1.getName());
        }
    }
}
