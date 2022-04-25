package com.hotel.management.Service;

import com.hotel.management.Model.Customer;

public interface CustomerService {

    public Customer addCustomer(Customer c);
    public Customer updateCustomer(Customer c, long id);
}
