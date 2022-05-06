package com.hotel.management.Controller;

import com.hotel.management.Model.Hotel;
import com.hotel.management.Model.Rewards;
import com.hotel.management.Service.CustomerService;
import com.hotel.management.Service.HotelService;
import com.hotel.management.Service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@Autowired
	HotelService hotelService;

	@Autowired
	RewardsService rewardsService;



	@PostMapping("/signup")
	public ResponseEntity<String> addUser(@RequestBody Customer data) {
		System.out.println("->"+data);
		
		String password = data.getPassword();
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		password = encoder.encode(password);
		data.setPassword(password);

		return customerService.addCustomer(data);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer c, @PathVariable long id){
		customerService.updateCustomer(c, id);
		return customerService.updateCustomer(c, id);
	}

	// hotel-id
	@GetMapping("/enroll/{custid}/{id}")
	public ResponseEntity<String> enrollAsCustomer(@PathVariable Long custid,@PathVariable Long id){
		Hotel hotel = hotelService.getHotelById(id);
		Customer customer = customerService.getCustomerById(custid);

		Rewards rewards = new Rewards();
		rewards.setCustomer(customer);
		rewards.setHotel(hotel);
		rewards.setLoyaltyBonus(5000);

		// TO-DO
		// If customer is already enrolled throw exception


		return rewardsService.enrollCustomer(rewards);

	}

	@GetMapping("/profile/{id}")
	public ResponseEntity<Customer> getCustomerProfile(@PathVariable long id){
		Customer customer = customerService.getCustomerById(id);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}






}
