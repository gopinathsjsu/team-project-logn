package com.hotel.management.Model;

import javax.persistence.*;

@Entity
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long id;

    private String type;
    private int numberOfRooms;

    private double rate;

    @ManyToOne
    @JoinColumn(name="hotel", referencedColumnName="hotel_id")
    private Hotel hotel;

    public Rooms(){

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "Rooms{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", numberOfRooms=" + numberOfRooms +
                ", rate=" + rate +
                '}';
    }
}
