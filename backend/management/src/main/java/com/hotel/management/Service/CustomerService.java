package com.hotel.management.Service;

import com.hotel.management.Model.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    public ResponseEntity<String>  addCustomer(Customer c);
    public ResponseEntity<Customer> updateCustomer(Customer c, long id);
    public Customer getCustomerById(long id);

}
