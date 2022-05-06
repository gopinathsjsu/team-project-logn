package com.hotel.management.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.ToString;

import javax.persistence.*;

@Entity
@ToString
public class RoomBooked {

    public RoomBooked(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booked_id")
    private long id;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "amd_id")
//    @JsonIgnore
    private Amenities amenities;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @JsonIgnoreProperties({"hotel"})
    private Rooms rooms;

    private double rate;

//    @ManyToOne
//    @JoinColumn(name="booking", referencedColumnName="booking_id")
//    private Booking booking;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Amenities getAmenities() {
        return amenities;
    }

    public void setAmenities(Amenities amenities) {
        this.amenities = amenities;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }



}
