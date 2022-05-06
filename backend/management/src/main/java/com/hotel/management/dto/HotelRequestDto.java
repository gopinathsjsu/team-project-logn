package com.hotel.management.dto;

import java.util.List;

public class HotelRequestDto {

    private String name;
    private String mobile;

    private String city;
    private String state;
    private String country;
    private String address;
    private Double holidayMultiplier;
    private Double seasonalMulitplier;

    private List<RoomDto> roomDtoList;

    @Override
    public String toString() {
        return "HotelRequestDto{" +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", holidayMultiplier=" + holidayMultiplier +
                ", seasonalMulitplier=" + seasonalMulitplier +
                ", roomDtoList="  + roomDtoList +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getHolidayMultiplier() {
        return holidayMultiplier;
    }

    public void setHolidayMultiplier(Double holidayMultiplier) {
        this.holidayMultiplier = holidayMultiplier;
    }

    public Double getSeasonalMulitplier() {
        return seasonalMulitplier;
    }

    public void setSeasonalMulitplier(Double seasonalMulitplier) {
        this.seasonalMulitplier = seasonalMulitplier;
    }

    public List<RoomDto> getRoomDtoList() {
        return roomDtoList;
    }

    public void setRoomDtoList(List<RoomDto> roomDtoList) {
        this.roomDtoList = roomDtoList;
    }
}
