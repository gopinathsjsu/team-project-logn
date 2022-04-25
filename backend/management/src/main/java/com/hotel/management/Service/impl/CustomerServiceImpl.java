package com.hotel.management.Service.impl;

import com.hotel.management.Model.Customer;
import com.hotel.management.Repository.CustomerRepository;
import com.hotel.management.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer c) {
        customerRepository.save(c);
        return c;
    }

    @Override
    public Customer updateCustomer(Customer c, long id) {

        Customer cust = customerRepository.findById(id).orElse(null);

        cust.setDob(c.getDob());
        cust.setEmail(c.getEmail());
        cust.setAddress(c.getAddress());
        cust.setName(c.getName());
        cust.setMobile(c.getMobile());

        customerRepository.save(cust);

        return c;
    }
}
