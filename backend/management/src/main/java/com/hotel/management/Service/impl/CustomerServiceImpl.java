package com.hotel.management.Service.impl;

import com.hotel.management.Model.Customer;
import com.hotel.management.Repository.CustomerRepository;
import com.hotel.management.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ResponseEntity<String> addCustomer(Customer c) {
        Customer customer = customerRepository.findByEmail(c.getEmail());
        if(customer!=null){
            return new ResponseEntity<>("user already exists", HttpStatus.BAD_REQUEST);
        }

        customerRepository.save(c);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer>  updateCustomer(Customer c, long id) {

        Customer cust = customerRepository.findById(id).orElse(null);

//        cust.setDob(c.getDob());
        cust.setEmail(c.getEmail());
        cust.setAddress(c.getAddress());
        cust.setName(c.getName());
        cust.setMobile(c.getMobile());

        Customer result = customerRepository.saveAndFlush(cust);
//        result.setPassword(null);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @Override
    public Customer  getCustomerById(long id) {
        return customerRepository.findById(id).orElse(null);
    }
}
