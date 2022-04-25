package com.hotel.management.Controller;

import com.hotel.management.Model.HotelEmployee;
import com.hotel.management.Service.HotelEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelEmployeeService hotelEmployeeService;

    @RequestMapping("/")
    public String showHome(){
        return "hello-2";
    }

    @PostMapping("/signup")
    public String addEmployee(@RequestBody HotelEmployee employee){

        System.out.println("->"+employee);

        String password = employee.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
        employee.setPassword(password);

        hotelEmployeeService.addEmployee(employee);

        return "Success";
    }

}
