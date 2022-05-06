package com.hotel.management.Service.impl;

import com.hotel.management.Model.Customer;
import com.hotel.management.Repository.CustomerRepository;
import com.hotel.management.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService , UserDetailsService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ResponseEntity<String> addCustomer(Customer c) {
        System.out.println("user email - "+c.getEmail());
        Customer customer = customerRepository.findByEmail(c.getEmail());

        if(customer!=null){
            return new ResponseEntity<>("user already exists", HttpStatus.BAD_REQUEST);
        }

        customerRepository.save(c);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //Logic to get the user form the Database
        Customer customer = customerRepository.findByEmail(userName);
        if(customer != null){
            List<GrantedAuthority> authorities = Arrays.stream(customer.getRole().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            return new User(customer.getEmail(),customer.getPassword(),authorities);
        }else{
            throw  new UsernameNotFoundException("User does Not Exist");
        }


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
