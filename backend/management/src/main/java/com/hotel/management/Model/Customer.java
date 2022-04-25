package com.hotel.management.Model;

//import java.sql.Date;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private long id;

    private String email;
    private String password;
    private String name;
    private String address;
    
//    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy") 
    private Date dob;
    
    private String mobile;

    public long getId() {
        return id;
    }

    @Override
	public String toString() {
		return "Customer [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", address="
				+ address + ", dob=" + dob + ", mobile=" + mobile + "]";
	}

	public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Date getDob() {
        return dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
