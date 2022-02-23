package com.access_and_expose.noroffassignment_6.data.customer;
import com.access_and_expose.noroffassignment_6.model.customer.Customer;
import com.access_and_expose.noroffassignment_6.model.customer.CustomerCountry;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Repository
public interface ICustomerRepository {

    Collection<Customer> getAll();
    Collection<Customer> getAllInRange(int offset, int limit);
    Customer getById(String customerId);
    Collection<Customer> getByName(String firstName);
    Customer add(Customer customer);
    Customer update(Customer customer);
    boolean delete(String customerId);
    LinkedHashMap<String, Integer> sortByCountry();
    Customer getCustomerHighestSpender();
}