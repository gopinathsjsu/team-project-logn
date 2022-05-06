package com.hotel.management.Model;

//import java.sql.Date;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor
public class Customer {
    public Customer(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private long id;

    private String email;
    private String password;
    private String name;
    private String address;
    
//    @Temporal(TemporalType.DATE)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
//    private Date dob;
    
    private String mobile;

    private String role;


}
