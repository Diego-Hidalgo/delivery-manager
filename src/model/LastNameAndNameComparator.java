package model;

import java.util.Comparator;

public class LastNameAndNameComparator implements Comparator<Customer>{

    /**
     * compares two customers according to two parameters: Last name and name. <br>
     *     <b>pre:</b> parameters are initialized. <br>
     *     <b>post:</b> The number according to the comparison used to sort the Customer list. <br>
     * @param c1 Current customer to compare with. c1 is initialized and name != null and lastName != null
     * @param c2 Other customer to compare with. c2 is initialized and name != null and lastName != null
     */
    @Override
    public int compare(Customer c1, Customer c2){
        if(c1.getLastName().compareTo(c2.getLastName()) != 0){
            return c1.getLastName().compareTo(c2.getLastName());
        } else{
            return c1.getName().compareTo(c2.getName());
        }
    }//End compare

}//End LastNameAndNameCoparator class