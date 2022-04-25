package com.hotel.management.Controller;

import com.hotel.management.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hotel.management.Model.Customer;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/signup")
	public String addUser(@RequestBody Customer data) {
		System.out.println("->"+data);
		
		String password = data.getPassword();
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		password = encoder.encode(password);
		data.setPassword(password);

		customerService.addCustomer(data);
		
		return "success";
	}

	@PutMapping("/update/{id}")
	public String updateCustomer(@RequestBody Customer c, @PathVariable long id){
		customerService.updateCustomer(c, id);
		return "success";
	}


	
	
	
}
