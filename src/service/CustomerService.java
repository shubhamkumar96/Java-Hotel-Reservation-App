package service;

import model.Customer;

import java.util.*;

public class CustomerService {
    private static CustomerService customerService = null;
    static Map<String, Customer> customers = new HashMap<String, Customer>();

    private CustomerService() {
    }

    public static CustomerService getInstance() {
        if(customerService == null) {
            customerService = new CustomerService();
        }
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        customers.put(email, new Customer(firstName, lastName, email));
    }

    public Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        ArrayList<Customer> customerList = new ArrayList<>();
        for(Customer customer: customers.values()) {
            customerList.add(customer);
        }
        return customerList;
    }

}
