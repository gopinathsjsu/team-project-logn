package com.hotel.management.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class HotelEmployee {

    public HotelEmployee(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    long id;

    private String email;
    private String password;
    private String name;
//    private String address;
    private String dob;
    private String mobile;

    @ManyToOne()
    @JoinColumn(name = "hotel", referencedColumnName = "hotel_id")
//    @JsonIgnoreProperties({})
    private Hotel hotel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "HotelEmployee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", mobile='" + mobile + '\'' +
                ", hotel=" + hotel +
                '}';
    }
}
