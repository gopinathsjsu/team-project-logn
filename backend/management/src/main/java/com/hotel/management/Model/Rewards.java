package com.hotel.management.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Rewards {

//    public Rewards(){
//
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rewards_id")
    private long id;

    private double loyaltyBonus;

    @OneToOne
    @JoinColumn(name = "cust_id")
    @JsonIgnoreProperties({"password","address","mobile","email"})
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "hotel_id")
    @JsonIgnoreProperties({"rooms","state","country","address","holidayMultiplier","seasonalMulitplier"})
    private Hotel hotel;

//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public double getLoyaltyBonus() {
//        return loyaltyBonus;
//    }
//
//    public void setLoyaltyBonus(double loyaltyBonus) {
//        this.loyaltyBonus = loyaltyBonus;
//    }
//
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
//
//    public Hotel getHotel() {
//        return hotel;
//    }
//
//    public void setHotel(Hotel hotel) {
//        this.hotel = hotel;
//    }
}
