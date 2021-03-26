package model;

import java.util.Comparator;

public class LastNameAndNameComparator implements Comparator<Customer>{

    @Override
    public int compare(Customer c1, Customer c2){
        if(c2.getLastName().compareTo(c1.getLastName()) != 0){
            return c2.getLastName().compareTo(c1.getLastName());
        } else{
            return c2.getName().compareTo(c1.getName());
        }
    }
}
