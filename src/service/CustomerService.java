package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    static Map<String, Customer> customers = new HashMap<String, Customer>();


    public static void addCustomer(String email, String firstName, String lastName) {
        customers.put(email, new Customer(firstName, lastName, email));
    }

    public static Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }

    public static Collection<Customer> getAllCustomers() {
        ArrayList<Customer> customerList = new ArrayList<>();
        for(Customer customer: customers.values()) {
            customerList.add(customer);
        }
        return customerList;
    }

}
