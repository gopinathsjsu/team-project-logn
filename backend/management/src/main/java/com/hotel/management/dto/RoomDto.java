package com.hotel.management.dto;

public class RoomDto {

    private String type;
    private int numberOfRooms;
    private Double rate;

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

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "type='" + type + '\'' +
                ", numberOfRooms='" + numberOfRooms + '\'' +
                ", rate=" + rate +
                '}';
    }
}
