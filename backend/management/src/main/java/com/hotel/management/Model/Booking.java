package com.hotel.management.Model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
//@Getter @Setter
public class Booking {
    public Booking() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private long id;

//    private String roomType;

//    @Temporal(TemporalType.DATE)
//    Date stayFrom;
    private String stayFrom;
    private String StayUpto;

//    private double roomrate;
    private double totalBill;

    private boolean isCancelled=false;

//    private int numberOfAdults;
//    private int numberOfChildren;
//    private int numberOfRooms;
    private int numberOfGuests;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hotel_id")
    @JsonIgnoreProperties({"rooms","city","state","country","holidayMultiplier","seasonalMulitplier"})
//    @JsonIgnore
    private Hotel hotel;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cust_id")
//    @JsonIgnore
    @JsonIgnoreProperties({"email","password","address","dob","mobile"})
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hotel","rooms"})
//    @JsonIgnore
    private List<RoomBooked> roomBookedList;

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", stayFrom='" + stayFrom + '\'' +
                ", StayUpto='" + StayUpto + '\'' +
                ", totalBill=" + totalBill +
                ", isCancelled=" + isCancelled +
                ", numberOfGuests=" + numberOfGuests +
                ", hotel=" + hotel +
                ", customer=" + customer +
                ", roomBookedList=" + roomBookedList +
                '}';
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStayFrom() {
        return stayFrom;
    }

    public void setStayFrom(String stayFrom) {
        this.stayFrom = stayFrom;
    }

    public String getStayUpto() {
        return StayUpto;
    }

    public void setStayUpto(String stayUpto) {
        StayUpto = stayUpto;
    }

    public double getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<RoomBooked> getRoomBookedList() {
        return roomBookedList;
    }

    public void setRoomBookedList(List<RoomBooked> roomBookedList) {
        this.roomBookedList = roomBookedList;
    }
}
