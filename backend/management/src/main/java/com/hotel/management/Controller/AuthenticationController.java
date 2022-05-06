package com.hotel.management.Controller;

import com.hotel.management.Exception.UserAlreadyExistException;
import com.hotel.management.Model.Customer;
import com.hotel.management.Model.JwtRequest;
import com.hotel.management.Model.JwtResponse;
import com.hotel.management.Service.CustomerService;
import com.hotel.management.Service.HotelService;
import com.hotel.management.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping("/")
public class AuthenticationController {
    @Autowired
    CustomerService customerService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    HotelService hotelService;
    @Autowired
    private JWTUtility jwtUtility;

    @PostMapping("/signup")
    public ResponseEntity<String> addUser(@RequestBody Customer data) throws UserAlreadyExistException {
        System.out.println("->"+data);

        String password = data.getPassword();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
        data.setPassword(password);

        return customerService.addCustomer(data);
    }


    @PostMapping("/login")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = customerService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);


        return  new JwtResponse(token);
    }
}
