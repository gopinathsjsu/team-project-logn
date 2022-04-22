package com.hotel.management.Model;

import javax.persistence.*;

@Entity
public class Rewards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rewards_id")
    private long id;

    private double loyaltyBonus;

    @OneToOne
    @JoinColumn(name = "cust_id")
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLoyaltyBonus() {
        return loyaltyBonus;
    }

    public void setLoyaltyBonus(double loyaltyBonus) {
        this.loyaltyBonus = loyaltyBonus;
    }
}
