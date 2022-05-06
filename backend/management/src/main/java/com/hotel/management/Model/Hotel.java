package com.hotel.management.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
public class Hotel {

    public Hotel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    long id;

    private String name;
    private String mobile;

    private String city;
    private String state;
    private String country;

    private String address;

    private double holidayMultiplier;
    private double seasonalMulitplier;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
//    @JsonIgnoreProperties({"elist"})
//    private List<HotelEmployee> elist;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    @JsonIgnoreProperties({"hotel"})
//    @JoinColumn(referencedColumnName = "room_id")
    private List<Rooms> rooms;



    public List<Rooms> getRooms() {
        return rooms;
    }

    public void setRooms(List<Rooms> rooms) {
        this.rooms = rooms;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getHolidayMultiplier() {
        return holidayMultiplier;
    }

    public void setHolidayMultiplier(double holidayMultiplier) {
        this.holidayMultiplier = holidayMultiplier;
    }

    public double getSeasonalMulitplier() {
        return seasonalMulitplier;
    }

    public void setSeasonalMulitplier(double seasonalMulitplier) {
        this.seasonalMulitplier = seasonalMulitplier;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", holidayMultiplier=" + holidayMultiplier +
                ", seasonalMulitplier=" + seasonalMulitplier +
                ", rooms=" + rooms +
                '}';
    }
}
