package com.hotel.management.Model;

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
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "hotel_id")
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
